
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreading {
    public static final ExecutorService POOL = Executors.newFixedThreadPool(100, new ThreadFactory() {
        final AtomicInteger counter = new AtomicInteger(0);

        public Thread newThread(Runnable r) {
            return new Thread(r, String.format("Thread %s", this.counter.incrementAndGet()));
        }
    });

    public static final ScheduledExecutorService RUNNABLE_POOL = Executors.newScheduledThreadPool(10, new ThreadFactory() {
        private final AtomicInteger counter = new AtomicInteger(0);

        public Thread newThread(Runnable r) {
            return new Thread(r, "Thread " + this.counter.incrementAndGet());
        }
    });

    public static ScheduledFuture<?> schedule(Runnable r, long initialDelay, long delay, TimeUnit unit) {
        return RUNNABLE_POOL.scheduleAtFixedRate(r, initialDelay, delay, unit);
    }

    public static ScheduledFuture<?> schedule(Runnable r, long delay, TimeUnit unit) {
        return RUNNABLE_POOL.schedule(r, delay, unit);
    }

    public static void runAsync(Runnable runnable) {
        POOL.execute(runnable);
    }

    public static int getTotal() {
        ThreadPoolExecutor tpe = (ThreadPoolExecutor)POOL;
        return tpe.getActiveCount();
    }

    public static void stopTask(){
        POOL.shutdown();
        RUNNABLE_POOL.shutdown();
    }
}
