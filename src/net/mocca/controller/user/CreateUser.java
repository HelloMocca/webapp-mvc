package net.mocca.controller.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.mocca.model.User;
import net.mocca.dao.UserDAO;

@WebServlet("/user/create")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  request.setCharacterEncoding("UTF-8");
		  String userId = request.getParameter("userId");
		  String password = request.getParameter("password");
		  String name = request.getParameter("name");
		  String email = request.getParameter("email");

		  User user = new User(userId, password, name, email);
		  
		  UserDAO userDAO = new UserDAO();
		  
		  try {
			userDAO.insert(user);
		  } catch (SQLException e) {
			e.printStackTrace();
		  }
		  
		  response.sendRedirect("/");
	}

}
