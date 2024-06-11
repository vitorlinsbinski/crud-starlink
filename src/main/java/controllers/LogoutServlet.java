//indica que este arquivo Java está no pacote (package) chamado "controllers"://
package controllers;

//Importando algumas classes que serão usadas neste arquivo://
//Classe para lidar com exceções relacionadas à entrada/saída de dados://
import java.io.IOException;
//Lida com exceções relacionadas a erros no Servlet://
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
//Classe base para todos os servlets://
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//Classe que permite a criação e manipulação de sessões de usuário://
import javax.servlet.http.HttpSession;

//Anotação para mapear esta classe de servlet para a URL "/login". Significa que quando alguém acessa essa URL, o servlet será chamado para lidar com a solicitação.//
@WebServlet("/logout")
    //Define a classe LogoutServlet, que estende HttpServlet. Significa que LogoutServlet é uma classe que pode lidar com solicitações HTTP.//
public class LogoutServlet extends HttpServlet {
    //Este método é chamado quando uma solicitação HTTP GET é feita para o servlet. Ele recebe um objeto HttpServletRequest que contém os dados da solicitação e um objeto HttpServletResponse que é usado para enviar a resposta.//
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //Obtém a sessão atual do usuário a partir do objeto HttpServletRequest. Se não existir uma sessão para o usuário, uma nova será criada.//
        HttpSession session = request.getSession();
               // Encerra a sessão atual do usuário. Isso significa que quaisquer dados armazenados na sessão serão perdidos e uma nova sessão será criada na próxima vez que o usuário fizer uma solicitação.//
        session.invalidate();
                //Redireciona o usuário para a página de login ("login.jsp") após o logout. Quando o método sendRedirect é chamado, o navegador do usuário será instruído a fazer uma nova solicitação para a URL especificada, neste caso, a página de login. Isso efetivamente encerra a sessão atual e retorna o usuário à página de login.//
        response.sendRedirect("login.jsp");
    }
}
