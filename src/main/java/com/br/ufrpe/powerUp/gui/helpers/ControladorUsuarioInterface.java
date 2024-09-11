package com.br.ufrpe.powerUp.gui.helpers;

import com.br.ufrpe.powerUp.negocio.controllers.ControladorUsuario;

public interface ControladorUsuarioInterface {
    void setUserController(ControladorUsuario userController);
    ControladorUsuario getUserController();
}

