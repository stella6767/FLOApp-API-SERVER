package com.kang.FloApiServer.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
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
	

	@GetMapping("/storage")   //원래는 유저아이디별로 찾아야 되는데 귀찮다.. 생략하자..
	public CMRespDto<?> storageFindAll(){
		List<Storage> storagesList = storageService.전체찾기();
		System.out.println("데이터 확인 : " + storagesList);
		return new CMRespDto<>(1, "성공", storagesList);
	}
	
	@PostMapping("/storage") //이하동문
	public CMRespDto<?> storageSave(@Valid @RequestBody StorageSaveDto storageSaveDto, BindingResult bindingResult){
		Storage storageEntity =  storageService.저장하기(storageSaveDto.toEntity());
		return new CMRespDto<>(1, "성공", storageEntity);
	}
	
	@DeleteMapping("/storage/{id}")
	public CMRespDto<?> storageDelete(@PathVariable int id){
		System.out.println("id:  " + id);
		storageService.한건삭제하기(id);
		return new CMRespDto<>(1, "성공", "");
	}
}
