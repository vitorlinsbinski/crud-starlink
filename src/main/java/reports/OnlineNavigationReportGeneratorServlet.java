package reports;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import database.ConnectionFactory;
import models.Account.AccountEntity;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;

@WebServlet("/get-online-navigation-report")
public class OnlineNavigationReportGeneratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		AccountEntity account = (AccountEntity) session.getAttribute("user");
		
		System.out.println("userId: " + account.getUserId());
		// set header as pdf

		response.setContentType("application/pdf");

		// set input and output stream
		ServletOutputStream servletOutputStream = response.getOutputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		FileInputStream fis;
		BufferedInputStream bufferedInputStream;

		try {
			Connection connection = new ConnectionFactory().getConnection();

			// get report location
			ServletContext context = getServletContext();
			String reportLocation = context.getRealPath("WEB-INF");

			System.out.println(reportLocation);

			// get report
			fis = new FileInputStream(reportLocation + "/jasper/pagamentos_transacoes.jasper");
			bufferedInputStream = new BufferedInputStream(fis);

			// fill parameters
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ParameterUserId", account.getUserId());

			// export to pdf
			JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, connection);
			JasperExportManager.exportReportToPdfStream(jasperPrint, baos);

			response.setContentLength(baos.size());
			baos.writeTo(servletOutputStream);

			// close it
			fis.close();
			bufferedInputStream.close();

		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			servletOutputStream.flush();
			servletOutputStream.close();
			baos.close();
		}
	}
}
