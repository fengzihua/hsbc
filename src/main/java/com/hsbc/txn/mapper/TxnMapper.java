package com.hsbc.txn.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hsbc.txn.db.Txn;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TxnMapper extends BaseMapper<Txn> {
    String TABLE_NAME = "txn";

    String INSERT_FIELDS = "user_id, tid, type, amount, txn_time, target_uid ";

    String SELECT_FIELDS = "id, user_id, tid, type, amount, txn_time, target_uid,created_time,update_time";

    @Insert("INSERT INTO " + TABLE_NAME + " (" + INSERT_FIELDS + ") VALUES (#{userId}, #{tid}, #{type}, #{amount}, #{txnTime}, #{targetUid})")
    int saveTxn(Txn txn);

    @Select("SELECT " + SELECT_FIELDS + " FROM " + TABLE_NAME + " WHERE tid = #{tid}")
    Txn getTxnByTid(@Param("tid") String tid);

    @Select("SELECT " + SELECT_FIELDS + " FROM " + TABLE_NAME)
    List<Txn> getAllTxn();

    @Update("UPDATE " + TABLE_NAME + " SET user_id = #{userId}, type = #{type}, amount = #{amount},txn_time = #{txnTime}, target_uid = #{targetUid} WHERE tid = #{tid}")
    int updateTxn(Txn txn);

    @Delete("Delete  FROM " + TABLE_NAME + " WHERE tid = #{tid}")
    int deleteTxn(@Param("tid") String tid);
}
