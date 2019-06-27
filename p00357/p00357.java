import java.util.Scanner;

class p00357{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int coinList[] = {50, 25, 10, 5, 1};
        int coinNum = coinList.length;

        int MAX = 30000;
        long possible[] = new long[MAX + 1];
        possible[0] = 1;
        for(int i = 0; i < coinNum; i++){
            for(int j = coinList[i]; j <= MAX; j++){
                possible[j] += possible[j-coinList[i]];
            }
        }


        while(in.hasNext()){
            int value = in.nextInt();
            if(possible[value] == 1)
                System.out.printf("There is only %d way to produce %d cents change.\n", possible[value], value);
            else
                System.out.printf("There are %d ways to produce %d cents change.\n", possible[value], value);
        }

        in.close();
    }
}