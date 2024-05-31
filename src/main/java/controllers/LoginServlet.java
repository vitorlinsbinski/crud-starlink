package controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import exceptions.UserCredentialsDoesNotMatch;
import models.Account.AccountEntity;
import services.AuthenticateService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	AuthenticateService authenticateService = new AuthenticateService();
	
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        try {
        	AccountEntity account = this.authenticateService.execute(username, password);
        	
        	HttpSession session = request.getSession();
            session.setAttribute("user", account);
            
            response.sendRedirect(request.getContextPath() + "/home");
        } catch (UserCredentialsDoesNotMatch e) {
        	String encodedError = URLEncoder.encode(e.getMessage(), "UTF-8");
			 response.sendRedirect("login.jsp?error=" + encodedError);
        }
	}

}
