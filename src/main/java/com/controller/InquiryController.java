package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.InquiryDao;
import com.domain.Criteria;
import com.domain.InquiryVO;
import com.domain.Pagination;
import com.service.InquiryService;

@WebServlet("/inquiry")
public class InquiryController extends HttpServlet {
	
	private InquiryService service;
    	
	@Override
	public void init() throws ServletException {
		InquiryDao dao = new InquiryDao();
		service = new InquiryService(dao);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pathInfo = request.getPathInfo(); 
		String contextPath = request.getContextPath();
		final String PREFIX = "/WEB-INF/views/inquiry/";
		final String SUFFIX = ".jsp";
		
		RequestDispatcher rd = null;
		String nextPage =null;
		
		// 게시판 보기
		if(pathInfo==null || pathInfo.equals("/") || pathInfo.equals("")) {
			
			int pageNum = 1; // 현제 페이지 번호
			int amount = 10; /// 페이징게시물수
			
			Criteria criteria = new Criteria();
			criteria.setAmount(amount);
			
			String paramPageNum = request.getParameter("pageNum");
			if (paramPageNum != null) pageNum = Integer.parseInt(paramPageNum);
			
			criteria.setPageNum(pageNum);
			
			Pagination pagination = null;
			List<InquiryVO> board = null;
			
			int totalCount = service.getTotalCount(); // 게시물수
			pagination = new Pagination(criteria, totalCount); // 페이지 정의
			board = service.list(criteria);
			
			request.setAttribute("board", board);
			request.setAttribute("p", pagination);
			
			nextPage = "list";
			
		} else if (pathInfo.equals("datail")) {
			
			
			
		}
		
		rd = request.getRequestDispatcher(PREFIX+nextPage+SUFFIX);
		rd.forward(request, response);
		
	}

}
