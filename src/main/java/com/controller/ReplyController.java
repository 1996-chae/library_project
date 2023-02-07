package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.ReplyDao;
import com.domain.ReplyVO;
import com.google.gson.Gson;
import com.service.ReplyService;

@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {
	
	private ReplyService service; 
	private Gson gson; 
	
	@Override
	public void init() throws ServletException {
		ReplyDao dao = new ReplyDao();
		service = new ReplyService(dao);
		gson = new Gson();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String pathInfo = request.getPathInfo(); 
		String contextPath = request.getContextPath();
		
		if(pathInfo.equals("/list")) {
			String paramBno = request.getParameter("bno");
			int bno = Integer.parseInt(paramBno);
			List<ReplyVO> list = service.list(bno);
			out.print(gson.toJson(list));
			return;
			
		// 댓글 쓰기
		} else if(pathInfo.equals("/write")) {
			String paramBno = request.getParameter("bno");
			
			/*
			long currentTime = System.currentTimeMillis();
			HttpSession session = request.getSession(false);
			
			if (session.getAttribute("lastWriting")!=null) { // 마지막에 글을 쓴 시간이 있다면
				long lastWriting = (long) session.getAttribute("lastWriting");
				if(currentTime-lastWriting<10000) {
					String result = gson.toJson(currentTime-lastWriting + "밀리초 후 댓글등록 이 가능합니다.");
					out.print(result);
					return;
				}
			}
			// 마지막에 글을 쓴 시간이 없다면
			session.setAttribute("lastWriting", currentTime);
			 */
			
			ReplyVO vo = ReplyVO.builder()
					.bno(Integer.parseInt(paramBno))
					.reply(request.getParameter("reply"))
					.writerId(request.getParameter("id"))
					.writerNick(request.getParameter("nickname"))
					.build();
			
			service.writer(vo);
			out.print(gson.toJson("댓글 등록 성공"));
			
			return;
			
		} else if(pathInfo.equals("/modify")) {
			String paramRno = request.getParameter("rno");
			ReplyVO vo = ReplyVO.builder()
					.rno(Integer.parseInt(paramRno))
					.reply(request.getParameter("reply"))
					.build();
			service.update(vo);
			String result = gson.toJson(paramRno + "댓글 수정 성공");
			out.print(result);
			
		} else if(pathInfo.equals("/delete")) {
			
			String paramRno = request.getParameter("rno");
			String paramBno = request.getParameter("bno");
			service.remove(Integer.parseInt(paramRno),Integer.parseInt(paramBno));
			String result = gson.toJson(paramRno + "댓글 삭제 성공");
			out.print(result);
		}
		
	}

	

}
