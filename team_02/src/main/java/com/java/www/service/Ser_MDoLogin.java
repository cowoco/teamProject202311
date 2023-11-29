package com.java.www.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.java.www.dao.Stu_memberDao;
import com.java.www.dto.Stu_boardDto;
import com.java.www.dto.Stu_memberDto;

public class Ser_MDoLogin implements Service {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		//dao접근 
		Stu_memberDao smdao = new Stu_memberDao();
		Stu_memberDto smdto = smdao.DoLogin(id,pw);
		
		//request
		int result=0;
		if(smdto!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("session_id", smdto.getId());
			session.setAttribute("session_name", smdto.getName());
			result=1;
		}//if
		System.out.println("result : " +result);
		
		
		request.setAttribute("result",result);

	}

}
