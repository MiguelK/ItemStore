package com.flow.commons;

import java.util.concurrent.*;
import java.util.logging.Logger;

public class AsyncService {

    private static final Logger log = Logger.getLogger(AsyncService.class.getName());

    private static final int SCHEDULED_THREAD_POOL_SIZE = 10;

    public interface FixedDelayHandler {
        void handle();
    }

    private static final AsyncService INSTANCE = new AsyncService();
    private ExecutorService executorService;
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    public static AsyncService instance() {
        return INSTANCE;
    }

    public void init() {
        int corePoolSize = 10;
        int maximumPoolSize = 200;
        long keepAliveTime = 0L;
        executorService = new MyThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
                Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        scheduledThreadPoolExecutor = new MyScheduledThreadPoolExecutor();
        scheduledThreadPoolExecutor.setCorePoolSize(10);

        log.info("Staring AsyncService...");
    }

    public void submit(Runnable runnable) {
        executorService.submit(runnable);
    }

    public ScheduledFuture<?> scheduleAtFixedRateInSeconds(Runnable command, long period) {
        return scheduledThreadPoolExecutor.scheduleAtFixedRate(command, 0, period, TimeUnit.SECONDS);
    }

    public void scheduleWithFixedDelayInSeconds(int delay, final FixedDelayHandler fixedDelayHandler) {
        scheduledThreadPoolExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                fixedDelayHandler.handle();
            }
        }, 0l, delay, TimeUnit.SECONDS);
    }

    private static class MyScheduledThreadPoolExecutor extends ScheduledThreadPoolExecutor {
        private MyScheduledThreadPoolExecutor() {
            super(SCHEDULED_THREAD_POOL_SIZE);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r); //FIXME add logiing ?
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
        }
    }

    private static class MyThreadPoolExecutor extends ThreadPoolExecutor {
        MyThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        protected void beforeExecute(Thread t, Runnable r) {
            super.beforeExecute(t, r);
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r, t);
        }
    }

    //FIXME call this
    public void shutdown() {
        if (scheduledThreadPoolExecutor != null) {
            try {
                scheduledThreadPoolExecutor.shutdown();
                scheduledThreadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println("scheduledThreadPoolExecutor " + e.getMessage());
            }
        }

        if (executorService != null) {
            try {
                executorService.shutdown();
                executorService.awaitTermination(10, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println("executorService " + e.getMessage());
            }
        }

    }
}
