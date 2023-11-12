package com.ddstudio.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddstudio.member.model.ReviewDTO;
import com.ddstudio.member.repository.ReviewDAO;

@WebServlet("/member/review/info.do")
public class Review extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// Review.java
		// 1.

		String email = req.getSession().getAttribute("email").toString();

		// 2.
		ReviewDAO dao = new ReviewDAO();

		ArrayList<ReviewDTO> dto = dao.get(email);

		// 3.
		req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/review/info.jsp");
		dispatcher.forward(req, resp);
	}
}