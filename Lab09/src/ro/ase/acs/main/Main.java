package ro.ase.acs.main;

import ro.ase.acs.classes.SummingCallable;
import ro.ase.acs.classes.SummingThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        final int NB_OF_ELEMENTS = 200_000_000;

        int[] array = new int[NB_OF_ELEMENTS];

        for (int i = 0; i < NB_OF_ELEMENTS; i++) {
            array[i] = i + 1;
        }

        long startTime = System.currentTimeMillis();
        long sum = 0;

        for (int i = 0; i < NB_OF_ELEMENTS; i++) {
            sum += array[i];
        }

        long endTime = System.currentTimeMillis();

        System.out.println("Single thread sum = " + sum + " computed in " +
                (endTime - startTime));

        final int NB_OF_THREADS = 4;
        sum = 0;
        startTime = System.currentTimeMillis();
        SummingThread[] arrayOfThreads =
                new SummingThread[NB_OF_THREADS];

        for (int i = 0; i < NB_OF_THREADS; i++) {
            arrayOfThreads[i] = new SummingThread(
                    i * (NB_OF_ELEMENTS / NB_OF_THREADS),
                    (i + 1) * (NB_OF_ELEMENTS / NB_OF_THREADS),
                    array);
            arrayOfThreads[i].start();
        }

        for (int i = 0; i < NB_OF_THREADS; i++) {
            try {
                arrayOfThreads[i].join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sum += arrayOfThreads[i].getSum();
        }

        endTime = System.currentTimeMillis();

        System.out.println("Thread array sum = " + sum + " computed in " +
                (endTime - startTime));

        sum = 0;
        startTime = System.currentTimeMillis();

        ExecutorService threadPool =
                Executors.newFixedThreadPool(NB_OF_THREADS);

        for (int i = 0; i < NB_OF_THREADS; i++) {
            arrayOfThreads[i] = new SummingThread(
                    i * (NB_OF_ELEMENTS / NB_OF_THREADS),
                    (i + 1) * (NB_OF_ELEMENTS / NB_OF_THREADS),
                    array);
            threadPool.submit(arrayOfThreads[i]);
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < NB_OF_THREADS; i++) {
            sum += arrayOfThreads[i].getSum();
        }

        endTime = System.currentTimeMillis();

        System.out.println("Threadpool sum = " + sum + " computed in " +
                (endTime - startTime));

        sum = 0;
        startTime = System.currentTimeMillis();

        threadPool = Executors.newFixedThreadPool(NB_OF_THREADS);
        List<Future<Long>> list = new ArrayList<>();
        for (int i = 0; i < NB_OF_THREADS; i++) {
            SummingCallable sc = new SummingCallable(i * (NB_OF_ELEMENTS / NB_OF_THREADS),
                    (i + 1) * (NB_OF_ELEMENTS / NB_OF_THREADS),
                    array);
            Future<Long> f = threadPool.submit(sc);
            list.add(f);
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (Future<Long> f : list) {
            try {
                sum += f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        endTime = System.currentTimeMillis();

        System.out.println("Callable sum = " + sum + " computed in " +
                (endTime - startTime));
    }
}