package beike.test;

import java.util.Scanner;

public class Main6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int n = in.nextInt();
            int m = in.nextInt();
            System.out.println(process(n, m));
        }
    }

        public static int process(int N, int M) {
            int res = 0;
            if (N >= M) {
                return res + N - M;
            }
            return 1 + process(N, M / 2);
        }
    }


