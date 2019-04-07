package base.datatypedemo;

import java.math.BigInteger;

public class DataTypeTest3 {
    public static void main(String[] args) {
//        Integer i = new Integer ("2147483648");
        BigInteger bi = new BigInteger("2147483648");
        System.out.println(bi);
        System.out.println(0.09 + 0.01);	//0.09999999999999999
        System.out.println(1.0 - 0.32);		//0.6799999999999999
        System.out.println(1.015 * 100);	//101.49999999999999
        System.out.println(1.301 / 100);	//0.013009999999999999

    }
}
