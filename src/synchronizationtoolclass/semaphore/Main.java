package synchronizationtoolclass.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();
        Semaphore semaphore = new Semaphore(6);
        for (int i = 0; i < 10; i++) {
            final int num = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        System.out.println("第" + (num + 1) + "队员获取了泳道，还剩" + semaphore.availablePermits() + "个泳道!");
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        semaphore.release();
                        System.out.println("第" + (num + 1) + "队员让出了泳道，还剩" + semaphore.availablePermits() + "个泳道!");
                    }
                }
            });
        }

        executorService.shutdown();

        //shutdown()，关闭线程池，无法再提交新任务；关闭前，会等待已经提交的任务执行完成(包括已经提交，但是还没有开始执行的任务)，才真正关闭线程池
        //shutdownNow()，关闭线程池，无法再提交新任务；会阻止已提交还未执行的任务的开始执行,并返回list，并且会尝试取消当前正在执行的任务。
    }
}
