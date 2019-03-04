package base.thread.threaddemo;

/*
 * 线程休眠
 *		public static void sleep(long millis)
 */

import java.util.Date;

public class ThreadSleepDemo {
    public static void main(String[] args) {
        ThreadSleep ts1 = new ThreadSleep();
        ThreadSleep ts2 = new ThreadSleep();
        ThreadSleep ts3 = new ThreadSleep();

        ts1.setName("线程1");
        ts1.setName("线程2");
        ts1.setName("线程3");

        ts1.start();
        ts2.start();
        ts3.start();
    }
}

class ThreadSleep extends Thread{
    public void run(){
        for (int x = 0; x < 100; x++){
            System.out.println(getName() + ":" + x + ",日期：" + new Date());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}


