package com.talentica.walletproducer.service;

import com.talentica.walletproducer.dto.AddWithdrawFundsDto;
import com.talentica.walletproducer.dto.StripeTokenDto;
import com.talentica.walletproducer.dto.UsersDto;
import com.talentica.walletproducer.entity.UserEntity;
import com.talentica.walletproducer.exception.InvalidStripeCardException;
import com.talentica.walletproducer.exception.NoUserFoundException;
import com.talentica.walletproducer.mapper.UserMapper;
import com.talentica.walletproducer.repository.UserRepository;
import com.talentica.walletproducer.service.kafka.WalletProducer;
import com.talentica.walletproducer.validation.UserValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddFundsServiceImpl implements AddFundsService {

    private final UserValidationUtil userValidationUtil;
    private final WalletProducer walletProducer;
    private final UserRepository userRepository;
    private final StripeServiceImpl stripeService;

    @Override
    public void publishAddFunds(AddWithdrawFundsDto addWithdrawFundsDto) {

        UsersDto addFundsUser =
                UserMapper.USER_MAPPER.convertUserEntityToUsersDto(
                        userRepository.
                                findById
                                        (Long.parseLong(addWithdrawFundsDto.getUserId()))
                                .orElseGet(UserEntity::new));

        if (userValidationUtil.checkIfUserValid(addFundsUser)) {
            log.info((String.format("AddFundsServiceImpl::publishAddFunds -> " +
                            "User Validation Check Successful for %s ",
                    addWithdrawFundsDto.toString())));
            StripeTokenDto stripeTokenDto = StripeTokenDto
                    .builder()
                    .cardNumber(addWithdrawFundsDto.getStripeDetails().getCardNumber())
                    .expMonth(addWithdrawFundsDto.getStripeDetails().getExpMonth())
                    .expYear(addWithdrawFundsDto.getStripeDetails().getExpYear())
                    .cvv(addWithdrawFundsDto.getStripeDetails().getCvv())
                    .build();

            if (stripeService.createCardToken(stripeTokenDto).isSuccessFlag()){
                log.info(String.format("AddFundsServiceImpl::publishAddFunds -> Stripe Token Generated for %s",addFundsUser.toString()));
                addWithdrawFundsDto
                        .getStripeDetails()
                        .setStripeToken(stripeTokenDto.getToken());
                walletProducer.addFundsPublish(addWithdrawFundsDto);
            }
            else {
                throw new InvalidStripeCardException("Invalid Stripe Card Details");
            }
        } else {
            throw new NoUserFoundException("User does not exist");
        }
    }
}
