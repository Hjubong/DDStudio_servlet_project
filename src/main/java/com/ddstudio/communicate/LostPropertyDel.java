package com.ddstudio.communicate;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ddstudio.communicate.repository.CommuDAO;

/**
 * LostPropertyDel 서블릿은 분실물 정보를 삭제하는 기능을 제공합니다.
 * 
 * 작성자: 차수민
 */
@WebServlet("/communicate/lostpropertydel.do")
public class LostPropertyDel extends HttpServlet {

	/**
	 * HTTP GET 요청을 처리하여 분실물 정보 삭제 페이지로 포워딩합니다.
	 * 
	 * @param req  클라이언트의 HttpServletRequest 객체입니다.
	 * @param resp 클라이언트에게 응답을 보내기 위한 HttpServletResponse 객체입니다.
	 * @throws ServletException 서블릿 요청을 처리하는 동안 오류가 발생한 경우 예외가 발생합니다.
	 * @throws IOException      입력 또는 출력 예외가 발생한 경우 예외가 발생합니다.
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String seq = req.getParameter("seq");
		
		req.setAttribute("seq", seq);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/communicate/lost-property/del.jsp");

		dispatcher.forward(req, resp);

	}
	
	/**
	 * HTTP POST 요청을 처리하여 분실물 정보를 데이터베이스에서 삭제합니다.
	 * 
	 * @param req  클라이언트의 HttpServletRequest 객체입니다.
	 * @param resp 클라이언트에게 응답을 보내기 위한 HttpServletResponse 객체입니다.
	 * @throws ServletException 서블릿 요청을 처리하는 동안 오류가 발생한 경우 예외가 발생합니다.
	 * @throws IOException      입력 또는 출력 예외가 발생한 경우 예외가 발생합니다.
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String seq = req.getParameter("seq");
		
		CommuDAO dao = new CommuDAO();
		
		int result = dao.deleteLostProperty(seq);
		
		if (result == 1) {
			
			resp.sendRedirect("/ddstudio/communicate/lostproperty.do");
			
		} else {
			
			PrintWriter writer = resp.getWriter();
			
			writer.println("<script>");
			writer.println("alert('failed');");
			writer.println("history.back();");
			writer.println("</script>");
			
			writer.close();
			
		}
		
	}

}
