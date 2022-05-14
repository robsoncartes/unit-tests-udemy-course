package br.com.releasesolutions.runners;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerScheduler;
import org.junit.runners.model.TestClass;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelRunner extends BlockJUnit4ClassRunner {


    public ParallelRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
    }

    protected ParallelRunner(TestClass testClass) throws InitializationError {
        super(testClass);
    }

    private static class ThreadPoll implements RunnerScheduler {

        private final ExecutorService executorService;

        public ThreadPoll() {
            this.executorService = Executors.newFixedThreadPool(50);
        }

        @Override
        public void schedule(Runnable runnable) {

            executorService.submit(runnable);
        }

        @Override
        public void finished() {

            executorService.shutdown();

            try {
                executorService.awaitTermination(10, TimeUnit.MINUTES);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
