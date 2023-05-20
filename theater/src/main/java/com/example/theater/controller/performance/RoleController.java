package com.example.theater.controller.performance;

import com.example.theater.dto.employee.ActorDTO;
import com.example.theater.dto.employee.character.ActorCharacterDTO;
import com.example.theater.dto.performance.PerformanceDTO;
import com.example.theater.dto.performance.troupe.RoleCharacterDTO;
import com.example.theater.dto.performance.troupe.RoleDTO;
import com.example.theater.dto.performance.troupe.RolesActorDTO;
import com.example.theater.service.employee.ActorService;
import com.example.theater.service.employee.character.ActorCharacterService;
import com.example.theater.service.performance.PerformanceService;
import com.example.theater.service.performance.troupe.RoleCharacterService;
import com.example.theater.service.performance.troupe.RoleService;
import com.example.theater.service.performance.troupe.RolesActorService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@FxmlView("/controller/performance/role-view.fxml")
public class RoleController {
    public Button back;
    public Button close;
    public Button characters;
    public Button character;
    public Button role;
    public Button search;
    public Button add;
    public Button edit;
    public Button drop;
    public TableView<PerformanceDTO> performanceTable;
    public TableView<RoleDTO> roleTable;
    public TableView<ActorCharacterDTO> characterTable;
    public TableView<RoleCharacterDTO> charactersTable;
    public TableView<ActorDTO> actorTable;
    public ListView<String> queries;
    public TextField result;
    private final PerformanceService performanceService;
    private final RoleService roleService;
    private final ActorCharacterService actorCharacterService;
    private final RoleCharacterService roleCharacterService;
    private final ActorService actorService;
    private final RolesActorService rolesActorService;
    private final FxWeaver fxWeaver;
    public TableView<RolesActorDTO> actorsTable;
    /*TODO:
           6.Получить список, подходящих по своим данным на указанную pоль
           8.Получить список, пpиезжавших на гастpоли, уезжавших на гастpоли
           9.Получить список для указанного спектакля:
           10.Получить перечень и общее число pолей*/
    /*TODO:
       8.Получить список, пpиезжавших на гастpоли, уезжавших на гастpоли*/

    /*TODO:
     *  Role
     *  Character
     *  Characters
     *  Actors*/

    public RoleController(PerformanceService performanceService, RoleService roleService,
                          ActorCharacterService actorCharacterService, RoleCharacterService roleCharacterService,
                          ActorService actorService, RolesActorService rolesActorService, FxWeaver fxWeaver) {
        this.performanceService = performanceService;
        this.roleService = roleService;
        this.actorCharacterService = actorCharacterService;
        this.roleCharacterService = roleCharacterService;
        this.actorService = actorService;
        this.rolesActorService = rolesActorService;
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        initPerformanceTable();
        initWorkerTable();
        initRoleTable();
        initActorsTable();
        initCharacterTable();
        initCharactersTable();
    }

    private void initCharactersTable() {
        TableColumn<RoleCharacterDTO, String> column = new TableColumn<>("Character");
        column.setCellValueFactory(new PropertyValueFactory<>("character"));
        charactersTable.getColumns().add(column);
    }

    private void initCharacterTable() {
        TableColumn<ActorCharacterDTO, String> column = new TableColumn<>("Character");
        column.setCellValueFactory(new PropertyValueFactory<>("character"));
        characterTable.getColumns().add(column);

        characterTable.setItems(FXCollections.observableList(actorCharacterService.getAll()));
    }

    private void initActorsTable() {
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
        roleTable.getColumns().add(column1);

        TableColumn<RoleDTO, Boolean> column2 = new TableColumn<>("Main");
        column2.setCellValueFactory(new PropertyValueFactory<>("main"));
        roleTable.getColumns().add(column2);

        TableColumn<RoleDTO, Boolean> column3 = new TableColumn<>("Understudy");
        column3.setCellValueFactory(new PropertyValueFactory<>("understudy"));
        roleTable.getColumns().add(column3);

        roleTable.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            charactersTable.setItems(FXCollections.observableList(roleCharacterService.getAll().stream()
                    .filter(el -> el.getIdRole() == roleTable.getSelectionModel().getSelectedItem().getId())
                    .toList()));
        });
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
        column7.setCellValueFactory(new PropertyValueFactory<>("worker"));
        actorTable.getColumns().add(column7);

        TableColumn<ActorDTO, String> column8 = new TableColumn<>("Theater");
        column8.setCellValueFactory(new PropertyValueFactory<>("theater"));
        actorTable.getColumns().add(column8);

        TableColumn<ActorDTO, String> column9 = new TableColumn<>("Category");
        column9.setCellValueFactory(new PropertyValueFactory<>("category"));
        actorTable.getColumns().add(column9);

        actorTable.setItems(FXCollections.observableList(actorService.getAll()));
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
            roleTable.setItems(FXCollections.observableList(roleService.getAll().stream()
                    .filter(el -> el.getIdPerformance() == performanceTable.getSelectionModel().getSelectedItem().getId())
                    .toList()));
            actorsTable.setItems(FXCollections.observableList(rolesActorService.getAll().stream()
                    .filter(el -> el.getIdPerformance() == performanceTable.getSelectionModel().getSelectedItem().getId())
                    .toList()));
        });

        performanceTable.setItems(FXCollections.observableList(performanceService.getAll()));
    }
}
