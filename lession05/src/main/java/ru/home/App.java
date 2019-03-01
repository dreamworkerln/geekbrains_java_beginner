package ru.home;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) {
        //System.out.println( "Hello World!" );

        Master master = new Master();


        List<Cat> list = new ArrayList<>();

        list.add(new Cat("Барсик", 10, master));
        list.add(new Cat("Василий", 15, master));
        list.add(new Cat("Рыжик", 20, master));
        list.add(new Cat("Степан", 20, master));
        list.add(new Cat("Тит", 25, master));



        Bowl bowl = new Bowl(20);
        bowl.fillUp();

        master.setBowl(bowl);

        for(Cat cat : list) {
            cat.fress(bowl);
        }

        for(Cat cat : list) {
            System.out.println(cat.toString());
        }
    }
}
