package com.example.theater.controller.employee;

import com.example.theater.service.employee.WorkerService;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/controller/employee/worker-view.fxml")
public class WorkerController {
    /*TODO:
     *  Category
     *  Character
     *  Characters*/

    private final WorkerService workerService;
    private final FxWeaver fxWeaver;

    public WorkerController(WorkerService workerService, FxWeaver fxWeaver) {
        this.workerService = workerService;
        this.fxWeaver = fxWeaver;
    }
}
