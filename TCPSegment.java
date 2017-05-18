
public class TCPSegment {
	int sourcePort;
	int destPort;
	int sequenceNumber;
	int acknowledgementNumber;
	int headerLength;
	int reserved;
	TCPFlag tcpFlag;
	int window;
	int checksum;
	int urgentDataPointer;
	int payload;
		
	String payloadData;
	
	
	public TCPSegment(){
		
	}
	public TCPSegment(int sourcePort,int destPort,int sequenceNumber,int acknowledgmentNumber,int headerLength,int reserved,TCPFlag tcpFlag,int window,int checksum,int urgentDataPointer,int payload){
		super(); //TCP생성자
		this.sourcePort=sourcePort;
		this.destPort=destPort;
		this.sequenceNumber=sequenceNumber;
		this.acknowledgementNumber=acknowledgmentNumber;
		this.headerLength=headerLength;
		this.reserved=reserved;
		this.tcpFlag=tcpFlag;
		this.window=window;
		this.checksum=checksum;
		this.urgentDataPointer=urgentDataPointer;
		this.payload=payload;
	}
	
	public void setChecksum(int checksum){
		this.checksum=checksum;
	}
	public int getChecksum(){
		return checksum;
	}
	//이 아래는 클래스멤버변수 set,get함수 나열.
	public void setSourcePort(int sourcePort){
		this.sourcePort=sourcePort;
	}
	public int getSourcePort(){
		return sourcePort; 
	}
	public void setDestPort(int destPort){
		this.destPort=destPort;
	}
	public int getDestPort(){
		return destPort;
	}
	public void setTcpFlag(TCPFlag tcpFlag){
		this.tcpFlag=tcpFlag;
	}
	public TCPFlag getTcpFlag(){
		return tcpFlag;
	}
	public int getUrgentDataPointer(){
		return urgentDataPointer;
	}
	public void setWindow(int window){
		this.window=window;
	}
	public int getWindow(){
		return window;
	}
	
	public void setSequenceNumber(int sequenceNumber){
		this.sequenceNumber=sequenceNumber;
	}
	public int getSequenceNumber(){
		return sequenceNumber;
	}
	public void setAcknowledgementNumber(int acknowledgementNumber){
		this.acknowledgementNumber=acknowledgementNumber;
	}
	public int getAcknowledgementNumber(){
		return acknowledgementNumber;
	}
	public void setHeaderLength(int headerLength){
		this.headerLength=headerLength;
	}
	public int getHeaderLength(){
		return headerLength;
	}
	public void setReserved(int reserved){
		this.reserved=reserved;
	}
	public int getReserved(){
		return reserved;
	}
	
	public void setPayloadData(String data) {
		// TODO Auto-generated method stub
		this.payloadData = data;
	}
	public String getPayloadData(){
		return payloadData;
	}
	
}
