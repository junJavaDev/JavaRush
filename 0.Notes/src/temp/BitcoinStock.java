package temp;
import java.util.ArrayList;
import java.util.List;


    public class BitcoinStock {

        public static final Object monitor = new Object();
        public static Cat cat = new Cat();

        public static Integer fee = 0;
        public static void main(String[] args) {
            List<Task> tasks = new ArrayList<>();
            for (int i = 0; i < 1000; i++) {
                Task task = new Task();
                tasks.add(task);
                task.start();
            }
            System.out.println("all started");
            for (Task task : tasks) {
                try {
                    task.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException();
                }
            }
            System.out.println(fee);
        }

    }

    class Task extends Thread {

        @Override
        public void run() {
            synchronized (BitcoinStock.cat){
                //Integer pool - bad monitor
                BitcoinStock.cat.setName("df");
                BitcoinStock.fee += getOneFee();
            }
        }

        private int getOneFee() {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            return 1;
        }

    }

