import java.util.Scanner;

class p00674{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int coinList[] = {50, 25, 10, 5, 1};
        int coinNum = coinList.length;

        int MAX = 7489;
        long possible[] = new long[MAX+1];
        possible[0] = 1;
        for(int i = 0; i < coinNum; i++){
            for(int j = coinList[i]; j <= MAX; j++){
                possible[j] += possible[j-coinList[i]];
            }
        }

        while(in.hasNext()){
            System.out.println(possible[in.nextInt()]);
        }

        in.close();
    }
}