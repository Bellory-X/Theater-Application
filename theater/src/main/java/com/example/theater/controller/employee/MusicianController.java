package com.example.theater.controller.employee;

import com.example.theater.service.employee.MusicianService;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/controller/employee/musician-view.fxml")
public class MusicianController {
    /*TODO:
       1.Получить список и общее число*/

    /*TODO:
     *  Category
     *  Character
     *  Characters*/

    private final MusicianService musicianService;
    private final FxWeaver fxWeaver;

    public MusicianController(MusicianService musicianService, FxWeaver fxWeaver) {
        this.musicianService = musicianService;
        this.fxWeaver = fxWeaver;
    }
}
