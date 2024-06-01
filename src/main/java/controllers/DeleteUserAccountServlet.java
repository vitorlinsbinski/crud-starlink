package controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Account.AccountEntity;
import services.DeleteUserAccountService;


@WebServlet("/delete-user-account")
public class DeleteUserAccountServlet extends HttpServlet {
	DeleteUserAccountService deleteUserAccountService = new DeleteUserAccountService();
	
	private static final long serialVersionUID = 1L;
	
	@Override
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String method = request.getParameter("_method");
	    if (method != null && method.equals("DELETE")) {
	      doDelete(request, response);
	    } 
	  }
       
	@Override
	  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    AccountEntity account = (AccountEntity) session.getAttribute("user");
	    
	    try {
	      this.deleteUserAccountService.execute(account.getId());
	      request.getRequestDispatcher("login.jsp").forward(request, response);
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	  }
}
