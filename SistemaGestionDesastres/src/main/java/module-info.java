module co.edu.uniquindio.sistemagestiondesastres {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens co.edu.uniquindio.sistemagestiondesastres to javafx.fxml;
    exports co.edu.uniquindio.sistemagestiondesastres;
    exports co.edu.uniquindio.sistemagestiondesastres.viewControllers;
    opens co.edu.uniquindio.sistemagestiondesastres.viewControllers to javafx.fxml;
}