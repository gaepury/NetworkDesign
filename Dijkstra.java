import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Dijkstra {
	ArrayList<Router> path = new ArrayList<Router>();

	public void computerPaths(Router routersource) {
		routersource.minDist = 0; // 처음 초기값은 0으로 설정(자기자신의 거리이므로)
		PriorityQueue<Router> routerQueue = new PriorityQueue<Router>(); // Queue사용을
																			// 위한
																			// 클래스
		routerQueue.add(routersource); // Queue에 맨처음 라우터 add

		while (!routerQueue.isEmpty()) {
			Router u = (Router) routerQueue.poll(); // 큐에서 하나를 뺀다.
			for (RoutingTable routingTable : u.adjRouter) { // for문의 다른바복문 형태
															// u.adjrouter 배열에
															// 들어있는 값들을 하나씩
															// routingtable 변수에
															// 넣는다.
				Router v = routingTable.router; // u의 인접한 노드 v에 대해 router v와
												// c(u,v) 거리를 저장

				double distance = routingTable.distance;
				double distanceThrougU = u.minDist + distance; // d(v)=min{d(v),d(w)+c(w,v),distanceA는
																// d(w)+c(w,v)에
																// 해당

				if (distanceThrougU < v.minDist) {
					routerQueue.remove(v);
					v.minDist = distanceThrougU; // 최소값 갱신
					v.previous = u; // v의 이전라우터는 u

					routerQueue.add(v);
				}
			}

		}
	}

	public ArrayList<Router> getShortestPath(Router destRouter) {

		for (Router router = destRouter; router != null; router = router.previous)
			path.add(router);
		Collections.reverse(path);
		return path;

	}

}
