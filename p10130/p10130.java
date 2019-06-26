import java.util.Arrays;
import java.util.Scanner;

class p10130{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        while(caseNum-->0){
            int itemNum = in.nextInt();
            Item itemList[] = new Item[itemNum];
            for(int i = 0; i < itemNum; i++){
                itemList[i] = new Item(in.nextInt(), in.nextInt());
            }
            Arrays.sort(itemList);

            int peopleNum = in.nextInt();
            int weightList[] = new int[peopleNum];
            for(int i = 0; i < peopleNum; i++){
                weightList[i] = in.nextInt();
            }
            Arrays.sort(weightList);

            // Calculate output
            int result = 0;
            for(int i = 0; i < peopleNum; i++){
                int weight = weightList[i];
                int dp[][] = new int[itemNum+1][weight+1];
                
                for(int j = 1; j <= itemNum; j++){
                    for(int k = 1; k <= weight; k++){
                        if(k < itemList[j-1].weight){
                            dp[j][k] = dp[j-1][k];
                        } else {
                            dp[j][k] = Math.max(itemList[j-1].value + dp[j-1][k-itemList[j-1].weight], dp[j-1][k]);
                        }
                    }
                }

                result += dp[itemNum][weight];
            }

            System.out.println(result);
        }

        in.close();
    }
}

class Item implements Comparable<Item>{
    int value;
    int weight;
    Item(int value, int weight){
        this.value = value;
        this.weight = weight;
    }

    @Override
    public int compareTo(Item other) {
        if(value == other.value) return weight - other.weight;
        return value - other.value;
    }
}