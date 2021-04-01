package com.kang.FloApiServer.web.dto.playsong;

import com.kang.FloApiServer.domain.playsong.PlaySong;
import com.kang.FloApiServer.domain.song.Song;

import lombok.Data;

@Data
public class PlaySongSaveReqDto {

	private Song song;
	
	public PlaySong toEntity() {
		return PlaySong.builder().song(song).build();
	}
	
}
