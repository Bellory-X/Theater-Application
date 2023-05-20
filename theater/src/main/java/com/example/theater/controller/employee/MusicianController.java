package com.example.theater.controller.employee;

import com.example.theater.dao.entity.employee.Musician;
import com.example.theater.dto.employee.ActorDTO;
import com.example.theater.dto.employee.MusicianDTO;
import com.example.theater.dto.employee.category.ActorCategoryDTO;
import com.example.theater.dto.employee.category.MusicianCategoryDTO;
import com.example.theater.dto.employee.character.ActorCharacterDTO;
import com.example.theater.dto.employee.character.CharactersActorDTO;
import com.example.theater.dto.employee.character.CharactersMusicianDTO;
import com.example.theater.dto.employee.character.MusicianCharacterDTO;
import com.example.theater.dto.employee.rank.RankDTO;
import com.example.theater.dto.employee.rank.RanksActorDTO;
import com.example.theater.exception.ItemException;
import com.example.theater.exception.QueryException;
import com.example.theater.service.employee.ActorService;
import com.example.theater.service.employee.EmployeeService;
import com.example.theater.service.employee.MusicianService;
import com.example.theater.service.employee.category.ActorCategoryService;
import com.example.theater.service.employee.category.MusicianCategoryService;
import com.example.theater.service.employee.character.ActorCharacterService;
import com.example.theater.service.employee.character.CharactersActorService;
import com.example.theater.service.employee.character.CharactersMusicianService;
import com.example.theater.service.employee.character.MusicianCharacterService;
import com.example.theater.service.employee.rank.RankService;
import com.example.theater.service.employee.rank.RanksActorService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.*;

@Component
@FxmlView("/controller/employee/musician-view.fxml")
public class MusicianController {
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
    private final MusicianService actorService;
    private final MusicianCategoryService actorCategoryService;
    private final MusicianCharacterService actorCharacterService;
    private final CharactersMusicianService charactersActorService;
    private final FxWeaver fxWeaver;
    private TableStatus tableStatus = TableStatus.ACTOR;
    private QueryStatus queryStatus = QueryStatus.QUERY1;
    private final Map<QueryStatus, String> queryMap = new HashMap<>();

    public MusicianController(MusicianService actorService, MusicianCategoryService actorCategoryService,
                              MusicianCharacterService actorCharacterService,
                              CharactersMusicianService charactersActorService, FxWeaver fxWeaver) {
        this.actorService = actorService;
        this.actorCategoryService = actorCategoryService;
        this.actorCharacterService = actorCharacterService;
        this.charactersActorService = charactersActorService;
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        queryMap.put(QueryStatus.QUERY0, "Получить всех служащих");
        queryMap.put(QueryStatus.QUERY1, "Получить служащих, по атрибутам работника");
//        queryMap.put(QueryStatus.QUERY2, "Получить актеpов по атрибутам звания");
        initWorkerTable();
        initCategoryTable();
        initCharacterTable();
//        initRankTable();
//        initRanksTable();
        initCharactersTable();
        initQueryList();
        clickButton();
    }

    private void dropEvent() {
        try {
            switch (tableStatus) {
                case ACTOR -> {
                    if (actorTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("null");
                    actorService.drop(actorTable.getSelectionModel().getSelectedItem());
                    actorTable.setItems(FXCollections.observableList(actorService.getAll()));
                    actorTable.refresh();
                }
                case CATEGORY -> {
                    if (categoryTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("null");
                    actorCategoryService.drop(categoryTable.getSelectionModel().getSelectedItem());
                    categoryTable.setItems(FXCollections.observableList(actorCategoryService.getAll()));
                    categoryTable.refresh();
                }
                case CHARACTER -> {
                    if (characterTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("null");
                    actorCharacterService.drop(characterTable.getSelectionModel().getSelectedItem());
                    characterTable.setItems(FXCollections.observableList(actorCharacterService.getAll()));
                    characterTable.refresh();
                }
                case CHARACTERS -> {
                    if (charactersTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("null");
                    charactersActorService.drop(charactersTable.getSelectionModel().getSelectedItem());

                    MusicianDTO dto = actorTable.getSelectionModel().getSelectedItem();
                    List<CharactersMusicianDTO> charactersList = charactersActorService.getAll().stream()
                            .filter(el -> el.getIdEmployee() == dto.getIdEmployee()).toList();
                    charactersTable.getItems().clear();
                    charactersTable.getItems().addAll(charactersList);
                    charactersTable.refresh();
                }
            }
            result.setText("Success");
        } catch (NumberFormatException e) {
            result.setText("In one of the fields not number");
        } catch (QueryException e) {
            result.setText("Recheck fields maybe it exists");
        } catch (ItemException e) {
            result.setText("select record");
        }
    }

    private void editEvent() {
        try {
            switch (tableStatus) {
                case ACTOR -> {
                    if (actorTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("null");
                    actorService.edit(MusicianDTO.builder()
                            .idEmployee(actorTable.getSelectionModel().getSelectedItem().getIdEmployee())
                            .fullName(addField1.getText())
                            .experience(Integer.parseInt(addField2.getText()))
                            .gender(addField3.getText())
                            .birthday(Date.from(addField4.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                            .countChild(Integer.parseInt(addField5.getText()))
                            .salary(Integer.parseInt(addField6.getText()))
                            .worker(Boolean.parseBoolean(addField7.getText()))
                            .theater(addField8.getText())
                            .category(categoryTable.getSelectionModel().getSelectedItem().getCategory())
                            .build());
                    actorTable.setItems(FXCollections.observableList(actorService.getAll()));
                    actorTable.refresh();
                }
                case CATEGORY -> {
                    if (categoryTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("null");
                    actorCategoryService.edit(MusicianCategoryDTO.builder()
                            .id(categoryTable.getSelectionModel().getSelectedItem().getId())
                            .category(addField1.getText())
                            .build());
                    categoryTable.setItems(FXCollections.observableList(actorCategoryService.getAll()));
                    categoryTable.refresh();
                }
                case CHARACTER -> {
                    if (characterTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("null");
                    actorCharacterService.edit(MusicianCharacterDTO.builder()
                            .id(characterTable.getSelectionModel().getSelectedItem().getId())
                            .character(addField1.getText())
                            .build());
                    characterTable.setItems(FXCollections.observableList(actorCharacterService.getAll()));
                    characterTable.refresh();
                }
                case CHARACTERS -> {
                    if (characterTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("null");
                    charactersActorService.edit(CharactersMusicianDTO.builder()
                            .id(charactersTable.getSelectionModel().getSelectedItem().getId())
                            .idEmployee(actorTable.getSelectionModel().getSelectedItem().getIdEmployee())
                            .character(characterTable.getSelectionModel().getSelectedItem().getCharacter())
                            .build());

                    MusicianDTO dto = actorTable.getSelectionModel().getSelectedItem();
                    List<CharactersMusicianDTO> charactersList = charactersActorService.getAll().stream()
                            .filter(el -> el.getIdEmployee() == dto.getIdEmployee()).toList();
                    charactersTable.getItems().clear();
                    charactersTable.getItems().addAll(charactersList);
                    charactersTable.refresh();
                }
            }
            result.setText("Success");
        } catch (NumberFormatException e) {
            result.setText("experience or countChild or salary not positive number");
        } catch (QueryException e) {
            result.setText("Recheck fields maybe it exists");
        } catch (ItemException e) {
            result.setText("select record");
        }
    }

    private void addEvent() {
        try {
            switch (tableStatus) {
                case ACTOR -> {
                    actorService.add(MusicianDTO.builder().fullName(addField1.getText())
                            .experience(Integer.parseInt(addField2.getText()))
                            .gender(addField3.getText())
                            .birthday(Date.from(addField4.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                            .countChild(Integer.parseInt(addField5.getText()))
                            .salary(Integer.parseInt(addField6.getText()))
                            .worker(Boolean.parseBoolean(addField7.getText()))
                            .theater(addField8.getText())
                            .category(categoryTable.getSelectionModel().getSelectedItem().getCategory())
                            .build());
                    actorTable.setItems(FXCollections.observableList(actorService.getAll()));
                    actorTable.refresh();
                }
                case CATEGORY -> {
                    actorCategoryService.add(MusicianCategoryDTO.builder().category(addField1.getText()).build());
                    categoryTable.setItems(FXCollections.observableList(actorCategoryService.getAll()));
                    categoryTable.refresh();
                }
                case CHARACTER -> {
                    actorCharacterService.add(MusicianCharacterDTO.builder().character(addField1.getText()).build());
                    characterTable.setItems(FXCollections.observableList(actorCharacterService.getAll()));
                    characterTable.refresh();
                }
                case CHARACTERS -> {
                    if (actorTable.getSelectionModel().getSelectedItem() == null || characterTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("null");
                    charactersActorService.add(CharactersMusicianDTO.builder()
                            .idEmployee(actorTable.getSelectionModel().getSelectedItem().getIdEmployee())
                            .character(characterTable.getSelectionModel().getSelectedItem().getCharacter())
                            .build());

                    charactersTable.getItems().clear();
                    charactersTable.getItems().addAll(charactersActorService.getAll().stream()
                            .filter(el -> el.getIdEmployee() == actorTable.getSelectionModel().getSelectedItem()
                                    .getIdEmployee()).toList());
                    charactersTable.refresh();
                }
            }
            result.setText("Success");
        } catch (NumberFormatException e) {
            result.setText("experience or countChild or salary not positive number");
        } catch (QueryException e) {
            result.setText("Recheck fields maybe it exists");
        } catch (ItemException e) {
            result.setText("select record");
        }
    }

    private void clickButton() {
        drop.setOnAction(event -> dropEvent());
        edit.setOnAction(event -> editEvent());
        add.setOnAction(event -> addEvent());
        actor.setOnAction(event -> {
            tableStatus = TableStatus.ACTOR;
            addField1.setVisible(true);
            addField2.setVisible(true);
            addField3.setVisible(true);
            addField4.setVisible(true);
            addField5.setVisible(true);
            addField6.setVisible(true);
            addField7.setVisible(true);
            addField8.setVisible(true);
            addText1.setVisible(true);
            addText2.setVisible(true);
            addText3.setVisible(true);
            addText4.setVisible(true);
            addText5.setVisible(true);
            addText6.setVisible(true);
            addText7.setVisible(true);
            addText8.setVisible(true);
            addText1.setText("fullName");
            addText2.setText("experience");
            addText3.setText("gender");
            addText4.setText("birthday");
            addText5.setText("countChild");
            addText6.setText("salary");
            addText7.setText("worker");
            addText8.setText("theater");
        });
        category.setOnAction(event -> {
            tableStatus = TableStatus.CATEGORY;
            addField1.setVisible(true);
            addField2.setVisible(false);
            addField3.setVisible(false);
            addField4.setVisible(false);
            addField5.setVisible(false);
            addField6.setVisible(false);
            addField7.setVisible(false);
            addField8.setVisible(false);
            addText1.setVisible(true);
            addText2.setVisible(false);
            addText3.setVisible(false);
            addText4.setVisible(false);
            addText5.setVisible(false);
            addText6.setVisible(false);
            addText7.setVisible(false);
            addText8.setVisible(false);
            addText1.setText("category");
        });
        character.setOnAction(event -> {
            tableStatus = TableStatus.CHARACTER;
            addField1.setVisible(true);
            addField2.setVisible(false);
            addField3.setVisible(false);
            addField4.setVisible(false);
            addField5.setVisible(false);
            addField6.setVisible(false);
            addField7.setVisible(false);
            addField8.setVisible(false);
            addText1.setVisible(true);
            addText2.setVisible(false);
            addText3.setVisible(false);
            addText4.setVisible(false);
            addText5.setVisible(false);
            addText6.setVisible(false);
            addText7.setVisible(false);
            addText8.setVisible(false);
            addText1.setText("character");
        });
        characters.setOnAction(event -> {
            tableStatus = TableStatus.CHARACTERS;
            addField1.setVisible(false);
            addField2.setVisible(false);
            addField3.setVisible(false);
            addField4.setVisible(false);
            addField5.setVisible(false);
            addField6.setVisible(false);
            addField7.setVisible(false);
            addField8.setVisible(false);
            addText1.setVisible(false);
            addText2.setVisible(false);
            addText3.setVisible(false);
            addText4.setVisible(false);
            addText5.setVisible(false);
            addText6.setVisible(false);
            addText7.setVisible(false);
            addText8.setVisible(false);
        });
//        rank.setOnAction(event -> {
//            tableStatus = TableStatus.RANK;
//            addField1.setVisible(true);
//            addField2.setVisible(true);
//            addField3.setVisible(false);
//            addField4.setVisible(true);
//            addField5.setVisible(false);
//            addField6.setVisible(false);
//            addField7.setVisible(false);
//            addField8.setVisible(false);
//            addText1.setVisible(true);
//            addText2.setVisible(true);
//            addText3.setVisible(false);
//            addText4.setVisible(true);
//            addText5.setVisible(false);
//            addText6.setVisible(false);
//            addText7.setVisible(false);
//            addText8.setVisible(false);
//            addText1.setText("name");
//            addText2.setText("contest");
//            addText4.setText("data");
//        });
//        ranks.setOnAction(event -> {
//            tableStatus = TableStatus.RANKS;
//            addField1.setVisible(false);
//            addField2.setVisible(false);
//            addField3.setVisible(false);
//            addField4.setVisible(false);
//            addField5.setVisible(false);
//            addField6.setVisible(false);
//            addField7.setVisible(false);
//            addField8.setVisible(false);
//            addText1.setVisible(false);
//            addText2.setVisible(false);
//            addText3.setVisible(false);
//            addText4.setVisible(false);
//            addText5.setVisible(false);
//            addText6.setVisible(false);
//            addText7.setVisible(false);
//            addText8.setVisible(false);
//        });
        back.setOnAction(event -> showNewStage(fxWeaver.loadView(EmployeeController.class)));
        close.setOnAction(event -> close.getScene().getWindow().hide());
        search.setOnAction(event -> searchEvent());
    }

    private void searchEvent() {
        actorTable.getItems().clear();
        switch (queryStatus) {
            case QUERY0 -> {
                List<MusicianDTO> dtoList = actorService.getAll();
                result.setText(String.valueOf(dtoList.size()));
                actorTable.getItems().addAll(dtoList);
            }
            case QUERY1 -> {
                List<MusicianDTO> dtoList = actorService
                        .findActorQuery1(searchField1.getText(), Integer.parseInt(searchField2.getText()),
                                Integer.parseInt(searchField3.getText()), searchField4.getText(),
                                Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                Integer.parseInt(searchField7.getText()), Integer.parseInt(searchField8.getText()),
                                Integer.parseInt(searchField9.getText()), Integer.parseInt(searchField10.getText()));
                result.setText(String.valueOf(dtoList.size()));
                actorTable.getItems().addAll(dtoList);
            }
//            case QUERY2 -> {
//                List<ActorDTO> dtoList = actorService
//                        .findActorQuery2(searchField1.getText(),
//                                Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
//                                Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
//                                searchField2.getText(), searchField3.getText(),
//                                Date.from(searchField11.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
//                result.setText(String.valueOf(dtoList.size()));
//                actorTable.getItems().addAll(dtoList);
//            }
        }
        actorTable.refresh();
    }

    private void initQueryList() {
        queries.getItems().add(queryMap.get(QueryStatus.QUERY0));
        queries.getItems().add(queryMap.get(QueryStatus.QUERY1));
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
        queries.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(QueryStatus.QUERY0))) {
                queryStatus = QueryStatus.QUERY0;
            }
            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(QueryStatus.QUERY1))) {
                queryStatus = QueryStatus.QUERY1;
                searchField4.setVisible(true);
                searchField7.setVisible(true);
                searchField8.setVisible(true);
                searchField9.setVisible(true);
                searchField10.setVisible(true);
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
        column7.setCellValueFactory(new PropertyValueFactory<>("worker"));
        actorTable.getColumns().add(column7);

        TableColumn<MusicianDTO, String> column8 = new TableColumn<>("Theater");
        column8.setCellValueFactory(new PropertyValueFactory<>("theater"));
        actorTable.getColumns().add(column8);

        TableColumn<MusicianDTO, String> column9 = new TableColumn<>("Category");
        column9.setCellValueFactory(new PropertyValueFactory<>("category"));
        actorTable.getColumns().add(column9);

        actorTable.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            MusicianDTO dto = actorTable.getSelectionModel().getSelectedItem();
            List<CharactersMusicianDTO> charactersList = charactersActorService.getAll().stream()
                    .filter(el -> el.getIdEmployee() == dto.getIdEmployee()).toList();
            charactersTable.getItems().clear();
            charactersTable.getItems().addAll(charactersList);
            charactersTable.refresh();

//            List<RanksActorDTO> ranksList = ranksActorService.getAll().stream()
//                    .filter(el -> el.getIdEmployee() == dto.getIdEmployee()).toList();
//            List<RankDTO> rankList = new ArrayList<>();
//            ranksList.forEach(el-> rankService.getById(el.getIdRank()).ifPresent(rankList::add));
//            ranksTable.getItems().clear();
//            ranksTable.getItems().addAll(rankList);
//            ranksTable.refresh();
        });

        actorTable.setItems(FXCollections.observableList(actorService.getAll()));
    }

    private void initCategoryTable() {
        TableColumn<MusicianCategoryDTO, String> column = new TableColumn<>("Category");
        column.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryTable.getColumns().add(column);

        categoryTable.setItems(FXCollections.observableList(actorCategoryService.getAll()));
    }

    private void initCharacterTable() {
        TableColumn<MusicianCharacterDTO, String> column = new TableColumn<>("Character");
        column.setCellValueFactory(new PropertyValueFactory<>("character"));
        characterTable.getColumns().add(column);

        characterTable.setItems(FXCollections.observableList(actorCharacterService.getAll()));
    }

    private void initCharactersTable() {
        TableColumn<CharactersMusicianDTO, String> column = new TableColumn<>("Character");
        column.setCellValueFactory(new PropertyValueFactory<>("character"));
        charactersTable.getColumns().add(column);
    }

    private void showNewStage(Parent parent) {
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

//    private void initRankTable() {
//        TableColumn<RankDTO, String> column1 = new TableColumn<>("Name");
//        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
//        rankTable.getColumns().add(column1);
//
//        TableColumn<RankDTO, String> column2 = new TableColumn<>("Contest");
//        column2.setCellValueFactory(new PropertyValueFactory<>("contest"));
//        rankTable.getColumns().add(column2);
//
//        TableColumn<RankDTO, Date> column3 = new TableColumn<>("Data");
//        column3.setCellValueFactory(new PropertyValueFactory<>("data"));
//        rankTable.getColumns().add(column3);
//
//        rankTable.setItems(FXCollections.observableList(rankService.getAll()));
//    }
//
//    private void initRanksTable() {
//        TableColumn<RankDTO, String> column1 = new TableColumn<>("Name");
//        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
//        ranksTable.getColumns().add(column1);
//
//        TableColumn<RankDTO, String> column2 = new TableColumn<>("Contest");
//        column2.setCellValueFactory(new PropertyValueFactory<>("contest"));
//        ranksTable.getColumns().add(column2);
//
//        TableColumn<RankDTO, Date> column3 = new TableColumn<>("Data");
//        column3.setCellValueFactory(new PropertyValueFactory<>("data"));
//        ranksTable.getColumns().add(column3);
//    }

    public enum TableStatus {
        ACTOR,
        CATEGORY,
        CHARACTER,
        CHARACTERS
    }

    public enum QueryStatus {
        QUERY0,
        QUERY1
    }
}
