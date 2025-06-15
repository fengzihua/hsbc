package com.hsbc.txn.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hsbc.txn.db.Txn;
import com.hsbc.txn.exception.BusRequestException;
import com.hsbc.txn.mapper.TxnMapper;
import com.hsbc.txn.vo.CreateTxn;
import com.hsbc.txn.vo.PageVo;
import com.hsbc.txn.vo.TxnVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.hsbc.txn.exception.TxnExceptionType.TXN_ALREADY_EXISTS;
import static com.hsbc.txn.exception.TxnExceptionType.TXN_NOT_FOUND;

@Service
public class TxnService {

    @Autowired
    private TxnMapper txnMapper;

    @CacheEvict(value = "txnCache")
    @Transactional
    public Integer create(CreateTxn createTxn) {
        Txn oldTxn = txnMapper.getTxnByTid(createTxn.getTid());
        if(oldTxn != null) {
            throw new BusRequestException(TXN_ALREADY_EXISTS.getCode(), TXN_ALREADY_EXISTS.getMessage());
        }
        Txn txn = new Txn();
        BeanUtils.copyProperties(createTxn,txn);
        return txnMapper.saveTxn(txn);
    }

    @CacheEvict(value = "txnCache")
    @Transactional
    public Integer modify(TxnVo vo) {
        Txn newTxn = vo.toEntity();
        Txn oldTxn = txnMapper.getTxnByTid(newTxn.getTid());
        if(oldTxn == null) {
            throw new BusRequestException(TXN_NOT_FOUND.getCode(), TXN_NOT_FOUND.getMessage());
        }
        newTxn.setId(oldTxn.getId());
        return txnMapper.updateTxn(newTxn);
    }

    @CacheEvict(value = "txnCache")
    @Transactional
    public Integer deleteTxn(String tid) {
        Txn oldTransaction = txnMapper.getTxnByTid(tid);
        if(oldTransaction == null) {
            throw new BusRequestException(TXN_NOT_FOUND.getCode(), TXN_NOT_FOUND.getMessage());
        }
        return txnMapper.deleteTxn(tid);
    }

    @Cacheable(value = "txnCache")
    public List<TxnVo> getAllTxns() {
        return txnMapper.getAllTxn().stream().map(TxnVo::new).toList();
    }

    @Cacheable(value = "txnCache")
    public PageVo<TxnVo> pageQueryTxn(Long pageNum, Long pageSize, Boolean isAsc) {
        Page<Txn> page = new Page<>(pageNum, pageSize,true);
        LambdaQueryWrapper<Txn> lambdaQueryWrapper= new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderBy(true, isAsc, Txn::getTxnTime);
        Page<Txn> txnPage = txnMapper.selectPage(page, lambdaQueryWrapper);

        return new PageVo(txnPage.getRecords().stream().map(TxnVo::new).toList(), txnPage.getTotal(), txnPage.getCurrent(), txnPage.getSize());

    }
}
