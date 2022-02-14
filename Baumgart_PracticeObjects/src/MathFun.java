
//Brent Baumgart
//ITDEV-110
//Assignment 5

class MathFun {

    private int numberOne;
    private int numberTwo;

    //multiplies the two numbers
    public int multiplyThem() {
        return this.numberOne * numberTwo;
    }

    //adds the two numbers together
    public int addThem() {
        return this.numberOne + this.numberTwo;
    }


    //getters and setters below
    public int getNumberOne() {
        return this.numberOne;
    }

    public void setNumberOne(int num) {
        this.numberOne = num;
    }

    public int getNumberTwo() {
        return this.numberTwo;
    }

    public void setNumberTwo(int num) {
        this.numberTwo = num;
    }

}
