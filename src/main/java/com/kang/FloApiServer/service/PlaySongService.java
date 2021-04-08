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
	public List<PlaySong> 전체찾기(int userId) {
		//return playSongRepository.findAll();
	
		return playSongRepository.mfindPlaysongAllByUserId(userId);
	}
	
	@Transactional
	public PlaySong 노래추가(PlaySong playSong) {
		
		System.out.println("송아이디" + playSong.getSong().getId());
		
		PlaySong playSongEntity = playSongRepository.mCheckContain(playSong.getUser().getId(), playSong.getSong().getId());
		
		if(playSongEntity == null) {  //중복방지..
			return playSongRepository.save(playSong);
			
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
