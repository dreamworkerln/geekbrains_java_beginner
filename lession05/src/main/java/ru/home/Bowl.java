package ru.home;

public class Bowl {

    /**
     * Current content level
     */
    private int level;


    /**
     *  Bowl capacity
     */
    private int capacity;


    public Bowl(int capacity) {

        level = 0;

        if (capacity < 0)
            throw new IllegalArgumentException("Объем миски меньше нуля: " + capacity);

        this.capacity = capacity;
    }


    /**
     * Full up bowl
     */
    public void fillUp() {

        level = capacity;
    }

    /**
     * Consume food from bowl
     * @param amount requested amount food to consume
     * @return really consumed food amount
     */
    public int consume(int amount) {

        if (amount < 0)
            throw new IllegalArgumentException("Для заполнения используйте другой метод, amount: " + amount);

        int consumed = Math.min(level, amount);

        level -= consumed;

        return consumed;
    }


    public int getLevel() {
        return level;
    }

    public int getCapacity() {
        return capacity;
    }


    @Override
    public String toString() {
        return "Bowl{" +
               "capacity=" + capacity +
               ", level=" + level +
               '}';
    }
}
