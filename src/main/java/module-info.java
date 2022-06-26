module islandsimulation {

    requires javafx.controls;
    requires javafx.fxml;
    requires org.reflections;

    opens com.javarush.island.ogarkov;
    opens com.javarush.island.ogarkov.location;
    opens com.javarush.island.ogarkov.view;
}