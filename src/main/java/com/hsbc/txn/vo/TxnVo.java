package com.hsbc.txn.vo;

import com.hsbc.txn.db.Txn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TxnVo {
    private Long id;

    @Schema(description = "用户ID")
    @NotNull
    private Long userId;

    @Schema(description = "transaction ID")
    @NotNull
    private String tid;

    @Schema(description = "交易类型")
    @NotNull
    private String type;

    @Schema(description = "交易金额")
    @NotNull
    private Double amount;

    @Schema(description = "货币类型")
    @NotNull
    private String currency;

    @Schema(description = "交易时间，unix毫秒时间戳", example = "1743305743000")
    @NotNull
    private Long txnTime;

    @Schema(description = "目标用户ID")
    private Long targetUid;

    @Schema(description = "链接键")
    private String linkKey;

    public TxnVo(Txn txn) {
        this.id = txn.getId();
        this.amount  = txn.getAmount();
        this.tid = txn.getTid();
        this.type = txn.getType();
        this.txnTime = txn.getTxnTime();
        this.userId = txn.getUserId();
        this.targetUid = txn.getTargetUid();
    }

    public Txn toEntity() {
        Txn txn = new Txn();
        txn.setId(this.id);
        txn.setTid(this.tid);
        txn.setUserId(this.userId);
        txn.setType(this.type);
        txn.setAmount(this.amount);
        txn.setTxnTime(this.txnTime);
        txn.setTargetUid(this.targetUid);
        return txn;
    }
}
