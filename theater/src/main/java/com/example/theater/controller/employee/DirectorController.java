package com.example.theater.controller.employee;

import com.example.theater.dao.entity.employee.Director;
import com.example.theater.dto.employee.DirectorDTO;
import com.example.theater.dto.employee.category.ActorCategoryDTO;
import com.example.theater.dto.employee.category.DirectorCategoryDTO;
import com.example.theater.dto.employee.character.ActorCharacterDTO;
import com.example.theater.dto.employee.character.CharactersActorDTO;
import com.example.theater.dto.employee.character.CharactersDirectorDTO;
import com.example.theater.dto.employee.character.DirectorCharacterDTO;
import com.example.theater.service.employee.DirectorService;
import com.example.theater.service.employee.category.DirectorCategoryService;
import com.example.theater.service.employee.character.CharactersDirectorService;
import com.example.theater.service.employee.character.DirectorCharacterService;
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
@FxmlView("/controller/employee/director-view.fxml")
public class DirectorController {
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
    public TableView<DirectorDTO> actorTable;
    public TableView<DirectorCategoryDTO> categoryTable;
    public TableView<DirectorCharacterDTO> characterTable;
    public TableView<CharactersDirectorDTO> charactersTable;
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
    private final DirectorService directorService;
    private final DirectorCategoryService directorCategoryService;
    private final DirectorCharacterService directorCharacterService;
    private final CharactersDirectorService charactersDirectorService;
    private final FxWeaver fxWeaver;

    public DirectorController(DirectorService directorService, DirectorCategoryService directorCategoryService,
                              DirectorCharacterService directorCharacterService,
                              CharactersDirectorService charactersDirectorService, FxWeaver fxWeaver) {
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
    }

    private void initWorkerTable() {
        TableColumn<DirectorDTO, String> column1 = new TableColumn<>("Full Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        actorTable.getColumns().add(column1);

        TableColumn<DirectorDTO, Integer> column2 = new TableColumn<>("Experience");
        column2.setCellValueFactory(new PropertyValueFactory<>("experience"));
        actorTable.getColumns().add(column2);

        TableColumn<DirectorDTO, String> column3 = new TableColumn<>("Gender");
        column3.setCellValueFactory(new PropertyValueFactory<>("gender"));
        actorTable.getColumns().add(column3);

        TableColumn<DirectorDTO, Date> column4 = new TableColumn<>("Birthday");
        column4.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        actorTable.getColumns().add(column4);

        TableColumn<DirectorDTO, Integer> column5 = new TableColumn<>("Count child");
        column5.setCellValueFactory(new PropertyValueFactory<>("countChild"));
        actorTable.getColumns().add(column5);

        TableColumn<DirectorDTO, Integer> column6 = new TableColumn<>("Salary");
        column6.setCellValueFactory(new PropertyValueFactory<>("salary"));
        actorTable.getColumns().add(column6);

        TableColumn<DirectorDTO, Boolean> column7 = new TableColumn<>("Is Worker");
        column7.setCellValueFactory(new PropertyValueFactory<>("isWorker"));
        actorTable.getColumns().add(column7);

        TableColumn<DirectorDTO, String> column8 = new TableColumn<>("Theater");
        column8.setCellValueFactory(new PropertyValueFactory<>("theater"));
        actorTable.getColumns().add(column8);

        TableColumn<DirectorDTO, String> column9 = new TableColumn<>("Category");
        column9.setCellValueFactory(new PropertyValueFactory<>("category"));
        actorTable.getColumns().add(column9);

        actorTable.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            DirectorDTO dto = actorTable.getSelectionModel().getSelectedItem();
            List<CharactersDirectorDTO> charactersList = charactersDirectorService.getAll().stream()
                    .filter(el -> el.getIdEmployee() == dto.getIdEmployee()).toList();
            charactersTable.getItems().clear();
            charactersTable.getItems().addAll(charactersList);
            charactersTable.refresh();
        });

        actorTable.setItems(FXCollections.observableList(directorService.getAll()));
    }

    private void initCategoryTable() {
        TableColumn<DirectorCategoryDTO, String> column = new TableColumn<>("Category");
        column.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryTable.getColumns().add(column);

        categoryTable.setItems(FXCollections.observableList(directorCategoryService.getAll()));
    }

    private void initCharacterTable() {
        TableColumn<DirectorCharacterDTO, String> column = new TableColumn<>("Character");
        column.setCellValueFactory(new PropertyValueFactory<>("character"));
        characterTable.getColumns().add(column);

        characterTable.setItems(FXCollections.observableList(directorCharacterService.getAll()));
    }

    private void initCharactersTable() {
        TableColumn<CharactersDirectorDTO, String> column = new TableColumn<>("Character");
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
