package com.kang.FloApiServer.domain.storage;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Storage {
	
	@Id //Pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence 
	private Integer id;
	
	private String title;
	
//	@OneToMany(mappedBy = "storage", fetch = FetchType.LAZY)
//	@JsonIgnoreProperties(value = "storage")
//	private List<StorageSong> storageSongs;
	
	
	
	// 곡 카운트??
	// private User user;
	


}
