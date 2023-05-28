package com.example.theater.controller.performance;

import com.example.theater.controller.TheaterController;
import com.example.theater.dto.performance.HallDTO;
import com.example.theater.dto.performance.PerformanceDTO;
import com.example.theater.dto.performance.PlaceDTO;
import com.example.theater.dto.performance.TimeHallDTO;
import com.example.theater.exception.ItemException;
import com.example.theater.exception.QueryException;
import com.example.theater.service.performance.place.HallService;
import com.example.theater.service.performance.PerformanceService;
import com.example.theater.service.performance.place.PlaceService;
import com.example.theater.service.performance.place.TimeHallService;
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
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

@Component
@FxmlView("/controller/performance/place-view.fxml")
public class PlaceController {
    @FXML private TextField addField1;
    @FXML private Text addText1;
    @FXML private TextField addField2;
    @FXML private Text addText2;
    @FXML private TextField addField3;
    @FXML private Text addText3;
    @FXML private TextField addField4;
    @FXML private Text addText4;
    @FXML private TextField addField5;
    @FXML private Text addText5;
    @FXML private Text searchText5;
    @FXML private Text searchText6;
    @FXML private DatePicker searchField5;
    @FXML private DatePicker searchField6;
    @FXML private TableView<TimeHallDTO> timeHallTable;
    @FXML private TableView<PlaceDTO> placeTable;
    @FXML private TableView<HallDTO> hallTable;
    @FXML private TableView<PerformanceDTO> performanceTable;
    @FXML private ListView<String> queries;
    @FXML private TextField result;
    @FXML private Button search;
    @FXML private Button timeHall;
    @FXML private Button hall;
    @FXML private Button place;
    @FXML private Button close;
    @FXML private Button back;
    @FXML private Button drop;
    @FXML private Button edit;
    @FXML private Button add;
    private TableStatus tableStatus = TableStatus.NOT_SELECT;
    private QueryStatus queryStatus = QueryStatus.QUERY0;
    private final PerformanceService performanceService;
    private final HallService hallService;
    private final PlaceService placeService;
    private final TimeHallService timeHallService;
    private final FxWeaver fxWeaver;
    private final Map<QueryStatus, String> queryMap = new HashMap<>();

    public PlaceController(PerformanceService performanceService, HallService hallService,
                           PlaceService placeService, TimeHallService timeHallService, FxWeaver fxWeaver) {
        this.performanceService = performanceService;
        this.hallService = hallService;
        this.placeService = placeService;
        this.timeHallService = timeHallService;
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        initQueries();
        initTimeHallTable();
        initPerformanceTable();
        initHallTable();
        initPlaceTable();
        clickButton();
    }

    private void clickButton() {
        hall.setOnAction(event -> setHall());
        place.setOnAction(event -> setPlace());
        timeHall.setOnAction(event -> setTimeHall());
        add.setOnAction(event -> addEvent());
        edit.setOnAction(event -> editEvent());
        drop.setOnAction(event -> dropEvent());
        search.setOnAction(event -> clickSearch());
        back.setOnAction(event -> showNewStage(fxWeaver.loadView(TheaterController.class)));
        close.setOnAction(event -> close.getScene().getWindow().hide());
        setHall();
    }

    private void initQueries() {
        queryMap.put(QueryStatus.QUERY11_1, "Получить число пpоданных билетов на все спектакли");
        queryMap.put(QueryStatus.QUERY11_2, "Получить число пpоданных билетов на кокретный спектакли");
        queryMap.put(QueryStatus.QUERY11_3, "Получить число пpоданных билетов на премьеры спектакли");
        queryMap.put(QueryStatus.QUERY12, "Получить общую сумму выpученных денег за спектакль");
        queryMap.put(QueryStatus.QUERY13_1, "Получить число свободных мест на все спектакли");
        queryMap.put(QueryStatus.QUERY13_2, "Получить число свободных мест на конкpетный спектакль");
        queryMap.put(QueryStatus.QUERY13_3, "Получить число свободных мест на пpемьеpы");
        queries.getItems().add(queryMap.get(QueryStatus.QUERY11_1));
        queries.getItems().add(queryMap.get(QueryStatus.QUERY11_2));
        queries.getItems().add(queryMap.get(QueryStatus.QUERY11_3));
        queries.getItems().add(queryMap.get(QueryStatus.QUERY12));
        queries.getItems().add(queryMap.get(QueryStatus.QUERY13_1));
        queries.getItems().add(queryMap.get(QueryStatus.QUERY13_2));
        queries.getItems().add(queryMap.get(QueryStatus.QUERY13_3));
        queries.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (queries.getSelectionModel().getSelectedItem()
                    .equals(queryMap.get(QueryStatus.QUERY11_1))) {
                queryStatus = QueryStatus.QUERY11_1;
                searchText5.setVisible(true);
                searchField5.setVisible(true);
                searchText6.setVisible(true);
                searchField6.setVisible(true);
            }
            if (queries.getSelectionModel().getSelectedItem()
                    .equals(queryMap.get(QueryStatus.QUERY11_2))) {
                queryStatus = QueryStatus.QUERY11_2;
                searchText5.setVisible(true);
                searchField5.setVisible(true);
                searchText6.setVisible(true);
                searchField6.setVisible(true);
            }
            if (queries.getSelectionModel().getSelectedItem()
                    .equals(queryMap.get(QueryStatus.QUERY11_3))) {
                queryStatus = QueryStatus.QUERY11_3;
                searchText5.setVisible(true);
                searchField5.setVisible(true);
                searchText6.setVisible(true);
                searchField6.setVisible(true);
            }
            if (queries.getSelectionModel().getSelectedItem()
                    .equals(queryMap.get(QueryStatus.QUERY12))) {
                queryStatus = QueryStatus.QUERY12;
                searchText5.setVisible(false);
                searchField5.setVisible(false);
                searchText6.setVisible(false);
                searchField6.setVisible(false);
            }
            if (queries.getSelectionModel().getSelectedItem()
                    .equals(queryMap.get(QueryStatus.QUERY13_1))) {
                queryStatus = QueryStatus.QUERY13_1;
                searchText5.setVisible(true);
                searchField5.setVisible(true);
                searchText6.setVisible(true);
                searchField6.setVisible(true);
            }
            if (queries.getSelectionModel().getSelectedItem()
                    .equals(queryMap.get(QueryStatus.QUERY13_2))) {
                queryStatus = QueryStatus.QUERY13_2;
                searchText5.setVisible(true);
                searchField5.setVisible(true);
                searchText6.setVisible(true);
                searchField6.setVisible(true);
            }
            if (queries.getSelectionModel().getSelectedItem()
                    .equals(queryMap.get(QueryStatus.QUERY13_3))) {
                queryStatus = QueryStatus.QUERY13_3;
                searchText5.setVisible(true);
                searchField5.setVisible(true);
                searchText6.setVisible(true);
                searchField6.setVisible(true);
            }
        });
    }

    private void clickSearch() {
        try {
            switch (queryStatus) {
                case QUERY0 -> result.setText("Select query");
                case QUERY11_1 -> result.setText(String.valueOf(placeService.findActorQuery11_1(
                        Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                        Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
                ).size()));
                case QUERY11_2 -> result.setText(String.valueOf(placeService.findActorQuery11_2(
                        Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                        Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                        performanceTable.getSelectionModel().getSelectedItem().getId()
                ).size()));
                case QUERY11_3 -> result.setText(String.valueOf(placeService.findActorQuery11_3(
                        Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                        Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
                ).size()));
                case QUERY12 -> {
                    int count = 0;
                    List<TimeHallDTO> thList = timeHallService.getAll().stream()
                            .filter(el -> el.getIdPerformance() == performanceTable.getSelectionModel().getSelectedItem().getId())
                            .toList();
                    for (var el : thList) {
                        List<PlaceDTO> list = placeService.getAll().stream()
                                .filter(PlaceDTO::isReserve)
                                .filter(v -> v.getIdHall() == el.getId()).toList();
                        for (var i : list) {
                            count += i.getPrice();
                            count += performanceTable.getSelectionModel().getSelectedItem().getPrice();
                        }
                    }
                    result.setText(String.valueOf(count));
                }
                case QUERY13_1 -> result.setText(String.valueOf(placeService.findActorQuery13_1(
                        Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                        Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
                ).size()));
                case QUERY13_2 -> result.setText(String.valueOf(placeService.findActorQuery13_2(
                        Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                        Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                        performanceTable.getSelectionModel().getSelectedItem().getId()
                ).size()));
                case QUERY13_3 -> result.setText(String.valueOf(placeService.findActorQuery13_3(
                        Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                        Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
                ).size()));
            }
        } catch (NullPointerException e) {
            result.setText("select record performance");
        }
    }

    private void dropEvent() {
        try {
            switch (tableStatus) {
                case NOT_SELECT -> result.setText("Select table");
                case HALL -> {
                    if (hallTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    hallService.drop(hallTable.getSelectionModel().getSelectedItem());
                    hallTable.setItems(FXCollections.observableList(hallService.getAll()));
                    result.setText("Success");
                }
                case PLACE -> result.setText("Function not available");
                case TIME_HALL -> {
                    if (timeHallTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("null");
                    timeHallService.drop(timeHallTable.getSelectionModel().getSelectedItem());
                    timeHallTable.setItems(FXCollections.observableList(timeHallService.getAll().stream()
                            .filter(el -> el.getIdPerformance() == performanceTable.getSelectionModel().getSelectedItem().getId())
                            .toList()));
                    result.setText("Success");
                }
            }
        } catch (NumberFormatException e) {
            result.setText("In one of the fields not number");
        } catch (ItemException e) {
            result.setText(e.getMessage());
        }
    }

    private void editEvent() {
        try {
            switch (tableStatus) {
                case NOT_SELECT -> result.setText("Select table");
                case HALL, TIME_HALL -> result.setText("Function not available");
                case PLACE -> {
                    if (placeTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    PlaceDTO dto = placeTable.getSelectionModel().getSelectedItem();
                    dto.setPrice(Integer.parseInt(addField1.getText()));
                    dto.setReserve(Boolean.parseBoolean(addField2.getText()));
                    placeService.edit(dto);
                    placeTable.setItems(FXCollections.observableList(placeService.getAll().stream()
                            .filter(el -> el.getIdHall() == timeHallTable.getSelectionModel().getSelectedItem().getId())
                            .toList()));
                    result.setText("Success");
                }
            }
        } catch (NumberFormatException e) {
            result.setText("Price not positive number");
        } catch (ItemException e) {
            result.setText(e.getMessage());
        }
    }

    private void addEvent() {
        try {
            switch (tableStatus) {
                case NOT_SELECT -> result.setText("Select table");
                case HALL -> {
                    hallService.add(HallDTO.builder()
                            .name(addField1.getText())
                            .theater(addField2.getText())
                            .count(Integer.parseInt(addField5.getText()))
                            .build());
                    hallTable.setItems(FXCollections.observableList(hallService.getAll()));
                    result.setText("Success");
                }
                case PLACE -> result.setText("Function not available");
                case TIME_HALL -> {
                    if (hallTable.getSelectionModel().getSelectedItem() == null ||
                            performanceTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select records hall and performance");
                    timeHallService.add(TimeHallDTO.builder()
                            .idHall(hallTable.getSelectionModel().getSelectedItem().getId())
                            .idPerformance((performanceTable.getSelectionModel().getSelectedItem().getId()))
                            .start(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(addField3.getText()))
                            .end(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(addField4.getText()))
                            .count(hallTable.getSelectionModel().getSelectedItem().getCount())
                            .build());
                    timeHallTable.setItems(FXCollections.observableList(timeHallService.getAll().stream()
                            .filter(el -> el.getIdPerformance() == performanceTable.getSelectionModel().getSelectedItem().getId())
                            .toList()));
                    result.setText("Success");
                }
            }
        } catch (NumberFormatException e) {
            result.setText("Count not positive number");
        } catch (QueryException | ItemException e) {
            result.setText(e.getMessage());
        } catch (ParseException e) {
            result.setText("Start ot end not date (dd-MM-yyyy HH:mm:ss)");
        }
    }

    private void setTimeHall() {
        tableStatus = TableStatus.TIME_HALL;
        addField1.setVisible(false);
        addField2.setVisible(false);
        addField3.setVisible(true);
        addField4.setVisible(true);
        addField5.setVisible(false);
        addText1.setVisible(false);
        addText2.setVisible(false);
        addText3.setVisible(true);
        addText4.setVisible(true);
        addText5.setVisible(false);
    }

    private void setPlace() {
        tableStatus = TableStatus.PLACE;
        addField1.setVisible(true);
        addField2.setVisible(true);
        addField3.setVisible(false);
        addField4.setVisible(false);
        addField5.setVisible(false);
        addText1.setVisible(true);
        addText2.setVisible(true);
        addText3.setVisible(false);
        addText4.setVisible(false);
        addText5.setVisible(false);
        addText1.setText("Price");
        addText2.setText("Reserve");
    }

    private void setHall() {
        tableStatus = TableStatus.HALL;
        addField1.setVisible(true);
        addField2.setVisible(true);
        addField3.setVisible(false);
        addField4.setVisible(false);
        addField5.setVisible(true);
        addText1.setVisible(true);
        addText2.setVisible(true);
        addText3.setVisible(false);
        addText4.setVisible(false);
        addText5.setVisible(true);
        addText1.setText("Name");
        addText2.setText("Theater");
        addField5.setText("Count Places");
    }

    private void showNewStage(Parent parent) {
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
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
        TableColumn<HallDTO, Date> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        hallTable.getColumns().add(column1);

        TableColumn<HallDTO, Date> column2 = new TableColumn<>("Theater");
        column2.setCellValueFactory(new PropertyValueFactory<>("theater"));
        hallTable.getColumns().add(column2);

        TableColumn<HallDTO, String> column3 = new TableColumn<>("Count");
        column3.setCellValueFactory(new PropertyValueFactory<>("count"));
        hallTable.getColumns().add(column3);

        hallTable.setItems(FXCollections.observableList(hallService.getAll()));
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

        performanceTable.addEventFilter(MouseEvent.MOUSE_CLICKED,
                event -> timeHallTable.setItems(FXCollections.observableList(timeHallService.getAll().stream()
                        .filter(el -> el.getIdPerformance() == performanceTable.getSelectionModel().getSelectedItem().getId())
                        .toList())));

        performanceTable.setItems(FXCollections.observableList(performanceService.getAll()));
    }

    private void initTimeHallTable() {
        TableColumn<TimeHallDTO, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        timeHallTable.getColumns().add(column1);

        TableColumn<TimeHallDTO, Date> column2 = new TableColumn<>("Start");
        column2.setCellValueFactory(new PropertyValueFactory<>("start"));
        timeHallTable.getColumns().add(column2);

        TableColumn<TimeHallDTO, Date> column3 = new TableColumn<>("End");
        column3.setCellValueFactory(new PropertyValueFactory<>("end"));
        timeHallTable.getColumns().add(column3);

        TableColumn<TimeHallDTO, String> column4 = new TableColumn<>("Theater");
        column4.setCellValueFactory(new PropertyValueFactory<>("theater"));
        timeHallTable.getColumns().add(column4);

        timeHallTable.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->
                placeTable.setItems(FXCollections.observableList(placeService.getAll().stream()
                        .filter(el -> el.getIdHall() == timeHallTable.getSelectionModel().getSelectedItem().getId())
                        .toList())));
    }

    public enum TableStatus {
        NOT_SELECT,
        HALL,
        PLACE,
        TIME_HALL
    }

    public enum QueryStatus {
        QUERY0,
        QUERY11_1,
        QUERY11_2,
        QUERY11_3,
        QUERY12,
        QUERY13_1,
        QUERY13_2,
        QUERY13_3
    }
}
