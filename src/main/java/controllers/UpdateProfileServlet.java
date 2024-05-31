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
import javax.servlet.http.HttpSession;

import exceptions.UserAlreadyExistsExepction;
import models.Account.AccountEntity;
import models.User.UserEntity;
import services.GetAccountByUsernameService;
import services.GetUserByIdService;
import services.UpdateProfileService;
import utils.Hashing;

@WebServlet("/update-profile")
public class UpdateProfileServlet extends HttpServlet {
    private UpdateProfileService updateAccountService = new UpdateProfileService();
    private GetUserByIdService getUserByIdService = new GetUserByIdService();
    private GetAccountByUsernameService getAccountByUsernameService = new GetAccountByUsernameService();
    
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
       
        String hashedPassword = Hashing.hashPassword(password);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthdate = LocalDate.parse(birthdateString, formatter);
        
        HttpSession session = request.getSession();
        AccountEntity sessionAccount = (AccountEntity) session.getAttribute("user");
        
        int userIdOnDatabase = this.getUserByIdService.execute(sessionAccount.getUserId()).getId();
        int accountIdOnDatabase = this.getAccountByUsernameService.execute(sessionAccount.getUsername()).getId();
        
        UserEntity user = new UserEntity(userIdOnDatabase, name, email, phone, address, birthdate, gender);
        
        AccountEntity account = new AccountEntity(accountIdOnDatabase, username, hashedPassword, 
                null, null, null, userIdOnDatabase);
        
        try { 
            this.updateAccountService.execute(user, account);
            
            session.invalidate();
            response.sendRedirect("login.jsp");
        } catch(UserAlreadyExistsExepction e) {
        	String encodedError = URLEncoder.encode(e.getMessage(), "UTF-8");
            response.sendRedirect("profile?error=" + encodedError);
        }
        catch (RuntimeException e) {
            String encodedError = URLEncoder.encode(e.getMessage(), "UTF-8");
            response.sendRedirect("profile?error=" + encodedError);
        }
    }
}
