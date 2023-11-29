package com.java.www.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.www.service.Ser_MDoLogin;
import com.java.www.service.Ser_MDoSingUp;
import com.java.www.service.Service;

@WebServlet("*.do")
public class T2_FController extends HttpServlet {

	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("2T_FCtrl - doAction");
		request.setCharacterEncoding("UTF-8");

		// ▼파일이름 찾기
		String uri = request.getRequestURI();
		String cPath = request.getContextPath();
		String fileName = uri.substring(cPath.length());
		// ▼파일호출 이름
		System.out.println("FCtrl 호출이름 : " + fileName);

		// ▼변수선언
		String url = null;
		Service service = null;
		
		// ▼경로Switch
		switch (fileName) {
		case "/a_main.do"://메인
			url="a_main.jsp";
			break;
		case "/a_login.do": //1.로그인 페이지
			response.sendRedirect("a_login.jsp");
			break;
		case "/doLogin.do"://1.로그인 하기
			service = new Ser_MDoLogin();
			service.execute(request, response);
			url="doLogin.jsp";
			break;
		case "/a_logout.do"://로그아웃
			response.sendRedirect("a_login.jsp");
			break;
		case "/a_signUp.do"://2.회원가입 페이지
			response.sendRedirect("a_signUp.jsp");
			break;
		case "/doMSignUp.do"://2.회원가입 하기
			service = new Ser_MDoSingUp();
			service.execute(request, response);
			url="doMSignUp.jsp";
			break;
		default:
			break;
		}// switch

		
		
		// ▼url이 있는 경우
		if (url != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} // if(url있는 경우)

	}// doAction

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		doAction(request, response);
	}// doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost");
		doAction(request, response);
	}// doPost

}// SerVlet(T2_FController) - 2팀 컨트롤러
