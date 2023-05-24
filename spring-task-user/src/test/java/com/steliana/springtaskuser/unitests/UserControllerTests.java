package com.steliana.springtaskuser.unitests;

import com.steliana.springtaskuser.api.UserRequest;
import com.steliana.springtaskuser.api.UserResponse;
import com.steliana.springtaskuser.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class UserControllerTests extends AbstractTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getUsersList() throws Exception {
        String uri = "/users/";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        User[] userList = super.mapFromJson(content, User[].class);
        assertTrue(userList.length > 0);
    }


    @Test
    public void getUser() throws Exception {
        UUID id = UUID.randomUUID();
        String uri = "/users/" + id;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        UserResponse user = super.mapFromJson(content, UserResponse.class);
        assertTrue(user != null);
    }

    @Test
    public void createUser() throws Exception {
        String uri = "/users/";
        UserRequest user = new UserRequest();
        user.setFirstname("Test");
        user.setSurname("Testopoulos");
        user.setPersonalID("AB123456");
        user.setPhoneNumber("+301234567890");
        user.setEmail("test@test.com");

        String inputJson = super.mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

    @Test
    public void updateUser() throws Exception {
        UUID id = UUID.randomUUID();
        String uri = "/users/" + id;
        UserRequest user = new UserRequest();
        user.setFirstname("Test");
        user.setSurname("Testopoulos");
        user.setPersonalID("AB123456");
        user.setPhoneNumber("+301234567890");
        user.setEmail("test@test.com");

        String inputJson = super.mapToJson(user);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    public void deleteUser() throws Exception {
        UUID id = UUID.randomUUID();
        String uri = "/users/" + id;

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }
}
