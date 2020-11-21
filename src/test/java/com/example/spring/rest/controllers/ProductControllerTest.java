package com.example.spring.rest.controllers;

import com.example.spring.rest.models.Product;
import com.example.spring.rest.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebMvcTest(value = ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    void beforeEach() {
        final Map<Long, Product> productMap = new HashMap<>();
        productMap.put(1L, new Product(1, "Pen", new BigDecimal(100)));
        productMap.put(2L, new Product(2, "NoteBook", new BigDecimal(200)));
        Mockito.when(productService.getAllProducts()).thenReturn(new ArrayList<>(productMap.values()));
        Mockito.when(productService.getProduct(1)).thenReturn(productMap.get(1L));
        Mockito.when(productService.addProduct(Mockito.any(Product.class))).thenReturn(productMap.get(2L));
        Mockito.when(productService.updateProduct(ArgumentMatchers.eq(1L), Mockito.any(Product.class))).thenReturn(productMap.get(2L));
        Mockito.when(productService.deleteProduct(1)).thenReturn(productMap.get(1L));
    }

    @Test
    void getAllTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/products")
                .accept(MediaType.APPLICATION_JSON);
        String actual = mockMvc
                .perform(requestBuilder)
                .andReturn()
                .getResponse()
                .getContentAsString();
        String expected = "[{\"id\":1,\"name\":\"Pen\",\"price\":100},{\"id\":2,\"name\":\"NoteBook\",\"price\":200}]";
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    void getTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/products/1")
                .accept(MediaType.APPLICATION_JSON);
        String actual = mockMvc
                .perform(requestBuilder)
                .andReturn()
                .getResponse()
                .getContentAsString();
        String expected = "{\"id\":1,\"name\":\"Pen\",\"price\":100}";
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    void add() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/v1/products")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Pen\",\"price\":100}")
                .contentType(MediaType.APPLICATION_JSON);
        String actual = mockMvc
                .perform(requestBuilder)
                .andReturn()
                .getResponse()
                .getContentAsString();
        String expected = "{\"id\":2,\"name\":\"NoteBook\",\"price\":200}";
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    void updateTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/api/v1/products/1")
                .accept(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"Pen\",\"price\":200}")
                .contentType(MediaType.APPLICATION_JSON);
        String actual = mockMvc
                .perform(requestBuilder)
                .andReturn()
                .getResponse()
                .getContentAsString();
        String expected = "{\"id\":2,\"name\":\"NoteBook\",\"price\":200}";
        JSONAssert.assertEquals(expected, actual, false);
    }

    @Test
    void deleteTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/v1/products/1")
                .accept(MediaType.APPLICATION_JSON);
        String actual = mockMvc
                .perform(requestBuilder)
                .andReturn()
                .getResponse()
                .getContentAsString();
        String expected = "{\"id\":1,\"name\":\"Pen\",\"price\":100}";
        JSONAssert.assertEquals(expected, actual, false);
    }
}