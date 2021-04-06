package com.kang.FloApiServer.config.auth;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kang.FloApiServer.domain.user.User;
import com.kang.FloApiServer.web.dto.CMRespDto;
import com.kang.FloApiServer.web.dto.auth.AuthLoginRespDto;


public class MyLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        
//      String username = userDetails.getUsername();
//      String password = userDetails.getPassword();
        
        System.out.println("=========================");
        //System.out.println(response.getHeader(""));
//        System.out.println(response.getHeader("Cookie"));
//        
//        System.out.println(request.getContentType());
//        System.out.println(request.getCookies());
//        //System.out.println(request.getSession().getSessionContext());
        
        final Integer SESSION_TIMEOUT_IN_SECONDS = 60 * 120; //단위는 초, 2시간 간격으로 세션만료

        request.getSession().setMaxInactiveInterval(SESSION_TIMEOUT_IN_SECONDS); //세션만료시간.
       
        
        PrincipalDetails principalDetails = (PrincipalDetails)userDetails;
        
        int id = principalDetails.getId();
        String username = principalDetails.getUsername();
        String email = principalDetails.getEmail();
        String role = principalDetails.getRole();
        
        AuthLoginRespDto authLoginRespDto = new AuthLoginRespDto(id, username, email, role);
         
        System.out.println("The user :" + authLoginRespDto);
        
        CMRespDto<?> cmRespDto = new CMRespDto<>(1, "login success", authLoginRespDto);
        
        
      
      //여기서는 @RestController 안 걸려있으니 직접 json파싱하고 PrintWriter로 데이터 리턴해줘야됨. 2가지 역할 
        System.out.println(request.getHeader("Cookie"));
        //String JsessionId = request.getHeader("Cookie"); //아하 애초에 여기서 할 필요가 없었구나..
        //String JsessionCookie = JsessionId.split("=")[1];
        //System.out.println(JsessionCookie);
        //response.setHeader("JSESSIONID", JsessionCookie);
        ObjectMapper mapper = new ObjectMapper();
        String jsonDto = mapper.writeValueAsString(cmRespDto);
        System.out.println(jsonDto);
        
        PrintWriter out = response.getWriter();

        out.print(jsonDto);
        out.flush();
        
        return;  //더 이상 컨트롤러 안 타게끔.
	}

	

}
