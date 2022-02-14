
/*
Little Arabella Miller

Little Arabella Miller[person]
Found a furry[adj1] caterpillar[animal]
First it climbed[verb] upon her mother
Then upon her baby[adj2] brother
"Ugh" said Arabella Miller[person]
"Take away that caterpillar![animal]"
*/

//Brent Baumgart
//ITDEV-110
//Assignment 6

public class InsertFun {

    private String person;
    private String animal;
    private String verb;
    private String adjectiveOne;
    private String adjectiveTwo;

    //I chose not to use a constructor but rather setters, since if I wanted to add something new
    //I wouldn't have to constantly change the constructor
    //I would just add a setter

    //setters
    public void setPerson(String person) {
        this.person = person;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }

    public void setAdjectiveOne(String adj) {
        this.adjectiveOne = adj;
    }

    public void setAdjectiveTwo(String adj) {
        this.adjectiveTwo = adj;
    }

    @Override
    public String toString() {
        return "Little " + person + "\n"
                + "Found a " + adjectiveOne + " " + animal + "\n"
                + "First it " + verb + " upon her mother \n"
                + "Then upon her " + adjectiveTwo + " brother \n"
                +  "\"Ugh\" said " + person + "\n"
                + "Take away that " + animal + "!";
    }

}
