package com.talentica.walletproducer.service;

import com.talentica.walletproducer.dto.AddWithdrawFundsDto;
import com.talentica.walletproducer.dto.StripeTokenDto;
import com.talentica.walletproducer.dto.UsersDto;
import com.talentica.walletproducer.entity.UserEntity;
import com.talentica.walletproducer.exception.InsufficientBalanceException;
import com.talentica.walletproducer.exception.InvalidStripeCardException;
import com.talentica.walletproducer.exception.NoUserFoundException;
import com.talentica.walletproducer.mapper.UserMapper;
import com.talentica.walletproducer.repository.UserRepository;
import com.talentica.walletproducer.service.kafka.WalletProducer;
import com.talentica.walletproducer.validation.UserValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WithdrawFundsServiceImpl implements WithdrawFundsService{

    private final StripeServiceImpl stripeService;
    private final UserValidationUtil userValidationUtil;
    private final UserRepository userRepository;
    private final WalletProducer walletProducer;

    @Override
    public void publishWithdrawFunds(AddWithdrawFundsDto addWithdrawFundsDto) {
        UsersDto withdrawFundsUser =
                UserMapper.USER_MAPPER.convertUserEntityToUsersDto(
                        userRepository.
                                findById
                                        (Long.parseLong(addWithdrawFundsDto.getUserId()))
                                .orElseGet(UserEntity::new));

        if (userValidationUtil.checkIfUserValid(withdrawFundsUser)) {
            log.info((String.format("WithdrawFundsServiceImpl::publishWithdrawFunds -> " +
                            "User Validation Check Successful for %s ",
                    addWithdrawFundsDto.toString())));
            if(userValidationUtil.checkIfBalanceAvailable(withdrawFundsUser,addWithdrawFundsDto.getAmount())){
                log.info((String.format("WithdrawFundsServiceImpl::publishWithdrawFunds -> " +
                                "User Balance Validation Check Successful for %s ",
                        addWithdrawFundsDto.toString())));
                StripeTokenDto stripeTokenDto = StripeTokenDto
                        .builder()
                        .cardNumber(addWithdrawFundsDto.getStripeDetails().getCardNumber())
                        .expMonth(addWithdrawFundsDto.getStripeDetails().getExpMonth())
                        .expYear(addWithdrawFundsDto.getStripeDetails().getExpYear())
                        .cvv(addWithdrawFundsDto.getStripeDetails().getCvv())
                        .build();

                if (stripeService.createCardToken(stripeTokenDto).isSuccessFlag()){
                    log.info(String.format("WithdrawFundsServiceImpl::publishWithdrawFunds -> Stripe Token Generated for %s",withdrawFundsUser.toString()));
                    addWithdrawFundsDto
                            .getStripeDetails()
                            .setStripeToken(stripeTokenDto.getToken());
                    walletProducer.withdrawFundsPublish(addWithdrawFundsDto);
                }
                else {
                    throw new InvalidStripeCardException("Invalid Stripe Card Details");
                }
            }else {
                throw new InsufficientBalanceException("Insufficient Balance in Wallet!");
            }
        } else {
            throw new NoUserFoundException("User does not exist");
        }
    }
}
