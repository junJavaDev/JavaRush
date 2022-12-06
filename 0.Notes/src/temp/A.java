package temp;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class A {
    public static void main(String[] args) throws Exception {
        // создаем ExecutorService с фиксированным числом нитей – три
        ExecutorService service = Executors.newFixedThreadPool(3);
        // передаем в ExecutorService простое задание типа Runnable
        service.submit(() -> System.out.println("done"));
        service.shutdown();
    }
}
