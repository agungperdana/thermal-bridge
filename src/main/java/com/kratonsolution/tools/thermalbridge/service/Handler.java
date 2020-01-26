/**
 * 
 */
package com.kratonsolution.tools.thermalbridge.service;

import io.undertow.io.Receiver.FullBytesCallback;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import javafx.scene.web.WebEngine;

/**
 * @author agung
 *
 */
public class Handler implements HttpHandler {

	@Override
	public void handleRequest(HttpServerExchange exchange) throws Exception {
		
		exchange.getRequestReceiver().receiveFullBytes(new CallBack());
	}
	
	private class CallBack implements FullBytesCallback {

		@Override
		public void handle(HttpServerExchange exchange, byte[] message) {
			
			WebEngine engine = new WebEngine();
			engine.loadContent(new String(message));
			
			if(exchange.getRequestHeaders().get("PRINTER_TYPE").contains("THERMAL")) {
				printThermal(message);
			}
			else {
				printNonThermal(message);
			}
		}
		
		public void printThermal(byte[] content) {
			
		}
		
		public void printNonThermal(byte[] content) {
			
		}
	}
}
