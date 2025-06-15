package com.hsbc.txn.txn.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TxnControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllTxn() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/txn/all")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    public void testPageQueryTxn() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/txn/page")
                .param("page", "1")
                .param("size", "10")
                .param("isAsc", "true")
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(builder).andExpect(status().isOk()).andReturn();
        System.out.println(result.getResponse().getContentAsString());

    }

    @Test
    public void testCreateTxn() throws Exception {
        // Arrange
        String jsonRequest = "{ \"userId\": 1, \"tid\": \" 7543b844c735b82737fz395dcfe33ef3 \", \"type\": \"deposit\", \"amount\": 100.0, \"txnTime\": 1743305743000 }";

        //Txn already exists
        mockMvc.perform(post("/txn/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(1));

    }

    @Test
    public void testCreateTxnAndReturnExists() throws Exception {
        // Arrange
        String jsonRequest = "{ \"userId\": 1, \"tid\": \"c8fb50c8692z9z15230b9142zf4ef56e\", \"type\": \"deposit\", \"amount\": 100.0, \"txnTime\": 1743305743000 }";


        //Txn already exists
        mockMvc.perform(post("/txn/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Txn already exists"));

    }



    @Test
    public void testModifiedTxn() throws Exception {
        // Arrange
        String jsonRequest = "{ \"userId\": 1, \"tid\": \"c8fb50c8692z9z15230b9142zf4ef56e\", \"type\": \"deposit\", \"amount\": 200.0, \"txnTime\": 1743305743000 }";

        //assert
        mockMvc.perform(post("/txn/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(1));

    }

    @Test
    public void testModifiedTxnNotExist() throws Exception {
        // Arrange
        String jsonRequest = "{ \"id\":1,\"userId\": 1, \"tid\": \"9dcb2688zz6c310f7737362744\", \"type\": \"deposit\", \"amount\": 200.0, \"txnTime\": 1743305743000 }";

        //assert
        mockMvc.perform(post("/txn/modify")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Txn not found"));

    }

    @Test
    public void testDeleteTxn() throws Exception {
        // Arrange
        String jsonRequest = "{ \"userId\": 1, \"tid\": \"c8fb50c8692z9z15230b9142zf4ef56e98978\", \"type\": \"deposit\", \"amount\": 100.0, \"txnTime\": 1743305743000 }";

        //create Txn
        mockMvc.perform(post("/txn/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value(1));

        String tid = "c8fb50c8692z9z15230b9142zf4ef56e98978";

        //delete and assert
        mockMvc.perform(delete("/txn/delete/" + tid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").value("c8fb50c8692z9z15230b9142zf4ef56e98978"));
    }

    @Test
    public void testDeleteTxnReturnNotExist() throws Exception {

        String tid = "9dcb2688zz6c3";

        //assert
        mockMvc.perform(delete("/txn/delete/" + tid)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Txn not found"));
    }
}
