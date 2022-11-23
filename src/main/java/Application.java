import com.fasterxml.jackson.annotation.JsonIgnore;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Application extends Frame {
    private Label lblCount;    // Declare a Label component
    private TextField tfCount; // Declare a TextField component
    private Button btnShow;
    private Button btnAdd;
    private int count = 0;// Counter's value
    List listOfRec = null;
    Recipies list = null;

    AddRec add = null;
    ShowRec show = null;

    // Constructor to setup GUI components and event handlers
    public Application() throws IOException {
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
        btnAdd.addActionListener(listenerShow);

        listOfRec = new List();
        list = Jsons.getAllFromJson("recipes.json");

        for (String r : list.names) {
            listOfRec.add(r);
         }

        add(listOfRec);

        setSize(450, 450);
        setVisible(true);         // "super" Frame shows
    }

    // The entry main() method
    public static void main(String[] args) throws IOException {
        // Invoke the constructor to setup the GUI, by allocating an instance
        Application app = new Application();
        // or simply "new AWTCounter();" for an anonymous instance
    }

    // Define an inner class to handle the "Count" button-click
    private class BtnAddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            add = new AddRec();
        }
    }

    private class BtnShowListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                show = new ShowRec(listOfRec.getItem(listOfRec.getSelectedIndex()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class ShowRec extends Frame {
        private Label lblCount;
        private Label lblName;
        private Label lblDesc;// Declare a Label component
        private TextField tfName;
        private TextArea desc;

        ShowRec(String rName) throws IOException {
            Recipe r = Jsons.getFromJson(rName + ".json");

            setLayout(new FlowLayout());

            lblCount = new Label("Добавление рецепта");  // construct the Label component
            add(lblCount);                    // "super" Frame container adds Label component

            lblName = new Label("Название");  // construct the Label component
            add(lblName);

            tfName = new TextField();
            tfName.setText(r.name);
            add(tfName);

            lblDesc = new Label("Описание");
            add(lblDesc);

            desc = new TextArea();
            desc.setText(r.instruction);
            add(desc);

            setSize(600, 600);
            setVisible(true);
        }
    }

    private class AddRec extends Frame {
        private Label lblCount;
        private Label lblName;
        private Label lblDesc;// Declare a Label component
        private TextField tfName;
        private TextArea desc;

        private Button btnSave;

        AddRec() {

            setLayout(new FlowLayout());

            lblCount = new Label("Добавление рецепта");  // construct the Label component
            add(lblCount);                    // "super" Frame container adds Label component

            btnSave = new Button("Сохранить рецепт");   // construct the Button component
            add(btnSave);                    // "super" Frame container adds Button component

            BtnSaveListener listenerSave = new BtnSaveListener();
            btnSave.addActionListener(listenerSave);

            lblName = new Label("Название");  // construct the Label component
            add(lblName);

            tfName = new TextField();
            add(tfName);

            lblDesc = new Label("Описание");  // construct the Label component
            add(lblDesc);

            desc = new TextArea();
            add(desc);

            setSize(600, 600);
            setVisible(true);
        }

        private class BtnSaveListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 Recipe r = new Recipe();
                 r.name = tfName.getText();
                 r.instruction = desc.getText();
                try {
                    Jsons.toJson(r, r.name + ".json");
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Recipies ll = null;
                try {
                    ll = Jsons.getAllFromJson("recipes.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                ll.names.add(r.name);
                try {
                    Jsons.allToJson(ll, "recipes.json");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}