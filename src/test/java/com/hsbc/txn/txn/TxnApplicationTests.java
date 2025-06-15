package com.hsbc.txn.txn;

import com.hsbc.txn.service.TxnService;
import com.hsbc.txn.vo.TxnVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;



@SpringBootTest
class TxnApplicationTests {
	@Autowired
	private TxnService txnService;

	@Test
	public void testGetAllTxns() throws Exception {
		// Arrange
		List<TxnVo> allTxn =txnService.getAllTxns();

	}

	@Test
	public void testPageQueryTxns() throws Exception {
		// Arrange


	}

	@Test
	public void testCreateTransaction() throws Exception {
		// Arrange


	}

	@Test
	public void testCreateTransactionAndReturnExists() throws Exception {
		// Arrange


	}



	@Test
	public void testModifiedTransaction() throws Exception {
		// Arrange


	}

	@Test
	public void testModifiedTransactionNotExist() throws Exception {
		// Arrange


	}

	@Test
	public void testDeleteTransaction() throws Exception {

	}

	@Test
	public void testDeleteTransactionReturnNotExist() throws Exception {

	}


}
