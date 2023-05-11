package com.example.theater.controller.employee;

import com.example.theater.dto.employee.MusicianDTO;
import com.example.theater.dto.employee.category.MusicianCategoryDTO;
import com.example.theater.dto.employee.character.CharactersMusicianDTO;
import com.example.theater.dto.employee.character.MusicianCharacterDTO;
import com.example.theater.service.employee.MusicianService;
import com.example.theater.service.employee.category.MusicianCategoryService;
import com.example.theater.service.employee.character.CharactersMusicianService;
import com.example.theater.service.employee.character.MusicianCharacterService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
@FxmlView("/controller/employee/musician-view.fxml")
public class MusicianController {
    @FXML private Text searchText11;
    @FXML private DatePicker searchField11;
    @FXML private Text searchText10;
    @FXML private TextField searchField10;
    @FXML private Text searchText9;
    @FXML private TextField searchField9;
    @FXML private Text searchText8;
    @FXML private Text searchText7;
    @FXML private DatePicker searchField6;
    @FXML private TextField searchField4;
    @FXML private TextField addField1;
    @FXML private Text addText1;
    @FXML private TextField addField2;
    @FXML private Text addText2;
    @FXML private TextField addField3;
    @FXML private Text addText3;
    @FXML private DatePicker addField4;
    @FXML private Text addText4;
    @FXML private TextField addField5;
    @FXML private Text addText5;
    @FXML private TextField addField6;
    @FXML private Text addText6;
    @FXML private TextField addField7;
    @FXML private Text addText7;
    @FXML private TextField addField8;
    @FXML private Text addText8;
    @FXML private TextField result;
    @FXML private Text searchText1;
    @FXML private Text searchText2;
    @FXML private Text searchText3;
    @FXML private Text searchText4;
    @FXML private Text searchText5;
    @FXML private Text searchText6;
    @FXML private TableView<CharactersMusicianDTO> charactersTable;
    @FXML private TableView<MusicianCharacterDTO> characterTable;
    @FXML private TableView<MusicianCategoryDTO> categoryTable;
    @FXML private TableView<MusicianDTO> actorTable;
    @FXML private TextField searchField8;
    @FXML private TextField searchField7;
    @FXML private TextField searchField3;
    @FXML private TextField searchField2;
    @FXML private DatePicker searchField5;
    @FXML private TextField searchField1;
    @FXML private ListView<String> queries;
    @FXML private Button search;
    @FXML private Button actor;
    @FXML private Button category;
    @FXML private Button character;
    @FXML private Button characters;
    @FXML private Button close;
    @FXML private Button back;
    @FXML private Button drop;
    @FXML private Button edit;
    @FXML private Button add;
    private final MusicianService directorService;
    private final MusicianCategoryService directorCategoryService;
    private final MusicianCharacterService directorCharacterService;
    private final CharactersMusicianService charactersDirectorService;
    private final FxWeaver fxWeaver;
    private QueryStatus queryStatus = QueryStatus.QUERY1;

    public MusicianController(MusicianService directorService, MusicianCategoryService directorCategoryService,
                              MusicianCharacterService directorCharacterService,
                               CharactersMusicianService charactersDirectorService, FxWeaver fxWeaver) {
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
        initQueryList();
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
        search.setOnAction(event -> {
            actorTable.getItems().clear();
            switch (queryStatus) {
                case QUERY0 -> {
                    List<MusicianDTO> dtoList = directorService.getAll();
                    result.setText(String.valueOf(dtoList.size()));
                    actorTable.getItems().addAll(dtoList);
                }
                case QUERY1 -> {
                    List<MusicianDTO> dtoList = directorService
                            .findActorQuery1(searchField1.getText(), Integer.parseInt(searchField2.getText()),
                                    Integer.parseInt(searchField3.getText()), searchField4.getText(),
                                    Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                    Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                    Integer.parseInt(searchField7.getText()), Integer.parseInt(searchField8.getText()),
                                    Integer.parseInt(searchField9.getText()), Integer.parseInt(searchField10.getText()));
                    result.setText(String.valueOf(dtoList.size()));
                    actorTable.getItems().addAll(dtoList);
                }
            }
            actorTable.refresh();
        });
    }

    private void initQueryList() {
        queries.getItems().add("Получить всех музыкантов");
        queries.getItems().add("Получить музыкантов, по атрибутам работника");
        queries.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (queries.getSelectionModel().getSelectedItem().equals("Получить всех музыкантов")) {
                queryStatus = QueryStatus.QUERY0;
            }
            if (queries.getSelectionModel().getSelectedItem().equals("Получить музыкантов, по атрибутам работника")) {
                queryStatus = QueryStatus.QUERY1;
                searchField4.setVisible(true);
                searchField5.setVisible(true);
                searchField6.setVisible(true);
                searchField7.setVisible(true);
                searchField8.setVisible(true);
                searchField9.setVisible(true);
                searchField10.setVisible(true);
                searchField11.setVisible(false);
                searchText4.setVisible(true);
                searchText5.setVisible(true);
                searchText6.setVisible(true);
                searchText7.setVisible(true);
                searchText8.setVisible(true);
                searchText9.setVisible(true);
                searchText10.setVisible(true);
                searchText11.setVisible(false);
                searchText1.setText("theater");
                searchText2.setText("from exp");
                searchText3.setText("before exp");
                searchText4.setText("gender");
                searchText5.setText("from birthday");
                searchText6.setText("before birthday");
                searchText7.setText("from countChild");
                searchText8.setText("before countChild");
                searchText9.setText("from salary");
                searchText10.setText("before salary");
            }
        });
    }

    private void initWorkerTable() {
        TableColumn<MusicianDTO, String> column1 = new TableColumn<>("Full Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        actorTable.getColumns().add(column1);

        TableColumn<MusicianDTO, Integer> column2 = new TableColumn<>("Experience");
        column2.setCellValueFactory(new PropertyValueFactory<>("experience"));
        actorTable.getColumns().add(column2);

        TableColumn<MusicianDTO, String> column3 = new TableColumn<>("Gender");
        column3.setCellValueFactory(new PropertyValueFactory<>("gender"));
        actorTable.getColumns().add(column3);

        TableColumn<MusicianDTO, Date> column4 = new TableColumn<>("Birthday");
        column4.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        actorTable.getColumns().add(column4);

        TableColumn<MusicianDTO, Integer> column5 = new TableColumn<>("Count child");
        column5.setCellValueFactory(new PropertyValueFactory<>("countChild"));
        actorTable.getColumns().add(column5);

        TableColumn<MusicianDTO, Integer> column6 = new TableColumn<>("Salary");
        column6.setCellValueFactory(new PropertyValueFactory<>("salary"));
        actorTable.getColumns().add(column6);

        TableColumn<MusicianDTO, Boolean> column7 = new TableColumn<>("Is Worker");
        column7.setCellValueFactory(new PropertyValueFactory<>("isWorker"));
        actorTable.getColumns().add(column7);

        TableColumn<MusicianDTO, String> column8 = new TableColumn<>("Theater");
        column8.setCellValueFactory(new PropertyValueFactory<>("theater"));
        actorTable.getColumns().add(column8);

        TableColumn<MusicianDTO, String> column9 = new TableColumn<>("Category");
        column9.setCellValueFactory(new PropertyValueFactory<>("category"));
        actorTable.getColumns().add(column9);

        actorTable.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            MusicianDTO dto = actorTable.getSelectionModel().getSelectedItem();
            List<CharactersMusicianDTO> charactersList = charactersDirectorService.getAll().stream()
                    .filter(el -> el.getIdEmployee() == dto.getIdEmployee()).toList();
            charactersTable.getItems().clear();
            charactersTable.getItems().addAll(charactersList);
            charactersTable.refresh();
        });

        actorTable.setItems(FXCollections.observableList(directorService.getAll()));
    }

    private void initCategoryTable() {
        TableColumn<MusicianCategoryDTO, String> column = new TableColumn<>("Category");
        column.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryTable.getColumns().add(column);

        categoryTable.setItems(FXCollections.observableList(directorCategoryService.getAll()));
    }

    private void initCharacterTable() {
        TableColumn<MusicianCharacterDTO, String> column = new TableColumn<>("Character");
        column.setCellValueFactory(new PropertyValueFactory<>("character"));
        characterTable.getColumns().add(column);

        characterTable.setItems(FXCollections.observableList(directorCharacterService.getAll()));
    }

    private void initCharactersTable() {
        TableColumn<CharactersMusicianDTO, String> column = new TableColumn<>("Character");
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

    public enum QueryStatus {
        QUERY0,
        QUERY1
    }
}
