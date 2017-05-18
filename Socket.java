
public class Socket {	
	Datagram datagram=new Datagram();
	TCPSegment tcpSegment=new TCPSegment();
	
	
	int sourcePort;
	int DestPort;
	public void setReserved(int reserved){
		this.tcpSegment.setReserved(reserved);
	}
	public void setSourceAddress(String sourceAddress){ //source address ����
		datagram.setSourceIP(sourceAddress);
	}
	public String getSourceAddress(){
		return datagram.getSourceIP();
	}
	public void setDestAddress(String destAddress){ //destination address ����
		datagram.setDestinationIP(destAddress);
	}
	public String getdestAddress(){
		return datagram.getDestinationIP();
	}
	public void setIPDatagramHeader() //���Ͼȿ� �����ͱ׷� ����κ� �ʱ�ȭ
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
	public void setDataPayload(TCPSegment tcpSegment){ //�����ͱ׷��ȿ� ���̷ε带 tcpsegment�� ����.
		datagram.setDataPayload(tcpSegment);
	}
	public Datagram sendDatagram(){ //������ ä�ο��� �����ͱ׷� ������.
		return this.datagram;
	}
	public Datagram sendDatagram(Datagram datagram){ //������ ä�ο��� �����ͱ׷� ������.
		return datagram;
	}
	public void receiveDatagram(Datagram datagram){ //ä�ηκ��� ������ �����ͱ׷��� �޴´�.
		this.datagram=datagram;

	}
	public TCPSegment extractTCPSegment(Datagram datagram){ //�����ͱ׷����� ���׸�Ʈ�� �̾Ƴ���.
		this.tcpSegment=datagram.getDataPayload();
		return this.tcpSegment;
	}
	public Datagram getDatagram(){
		return datagram;
	}
	public void getDatagram(Datagram datagram){ //�����ͱ׷� ������� ���
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
	
	public void setSourcePort(int sourcePort){ //source port ����
		this.sourcePort=sourcePort;
	}
	public int getSourcePort(){
		return sourcePort;
	}
	public void setDestPort(int destPort){ //destination port ����
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
		tcpSegment.setAcknowledgementNumber(temp2+1); //ACK�� Seq+1�� ��
		tcpSegment.setSequenceNumber(temp == 0? temp2+101 : temp); //ACK�� 0�̸� Seq�� seq+101�� ,�ƴϸ� ACK�� ����
		if(tcpSegment.tcpFlag.getACK()==1)
			tcpSegment.tcpFlag.setSYN(0);
		tcpSegment.tcpFlag.setACK(1);
	}
	public TCPSegment sendPacket(){ //���Ͽ��� ��Ŷ�� ��������. .ä�ο� inpudata ���ڷ� ����.
		return this.tcpSegment;
	}
	public TCPSegment sendPacket(TCPSegment tcpSegment){ //���Ͽ��� ��Ŷ�� ��������. .ä�ο� inpudata ���ڷ� ����.
		this.tcpSegment=tcpSegment;
		return this.tcpSegment;
	}
	public TCPSegment receivePacket(TCPSegment receiveTcpSegment){ //ä�ο��� TCP���׸�Ʈ�� �޴´�
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
	public void close(){ //������ �ݴ´�.
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
