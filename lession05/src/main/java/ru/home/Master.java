package ru.home;

class Master {

    private Bowl bowl;

    public void call() {

        if (bowl!= null) {
            
            bowl.fillUp();
            System.out.println("Master: Жрите");
        }
    }

    public Bowl getBowl() {
        return bowl;
    }

    public void setBowl(Bowl bowl) {
        this.bowl = bowl;
    }
}