import java.util.*;
import java.io.*;

public class p10389 {

	static double adjMatrix[][];
	static int nodeNum;
	static final long INF = Long.MAX_VALUE;
	
	public static long dijkstra(int src, int dest) {
		double[] time = new double[nodeNum];
		Arrays.fill(time, INF);
        time[src] = 0;
        
		PriorityQueue<Pair> pq = new PriorityQueue<Pair>();
		pq.add(new Pair(src,0));
		while(!pq.isEmpty()){
			Pair curr = pq.poll();
			if(curr.time > time[curr.node]) continue;
                
			for(int i = 0; i < nodeNum; i++){
                if(curr.node == i) continue;
                if(curr.time + adjMatrix[curr.node][i] < time[i]){
                    time[i] = curr.time + adjMatrix[curr.node][i];
                    pq.add(new Pair(i,time[i]));
                }	
            }

		}
			
		return Math.round(time[dest]/60);
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int caseNum = Integer.parseInt(in.readLine());
		in.readLine();
		while(caseNum-->0){
			TreeMap<Point,Integer> mapPointToIndex  = new TreeMap<Point,Integer>();
			Point[] pointList = new Point[250];
            adjMatrix = new double[204][204];
            
			for(int i = 0; i < 204; i++)
                Arrays.fill(adjMatrix[i], -10);
                
			StringTokenizer st = new StringTokenizer(in.readLine());
			Point src = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
			Point dest = new Point(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
            pointList[0] = src;
            pointList[1] = dest;
			mapPointToIndex.put(src,0);
			mapPointToIndex.put(dest,1);
			adjMatrix[0][1] = adjMatrix[1][0] = Math.hypot(src.x - dest.x, src.y - dest.y)/50*18;
            nodeNum = 2;
            
			String line;
			while(in.ready() && !(line=in.readLine()).equals("")){
				st = new StringTokenizer(line);
                Point last = null;
                int u = -1;
				while(true){
					if(!st.hasMoreTokens())
						st = new StringTokenizer(in.readLine());
					int x = Integer.parseInt(st.nextToken());
					int y = Integer.parseInt(st.nextToken());
					if(x==-1 && y==-1)
						break;
                    Point curr = new Point(x,y);
                    int index;
                    
					if(mapPointToIndex.containsKey(curr)){
						index = mapPointToIndex.get(curr);
                    } else {
						index = nodeNum++;
						pointList[index] = curr;
						mapPointToIndex.put(curr, index);
					}
					if(last!=null)
						adjMatrix[u][index] = adjMatrix[index][u] = Math.hypot(curr.x - last.x, curr.y - last.y)/200*18;
					adjMatrix[0][index] = adjMatrix[index][0] = Math.hypot(curr.x - src.x, curr.y - src.y)/50*18;
					adjMatrix[1][index] = adjMatrix[index][1] = Math.hypot(curr.x - dest.x, curr.y - dest.y)/50*18;
					last = curr;
					u = index;
						
				}
			}
			for(int i = 0; i < nodeNum; i++)
				for(int j = i + 1; j < nodeNum; j++)
					if(adjMatrix[i][j]<-1)
						adjMatrix[i][j] = adjMatrix[j][i] = Math.hypot(pointList[i].x - pointList[j].x, pointList[i].y - pointList[j].y)/50*18;
                    
                        
			long result = dijkstra(0,1);
			System.out.println(result);
			if(caseNum!=0)
                System.out.println();	
		}
		
	}
	
	static class Point implements Comparable<Point>{
		int x,y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
		
		public int compareTo(Point p) {
			if(this.x!=p.x)
				return this.x - p.x;
			return this.y - p.y;
		}
	}

	static class Pair implements Comparable<Pair>{
        int node; 
        double time;
		Pair(int x, double t){
            node = x; 
            time = t;
        }
		
		public int compareTo(Pair p){
			if(this.time <= p.time)
				return -1;
			return 1;
		}
		
	}
}
