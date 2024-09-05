module com.br.ufrpe.powerUp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    exports com.br.ufrpe.powerUp.gui;
    opens com.br.ufrpe.powerUp.gui to javafx.fxml;
    opens com.br.ufrpe.powerUp.negocio.beans to javafx.base;
    opens com.br.ufrpe.powerUp.negocio to javafx.base;
}