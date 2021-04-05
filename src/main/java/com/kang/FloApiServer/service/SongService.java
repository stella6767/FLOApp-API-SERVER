package com.kang.FloApiServer.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kang.FloApiServer.domain.song.Song;
import com.kang.FloApiServer.domain.song.SongRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SongService {

	private final SongRepository songRepository;

	@Transactional
	public Song 노래저장하기(Song song) {	
		songRepository.save(song);
		return song;
		
	}
	
	@Transactional(readOnly = true)
	public List<Song> 노래전체찾기(){
		List<Song> songList = songRepository.findAll();
		return songList;
	}
	
	@Transactional(readOnly = true)
	public List<Song> 카테고리리스트(String category){ //나중에 키워드로 get요청해서 안드로이드 어댑터와 동기화
		List<Song> songList = songRepository.mfindCategory(category);
		return songList;
	}
	
	@Transactional(readOnly = true)
	public List<Song> 검색리스트(String keyword){
		List<Song> searchSongList = songRepository.mfindByKeyword(keyword);
		
		return searchSongList;
	}
	
	
	
}
