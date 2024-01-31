package com.talentica.walletproducer.service;

import com.talentica.walletproducer.dto.UsersDto;
import com.talentica.walletproducer.entity.UserWalletEntity;
import com.talentica.walletproducer.entity.UserWalletTransactionHistoryEntity;
import com.talentica.walletproducer.repository.UserWalletRepository;
import com.talentica.walletproducer.repository.UserWalletTxnHstRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class TransactionHistoryServiceImplTest {

    @Mock
    private UserWalletTxnHstRepository userWalletTxnHstRepository;
    private UserWalletTransactionHistoryEntity userWalletTransactionHistoryEntity;
    private TransactionHistoryService transactionHistoryService;
    AutoCloseable autoCloseable;
    UsersDto usersDto;

    @BeforeEach
    void setUp() {

        autoCloseable = MockitoAnnotations.openMocks(this);
        transactionHistoryService = new TransactionHistoryServiceImpl(userWalletTxnHstRepository);
        userWalletTransactionHistoryEntity = UserWalletTransactionHistoryEntity
                .builder()
                .userId(Long.parseLong("111"))
                .transactionId(UUID.randomUUID().toString())
                .amount(new BigDecimal("100"))
                .transactionType("Credit")
                .userType("Merchant")
                .actionDate(LocalDateTime.now())
                .build();
        usersDto = UsersDto.builder()
                .userId("111")
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void getTransactionHistory() {

        mock(UserWalletTransactionHistoryEntity.class);
        mock(UserWalletTxnHstRepository.class);

        when(userWalletTxnHstRepository.findAll()).thenReturn(new ArrayList<UserWalletTransactionHistoryEntity>(
                Collections.singleton(userWalletTransactionHistoryEntity)
        ));

        assertEquals(transactionHistoryService.getTransactionHistory(usersDto).get(0).getTransactionId(),
                userWalletTransactionHistoryEntity.getTransactionId());

    }
}