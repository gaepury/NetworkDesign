import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Dijkstra {
	ArrayList<Router> path = new ArrayList<Router>();

	public void computerPaths(Router routersource) {
		routersource.minDist = 0; // ó�� �ʱⰪ�� 0���� ����(�ڱ��ڽ��� �Ÿ��̹Ƿ�)
		PriorityQueue<Router> routerQueue = new PriorityQueue<Router>(); // Queue�����
																			// ����
																			// Ŭ����
		routerQueue.add(routersource); // Queue�� ��ó�� ����� add

		while (!routerQueue.isEmpty()) {
			Router u = (Router) routerQueue.poll(); // ť���� �ϳ��� ����.
			for (RoutingTable routingTable : u.adjRouter) { // for���� �ٸ��ٺ��� ����
															// u.adjrouter �迭��
															// ����ִ� ������ �ϳ���
															// routingtable ������
															// �ִ´�.
				Router v = routingTable.router; // u�� ������ ��� v�� ���� router v��
												// c(u,v) �Ÿ��� ����

				double distance = routingTable.distance;
				double distanceThrougU = u.minDist + distance; // d(v)=min{d(v),d(w)+c(w,v),distanceA��
																// d(w)+c(w,v)��
																// �ش�

				if (distanceThrougU < v.minDist) {
					routerQueue.remove(v);
					v.minDist = distanceThrougU; // �ּҰ� ����
					v.previous = u; // v�� ��������ʹ� u

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
