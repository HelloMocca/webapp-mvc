package net.mocca.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.mocca.dao.UserDAO;
import net.mocca.model.User;

@WebServlet("/api/users/find")
public class ApiFindUserServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String userId = request.getParameter("userId");
		if(userId == null){
			response.sendRedirect("/");
			return;
		}
		UserDAO userDao = new UserDAO();
		try {
			User user = userDao.select(userId);
			if (user == null) {
				return;
			}
			
			final GsonBuilder builder = new GsonBuilder();
			builder.excludeFieldsWithoutExposeAnnotation();
			final Gson gson = builder.create();
			String jsonData = gson.toJson(user);
			PrintWriter out = response.getWriter();
			response.setContentType("application/json; charset=UTF-8");
			out.print(jsonData);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
