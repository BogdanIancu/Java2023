package ro.ase.acs.main;


import ro.ase.acs.classes.Sum;
import ro.ase.acs.interfaces.BinaryOperation;
import ro.ase.acs.interfaces.Printable;
import ro.ase.acs.interfaces.Taxable;

import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        BinaryOperation operation;
        operation = new Sum();
        double result = operation.compute(10, 20);
        System.out.println(result);
        operation = new BinaryOperation() {
            @Override
            public double compute(double val1, double val2) {
                return val1 - val2;
            }
        };
        result = operation.compute(10, 20);
        System.out.println(result);

        new Sum().compute(15, 25);

        operation = (x, y) -> x * y;
        result = operation.compute(5, 10);
        System.out.println(result);

        operation = (x, y) -> {
            double sum = x + y;
            return sum / 2;
        };
        result = operation.compute(8, 7);
        System.out.println(result);

        Taxable t = () -> 50;
        System.out.println(t.computeTax());
        Printable p = x -> System.out.print(x);
        p.print("Hello, World!");

        List<Integer> list = List.of(12, 3, 5, 7, 23, 6);
        long r = list.stream().filter(x -> x % 2 == 0).count();
        System.out.println(r);

        List<Integer> list2 = list.stream().map(x -> x * x).sorted().toList();
        System.out.println(list2);

        List<String> list3 = List.of("ab", "abcd", "xyz", "qwerty", "sdfgj");
        String s = list3.stream().
                filter(st -> st.contains("b")).
                collect(Collectors.joining("-"));
        System.out.println(s);

        list3.stream().filter(x -> x.length() >= 3).
                map(String::length).distinct().sorted().
                forEach(System.out::println);

        Random random = new Random();
        OptionalInt oi = random.ints().limit(10).reduce((x, y) -> x + y);
        System.out.println(oi.getAsInt());
    }
}