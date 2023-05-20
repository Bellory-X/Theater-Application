package com.example.theater.controller.performance;

import com.example.theater.controller.TheaterController;
import com.example.theater.dto.performance.PerformanceDTO;
import com.example.theater.dto.performance.troupe.DirectorsPerformanceDTO;
import com.example.theater.dto.performance.troupe.MusiciansPerformanceDTO;
import com.example.theater.dto.performance.troupe.RoleDTO;
import com.example.theater.dto.performance.troupe.RolesActorDTO;
import com.example.theater.service.performance.PerformanceService;
import com.example.theater.service.performance.troupe.DirectorsPerformanceService;
import com.example.theater.service.performance.troupe.MusiciansPerformanceService;
import com.example.theater.service.performance.troupe.RoleService;
import com.example.theater.service.performance.troupe.RolesActorService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@FxmlView("/controller/performance/troupe-view.fxml")
public class TroupeController {
    /*TODO:
    *  queries and add, edit, drop*/
    public Button back;
    public Button close;
    public Button musicians;
    public Button directors;
    public Button actors;
    public Button search;
    public Button add;
    public Button edit;
    public Button drop;
    public Button roles;
    public TableView<PerformanceDTO> performanceTable;
    public TableView<RolesActorDTO> actorsTable;
    public TableView<DirectorsPerformanceDTO> directorsTable;
    public TableView<MusiciansPerformanceDTO> musiciansTable;
    public TableView<RoleDTO> rolesTable;
    public ListView<String> queries;
    public TextField result;
    private final PerformanceService performanceService;
    private final RoleService roleService;
    private final RolesActorService rolesActorService;
    private final DirectorsPerformanceService directorsPerformanceService;
    private final MusiciansPerformanceService musiciansPerformanceService;
    private final FxWeaver fxWeaver;

    public TroupeController(PerformanceService performanceService, RoleService roleService,
                            RolesActorService rolesActorService, DirectorsPerformanceService directorsPerformanceService,
                            MusiciansPerformanceService musiciansPerformanceService, FxWeaver fxWeaver) {
        this.performanceService = performanceService;
        this.roleService = roleService;
        this.rolesActorService = rolesActorService;
        this.directorsPerformanceService = directorsPerformanceService;
        this.musiciansPerformanceService = musiciansPerformanceService;
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        initPerformanceTable();
        initRoleTable();
        initActorTable();
        initDirectorTable();
        initMusicianTable();
        clickButton();
    }

    private void clickButton() {
        actors.setOnAction(event -> {});
        directors.setOnAction(event -> {});
        musicians.setOnAction(event -> {});
        add.setOnAction(event -> {});
        edit.setOnAction(event -> {});
        drop.setOnAction(event -> {});
        search.setOnAction(event -> {});
        roles.setOnAction(event -> showNewStage(fxWeaver.loadView(RoleController.class)));
        back.setOnAction(event -> showNewStage(fxWeaver.loadView(TheaterController.class)));
        close.setOnAction(event -> close.getScene().getWindow().hide());
    }

    private void showNewStage(Parent parent) {
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private void initMusicianTable() {
        TableColumn<MusiciansPerformanceDTO, String> column0 = new TableColumn<>("Full Name");
        column0.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        musiciansTable.getColumns().add(column0);
    }

    private void initDirectorTable() {
        TableColumn<DirectorsPerformanceDTO, String> column0 = new TableColumn<>("Full Name");
        column0.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        directorsTable.getColumns().add(column0);

        TableColumn<DirectorsPerformanceDTO, String> column1 = new TableColumn<>("Category");
        column1.setCellValueFactory(new PropertyValueFactory<>("category"));
        directorsTable.getColumns().add(column1);
    }

    private void initActorTable() {
        TableColumn<RolesActorDTO, String> column0 = new TableColumn<>("Full Name");
        column0.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        actorsTable.getColumns().add(column0);

        TableColumn<RolesActorDTO, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        actorsTable.getColumns().add(column1);

        TableColumn<RolesActorDTO, Boolean> column2 = new TableColumn<>("Main");
        column2.setCellValueFactory(new PropertyValueFactory<>("main"));
        actorsTable.getColumns().add(column2);

        TableColumn<RolesActorDTO, Boolean> column3 = new TableColumn<>("Understudy");
        column3.setCellValueFactory(new PropertyValueFactory<>("understudy"));
        actorsTable.getColumns().add(column3);
    }

    private void initRoleTable() {
        TableColumn<RoleDTO, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        rolesTable.getColumns().add(column1);

        TableColumn<RoleDTO, Boolean> column2 = new TableColumn<>("Main");
        column2.setCellValueFactory(new PropertyValueFactory<>("main"));
        rolesTable.getColumns().add(column2);

        TableColumn<RoleDTO, Boolean> column3 = new TableColumn<>("Understudy");
        column3.setCellValueFactory(new PropertyValueFactory<>("understudy"));
        rolesTable.getColumns().add(column3);
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
        column4.setCellValueFactory(new PropertyValueFactory<>("genre"));
        performanceTable.getColumns().add(column4);

        TableColumn<PerformanceDTO, Integer> column5 = new TableColumn<>("Price");
        column5.setCellValueFactory(new PropertyValueFactory<>("price"));
        performanceTable.getColumns().add(column5);

        TableColumn<PerformanceDTO, String> column6 = new TableColumn<>("Theater");
        column6.setCellValueFactory(new PropertyValueFactory<>("theater"));
        performanceTable.getColumns().add(column6);

        performanceTable.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            rolesTable.setItems(FXCollections.observableList(roleService.getAll().stream()
                    .filter(el -> el.getIdPerformance() == performanceTable.getSelectionModel().getSelectedItem().getId())
                    .toList()));
            actorsTable.setItems(FXCollections.observableList(rolesActorService.getAll().stream()
                    .filter(el -> el.getIdPerformance() == performanceTable.getSelectionModel().getSelectedItem().getId())
                    .toList()));
            musiciansTable.setItems(FXCollections.observableList(musiciansPerformanceService.getAll().stream()
                    .filter(el -> el.getIdPerformance() == performanceTable.getSelectionModel().getSelectedItem().getId())
                    .toList()));
            directorsTable.setItems(FXCollections.observableList(directorsPerformanceService.getAll().stream()
                    .filter(el -> el.getIdPerformance() == performanceTable.getSelectionModel().getSelectedItem().getId())
                    .toList()));
        });

        performanceTable.setItems(FXCollections.observableList(performanceService.getAll()));
    }

    public enum TableStatus {
        ACTORS,
        MUSICIANS,
        DIRECTORS
    }

    public enum QueryStatus {
        QUERY0,
        QUERY1
    }
}
