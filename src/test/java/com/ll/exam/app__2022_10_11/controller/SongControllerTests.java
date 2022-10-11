package com.ll.exam.app__2022_10_11.controller;

import com.ll.exam.app__2022_10_11.app.song.controller.SongController;
import com.ll.exam.app__2022_10_11.app.song.entity.Song;
import com.ll.exam.app__2022_10_11.app.song.service.SongService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@ActiveProfiles("test")
public class SongControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private SongService songService;

    @Test
    @DisplayName("음원 업로드 폼")
    @WithUserDetails("user1")
    void t1() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(
                        get("/song/create")
                )
                .andDo(print());

        // THEN
        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(SongController.class))
                .andExpect(handler().methodName("showCreate"))
                .andExpect(content().string(containsString("음원 업로드")));
    }

    @Test
    @DisplayName("음원 업로드")
    @WithUserDetails("user1")
    void t2() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(post("/song/create")
                        .param("subject", "제목")
                        .param("content", "내용")
                        .with(csrf())
                )
                .andDo(print());

        // THEN
        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(handler().handlerType(SongController.class))
                .andExpect(handler().methodName("create"))
                .andExpect(redirectedUrlPattern("/song/**"));

        Long songId = Long.valueOf(resultActions.andReturn().getResponse().getRedirectedUrl().replace("/song/", "").split("\\?", 2)[0]);
        assertThat(songService.findById(songId).isPresent()).isTrue();
    }

    @Test
    @DisplayName("음원 수정 폼")
    @WithUserDetails("user1")
    void t3() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(get("/song/1/modify"))
                .andDo(print());

        // THEN
        resultActions
                .andExpect(status().is2xxSuccessful())
                .andExpect(handler().handlerType(SongController.class))
                .andExpect(handler().methodName("showModify"))
                .andExpect(content().string(containsString("음원 수정")));
    }

    @Test
    @DisplayName("음원 수정")
    @WithUserDetails("user1")
    void t4() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(post("/song/1/modify")
                        .param("subject", "제목1 NEW")
                        .param("content", "내용1 NEW")
                        .with(csrf())
                )
                .andDo(print());

        // THEN
        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(handler().handlerType(SongController.class))
                .andExpect(handler().methodName("modify"))
                .andExpect(redirectedUrlPattern("/song/**"));

        Long songId = Long.valueOf(resultActions.andReturn().getResponse().getRedirectedUrl().replace("/song/", "").split("\\?")[0]);

        Song song = songService.findById(songId).get();

        assertThat(song).isNotNull();
        assertThat(song.getSubject()).isEqualTo("제목1 NEW");
        assertThat(song.getContent()).isEqualTo("내용1 NEW");

    }
}
