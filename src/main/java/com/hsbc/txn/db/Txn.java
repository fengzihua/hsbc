package com.hsbc.txn.db;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "txn")
public class Txn {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id",nullable = false)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String tid;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Double amount;

    @Column(name = "txn_time", nullable = false)
    private Long txnTime;

    @Column(name = "target_uid", nullable = false)
    private Long targetUid;


    @Column(name = "created_time", updatable = false)
    private LocalDateTime  createdTime;

    @Column(name = "update_time", updatable = false)
    private LocalDateTime  updateTime;
}
