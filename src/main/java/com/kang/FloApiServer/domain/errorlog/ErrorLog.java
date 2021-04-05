package com.kang.FloApiServer.domain.errorlog;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class ErrorLog {

	@Id //Pk
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long id;
	
	private String log;
	
	@CreationTimestamp 
	private Timestamp createDate;
	
}
