package reports;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import models.Account.AccountEntity;
import net.sf.jasperreports.engine.JRException;
import utils.ReportGenerator;

@WebServlet("/get-report")
public class ReportGeneratorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountEntity account = (AccountEntity) session.getAttribute("user");

        String fileName = request.getParameter("fileName") + ".jasper";

        if (fileName == null || fileName.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Parâmetro de nome de arquivo ausente.");
            return;
        }

        try {
            new ReportGenerator().generateReport(fileName, account, getServletContext(), response);
        } catch (JRException | SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao gerar o relatório.");
        }
    }
}
