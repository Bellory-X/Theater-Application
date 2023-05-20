package com.example.theater.controller.performance;

import com.example.theater.controller.TheaterController;
import com.example.theater.controller.employee.ActorController;
import com.example.theater.dto.employee.ActorDTO;
import com.example.theater.dto.performance.HallDTO;
import com.example.theater.dto.performance.PerformanceDTO;
import com.example.theater.dto.performance.PlaceDTO;
import com.example.theater.dto.performance.TimeHallDTO;
import com.example.theater.exception.ItemException;
import com.example.theater.exception.QueryException;
import com.example.theater.service.performance.HallService;
import com.example.theater.service.performance.PerformanceService;
import com.example.theater.service.performance.PlaceService;
import com.example.theater.service.performance.TimeHallService;
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
    @FXML private TextField result;
    @FXML private Text searchText1;
    @FXML private Text searchText2;
    @FXML private Text searchText3;
    @FXML private Text searchText4;
    @FXML private Text searchText5;
    @FXML private Text searchText6;
    @FXML private Text searchText7;
    @FXML private Text searchText8;
    @FXML private TextField searchField8;
    @FXML private TextField searchField7;
    @FXML private TextField searchField3;
    @FXML private TextField searchField2;
    @FXML private DatePicker searchField5;
    @FXML private TextField searchField1;
    @FXML private DatePicker searchField6;
    @FXML private TextField searchField4;
    @FXML private TableView<TimeHallDTO> timeHallTable;
    @FXML private TableView<PlaceDTO> placeTable;
    @FXML private TableView<HallDTO> hallTable;
    @FXML private TableView<PerformanceDTO> performanceTable;
    @FXML private ListView<String> queries;
    @FXML private Button search;
    @FXML private Button timeHall;
    @FXML private Button hall;
    @FXML private Button place;
    @FXML private Button close;
    @FXML private Button back;
    @FXML private Button drop;
    @FXML private Button edit;
    @FXML private Button add;
    private final PerformanceService performanceService;
    private final HallService hallService;
    private final PlaceService placeService;
    private final TimeHallService timeHallService;
    private final FxWeaver fxWeaver;
    private TableStatus tableStatus = TableStatus.HALL;
    private QueryStatus queryStatus = QueryStatus.QUERY1;
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
        add.setOnAction(event -> clickAdd());
        edit.setOnAction(event -> clickEdit());
        drop.setOnAction(event -> clickDrop());
        search.setOnAction(event -> clickSearch());
        back.setOnAction(event -> showNewStage(fxWeaver.loadView(TheaterController.class)));
        close.setOnAction(event -> close.getScene().getWindow().hide());
    }

    private void initQueries() {
        queryMap.put(QueryStatus.QUERY1, "Получить число пpоданных билетов на все спектакли");
        queryMap.put(QueryStatus.QUERY2, "Получить число пpоданных билетов на кокретный спектакли");
        queryMap.put(QueryStatus.QUERY3, "Получить число пpоданных билетов на премьеры спектакли");
        queryMap.put(QueryStatus.QUERY4, "Получить общую сумму выpученных денег за спектакль");
        queryMap.put(QueryStatus.QUERY5, "Получить число свободных мест на все спектакли");
        queryMap.put(QueryStatus.QUERY6, "Получить число свободных мест на конкpетный спектакль");
        queryMap.put(QueryStatus.QUERY7, "Получить число свободных мест на пpемьеpы");
        queries.getItems().add(queryMap.get(QueryStatus.QUERY1));
        queries.getItems().add(queryMap.get(QueryStatus.QUERY2));
        queries.getItems().add(queryMap.get(QueryStatus.QUERY3));
        queries.getItems().add(queryMap.get(QueryStatus.QUERY4));
        queries.getItems().add(queryMap.get(QueryStatus.QUERY5));
        queries.getItems().add(queryMap.get(QueryStatus.QUERY6));
        queries.getItems().add(queryMap.get(QueryStatus.QUERY7));

        searchText7.setVisible(false);
        searchText7.setVisible(false);
        searchText5.setText("from data");
        searchText6.setText("to data");

        queries.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(QueryStatus.QUERY1))) {
                queryStatus = QueryStatus.QUERY1;
            }
            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(QueryStatus.QUERY2))) {
                queryStatus = QueryStatus.QUERY2;
            }
            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(QueryStatus.QUERY3))) {
                queryStatus = QueryStatus.QUERY3;
            }
            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(QueryStatus.QUERY4))) {
                queryStatus = QueryStatus.QUERY4;
            }
            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(QueryStatus.QUERY5))) {
                queryStatus = QueryStatus.QUERY5;
            }
            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(QueryStatus.QUERY6))) {
                queryStatus = QueryStatus.QUERY6;
            }
            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(QueryStatus.QUERY7))) {
                queryStatus = QueryStatus.QUERY7;
            }
        });
    }

    private void clickSearch() {
        switch (queryStatus) {
            case QUERY1 -> {
                result.setText(String.valueOf(placeService
                        .findActorQuery11_1(
                                Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
                        ).size()));
            }
            case QUERY2 -> {
                result.setText(String.valueOf(placeService
                        .findActorQuery11_2(
                                Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                performanceTable.getSelectionModel().getSelectedItem().getId()
                ).size()));
            }
            case QUERY3 -> {
                result.setText(String.valueOf(placeService
                        .findActorQuery11_3(
                                Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
                        ).size()));
            }
            case QUERY4 -> {
                int count = 0;
                List<TimeHallDTO> thList = timeHallService.getAll().stream()
                        .filter(el-> el.getIdPerformance() == performanceTable.getSelectionModel().getSelectedItem().getId())
                        .toList();
                for (var el : thList) {
                    List<PlaceDTO> list = placeService.getAll().stream()
                            .filter(PlaceDTO::isReserve)
                            .filter(v -> v.getIdHall() == el.getId()).toList();
                    for (var i: list) {
                        count += i.getPrice();
                        count += performanceTable.getSelectionModel().getSelectedItem().getPrice();
                    }
                }
                result.setText(String.valueOf(count));
            }
            case QUERY5 -> {
                result.setText(String.valueOf(placeService
                        .findActorQuery13_1(
                                Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
                        ).size()));
            }
            case QUERY6 -> {
                result.setText(String.valueOf(placeService
                        .findActorQuery13_2(
                                Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                performanceTable.getSelectionModel().getSelectedItem().getId()
                        ).size()));
            }
            case QUERY7 -> {
                result.setText(String.valueOf(placeService
                        .findActorQuery13_3(
                                Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())
                        ).size()));
            }
        }
    }

    private void clickDrop() {
        try {
            switch (tableStatus) {
                case HALL -> {
                    if (hallTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("null");

                    hallService.drop(hallTable.getSelectionModel().getSelectedItem());
                    hallTable.setItems(FXCollections.observableList(hallService.getAll()));
                    hallTable.refresh();
                }
                case PLACE -> {
                    result.setText("Function not available");
//                    if (placeTable.getSelectionModel().getSelectedItem() == null)
//                        throw new ItemException("null");
//
//                    placeService.drop(placeTable.getSelectionModel().getSelectedItem());
                }
                case TIME_HALL -> {
                    if (timeHallTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("null");

                    timeHallService.drop(timeHallTable.getSelectionModel().getSelectedItem());
                }
            }
            result.setText("Success");
        } catch (NumberFormatException e) {
            result.setText("In one of the fields not number");
        } catch (ItemException e) {
            result.setText("select record");
        }
    }

    private void clickEdit() {
        try {
            switch (tableStatus) {
                case HALL -> {
                    result.setText("Function not available");
//                    if (hallTable.getSelectionModel().getSelectedItem() == null)
//                        throw new ItemException("null");
//
//                    hallService.edit(hallTable.getSelectionModel().getSelectedItem());
                }
                case PLACE -> {
                    if (placeTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("null");

                    PlaceDTO dto = placeTable.getSelectionModel().getSelectedItem();
                    dto.setPrice(Integer.parseInt(addField1.getText()));
                    dto.setReserve(Boolean.parseBoolean(addField2.getText()));
                    placeService.edit(dto);
                }
                case TIME_HALL -> {
                    result.setText("Function not available");
//                    if (timeHallTable.getSelectionModel().getSelectedItem() == null)
//                        throw new ItemException("null");
//
//                    timeHallService.edit(timeHallTable.getSelectionModel().getSelectedItem());
                }
            }
            result.setText("Success");
        } catch (NumberFormatException e) {
            result.setText("Price not positive number");
        } catch (ItemException e) {
            result.setText("select record");
        }
    }

    private void clickAdd() {
        try {
            switch (tableStatus) {
                case HALL -> {
                    hallService.add(HallDTO.builder()
                            .name(addField1.getText())
                            .theater(addField2.getText())
                            .count(Integer.parseInt(addField5.getText()))
                            .build());
                    hallTable.setItems(FXCollections.observableList(hallService.getAll()));
                    hallTable.refresh();
                }
                case PLACE -> {
                    result.setText("Function not available");
                }
                case TIME_HALL -> {
                    if (hallTable.getSelectionModel().getSelectedItem() == null ||
                            performanceTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("null");

                    timeHallService.add(TimeHallDTO.builder()
                            .idHall(hallTable.getSelectionModel().getSelectedItem().getId())
                            .idPerformance((performanceTable.getSelectionModel().getSelectedItem().getId()))
                            .start(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(addField3.getText()))
                            .end(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(addField4.getText()))
                            .count(hallTable.getSelectionModel().getSelectedItem().getCount())
                            .build());
                }
            }
            result.setText("Success");
        } catch (NumberFormatException e) {
            result.setText("Count not positive number");
        } catch (QueryException e) {
            result.setText(e.getMessage());
        } catch (ItemException e) {
            result.setText("select record");
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
        addText3.setText("Start");
        addText4.setText("End");
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

        performanceTable.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            timeHallTable.setItems(FXCollections.observableList(timeHallService.getAll().stream()
                    .filter(el -> el.getIdPerformance() == performanceTable.getSelectionModel().getSelectedItem().getId())
                    .toList()
            ));
            timeHallTable.refresh();
        });

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

        timeHallTable.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            placeTable.setItems(FXCollections.observableList(placeService.getAll().stream()
                    .filter(el -> el.getIdHall() == timeHallTable.getSelectionModel().getSelectedItem().getId())
                    .toList()
            ));
            placeTable.refresh();
        });

//        timeHallTable.setItems(FXCollections.observableList(timeHallService.getAll()));
    }

    public enum TableStatus {
        HALL,
        PLACE,
        TIME_HALL
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
