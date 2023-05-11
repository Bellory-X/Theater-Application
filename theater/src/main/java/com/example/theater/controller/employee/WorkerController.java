package com.example.theater.controller.employee;

import com.example.theater.dao.entity.employee.Worker;
import com.example.theater.dao.entity.employee.character.CharactersWorker;
import com.example.theater.dto.employee.MusicianDTO;
import com.example.theater.dto.employee.WorkerDTO;
import com.example.theater.dto.employee.category.MusicianCategoryDTO;
import com.example.theater.dto.employee.category.WorkerCategoryDTO;
import com.example.theater.dto.employee.character.CharactersMusicianDTO;
import com.example.theater.dto.employee.character.CharactersWorkerDTO;
import com.example.theater.dto.employee.character.MusicianCharacterDTO;
import com.example.theater.dto.employee.character.WorkerCharacterDTO;
import com.example.theater.service.employee.MusicianService;
import com.example.theater.service.employee.WorkerService;
import com.example.theater.service.employee.category.MusicianCategoryService;
import com.example.theater.service.employee.category.WorkerCategoryService;
import com.example.theater.service.employee.character.CharactersMusicianService;
import com.example.theater.service.employee.character.CharactersWorkerService;
import com.example.theater.service.employee.character.MusicianCharacterService;
import com.example.theater.service.employee.character.WorkerCharacterService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@FxmlView("/controller/employee/worker-view.fxml")
public class WorkerController {
    public Button back;
    public Button close;
    public Button characters;
    public Button character;
    public Button category;
    public Button actor;
    public Button search;
    public Button add;
    public Button edit;
    public Button drop;
    public TableView<WorkerDTO> actorTable;
    public TableView<WorkerCategoryDTO> categoryTable;
    public TableView<WorkerCharacterDTO> characterTable;
    public TableView<CharactersWorkerDTO> charactersTable;
    public ListView<String> queries;
    public TextField result;
    public TextField searchField1;
    public Text searchText1;
    public TextField searchField2;
    public Text searchText2;
    public TextField searchField3;
    public Text searchText3;
    public DatePicker searchField4;
    public Text searchText4;
    public TextField searchField5;
    public Text searchText5;
    public TextField searchField6;
    public Text searchText6;
    public TextField addField1;
    public Text addText1;
    public TextField addField2;
    public Text addText2;
    public TextField addField3;
    public Text addText3;
    public DatePicker addField4;
    public Text addText4;
    public TextField addField5;
    public Text addText5;
    public TextField addField6;
    public Text addText6;
    public TextField addField7;
    public Text addText7;
    public TextField addField8;
    public Text addText8;
    private final WorkerService directorService;
    private final WorkerCategoryService directorCategoryService;
    private final WorkerCharacterService directorCharacterService;
    private final CharactersWorkerService charactersDirectorService;
    private final FxWeaver fxWeaver;

    public WorkerController(WorkerService directorService, WorkerCategoryService directorCategoryService,
                            WorkerCharacterService directorCharacterService,
                              CharactersWorkerService charactersDirectorService, FxWeaver fxWeaver) {
        this.directorService = directorService;
        this.directorCategoryService = directorCategoryService;
        this.directorCharacterService = directorCharacterService;
        this.charactersDirectorService = charactersDirectorService;
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
        drop.setOnAction(event -> {});
        edit.setOnAction(event -> {});
        add.setOnAction(event -> {});
        actor.setOnAction(event -> {});
        category.setOnAction(event -> {});
        character.setOnAction(event -> {});
        characters.setOnAction(event -> {});
        back.setOnAction(event -> {});
        close.setOnAction(event -> close.getScene().getWindow().hide());
        search.setOnAction(event -> {});
    }

    private void initWorkerTable() {
        TableColumn<WorkerDTO, String> column1 = new TableColumn<>("Full Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        actorTable.getColumns().add(column1);

        TableColumn<WorkerDTO, Integer> column2 = new TableColumn<>("Experience");
        column2.setCellValueFactory(new PropertyValueFactory<>("experience"));
        actorTable.getColumns().add(column2);

        TableColumn<WorkerDTO, String> column3 = new TableColumn<>("Gender");
        column3.setCellValueFactory(new PropertyValueFactory<>("gender"));
        actorTable.getColumns().add(column3);

        TableColumn<WorkerDTO, Date> column4 = new TableColumn<>("Birthday");
        column4.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        actorTable.getColumns().add(column4);

        TableColumn<WorkerDTO, Integer> column5 = new TableColumn<>("Count child");
        column5.setCellValueFactory(new PropertyValueFactory<>("countChild"));
        actorTable.getColumns().add(column5);

        TableColumn<WorkerDTO, Integer> column6 = new TableColumn<>("Salary");
        column6.setCellValueFactory(new PropertyValueFactory<>("salary"));
        actorTable.getColumns().add(column6);

        TableColumn<WorkerDTO, Boolean> column7 = new TableColumn<>("Is Worker");
        column7.setCellValueFactory(new PropertyValueFactory<>("isWorker"));
        actorTable.getColumns().add(column7);

        TableColumn<WorkerDTO, String> column8 = new TableColumn<>("Theater");
        column8.setCellValueFactory(new PropertyValueFactory<>("theater"));
        actorTable.getColumns().add(column8);

        TableColumn<WorkerDTO, String> column9 = new TableColumn<>("Category");
        column9.setCellValueFactory(new PropertyValueFactory<>("category"));
        actorTable.getColumns().add(column9);

        actorTable.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            WorkerDTO dto = actorTable.getSelectionModel().getSelectedItem();
            List<CharactersWorkerDTO> charactersList = charactersDirectorService.getAll().stream()
                    .filter(el -> el.getIdEmployee() == dto.getIdEmployee()).toList();
            charactersTable.getItems().clear();
            charactersTable.getItems().addAll(charactersList);
            charactersTable.refresh();
        });

        actorTable.setItems(FXCollections.observableList(directorService.getAll()));
    }

    private void initCategoryTable() {
        TableColumn<WorkerCategoryDTO, String> column = new TableColumn<>("Category");
        column.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryTable.getColumns().add(column);

        categoryTable.setItems(FXCollections.observableList(directorCategoryService.getAll()));
    }

    private void initCharacterTable() {
        TableColumn<WorkerCharacterDTO, String> column = new TableColumn<>("Character");
        column.setCellValueFactory(new PropertyValueFactory<>("character"));
        characterTable.getColumns().add(column);

        characterTable.setItems(FXCollections.observableList(directorCharacterService.getAll()));
    }

    private void initCharactersTable() {
        TableColumn<CharactersWorkerDTO, String> column = new TableColumn<>("Character");
        column.setCellValueFactory(new PropertyValueFactory<>("character"));
        charactersTable.getColumns().add(column);
    }

    public enum Status {
        ACTOR,
        CATEGORY,
        CHARACTER,
        CHARACTERS,
        RANK,
        RANKS
    }
}
