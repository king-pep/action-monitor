package com.betvictor.assessment;

import com.betvictor.assessment.domain.User;
import com.betvictor.assessment.repository.UserRepository;
import com.betvictor.assessment.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ActionMonitorApplicationTests {

    ObjectMapper om = new ObjectMapper();
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    MockMvc mockMvc;
    Map<String, User> testData;
    @Before
    public void setup() {
        userRepository.deleteAll();
        testData = getTestData();
    }

    @Test
    public void testSendMessage() throws Exception {

        for (User user : testData.values()) {
          mockMvc.perform(post("/v1/" + user.getUserId())
                            .contentType("application/json")
                            .content(om.writeValueAsString(user)))
                    .andDo(print())
                    .andExpect(status().isOk());

                  }
        User expectedUser = testData.get("user_1");
        User actualUser = userRepository.findById(expectedUser.getUserId()).get();
        assertEquals(expectedUser.getUserId(), actualUser.getUserId());

        expectedUser = testData.get("user_2");
        actualUser = userRepository.findById(expectedUser.getUserId()).get();
        assertEquals(expectedUser.getUserId(), actualUser.getUserId());

    }
    
    @Test
    public void testUpdateMessage() throws Exception {
        //test update by userId
        User userToSave = testData.get("user_1");
        userService.notifyUser(userToSave.getUserId(), userToSave.getMessage());
        User actualUser = userRepository.findById(userToSave.getUserId()).get();
        actualUser.setMessage("new message");
       User expectedUser =  userService.updateUser(actualUser.getUserId(), actualUser.getMessage());


        assertEquals(expectedUser.getMessage(), actualUser.getMessage());


    }
    private Map<String, User> getTestData() {
        Map<String, User> data = new HashMap<>();

        User user_1 = new User(
                "123",
                "Hello world, from user 1",
                new Timestamp(System.currentTimeMillis()));
        data.put("user_1", user_1);
        User user_2 = new User(
                "456",
                "Hello world, from user 2",
                new Timestamp(System.currentTimeMillis()));
        data.put("user_2", user_2);
        return data;
    }

}
