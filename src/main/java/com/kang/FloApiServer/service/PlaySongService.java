package com.kang.FloApiServer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kang.FloApiServer.domain.playsong.PlaySong;
import com.kang.FloApiServer.domain.playsong.PlaySongRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PlaySongService {

	private final PlaySongRepository playSongRepository;
	
	public List<PlaySong> 전체찾기() {
		return playSongRepository.findAll();
	}
	
	public PlaySong 노래추가(PlaySong playSong) {
		
		PlaySong playSongEntity = playSongRepository.mCheckContain(playSong.getSong().getId());
		
		if(playSongEntity == null) {  //중복방지..
			playSongRepository.save(playSong);
			return playSong;
		}
				
		return playSongEntity;
	}
	
	
	public void 노래삭제(int id) {
		playSongRepository.deleteById(id);
	}
	
	
	
}
