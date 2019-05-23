package com.urvin.integrationtest.controller;

import com.urvin.ApiDemoApplication;
import com.urvin.entity.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApiDemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIntegrationTest {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Before
    public void init() {
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateCustomerAccount() {
        String url = "http://localhost:" + port + "/customer/add";

        Customer customer = new Customer();
        customer.setCustomerName("Urvin Shah");
        HttpEntity entity = new HttpEntity<Customer>(customer, headers);

        ResponseEntity<String> response = restTemplate.exchange(url,
                HttpMethod.POST, entity, String.class);
        String expectedString = "Account of Urvin Shah created with CustId: 1";
        assertEquals(expectedString,response.getBody());

        assertEquals(200,response.getStatusCodeValue());
    }

}
