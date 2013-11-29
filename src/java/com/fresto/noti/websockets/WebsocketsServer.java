package com.fresto.noti.websockets;

import java.util.Set;

import org.vertx.java.core.Handler;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.eventbus.EventBus;
import org.vertx.java.core.eventbus.Message;
import org.vertx.java.core.http.HttpServer;
import org.vertx.java.core.http.HttpServerRequest;
import org.vertx.java.core.http.ServerWebSocket;
import org.vertx.java.deploy.Verticle;

public class WebsocketsServer extends Verticle {
	
	private final String dataReceiverID = "receiver.websockeserver"; //$NON-NLS-1$
	
	private EventBus eb = null;
	private Set<String> connections = null;
	
	private String name;
	private int port;
	
	public WebsocketsServer(String name, int port) {
		this.name = name;
		this.port = port;
	}

	@Override
	public void start() {
		
		eb = vertx.eventBus();
		
		connections = vertx.sharedData().getSet("conns"); //$NON-NLS-1$
		
		Handler<Message<String>> msgReceiveHandler = new Handler<Message<String>>() {
			@Override
			public void handle(Message<String> msg) {
				publishToWebSocketClient(msg);
			}
		};
		eb.registerHandler(dataReceiverID, msgReceiveHandler);

		Handler<ServerWebSocket> serverWebSocketHanler = new Handler<ServerWebSocket>() {
			@Override
			public void handle(final ServerWebSocket ws) {
				if (name.startsWith("/")) { //$NON-NLS-1$
					name = "/" + name; //$NON-NLS-1$
				}
				
				if (ws.path.equals(name)) {
					ws.dataHandler(new Handler<Buffer>() {
						public void handle(Buffer data) {
							for (String actorId : connections) {
								eb.publish(actorId, data.toString());
							}
						}
					});
					
					ws.endHandler(new Handler<Void>() {
						@Override
						public void handle(Void arg0) {
							connections.remove(ws.textHandlerID);
						}
					});
				} else {
					ws.reject();
				}
			}
		};
		
		Handler<HttpServerRequest> httpServerRequestHandler = new Handler<HttpServerRequest>() {
			@Override
			public void handle(HttpServerRequest req) {
				if (req.path.equals("/")) { //$NON-NLS-N$
					req.response.sendFile("websockets/ws.html");
				}
			}
		};
		
		HttpServer httpServer = vertx.createHttpServer();
		httpServer.websocketHandler(serverWebSocketHanler)/*.requestHandler(httpServerRequestHandler)*/.listen(port);
	}
	
	private void publishToWebSocketClient(Message<String> msg) {
		for (String actorId : connections) {
			eb.publish(actorId, msg.body);
		}
	}

}
