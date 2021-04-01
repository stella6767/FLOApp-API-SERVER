package com.kang.FloApiServer.domain.song;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SongRepository extends JpaRepository<Song, Integer> {
	
	@Query(value ="select * FROM song where category=:category", nativeQuery = true)
	List<Song> mfindCategory(String category);
	
	@Query(value ="select * FROM song where title LIKE %:keyword% OR artist LIKE %:keyword%", nativeQuery = true)
	List<Song> mfindByKeyword(String keyword);
	
	
}
