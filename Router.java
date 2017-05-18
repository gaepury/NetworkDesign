public class Router implements Comparable<Router>{
	Datagram datagram=new Datagram();
	Datagram[] routingDatagram=null; //���� 3���� Ŭ���̾�Ʈ 1���� �����ͱ׷��� ��� �����ͱ׷� �迭
	double minDist =Double.POSITIVE_INFINITY;
	
	Router previous;
	String routerIP;
	RoutingTable[] adjRouter; //������ ������� ���������ϴ� ��������̺�
	
	public Router(String routerIP){ //������� IP�ּ� �����ϴ� ������
		this.routerIP=routerIP;	
	}
	public String getRouterIP(){
		return routerIP;
	}

	public void setRouter(Datagram[] routing){ //set each source address of the IP datagram in router.
	                                           //����3��,Ŭ���̾�Ʈ 1���� source address set in router
//		routingDatagram=
		routingDatagram=new Datagram[]{routing[0],routing[1],routing[2],routing[3]};
//		System.out.println(routingDatagram[0].getSourceIP());
	}
	
	public void receiveDatagram(Datagram udc_datagram){ //ä�ο��� �ƿ�ǲ �����ͱ׷��� �޴´�. receive the IP datagram sent from a communication channel.
		this.datagram=udc_datagram;
		
	}
	public Datagram checkandsendDatagram(){//compare a destination address in the IP datagram with source addresses in router, and send the IP datagram to a correct destination. 

		

		for(int i=0;i<3;i++){
			if((routingDatagram[i].getSourceIP()).equals(datagram.getDestinationIP())){ //����3���߿� sourceIP�� router�� ������ �����ͱ׷�(Ŭ���̾�Ʈ�ʿ��� �� �����ͱ׷�)�� destIP�� ������ send�Ѵ�.
				return datagram;
			}
		}
		if((routingDatagram[3].getDestinationIP()).equals(datagram.getSourceIP())) //�������� ���ƿö��� ���� if�� . router�� ������ �����ͱ׷��� sourceIP(�������� �� �����ͱ׷�)�� �ʱ� �������� �Է��� Ŭ���̾�Ʈ destIP���̶� ������ send�Ѥ�.
			return datagram;
		
		return datagram;
		
	}
	public String toString(){return this.routerIP;} //Arraylist�� path�� ��½�Ű�� ���� �Լ�.
	@Override
	public int compareTo(Router o) { //priorityQueue�� �ִ� ���� ��
		// TODO Auto-generated method stub
		return Double.compare(minDist, o.minDist);
	}
	
	
}
