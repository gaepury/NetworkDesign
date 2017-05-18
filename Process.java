import java.util.Scanner;

public class Process {
	public TCPSegment serverdata;
	public TCPSegment clientdata;

	public TCPSegment getServerdata() {
		return serverdata;
	}

	public TCPSegment getClientdata() {
		return clientdata;
	}



	public void sendToServerSocket(TCPSegment clientdata) {
		this.serverdata = clientdata;
	}

	public void sendToServerComputer(TCPSegment clientdata) {
		this.serverdata = clientdata;
	}

	public void sendToClientSocket(TCPSegment serverdata) {
		this.clientdata = serverdata;
	}

	public void sendToClientComputer(TCPSegment serverdata) {
		this.clientdata = serverdata;
	}
}
