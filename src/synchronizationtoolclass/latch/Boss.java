package synchronizationtoolclass.latch;

import java.util.concurrent.CountDownLatch;

public class Boss {

    private CountDownLatch startLatch;
    private CountDownLatch endLatch;

    public Boss(CountDownLatch startLatch, CountDownLatch endLatch) {
        this.startLatch = startLatch;
        this.endLatch = endLatch;
    }

    /**
     * 下达开始工作的指令
     */
    public void giveOrderToWork() {
        System.out.println("I'm the boss,you all begin to work!");
        startLatch.countDown();
    }

    public void waitWorkFinish() {
        try {
            endLatch.await();
            System.out.println("boss accept work is finish!");
        } catch (InterruptedException e) {
        }
    }

    public void checkTheWork() {
        System.out.println("the work is good!");
    }
}
