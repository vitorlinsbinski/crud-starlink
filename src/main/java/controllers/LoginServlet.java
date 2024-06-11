//indica que este arquivo Java está no pacote (package) chamado "controllers"://
package controllers;

//Importando algumas classes que serão usadas neste arquivo://
//Classe para lidar com exceções relacionadas à entrada/saída de dados://
import java.io.IOException;
//Usada para codificar URLs://
import java.net.URLEncoder;
//Usadas para interagir com um banco de dados SQL://
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//Importando classes relacionadas ao Servlet://
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//Classe que permite a criação de sessões de usuário em aplicativos web://
import javax.servlet.http.HttpSession;
//Importando outras classes que serão usadas neste arquivo://
//exceções definidas pelo programador para lidar com casos em que não é possível encontrar um recurso ou quando as credenciais do usuário não correspondem://
import exceptions.ResourceNotFoundException;
import exceptions.UserCredentialsDoesNotMatch;
//Classe de entidade que representa uma conta de usuário://
import models.Account.AccountEntity;
//Classe de serviço para autenticar usuários://
import services.AuthenticateService;
//Classe que contém métodos para hash de senhas://
import utils.Hashing;

//Anotação para mapear esta classe de servlet para a URL "/login". Significa que quando alguém acessa essa URL, o servlet será chamado para lidar com a solicitação.//
@WebServlet("/login")
	//Define a classe LoginServlet, que estende HttpServlet. Significa que LoginServlet é uma classe que pode lidar com solicitações HTTP://
public class LoginServlet extends HttpServlet {
	//Cria uma instância da classe AuthenticateService, que será usada para autenticar usuários://
	AuthenticateService authenticateService = new AuthenticateService();

	//Declara uma constante serialVersionUID que é usada para controlar a versão de serialização de objetos da classe://
	private static final long serialVersionUID = 1L;

	//Chamado quando uma solicitação HTTP POST é feita para o servlet. Ele recebe um objeto HttpServletRequest que contém os dados da solicitação e um objeto HttpServletResponse que é usado para enviar a resposta.//
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtendo os parâmetros da solicitação HTTP://
		String username = request.getParameter("username");
        String password = request.getParameter("password");
		
        //Chama o método execute do serviço AuthenticateService para autenticar o usuário com o nome de usuário e senha fornecidos na solicitação://
        try {
        	AccountEntity account = this.authenticateService.execute(username, password);
        	//cria uma sessão HTTP para o usuário e armazena a conta do usuário como um atributo da sessão, usando o nome "user"://
        	HttpSession session = request.getSession();
            session.setAttribute("user", account);
            //Redireciona o usuário para a página "/home" após o login bem-sucedido://
            response.sendRedirect(request.getContextPath() + "/home");
		//Se ocorrer uma exceção do tipo UserCredentialsDoesNotMatch ou ResourceNotFoundException, o usuário será redirecionado de volta para a página de login com uma mensagem de erro na URL. A mensagem de erro é codificada usando URLEncoder para garantir que caracteres especiais sejam tratados corretamente na URL.//
        } catch (UserCredentialsDoesNotMatch | ResourceNotFoundException e) {
        	String encodedError = URLEncoder.encode(e.getMessage(), "UTF-8");
			 response.sendRedirect("login.jsp?error=" + encodedError);
        }
	}

}
