package com.player.playprocess;

import javafx.application.HostServices;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import org.springframework.stereotype.Component;
import java.net.URL;

@Component
public class UIController {

    private HostServices hostServices;
    private String mediaSource = "";//todo select file from computer

    public UIController(HostServices hostServices) {//Intellij can't see this but works
        this.hostServices = hostServices;
    }

    @FXML
    public Label testLabel;

    @FXML
    public Button testButton;

    @FXML
    public MediaView media;

    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize() {
        this.runMedia();
        this.testButton.setOnAction(actionEvent -> {
            this.testLabel.setText(this.hostServices.getDocumentBase());
        });
    }

    public void runMedia() {
        URL resource = UIController.class.getResource("/video/VID_20191004_211955.mp4");//todo select file from computer
        this.mediaPlayer = new MediaPlayer(new Media(resource.toExternalForm()));
        this.mediaPlayer.setAutoPlay(true);
        this.media.setMediaPlayer(mediaPlayer);
    }
}
