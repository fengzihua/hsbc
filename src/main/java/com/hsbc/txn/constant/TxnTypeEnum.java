package com.hsbc.txn.constant;

public enum TxnTypeEnum {
    DEPOSIT("deposit"),
    WITHDRAWAL("withdrawal"),;

    private String type;
    TxnTypeEnum(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
