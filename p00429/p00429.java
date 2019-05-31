import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;
 
public class p00429 {
    static LinkedList<Integer>[] adjList;
    static TreeMap<Integer, Integer> dist;
    static Queue<Integer> queue;
 
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();
        int cases = Integer.parseInt(in.readLine());
        String[] inp, sp;
        String l;
        int counter;
        queue = new LinkedList<Integer>();
        in.readLine();

        for (int i = 0; i < cases; i++) {
            inp = new String[200];
            counter = 0;
            l = in.readLine();
            while (!l.equals("*")) {
                inp[counter++] = l;
                l = in.readLine();
            }

            adjList = new LinkedList[counter];
            for (int j = 0; j < counter; j++)
                adjList[j] = new LinkedList<Integer>();
            for (int j = 0; j < counter; j++)
                for (int j2 = j + 1; j2 < counter; j2++)
                    if (checkWords(inp[j], inp[j2])) {
                        adjList[j].add(j2);
                        adjList[j2].add(j);
                    }
            l = in.readLine();

            while (!l.equals("")) {
                dist = new TreeMap<Integer, Integer>();
                queue.clear();
                sp = l.split(" ");
                int start = getIndex(inp, sp[0]);
                int end = getIndex(inp, sp[1]);
                out.append(l + " " + bfs(start, end) + "\n");
                l = in.readLine();
                if (l == null)
                    break;
            }
            if (i != cases - 1)
                out.append("\n");
        }
        System.out.print(out);
    }

    static boolean checkWords(String first, String second) {
        int counter = 0;
        if (first.length() != second.length())
            return false;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != second.charAt(i))
                counter++;
            if (counter > 1)
                return false;
        }
        return true;
    }

    static int getIndex(String[] inp, String s) {
        for (int i = 0; i < inp.length; i++)
            if (inp[i].equals(s))
                return i;
        return -1;
    }

    static int bfs(int st, int end) {
        dist.put(st, 0);
        queue.add(st);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int i = 0; i < adjList[u].size(); i++) {
                int v = adjList[u].get(i);
                if (!dist.containsKey(v)) {
                    if (v == end)
                        return dist.get(u) + 1;
                    dist.put(v, dist.get(u) + 1);
                    queue.add(v);
                }
            }
        }
        return 0;
    }
}