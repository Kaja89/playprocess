package com.player.playprocess.controller;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import org.springframework.stereotype.Controller;

import java.net.URL;

@Controller
public class VideoController {

    @FXML
    public MediaView video;

    @FXML
    public Button playVideo;

    @FXML
    public Button pauseVideo;

    @FXML
    public Slider videoSlider;

    private MediaPlayer mediaPlayer;

    public VideoController() {
    }

    @FXML
    public void initialize() {
        this.runMedia();
    }

    public void runMedia() {
        URL resource = UIController.class.getResource("/video/VID_20191004_211955.mp4");//todo select file from computer
        this.mediaPlayer = new MediaPlayer(new Media(resource.toExternalForm()));
        this.video.setMediaPlayer(this.mediaPlayer);
        DoubleProperty height = this.video.fitHeightProperty();
        DoubleProperty width = this.video.fitWidthProperty();
        width.bind(Bindings.selectDouble(this.video.sceneProperty(), "width"));
        height.bind(Bindings.selectDouble(this.video.sceneProperty(), "height"));

        this.mediaPlayer.setOnEndOfMedia(() -> {
            this.videoSlider.setValue(0.0);
        });

        this.mediaPlayer.currentTimeProperty().addListener((observableValue, oldTime, newTime) -> {
            System.out.println(newTime);
            this.videoSlider.setValue(mediaPlayer.getCurrentTime().toSeconds());
        });
    }

    @FXML
    public void playVideo() {//todo do it in better way
        this.videoSlider.setMax(this.mediaPlayer.getTotalDuration().toSeconds());
        this.mediaPlayer.play();
    }

    @FXML
    public void pauseVideo() {
        this.mediaPlayer.pause();
    }
}
