package com.jr.starbux.controller.integration;

import com.jr.starbux.security.entity.User;
import com.jr.starbux.security.request.UserRequest;
import com.jr.starbux.security.response.UserJwtResponse;
import com.jr.starbux.security.response.UserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    void shouldPostUserInfoAndAuthenticate() throws Exception {
        UserRequest user = new UserRequest();
        user.setEmail("admin@admin.com");
        user.setPassword("@#$@#$AA");
        HttpEntity<User> request = new HttpEntity(user);
        ResponseEntity<UserJwtResponse> response = template.postForEntity("/user/authenticate",request, UserJwtResponse.class);

        Assertions.assertNotNull(response.getBody().getToken());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

    }
}
