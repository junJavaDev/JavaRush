package com.javarush.task.jdk13.task28.task2818;

import java.util.concurrent.*;

/* 
С ScheduledExecutor по галактике
*/

public class Solution {

    public static ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(3);

    public static void main(String[] args) throws Exception {
        Future<Integer> theUltimateAnswer =
                scheduledPool.schedule(
                        new TheUltimateQuestion(),
                        7500000L * 365L,
                        TimeUnit.DAYS);
        System.out.println(theUltimateAnswer.get());
        scheduledPool.shutdown();
    }
}
