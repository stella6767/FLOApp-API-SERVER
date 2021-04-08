package com.kang.FloApiServer.domain.playsong;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PlaySongRepository extends JpaRepository<PlaySong, Integer>{
	
	@Query(value ="select * FROM playsong where userId=:userId AND songId=:songId ", nativeQuery = true) //희한하네 원래는 안되는데.
	PlaySong mCheckContain(int userId, int songId);
	
	
	@Query(value ="select * FROM playsong where userId=:userId", nativeQuery = true)
	List<PlaySong> mfindPlaysongAllByUserId(int userId);
}
