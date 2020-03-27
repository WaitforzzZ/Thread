package com.waitfor.thread;

public class CounterTest {
    private int count = 0;

    public void add(int n) {
        synchronized (this) {
            count += n;
        }
    }

    public synchronized void des(int n) {
        count -= n;
        System.out.println(count);

    }

    public int get() {
        return count;
    }

    public static void main(String[] args) {
        CounterTest c1 = new CounterTest();
        CounterTest c2 = new CounterTest();
        new Thread(() -> {
            c1.add(100);
        }).start();
        new Thread(() -> {
            c1.des(100);
        }).start();
       /* new Thread(() -> { c2.add(100); }).start();
        new Thread(() -> { c2.des(100); }).start();*/
        System.out.println("t1.count = " + c1.get());
        /*System.out.println("t2.count = " + c2.get());*/
    }
}
