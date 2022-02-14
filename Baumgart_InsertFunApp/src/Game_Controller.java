
//Brent Baumgart
//ITDEV-110
//Assignment 6

public class Game_Controller {

    private final InsertFun poem;
    private final InsertFunUI_View view;
    private final Admin admin;

    public Game_Controller(InsertFun poem, InsertFunUI_View view) {
        this.poem = poem;
        this.view = view;
        this.admin = new Admin();
    }

    public void play() {
        boolean isPlaying = true;
        //parts of speech being used
        String person;
        String animal;
        String adj1;
        String adj2;
        String verb;

        //print intro
        view.printIntro();

        while (isPlaying) {
            //get input from the view
            person = view.getPerson();
            animal = view.getAnimal();
            adj1 = view.getAdjectiveOne();
            adj2 = view.getAdjectiveTwo();
            verb = view.getVerb();

            //set variables for poem
            updatePoem(person, animal, adj1, adj2, verb);

            //print the poem to the user
            view.printPoem(poem);

            //check if user wants to play again
            String choice = view.displayContinueMessage();

            if ("exit".equals(choice)) {
                isPlaying = false;
            }

            System.out.println();
        }

        //at this point user has chosen to stop, need to print goodbye message
        view.printOutro();
    }

    //rather than instantiating a new object for each update, setters
    //are being used on just one object
    private void updatePoem(String person, String animal, String adj1, String adj2, String verb) {
        poem.setPerson(person);
        poem.setAnimal(animal);
        poem.setAdjectiveOne(adj1);
        poem.setAdjectiveTwo(adj2);
        poem.setVerb(verb);
    }



}
