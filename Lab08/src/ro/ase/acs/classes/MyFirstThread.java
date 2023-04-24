package ro.ase.acs.classes;

public class MyFirstThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("Message from another thread");
    }
}
