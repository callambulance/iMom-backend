//package com.el_proyecte_grande.imom.users.controller;
//
//import com.el_proyecte_grande.imom.users.model.User;
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//
//    @Test
//    @DirtiesContext
//    void givenNewTestUser_whenSavingUserToDB_shouldAddUser() throws Exception {
//        User testUserToSaveInDB = new User("TestName", "Test@gmail.com", 33);
//        var testUserInJson = objectMapper.writeValueAsString(testUserToSaveInDB);
//
//        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
//                .post("/save-user")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(testUserInJson))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        String contentAsString = mvcResult.getRequest().getContentAsString();
//        User savedUser = objectMapper.readValue(contentAsString, User.class);
//
//        assertAll(
//                () -> assertEquals(testUserToSaveInDB.getId(), savedUser.getId()),
//                () -> assertEquals(testUserToSaveInDB.getName(), savedUser.getName()),
//                () -> assertEquals(testUserToSaveInDB.getEmail(), savedUser.getEmail()));
//    }
//
//    @Test
//    @DirtiesContext
//    void givenTestUserAlreadyInDB_whenGetUserRequest_shouldReturnSameUser() throws Exception {
//        //Given Test User in DB
//
//        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
//                .get("/users/{userId}", 1))
//                .andDo(print())
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//
//        String getAsString = mvcResult.getResponse().getContentAsString();
//
//        User returnedUser = objectMapper.readValue(getAsString,
//                new TypeReference<>() {});
//
//        assertAll(
//                () -> assertEquals("UserName", returnedUser.getName()),
//        () -> assertEquals("UserEmail", returnedUser.getEmail()));
//
//
//    }
//}
