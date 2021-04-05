package com.kang.FloApiServer.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kang.FloApiServer.domain.playsong.PlaySong;
import com.kang.FloApiServer.domain.playsong.PlaySongRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PlaySongService {

	private final PlaySongRepository playSongRepository;
	
	@Transactional(readOnly = true)
	public List<PlaySong> 전체찾기() {
		return playSongRepository.findAll();
	}
	
	@Transactional
	public PlaySong 노래추가(PlaySong playSong) {
		
		PlaySong playSongEntity = playSongRepository.mCheckContain(playSong.getSong().getId());
		
		if(playSongEntity == null) {  //중복방지..
			playSongRepository.save(playSong);
			return playSong;
		}
				
		return playSongEntity;
	}
	
	@Transactional
	public int 노래삭제(int id) {
		
		PlaySong playSongEntity = playSongRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		

		playSongRepository.deleteById(id);
		
		return 1;
	}
	
	
	
}
