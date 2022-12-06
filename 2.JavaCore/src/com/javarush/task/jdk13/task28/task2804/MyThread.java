package com.javarush.task.jdk13.task28.task2804;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread{

    private static final AtomicInteger priority = new AtomicInteger(1);

    public MyThread() {
        setCorrectPriority();
    }

    public MyThread(Runnable target) {
        super(target);
        setCorrectPriority();
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        setCorrectPriority();
    }

    public MyThread(String name) {
        super(name);
        setCorrectPriority();
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        setCorrectPriority();
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        setCorrectPriority();

    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        setCorrectPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        setCorrectPriority();
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize, boolean inheritThreadLocals) {
        super(group, target, name, stackSize, inheritThreadLocals);
        setCorrectPriority();
    }

    private void setCorrectPriority() {
        if (priority.get() > 10) {
            priority.set(1);
        }
        int newPriority = priority.getAndIncrement();
        int groupPriority = getThreadGroup().getMaxPriority();
        if (newPriority > groupPriority) {
            newPriority = groupPriority;
        }
        setPriority(newPriority);
    }
}
