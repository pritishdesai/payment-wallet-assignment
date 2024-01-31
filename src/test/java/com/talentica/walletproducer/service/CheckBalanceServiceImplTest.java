package com.talentica.walletproducer.service;

import com.talentica.walletproducer.dto.UsersDto;
import com.talentica.walletproducer.entity.UserEntity;
import com.talentica.walletproducer.entity.UserWalletEntity;
import com.talentica.walletproducer.exception.NoUserFoundException;
import com.talentica.walletproducer.repository.UserRepository;
import com.talentica.walletproducer.repository.UserWalletRepository;
import com.talentica.walletproducer.validation.UserValidationUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class CheckBalanceServiceImplTest {

    @Mock
    private UserWalletRepository userWalletRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserValidationUtil userValidationUtil;
    private UserWalletEntity firstUserWalletEntity;
    private UserWalletEntity secondUserWalletEntity;
    private CheckBalanceService checkBalanceService;
    AutoCloseable autoCloseable;
    UsersDto firstUsersDto;
    UsersDto secondUsersDto;


    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        checkBalanceService = new CheckBalanceServiceImpl(userWalletRepository);
        firstUserWalletEntity = UserWalletEntity.builder()
                .balance(new BigDecimal("2000"))
                .userId(Long.parseLong("111"))
                .actionDate(LocalDateTime.now())
                .build();
        secondUserWalletEntity = UserWalletEntity.builder()
                .balance(new BigDecimal("4000"))
                .userId(Long.parseLong("112"))
                .actionDate(LocalDateTime.now())
                .build();
        firstUsersDto = UsersDto.builder()
                .userId("111")
                .build();
        secondUsersDto = UsersDto
                .builder()
                .userId("112")
                .build();


    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void GetBalanceNotAMatch() {
        mock(UserWalletEntity.class);
        mock(UserWalletRepository.class);

        when(userWalletRepository.getBalance("111"))
                .thenReturn(String.valueOf(firstUserWalletEntity.getBalance()));
        ;
        assertNotEquals(checkBalanceService.getBalance(secondUsersDto),
                String.valueOf(firstUserWalletEntity.getBalance()));
    }

    @Test
    void testGetBalance() {
        mock(UserWalletEntity.class);
        mock(UserWalletRepository.class);

        when(userWalletRepository.getBalance("111"))
                .thenReturn(String.valueOf(firstUserWalletEntity.getBalance()));
        ;
        assertEquals(checkBalanceService.getBalance(firstUsersDto),
                String.valueOf(firstUserWalletEntity.getBalance()));
    }

    @Test
    void validateUserNotFound(){
        mock(UserWalletEntity.class);
        mock(UserWalletRepository.class);
        mock(UserRepository.class);
        mock(UserValidationUtil.class);

        assertFalse(userValidationUtil.checkIfUserValid(UsersDto
                .builder().userId("100000").build()));

    }
}