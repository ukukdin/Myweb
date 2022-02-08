package com.myweb.board.service;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;

public class UpHitServiceImpl implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String bno=request.getParameter("bno");
		
		//쿠기 검사
		Cookie[] cookies=request.getCookies();
		
		boolean flag=true;//중복검사 변수
		if(cookies!=null) {
			for(Cookie s : cookies) {
				if(s.getName().equals(bno)) {//쿠키 이름이 bno랑 같으면 중복이란뜻	
					flag=false;//중복이 되었다는 뜻
					break;
				}
			}
		}
		//flag가 true라면 조회수를 증가
		if(flag) {
		BoardDAO dao=BoardDAO.getInstance();
		dao.Uphit(bno);
		
		}
		
		//쿠키문법 - 클라이언트에 저정되는 정보
		Cookie cookie=new Cookie(bno,bno);//((이름,값))
		cookie.setMaxAge(20);
		response.addCookie(cookie);
		//서버측에서 생성헤서 클라이언트에 저정, 쿠키에 글번호에 저장
		//쿠키 -서버로 전송되서 들어오면 글번호 쿠키가 있다면 조회수 증가 x 글 번호 없다면 조회수 증가o
		//쿠키생성	
		//시간설정
		//쿠키전송
		
		
	}

	
	
}
