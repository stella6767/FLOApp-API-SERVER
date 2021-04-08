package com.kang.FloApiServer.domain.reply;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;

import com.kang.FloApiServer.domain.song.Song;
import com.kang.FloApiServer.domain.user.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 200)
	private String content;
	
	// 유저
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;
	
	// 앨범
	@ManyToOne
	@JoinColumn(name="songId")
	private Song song;
	
	@CreationTimestamp
	private Timestamp createDate;
}