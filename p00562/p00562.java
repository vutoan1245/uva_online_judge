import java.util.Scanner;

class p00562{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        while(caseNum-->0){
            int coinNum = in.nextInt();
            int coinList[] = new int[coinNum];
            int totalValue = 0;
            for(int i = 0; i < coinNum; i++){
                coinList[i] = in.nextInt();
                totalValue += coinList[i];
            }

            int dp[][] = new int[coinNum+1][totalValue+1];

            for(int i = 1; i <= coinNum; i++){
                for(int j = 1; j <= totalValue; j++){
                    if(j < coinList[i-1]){
                        dp[i][j] = dp[i-1][j];
                    } else {
                        dp[i][j] = Math.max(coinList[i-1] + dp[i-1][j-coinList[i-1]], dp[i-1][j]);

                    }
                }
            }


            int target = totalValue / 2;


            System.out.println(totalValue - dp[coinNum][target] * 2);
        }

        in.close();
    }
}