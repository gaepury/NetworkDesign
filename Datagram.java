
public class Datagram {
	private short version;
	private short headerLength;
	private short serviceType;
	private short datagramLength;
	private short identifier;
	private short flags;
	private short fragmentationOffset;
	private short timeToLive;
	private short protocol;
	private short headerChecksum;
	private String SourceIP,DestinationIP;
	private TCPSegment dataPayload;
	//----------------------멤머 변수 set,get 함수-----------
	public void setVersion(short version){
		this.version=version;
	}
	public short getVersion(){
		return version;
	}
	public void setHeaderLengh(short headerLength){
		this.headerLength=headerLength;
	}
	public short getHeaderLength(){
		return headerLength;
	}
	public void setServiceType(short serviceType){
		this.serviceType=serviceType;
	}
	public short getServiceType(){
		return serviceType;
	}
	public void setDatagramLength(short datagramLength){
		this.datagramLength=datagramLength;
	}
	public short getDatagramLength(){
		return datagramLength;
	}
	public void setIdentifier(short identifier){
		this.identifier=identifier;
	}
	public short getIdentifier(){
		return identifier;
	}
	public void setFlags(short flags){
		this.flags=flags;
	}
	public short getFlags(){
		return flags;
	}
	public void setFragmentationOffset(short fragmentationOffset){
		this.fragmentationOffset=fragmentationOffset;
	}
	public short getFragmentationOffset(){
		return fragmentationOffset;
	}
	public void setTimeToLive(short timeToLive){
		this.timeToLive=timeToLive;
	}
	public short getTimeToLive(){
		return timeToLive;
	}
	public void setProtocol(short protocol){
		this.protocol=protocol;
	}
	public short getProtocol(){
		return protocol;
	}
	public void setHeaderChecksum(short headerChecksum){
		this.headerChecksum=headerChecksum;
	}
	public short getHeaderChecksum(){
		return headerChecksum;
	}
	public void setSourceIP(String SourceIP){
		this.SourceIP=SourceIP;
	}
	public String getSourceIP(){
		return SourceIP;
	}
	public void setDestinationIP(String DestinationIP){
		this.DestinationIP=DestinationIP;
	}
	public String getDestinationIP(){
		return DestinationIP;
	}
	public void setDataPayload(TCPSegment dataPayload){
		this.dataPayload=dataPayload;
	}
	public TCPSegment getDataPayload(){
		return dataPayload;
	}
	
	
}
