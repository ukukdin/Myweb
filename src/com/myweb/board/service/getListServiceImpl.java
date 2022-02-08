package com.myweb.board.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myweb.board.model.BoardDAO;
import com.myweb.board.model.BoardVO;
import com.myweb.util.PageVO;

public class getListServiceImpl implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		//받은값 x
		BoardDAO dao=BoardDAO.getInstance();
		int pageNum=1;
		int amount=10;
		
		//2 화면에서 pageNum amout값이 넘어오는 경우
		if(request.getParameter("pageNum")!=null||request.getParameter("amount")!=null) {
			pageNum =Integer.parseInt(request.getParameter("pageNum"));
			amount =Integer.parseInt(request.getParameter("amount"));
		}
		ArrayList<BoardVO>list =dao.getList(pageNum,amount);
		
		request.setAttribute("list", list);
		int total=dao.getTotal();
	//전체 게시글수 필요
		PageVO pageVO=new PageVO(pageNum,amount,total);
		//페이지vo를 화면에 전달
		request.setAttribute("pageVO", pageVO);
		
	}
	
	
	
}
