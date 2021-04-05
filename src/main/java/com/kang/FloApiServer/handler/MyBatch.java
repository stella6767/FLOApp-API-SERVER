package com.kang.FloApiServer.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import com.kang.FloApiServer.domain.errorlog.ErrorLog;
import com.kang.FloApiServer.domain.errorlog.ErrorLogRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class MyBatch {

	public final ExceptionList exceptionList;
	private final ErrorLogRepository errorLogRepository;
	
	
	@Scheduled(fixedDelay = 1000*60*10) //Cron 정기적 실행
	public void excute() {
		List<String> exList = exceptionList.getExList();
		//DB에 insert 하기, 
		
		List<ErrorLog> errorLogs = new ArrayList<>();
		for (String error : exList) {
			errorLogs.add(new ErrorLog(null, error, null));
		}
		
		errorLogRepository.saveAll(errorLogs);
		
		exList.clear();
	}
}
