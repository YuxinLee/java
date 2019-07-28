package base.thread_learning.threaddemo;

/*
 * public final void join():等待该线程终止。
 * 当线程1运行完，才会运行线程2和线程3
 */

public class ThreadJoinDemo {
    public static void main(String[] args) throws InterruptedException {
        ThreadJoin tj1 = new ThreadJoin();
        ThreadJoin tj2 = new ThreadJoin();
        ThreadJoin tj3 = new ThreadJoin();

        tj1.setName("线程1");
        tj2.setName("线程2");
        tj3.setName("线程3");

        tj1.start();
        tj1.join();
        tj2.start();
        tj3.start();
    }
}

class ThreadJoin extends Thread{
    public void run(){
        for (int x = 0; x < 100; x++) {
            System.out.println(getName() + ":" + x);
        }
    }
}
