
public class RoutingTable {
	Router router;
	double distance; //router information for object Router, and metric information(거리정보) for adjacent routers, clients, and servers.
	
	public RoutingTable(Router router, double distance){ //라우팅테이블 생성자, 
		this.router=router;
		this.distance=distance;
		
	}
	
}
