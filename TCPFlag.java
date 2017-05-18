
public class TCPFlag {
	public int URG,ACK,PSH,RST,SYN,FIN;
	
	public TCPFlag(int URG,int ACK,int PSH,int RST,int SYN,int FIN){
		this.URG=URG;
		this.ACK=ACK;
		this.PSH=PSH;
		this.RST=RST;
		this.SYN=SYN;
		this.FIN=FIN;
	}
	//멤버변수 set,get함수
	public void setURG(int URG){this.URG=URG;}
	public int getURG(){return URG;}
	public void setACK(int ACK){this.ACK=ACK;}
	public int getACK(){return ACK;}
	public void setPSH(int PSH){this.PSH=PSH;}
	public int getPSH(){return PSH;}
	public void setRST(int RST){this.RST=RST;}
	public int getRST(){return RST;}
	public void setSYN(int SYN){this.SYN=SYN;}
	public int getSYN(){return SYN;}
	public void setFIN(int FIN){this.FIN=FIN;}
	public int getFIN(){return FIN;}
}
