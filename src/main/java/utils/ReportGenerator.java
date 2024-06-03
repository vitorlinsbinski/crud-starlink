package utils;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.Map;


import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class ReportGenerator {
	private String fileName;
	private Map<String, Object> params;
	private Connection conneciton;
	
	public ReportGenerator(String fileName, Map<String, Object> params, Connection conneciton) {
		this.fileName = fileName;
		this.params = params;
		this.conneciton = conneciton;
	}
	
	public void generatePDFOutputStream(OutputStream outputStream) {
		try {
			System.out.println("fileName: " + this.fileName);
			JasperPrint jasperPrint = JasperFillManager.fillReport(this.fileName, this.params, this.conneciton);
			
			JRExporter exporter = new JRPdfExporter();
			
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
			
			exporter.exportReport();
		} catch(JRException e) {
			throw new RuntimeException(e);
		}
	}
}
