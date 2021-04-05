package com.kang.FloApiServer.web.dto.storage;



import com.kang.FloApiServer.domain.storage.Storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StorageSaveDto {
	private String title;
	
	public Storage toEntity() {
		Storage storage = Storage.builder()
				.title(title)
				.build();
		
		return storage;
	}
}
