package com.hsbc.txn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateTxn {

    @Schema(description = "用户ID")
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @Schema(description = "transaction ID")
    @NotNull(message = "交易ID不能为空")
    private String tid;

    @Schema(description = "交易类型")
    @NotNull(message = "交易类型不能为空")
    private String type;

    @Schema(description = "交易金额")
    @NotNull(message = "交易金额不能为空")
    private Double amount;


    @Schema(description = "交易时间，unix毫秒时间戳", example = "1743305743000")
    @NotNull(message = "交易时间不能为空")
    private Long txnTime;

    @Schema(description = "目标用户ID")
    private Long targetUid;

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
}
