package com.bit.phoneservlet.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.phoneservlet.dao.PhoneBookDao;
import com.bit.phoneservlet.dao.PhoneBookDaoimpl;
import com.bit.phoneservlet.vo.PhoneBookVO;

@WebServlet("/")
public class AddressServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		
		
		PhoneBookDao dao = new PhoneBookDaoimpl();
//		List<PhoneBookVO> list = dao.getList();
		List<PhoneBookVO> list = null;
		//	name 이 null?
		if(name == null) {
		list = dao.getList();
			
		}else{
			list =dao.searchName(name);
		}
		
		
		
		req.setAttribute("list", list);
		req.setAttribute("name", name);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/phone.jsp");
		rd.forward(req, resp);
	}

//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		super.doPost(req, resp);
//	}

}
