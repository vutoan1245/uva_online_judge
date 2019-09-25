import java.util.*;

class p10910 {
    static int subjectNum, totalMark, min;
    static int dp[][];

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int caseNum = in.nextInt();
        while (caseNum-- > 0) {
            subjectNum = in.nextInt();
            totalMark = in.nextInt();
            min = in.nextInt();

            dp = new int[subjectNum + 1][totalMark + 1];
            for (int i = 0; i <= subjectNum; i++) {
                Arrays.fill(dp[i], -1);
            }

            System.out.println(solve(1, totalMark));
        }

        in.close();
    }

    static int solve(int subject, int marks) {
        if (subject == subjectNum && marks >= min) {
            return 1;
        }

        if (marks < min) {
            return 0;
        }

        if (dp[subject][marks] != -1) {
            return dp[subject][marks];
        }

        int ans = 0;
        ans += solve(subject, marks - 1);
        ans += solve(subject + 1, marks - min);

        return dp[subject][marks] = ans;
    }
}