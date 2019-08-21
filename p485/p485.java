import java.math.BigInteger;

class p485 {
    public static void main(String args[]) {
        String limit = "1";
        for (int i = 0; i < 60; i++) {
            limit = limit + "0";
        }

        BigInteger MAX = new BigInteger(limit);

        System.out.println("1");

        BigInteger[] prev;
        BigInteger[] curr = new BigInteger[1];

        curr[0] = BigInteger.ONE;

        boolean condition = true;
        while (condition) {

            prev = curr;
            int size = prev.length;

            // First Item
            curr = new BigInteger[size + 1];
            curr[0] = BigInteger.ONE;
            System.out.print("1");

            // Middle
            for (int i = 1; i < size; i++) {
                curr[i] = prev[i - 1].add(prev[i]);
                if (curr[i].compareTo(MAX) >= 0) {
                    condition = false;
                }
                System.out.print(" " + curr[i]);

            }

            // Last Items
            curr[size] = BigInteger.ONE;
            System.out.println(" 1");
        }

    }
}