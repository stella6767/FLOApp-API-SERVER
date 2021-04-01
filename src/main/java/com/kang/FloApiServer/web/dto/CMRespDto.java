package com.kang.FloApiServer.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CMRespDto<T> {
	private int statusCode; //1:정상, -1:실패
	private String msg; //오류 내용
	private T data;
}
