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

    Label ingred = null;
    private TextField ingToFind;
    private Button findRec;

    private Button showAllRec;

    public Application() throws IOException {
        setLayout(new FlowLayout());

        lblCount = new Label("Книга рецептов");
        add(lblCount);

        btnShow = new Button("Посмотреть рецепт");
        add(btnShow);

        btnAdd = new Button("Добавить рецепт");
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

        ingred = new Label("Поиск по ингредиенту:");
        add(ingred);

        ingToFind = new TextField(20);
        add(ingToFind);

        showAllRec = new Button("Показать все рецепты");
        BtnShowAllListener a = new BtnShowAllListener();
        showAllRec.addActionListener(a);
        add(showAllRec);


        findRec = new Button("Найти рецепты");
        add(findRec);
        BtnFindListener findR = new BtnFindListener();
        findRec.addActionListener(findR);


        setSize(450, 450);
        setVisible(true);
    }

    public static void main(String[] args) throws IOException {
        Application app = new Application();
    }

    private class BtnAddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            add = new AddRec();
        }
    }

    private class BtnShowAllListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            try {
                list = Jsons.getAllFromJson("recipes.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
            listOfRec.removeAll();
            for (String r : list.names) {
                listOfRec.add(r);
            }
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

    private class BtnFindListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent evt) {
            Recipies reps = null;
            try {
                reps = Jsons.getAllFromJson("recipes.json");
            } catch (IOException e) {
                e.printStackTrace();
            }
            java.util.List<Recipe> recs = new ArrayList<>();
            for (String r : reps.names) {
                try {
                    recs.add(Jsons.getFromJson(r + ".json"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            java.util.List<Recipe> recipes = CookbookFuncs.SearchIngridient(recs, ingToFind.getText());

            listOfRec.removeAll();

                for (Recipe r : recipes) {
                    listOfRec.add(r.name);
                }

            //add(listOfRec);
        }
    }

    private class ShowRec extends Frame {
        private Label lblCount;
        private Label lblName;
        private Label lblDesc;
        private TextField tfName;
        private TextArea desc;

        ShowRec(String rName) throws IOException {
            Recipe r = Jsons.getFromJson(rName + ".json");

            setLayout(new FlowLayout());

            lblCount = new Label("Просмотр рецепта");
            add(lblCount);

            lblName = new Label("Название");
            add(lblName);

            tfName = new TextField();
            tfName.setText(r.name);
            add(tfName);

            lblDesc = new Label("Описание");
            add(lblDesc);

            desc = new TextArea();
            desc.setText(r.instruction);
            add(desc);

            for(Ingridient i : r.ingridients){
                Label ingr = new Label(i.name + " " + i.amount + " " + i.amount);
                add(ingr);
            }

            setSize(600, 600);
            setVisible(true);
        }
    }

    private class AddRec extends Frame {
        private Label lblCount;
        private Label lblName;
        private Label lblDesc;
        private TextField tfName;
        private TextArea desc;

        private Button btnSave;

        java.util.List<TextField> name = new ArrayList<TextField>(6);
        java.util.List<TextField> amount = new ArrayList<TextField>(6);
        java.util.List<TextField> unit = new ArrayList<TextField>(6);

        AddRec() {
            setLayout(new FlowLayout());

            lblCount = new Label("Добавление рецепта");
            add(lblCount);

            btnSave = new Button("Сохранить рецепт");
            add(btnSave);

            BtnSaveListener listenerSave = new BtnSaveListener();
            btnSave.addActionListener(listenerSave);

            lblName = new Label("Название");
            add(lblName);

            tfName = new TextField(20);
            add(tfName);

            lblDesc = new Label("Описание");
            add(lblDesc);

            desc = new TextArea();
            add(desc);

            for(int i = 0; i < 7; i++) {
                name.add(new TextField(20));
                add(name.get(i));
                amount.add(new TextField(20));
                add(amount.get(i));
                unit.add(new TextField(20));
                add(unit.get(i));
            }

            setSize(600, 600);
            setVisible(true);
        }

        private class BtnSaveListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Recipe r = new Recipe();
                r.name = tfName.getText();
                r.instruction = desc.getText();
                java.util.List<Ingridient> ingrs = new ArrayList<>();
                for(int i = 0; i < 7; i++) {
                    if(!name.get(i).getText().equals("")) {
                        Ingridient in = new Ingridient();
                        in.amount = Integer.getInteger(amount.get(i).getText());
                        in.name = name.get(i).getText();
                        in.unit = unit.get(i).getText();
                        ingrs.add(in);
                    }
                }
                r.ingridients = ingrs;
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