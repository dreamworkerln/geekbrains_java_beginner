package ru.home;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

class Employee {

    private String fullName;
    private String email;
    private String phone;
    private int salary;
    private int age;

    Employee(String fullName, String email, String phone, int salary, int age) {

        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {

        return "Employee{" +
               "fullName='" + fullName + '\'' +
               ", email='" + email + '\'' +
               ", phone='" + phone + '\'' +
               ", salary=" + salary +
               ", age=" + age +
               '}';
    }

    static void test() {



        final int COUNT = 5;

        Employee[] crew = new Employee[5];

        for(int i = 0; i < COUNT; i++) {

            String fullName = UUID.randomUUID().toString();
            fullName = fullName.replace("-", "");
            String email = UUID.randomUUID().toString() + "@" + UUID.randomUUID().toString() + ".com";
            email = email.replace("-", "");
            String phone = ((Long)ThreadLocalRandom.current().nextLong(10000000000L, 99999999999L)).toString();
            int salary = ThreadLocalRandom.current().nextInt(1000, 999999);
            int age = ThreadLocalRandom.current().nextInt(16, 99);

            crew[i] = new Employee(fullName, email, phone, salary, age);

            if (crew[i].age > 40)
                System.out.println(crew[i].toString());
        }


    }
}
