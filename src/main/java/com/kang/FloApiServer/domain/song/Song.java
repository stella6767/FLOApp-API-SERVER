package com.kang.FloApiServer.domain.song;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kang.FloApiServer.domain.reply.Reply;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Song {

	@Id //Pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence 
	private Integer id;
	
	@Column(nullable = false, unique = true)
	private String title;
	
	@Column(nullable = false) 
	private String artist;
	
	@Enumerated(EnumType.STRING) 
	private CategoryType category;
	
	
	@Lob
	private String lyrics;

	private String relaseDate;
	
	private String img;
	
		
	@Column(nullable = false)
	private String file;
	
	
	// 양방향 매핑
	@OneToMany(mappedBy = "song", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties({"song"})
	@OrderBy("id desc")
	private List<Reply> replys;
	
	
	public Song (int id, String title, String artist, CategoryType category, String lyrics, String relaseDate, String img, String file ) {
		this.id =id;  //Junit 테스트 용도 생성자
		this.title =title;
		this.artist =artist;
		this.category = category;
		this.lyrics =lyrics;
		this.relaseDate = relaseDate;
		this.img = img;
		this.file = file;
	}


	public Song(String title, String artist, CategoryType category, String lyrics,
			String relaseDate, String img, String file) {
		this.title =title;
		this.artist =artist;
		this.category = category;
		this.lyrics =lyrics;
		this.relaseDate = relaseDate;
		this.img = img;
		this.file = file;
		
	}
	
}
