package net.mocca;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="HellosServlet", urlPatterns={"/hello", "/hello/servlet"})
public class helloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private int count = 0; // 공유 값 
    public helloServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  System.out.println("HelloServlet: Request Success!");
	  String name = request.getParameter("name");
	  count ++;
	  response.getWriter().print("Hello "+name+" From Server\n");
	  response.getWriter().print("count:"+count);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
