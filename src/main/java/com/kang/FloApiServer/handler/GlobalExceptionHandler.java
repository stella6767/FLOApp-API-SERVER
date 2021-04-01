package com.kang.FloApiServer.handler;

import java.sql.SQLException;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.kang.FloApiServer.web.dto.CMRespDto;

@RestController //데이터를 리턴하기 위해서
@ControllerAdvice //모든 익셉션을 낚아챔.
public class GlobalExceptionHandler {
	
	
		@ExceptionHandler(value = DataIntegrityViolationException.class)
		public CMRespDto<?> dataIntegrityViolationException(Exception e){
			return new CMRespDto<>(-1, "실패1",null);
		}
		
		@ExceptionHandler(value = IllegalArgumentException.class)
		public CMRespDto<?> illegalArgumentException(Exception e){
			return new CMRespDto<>(-1,"실패2",null);
		}
		
		@ExceptionHandler(value = EmptyResultDataAccessException.class)
		public CMRespDto<?> emptyResultDataAccessException(Exception e){
			return new CMRespDto<>(-1, "실패3", null);
		}
		
		@ExceptionHandler(value = SQLException.class)
		public CMRespDto<?> sQLException(Exception e){
			return new CMRespDto<>(-1, "실패4", null);
		}


}