public class Router implements Comparable<Router>{
	Datagram datagram=new Datagram();
	Datagram[] routingDatagram=null; //서버 3개와 클라이언트 1개의 데이터그램을 담는 데이터그램 배열
	double minDist =Double.POSITIVE_INFINITY;
	
	Router previous;
	String routerIP;
	RoutingTable[] adjRouter; //인접한 라우터의 정보저장하는 라우팅테이블
	
	public Router(String routerIP){ //라우터의 IP주소 설정하는 생성자
		this.routerIP=routerIP;	
	}
	public String getRouterIP(){
		return routerIP;
	}

	public void setRouter(Datagram[] routing){ //set each source address of the IP datagram in router.
	                                           //서버3개,클라이언트 1개의 source address set in router
//		routingDatagram=
		routingDatagram=new Datagram[]{routing[0],routing[1],routing[2],routing[3]};
//		System.out.println(routingDatagram[0].getSourceIP());
	}
	
	public void receiveDatagram(Datagram udc_datagram){ //채널에서 아웃풋 데이터그램을 받는다. receive the IP datagram sent from a communication channel.
		this.datagram=udc_datagram;
		
	}
	public Datagram checkandsendDatagram(){//compare a destination address in the IP datagram with source addresses in router, and send the IP datagram to a correct destination. 

		

		for(int i=0;i<3;i++){
			if((routingDatagram[i].getSourceIP()).equals(datagram.getDestinationIP())){ //서버3개중에 sourceIP가 router에 도착한 데이터그램(클라이언트쪽에서 온 데이터그램)에 destIP랑 같으면 send한다.
				return datagram;
			}
		}
		if((routingDatagram[3].getDestinationIP()).equals(datagram.getSourceIP())) //서버에서 돌아올때를 위한 if문 . router에 도착한 데이터그램의 sourceIP(서버에서 온 데이터그램)가 초기 설정에서 입력한 클라이언트 destIP값이랑 같으면 send한ㄷ.
			return datagram;
		
		return datagram;
		
	}
	public String toString(){return this.routerIP;} //Arraylist인 path를 출력시키기 위한 함수.
	@Override
	public int compareTo(Router o) { //priorityQueue에 있는 것을 비교
		// TODO Auto-generated method stub
		return Double.compare(minDist, o.minDist);
	}
	
	
}
