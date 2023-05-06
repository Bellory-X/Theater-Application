package com.example.theater.controller.employee;

import com.example.theater.dto.employee.WorkerDTO;
import com.example.theater.dto.employee.category.WorkerCategoryDTO;
import com.example.theater.dto.employee.character.CharactersWorkerDTO;
import com.example.theater.dto.employee.character.WorkerCharacterDTO;
import com.example.theater.service.employee.WorkerService;
import com.example.theater.service.employee.category.WorkerCategoryService;
import com.example.theater.service.employee.character.CharactersWorkerService;
import com.example.theater.service.employee.character.WorkerCharacterService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@FxmlView("/controller/employee/worker-view.fxml")
public class WorkerController {
    @FXML private TableView<CharactersWorkerDTO> charactersTable;
    @FXML private TableView<WorkerCharacterDTO> characterTable;
    @FXML private TableView<WorkerCategoryDTO> categoryTable;
    @FXML private Button drop;
    @FXML private Button edit;
    @FXML private Button add;
    @FXML private TextField name;
    @FXML private TableView<WorkerDTO> workerTable;
    @FXML private Button worker;
    @FXML private Button category;
    @FXML private Button character;
    @FXML private Button characters;
    @FXML private Button close;
    @FXML private Button back;
    private final WorkerService workerService;
    private final WorkerCategoryService workerCategoryService;
    private final WorkerCharacterService workerCharacterService;
    private final CharactersWorkerService charactersWorkerService;
    private final FxWeaver fxWeaver;
    private Status status = Status.WORKER;

    public WorkerController(WorkerService workerService, WorkerCategoryService workerCategoryService, WorkerCharacterService workerCharacterService,
                            CharactersWorkerService charactersWorkerService, FxWeaver fxWeaver) {
        this.workerCategoryService = workerCategoryService;
        this.workerCharacterService = workerCharacterService;
        this.charactersWorkerService = charactersWorkerService;
        this.workerService = workerService;
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        initWorkerTable();
        initCategoryTable();
        initCharacterTable();
        initCharactersTable();
        clickButton();
    }

    private void clickButton() {
        worker.setOnAction(event -> {});
        category.setOnAction(event -> {});
        character.setOnAction(event -> {});
        characters.setOnAction(event -> {});
        back.setOnAction(event -> {});
        close.setOnAction(event -> close.getScene().getWindow().hide());
    }

    private void initWorkerTable() {
        TableColumn<WorkerDTO, String> column1 = new TableColumn<>("Full Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        workerTable.getColumns().add(column1);

        TableColumn<WorkerDTO, Integer> column2 = new TableColumn<>("Experience");
        column2.setCellValueFactory(new PropertyValueFactory<>("experience"));
        workerTable.getColumns().add(column2);

        TableColumn<WorkerDTO, String> column3 = new TableColumn<>("Gender");
        column3.setCellValueFactory(new PropertyValueFactory<>("gender"));
        workerTable.getColumns().add(column3);

        TableColumn<WorkerDTO, Date> column4 = new TableColumn<>("Birthday");
        column4.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        workerTable.getColumns().add(column4);

        TableColumn<WorkerDTO, Integer> column5 = new TableColumn<>("Count child");
        column5.setCellValueFactory(new PropertyValueFactory<>("countChild"));
        workerTable.getColumns().add(column5);

        TableColumn<WorkerDTO, Integer> column6 = new TableColumn<>("Salary");
        column6.setCellValueFactory(new PropertyValueFactory<>("salary"));
        workerTable.getColumns().add(column6);

        TableColumn<WorkerDTO, Boolean> column7 = new TableColumn<>("Is Worker");
        column7.setCellValueFactory(new PropertyValueFactory<>("isWorker"));
        workerTable.getColumns().add(column7);

        TableColumn<WorkerDTO, String> column8 = new TableColumn<>("Theater");
        column8.setCellValueFactory(new PropertyValueFactory<>("theater"));
        workerTable.getColumns().add(column8);

        TableColumn<WorkerDTO, String> column9 = new TableColumn<>("Category");
        column9.setCellValueFactory(new PropertyValueFactory<>("category"));
        workerTable.getColumns().add(column9);

        workerTable.setItems(FXCollections.observableList(workerService.getAll()));
    }

    private void initCategoryTable() {
        TableColumn<WorkerCategoryDTO, String> column = new TableColumn<>("Category");
        column.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryTable.getColumns().add(column);

        categoryTable.setItems(FXCollections.observableList(workerCategoryService.getAll()));
    }

    private void initCharacterTable() {
        TableColumn<WorkerCharacterDTO, String> column = new TableColumn<>("Character");
        column.setCellValueFactory(new PropertyValueFactory<>("character"));
        characterTable.getColumns().add(column);

        characterTable.setItems(FXCollections.observableList(workerCharacterService.getAll()));
    }

    private void initCharactersTable() {
        TableColumn<CharactersWorkerDTO, String> column = new TableColumn<>("Character");
        column.setCellValueFactory(new PropertyValueFactory<>("character"));
        charactersTable.getColumns().add(column);

//        charactersTable.setItems(FXCollections.observableList(charactersWorkerService.getAll()));
    }

    public enum Status {
        WORKER,
        CATEGORY,
        CHARACTER,
        CHARACTERS
    }
}
