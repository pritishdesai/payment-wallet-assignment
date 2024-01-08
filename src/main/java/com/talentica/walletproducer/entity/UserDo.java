package com.talentica.walletproducer.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class UserDo {

    @Id
    private Long userId;
    private String userName;
    private String userType;
}
