package base.date_learning;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Date_Demo1 {
    public static void main(String[] args) {
        String s = "2019-07-28";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = sdf.parse(s);
            System.out.println(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
