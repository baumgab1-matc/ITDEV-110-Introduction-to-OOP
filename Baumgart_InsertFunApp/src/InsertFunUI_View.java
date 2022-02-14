import java.util.Scanner;

//Brent Baumgart
//ITDEV-110
//Assignment 6

class InsertFunUI_View {

    private final Scanner scanner;
    private final Admin admin;

    public InsertFunUI_View() {
        this.scanner = new Scanner(System.in);
        this.admin = new Admin();
    }

    public void printIntro() {
        admin.intro();
    }

    public void printOutro() {
        admin.outro();
    }

    public String getPerson() {
        System.out.print("Type a female person: ");
        return scanner.nextLine();
    }

    public String getAnimal() {
        System.out.print("Type an animal: ");
        return scanner.nextLine();
    }

    public String getAdjectiveOne() {
        System.out.print("Give an adjective: ");
        return scanner.nextLine();
    }

    public String getAdjectiveTwo() {
        System.out.print("Give another adjective: ");
        return scanner.nextLine();
    }

    public String getVerb() {
        System.out.print("Finally give a verb in past tense: ");
        return scanner.nextLine();
    }

    public String displayContinueMessage() {
        System.out.print("\nWould you like to play again? Type 'exit' to stop, else type anything to continue ");
        return scanner.nextLine();
    }

    public void printPoem(InsertFun poem) {
        System.out.println("\nAdjusting poem....Let's see what you came up with!\n");
        System.out.println(poem);
    }

}
