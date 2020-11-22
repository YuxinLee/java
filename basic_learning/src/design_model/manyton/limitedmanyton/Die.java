package design_model.manyton.limitedmanyton;

import java.util.Date;
import java.util.Random;

/**
 * 多例类：骰子(有限多例)
 */
public class Die {

    private static Die die1 = new Die();
    private static Die die2 = new Die();

    /**
     * 私有构造方法
     */
    private Die() {}

    public static Die getInstance(int whichOne) {
        if (whichOne == 1) {
            return die1;
        } else {
            return die2;
        }
    }

    /**
     * 掷骰子 1-6
     */
    public synchronized int dice() {
        Date date = new Date();
        Random random = new Random(date.getTime());
        int value = random.nextInt();
        value = Math.abs(value);
        value = value % 6;
        value +=1;
        return value;
    }
}
