package base.thread.threaddemo;
/*
 * public final void setDaemon(boolean on):将该线程标记为守护线程或用户线程。
 * 当正在运行的线程都是守护线程时，Java 虚拟机退出。 该方法必须在启动线程前调用。
 * 当线程3退出时，线程1和线程2将自动退出
 *
 */
public class ThreadDaemonDemo {
    public static void main(String[] args) {
        ThreadDaemon td1 = new ThreadDaemon();
        ThreadDaemon td2 = new ThreadDaemon();
        td1.setName("线程1");
        td2.setName("线程2");

        //设置守护线程
        td1.setDaemon(true);
        td2.setDaemon(true);

        td1.start();
        td2.start();

        Thread.currentThread().setName("线程3");
        for (int x = 0; x < 5; x++) {
            System.out.println(Thread.currentThread().getName() + ":" + x);
        }
    }
}

class ThreadDaemon extends Thread{
    public void run(){
        for (int x = 0; x < 100; x++) {
            System.out.println(getName() + ":" + x);
        }
    }
}
