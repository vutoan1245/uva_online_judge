import java.util.Scanner;

class p00507{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);
        
        int caseNum = in.nextInt();
        for(int i = 0; i < caseNum; i++){
            // Get input
            int stopNum = in.nextInt()-1;
            int stopList[] = new int[stopNum];
            for(int j = 0; j < stopNum; j++){
                stopList[j] = in.nextInt();
            }

            // Calculate output
            int sum = 0, ans = 0, size = 0, from = 1, to = 1, oldFrom = 1;
            for(int j = 0; j < stopNum; j++){
                sum += stopList[j];

                // New result
                if(sum > ans){
                    ans = sum;
                    from = oldFrom;
                    to = j+2;
                    size = to - from;
                }
                // Case if the two path have the same result
                if(sum == ans){
                    if(j+2 - oldFrom > size){
                        ans = sum;
                        from = oldFrom;
                        to = j+2;
                        size = from - to;
                    }
                }

                // reset sum to zero if it is negative
                if(sum < 0) {
                    sum = 0;
                    oldFrom = j+2;
                }
            }

            // Print result
            if(ans == 0){
                System.out.printf("Route %d has no nice parts\n", i+1);
            } else {
                System.out.printf("The nicest part of route %d is between stops %d and %d\n", i+1, from, to);
            }
        }

        in.close();
    }
}