package synchronizationtoolclass.latch;

import java.util.concurrent.CountDownLatch;

public class Main {

    public static void main(String[] args) {
        CountDownLatch startLatch = new CountDownLatch(1);
        int employeeCount = 3;
        CountDownLatch endLatch = new CountDownLatch(employeeCount);
        for (int i = 0; i < employeeCount; i++) {
            Employee employee = new Employee(startLatch, endLatch,(i+1));
            employee.waitOrderToWork();
        }
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        Boss boss = new Boss(startLatch, endLatch);
        boss.giveOrderToWork();
        boss.waitWorkFinish();
        boss.checkTheWork();
    }
}
