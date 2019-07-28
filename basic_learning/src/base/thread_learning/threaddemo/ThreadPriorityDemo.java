package base.thread_learning.threaddemo;

/* 如何获取线程对象的优先级?
        * 		public final int getPriority():返回线程对象的优先级
        * 如何设置线程对象的优先级呢?
        * 		public final void setPriority(int newPriority)：更改线程的优先级。
        *
        * 注意：
        * 		线程默认优先级是5。
        * 		线程优先级的范围是：1-10。
        * 		线程优先级高仅仅表示线程获取的 CPU时间片的几率高，但是要在次数比较多，或者多次运行的时候才能看到比较好的效果。
        *
*/
public class ThreadPriorityDemo {
    public static void main(String[] args) {
        ThreadPriority tp1 = new ThreadPriority();
        ThreadPriority tp2 = new ThreadPriority();
        ThreadPriority tp3 = new ThreadPriority();

        tp1.setName("线程1");
        tp1.setName("线程2");
        tp1.setName("线程3");

        // 获取默认优先级
        // System.out.println(tp1.getPriority());
        // System.out.println(tp2.getPriority());
        // System.out.println(tp3.getPriority());

        //设置正确的线程优先级
        tp1.setPriority(10);
        tp2.setPriority(1);

        tp1.start();
        tp2.start();
        tp3.start();
    }
}

class ThreadPriority extends Thread{
    public void run(){
        for (int x = 0; x < 100; x++) {
            System.out.println(getName() + ":" + x);
        }
    }
}
