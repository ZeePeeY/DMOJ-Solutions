import java.io.*;
import java.util.*;

public class Hello_Officer{
	static class Obj implements Comparable<Obj>{
		int id;
		int cost;
		Obj(int id1, int cost1){
			id = id1;
			cost = cost1;
		}
		@Override
		public int compareTo(Obj o){
			if(cost < o.cost){
				return -1;
			} else if(cost > o.cost){
				return 1;
			}
			return 0;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tokens = br.readLine().split(" ");
		int houses = Integer.parseInt(tokens[0]);
		int roads = Integer.parseInt(tokens[1]);
		int dateh = Integer.parseInt(tokens[2]);
		int queries = Integer.parseInt(tokens[3]);
		ArrayList<Obj>[] graph = new ArrayList[houses + 1];
		for(int i = 1; i <= houses; i++){
			graph[i] = new ArrayList<>();
		}
		for(int i = 0; i < roads; i++){
			tokens = br.readLine().split(" ");
			int housea = Integer.parseInt(tokens[0]);
			int houseb = Integer.parseInt(tokens[1]);
			int time = Integer.parseInt(tokens[2]);
			graph[housea].add(new Obj(houseb, time));
			graph[houseb].add(new Obj(housea, time));
		}
		PriorityQueue<Obj> nodes = new PriorityQueue<>();
		nodes.add(new Obj(dateh, 0));
		int[] dis = new int[houses + 1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		dis[dateh] = 0;
		boolean[] vis = new boolean[houses + 1];
		while(!nodes.isEmpty()){
			Obj cur = nodes.poll();
			if(vis[cur.id]){
				continue;
			}
			vis[cur.id] = true;
			ArrayList<Obj> adjnodes = graph[cur.id];
			for(Obj adj : adjnodes){
				if(!vis[adj.id]){
					if(dis[cur.id] + adj.cost < dis[adj.id]){
						nodes.add(new Obj(adj.id, dis[cur.id] + adj.cost));
						dis[adj.id] = dis[cur.id] + adj.cost;
					}
				}
			}
		}
		for(int i = 0; i < queries; i++){
			int curh = Integer.parseInt(br.readLine());
			if(dis[curh] == Integer.MAX_VALUE){
				System.out.println(-1);
			} else{
				System.out.println(dis[curh]);
			}
		}
	}
}