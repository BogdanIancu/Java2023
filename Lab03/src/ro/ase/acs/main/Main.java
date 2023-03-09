package ro.ase.acs.main;

import ro.ase.acs.classes.Car;
import ro.ase.acs.classes.Vehicle;
import ro.ase.acs.interfaces.Taxable;

public class Main {
    public static void main(String[] args) {
        Taxable t;
        Vehicle v;

        Car c = new Car("Dacia", 60, "red");
        v = c;
        v.move();

        t = c;
        System.out.println(t.computeTax());

        try {
            Car c2 = (Car) c.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

