package com.player.playprocess.controller;

import javafx.application.HostServices;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;

@Component
public class UIController {

    private HostServices hostServices;

    public UIController(HostServices hostServices) {//Intellij can't see this but works
        this.hostServices = hostServices;
    }

    @FXML
    public void initialize() {
    }
}
