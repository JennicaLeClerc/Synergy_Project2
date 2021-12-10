package com.revature.shms.controllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.shms.auth.AuthenticationRequest;
import com.revature.shms.controllers.AuthenticationController;
import com.revature.shms.controllers.UserController;
import com.revature.shms.enums.Roles;
import com.revature.shms.models.User;
import com.revature.shms.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:test-application.properties")
class UserControllerTest {
    @Autowired
    private AuthenticationController authenticationController;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserController userController;

    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

//    @Test
//    void getUserWithIdWhenUserExistReturnUser() throws Exception {
//        User user = new User();
//        user.setUsername("mike");
//        user.setPassword("123");
//        int userId = userRepository.save(user).getUserID();
////        AuthenticationRequest authenticationRequest = new AuthenticationRequest();
//        authenticationRequest.setRole(Roles.USER);
//        authenticationRequest.setUsername("mike");
//        authenticationRequest.setPassword("123");
//        mockMvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/authenticate")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(mapper.writeValueAsString(authenticationRequest))
//                        .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.jwt").hasJsonPath())
//                //.andDo(MockMvcResultHandlers.print())
//                .andReturn();

//        String content = result.getResponse().getContentAsString();
//        String jwt = content.substring(8, content.length()-2);
//        System.out.println(jwt);
//        System.out.println("doing next test");
//            mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
//            mockMvc.perform(MockMvcRequestBuilders.get("/users/{userId}", userId)
//                            //.header(HttpHeaders.AUTHORIZATION, jwt
//                            .contentType(MediaType.APPLICATION_JSON)
//                            .accept(MediaType.APPLICATION_JSON))
//                    .andDo(print())
//                    .andExpect(status().isOk());
//              .andExpect(jsonPath("$.username").value("mike"))
//                .andExpect(jsonPath("$.password").value("123"))
//    }
}


