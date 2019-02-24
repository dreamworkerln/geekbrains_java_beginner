package ru.home;

import java.util.concurrent.ThreadLocalRandom;

abstract class Aninal {

    double runRestriction;
    double swimRestriction;
    double jumpRestriction;

    void run(double value) {

        doIt(value, runRestriction);
    }

    void swim(double value) {

        doIt(value, swimRestriction);
    }

    void jump(double value) {

        doIt(value, jumpRestriction);
    }

    private void doIt(double value, double restriction) {

        if (value < 0 ) {
            System.out.println("Неверные входные данные: " + value);
            return;
        }

        String classname = this.getClass().getSimpleName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();


        String res = String.format("%1$s.%2$s(%3$.1f): ",classname, methodName, value);

        if (value < restriction)
            System.out.println(res + "OK");
        else
            System.out.println(res + "FAILED");
    }
}



// ==========================================

class Cat extends Aninal {

    Cat() {

        runRestriction = ThreadLocalRandom.current().nextDouble(100, 300);
        swimRestriction = 0;
        jumpRestriction = ThreadLocalRandom.current().nextDouble(1, 3);
    }

}

// ==========================================

class Dog extends Aninal {

    Dog() {
        runRestriction = ThreadLocalRandom.current().nextDouble(400, 600);
        swimRestriction = ThreadLocalRandom.current().nextDouble(5, 15);
        jumpRestriction = ThreadLocalRandom.current().nextDouble(0.25, 0.75);
    }

}

class TestAnimals {

    static void test() {

        Aninal cat = new Cat();
        Aninal dog = new Dog();

        cat.run(400);
        cat.run(100);

        cat.swim(5);

        cat.jump(10);
        cat.jump(1);

        System.out.println();

        dog.run(1000);
        dog.run(200);

        dog.swim(10);
        dog.swim(5);

        dog.jump(1);
        dog.jump(0.4);

    }
}




