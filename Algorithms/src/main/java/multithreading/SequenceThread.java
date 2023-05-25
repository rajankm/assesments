package multithreading;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

public class SequenceThread {

    public void printNumber(int num) {
        System.out.println(Thread.currentThread().getName()+"-" + num);
    }
    public static void main(String[] args) {
        SequenceThread obj = new SequenceThread();
        AtomicInteger i = new AtomicInteger(0);
        int ii=0;
        ExecutorService executorService = Executors.newCachedThreadPool();
       // Executor executor = Executors.newScheduledThreadPool();
        executorService.execute(()->{
            obj.printNumber(ii);
        });

    }
}
