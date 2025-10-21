package com.example.hellospring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.hellospring.dto.MoveRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

@SpringBootTest
@AutoConfigureMockMvc
class HelloSpringApplicationTests {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    private MockHttpServletRequestBuilder ctx(MockHttpServletRequestBuilder b) {
        return b.contextPath("/api");
    }

    @Test
    void messagesEndpoint() throws Exception {
        mockMvc.perform(ctx(get("/messages")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("Hello, Spring!"))
                .andExpect(jsonPath("$[1].content").value("In-memory storage works"));
    }

    @Test
    void ticTacToeHappyPath() throws Exception {
        String createJson =
                mockMvc.perform(ctx(post("/games")))
                        .andExpect(status().isCreated())
                        .andReturn()
                        .getResponse()
                        .getContentAsString();
        long id = objectMapper.readTree(createJson).get("id").asLong();

        MoveRequest m1 = new MoveRequest(); m1.setX(0); m1.setY(0);
        mockMvc.perform(ctx(post("/games/" + id + "/moves"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(m1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nextPlayer").value("O"))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));

        MoveRequest m2 = new MoveRequest(); m2.setX(1); m2.setY(0);
        mockMvc.perform(ctx(post("/games/" + id + "/moves"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(m2)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nextPlayer").value("X"));

        MoveRequest m3 = new MoveRequest(); m3.setX(0); m3.setY(1);
        mockMvc.perform(ctx(post("/games/" + id + "/moves"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(m3)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nextPlayer").value("O"));

        MoveRequest m4 = new MoveRequest(); m4.setX(1); m4.setY(1);
        mockMvc.perform(ctx(post("/games/" + id + "/moves"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(m4)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nextPlayer").value("X"));

        MoveRequest m5 = new MoveRequest(); m5.setX(0); m5.setY(2);
        mockMvc.perform(ctx(post("/games/" + id + "/moves"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(m5)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("X_WON"));
    }
}
