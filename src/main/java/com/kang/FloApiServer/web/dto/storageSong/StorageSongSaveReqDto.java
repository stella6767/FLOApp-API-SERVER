package com.kang.FloApiServer.web.dto.storageSong;


import com.kang.FloApiServer.domain.song.Song;
import com.kang.FloApiServer.domain.storage.Storage;
import com.kang.FloApiServer.domain.storageSong.StorageSong;
import com.kang.FloApiServer.domain.user.User;

import lombok.Data;

@Data
public class StorageSongSaveReqDto {
	
	private User user;
	private Storage storage;
	private Song song;
	
	
	public StorageSong toEntity() {
		StorageSong storageSong = new StorageSong().builder()
				.user(user)
				.song(song)
				.storage(storage)
				.build();
		
		return storageSong;
		
	}
}
