package nuainghttp.app.factory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class NuAingTcpIpServer {
	
	private final Logger log = Logger.getLogger(NuAingTcpIpServer.class.getName());
	private int port = 0;
    private ExecutorService executor = null;
    public NuAingTcpIpServer(int port) {
    	this.port = port;
    	this.executor = Executors.newFixedThreadPool(2);
    }
    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            log.info("HTTP Server started on port " + port);
            while (true) {
            	
                Socket clientSocket = serverSocket.accept();
                log.info("Received request from " + clientSocket.getRemoteSocketAddress());
                executor.submit(new ConnectionHandler(clientSocket));
            }
        } catch (IOException e) {
            log.info("Failed to start server: " + e.getMessage());
        }
    }
}
