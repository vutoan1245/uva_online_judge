import java.util.Scanner;

class p12032{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        for(int i = 0; i < caseNum; i++){
            int rungNum = in.nextInt();
            int rungList[] = new int[rungNum+1];
            rungList[0] = 0;
            for(int j = 1; j <= rungNum; j++){
                rungList[j] = in.nextInt();
            }

            // Calculate output
            int start = 1, end = 10000000, mid = 0, result = 0;

            while(end >= start){
                mid = (end + start) / 2;
                if(validate(mid, rungList)){
                    result = mid;
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }

            System.out.printf("Case %d: %d\n", i+1, result);
        }
    }

    static boolean validate(int height, int rungList[]){
        int holder = height;
        int len = rungList.length;
        for(int i = 1; i < len; i++){
            int diff = rungList[i] - rungList[i-1];
            if(diff == holder) holder--;
            else if(diff > holder) return false;
        }
        return true;
    }
}