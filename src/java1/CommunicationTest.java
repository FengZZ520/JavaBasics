package java1;

/**
 *
 * 线程通信的例子：使用两个线程打印1-100，线程1，线程2，交替打印
 * 涉及三个方法
 * wait()：一旦执行此方法，当前线就进入阻塞状态，并释放同步监视器
 * notify()：一旦执行此方法，就会唤醒被wait的一个线程，如果有多个线程被wait，就唤醒优先级较高的
 * notifyAAll()：一旦执行此方法，就会唤醒被wait的所有线程
 *
 * 说明：
 * 1.wait()，notify()，notifyAAll()，三个方法必须使用在同步代码块或同步方法中
 * 2.wait()，notify()，notifyAAll()，三个方法的调用者必须是同步代码块或同步方法中的同步监视器，
 *      否则，会出现IllegalMonitorStateException异常
 * 3.wait()，notify()，notifyAAll()，三个方法是定义在java.lang.Object类中的。
 *
 * 面试题：sleep()和wait()的异同？
 * 1.相同点：一旦执行方法，都可以使得当前线程进入阻塞状态
 * 2.不同点：1）两个方法声明的位置不同：Thread()类中声明sleep()，Object()类中声明wait()
 *          2）调用的要求不同：sleep()可以在任何需要的场景下调用，wait()必须使用在同步代码块或同步方法中
 *          3）关于是否释放同步监视器的问题：如果两个方法都声明在同步代码块或同步方法中，sleep()不会释放锁，wait()会是释放锁
 *
 *
 * @author 冯振卓
 * @ 2021/11/27 20:30
 */
class Number implements Runnable{
    private int number = 1;
    @Override
    public void run() {
        while(true){
            synchronized (this) {

                notify();

                if (number <= 100) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println(Thread.currentThread().getName() + ":" + number);
                    number++;

                    try {
                        //使得调用wait()方法的线程进入阻塞状态
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                } else {
                    break;
                }
            }
        }
    }
}

public class CommunicationTest {
    public static void main(String[] args) {
        Number number = new Number();
        Thread t1 = new Thread(number);
        Thread t2 = new Thread(number);

        t1.setName("线程A");
        t2.setName("线程B");

        t1.start();
        t2.start();

    }
}
