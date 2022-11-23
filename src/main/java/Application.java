import java.awt.*;        // Using AWT container and component classes
import java.awt.event.*;
    // Using AWT event classes and listener interfaces

// An AWT program inherits from the top-level container java.awt.Frame
public class Application extends Frame {
    private Label lblCount;    // Declare a Label component
    private TextField tfCount; // Declare a TextField component
    private Button btnShow;
    private Button btnAdd;
    private int count = 0;// Counter's value
    List listOfRec = null;


    // List<Recepies> r = null;

    // Constructor to setup GUI components and event handlers
    public Application() {
        setLayout(new FlowLayout());
        // "super" Frame, which is a Container, sets its layout to FlowLayout to arrange
        // the components from left-to-right, and flow to next row from top-to-bottom.

        lblCount = new Label("Книга рецептов");  // construct the Label component
        add(lblCount);                    // "super" Frame container adds Label component


        btnShow = new Button("Посмотреть рецепт");   // construct the Button component
        add(btnShow);                    // "super" Frame container adds Button component

        btnAdd = new Button("Добавить рецепт");   // construct the Button component
        add(btnAdd);

        BtnShowListener listenerAdd = new BtnShowListener();
        btnShow.addActionListener(listenerAdd);

        BtnAddListener listenerShow = new BtnAddListener();
        btnShow.addActionListener(listenerShow);

        listOfRec = new List();
        //for ( : recp) {
        //    listOfRec.add();
        // }
        listOfRec.add("Item 1");
        listOfRec.add("Item 2");
        listOfRec.add("Item 3");

        add(listOfRec);

        setSize(450, 450);
        setVisible(true);         // "super" Frame shows


    }

    // The entry main() method
    public static void main(String[] args) {
        // Invoke the constructor to setup the GUI, by allocating an instance
        Application app = new Application();
        // or simply "new AWTCounter();" for an anonymous instance
    }

    // Define an inner class to handle the "Count" button-click
    private class BtnAddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }

    private class BtnShowListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {

        }
    }

    private class ShowRec extends Frame {
        private Label lblCount;    // Declare a Label component
        private TextField tfCount; // Declare a TextField component
        private Button btnShow;
        private Button btnAdd;
        private int count = 0;// Counter's value
        List listOfRec = null;

        //ShowRec(Rec ) {

        //}
    }

    private class AddRec extends Frame {
        private Label lblCount;    // Declare a Label component
        private TextField tfCount; // Declare a TextField component
        private Button save;
        private int count = 0;// Counter's value
        List listOfRec = null;

        //ShowRec() {

        //}

    }
}