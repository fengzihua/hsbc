package com.hsbc.txn.txn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@SpringBootTest
@AutoConfigureMockMvc
class TxnApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetAllTxns() throws Exception {
		// Arrange
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/txn/all")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}

	@Test
	public void testPageQueryTxns() throws Exception {
		// Arrange
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/api/txn/page")
				.param("page", "1")
				.param("size", "10")
				.param("isAsc", "true")
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());

	}

	@Test
	public void testCreateTransaction() throws Exception {
		// Arrange
		String jsonRequest = "{ \"userId\": 1, \"tid\": \" 7543b844c735b82737fz395dcfe33ef3 \", \"type\": \"deposit\", \"amount\": 100.0, \"currency\": \"USD\", \"transactionTime\": 1743305743000 }";

		//transaction already exists
		mockMvc.perform(post("/api/txn/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").value(1));

	}

	@Test
	public void testCreateTransactionAndReturnExists() throws Exception {
		// Arrange
		String jsonRequest = "{ \"userId\": 1, \"tid\": \"7543b844c735b82737fz395dcfe33ef3\", \"type\": \"deposit\", \"amount\": 100.0, \"currency\": \"USD\", \"transactionTime\": 1743305743000 }";


		//transaction already exists
		mockMvc.perform(post("/api/txn/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("txn already exists"));

	}



	@Test
	public void testModifiedTransaction() throws Exception {
		// Arrange
		String jsonRequest = "{ \"userId\": 1, \"tid\": \"c8fb50c8692z9z15230b9142zf4ef56e\", \"type\": \"deposit\", \"amount\": 200.0, \"currency\": \"USD\", \"transactionTime\": 1743305743000 }";

		//assert
		mockMvc.perform(post("/api/txn/modify")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").value(1));

	}

	@Test
	public void testModifiedTransactionNotExist() throws Exception {
		// Arrange
		String jsonRequest = "{ \"userId\": 1, \"tid\": \"c8fb50c8692z9z15230b9142zf4ef56e\", \"type\": \"deposit\", \"amount\": 200.0, \"currency\": \"USD\", \"transactionTime\": 1743305743000 }";

		//assert
		mockMvc.perform(post("/api/txn/modify")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("txn not found"));

	}

	@Test
	public void testDeleteTransaction() throws Exception {
		// Arrange
		String jsonRequest = "{ \"userId\": 1, \"tid\": \"c8fb50c8692z9z15230b9142zf4ef56e\", \"type\": \"deposit\", \"amount\": 100.0, \"currency\": \"USD\", \"transactionTime\": 1743305743000 }";

		//create transaction
		mockMvc.perform(post("/api/txn/create")
						.contentType(MediaType.APPLICATION_JSON)
						.content(jsonRequest))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").value(1));

		String tid = "c8fb50c8692z9z15230b9142zf4ef56e";

		//delete and assert
		mockMvc.perform(delete("/api/txn/delete/" + tid)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.data").value("c8fb50c8692z9z15230b9142zf4ef56e"));
	}

	@Test
	public void testDeleteTransactionReturnNotExist() throws Exception {

		String tid = "c8fb50c8692z9z15230b9142zf4ef56e";

		//assert
		mockMvc.perform(delete("/api/txn/delete/" + tid)
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.message").value("txn not found"));
	}


}
