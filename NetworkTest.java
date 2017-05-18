
import java.util.*;

public class NetworkTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String address; // destionation address ����
		int port; // destination port ����
		int index = -1;
		int selectflag = 0;
		// ----------------------------------------------------------
		// computer,router,process,socket,channel ��ü ����

		Scanner scanner = new Scanner(System.in);
		Computer server[] = new Computer[3];
		for (int i = 0; i < 3; i++) {
			server[i] = new Computer();
		}
		Computer client = new Computer();

		Process[] serverProcess = new Process[3];
		for (int i = 0; i < 3; i++) {
			serverProcess[i] = new Process();
		}
		Process clientProcess = new Process();

		Socket serversocket[] = new Socket[3];
		for (int i = 0; i < 3; i++) {
			serversocket[i] = new Socket();
			serversocket[i].setSourcePort(190);
		}
		Socket clientsocket = new Socket();
		UnreliableDataChannel[] serverudc = new UnreliableDataChannel[3];
		for (int i = 0; i < 3; i++) {
			serverudc[i] = new UnreliableDataChannel();
		}
		UnreliableDataChannel clientudc = new UnreliableDataChannel();

		// ----------------------------------------------------------
		// 3���� server IP �Է¹޴´�.
		for (int i = 0; i < 3; i++) {
			System.out.print("Source IP address for server" + (i + 1) + " : ");
			serversocket[i].setSourceAddress(scanner.next());
			serversocket[i].setIPDatagramHeader();
			System.out.println("***********Server" + (i + 1) + "-IP:" + serversocket[i].getSourceAddress() + ",Port: "
					+ serversocket[i].getSourcePort() + "************");
			System.out.println();
		}
		// -----------------------------------------------------------

		// destination ip address�� port�� �Է¹޾Ƽ� � ������ ������ üũ.
		while (true) {
			System.out.print("Destination IP Address :");
			address = scanner.next();
			System.out.print("Destination Port :");
			port = scanner.nextInt();

			for (int i = 0; i < 3; i++) {
				if ((serversocket[i].getSourceAddress()).equals(address) && serversocket[i].getSourcePort() == port) {
					index = i;
					break;
				}
			}
			if (index == -1) {
				System.out.println("not correspond IP Address or Port, reinput!");
				continue;
			}
			// Ŭ���̾������ ����.
			clientsocket.setDestPort(port);
			clientsocket.setSourcePort(250);
			clientsocket.setIPDatagramHeader();
			clientsocket.setDestAddress(address);
			clientsocket.setSourceAddress("10.0.0.20");

			break;
		}
		// ----------------------------------------------------------
		// ����� ����
		// Make fourteen objects Router and input routing tables in each Router.
		Router routerA = new Router("10.0.0.1");
		Router routerB = new Router("10.0.0.2");
		Router routerC = new Router("10.0.0.3");
		Router routerD = new Router("10.1.0.1");
		Router routerE = new Router("10.1.0.2");
		Router routerF = new Router("10.1.1.2");
		Router routerG = new Router("10.2.0.1");
		Router routerH = new Router("10.2.0.3");
		Router routerI = new Router("10.3.0.1");
		Router routerJ = new Router("10.3.1.1");
		Router routerClient = new Router(clientsocket.getSourceAddress());

		Router[] routerServer = new Router[] { new Router(serversocket[0].getSourceAddress()),
				new Router(serversocket[1].getSourceAddress()), new Router(serversocket[2].getSourceAddress()) };

		routerA.adjRouter = new RoutingTable[] { new RoutingTable(routerB, 1), new RoutingTable(routerC, 2),
				new RoutingTable(routerD, 5), new RoutingTable(routerClient, 3) };
		routerB.adjRouter = new RoutingTable[] { new RoutingTable(routerA, 1), new RoutingTable(routerD, 5),
				new RoutingTable(routerG, 10) };
		routerC.adjRouter = new RoutingTable[] { new RoutingTable(routerA, 2), new RoutingTable(routerE, 5) };
		routerD.adjRouter = new RoutingTable[] { new RoutingTable(routerA, 5), new RoutingTable(routerB, 5),
				new RoutingTable(routerE, 1) };
		routerE.adjRouter = new RoutingTable[] { new RoutingTable(routerC, 5), new RoutingTable(routerD, 1),
				new RoutingTable(routerF, 3), new RoutingTable(routerServer[0], 3) };
		routerF.adjRouter = new RoutingTable[] { new RoutingTable(routerE, 3), new RoutingTable(routerH, 5),
				new RoutingTable(routerI, 8) };
		routerG.adjRouter = new RoutingTable[] { new RoutingTable(routerB, 10), new RoutingTable(routerH, 2),
				new RoutingTable(routerJ, 5), new RoutingTable(routerServer[2], 3) };
		routerH.adjRouter = new RoutingTable[] { new RoutingTable(routerF, 5), new RoutingTable(routerG, 2) };
		routerI.adjRouter = new RoutingTable[] { new RoutingTable(routerF, 8), new RoutingTable(routerJ, 3),
				new RoutingTable(routerServer[1], 3) };
		routerJ.adjRouter = new RoutingTable[] { new RoutingTable(routerG, 5), new RoutingTable(routerI, 3) };
		routerClient.adjRouter = new RoutingTable[] { new RoutingTable(routerA, 3) };
		routerServer[0].adjRouter = new RoutingTable[] { new RoutingTable(routerE, 3) };
		routerServer[1].adjRouter = new RoutingTable[] { new RoutingTable(routerI, 3) };
		routerServer[2].adjRouter = new RoutingTable[] { new RoutingTable(routerG, 3) };

		Router[] allrouter = new Router[] { routerA, routerB, routerC, routerD, routerE, routerF, routerG, routerH,
				routerI, routerJ, routerClient, routerServer[0], routerServer[1], routerServer[2] };

		// -----------------------------------------------------------

		// router�� ����þ˰����� �̿��Ͽ� �ùٸ� ��¸�ũ��(������) ������ �ϱ� ���Ͽ� ����� ����.

		Datagram[] routing = new Datagram[] { serversocket[0].getDatagram(), serversocket[1].getDatagram(),
				serversocket[2].getDatagram(), clientsocket.getDatagram() };
		// routerClient.setRouter(routing);

		Boolean flag = true;

		while (true) {

			TCPSegment tcpSegment = new TCPSegment(clientsocket.getSourcePort(), clientsocket.DestPort, 100, 0, 8, 0,
					new TCPFlag(0, 0, 0, 0, 1, 0), 3, 3, 5, 0); // ���� tcpSegment
			TCPSegment clientTcpSegment = new TCPSegment(); // Ŭ���̾�Ʈ ���׸�Ʈ
			TCPSegment serverTcpSegment = new TCPSegment();// ���� ���׸�Ʈ
			Datagram clientDatagram = new Datagram(); // Ŭ���̾�Ʈ �����ͱ׷�
			Datagram serverDatagram = new Datagram(); // ���� �����ͱ׷�

			if (flag) {
				clientsocket.setTCPSegment(tcpSegment); // socket�� ���� tcpSegment
														// �� �ִ´�.
				clientsocket.setDataPayload(clientsocket.sendPacket()); // socket��
																		// tcpsegment��
																		// �����ͱ׷�����
																		// �����Ų��.
				clientDatagram = clientsocket.getDatagram(); // ������ �����ͱ׷��� ������
																// ����.
				clientudc.inputDatagram(clientsocket.sendDatagram()); // ������
																		// �����ͱ׷���
																		// ä�η�
																		// ������.
				serverDatagram = processRouting(allrouter, routerClient, clientudc.outputDatagram()); // ����Ϳ���
																										// �ùٸ�
																										// ������
																										// ����.
				serversocket[index].receiveDatagram(serverDatagram);// �������Ͽ���
																	// ������
																	// �����ͱ׷���
																	// �޴´�.

				System.out.println("\n------HandShake in server--------");
				System.out.println("-----------IP Datagram-----------");
				serversocket[index].getDatagram(serverDatagram); // ����Ѵ��� ������
																	// �ٲ۴�.

				System.out.println("-----------TCP Segment-----------");
				serverTcpSegment = serversocket[index].extractTCPSegment(serverDatagram); // ������
																							// �����ͱ׷�����
																							// ���׸�Ʈ��
																							// ����.
				serversocket[index].setTCPSegment(serverTcpSegment);
				serversocket[index].getTCPSegment(); // sersocket�� TCP�� �ް�
														// sequence,ack�� �����ϰ� ����
														// ���

				serverudc[index].inputDatagram(serversocket[index].sendDatagram()); // ��������
																					// ���������ͱ׷���
																					// ����ä�η�
																					// ����.

				clientDatagram = processRouting(allrouter, routerServer[index], serverudc[index].outputDatagram()); // Ŭ���̾�Ʈ������
																													// ����.
				clientsocket.receiveDatagram(clientDatagram);

				System.out.println("\n-------HandShake in client-------"); // ��������
																			// ����
																			// �����ͱ׷���
																			// ���.
				System.out.println("-----------IP Datagram-----------");
				clientsocket.getDatagram(clientDatagram);
				System.out.println("-----------TCP Segment-----------");

				clientTcpSegment = clientsocket.extractTCPSegment(clientDatagram);
				clientsocket.setTCPSegment(clientTcpSegment);
				clientsocket.getTCPSegment();

				serverDatagram = processRouting(allrouter, routerClient, clientudc.outputDatagram());
				System.out.println("\n-------HandShake in server-------"); // ������
																			// �ڵ彦��ũ
				System.out.println("-----------IP Datagram-----------");
				System.out.println(">>Version : " + serversocket[index].datagram.getVersion());
				System.out.println(">>Header Length : " + serversocket[index].datagram.getHeaderLength());
				System.out.println(">>Service Type : " + serversocket[index].datagram.getServiceType());
				System.out.println(">>Total Length : " + serversocket[index].datagram.getDatagramLength());
				System.out.println(">>Identication : " + serversocket[index].datagram.getIdentifier());
				System.out.println(">>Flag : " + serversocket[index].datagram.getFlags());
				System.out.println(">>Fragmentation Offset : " + serversocket[index].datagram.getFragmentationOffset());
				System.out.println(">>Time to Live : " + serversocket[index].datagram.getTimeToLive());
				System.out.println(">>Protocol : " + serversocket[index].datagram.getProtocol());
				System.out.println(">>Header Checksum : " + serversocket[index].datagram.getHeaderChecksum());
				System.out.println(">>Source IP : " + serversocket[index].datagram.getSourceIP());
				System.out.println(">>Destination IP : " + serversocket[index].datagram.getDestinationIP());
				System.out.println("-----------TCP Segment-----------");
				serversocket[index].getTCPSegment();

				int temp, temp2;
				temp = tcpSegment.getSourcePort();
				temp2 = tcpSegment.getDestPort();
				tcpSegment.setSourcePort(temp2);
				tcpSegment.setDestPort(temp);

				flag = false;

			} // TCP handshake
			System.out.println("\n----------input Data for Transmitting------------");
			System.out.print("InputData : ");
			String payload = scanner.next();

			clientProcess.sendToClientSocket(client.inputData(payload, serversocket[index].tcpSegment));
			// �Է¹��� string���� ������ ���׸�Ʈ�� ��´�.
			clientTcpSegment = clientsocket.sendPacket(clientProcess.clientdata);

			// ���Ͽ� �� ���׸�Ʈ�� �ش�. ���������� ���� ������ ���� Ŭ���̾�Ʈ�� ������ ���� �����̴�.
			clientsocket.setDataPayload(clientTcpSegment);
			clientDatagram = clientsocket.getDatagram();
			clientudc.inputDatagram(clientDatagram);

			serversocket[index].receiveDatagram(serverDatagram);
			serverTcpSegment = serversocket[index].extractTCPSegment(serverDatagram);
			serverProcess[index].sendToClientComputer(serverTcpSegment);
			serverProcess[index].serverdata = clientTcpSegment;
			String data = serverProcess[index].serverdata.payloadData;
			server[index].outputData(serverProcess[index].serverdata);

			for (int i = 0; i < data.length(); i++) { // �����ͱ׷��� TCP ���׸�Ʈ�� ������ ���
				serverDatagram = processRouting(allrouter, routerClient, clientudc.outputDatagram());
				System.out.println("-----------------IP Datagram--------------");
				System.out.println(">>Version : " + serverDatagram.getVersion());
				System.out.println(">>Header Length : " + serverDatagram.getHeaderLength());
				System.out.println(">>Service Type : " + serverDatagram.getServiceType());
				System.out.println(">>Total Length : " + serverDatagram.getDatagramLength());
				System.out.println(">>Identication : " + serverDatagram.getIdentifier());
				System.out.println(">>Flag : " + serverDatagram.getFlags());
				System.out.println(">>Fragmentation Offset : " + serverDatagram.getFragmentationOffset());
				System.out.println(">>Time to Live : " + serverDatagram.getTimeToLive());
				System.out.println(">>Protocol : " + serverDatagram.getProtocol());
				System.out.println(">>Header Checksum : " + serverDatagram.getHeaderChecksum());
				System.out.println(">>Source IP : " + serverDatagram.getSourceIP());
				System.out.println(">>Destination IP : " + serverDatagram.getDestinationIP());
				System.out.println("\n---------------TCP Segment-------------");
				System.out.println(">>SourcePort :" + serverProcess[index].serverdata.getSourcePort());
				System.out.println(">>DestinationPort :" + serverProcess[index].serverdata.getDestPort());
				System.out.println(">>SequenceNumber :" + serverProcess[index].serverdata.getSequenceNumber());
				System.out.println(
						">>AcknowledgementNumber :" + serverProcess[index].serverdata.getAcknowledgementNumber());
				System.out.println(">>HeaderLength :" + serverProcess[index].serverdata.getHeaderLength());
				System.out.println(">>Reserved :" + serverProcess[index].serverdata.getReserved());
				System.out.println(">>TCPFlags ");
				System.out.println(">>URG :" + serverProcess[index].serverdata.getTcpFlag().getURG());
				System.out.println(">>ACK :" + serverProcess[index].serverdata.getTcpFlag().getACK());
				System.out.println(">>PSH :" + serverProcess[index].serverdata.getTcpFlag().getPSH());
				System.out.println(">>RST :" + serverProcess[index].serverdata.getTcpFlag().getRST());
				System.out.println(">>SYN :" + serverProcess[index].serverdata.getTcpFlag().getSYN());
				System.out.println(">>FIN :" + serverProcess[index].serverdata.getTcpFlag().getFIN());
				System.out.println(">>ReceiveWindow :" + serverProcess[index].serverdata.getWindow());
				System.out.println(">>Checksum :" + serverProcess[index].serverdata.getChecksum());
				System.out.println(">>UrgentData :" + serverProcess[index].serverdata.getUrgentDataPointer());
				System.out.println(">Payload : " + data.charAt(i));
				tcpSegment.setAcknowledgementNumber(tcpSegment.getAcknowledgementNumber() + 1);
				tcpSegment.setSequenceNumber(tcpSegment.getSequenceNumber() + 1);
			}

			System.out.println(">>you would like to continue the program,please enter y.");

			if (!((scanner.next()).equals("y"))) {

				clientsocket.tcpSegment.setReserved(1);
				clientsocket.setDataPayload(clientsocket.tcpSegment);
				clientDatagram = clientsocket.getDatagram(); // ���� �����ͱ׷� ����
				clientudc.inputDatagram(clientsocket.sendDatagram()); // ������
																		// �����ͱ׷���
																		// ä�η�
																		// ������.
				serverDatagram = processRouting(allrouter, routerClient, clientudc.outputDatagram()); // �����
																										// ����
				serversocket[index].receiveDatagram(serverDatagram); // ����������
																		// ������
																		// �����ͱ׷���
																		// �޴´�.
				clientsocket.close(); // Ŭ���̾�Ʈ������ �ݴ´�.

				serverudc[index].inputDatagram(serversocket[index].sendDatagram()); // ������
																					// �����ͱ׷���
																					// ä�η�
																					// ������.

				clientDatagram = processRouting(allrouter, routerServer[index], serverudc[index].outputDatagram()); // �����
																													// ����
				clientsocket.receiveDatagram(clientDatagram); // Ŭ���̾�Ʈ ������ ������
																// �����ͱ׷��� �޴´�.
				// receive��Ŷ�ϰ� ��ǲ ��Ŷ���� ����ؾߵǳ�

				System.out.println("-----------close socket in client-----------");
				System.out.println("-----------------IP Datagram--------------");

				clientsocket.setValue();
				clientudc.inputPacket(clientsocket.datagram.getDataPayload());

				clientTcpSegment = clientsocket.extractTCPSegment(clientDatagram);

				// clientsocket.receivePacket(clientTcpSegment);

				clientDatagram = processRouting(allrouter, routerServer[index], serverudc[index].outputDatagram());
				clientsocket.receiveDatagram(clientDatagram);
				System.out.println("\n-----------close socket in client-----------");
				System.out.println("-----------------IP Datagram--------------");
				System.out.println(">>Version : " + clientDatagram.getVersion());
				System.out.println(">>Header Length : " + clientDatagram.getHeaderLength());
				System.out.println(">>Service Type : " + clientDatagram.getServiceType());
				System.out.println(">>Total Length : " + clientDatagram.getDatagramLength());
				System.out.println(">>Identication : " + clientDatagram.getIdentifier());
				System.out.println(">>Flag : " + clientDatagram.getFlags());
				System.out.println(">>Fragmentation Offset : " + clientDatagram.getFragmentationOffset());
				System.out.println(">>Time to Live : " + clientDatagram.getTimeToLive());
				System.out.println(">>Protocol : " + clientDatagram.getProtocol());
				System.out.println(">>Header Checksum : " + clientDatagram.getHeaderChecksum());
				System.out.println(">>Source IP : " + clientDatagram.getSourceIP());
				System.out.println(">>Destination IP : " + clientDatagram.getDestinationIP());
				serverudc[index].inputPacket(serversocket[index].datagram.getDataPayload());

				clientDatagram = clientsocket.getDatagram();
				clientsocket.change(clientDatagram);
				System.out.println(clientDatagram.getDestinationIP());
				clientudc.inputDatagram(clientsocket.sendDatagram()); // ������
																		// �����ͱ׷���
																		// ä�η�
																		// ������.
				serverDatagram = processRouting(allrouter, routerClient, clientsocket.datagram); // �����
																									// ����
				serversocket[index].receiveDatagram(serverDatagram);
				TCPSegment tcpSegment2 = new TCPSegment();
				tcpSegment2 = serverudc[index].outputPacket();
				tcpSegment2.tcpFlag.setACK(1);

				serversocket[index].receivePacket(tcpSegment2);
				scanner.close();
				break;

			}
		}

	}

	static Datagram processRouting(Router[] allrouter, Router router, Datagram datagram) { // ù����
																							// ���ڴ�
																							// ��ü
																							// �����,�ι�°
																							// ���ڴ�
																							// ����
																							// �����,
																							// ����°
																							// ���ڴ�
																							// ����
																							// �����ͱ׷�
		for (Router r : allrouter) { // allrouter�� �迭�� r�� �ϳ��� �־ �ݺ��� ����,��� ����Ϳ�
										// ������
			r.minDist = Double.POSITIVE_INFINITY;
			r.previous = null; //
		}

		Dijkstra dijkstra = new Dijkstra(); // ���ͽ�Ʈ�� ������.
		// �ִ� �Ÿ��� path ����

		dijkstra.computerPaths(router); // Calculate the shortest path by
										// comparing routing tables.

		ArrayList<Router> path = new ArrayList<Router>();

		for (Router r : allrouter) {

			if (r.routerIP.equals(datagram.getDestinationIP())) {
				path = (ArrayList<Router>) dijkstra.getShortestPath(r);
				System.out.println("\nShortest path for sending IP datagram : " + path);
				System.out.println("Distance to destination : " + r.minDist + "\n");

				break;
			}
		}

		for (Router r : dijkstra.path) {
			// router �迭��� datagram ���
			switch (r.routerIP) {
			case "10.0.0.1":
				System.out.println("--- Pass through Router A---");
				break;
			case "10.0.0.2":
				System.out.println("--- Pass through Router B---");
				break;
			case "10.0.0.3":
				System.out.println("--- Pass through Router C---");
				break;
			case "10.1.0.1":
				System.out.println("--- Pass through Router D---");
				break;
			case "10.1.0.2":
				System.out.println("--- Pass through Router E---");
				break;
			case "10.1.1.2":
				System.out.println("--- Pass through Router F---");
				break;
			case "10.2.0.1":
				System.out.println("--- Pass through Router G---");
				break;
			case "10.2.0.3":
				System.out.println("--- Pass through Router H---");
				break;
			case "10.3.0.1":
				System.out.println("--- Pass through Router I---");
				break;
			case "10.3.1.1":
				System.out.println("--- Pass through Router J---");
				break;
			}
		}
		System.out.println();
		path = null;
		return datagram;
	}

}