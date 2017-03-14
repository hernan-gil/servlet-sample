package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.Authenticator;
import dto.User;

public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(LoginController.class.getName());
	public LoginController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("email");
		String password = request.getParameter("password");
		log.debug("Login->U: " +username+" / P: "+password);
		RequestDispatcher rd = null;
		if("login".equals(request.getParameter("action"))) {
			Authenticator authenticator = new Authenticator();
			String result = authenticator.authenticate(username, password);
			if (result.equals("success")) {
				rd = request.getRequestDispatcher("/success.jsp");
				User user = new User(username, password);
				request.setAttribute("user", user);
			} else {
				request.setAttribute("msj", new String("Login failed, please try again."));
				rd = request.getRequestDispatcher("/login.jsp");
			}
		}
		rd.forward(request, response);
	}

}