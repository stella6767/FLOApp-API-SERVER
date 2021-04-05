package com.kang.FloApiServer.domain.storage;


import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kang.FloApiServer.domain.storageSong.StorageSong;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@DynamicInsert
@Entity
public class Storage {
	
	@Id //Pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Table, auto_increment, Sequence 
	private Integer id;
	
	private String title;
	
	@ColumnDefault("'storage_default_imag.png'")
	private String image;
	
	@ToString.Exclude
	@JsonIgnoreProperties({"storage"})
	@OneToMany(mappedBy = "storage", cascade = CascadeType.REMOVE) //이게 맞나....
	private List<StorageSong> stoarageSongs;
	

	@CreationTimestamp
	private Timestamp createDate;

}
