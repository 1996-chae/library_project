package com.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.FileUpload;
import com.dao.BoardDao;
import com.domain.AuthVO;
import com.domain.BoardVO;
import com.domain.Criteria;
import com.domain.Pagination;
import com.service.BoardSevice;

@WebServlet(value = {
		"/board",
		"/board/*"
		})
public class BoardController extends HttpServlet {
	
	BoardSevice service;
	private FileUpload mutiReq; 
	
	@Override
	public void init() throws ServletException {
		BoardDao dao = new BoardDao();
		service = new BoardSevice(dao);
		mutiReq = new FileUpload("board/");
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
		final String PREFIX = "/WEB-INF/views/board/";
		final String SUFFIX = ".jsp";
		
		RequestDispatcher rd = null;
		String nextPage =null;
		
		// 상시 작동
		List<String> typeAll = service.boardTotalType();
		request.setAttribute("typeAll", typeAll);
		
		
		// 게시판 보기
		if(pathInfo==null || pathInfo.equals("/") ||pathInfo.startsWith("/list")) {
			int pageNum = 1; // 현제 페이지 번호
			int amount = 15; /// 페이징게시물수
			
			Criteria criteria = new Criteria();
			criteria.setAmount(amount);
			
			String paramPageNum = request.getParameter("pageNum");
			if (paramPageNum != null) pageNum = Integer.parseInt(paramPageNum);
			
			criteria.setPageNum(pageNum);
			
			String writeType = request.getParameter("writeType");
			criteria.setType(writeType);
			
			Pagination pagination = null;
			List<BoardVO> board = null;
			
			// 전체보기
			if(writeType==null) {
				int totalCount = service.getTotalCount(); // 게시물수
				pagination = new Pagination(criteria, totalCount); // 페이지 정의
				board = service.boardTotalList(criteria);
				request.setAttribute("board", board);
			// 선택보기
			} else {
				int oneCount = service.getOneCount(writeType); // 게시물수
				pagination = new Pagination(criteria, oneCount); // 페이지 정의
				board = service.boardTotalList(criteria);
			}
			
			nextPage = "list";
			
			
			request.setAttribute("board", board);
			request.setAttribute("p", pagination);
			
		// 상세 조회 하기
		} else if( pathInfo.equals("/detail")) {
			
			String paramBno = request.getParameter("bno");
			int bno = Integer.parseInt(paramBno);
			BoardVO board = service.findBoard(bno);
			service.oneUpHit(bno);
			
			request.setAttribute("board", board);
			
			nextPage = "detail";
			
			// 글쓰기 폼
		} else if( pathInfo.equals("/writeForm")) {
			nextPage = "writeForm";
			// 글쓰기
		} else if( pathInfo.equals("/write")) {
			
			HttpSession session = request.getSession();
			AuthVO authVO = (AuthVO) session.getAttribute("auth");
			
			Map<String, String> req = mutiReq.getMutipartRequest(request);
			
			String fileName = req.get("fileName");
			BoardVO vo = BoardVO.builder()
					.writeType(req.get("writeType"))
					.title(req.get("title"))
					.content(req.get("content"))
					.writerId(authVO.getId())
					.writerNickname(authVO.getNickname())
					.fileName(fileName)
					.build();
			int boardNO = service.addBoard(vo);
			// 이미지파일을 첨부한 경우
			if(fileName!=null && fileName.length()>0 ) {
				mutiReq.uploadImage(boardNO, fileName);
			}
			response.sendRedirect(contextPath+"/board/list");
			return;
			
			// 수정하기
		} else if (pathInfo.equals("/modifyForm")) {
			int bno = Integer.parseInt(request.getParameter("bno"));
			BoardVO board = service.findBoard(bno);
			request.setAttribute("board", board);
			nextPage = "modifyForm";
		} else if (pathInfo.equals("/modify")) {
			Map<String, String> req = mutiReq.getMutipartRequest(request);
			
			String fileName = req.get("fileName");
			int bno = Integer.parseInt(req.get("bno"));
			BoardVO vo = BoardVO.builder()
					.bno(bno)
					.writeType(req.get("writeType"))
					.title(req.get("title"))
					.content(req.get("content"))
					.build();
			
			if(fileName!=null && fileName.length()>0) {
				vo.setFileName(fileName);
				// 기존 이미지 삭제
				String originFileName = req.get("originFileName");
				File oldFile = new File("c:/file_repo/"+bno+"/"+originFileName);
				oldFile.delete();
				// 새로운 이미지 업로드 
				mutiReq.uploadImage(bno, fileName);
				}
			
			service.chageBoard(vo);
				
			response.sendRedirect(contextPath+"/board/detail?bno="+bno);
			return;
			
		// 글 삭제
		} else if (pathInfo.equals("/delete")) {
			Map<String, String> req = mutiReq.getMutipartRequest(request);
			String paramBno = req.get("bno");
			int bno = Integer.parseInt(paramBno);
			service.removeBoard(bno);
			mutiReq.deleteAllImage(bno);
			response.sendRedirect(contextPath+"/board/list");
			return;
			
		} else {
			nextPage = "_404";
		}
		
		rd = request.getRequestDispatcher(PREFIX+nextPage+SUFFIX);
		rd.forward(request, response);
		
	}

}
