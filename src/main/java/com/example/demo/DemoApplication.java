package com.example.demo;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.components.grid.MultiSelectionModel;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;

@SpringBootApplication
public class DemoApplication {

    // This is a runnable Spring Boot application
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    /* Business Service Facade.
     * Annotate your business services with @Service and they
     * will be autodetected by the framework.
     */
    @Service
    public static class MyService {

        public String sayHi() {
            return "Hello Spring Initializr!";
        }

    }

    /* Vaadin UI class.
     * Specify the theme and URI path for the
     * web application.
     */
    @Theme("mytheme")
    @SpringUI(path = "")
    public static class VaadinUI extends UI {

        // You can easily autowire the services to you
        // Vaadin applications
        @Autowired
        MyService myService;

        VerticalLayout layout = new VerticalLayout();

        @Override
        protected void init(VaadinRequest request) {
            setContent(layout);
            Label label = new Label(myService.sayHi());
            layout.addComponent(label);
            layout.addComponent(new Button("gomb"));
            createGrid();
        }

        private void createGrid() {
            // Have some data
            List<Person> people = Arrays.asList(
                new Person("Nicolaus Copernicus", 1543),
                new Person("Galileo Galilei", 1564),
                new Person("Johannes Kepler", 1571));

            // Create a grid bound to the list
            Grid<Person> grid = new Grid<>();
            grid.setItems(people);
            grid.addColumn(Person::getName).setCaption("Name");
            grid.addColumn(Person::getBirthYear).setCaption("Year of birth");

            MultiSelectionModel<Person> selectionModel
                = (MultiSelectionModel<Person>) grid.setSelectionMode(Grid.SelectionMode.MULTI);
            selectionModel.selectAll();

            layout.addComponent(grid);
        }

    }

    @SpringView
    public static class MyView implements View {

    }
}