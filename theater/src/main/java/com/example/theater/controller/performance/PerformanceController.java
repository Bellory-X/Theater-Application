package com.example.theater.controller.performance;

import com.example.theater.dto.employee.ActorDTO;
import com.example.theater.dto.employee.category.ActorCategoryDTO;
import com.example.theater.dto.employee.character.ActorCharacterDTO;
import com.example.theater.dto.employee.character.CharactersActorDTO;
import com.example.theater.dto.employee.rank.RankDTO;
import com.example.theater.dto.employee.rank.RanksActorDTO;
import com.example.theater.service.employee.ActorService;
import com.example.theater.service.employee.category.ActorCategoryService;
import com.example.theater.service.employee.character.ActorCharacterService;
import com.example.theater.service.employee.character.CharactersActorService;
import com.example.theater.service.employee.rank.RankService;
import com.example.theater.service.employee.rank.RanksActorService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@FxmlView("/controller/performance/performance-view.fxml")
public class PerformanceController {
    /*TODO:
    *  2. Получить перечень и общее число спектаклей
    *  3.Получить перечень и общее число всех поставленных спектаклей
    *  5.Получить перечень спектаклей
    *  9.Получить список для указанного спектакля:
    *  11.Получить сведения о числе пpоданных билетов на все спектакли
    *  12.Получить общую сумму выpученных денег за указанный спектакль
    *  13.Получить перечень и общее число свободных мест на все спектакли*/
    @FXML private Text addText1;
    @FXML private TextField addField2;
    @FXML private TextField result;
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
    @FXML private Text searchText1;
    @FXML private Text searchText2;
    @FXML private Text searchText3;
    @FXML private Text searchText4;
    @FXML private Text searchText5;
    @FXML private Text searchText6;
    @FXML private TableView<RankDTO> rankTable;
    @FXML private TableView<ActorCharacterDTO> characterTable;
    @FXML private TableView<ActorCategoryDTO> categoryTable;
    @FXML private TableView<ActorDTO> actorTable;
    @FXML private TextField addField1;
    @FXML private TextField searchField6;
    @FXML private TextField searchField5;
    @FXML private TextField searchField3;
    @FXML private TextField searchField2;
    @FXML private DatePicker searchField4;
    @FXML private TextField searchField1;
    @FXML private ListView<String> queries;
    @FXML private Button search;
    @FXML private Button rank;
    @FXML private Button ranks;
    @FXML private Button actor;
    @FXML private Button category;
    @FXML private Button character;
    @FXML private Button characters;
    @FXML private Button close;
    @FXML private Button back;
    @FXML private Button drop;
    @FXML private Button edit;
    @FXML private Button add;
    private final ActorService actorService;
    private final ActorCategoryService actorCategoryService;
    private final ActorCharacterService actorCharacterService;
    private final CharactersActorService charactersActorService;
    private final RankService rankService;
    private final RanksActorService ranksActorService;
    private final FxWeaver fxWeaver;

    public PerformanceController(ActorService actorService, ActorCategoryService actorCategoryService,
                           ActorCharacterService actorCharacterService, CharactersActorService charactersActorService,
                           RankService rankService, RanksActorService ranksActorService, FxWeaver fxWeaver) {
        this.actorService = actorService;
        this.actorCategoryService = actorCategoryService;
        this.actorCharacterService = actorCharacterService;
        this.charactersActorService = charactersActorService;
        this.rankService = rankService;
        this.ranksActorService = ranksActorService;
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        initWorkerTable();
        initCategoryTable();
        initCharacterTable();
        initRankTable();
        clickButton();
    }

    private void clickButton() {
        search.setOnAction(event -> {});
        actor.setOnAction(event -> {});
        category.setOnAction(event -> {});
        character.setOnAction(event -> {});
        characters.setOnAction(event -> {});
        rank.setOnAction(event -> {});
        ranks.setOnAction(event -> {});
        back.setOnAction(event -> {});
        close.setOnAction(event -> close.getScene().getWindow().hide());
    }

    private void initWorkerTable() {
        TableColumn<ActorDTO, String> column1 = new TableColumn<>("Full Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        actorTable.getColumns().add(column1);

        TableColumn<ActorDTO, Integer> column2 = new TableColumn<>("Experience");
        column2.setCellValueFactory(new PropertyValueFactory<>("experience"));
        actorTable.getColumns().add(column2);

        TableColumn<ActorDTO, String> column3 = new TableColumn<>("Gender");
        column3.setCellValueFactory(new PropertyValueFactory<>("gender"));
        actorTable.getColumns().add(column3);

        TableColumn<ActorDTO, Date> column4 = new TableColumn<>("Birthday");
        column4.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        actorTable.getColumns().add(column4);

        TableColumn<ActorDTO, Integer> column5 = new TableColumn<>("Count child");
        column5.setCellValueFactory(new PropertyValueFactory<>("countChild"));
        actorTable.getColumns().add(column5);

        TableColumn<ActorDTO, Integer> column6 = new TableColumn<>("Salary");
        column6.setCellValueFactory(new PropertyValueFactory<>("salary"));
        actorTable.getColumns().add(column6);

        TableColumn<ActorDTO, Boolean> column7 = new TableColumn<>("Is Worker");
        column7.setCellValueFactory(new PropertyValueFactory<>("isWorker"));
        actorTable.getColumns().add(column7);

        TableColumn<ActorDTO, String> column8 = new TableColumn<>("Theater");
        column8.setCellValueFactory(new PropertyValueFactory<>("theater"));
        actorTable.getColumns().add(column8);

        TableColumn<ActorDTO, String> column9 = new TableColumn<>("Category");
        column9.setCellValueFactory(new PropertyValueFactory<>("category"));
        actorTable.getColumns().add(column9);

        actorTable.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
        });

        actorTable.setItems(FXCollections.observableList(actorService.getAll()));
    }

    private void initCategoryTable() {
        TableColumn<ActorCategoryDTO, String> column = new TableColumn<>("Category");
        column.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryTable.getColumns().add(column);

        categoryTable.setItems(FXCollections.observableList(actorCategoryService.getAll()));
    }

    private void initCharacterTable() {
        TableColumn<ActorCharacterDTO, String> column = new TableColumn<>("Character");
        column.setCellValueFactory(new PropertyValueFactory<>("character"));
        characterTable.getColumns().add(column);

        characterTable.setItems(FXCollections.observableList(actorCharacterService.getAll()));
    }

    private void initRankTable() {
        TableColumn<RankDTO, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        rankTable.getColumns().add(column1);

        TableColumn<RankDTO, String> column2 = new TableColumn<>("Contest");
        column2.setCellValueFactory(new PropertyValueFactory<>("contest"));
        rankTable.getColumns().add(column2);

        TableColumn<RankDTO, Date> column3 = new TableColumn<>("Data");
        column3.setCellValueFactory(new PropertyValueFactory<>("data"));
        rankTable.getColumns().add(column3);

        rankTable.setItems(FXCollections.observableList(rankService.getAll()));
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
