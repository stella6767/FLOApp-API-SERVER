package com.kang.FloApiServer.web.dto.storage;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.kang.FloApiServer.domain.storage.Storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StorageSaveDto {
	
	@NotBlank(message = "저장소 이름을 입력해주세요.")
	@Size(max = 30,message = "저장소 이름의 길이를 초과하였습니다.")
	private String title;
	
	public Storage toEntity() {
		Storage storage = Storage.builder()
				.title(title)
				.build();
		
		return storage;
	}
}
