package ro.ase.acs.java.main;

import ro.ase.acs.classes.Student;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Student s=new Student("Andreea", 8.3f);
        System.out.println(s);
        System.out.println(eq(2,3));
        Student t=s;
        System.out.println(eq(s,t));
        System.out.println(lt(2,3));

        List<Student> list=new ArrayList<>();
        list.add(s);
        list.add(t);
        for(int i=0;i<list.size();i++)
        {
            System.out.println(list.get(i));
        }

        // List l2=new ArrayList<>();
        List<Integer> list2=new Vector<>();
        list2.add(3);
        list2.add(2);
        list2.add(1);
        // list2.remove(1);
        list2.remove(Integer.valueOf(1));
        for(var x : list2)
        {
            System.out.println(x);
        }

        for(Iterator<Integer> it = list2.iterator(); it.hasNext();)
        {
            System.out.println(it.next());
        }

        var y=7;
    }

    public static <T> boolean eq(T a, T b){
        return a.equals(b);
    }
    public static <T extends Comparable> boolean lt(T a, T b){
        return a.compareTo(b)<0;
    }
}

