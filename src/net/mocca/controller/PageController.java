package net.mocca.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PageController
 */
@WebServlet("/PageController")
public class PageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static void Forward(HttpServletRequest request, HttpServletResponse response, String Msg, String url) throws ServletException, IOException{
		request.setAttribute("errorMessage", Msg);
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
}
