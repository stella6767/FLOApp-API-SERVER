package com.kang.FloApiServer.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kang.FloApiServer.domain.song.Song;
import com.kang.FloApiServer.domain.storageSong.StorageSong;
import com.kang.FloApiServer.service.StorageSongService;
import com.kang.FloApiServer.web.dto.CMRespDto;
import com.kang.FloApiServer.web.dto.storageSong.StorageSongSaveReqDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class StorageSongController {

	private final StorageSongService storageSongService;
	
	@PostMapping("/storageSong")
	public CMRespDto<?> save(@RequestBody StorageSongSaveReqDto saveReqDto) {
		
		System.out.println(saveReqDto);	
		String stoargeTitle = saveReqDto.getStorage().getTitle();
		storageSongService.노래담기(saveReqDto.toEntity());
		
		return new CMRespDto<>(1, "성공적으로 "+stoargeTitle+"에 노래를 담았습니다.", "" );
	}
	
	
	@GetMapping("/storageSong")
	public CMRespDto<?> findAll(int storageId, int userId){  //storageId랑 userId 넘겨받아야됨
		System.out.println("storageId 값: " + storageId + "   " + userId );
			
		List<StorageSong> storageSongEntity = storageSongService.노래찾기(storageId, userId);

//		List<Song> songs = new ArrayList<>();	
//		for (StorageSong storagesong : storageSongEntity) {  //아 몰라 귀찮아서 그냥 이렇게 짜겠음
//			songs.add(storagesong.getSong());
//		}
//		System.out.println(songs);
		
		return new CMRespDto<>(1, "성공", storageSongEntity);
	}
	
	
	@DeleteMapping("/storageSong/{id}")
	public CMRespDto<?> delete(@PathVariable int id){
		
		storageSongService.노래삭제(id); 
		
		return new CMRespDto<>(1, "성공", "");
	}
	
	
	
	
}
