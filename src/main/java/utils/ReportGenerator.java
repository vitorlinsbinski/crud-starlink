package utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import models.Account.AccountEntity;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

public class ReportGenerator {

    public void generateReport(String fileName, AccountEntity account, ServletContext context, HttpServletResponse response)
            throws JRException, SQLException, IOException {
        response.setContentType("application/pdf");

        ServletOutputStream servletOutputStream = response.getOutputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        FileInputStream fis = null;
        BufferedInputStream bufferedInputStream = null;

        try {
            Connection connection = new database.ConnectionFactory().getConnection();

            String reportLocation = context.getRealPath("WEB-INF");

            fis = new FileInputStream(reportLocation + "/jasper/" + fileName);
            bufferedInputStream = new BufferedInputStream(fis);

            Map<String, Object> map = new HashMap<>();
            map.put("UserId", account.getUserId());

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(bufferedInputStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, connection);
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);

            response.setContentLength(baos.size());
            baos.writeTo(servletOutputStream);
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            servletOutputStream.flush();
            servletOutputStream.close();
            baos.close();
        }
    }
}

