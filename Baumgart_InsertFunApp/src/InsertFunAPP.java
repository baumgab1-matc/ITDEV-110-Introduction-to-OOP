
//Brent Baumgart
//ITDEV-110
//Assignment 6

public class InsertFunAPP  {

    public static void main(String[] args) {
        InsertFun poem = new InsertFun();
        InsertFunUI_View view = new InsertFunUI_View();

        Game_Controller controller = new Game_Controller(poem, view);
        controller.play();

    }
}
