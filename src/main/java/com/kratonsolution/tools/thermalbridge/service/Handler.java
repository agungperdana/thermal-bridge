/**
 * 
 */
package com.kratonsolution.tools.thermalbridge.service;

import com.kratonsolution.tools.thermalbridge.ui.model.Config;

import io.undertow.io.Receiver.FullBytesCallback;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.print.PrinterJob.JobStatus;
import javafx.scene.web.WebEngine;

/**
 * @author agung
 *
 */
public class Handler implements HttpHandler {

	private ConfigService service = new ConfigService();

	private Printer printer = null;

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {

		exchange.getRequestReceiver().receiveFullBytes(new CallBack());
	}

	private class CallBack implements FullBytesCallback {

		@Override
		public void handle(HttpServerExchange exchange, byte[] message) {
			
			if(exchange.getRequestHeaders().get("PRINTER_TYPE").contains("THERMAL")) {
				printThermal(message);
			}
			else {
				printStandard(message);
			}
		}

		public void printThermal(byte[] content) {

			Config con = service.load();

			Printer.getAllPrinters().forEach(print -> {

				if(print.getName().equals(con.thermalPrinterName)) {
					printer = print;
				}
			});

			if(printer != null) {

				PrinterJob job = PrinterJob.createPrinterJob(printer);

				WebEngine engine = new WebEngine();
				engine.loadContent(new String(content));
				engine.print(job);
				
				if(job.getJobStatus().equals(JobStatus.CANCELED) || 
						job.getJobStatus().equals(JobStatus.ERROR) || 
						job.getJobStatus().equals(JobStatus.DONE)) {
					
					job.endJob();
				}
			}
		}

		public void printStandard(byte[] content) {

			Config con = service.load();

			Printer.getAllPrinters().forEach(print -> {

				if(print.getName().equals(con.standarPrinterName)) {
					printer = print;
				}
			});

			if(printer != null) {

				PrinterJob job = PrinterJob.createPrinterJob(printer);
				job.getJobSettings().setCopies(2);

				WebEngine engine = new WebEngine();
				engine.loadContent(new String(content));
				engine.print(job);
				
				if(job.getJobStatus().equals(JobStatus.CANCELED) || 
						job.getJobStatus().equals(JobStatus.ERROR) || 
						job.getJobStatus().equals(JobStatus.DONE)) {
					
					job.endJob();
				}
			}
		}
	}
}
