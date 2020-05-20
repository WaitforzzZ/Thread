package com.waitfor.thread.waitandnotify;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 模拟任务管理器，多个线程同时往队列中添加任务
 */
public class TaskQueue {
    Queue<String> queue = new LinkedList<>();

    public synchronized void addTask(String taskName){
        this.queue.add(taskName);
        this.notifyAll();//唤醒this锁等待的线程
    }

    //取出队列的第一个任务
    public synchronized String getTask() throws InterruptedException {
        while(queue.isEmpty()){
            //释放this锁
            this.wait();
            //重新获取this锁
        }
        return queue.remove();
    }
}
