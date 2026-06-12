package nuainghttp.app.factory;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

public class ConnectionHandler implements Runnable{
	private final Logger log = Logger.getLogger(ConnectionHandler.class.getName());
	private Socket clientSocket;
	public ConnectionHandler(Socket clientSocket) {
		this.clientSocket = clientSocket;
		
	}
	private void parseSocket() throws IOException {
		log.info("starting read data from socket");
		InputStream inputStreamData = this.clientSocket.getInputStream();
		OutputStream outputStream = this.clientSocket.getOutputStream();
		PrintWriter clientOutput = new PrintWriter(outputStream);
		var clientInput = new BufferedReader(new InputStreamReader(inputStreamData));
		for(String inputLine; (inputLine = clientInput.readLine()) != null;) {
			System.out.println(inputLine);
			if(inputLine.isEmpty())
				break;
		}
		byte[] sgimtt = "Hi this is SGIMTT Engine".getBytes();
		outputStream.write(httpResponse(sgimtt.length).getBytes());
		outputStream.write(sgimtt);
		outputStream.flush();
		this.clientSocket.close();
		log.info("End of client data");
	}
	@Override
	public void run() {
		try {
			parseSocket();
		} catch (IOException e) {
			e.printStackTrace();
			log.info("Error; " + e.getMessage());
		}
	}
	private String httpResponse(int length) {
		
		return "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html; charset=UTF-8\r\n" +
                "Content-Length: "+length+"\r\n" +
                "Connection: close\r\n" +
                "\r\n";
	}
	/*
	 * private void parseSocket() throws IOException { log.info("read all data");
	 * this.clientSocket.setSoTimeout(10000); InputStream socketDataStream =
	 * clientSocket.getInputStream(); ByteArrayOutputStream buffer = new
	 * ByteArrayOutputStream(); byte[] chunk = new byte[4096]; // 4KB read buffer
	 * int bytesRead; while ((bytesRead = socketDataStream.read(chunk)) != -1) {
	 * buffer.write(chunk, 0, bytesRead); log.info("Read " + bytesRead + " bytes.");
	 * }
	 * 
	 * byte[] totalData = buffer.toByteArray(); log.info("Total data package size: "
	 * + totalData.length); }
	 */

	/*
	 * private void parseSocket() throws IOException { DataInputStream in = new
	 * DataInputStream(new BufferedInputStream(this.clientSocket.getInputStream()));
	 * DataOutputStream out = new DataOutputStream(new
	 * BufferedOutputStream(this.clientSocket.getOutputStream())); char dataType =
	 * in.readChar(); int length = in.readInt(); log.info("Data type " + dataType);
	 * log.info("Length " + length); if(dataType == 's') { boolean end = false;
	 * byte[] messageByte = new byte[length]; StringBuilder dataString = new
	 * StringBuilder(length); int totalBytesRead = 0; while(end) { int
	 * currentBytesRead = in.read(messageByte); totalBytesRead = currentBytesRead +
	 * totalBytesRead; if(totalBytesRead <= length) { dataString.append(new
	 * String(messageByte, 0, currentBytesRead, StandardCharsets.UTF_8));
	 * 
	 * }else { dataString.append(new String(messageByte, 0, length - totalBytesRead
	 * + currentBytesRead)); } if(dataString.length()>=length) { end = true; } }
	 * String data = "This is a string of length 29"; byte[] dataInBytes =
	 * data.getBytes(StandardCharsets.UTF_8);
	 * 
	 * out.writeChar(dataType); out.writeInt(dataInBytes.length);
	 * out.write(dataInBytes); } }
	 */
}
