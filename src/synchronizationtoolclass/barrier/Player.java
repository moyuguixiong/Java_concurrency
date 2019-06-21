package synchronizationtoolclass.barrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Player {
    private CyclicBarrier barrier;
    private int num;

    public Player(CyclicBarrier barrier, int num) {
        this.barrier = barrier;
        this.num = num;
    }

    public void play(int level) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                //完成本关后，阻塞等待其他线程
                System.out.println("玩家" + num + "正在玩第" + level + "关...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                System.out.println("玩家" + num + "进入第" + (level + 1) + "关...");
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                } catch (BrokenBarrierException e) {
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
}
