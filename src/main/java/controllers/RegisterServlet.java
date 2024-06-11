//indica que este arquivo Java está no pacote (package) chamado "controllers"://
package controllers;

//Importando algumas classes que serão usadas neste arquivo://
//Classe para lidar com exceções relacionadas à entrada/saída de dados://
import java.io.IOException;
//Usada para codificar URLs://
import java.net.URLEncoder;
//Classes para lidar com datas e formatação de datas://
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Importando classes relacionadas ao Servlet://
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Importando outras classes que serão usadas neste arquivo://
//Uma exceção definida para lidar com casos em que um usuário já existe://
import exceptions.UserAlreadyExistsExepction;
//Classes de entidades que representam contas de usuário e usuários://
import models.Account.AccountEntity;
import models.User.UserEntity;
//Classe de serviço para criar contas de usuário://
import services.CreateUserAccountService;
//Classe que contém métodos para hash de senhas://
import utils.Hashing;

//Anotação para mapear esta classe de servlet para a URL "/register". Significa que quando alguém acessa essa URL, o servlet será chamado para lidar com a solicitação.//
@WebServlet("/register")
	//Define a classe RegisterServlet, que estende HttpServlet. Isso significa que RegisterServlet é uma classe que pode lidar com solicitações HTTP.//
public class RegisterServlet extends HttpServlet {
	//Criada instância da classe CreateUserAccountService, que será usada para criar contas de usuário://
	private CreateUserAccountService createAccountService = new CreateUserAccountService();

	//Declara uma constante chamada serialVersionUID que é usada para controlar a versão de serialização de objetos da classe://
	//O valor atribuído a serialVersionUID é um long, que é usado pelo mecanismo de serialização para verificar se os objetos serializados são compatíveis com a classe.//
	//Atribuir manualmente um valor a serialVersionUID, definido como 1L, para garantir que a versão de serialização da classe seja controlada de forma explícita.//
	private static final long serialVersionUID = 1L;

	//Método chamado quando uma solicitação HTTP POST é feita para o servlet. Ele recebe um objeto HttpServletRequest que contém os dados da solicitação e um objeto HttpServletResponse que é usado para enviar a resposta://
	//ServletException: se houver algum problema ao lidar com a solicitação HTTP dentro do servlet.//
	//IOException: Lançada quando ocorre um erro relacionado à entrada ou saída de dados.//
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Obtendo os parâmetros da solicitação HTTP://
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String username = request.getParameter("username");
        String password = request.getParameter("password");
        String birthdateString = request.getParameter("birthdate");
        String gender = request.getParameter("gender");

		//Convertendo a data de nascimento, que é recebida como uma string, em um objeto LocalDate usando um DateTimeFormatter para especificar o formato da data://
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birthdate = LocalDate.parse(birthdateString, formatter);

		//Cria um novo objeto UserEntity com os dados do novo usuário://
		UserEntity newUser = new UserEntity(name, email, phone, address, birthdate, gender);

		//Usa a classe Hashing para calcular o hash da senha do usuário://
		String hashedPassword = Hashing.hashPassword(password);
		//Cria um novo objeto AccountEntity com os dados da nova conta de usuário, incluindo o nome de usuário, a senha criptografada e o ID do usuário://
		AccountEntity newAccount = new AccountEntity(username, hashedPassword, null, null, null, newUser.getId());

		//Chamam o método execute do serviço CreateUserAccountService para criar a nova conta de usuário://
		//Se a conta já existir, uma exceção UserAlreadyExistsExepction será lançada e o usuário será redirecionado para a página de registro com uma mensagem de erro codificada na URL. Caso contrário, o usuário será redirecionado para a página de login.//
		try { 
			this.createAccountService.execute(newUser, newAccount);
			
			response.sendRedirect("login.jsp");
		} catch (UserAlreadyExistsExepction e) {
			 String encodedError = URLEncoder.encode(e.getMessage(), "UTF-8");
			 response.sendRedirect("register.jsp?error=" + encodedError);
		}
		
		
	}

}
