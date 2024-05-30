package controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.UserAlreadyExistsExepction;
import models.User.UserEntity;
import models.User.UserService;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static UserService userService = new UserService();
	
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        String birthdateString = request.getParameter("birthdate");
        String gender = request.getParameter("gender");
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        LocalDate birthdate = LocalDate.parse(birthdateString, formatter);
        
		System.out.println("Register Servlet sendo chamado.");
		
		UserEntity user = new UserEntity(0, name, email, phone, address, birthdate, gender);
		
		try { 
			RegisterServlet.userService.createUser(user);
			response.sendRedirect("index.jsp");
		} catch (UserAlreadyExistsExepction e) {
			System.out.println("ERRO USUÁRIO JÁ EXISTE!");
			 String encodedError = URLEncoder.encode(e.getMessage(), "UTF-8");
			 response.sendRedirect("register.jsp?error=" + encodedError);
		}
		
		
	}

}
