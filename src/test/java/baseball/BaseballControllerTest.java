package baseball;

import static baseball.BaseballController.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import baseball.dto.BaseballAnswerResponse;

@WebMvcTest(controllers = BaseballController.class)
class BaseballControllerTest {
    @MockBean
    BaseballService baseballService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void createGame() throws Exception {
        given(baseballService.createGame())
            .willReturn(1L);

        mockMvc.perform(post(BASEBALL_URI))
            .andDo(print())
            .andExpect(status().isCreated());
    }

    @Test
    void showAnswer() throws Exception {
        String gameId = "1";

        given(baseballService.showAnswer(gameId))
            .willReturn(BaseballAnswerResponse.of("123"));

        mockMvc.perform(get(BASEBALL_URI + "/" + gameId + "/answer"))
            .andDo(print())
            .andExpect(status().isOk());
    }
}