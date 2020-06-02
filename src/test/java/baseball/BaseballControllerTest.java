package baseball;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = BaseballController.class)
class BaseballControllerTest {
    @MockBean
    BaseballService baseballService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void createGame() throws Exception {
        given(baseballService.createGame()).willReturn(1L);

        mockMvc.perform(post("/baseball"))
            .andDo(print())
            .andExpect(status().isCreated());
    }
}