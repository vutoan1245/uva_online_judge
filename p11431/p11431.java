import java.util.Scanner;

class p11431{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        while(in.hasNext()){
            int vesselNum = in.nextInt();
            int containerNum = in.nextInt();
            int vesselList[] = new int[vesselNum];
            for(int i = 0; i < vesselNum; i++){
                vesselList[i] = in.nextInt();
            }

            // Calculate output
            int start = 0, end = 1000000000, mid = 0, result = 0;
            while(end >= start){
                mid = (start + end) / 2;
                if(check(vesselList, containerNum, mid)){
                    result = mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            System.out.println(result);
        }

        in.close();
    }

    public static boolean check(int vesselList[], int containerNum, int C){
        int container = 1;
        int capacity = C;
        for (int i = 0; i < vesselList.length; ++i) {
            if (vesselList[i] > C)
                return false;
    
            if (vesselList[i] > capacity) {
                if (container == containerNum)
                    return false;
                ++container;
                capacity = C;
            }
            capacity -= vesselList[i];
        }
        return true;
    }
}