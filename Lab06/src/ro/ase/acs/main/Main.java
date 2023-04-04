package ro.ase.acs.main;

import ro.ase.acs.classes.Student;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Name = ");
        String name = scanner.nextLine();
        System.out.print("Age = ");
        int age = scanner.nextInt();
        System.out.print("Grade = ");
        float grade = scanner.nextFloat();
        Student s1 = new Student(name, age, grade);
        System.out.println(s1);
        scanner.close();

        BufferedWriter bufferedWriter = null;

        try {
            FileOutputStream fileOutputStream =
                    new FileOutputStream("student.txt");
            OutputStreamWriter outputStreamWriter =
                    new OutputStreamWriter(fileOutputStream);
            bufferedWriter =
                    new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(s1.getName());
            bufferedWriter.write(System.lineSeparator());
            bufferedWriter.write(Integer.toString(s1.getAge()));
            bufferedWriter.write(System.lineSeparator());
            bufferedWriter.write(Float.toString(s1.getGrade()));
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (FileInputStream fileInputStream =
                     new FileInputStream("student.txt");
             InputStreamReader inputStreamReader =
                     new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader =
                     new BufferedReader(inputStreamReader)) {
            String n = bufferedReader.readLine();
            int a = Integer.parseInt(bufferedReader.readLine());
            float g = Float.parseFloat(bufferedReader.readLine());
            Student s2 = new Student(n, a, g);
            System.out.println(s2);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DataOutputStream dataOutputStream = null;
        try {
            FileOutputStream fileOutputStream =
                    new FileOutputStream("student.bin");
            dataOutputStream = new DataOutputStream(fileOutputStream);
            dataOutputStream.writeUTF(s1.getName());
            dataOutputStream.writeInt(s1.getAge());
            dataOutputStream.writeFloat(s1.getGrade());
            dataOutputStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        DataInputStream dataInputStream = null;
        try {
            FileInputStream fileInputStream =
                    new FileInputStream("student.bin");
            dataInputStream = new DataInputStream(fileInputStream);
            String n = dataInputStream.readUTF();
            int a = dataInputStream.readInt();
            float g = dataInputStream.readFloat();
            Student s3 = new Student(n, a, g);
            System.out.println(s3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if(dataInputStream != null) {
                    dataInputStream.close();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}