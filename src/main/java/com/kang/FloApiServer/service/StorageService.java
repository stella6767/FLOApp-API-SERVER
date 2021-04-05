package com.kang.FloApiServer.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kang.FloApiServer.domain.storage.Storage;
import com.kang.FloApiServer.domain.storage.StorageRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StorageService {

	private final StorageRepository storageRepository;
	
	
	@Transactional(readOnly = true)
	public List<Storage> 전체찾기(){
		List<Storage> storagesEntity = storageRepository.findAll();
		return storagesEntity;
	}
	
	@Transactional
	public Storage 저장하기(Storage storage) {
		System.out.println("storage 데이터 : " + storage);
		Storage storageEntity = storageRepository.save(storage);
		System.out.println("storageEntity 데이터 : " + storageEntity);
		return storageEntity;
	}
	
	@Transactional
	public void 한건삭제하기(int id) {
		storageRepository.deleteById(id);
	}
}
