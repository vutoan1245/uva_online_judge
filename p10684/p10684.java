import java.util.Scanner;

class p10684{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int betNum;
        while((betNum = in.nextInt()) != 0){
            int betList[] = new int[betNum];
            for(int i = 0; i < betNum; i++){
                betList[i] = in.nextInt();
            }

            int ans = 0, sum = 0;
            for(int i = 0; i < betNum; i++){
                sum += betList[i];
                ans = Math.max(ans, sum);
                if(sum < 0) sum = 0;
            }

            if(ans == 0){
                System.out.println("Losing streak.");
            } else {
                System.out.printf("The maximum winning streak is %d.\n", ans);
            }
        }

        in.close();
    }
}