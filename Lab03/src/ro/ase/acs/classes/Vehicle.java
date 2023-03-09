package ro.ase.acs.classes;

public abstract class Vehicle implements Cloneable {
    private String producer;
    private int speed;

    public Vehicle() {

    }

    public Vehicle(String producer, int speed) {
        this.producer = producer;
        this.speed = speed;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Vehicle copy = (Vehicle) super.clone();
        copy.producer = producer;
        copy.speed = speed;
        return copy;
    }

    public abstract void move();
}
