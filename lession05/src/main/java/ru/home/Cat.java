package ru.home;

public class Cat {

    private String name;
    private int stomachVolume;
    private boolean full;
    private Master owner;

    public Cat(String name, int stomachVolume, Master owner) {

        if (stomachVolume < 0)
            throw new IllegalArgumentException("Это какой-то неправильный кот, stomachVolume: " + stomachVolume);

        this.name = name;
        this.stomachVolume = stomachVolume;
        full = false;
        this.owner = owner;
    }

    public void fress(Bowl bowl) {

        if (bowl == null)
            throw new IllegalArgumentException("Сука, издеваешься ?");


        if (bowl.getLevel() >= stomachVolume) {
            bowl.consume(stomachVolume);
            System.out.println(name + ": Om-nom-nom-nom-nom");
            full = true;
        }
        else {
            System.out.println(name + ": Meooooooooowww! Meoooowwowow! Meeeeeeeeeoooowwwwwww! Meeeeeeoooooooooowoewoww!!!");

            if (owner != null) {
                owner.call();
            }

        }
    }

    public String getName() {
        return name;
    }

    public int getStomachVolume() {
        return stomachVolume;
    }

    public boolean isFull() {
        return full;
    }


    @Override
    public String toString() {
        return "Cat{" +
               "name='" + name + '\'' +
               ", full=" + full +
               '}';
    }
}
