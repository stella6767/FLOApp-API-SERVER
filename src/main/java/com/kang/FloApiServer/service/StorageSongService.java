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

	private final StorageSongRepository storageSongRepo;
	
	@Transactional
	public void 노래담기(StorageSong storageSong) {
		storageSongRepo.save(storageSong);
	}
	
	@Transactional
	public List<StorageSong> 노래찾기(int id){
		List<StorageSong> storageSongsEntity = storageSongRepo.findAllSong(id);
		return storageSongsEntity;
	}

}
