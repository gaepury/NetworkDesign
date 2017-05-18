
public class Socket {	
	Datagram datagram=new Datagram();
	TCPSegment tcpSegment=new TCPSegment();
	
	
	int sourcePort;
	int DestPort;
	public void setReserved(int reserved){
		this.tcpSegment.setReserved(reserved);
	}
	public void setSourceAddress(String sourceAddress){ //source address 설정
		datagram.setSourceIP(sourceAddress);
	}
	public String getSourceAddress(){
		return datagram.getSourceIP();
	}
	public void setDestAddress(String destAddress){ //destination address 설정
		datagram.setDestinationIP(destAddress);
	}
	public String getdestAddress(){
		return datagram.getDestinationIP();
	}
	public void setIPDatagramHeader() //소켓안에 데이터그램 헤더부분 초기화
	{
		
		datagram.setVersion((short)4);
		datagram.setHeaderLengh((short)20);
		datagram.setServiceType((short)0);
		datagram.setDatagramLength((short)600);
		datagram.setIdentifier((short)0);
		datagram.setFlags((short)0);
		datagram.setFragmentationOffset((short)0);
		datagram.setTimeToLive((short)500);
		datagram.setProtocol((short)6);
		datagram.setHeaderChecksum((short)0);
		
	}
	public void setDataPayload(TCPSegment tcpSegment){ //데이터그램안에 페이로드를 tcpsegment로 설정.
		datagram.setDataPayload(tcpSegment);
	}
	public Datagram sendDatagram(){ //소켓이 채널에게 데이터그램 보낸다.
		return this.datagram;
	}
	public Datagram sendDatagram(Datagram datagram){ //소켓이 채널에게 데이터그램 보낸다.
		return datagram;
	}
	public void receiveDatagram(Datagram datagram){ //채널로부터 소켓은 데이터그램을 받는다.
		this.datagram=datagram;

	}
	public TCPSegment extractTCPSegment(Datagram datagram){ //데이터그램에서 세그먼트를 뽑아낸다.
		this.tcpSegment=datagram.getDataPayload();
		return this.tcpSegment;
	}
	public Datagram getDatagram(){
		return datagram;
	}
	public void getDatagram(Datagram datagram){ //데이터그램 헤더정보 출력
		System.out.println(">>Version : " +datagram.getVersion());
		System.out.println(">>Header Length : "+ datagram.getHeaderLength() );
		System.out.println(">>Service Type : "+datagram.getServiceType());
		System.out.println(">>Total Length : "+datagram.getDatagramLength());
		System.out.println(">>Identication : "+datagram.getIdentifier());
		System.out.println(">>Flag : "+datagram.getFlags());
		System.out.println(">>Fragmentation Offset : "+datagram.getFragmentationOffset());
		System.out.println(">>Time to Live : "+datagram.getTimeToLive());
		System.out.println(">>Protocol : "+datagram.getProtocol());
		System.out.println(">>Header Checksum : "+datagram.getHeaderChecksum());
		System.out.println(">>Source IP : "+datagram.getSourceIP());
		System.out.println(">>Destination IP : "+datagram.getDestinationIP());
		String temp,temp2;
		temp=datagram.getSourceIP();
		temp2=datagram.getDestinationIP();
		datagram.setSourceIP(temp2);
		datagram.setDestinationIP(temp);
		this.datagram=datagram;
		
	}
	
	public void setSourcePort(int sourcePort){ //source port 설정
		this.sourcePort=sourcePort;
	}
	public int getSourcePort(){
		return sourcePort;
	}
	public void setDestPort(int destPort){ //destination port 설정
		this.DestPort=destPort;
	}
	public int getDestPort(){
		return sourcePort;
	}
	public void setTCPSegment(TCPSegment tcpSegment){
		this.tcpSegment=tcpSegment;
	}
	public void getTCPSegment(){ 
		
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
		System.out.println(">>Checksum :" +tcpSegment.getChecksum());
		System.out.println(">>UrgentData :" +tcpSegment.getUrgentDataPointer());
		System.out.println(">>payload :" +tcpSegment.getPayloadData());
		
		int temp,temp2;
		temp=tcpSegment.getSourcePort();
		temp2=tcpSegment.getDestPort();
		tcpSegment.setSourcePort(temp2);
		tcpSegment.setDestPort(temp);
		temp=tcpSegment.getAcknowledgementNumber();
		temp2=tcpSegment.getSequenceNumber();
		tcpSegment.setAcknowledgementNumber(temp2+1); //ACK를 Seq+1로 함
		tcpSegment.setSequenceNumber(temp == 0? temp2+101 : temp); //ACK가 0이면 Seq를 seq+101로 ,아니면 ACK로 변경
		if(tcpSegment.tcpFlag.getACK()==1)
			tcpSegment.tcpFlag.setSYN(0);
		tcpSegment.tcpFlag.setACK(1);
	}
	public TCPSegment sendPacket(){ //소켓에서 패킷을 내보낸다. .채널에 inpudata 인자로 들어간다.
		return this.tcpSegment;
	}
	public TCPSegment sendPacket(TCPSegment tcpSegment){ //소켓에서 패킷을 내보낸다. .채널에 inpudata 인자로 들어간다.
		this.tcpSegment=tcpSegment;
		return this.tcpSegment;
	}
	public TCPSegment receivePacket(TCPSegment receiveTcpSegment){ //채널에서 TCP세그먼트를 받는다
		this.tcpSegment=receiveTcpSegment;
		
		if(tcpSegment.getTcpFlag().getFIN()==1){
			System.out.println();
			String temp3,temp4;
			temp3=datagram.getSourceIP();
			temp4=datagram.getDestinationIP();
			datagram.setSourceIP(temp4);
			datagram.setDestinationIP(temp3);
			int temp,temp2;
			temp=tcpSegment.getSourcePort();
			temp2=tcpSegment.getDestPort();
			tcpSegment.setSourcePort(temp2);
			tcpSegment.setDestPort(temp);
			temp=tcpSegment.getAcknowledgementNumber();
			temp2=tcpSegment.getSequenceNumber();
			tcpSegment.setAcknowledgementNumber(temp2+1);
			tcpSegment.setSequenceNumber(temp == 0? temp2+101 : temp);
			if(tcpSegment.getTcpFlag().getACK()==1) 
				tcpSegment.getTcpFlag().setACK(1);
				tcpSegment.getTcpFlag().setFIN(0);
			System.out.println("-----------close socket in server-----------");
			System.out.println("-----------------IP Datagram--------------");
			System.out.println(">>Version : " +datagram.getVersion());
			System.out.println(">>Header Length : "+ datagram.getHeaderLength() );
			System.out.println(">>Service Type : "+datagram.getServiceType());
			System.out.println(">>Total Length : "+datagram.getDatagramLength());
			System.out.println(">>Identication : "+datagram.getIdentifier());
			System.out.println(">>Flag : "+datagram.getFlags());
			System.out.println(">>Fragmentation Offset : "+datagram.getFragmentationOffset());
			System.out.println(">>Time to Live : "+datagram.getTimeToLive());
			System.out.println(">>Protocol : "+datagram.getProtocol());
			System.out.println(">>Header Checksum : "+datagram.getHeaderChecksum());
			System.out.println(">>Source IP : "+datagram.getSourceIP());
			System.out.println(">>Destination IP : "+datagram.getDestinationIP());
			System.out.println("\n---------------TCP Segment-------------\n");
			System.out.println(">>SourcePort :" +tcpSegment.getSourcePort());
			System.out.println(">>DestinationPort :" +tcpSegment.getDestPort());
			System.out.println(">>SequenceNumber :" +tcpSegment.getSequenceNumber());
			System.out.println(">>AcknowledgementNumber :" +tcpSegment.getAcknowledgementNumber());
			System.out.println(">>HeaderLength :" +tcpSegment.getHeaderLength());
			System.out.println(">>Reserved :" +tcpSegment.getReserved());
			System.out.println(">>TCPFlags :" );
			System.out.println(">>URG :" +tcpSegment.getTcpFlag().getURG());
			System.out.println(">>ACK :" +tcpSegment.getTcpFlag().getACK());
			System.out.println(">>PSH :" +tcpSegment.getTcpFlag().getPSH());
			System.out.println(">>RST :" +tcpSegment.getTcpFlag().getRST());
			System.out.println(">>SYN :" +tcpSegment.getTcpFlag().getSYN());
			System.out.println(">>FIN :" +tcpSegment.getTcpFlag().getFIN());
			System.out.println(">>ReceiveWindow :" +tcpSegment.getWindow());
			System.out.println(">>UrgentData :" +tcpSegment.getUrgentDataPointer());
		}
		return this.tcpSegment;
	}
	public void change(Datagram datagram){
		String temp1,temp2;
		temp1=datagram.getSourceIP();
		temp2=datagram.getDestinationIP();
		datagram.setSourceIP(temp2);
		datagram.setDestinationIP(temp1);
		this.datagram=datagram;
	}
	public void close(){ //소켓을 닫는다.
		int temp,temp2;
		
		tcpSegment.getTcpFlag().setACK(0);
		tcpSegment.getTcpFlag().setFIN(1);
		
		System.out.println("-----------close socket in server-----------");
		System.out.println("-----------------IP Datagram--------------");
		System.out.println(">>Version : " +datagram.getVersion());
		System.out.println(">>Header Length : "+ datagram.getHeaderLength() );
		System.out.println(">>Service Type : "+datagram.getServiceType());
		System.out.println(">>Total Length : "+datagram.getDatagramLength());
		System.out.println(">>Identication : "+datagram.getIdentifier());
		System.out.println(">>Flag : "+datagram.getFlags());
		System.out.println(">>Fragmentation Offset : "+datagram.getFragmentationOffset());
		System.out.println(">>Time to Live : "+datagram.getTimeToLive());
		System.out.println(">>Protocol : "+datagram.getProtocol());
		System.out.println(">>Header Checksum : "+datagram.getHeaderChecksum());
		System.out.println(">>Source IP : "+datagram.getSourceIP());
		System.out.println(">>Destination IP : "+datagram.getDestinationIP());
		System.out.println("-----------------TCP Segment----------------");
		System.out.println(">>SourcePort :" +tcpSegment.getSourcePort());
		System.out.println(">>DestinationPort :" +tcpSegment.getDestPort());
		System.out.println(">>SequenceNumber :" +tcpSegment.getSequenceNumber());
		System.out.println(">>AcknowledgementNumber :" +tcpSegment.getAcknowledgementNumber());
		System.out.println(">>HeaderLength :" +tcpSegment.getHeaderLength());
		System.out.println(">>Reserved :" +tcpSegment.getReserved());
		System.out.println(">>TCPFlags " );
		System.out.println(">>URG :" +tcpSegment.getTcpFlag().getURG());
		System.out.println(">>ACK :" +tcpSegment.getTcpFlag().getACK());
		System.out.println(">>PSH :" +tcpSegment.getTcpFlag().getPSH());
		System.out.println(">>RST :" +tcpSegment.getTcpFlag().getRST());
		System.out.println(">>SYN :" +tcpSegment.getTcpFlag().getSYN());
		System.out.println(">>FIN :" +tcpSegment.getTcpFlag().getFIN());
		System.out.println(">>ReceiveWindow :" +tcpSegment.getWindow());
		System.out.println(">>UrgentData :" +tcpSegment.getUrgentDataPointer());
		temp=tcpSegment.getSourcePort();
		temp2=tcpSegment.getDestPort();
		tcpSegment.setSourcePort(temp2);
		tcpSegment.setDestPort(temp);
		temp=tcpSegment.getAcknowledgementNumber();
		temp2=tcpSegment.getSequenceNumber();
		tcpSegment.setAcknowledgementNumber(temp2);
		tcpSegment.setSequenceNumber(temp);
		String temp3,temp4;
		temp3=datagram.getSourceIP();
		temp4=datagram.getDestinationIP();
		datagram.setSourceIP(temp4);
		datagram.setDestinationIP(temp3);
	}

	public void setValue() {
		// TODO Auto-generated method stub
		int temp=tcpSegment.getAcknowledgementNumber();
		int temp2=tcpSegment.getSequenceNumber();
		tcpSegment.setSequenceNumber(temp2-1);
		
	}
}
