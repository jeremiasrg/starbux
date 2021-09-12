package com.jr.starbux.controller.integration;

import com.jr.starbux.StarbuxApplication;
import com.jr.starbux.request.ClientRequest;
import com.jr.starbux.response.ClientResponse;
import com.jr.starbux.security.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ClientControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    void shouldPostNewClient() {
        ClientRequest client = new ClientRequest();
        client.setEmail("client_test@tester.com");
        client.setName("Tester");
        HttpEntity<ClientRequest> request = new HttpEntity(client);
        ResponseEntity<ClientResponse> response = template.postForEntity("/client", request, ClientResponse.class);


        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
        Assertions.assertNotNull(response.getBody().getId());
    }

    @Test
    void shouldReturnErrorWhenGetClientByIdWithNoToken() {
        ResponseEntity<ClientResponse> response = template.getForEntity("/client/1", ClientResponse.class);
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    void shouldReturnErrorWhenGetAllClientWithNoToken() {
        ResponseEntity<ClientResponse> response = template.getForEntity("/client", ClientResponse.class);
        Assertions.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
}
