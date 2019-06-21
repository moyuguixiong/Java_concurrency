package synchronizationtoolclass.barrier;

import java.util.concurrent.CyclicBarrier;

public class Main {

    public static void main(String[] args) {
//        实现原理：在CyclicBarrier的内部定义了一个Lock对象，每当一个线程调用await方法时，将拦截的线程数减1，然后判断剩余拦截数是否为初始值parties，如果不是，进入Lock对象的条件队列等待。如果是，执行barrierAction对象的Runnable方法，然后将锁的条件队列中的所有线程放入锁等待队列中，这些线程会依次的获取锁、释放锁。
        int playCount = 3;
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("所有玩家进入第2关");
            }
        });
        for (int i = 0; i < playCount; i++) {
            Player player = new Player(barrier, (i + 1));
            player.play(1);
        }

    }
}
