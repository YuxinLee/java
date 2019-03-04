package qunaer.test;

import java.util.Scanner;

public class Main1 {
    public static int index = 0;

    class Node {
        Node left;
        Node right;
        int data;

        public Node(int data) {
            left = null;
            right = null;
            this.data = data;
        }
    }

        public void afterOrder(Node node){
            if(node != null){

                afterOrder(node.left);
                afterOrder(node.right);
                System.out.print(node.data+" ");
            }
        }

        public Node process(int[] pre, int[] mid, int midStart, int minEnd){
            int mstart = midStart;
            int mend = minEnd;
            int flag = 0;
            if(index >= pre.length){
                return null;
            }

            Node node = new Node(pre[index]);
            for(int i=mstart;i<=mend;i++){
                if(mid[i]==pre[index]){
                    flag = i;
                }
            }
            index++;
            if(midStart<flag){
                node.left = process(pre,mid,midStart,flag-1);
            }

            if(flag<minEnd){
                node.right = process(pre,mid,flag+1,minEnd);
            }
            return node;
        }





    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str1 = in.next();
        String str2 = in.next();
//        System.out.println(str1);
//        System.out.println(str2);
        String[] str1s = str1.split(",");
        String[] str2s = str2.split(",");
        int[] pre = new int[str1s.length];
        int[] mid = new int[str2s.length];
        for(int i=0;i<str1s.length;i++){
            pre[i] = Integer.parseInt(str1s[i]);
        }
        for(int i=0;i<str2s.length;i++){
            mid[i] = Integer.parseInt(str2s[i]);
        }

//        for(int i=0;i<str1s.length;i++){
//            System.out.print(arr1[i]+" ");
//        }
//        int[] pre = {1,2,4,3};
//        int[] mid = {2,4,1,3};
        Main1 main = new Main1();
        Node root = main.process(pre,mid,0,mid.length-1);
        main.afterOrder(root);
    }

}
