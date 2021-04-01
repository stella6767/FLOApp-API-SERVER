package com.kang.FloApiServer.domain.playsong;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlaySongRepository extends JpaRepository<PlaySong, Integer>{
	
	@Query(value ="select * FROM playsong where songId=:songId", nativeQuery = true)
	PlaySong mCheckContain(int songId);
}
