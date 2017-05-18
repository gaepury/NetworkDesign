
public class UnreliableDataChannel {
	public Datagram datagram=new Datagram();
	public TCPSegment tcpSegment=new TCPSegment();
	public static boolean isClose=false;
	
	public void inputPacket(TCPSegment tcpSegment){  //소켓이 보낸 세그먼트가 채널로 들어간다.
		this.tcpSegment=tcpSegment;
		
		if(tcpSegment.getTcpFlag().getFIN()==1)
			isClose=true;
		if(isClose){
			tcpSegment.setAcknowledgementNumber(tcpSegment.getAcknowledgementNumber()+1); 
			tcpSegment.setSequenceNumber(tcpSegment.getSequenceNumber()+1);
			if(tcpSegment.getTcpFlag().getFIN()==1){
				tcpSegment.getTcpFlag().setACK(1);
				tcpSegment.getTcpFlag().setFIN(0);
			}else if(tcpSegment.getTcpFlag().getACK()==1){
				tcpSegment.getTcpFlag().setACK(0);
				tcpSegment.getTcpFlag().setFIN(1);
			}
			System.out.println("---------------TCP Segment-------------");
			System.out.println(">>SourcePort :" +tcpSegment.getSourcePort());
			System.out.println(">>DestinationPort :" +tcpSegment.getDestPort());
			System.out.println(">>SequenceNumber :" +tcpSegment.getSequenceNumber());
			System.out.println(">>AcknowledgementNumber :" +tcpSegment.getAcknowledgementNumber());
			System.out.println(">>HeaderLength :" +tcpSegment.getHeaderLength());
			System.out.println(">>Reserved :" +tcpSegment.getReserved());
			System.out.println(">>TCPFlags " );
			System.out.println(">>URG :" +tcpSegment.getTcpFlag().URG);	
			System.out.println(">>ACK :" +tcpSegment.getTcpFlag().ACK);
			System.out.println(">>PSH :" +tcpSegment.getTcpFlag().PSH);
			System.out.println(">>RST :" +tcpSegment.getTcpFlag().RST);
			System.out.println(">>SYN :" +tcpSegment.getTcpFlag().SYN);
			System.out.println(">>FIN :" +tcpSegment.getTcpFlag().FIN);
			System.out.println(">>ReceiveWindow :" +tcpSegment.getWindow());
			System.out.println(">>UrgentData :" +tcpSegment.getUrgentDataPointer());
		}
	}
	public TCPSegment outputPacket(){
		return this.tcpSegment;
	}
	public void inputDatagram(Datagram datagram){
		this.datagram=datagram;

	}
	public Datagram outputDatagram(){
		return this.datagram;
	}
	
	
}
