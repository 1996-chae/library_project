package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BookDao;
import com.domain.BookDTO;
import com.domain.BookVO;
import com.google.gson.Gson;
import com.service.BookService;

@WebServlet(value = {"/book","/book/*"})
public class BookController extends HttpServlet {
	
	private BookService service;
	private Gson gson; 
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		ServletContext sc = config.getServletContext();
//		service = (BookService) sc.getAttribute("bookService");
		service = new BookService(new BookDao());
		gson = new Gson();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHanlde(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHanlde(request,response);
	}
	
	protected void doHanlde(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 문자 셋팅 없으면 깨짐
		response.setContentType("application/json;charset=utf-8");
		
		String pathInfo = request.getPathInfo(); 
		String contextPath = request.getContextPath();
		
		
		final String PREFIX = "/WEB-INF/views/book/";
		final String SUFFIX = ".jsp";
		
		RequestDispatcher rd = null;
		
		if(pathInfo.equals("/find")) {
			String search = request.getParameter("search");
			String category = request.getParameter("selection");
			PrintWriter out = response.getWriter();
			BookDTO dto = new BookDTO();
			dto.setCategory(category);
			dto.setSearch(search);
			List<BookVO> bookList = service.find(dto);
			out.print(gson.toJson(bookList));
			return;
		}
		
		String nextPage = "main";
		
		rd = request.getRequestDispatcher(PREFIX+nextPage+SUFFIX);
		rd.forward(request, response);
	}

}
