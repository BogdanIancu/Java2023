package ro.ase.acs.main;

import ro.ase.acs.classes.MyFirstThread;
import ro.ase.acs.classes.SyncedThread;
import ro.ase.acs.classes.UnsyncedThread;

public class Main {
    public static void main(String[] args) {
        MyFirstThread t = new MyFirstThread();
        t.start();
        System.out.println("Hello world!");

//        UnsyncedThread t1 = new UnsyncedThread();
//        t1.start();
//
//        UnsyncedThread t2 = new UnsyncedThread();
//        t2.start();

        SyncedThread t3 = new SyncedThread();
        Thread t3_ = new Thread(t3);
        t3_.start();

        SyncedThread t4 = new SyncedThread();
        new Thread(t4).start();

        Runnable r = () -> System.out.println("Something");
        new Thread(r).start();

        try {

            new Thread(() -> System.out.println(2 / 0)).start();

        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}