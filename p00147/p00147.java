import java.util.Scanner;

class p00147{
    public static void main(String args[]){
        Scanner in = new Scanner(System.in);

        int centList[] = {5, 10, 20, 50, 100, 200, 500, 1000, 2000, 5000, 10000};
        int coinNum = centList.length;

        int SIZE = 30005;
        long possible[] = new long[SIZE];
        possible[0] = 1;
    
        for(int c = 0; c < coinNum; ++c){
            int end = SIZE - centList[c];
            for (int i = 0; i < end; ++i){
                possible[i + centList[c]] += possible[i];      
            }
        }

        String line;
        while(!(line = in.next()).equals("0.00")){
            double num = Double.parseDouble(line);
            int value = (int) (num * 100 + 0.5);
            String resultString = possible[value] + "";

            for(int i = 0; i < 6 - line.length(); i++){
                System.out.print(" ");
            }
            System.out.print(line);
            for(int i = 0; i < 17 - resultString.length(); i++){
                System.out.print(" ");
            }
            System.out.println(resultString);
        }

        in.close();
    }
}