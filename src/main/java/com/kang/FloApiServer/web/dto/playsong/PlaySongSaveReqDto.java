package com.kang.FloApiServer.web.dto.playsong;

import com.kang.FloApiServer.domain.playsong.PlaySong;
import com.kang.FloApiServer.domain.song.Song;
import com.kang.FloApiServer.domain.user.User;

import lombok.Data;

@Data
public class PlaySongSaveReqDto {

	private Song song;
	private User user;
	
	public PlaySong toEntity() {
		return PlaySong.builder().song(song)
				.user(user).build();
	}
	
}
