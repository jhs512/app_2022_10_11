package com.ll.exam.app__2022_10_11.app.song.service;

import com.ll.exam.app__2022_10_11.app.member.entity.Member;
import com.ll.exam.app__2022_10_11.app.song.entity.Song;
import com.ll.exam.app__2022_10_11.app.song.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SongService {
    private final SongRepository songRepository;

    @Transactional
    public Song create(Member author, String subject, String content) {
        Song song = Song.builder()
                .subject(subject)
                .content(content)
                .author(author)
                .build();
        songRepository.save(song);

        return song;
    }

    public Optional<Song> findById(long songId) {
        return songRepository.findById(songId);
    }

    @Transactional
    public void modify(Song song, String subject, String content) {
        song.setSubject(subject);
        song.setContent(content);
    }

    public boolean actorCanModify(Member author, Song song) {
        return author.getId().equals(song.getAuthor().getId());
    }

    public boolean actorCanDelete(Member author, Song song) {
        return actorCanModify(author, song);
    }

    public Optional<Song> findForPrintById(long id) {
        Optional<Song> opSong = findById(id);

        if (opSong.isEmpty()) return opSong;

        return opSong;
    }
}
