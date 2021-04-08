package com.kang.FloApiServer.web;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kang.FloApiServer.service.PlaySongService;
import com.kang.FloApiServer.web.dto.CMRespDto;
import com.kang.FloApiServer.web.dto.playsong.PlaySongSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PlaySongController { //내 재생목록

	private final PlaySongService playSongService;
	
	
	@GetMapping("/playSong/{id}")
	public CMRespDto<?> findAll(@PathVariable int id) {

		return new CMRespDto<>(1, "성공", playSongService.전체찾기(id));
	}
	
	
	@PostMapping("/playSong")
	public CMRespDto<?> save(@RequestBody PlaySongSaveReqDto playSongSaveReqDto) {
		//System.out.println("재생목록에 곡 추가"+ song);
		System.out.println("재생목록에 곡 추가"+ playSongSaveReqDto);
			
		return new CMRespDto<>(1, "성공", playSongService.노래추가(playSongSaveReqDto.toEntity()));
	}
	
	
	@DeleteMapping("/playSong/{id}")
	public CMRespDto<?> deleteById(@PathVariable int id){
		
		System.out.println("id:  " + id);
		playSongService.노래삭제(id);
		
		return new CMRespDto<>(1, "성공", "");
	}
	
}
