package synchronizationtoolclass.latch;

import java.util.concurrent.CountDownLatch;

public class Employee {
    private CountDownLatch endLatch;
    private CountDownLatch startLatch;
    private int grade;

    public Employee(CountDownLatch startLatch, CountDownLatch endLatch, int grade) {
        this.startLatch = startLatch;
        this.endLatch = endLatch;
        this.grade = grade;
    }

    /**
     * 等待工作指令后开始工作
     */
    public void waitOrderToWork() {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    long id = Thread.currentThread().getId();
                    System.out.println("员工编号" + id + ",准备就绪");
                    startLatch.await();
                    System.out.println("员工编号" + id + ",工作中。。。。。");
                    long sleep = 6000;
                    if (grade == 2) {
                        sleep = 4000;
                    } else if (grade == 3) {
                        sleep = 2000;
                    }
                    Thread.sleep(sleep);
                    System.out.println("员工编号" + id + ",完成工作");
                    endLatch.countDown();
                } catch (InterruptedException e) {
                }
            }
        };
        Thread thread = new Thread(task);
        thread.start();
    }
}
