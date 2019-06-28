import java.util.Scanner;

class p10341{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        while(in.hasNext()){
            int p = in.nextInt();
            int q = in.nextInt();
            int r = in.nextInt();
            int s = in.nextInt();
            int t = in.nextInt();
            int u = in.nextInt();

            double start = 0, end = 1, mid = 0, result = 0;
            for(int i = 0; i < 50; i++){
                mid = (start + end) / 2;
                result = p*Math.pow(Math.E, -mid) + q*Math.sin(mid) + r*Math.cos(mid) + s*Math.tan(mid) + t*mid*mid + u;
                if(result < 0){
                    end = mid;
                } else {
                    start = mid;
                }
            }

            if(p*Math.pow(Math.E, -mid)+q*Math.sin(1)+r*Math.cos(1)+s*Math.tan(1)+t+u>1e-9 || p+r+u<0){
                System.out.println("No solution");
            } else {
                System.out.printf("%.4f\n", mid);
            }
        }

        in.close();
    }
}