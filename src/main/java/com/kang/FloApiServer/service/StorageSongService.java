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
	
	
	@Transactional
	public List<StorageSong> 노래찾기(int storageId, int userId){
		List<StorageSong> storageSongsEntity = storageSongRepository.findByStroageId(storageId, userId);
		return storageSongsEntity;
	}
	
	
	public void 노래삭제(int id) {
		storageSongRepository.deleteById(id);
	}
	


}
