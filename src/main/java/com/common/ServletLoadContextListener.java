package com.common;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.dao.BookDao;
import com.service.BookService;

@WebListener
public class ServletLoadContextListener implements ServletContextListener{

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		BookDao bookDao = new BookDao(); 
		BookService bookService = new BookService(bookDao);
		sc.setAttribute("bookService", bookService);
	}
	
	
}
