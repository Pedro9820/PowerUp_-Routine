module com.br.ufrpe.powerUp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.media;
    requires java.desktop;
    requires com.sshtools.twoslices;
    requires animatefx;

    exports com.br.ufrpe.powerUp.gui;
    exports com.br.ufrpe.powerUp.negocio.controllers;
    exports com.br.ufrpe.powerUp.gui.helpers;

    opens com.br.ufrpe.powerUp.gui to javafx.fxml;
    opens com.br.ufrpe.powerUp.negocio.beans to javafx.base;
    opens com.br.ufrpe.powerUp.negocio to javafx.base;
    opens com.br.ufrpe.powerUp.gui.helpers to javafx.fxml;
}
