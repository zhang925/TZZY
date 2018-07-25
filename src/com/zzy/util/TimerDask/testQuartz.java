package com.zzy.util.TimerDask;

import java.util.Calendar;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
 
public class testQuartz {
 
    public static void main(String[] args) throws InterruptedException {
 
        SchedulerManager manager = new SchedulerManager();
        manager.addJob(new EveryMinuteDelay(new MyJob()));
        manager.start();
 
        printCurrentTime();
        sleepMinutes(3);           // 休息 3 分钟后关掉
        manager.stop();     // 停止工作
        printCurrentTime();
    }
 
    private static void sleepMinutes(long minutes) {
        try {
            TimeUnit.MINUTES.sleep(minutes);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
    private static void printCurrentTime() {
        System.out.printf("Current: %tF %<tT%n", System.currentTimeMillis());
    }
}
 
/**
 * <p>任务调度管理器</p>
 *
 * 2010-5-28 下午11:30:20
 */
class SchedulerManager {
 
    private ExecutorService executor;
    private DelayQueue<JobDelayed> jobs;
    private Scheduler scheduler;
 
    public SchedulerManager() {
        this(1);
    }
 
    /**
     * 根据线程池中线程数量构造调度管理器
     * @param threadPool
     */
    public SchedulerManager(int threadPool) {
        this.jobs = new DelayQueue<JobDelayed>();
        // 由于调度管理器需要占用一个线程，因此需要加 1
        this.executor = Executors.newFixedThreadPool(threadPool + 1);
        this.scheduler = new Scheduler(this);
    }
 
    /**
     * <p>添加需要计划的任务</p>
     *
     * @param job
     */
    public void addJob(JobDelayed jobDelayed) {
        jobDelayed.nextTime();  // 计算下一次执行的时间
        jobs.put(jobDelayed);   // 添加到延迟队列中去
    }
 
    public void start() {
        scheduler.start();  // 启动任务调度器
    }
 
    public void stop() {
        scheduler.stop();  // 停止任务调度器
    }
 
    private void execute() throws InterruptedException {
        submit(take());
    }
 
    /**
     * <p>将任务提交给线程池去执行</p>
     *
     * @param task
     */
    private void submit(Runnable task) {
        executor.submit(task);
    }
 
    /**
     * <p>强制停止工作</p>
     */
    private void shutdown() {
        executor.shutdown();
    }
 
    /**
     * <p>获取到时间的任务，如果该任务下一次还需要执行，将该任务加回队列中去。</p>
     *
     * @return
     * @throws InterruptedException
     */
    private JobDelayed take() throws InterruptedException {
        JobDelayed jobDelayed = jobs.take();
        if(jobDelayed.hasNext()) {
            addJob(jobDelayed);
        }
        return jobDelayed;
    }
 
    /**
     * <p>调度器</p>
     * 2010-5-28 下午11:40:58
     */
    private static class Scheduler implements Runnable {
 
        private SchedulerManager manager;
        private boolean running = false;
 
        /**
         * 使用调度管理器构造
         * @param manager
         */
        public Scheduler(SchedulerManager manager) {
            this.manager = manager;
        }
 
        /**
         * <p>启动该调度器</p>
         */
        public void start() {
            if(!running) {
                manager.submit(this);
                this.running = true;
            }
        }
 
        /**
         * 执行任务调度工作
         */
        public void run() {
            while(running) {
                try {
                    manager.execute();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
 
        /**
         * <p>强制停止该调度器</p>
         */
        public void stop() {
            manager.shutdown();
            running = false;
        }
    }
}
 
/**
 * <p>需要进行任务调度的工作内容</p>
 */
interface Job {
    public void execute();
}
 
/**
 * <p>测试工作，输出当前时间</p>
 */
class MyJob implements Job {
    public void execute() {
        System.out.printf("JOB OUTPUT: %tF %<tT%n", System.currentTimeMillis());
    }
}
 
/**
 * <p>需要进行调度工作的任务</p>
 */
interface JobDelayed extends Delayed, Runnable {
 
    /**
     * <p>计算下一次执行的时间</p>
     */
    public void nextTime();
 
    /**
     * <p>下一次是否需要执行</p>
     */
    public boolean hasNext();
}
 
/**
 * <p>需要进行每分钟调度工作的任务</p>
 */
class EveryMinuteDelay implements JobDelayed {
 
    private long nextTime;
    private Job job;
 
    public EveryMinuteDelay(Job job) {
        this.job = job;
    }
 
    public void nextTime() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        c.add(Calendar.MINUTE, 1);
        this.nextTime = c.getTimeInMillis();  // 这个任务的 nextTime 为下一分钟
    }
 
    public boolean hasNext() {
        return true;   // 永远执行下去
    }
 
    public long getDelay(TimeUnit unit) {
        return nextTime - System.currentTimeMillis();   // 是否超时
    }
 
    public int compareTo(Delayed o) {
        return (int)(o.getDelay(TimeUnit.MILLISECONDS) - getDelay(TimeUnit.MILLISECONDS));
    }
 
    public void run() {
        job.execute();   // 执行任务
    }
 
    public String toString() {
        return String.format("next: %tF %<tT", nextTime);
    }
}
