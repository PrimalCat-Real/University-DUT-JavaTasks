module romanenko.aplet {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens romanenko.aplet to javafx.fxml;
    exports romanenko.aplet;
}