package com.fresto.noti.websockets;

import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.HttpClient;
import org.vertx.java.core.http.WebSocket;
import org.vertx.java.deploy.Verticle;

public class WebsocketsClient extends Verticle {

	private String name;
	private int port;
	
	public WebsocketsClient(String name, int port) {
		this.name = name;
		this.port = port;
	}

	public void start() {

		Handler<WebSocket> webSocketHandler = new Handler<WebSocket>() {
			@Override
			public void handle(WebSocket websocket) {
				websocket.dataHandler(new Handler<Buffer>() {
					@Override
					public void handle(Buffer data) {
						System.out.println("Received " + data);
					}
				});
				//Send some data
				websocket.writeTextFrame("hello world");
			}
		};
		
		// Setting host as localhost is not strictly necessary as it's the default
		HttpClient httpClient = vertx.createHttpClient();
		httpClient.setHost("localhost"); //$NON-NLS-1$
		httpClient.setPort(port);
		
		if (name.startsWith("/")) {
			name = "/" + name;
		}
		httpClient.connectWebsocket(name, webSocketHandler);
	}
}