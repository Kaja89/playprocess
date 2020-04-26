package com.player.playprocess;

import javafx.application.HostServices;
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
    public Label testLabel;

    @FXML
    public Button testButton;

    @FXML
    public void initialize() {
        this.testButton.setOnAction(actionEvent -> {
            this.testLabel.setText(this.hostServices.getDocumentBase());
        });
    }
}
