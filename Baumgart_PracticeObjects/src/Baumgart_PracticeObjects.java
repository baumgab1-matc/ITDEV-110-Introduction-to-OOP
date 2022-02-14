
//Brent Baumgart
//ITDEV-110
//Assignment 5

public class Baumgart_PracticeObjects {

    public static void main(String[] args) {
        //create admin object and call method intro
        Admin getReady = new Admin();
        getReady.intro();

        //create two objects of MathFun
        MathFun calc1 = new MathFun();
        MathFun calc2 = new MathFun();

        //set values for the two objects
        calc1.setNumberOne(7);
        calc1.setNumberTwo(-4);

        calc2.setNumberOne(5);
        calc2.setNumberTwo(4);

        //get and display the numbers
        int calc1NumberOne = calc1.getNumberOne();
        int calc1NumberTwo = calc1.getNumberTwo();

        int calc2NumberOne = calc2.getNumberOne();
        int calc2NumberTwo = calc2.getNumberTwo();

        System.out.println("calc1 numbers are " + calc1NumberOne + " and " + calc1NumberTwo);
        System.out.println("calc2 numbers are " + calc2NumberOne + " and " + calc2NumberTwo + "\n");

        //multiply and display the numbers
        int calc1Product = calc1.multiplyThem();
        int calc2Product = calc2.multiplyThem();

        System.out.println("The product for calc1 is " + calc1Product);
        System.out.println("The product for calc2 is " + calc2Product + "\n");

        //add and display the numbers
        int calc1Sum = calc1.addThem();
        int calc2Sum = calc2.addThem();

        System.out.println("The sum for calc1 is " + calc1Sum);
        System.out.println("The sum for calc2 is " + calc2Sum + "\n");

        //total the the values of both objects and display them
        int totalValue = calc1Sum + calc2Sum + calc1Product + calc2Product;
        System.out.println("The total value is " + totalValue + "\n");

        //display closing info
        getReady.goodbye();
    }

}
