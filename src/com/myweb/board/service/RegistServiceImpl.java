package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;

public class RegistServiceImpl implements BoardService{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//컨트롤러 역할분담.
		String writer=request.getParameter("writer");
		String title = request.getParameter("title");
		String content =request.getParameter("content");
		//DAO 생성
		BoardDAO dao=BoardDAO.getInstance();
		dao.regist(writer, title, content);
	}

	
}
