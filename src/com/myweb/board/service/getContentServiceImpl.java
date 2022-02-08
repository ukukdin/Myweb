package com.myweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardVO;

public class getContentServiceImpl implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String bno=request.getParameter("bno");
		
		BoardDAO dao=BoardDAO.getInstance();
		BoardVO vo=dao.getContent(bno);
	
		//requeset에 vo를 담습니다.
		request.setAttribute("vo", vo);
	
	
	}

	
	
	
}
