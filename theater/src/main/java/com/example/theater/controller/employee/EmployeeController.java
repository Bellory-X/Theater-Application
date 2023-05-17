package com.example.theater.controller.employee;

import com.example.theater.controller.TheaterController;
import com.example.theater.dto.employee.EmployeeDTO;
import com.example.theater.dto.employee.WorkerDTO;
import com.example.theater.service.employee.*;
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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@FxmlView("/controller/employee/employee-view.fxml")
public class EmployeeController {
    /*TODO:
       1.Получить список и общее число всех работников театра*/
    @FXML private Button employee;
    @FXML private ListView<String> queries;
    @FXML private Button search;
    @FXML private Button actor;
    @FXML private Button musician;
    @FXML private Button director;
    @FXML private Button worker;
    @FXML private Button back;
    @FXML private Button close;
    @FXML private TableView <EmployeeDTO> table;
    @FXML private TextField result;
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
    @FXML private Text searchText1;
    @FXML private Text searchText2;
    @FXML private Text searchText3;
    @FXML private Text searchText4;
    @FXML private Text searchText5;
    @FXML private Text searchText6;
    @FXML private TextField searchField8;
    @FXML private TextField searchField7;
    @FXML private TextField searchField3;
    @FXML private TextField searchField2;
    @FXML private DatePicker searchField5;
    @FXML private TextField searchField1;
    private final EmployeeService employeeService;
    private final FxWeaver fxWeaver;
    private QueryStatus queryStatus = QueryStatus.QUERY1;
    private final Map<QueryStatus, String> queryMap = new HashMap<>();

    public EmployeeController(EmployeeService employeeService, FxWeaver fxWeaver) {
        this.employeeService = employeeService;
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        queryMap.put(QueryStatus.QUERY0, "Получить всех служащих");
        queryMap.put(QueryStatus.QUERY1, "Получить служащих, по атрибутам работника");
        initTable();
        initQueryList();
        clickButton();
    }

    private void clickButton() {
        search.setOnAction(event -> searchEvent());
        employee.setOnAction(event -> {
            table.setItems(FXCollections.observableList(employeeService.getAll()));
            table.refresh();
        });
        actor.setOnAction(event -> showNewStage(fxWeaver.loadView(ActorController.class)));
        musician.setOnAction(event -> showNewStage(fxWeaver.loadView(MusicianController.class)));
        director.setOnAction(event -> showNewStage(fxWeaver.loadView(DirectorController.class)));
        worker.setOnAction(event -> showNewStage(fxWeaver.loadView(WorkerController.class)));
        back.setOnAction(event -> showNewStage(fxWeaver.loadView(TheaterController.class)));
        close.setOnAction(event -> close.getScene().getWindow().hide());
    }

    private void searchEvent() {
        table.getItems().clear();
        switch (queryStatus) {
            case QUERY0 -> {
                List<EmployeeDTO> dtoList = employeeService.getAll();
                result.setText(String.valueOf(dtoList.size()));
                table.getItems().addAll(dtoList);
            }
            case QUERY1 -> {
                List<EmployeeDTO> dtoList = employeeService
                        .findActorQuery1(searchField1.getText(), Integer.parseInt(searchField2.getText()),
                                Integer.parseInt(searchField3.getText()), searchField4.getText(),
                                Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                                Integer.parseInt(searchField7.getText()), Integer.parseInt(searchField8.getText()),
                                Integer.parseInt(searchField9.getText()), Integer.parseInt(searchField10.getText()));
                result.setText(String.valueOf(dtoList.size()));
                table.getItems().addAll(dtoList);
            }
        }
        table.refresh();
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

        TableColumn<EmployeeDTO, Boolean> column7 = new TableColumn<>("Is Worker");
        column7.setCellValueFactory(new PropertyValueFactory<>("worker"));
        table.getColumns().add(column7);

        TableColumn<EmployeeDTO, String> column8 = new TableColumn<>("Theater");
        column8.setCellValueFactory(new PropertyValueFactory<>("theater"));
        table.getColumns().add(column8);

        table.setItems(FXCollections.observableList(employeeService.getAll()));
    }

    public enum QueryStatus {
        QUERY0,
        QUERY1
    }
}
