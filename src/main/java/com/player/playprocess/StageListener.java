package com.player.playprocess;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;

@Component
public class StageListener implements ApplicationListener<StageReadyEvent> {

    private final String appTitle;
    private final Resource fxml;
    private final ApplicationContext applicationContext;

    public StageListener(@Value("${spring.application.ui.title}") String appTitle,
                         @Value("classpath:/fxml/ui.fxml") Resource resource,
                         ApplicationContext applicationContext) {
        this.appTitle = appTitle;
        this.fxml = resource;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent stageReadyEvent) {
        try {
            Stage stage = stageReadyEvent.getStage();
            URL url = this.fxml.getURL();
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> aClass) {
                    return applicationContext.getBean(aClass);
                }
            });
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root, 800, 600);
            stage.setScene(scene);
            stage.setTitle(this.appTitle);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();//todo
        }
    }
}
