package ro.ase.acs.classes;

import ro.ase.acs.interfaces.BinaryOperation;

public class Sum implements BinaryOperation {
    @Override
    public double compute(double val1, double val2) {
        return val1 + val2;
    }
}
