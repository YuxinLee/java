package base.date_learning;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Date_Demo2 {
    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd hh:mm:ss");
        String currentTime = sdf.format(date);
        System.out.println(currentTime);
    }
}
