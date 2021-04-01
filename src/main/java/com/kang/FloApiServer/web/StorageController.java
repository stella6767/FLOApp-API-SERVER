package com.kang.FloApiServer.web;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kang.FloApiServer.domain.storage.Storage;
import com.kang.FloApiServer.service.StorageService;
import com.kang.FloApiServer.web.dto.CMRespDto;
import com.kang.FloApiServer.web.dto.storage.StorageSaveDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class StorageController {

	private final StorageService storageService;
	
	@GetMapping("/storage/test")
		public CMRespDto<?> storageTest() {
			return new CMRespDto<>(1, "성공", "테스트 데이터 전달");
		}
	
	@GetMapping("/storage")
	public CMRespDto<?> storageFindAll(){
		List<Storage> storagesList = storageService.전체찾기();
		System.out.println("데이터 확인 : " + storagesList);
		return new CMRespDto<>(1, "성공", storagesList);
	}
	
	@PostMapping("/storage")
	public CMRespDto<?> storageSave(@RequestBody StorageSaveDto storageSaveDto){
		Storage storageEntity =  storageService.저장하기(storageSaveDto.toEntity());
		return new CMRespDto<>(1, "성공", storageEntity);
	}
	
	@DeleteMapping("/storage/{id}")
	public CMRespDto<?> storageDelete(@PathVariable int id){
		storageService.한건삭제하기(id);
		return new CMRespDto<>(1, "성공", "삭제하기 성공");
	}
}
