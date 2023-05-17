package com.example.theater.controller.performance;

import com.example.theater.controller.TheaterController;
import com.example.theater.controller.employee.ActorController;
import com.example.theater.dto.performance.HallDTO;
import com.example.theater.dto.performance.PerformanceDTO;
import com.example.theater.dto.performance.PlaceDTO;
import com.example.theater.dto.performance.RepertoireDTO;
import com.example.theater.dto.performance.play.AuthorDTO;
import com.example.theater.dto.performance.play.PlayDTO;
import com.example.theater.service.employee.category.ActorCategoryService;
import com.example.theater.service.employee.character.ActorCharacterService;
import com.example.theater.service.employee.character.CharactersActorService;
import com.example.theater.service.employee.rank.RankService;
import com.example.theater.service.employee.rank.RanksActorService;
import com.example.theater.service.performance.HallService;
import com.example.theater.service.performance.PerformanceService;
import com.example.theater.service.performance.PlaceService;
import com.example.theater.service.performance.RepertoireService;
import com.example.theater.service.performance.play.PlayService;
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

import java.util.*;

@Component
@FxmlView("/controller/performance/performance-view.fxml")
public class PerformanceController {
    /*TODO:
     *  2. Получить перечень и общее число спектаклей
     *  3.Получить перечень и общее число всех поставленных спектаклей
     *  5.Получить перечень спектаклей
     *  11.Получить сведения о числе пpоданных билетов на все спектакли
     *  12.Получить общую сумму выpученных денег за указанный спектакль
     *  13.Получить перечень и общее число свободных мест на все спектакли*/
    @FXML private Text searchText8;
    @FXML private Text searchText7;
    @FXML private DatePicker searchField6;
    @FXML private TextField searchField4;
    @FXML private TextField addField1;
    @FXML private Text addText1;
    @FXML private TextField addField2;
    @FXML private Text addText2;
    @FXML private DatePicker addField3;
    @FXML private Text addText3;
    @FXML private DatePicker addField4;
    @FXML private Text addText4;
    @FXML private TextField addField5;
    @FXML private Text addText5;
    @FXML private TextField addField6;
    @FXML private Text addText6;
    @FXML private TextField result;
    @FXML private Text searchText1;
    @FXML private Text searchText2;
    @FXML private Text searchText3;
    @FXML private Text searchText4;
    @FXML private Text searchText5;
    @FXML private Text searchText6;
    @FXML private TableView<PlayDTO> playTable;
    @FXML private TableView<RepertoireDTO> repertoireTable;
    @FXML private TableView<PlaceDTO> placeTable;
    @FXML private TableView<HallDTO> hallTable;
    @FXML private TableView<PerformanceDTO> performanceTable;
    @FXML private TextField searchField8;
    @FXML private TextField searchField7;
    @FXML private TextField searchField3;
    @FXML private TextField searchField2;
    @FXML private DatePicker searchField5;
    @FXML private TextField searchField1;
    @FXML private ListView<String> queries;
    @FXML private Button search;
    @FXML private Button repertoire;
    @FXML private Button troupe;
    @FXML private Button performance;
    @FXML private Button hall;
    @FXML private Button place;
    @FXML private Button play;
    @FXML private Button close;
    @FXML private Button back;
    @FXML private Button drop;
    @FXML private Button edit;
    @FXML private Button add;
    private final PerformanceService performanceService;
    private final HallService hallService;
    private final PlaceService placeService;
    private final RepertoireService repertoireService;
    private final PlayService playService;
    private final FxWeaver fxWeaver;
    private TableStatus tableStatus = TableStatus.PERFORMANCE;
    private QueryStatus queryStatus = QueryStatus.QUERY1;
    private final Map<QueryStatus, String> queryMap = new HashMap<>();

    public PerformanceController(PerformanceService performanceService, HallService hallService,
                                 PlaceService placeService, RepertoireService repertoireService,
                                 PlayService playService, FxWeaver fxWeaver) {
        this.performanceService = performanceService;
        this.hallService = hallService;
        this.placeService = placeService;
        this.repertoireService = repertoireService;
        this.playService = playService;
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        initQueries();
        initPlayTable();
        initPerformanceTable();
        initHallTable();
        initPlaceTable();
        initRepertoireTable();
        clickButton();
    }

    private void clickButton() {
        performance.setOnAction(event -> {});
        hall.setOnAction(event -> {});
        place.setOnAction(event -> {});
        repertoire.setOnAction(event -> {});
        troupe.setOnAction(event -> {});
        play.setOnAction(event -> {});
        add.setOnAction(event -> {});
        edit.setOnAction(event -> {});
        drop.setOnAction(event -> {});
        search.setOnAction(event -> {});
        back.setOnAction(event -> showNewStage(fxWeaver.loadView(TheaterController.class)));
        close.setOnAction(event -> close.getScene().getWindow().hide());
    }

    private void showNewStage(Parent parent) {
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private void initQueries() {
        queryMap.put(QueryStatus.QUERY0, "Получить все скектакли");
        queryMap.put(QueryStatus.QUERY1, "Получить скектакли, сыгранных в театре");
        queryMap.put(QueryStatus.QUERY2, "Получить скектакли по автору");
        queryMap.put(QueryStatus.QUERY3, "Получить число пpоданных билетов на все спектакли");
        queryMap.put(QueryStatus.QUERY4, "Получить число пpоданных билетов на кокретный спектакли");
        queryMap.put(QueryStatus.QUERY5, "Получить число пpоданных билетов на премьеры спектакли");
        queryMap.put(QueryStatus.QUERY6, "Получить общую сумму выpученных денег за спектакль");
        queryMap.put(QueryStatus.QUERY7, "Получить число свободных мест на все спектакли");
        queryMap.put(QueryStatus.QUERY8, "Получить число свободных мест на конкpетный спектакль");
        queryMap.put(QueryStatus.QUERY9, "Получить число свободных мест на пpемьеpы");
    }

    private void initRepertoireTable() {
        TableColumn<RepertoireDTO, Integer> column1 = new TableColumn<>("Number");
        column1.setCellValueFactory(new PropertyValueFactory<>("number"));
        repertoireTable.getColumns().add(column1);

        TableColumn<RepertoireDTO, String> column2 = new TableColumn<>("Theater");
        column2.setCellValueFactory(new PropertyValueFactory<>("theater"));
        repertoireTable.getColumns().add(column2);

        repertoireTable.setItems(FXCollections.observableList(repertoireService.getAll()));
    }

    private void initPlaceTable() {
        TableColumn<PlaceDTO, Integer> column1 = new TableColumn<>("Number");
        column1.setCellValueFactory(new PropertyValueFactory<>("number"));
        placeTable.getColumns().add(column1);

        TableColumn<PlaceDTO, Integer> column2 = new TableColumn<>("Price");
        column2.setCellValueFactory(new PropertyValueFactory<>("price"));
        placeTable.getColumns().add(column2);

        TableColumn<PlaceDTO, Boolean> column3 = new TableColumn<>("Reserve");
        column3.setCellValueFactory(new PropertyValueFactory<>("reserve"));
        placeTable.getColumns().add(column3);
    }

    private void initHallTable() {
        TableColumn<HallDTO, Date> column1 = new TableColumn<>("Start");
        column1.setCellValueFactory(new PropertyValueFactory<>("start"));
        hallTable.getColumns().add(column1);

        TableColumn<HallDTO, Date> column2 = new TableColumn<>("End");
        column2.setCellValueFactory(new PropertyValueFactory<>("end"));
        hallTable.getColumns().add(column2);

        TableColumn<HallDTO, String> column3 = new TableColumn<>("Name");
        column3.setCellValueFactory(new PropertyValueFactory<>("name"));
        hallTable.getColumns().add(column3);

        hallTable.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            placeTable.getItems().clear();
            placeTable.getItems().addAll(placeService.getAll().stream()
                    .filter(el -> el.getIdHall() == hallTable.getSelectionModel().getSelectedItem().getId())
                    .toList());
            placeTable.refresh();
        });
    }

    private void initPerformanceTable() {
        TableColumn<PerformanceDTO, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        performanceTable.getColumns().add(column1);

        TableColumn<PerformanceDTO, Date> column2 = new TableColumn<>("Data");
        column2.setCellValueFactory(new PropertyValueFactory<>("data"));
        performanceTable.getColumns().add(column2);

        TableColumn<PerformanceDTO, String> column3 = new TableColumn<>("Rating");
        column3.setCellValueFactory(new PropertyValueFactory<>("rating"));
        performanceTable.getColumns().add(column3);

        TableColumn<PerformanceDTO, String> column4 = new TableColumn<>("Genre");
        column4.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        performanceTable.getColumns().add(column4);

        TableColumn<PerformanceDTO, Integer> column5 = new TableColumn<>("price");
        column5.setCellValueFactory(new PropertyValueFactory<>("price"));
        performanceTable.getColumns().add(column5);

        TableColumn<PerformanceDTO, String> column6 = new TableColumn<>("From theater");
        column6.setCellValueFactory(new PropertyValueFactory<>("theater"));
        performanceTable.getColumns().add(column6);

        TableColumn<PerformanceDTO, Integer> column7 = new TableColumn<>("Number");
        column7.setCellValueFactory(new PropertyValueFactory<>("number"));
        performanceTable.getColumns().add(column7);

        TableColumn<PerformanceDTO, String> column8 = new TableColumn<>("To theater");
        column8.setCellValueFactory(new PropertyValueFactory<>("to_theater"));
        performanceTable.getColumns().add(column8);

        performanceTable.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            hallTable.getItems().clear();
            hallTable.getItems().addAll(hallService.getAll().stream()
                    .filter(el -> el.getIdPerformance() == performanceTable.getSelectionModel().getSelectedItem().getId())
                    .toList());
            hallTable.refresh();
        });

        performanceTable.setItems(FXCollections.observableList(performanceService.getAll()));
    }

    private void initPlayTable() {
        TableColumn<PlayDTO, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        playTable.getColumns().add(column1);

        TableColumn<PlayDTO, Date> column2 = new TableColumn<>("Data");
        column2.setCellValueFactory(new PropertyValueFactory<>("data"));
        playTable.getColumns().add(column2);

        TableColumn<PlayDTO, String> column3 = new TableColumn<>("Rating");
        column3.setCellValueFactory(new PropertyValueFactory<>("rating"));
        playTable.getColumns().add(column3);

        TableColumn<PlayDTO, String> column4 = new TableColumn<>("Genre");
        column4.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        playTable.getColumns().add(column4);

        playTable.setItems(FXCollections.observableList(playService.getAll()));
    }

    public enum TableStatus {
        PERFORMANCE,
        HALL,
        PLACE,
        REPERTOIRE,
        PLAY
    }

    public enum QueryStatus {
        QUERY0,
        QUERY1,
        QUERY2,
        QUERY3,
        QUERY4,
        QUERY5,
        QUERY6,
        QUERY7,
        QUERY8,
        QUERY9
    }
}
