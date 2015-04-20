package net.mocca.controller.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mocca.SessionUtils;
import net.mocca.dao.UserDAO;
import net.mocca.model.User;

@WebServlet("/user/update")
public class UpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sessionUserId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		if (sessionUserId == null) {
			response.sendRedirect("/");
			return;
		}
		UserDAO userDAO = new UserDAO();
		try {
			User user = userDAO.select(sessionUserId);
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/update_form.jsp");
			rd.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String SessionUserId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		String userId = request.getParameter("userId");
		if (SessionUserId == null) {
			response.sendRedirect("/");
			return;
		}
		if(!SessionUserId.equals(userId)){
			response.sendRedirect("/");
			return;
		}
		
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		User user = new User(userId, password, name, email);
		UserDAO userDAO = new UserDAO();
		try {
			userDAO.update(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/");
	}

}
