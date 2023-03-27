package ro.ase.acs.main;

import ro.ase.acs.classes.Student;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {

        Student s1 = new Student("John", 9);
        System.out.println(s1);

        Set<Student> set = new TreeSet<>();

        Student s2 = new Student("George", 8);

        set.add(s1);
        set.add(s2);

        for (Student s : set) {
            System.out.println(s);
        }
        Map<Student, Integer> map = new HashMap<>();
        map.put(s1, 123);
        map.put(s2, 456);
        Student s3 = new Student("George", 8);
        map.put(s3, 789);
        for (Student s : map.keySet()) {
            System.out.println(s + ": " + map.get(s));

        }
        String x = new String("abc");
        String y = new String("abc");
        System.out.println(x.equals(y));
        interchange(s1, s2);
        System.out.println(s1.getGrade());
    }

    public static void interchange(Student s1, Student s2) {
//        float aux= s1.getGrade();
//        s1.setGrade(s2.getGrade());
//        s2.setGrade(aux);
        Student aux = s1;
        s1 = s2;
        s2 = aux;
    }

}
