package com.hsbc.txn.controller;

import com.hsbc.txn.service.TxnService;
import com.hsbc.txn.vo.ApiBaseResponse;
import com.hsbc.txn.vo.CreateTxn;
import com.hsbc.txn.vo.PageVo;
import com.hsbc.txn.vo.TxnVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@Tag(name = "交易管理api", description = "提供交易的创建、修改、查询和删除功能")
@RequestMapping("/txn")
public class TxnController {
    @Autowired
    private TxnService txnService;

    @Operation(summary = "创建交易")
    @PostMapping(value = "/create")
    public ResponseEntity<ApiBaseResponse<Integer>> create(@RequestBody @Validated CreateTxn createTxn) {
        int result = txnService.create(createTxn);
        return new ResponseEntity<>(ApiBaseResponse.success(result), HttpStatus.OK);
    }

    @Operation(summary = "更改交易")
    @PostMapping(value = "/modify")
    public ResponseEntity<ApiBaseResponse<Integer>> modify(@RequestBody @Validated TxnVo vo) {
        int result =  txnService.modify(vo);
        return new ResponseEntity<>(ApiBaseResponse.success(result), HttpStatus.OK);
    }

    @Operation(summary = "删除交易")
    @DeleteMapping("/delete/{tid}")
    public  ResponseEntity<ApiBaseResponse<String>> deleteTxn(@PathVariable String tid) {
        txnService.deleteTxn(tid);
        return new ResponseEntity<>(ApiBaseResponse.success(tid), HttpStatus.OK);
    }

    @Operation(summary = "查询全部交易")
    @GetMapping(value = "/all")
    public ResponseEntity<ApiBaseResponse<List<TxnVo>>> getAllTxns() {
        List<TxnVo> allData = txnService.getAllTxns();
        return new ResponseEntity<>(ApiBaseResponse.success(allData), HttpStatus.OK);
    }

    @Operation(summary = "分页查询交易")
    @GetMapping(value = "/page")
    public ResponseEntity<ApiBaseResponse<PageVo<TxnVo>>> getTxnByPage(
            @RequestParam(value = "page", defaultValue = "1") long page,
            @RequestParam(value = "size", defaultValue = "10") long size,
            @RequestParam(value = "isAsc", defaultValue = "false") boolean isAsc){
        return new ResponseEntity<>(ApiBaseResponse.success(txnService.pageQueryTxn(page, size, isAsc)), HttpStatus.OK);
    }

}
