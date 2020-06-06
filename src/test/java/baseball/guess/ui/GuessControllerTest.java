package baseball.guess.ui;

import static baseball.guess.ui.GuessController.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import baseball.guess.application.GuessService;
import baseball.guess.domain.GuessResultType;
import baseball.guess.domain.Hint;

@WebMvcTest(controllers = GuessController.class)
public class GuessControllerTest {
    @MockBean
    GuessService guessService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void guess() throws Exception {
        given(guessService.guess(any(), any())).willReturn(new Hint(1L, GuessResultType.NO_MATCH, 2, 1));

        String inputJson = "{\"guess\":\"123\"}";

        MvcResult mvcResult = mockMvc.perform(post(GUESS_URI + "/1")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(inputJson))
            .andDo(print())
            .andExpect(status().isCreated())
            .andReturn();

        assertThat(mvcResult.getResponse().getContentAsString()).isNotBlank();
    }
}
