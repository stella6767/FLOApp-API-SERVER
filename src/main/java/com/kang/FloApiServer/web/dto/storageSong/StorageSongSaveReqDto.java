package com.kang.FloApiServer.web.dto.storageSong;


import com.kang.FloApiServer.domain.song.Song;
import com.kang.FloApiServer.domain.storage.Storage;
import com.kang.FloApiServer.domain.storageSong.StorageSong;

import lombok.Data;

@Data
public class StorageSongSaveReqDto {
	private Storage storage;
	private Song song;
	
	public StorageSong toEntity() {
		StorageSong storageSong = new StorageSong().builder()
				.song(song)
				.storage(storage)
				.build();
		return storageSong;
		
	}
}
