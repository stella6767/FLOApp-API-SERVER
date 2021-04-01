package com.kang.FloApiServer.domain.song;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

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
	
	private Integer duration;
	
	@Lob
	private String lyrics;

	private String date;
	private String img;
	
	//private List<> 
		
	@Column(nullable = false)
	private String file;
}
