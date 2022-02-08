package com.myweb.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//확장자 패턴으로 *.XXX형태로 변경
@WebServlet("*.test")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public TestController() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	
	//2번째 get과post요청을 하나로 모은다.
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri =request.getRequestURI();
		String path=request.getContextPath();
		String command = uri.substring(path.length());
		System.out.println(command);
	
		
		//4.
		if(command.equals("/controller/login.test")) {
			//로그인 작업
		}else if(command.equals("/controller/logout.test")){
			//로그아웃 작업..........
		}
		
		
	}	
	
	
	
}
