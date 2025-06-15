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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Long getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(Long txnTime) {
        this.txnTime = txnTime;
    }

    public Long getTargetUid() {
        return targetUid;
    }

    public void setTargetUid(Long targetUid) {
        this.targetUid = targetUid;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }
}
