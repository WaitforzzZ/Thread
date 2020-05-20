package com.waitfor.thread.waitandnotify;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        TaskQueue taskQueue = new TaskQueue();
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread() {
                public void run() {
                    //执行task
                    while (true) {
                        try {
                            String task = taskQueue.getTask();
                            System.out.println(this.getName() + "execute task:" + task);
                        } catch (InterruptedException e) {
                            return;
                        }
                    }
                }
            };
            thread.start();
            threads.add(thread);
        }
        Thread add = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                //放入task
                String taskName = "task-" + Math.random();
                System.out.println(Thread.currentThread().getName() + "add task:" + taskName);
                taskQueue.addTask(taskName);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        add.start();
        add.join();
        Thread.sleep(100);
        for (Thread t : threads) {
            t.interrupt();
        }
    }
}
