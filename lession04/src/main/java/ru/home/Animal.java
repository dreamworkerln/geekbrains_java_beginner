package ru.home;

import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

abstract class Animal {

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

        String classname = this.getClass().getSimpleName();
        String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();

        if (value < 0 ) {
            System.out.println(
                    String.format("%1$s.%2$s(%3$.1f): Bad input params",
                            classname, methodName, value));
            return;
        }


        String res = String.format("%1$s.%2$s(%3$.1f): ", classname, methodName, value);

        if (value < restriction)
            System.out.println(res + "OK");
        else
            System.out.println(res + "FAILED");
    }
}



// ==========================================

class Cat extends Animal {

    Cat() {

        runRestriction = ThreadLocalRandom.current().nextDouble(100, 300);
        swimRestriction = 0;
        jumpRestriction = ThreadLocalRandom.current().nextDouble(1, 3);
    }

}

// ==========================================

class Dog extends Animal {

    Dog() {
        runRestriction = ThreadLocalRandom.current().nextDouble(400, 600);
        swimRestriction = ThreadLocalRandom.current().nextDouble(5, 15);
        jumpRestriction = ThreadLocalRandom.current().nextDouble(0.25, 0.75);
    }

}

class TestAnimals {

    static void test() {

        Animal cat = new Cat();
        Animal dog = new Dog();

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

        System.out.println();

        cat.run(-5.4);

    }
}




