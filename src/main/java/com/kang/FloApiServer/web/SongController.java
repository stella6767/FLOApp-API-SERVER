package com.kang.FloApiServer.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kang.FloApiServer.domain.song.Song;
import com.kang.FloApiServer.service.SongService;
import com.kang.FloApiServer.web.dto.CMRespDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class SongController {
	
	@Autowired
	ResourceLoader resourceLoader;
	
	private final SongService songService;
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@PostMapping("/down")
	public CMRespDto<?> down(@RequestBody Song song) {
		song.setId(null);
		return new CMRespDto<>(1, "성공", songService.노래저장하기(song));
	}
	
	
	@GetMapping("/song")  //아주 간단히 테스트할 용도
	public CMRespDto<?> findAll(){

		return new CMRespDto<>(1,"성공",songService.노래전체찾기());
	}
	
	
	@GetMapping("/song/category")
	public CMRespDto<?> findCategory(String category){
		
		System.out.println("카테고리" + category);
		
		List<Song> categoryList = songService.카테고리리스트(category);	
		return new CMRespDto<>(1, "성공", categoryList);
	}
	
	@GetMapping("/song/search")
	public CMRespDto<?> findByKeyword(String keyword){
		
		System.out.println("키워드" + keyword);	
		List<Song> searchSongList = songService.검색리스트(keyword);
	
		return new CMRespDto<>(1, "성공", searchSongList);
	}
	
	

}
