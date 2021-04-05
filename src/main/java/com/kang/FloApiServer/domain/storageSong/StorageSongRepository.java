package com.kang.FloApiServer.domain.storageSong;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface StorageSongRepository extends JpaRepository<StorageSong, Integer> {
	
	
	@Query(value = "select * from storageSong where storageId=:storageId and userId=:userId",
			nativeQuery = true)
	List<StorageSong> findByStroageId(int storageId, int userId);
	
	@Query(value = "select id from storageSong where userId=:userId and storageId=:storageId and songId=:songId",
			nativeQuery = true)
	int mfindStroageSongId(int userId,int storageId,int songId);
	
//	@Query(value = "delete from storageSong where userId=:userId and storageId=:storageId and songId=:songId",
//			nativeQuery = true)
//	void mdeleteStorageSong(int userId,int storageId,int songId);
	
}

