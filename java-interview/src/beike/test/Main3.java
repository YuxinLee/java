package beike.test;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            int n = in.nextInt();
            int m = in.nextInt();
            int count=0;
           while (true){
               if(m%2==0){
                   m = m/2;
                   count++;
                   if(n==m){
                       break;
                   }
               }
               else{
                   m = m+1;
                   count++;
                   if(n==m){
                       break;
                   }
               }



               }

           System.out.println(count);


        }
    }
}

/*
* #include <iostream>
using namespace std;
int process(int N, int M);
int main() {
	int N, M;
	while (cin >> N >> M) {
		cout << process(N, M) << endl;
	}

	return 0;
}

int process(int N, int M) {
	int res = 0;
	if (N >= M) {
		return res + N - M;
	}
	return 1 + process(N, M / 2);
}
* */
