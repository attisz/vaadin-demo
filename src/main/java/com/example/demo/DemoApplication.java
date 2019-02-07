package demo;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.View;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
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

        @Override
        protected void init(VaadinRequest request) {
            VerticalLayout layout = new VerticalLayout();
            setContent(layout);

            Label label = new Label(myService.sayHi());
            layout.addComponent(label);
            layout.addComponent(new Button("gomb"));
        }

    }

    @SpringView
    public static class MyView implements View {

    }
}