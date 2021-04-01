package com.kang.FloApiServer.domain.storageSong;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface StorageSongRepository extends JpaRepository<StorageSong, Integer> {
	

	@Query(value = "select * from storagesong st inner join song s on st.songId = s.id where st.storageId = :id",
			nativeQuery = true)
	List<StorageSong> findAllSong(int id);
}

