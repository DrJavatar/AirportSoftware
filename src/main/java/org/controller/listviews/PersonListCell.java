package org.controller.listviews;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import org.people.Person;
import org.people.impl.Employee;

public class PersonListCell extends ListCell<Person> implements Callback<ListView<Person>, ListCell<Person>> {

    @Override
    protected void updateItem(Person item, boolean empty) {
        super.updateItem(item, empty);
        if(item != null && !empty) {
            String fullName = item.getFirstName() + " " + item.getLastName();
            super.setText(fullName);
            if(item instanceof Employee) {
                super.setText(fullName + " - " + ((Employee) item).getRole().name());
                super.setTextFill(Color.rgb(155,9,0));
            } else {
                super.setTextFill(Color.BLACK);
            }
        } else {
            super.setText(null);
        }
    }

    @Override
    public ListCell<Person> call(ListView<Person> personListView) {
        return new PersonListCell();
    }
}
