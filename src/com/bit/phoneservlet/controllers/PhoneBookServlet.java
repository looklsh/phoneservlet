package com.bit.phoneservlet.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.phoneservlet.dao.PhoneBookDao;
import com.bit.phoneservlet.dao.PhoneBookDaoimpl;
import com.bit.phoneservlet.vo.PhoneBookVO;
@WebServlet("/ser")
public class PhoneBookServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String action = req.getParameter("a");
		if("addform".equals(action)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/phonebooks/addform.jsp");
			rd.forward(req, resp);
		}else if("success".equals(action)){
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/phonebooks/success.jsp");
			rd.forward(req, resp);
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("a");
		
		if("add".equals(action)) {
			String name= req.getParameter("name");
			String hp = req.getParameter("hp");
			String tel = req.getParameter("tel");
			PhoneBookVO phone = new PhoneBookVO(name, hp, tel);
			PhoneBookDao dao = new PhoneBookDaoimpl();
			boolean isSuccess =dao.insert(phone);
			
			
			if(isSuccess) {
				resp.sendRedirect(req.getContextPath()+"/ser?a=success");
			}else {
				resp.sendRedirect(req.getContextPath() + "/ser?a=addform");
			}
		}else if("delete".equals(action)) {
			String id = req.getParameter("id");
			PhoneBookDao dao = new PhoneBookDaoimpl();
			boolean isSuccess = dao.delete(Long.valueOf(id));
			
			if(isSuccess) {
				resp.sendRedirect(req.getContextPath()+ "/");
			}else {
				System.err.println("failed");
			}
		}
			
		}

	
}
