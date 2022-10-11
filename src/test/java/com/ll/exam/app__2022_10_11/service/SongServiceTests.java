package com.ll.exam.app__2022_10_11.service;

import com.ll.exam.app__2022_10_11.app.member.entity.Member;
import com.ll.exam.app__2022_10_11.app.member.repository.MemberRepository;
import com.ll.exam.app__2022_10_11.app.song.entity.Song;
import com.ll.exam.app__2022_10_11.app.song.service.SongService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class SongServiceTests {
    @Autowired
    private SongService songService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("음원 업로드")
    void t1() {
        Member author = memberRepository.findByUsername("user1").get();

        Song song = songService.create(author, "제목", "내용");

        assertThat(song).isNotNull();
        assertThat(song.getSubject()).isEqualTo("제목");
        assertThat(song.getContent()).isEqualTo("내용");
    }

    @Test
    @DisplayName("음원 수정")
    void t2() {
        Song song = songService.findById(1).get();
        songService.modify(song, "제목 new", "내용 new");

        assertThat(song).isNotNull();
        assertThat(song.getSubject()).isEqualTo("제목 new");
        assertThat(song.getContent()).isEqualTo("내용 new");
    }
}

