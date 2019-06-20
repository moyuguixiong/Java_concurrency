package synchronizationtoolclass.latch;

import java.util.List;

public class Employee {
    private List<Work> works;

    public Employee(List<Work> works) {
        this.works = works;
    }

    public void work() {
        System.out.println("finish work");
    }
}
