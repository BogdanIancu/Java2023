package ro.ase.acs.classes;

import ro.ase.acs.interfaces.Taxable;

public final class Car extends Vehicle implements Taxable {
    private String color;
    private final Engine engine = new Engine();

    public Car() {
        super();
        engine.power = 200;
        //engine = new Engine();
    }

    public Car(String producer, int speed, String color) {
        super(producer, speed);
        this.color = color;
    }

    @Override
    public final void move() {
        System.out.println("The car is moving!");
    }

    @Override
    public double computeTax() {
        double tax = getSpeed() * 2;
        if(tax < MIN_TAX) {
            return MIN_TAX;
        } else {
            return tax;
        }
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Car copy = (Car)super.clone();
        copy.color = color;
        return copy;
    }
}

class Engine {
    public int power;
}
