package ro.ase.acs.main;

import ro.ase.acs.classes.Student;

public class Main {
    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student.getTuitionType());

        int[] v = new int[] { 8, 7, 9 };

        Object copy = student.clone();
        if(copy instanceof Student) {
            Student s = (Student)copy;
        }

        Student student2 = new Student("John", v);
        v[0] = 11;
        int[] grades = student2.getGrades();
        System.out.println(grades[0]);

        int[][] m = new int[2][3];

        int[][] x = new int[2][];
        x[0] = new int[3];
        x[1] = new int[4];
    }
}
