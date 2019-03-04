public class test {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int a=2;
        int b=3;
//		int temp=a;
//		a=b;
//		b=temp;
        swap(a,b);

    }
    public static void swap(int a,int b){
        int temp;
        temp = a;
        a = b;
        b = temp;
        System.out.print("a的值为：" + a +"b的值为："+b);

    }

}
