package com.kang.FloApiServer.domain.playsong;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;

import com.kang.FloApiServer.domain.song.Song;
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
		name="playSong",
		uniqueConstraints={
			@UniqueConstraint(
				name = "playSong_uk",
				columnNames={"userId","songId"}
			)
		}
	)
public class PlaySong {
	
	@Id //Pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence 
	private Integer id;

 
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	
	@ManyToOne //oneToOne이라 굳이 user를 넣을 필요는 없는데..
	@JoinColumn(name = "songId")
	private Song song;
	

	@CreationTimestamp 
	private Timestamp createDate;
}
