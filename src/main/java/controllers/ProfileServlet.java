package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Account.AccountEntity;
import models.User.UserEntity;
import services.GetAccountByUsernameService;
import services.GetUserByIdService;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private GetUserByIdService getUserByIdService;
	
	public ProfileServlet() {
		this.getUserByIdService = new GetUserByIdService();
	}
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		AccountEntity account = (AccountEntity) session.getAttribute("user");
		
		if(account != null) {
			UserEntity user = this.getUserByIdService.execute(account.getUserId());
			
			 request.setAttribute("username", account.getUsername());
			 
	         request.setAttribute("name", user.getFullName());
	         request.setAttribute("email", user.getEmail());
	         request.setAttribute("phone", user.getPhone());
	         request.setAttribute("address", user.getResidentialAddress());
	         request.setAttribute("birthdate", user.getBirthdate());
	         request.setAttribute("gender", user.getGender());
	         
	         request.getRequestDispatcher("profile.jsp").forward(request, response);
		} else {
			response.sendRedirect("login.jsp");
		}
	}

}
