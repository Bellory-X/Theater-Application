package com.example.theater.controller.employee;

import com.example.theater.dto.employee.EmployeeDTO;
import com.example.theater.service.employee.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/controller/employee/employee-view.fxml")
public class EmployeeController {
    /*TODO:
       1.Получить список и общее число всех работников театра*/
    @FXML private Button employee;
    @FXML private TextField salary;
    @FXML private TextField countChild;
    @FXML private TextField gender;
    @FXML private TextField experience;
    @FXML private DatePicker birthday;
    @FXML private TextField theater;
    @FXML private ListView<String> queries;
    @FXML private Button search;
    @FXML private Button actor;
    @FXML private Button musician;
    @FXML private Button director;
    @FXML private Button worker;
    @FXML private Button back;
    @FXML private Button close;
    @FXML private TableView <EmployeeDTO> table;
    private final EmployeeService employeeService;
    private final FxWeaver fxWeaver;

    public EmployeeController(EmployeeService employeeService, FxWeaver fxWeaver) {
        this.employeeService = employeeService;
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        initTable();
        clickButton();
    }

    private void clickButton() {
        search.setOnAction(event -> {});
        employee.setOnAction(event -> {
            table.setItems(FXCollections.observableList(employeeService.getAll()));
            table.refresh();
        });
        actor.setOnAction(event -> showNewStage(fxWeaver.loadView(ActorController.class)));
        musician.setOnAction(event -> showNewStage(fxWeaver.loadView(MusicianController.class)));
        director.setOnAction(event -> showNewStage(fxWeaver.loadView(DirectorController.class)));
        worker.setOnAction(event -> showNewStage(fxWeaver.loadView(WorkerController.class)));
        back.setOnAction(event -> {});
        close.setOnAction(event -> close.getScene().getWindow().hide());
    }

    private void showNewStage(Parent parent) {
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private void initTable() {
        TableColumn<EmployeeDTO, String> column1 = new TableColumn<>("Full Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        table.getColumns().add(column1);

        TableColumn<EmployeeDTO, String> column2 = new TableColumn<>("Experience");
        column2.setCellValueFactory(new PropertyValueFactory<>("experience"));
        table.getColumns().add(column2);

        TableColumn<EmployeeDTO, String> column3 = new TableColumn<>("Gender");
        column3.setCellValueFactory(new PropertyValueFactory<>("gender"));
        table.getColumns().add(column3);

        TableColumn<EmployeeDTO, String> column4 = new TableColumn<>("Birthday");
        column4.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        table.getColumns().add(column4);

        TableColumn<EmployeeDTO, String> column5 = new TableColumn<>("Count child");
        column5.setCellValueFactory(new PropertyValueFactory<>("countChild"));
        table.getColumns().add(column5);

        TableColumn<EmployeeDTO, String> column6 = new TableColumn<>("Salary");
        column6.setCellValueFactory(new PropertyValueFactory<>("salary"));
        table.getColumns().add(column6);

        TableColumn<EmployeeDTO, String> column7 = new TableColumn<>("Is Worker");
        column7.setCellValueFactory(new PropertyValueFactory<>("isWorker"));
        table.getColumns().add(column7);

        TableColumn<EmployeeDTO, String> column8 = new TableColumn<>("Theater");
        column8.setCellValueFactory(new PropertyValueFactory<>("theater"));
        table.getColumns().add(column8);

        table.setItems(FXCollections.observableList(employeeService.getAll()));
    }
}
