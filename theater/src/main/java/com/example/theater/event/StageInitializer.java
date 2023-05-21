package com.example.theater.event;

import com.example.theater.controller.TheaterController;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    private final FxWeaver fxWeaver;
    private final String title;

    @Autowired
    public StageInitializer(@Value("${spring.application.ui.title}") String title, FxWeaver fxWeaver) {
        this.title = title;
        this.fxWeaver = fxWeaver;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        Stage stage = event.getStage();
        Scene scene = new Scene(fxWeaver.loadView(TheaterController.class));
        stage.setScene(scene);
        stage.setTitle(title);
        stage.show();
    }
}
