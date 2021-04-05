package com.kang.FloApiServer.domain.storageSong;



import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kang.FloApiServer.domain.song.Song;
import com.kang.FloApiServer.domain.storage.Storage;
import com.kang.FloApiServer.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(   //복합키 제약조건 걸기 
		name="storageSong",
		uniqueConstraints={
			@UniqueConstraint(
				name = "storageSong_uk",
				columnNames={"storageId","songId","userId"}
			)
		}
	)
public class StorageSong {  //Song과 Storage 다대다 관계 매개체 앤티티
	
	@Id //Pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence 
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "storageId")
	private Storage storage;
	
	@ManyToOne
	@JoinColumn(name = "songId")
	private Song song;
	

	@CreationTimestamp
	private Timestamp createDate;
	
}
