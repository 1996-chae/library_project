package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BoardDao;
import com.dao.MemberDao;
import com.domain.AuthVO;
import com.domain.MemberVO;
import com.google.gson.Gson;
import com.service.BoardSevice;
import com.service.MemberService;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {

	private MemberService service;
	private Gson gson; 
	
	@Override
	public void init() throws ServletException {
		MemberDao dao = new MemberDao();
		service = new MemberService(dao);
		gson = new Gson();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json;charset=utf-8");
		String pathInfo = request.getPathInfo(); 
		String contextPath = request.getContextPath();
		
		final String PREFIX = "/WEB-INF/views/member/";
		final String SUFFIX = ".jsp";
		
		RequestDispatcher rd = null;
		String nextPage =null;
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		// 로그인 하기
		if(pathInfo.equals("/login")) {
			String id = request.getParameter("id");
			String pwd = (String) request.getAttribute("pwd");
			MemberVO vo = MemberVO.builder()
					.id(id).pwd(pwd).build();
			
			AuthVO authVO = null;
			
			if(service.loginService(vo)) {
				// 회원 권한 설정
				authVO = service.getMemberGrade(id);
				session.setAttribute("auth", authVO);
			} else {
				System.out.println("MemberController.login : 아이디 또는 비밀번호 불일치");
			}
			out.print(gson.toJson(authVO.getNickname()+" 님 환영합니다."));
			return;
		}
		// 로그아웃 처리 
		else if(pathInfo.equals("/logout")) {
			System.out.println("로그아웃 작업");
			session = request.getSession(false);
			session.removeAttribute("auth");
			out.print(gson.toJson("다음에 만나요~~"));
			return;
		}
		// 회원가입 폼 
		else if(pathInfo.equals("/joinForm")) {
			nextPage = "joinForm";
		}
		// 회원가입
		else if(pathInfo.equals("/join")) {
			System.out.println("회원강비 실행");
			String id = request.getParameter("id");
			String pwd = (String) request.getAttribute("pwd");
			String nickname = request.getParameter("nickname");
			String name = request.getParameter("name");
			String email =request.getParameter("email");
			MemberVO vo = MemberVO.builder()
					.id(id)
					.pwd(pwd)
					.nickname(nickname)
					.name(name)
					.email(email).build();
			service.memberJoin(vo);
			out.print(gson.toJson(nickname+"("+id+")님 환영합니다."));
			return;
		}
		
		// 내 정보
		if(pathInfo.equals("/infoFage")) {
			String id = request.getParameter("id");
			MemberVO member = service.getMemberAll(id);
			request.setAttribute("m", member);
			nextPage = "infoFage";
		}
		// 회원 수정폼
		else if(pathInfo.equals("/modForm")) {
			
			nextPage = "modForm";
		}
		// 비밀번호 확인
		else if(pathInfo.equals("/pwdCheck")) {
			String id = request.getParameter("id");
			String pwd = (String) request.getAttribute("pwd");
			MemberVO vo = MemberVO.builder().id(id).pwd(pwd).build();
			out.print(gson.toJson(service.loginService(vo)));
			return;
		}
		// 회원 수정 
		else if(pathInfo.equals("/mod")) {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String email =request.getParameter("email");
			MemberVO vo = MemberVO.builder()
					.id(id)
					.name(name)
					.email(email).build();
			service.modMember(vo);
			// 회원 수정으로 인한 보드게시판 닉네임 변경
			response.sendRedirect(contextPath+"/board/list");
			return;
		}
		// 비밀번호 변경
		else if(pathInfo.equals("/modPwdForm")) {
			nextPage = "modPwdForm";
			
		}
		// 비밀번호 변경
		else if(pathInfo.equals("/modPwd")) {
			String id = request.getParameter("id");
			String pwd = (String) request.getAttribute("pwd");
			MemberVO vo = MemberVO.builder()
					.id(id)
					.pwd(pwd).build();
			service.modPwdChange(vo);
			response.sendRedirect(contextPath+"/board/list");
			return;
		}
		
		// 닉네임 변경
		else if(pathInfo.equals("/modNickForm")) {
			nextPage = "modNickForm";
			
		}
		// 닉네임 변경
		else if(pathInfo.equals("/modNick")) {
			String id = request.getParameter("id");
			String nickString = request.getParameter("nickname");
			MemberVO vo = MemberVO.builder()
					.id(id)
					.nickname(nickString).build();
			// 닉네임 체인지
			vo.setNickname(service.modNickChange(vo));
			// 회원 수정으로 인한 보드게시판 닉네임 변경
			service.chageNickname(vo);
			AuthVO authVO = service.getMemberGrade(id);
			// 계정 정보 다시 주입
			session.setAttribute("auth", authVO);
			
			response.sendRedirect(contextPath+"/board/list");
			return;
		}
		// 중복(아이디)체크
		else if(pathInfo.equals("/idChek")) {
			String kindId = "id";
			String id = request.getParameter("id");
			if (!service.dupChek(kindId, id) && id.length() > 4) { // 중복되는 아이디가 없으면
				out.print(gson.toJson(id));
			}
			return;
		}
		// 중복(닉네임)체크
		else if(pathInfo.equals("/nicknameChek")) {
			String kindNick = "nickname";
			String nickname = request.getParameter("nickname");
			if (!service.dupChek(kindNick, nickname) && nickname.length()>2) { // 중복되는 닉네임이 없으면
				out.print(gson.toJson(nickname));
			}
			return;
		}
		
		rd = request.getRequestDispatcher(PREFIX+nextPage+SUFFIX);
		rd.forward(request, response);
		
	}
}
