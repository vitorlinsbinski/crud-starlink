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
import models.Account.AccountEntity;
import models.User.UserEntity;
import services.CreateAccountService;
import utils.Hashing;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private CreateAccountService createAccountService = new CreateAccountService();
	
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
		
		UserEntity user = new UserEntity(name, email, phone, address, birthdate, gender);
		
		String hashedPassword = Hashing.hashPassword(password);
		
		AccountEntity account = new AccountEntity(username, hashedPassword, null, null, null, user.getId());
		
		try { 
			this.createAccountService.execute(user, account);
			
			response.sendRedirect("login.jsp");
		} catch (UserAlreadyExistsExepction e) {
			 String encodedError = URLEncoder.encode(e.getMessage(), "UTF-8");
			 response.sendRedirect("register.jsp?error=" + encodedError);
		}
		
		
	}

}
