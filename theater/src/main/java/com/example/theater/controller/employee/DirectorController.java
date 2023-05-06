package com.example.theater.controller.employee;

import com.example.theater.service.employee.DirectorService;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/controller/employee/director-view.fxml")
public class DirectorController {
    /*TODO:
       8.Получить список, пpиезжавших на гастpоли, уезжавших на гастpоли*/

    /*TODO:
    *  Category
    *  Character
    *  Characters*/

    private final DirectorService directorService;
    private final FxWeaver fxWeaver;

    public DirectorController(DirectorService directorService, FxWeaver fxWeaver) {
        this.directorService = directorService;
        this.fxWeaver = fxWeaver;
    }
}
