package com.waitfor.thread;

/**
 * volatile 修饰的变量，获取值和回写值时都不会阻塞线程，它只是保证了其他线程能更快的看到修改后的值。
 * volatile只保证时效性，不保证原子性：
 * 1.读主内存到本地副本；
 * 2.操作本地副本；
 * 3.回写主内存。
 * 这3步多个线程可以同时进行。
 */
public class ThreadSynchronized {
    public static void main(String[] args) throws InterruptedException {
        Thread add = new AddThread();
        Thread dec = new DecThread();
        add.start();
        dec.start();
        add.join();
        dec.join();
        System.out.println(Counter.count);
    }
}

class Counter {
    public static final Object lock = new Object();
    public static int count = 0;
}

class AddThread extends Thread {
    public void run() {
        for (int i=0; i<10000; i++) {
            synchronized(Counter.lock) {
                Counter.count += 1;
            }
        }
    }
}

class DecThread  extends Thread {
    public void run() {
        for (int i=0; i<10000; i++) {
            synchronized(Counter.lock) {
                Counter.count -= 1;
            }
        }
    }
}