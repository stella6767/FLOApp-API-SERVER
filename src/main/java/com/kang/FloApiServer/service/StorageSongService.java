package com.kang.FloApiServer.service;

import java.util.List;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.kang.FloApiServer.domain.storageSong.StorageSong;
import com.kang.FloApiServer.domain.storageSong.StorageSongRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StorageSongService {

	private final StorageSongRepository storageSongRepository;
	
	@Transactional
	public StorageSong 노래담기(StorageSong storageSong) {
		
		return storageSongRepository.save(storageSong);
		
	}
	
	
	@Transactional(readOnly = true)
	public List<StorageSong> 노래찾기(int storageId, int userId){
		List<StorageSong> storageSongsEntity = storageSongRepository.findByStroageId(storageId, userId);
		return storageSongsEntity;
	}
	
	
	@Transactional
	public int 노래삭제(int id) {
		
		StorageSong storageSongEntity = storageSongRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("id를 찾을 수 없습니다.");
		});
		
		storageSongRepository.deleteById(id);
		
		return 1;
	}
	


}
