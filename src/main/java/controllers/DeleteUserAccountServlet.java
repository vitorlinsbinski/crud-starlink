//indica que este arquivo Java está no pacote (package) chamado "controllers"://
package controllers;

//Classes para lidar com exceções://
import java.io.IOException;
import javax.servlet.ServletException;
//Nos ajuda a dizer ao nosso programa onde este servlet (um tipo especial de classe que responde a pedidos da web) estará disponível.//
import javax.servlet.annotation.WebServlet;
//Ferramentas para lidar com requisições e sessões HTTP (a forma como o navegador e o servidor conversam).//
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//Entidade que representa uma conta de usuário://
import models.Account.AccountEntity;
//Serviço que vai nos ajudar a deletar a conta do usuário://
import services.DeleteUserAccountService;

//Anotação @WebServlet para mapear este servlet para a URL "/delete-user-account". É como dizer: "Quando alguém visita '/delete-user-account', use esta classe para responder."//
@WebServlet("/delete-user-account")
	//Define uma nova classe chamada DeleteUserAccountServlet, que estende (ou seja, herda) HttpServlet. Isso significa que nossa classe pode fazer todas as coisas que um HttpServlet pode fazer.//
public class DeleteUserAccountServlet extends HttpServlet {
	//Criando uma nova instância do DeleteUserAccountService://
	DeleteUserAccountService deleteUserAccountService = new DeleteUserAccountService();

	//Declara uma constante serialVersionUID. É como dar um número de identificação único para esta classe. Isso é usado para garantir que a classe possa ser serializada (convertida em uma série de bytes) e desserializada (reconstruída a partir desses bytes) corretamente.//
	private static final long serialVersionUID = 1L;

	//Este método doPost é chamado quando uma requisição HTTP POST é feita para o servlet. Significa que ele lida com dados enviados de um formulário da web usando o método POST.//
	@Override
	  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  //verificando se um parâmetro chamado _method foi enviado com o valor "DELETE". Se foi, ele chama o método doDelete. Isso é um truque para permitir que formulários HTML façam requisições DELETE, que não são suportadas diretamente.//
	    String method = request.getParameter("_method");
	    if (method != null && method.equals("DELETE")) {
	      doDelete(request, response);
	    } 
	  }
       //Este método doDelete é chamado quando uma requisição HTTP DELETE é feita. É onde realmente vamos deletar a conta do usuário.//
	@Override
	  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  //Obtendo a sessão atual do usuário e pegando a conta do usuário armazenada na sessão. A sessão é como uma caixinha que guarda informações sobre o usuário enquanto ele navega no site.//
	    HttpSession session = request.getSession();
	    AccountEntity account = (AccountEntity) session.getAttribute("user");
	    //Deletando a conta do usuário usando o deleteUserAccountService e, em seguida, redirecionando o usuário para a página de login (login.jsp).//
	    try {
	      this.deleteUserAccountService.execute(account.getId());
	      request.getRequestDispatcher("login.jsp").forward(request, response);
		//Se algo der errado e uma exceção for lançada, nós a capturamos e lançamos uma RuntimeException://
	    } catch (Exception e) {
	      throw new RuntimeException(e);
	    }
	  }
}
