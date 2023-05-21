package com.example.theater.controller.performance;

import com.example.theater.controller.TheaterController;
import com.example.theater.dto.employee.ActorDTO;
import com.example.theater.dto.employee.DirectorDTO;
import com.example.theater.dto.employee.MusicianDTO;
import com.example.theater.dto.employee.character.ActorCharacterDTO;
import com.example.theater.dto.performance.PerformanceDTO;
import com.example.theater.dto.performance.troupe.*;
import com.example.theater.exception.ItemException;
import com.example.theater.exception.QueryException;
import com.example.theater.service.employee.character.ActorCharacterService;
import com.example.theater.service.performance.PerformanceService;
import com.example.theater.service.performance.troupe.*;
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

import java.util.Date;

@Component
@FxmlView("/controller/performance/troupe-view.fxml")
public class TroupeController {
    public Button back;
    public Button close;
    public Button musicians;
    public Button directors;
    public Button actors;
//    public Button search;
    public Button add;
    public Button edit;
    public Button drop;
    public Button roles;
    public Button characters;
    public Text addText1;
    public Text addText2;
    public Text addText3;
    public TextField addField1;
    public TextField addField2;
    public TextField addField3;
    public ListView<String> queries;
    public TextField result;
    public TableView<ActorDTO> actorTable;
    public TableView<RoleDTO> roleTable;
    public TableView<ActorCharacterDTO> characterTable;
    public TableView<RoleCharacterDTO> charactersTable;
    public TableView<PerformanceDTO> performanceTable;
    public TableView<RolesActorDTO> actorsTable;
    public TableView<DirectorsPerformanceDTO> directorsTable;
    public TableView<MusiciansPerformanceDTO> musiciansTable;
    public TableView<DirectorDTO> directorTable;
    public TableView<MusicianDTO> musicianTable;
    private TableStatus tableStatus = TableStatus.ACTORS;
    private final PerformanceService performanceService;
    private final RolesActorService actorsService;
    private final DirectorsPerformanceService directorsService;
    private final MusiciansPerformanceService musiciansService;
    private final RoleService roleService;
    private final RoleCharacterService charactersService;
    private final ActorCharacterService characterService;
    private final FxWeaver fxWeaver;

    public TroupeController(PerformanceService performanceService, RolesActorService actorsService,
                            DirectorsPerformanceService directorsService, MusiciansPerformanceService musiciansService,
                            RoleService roleService, RoleCharacterService charactersService,
                            ActorCharacterService characterService, FxWeaver fxWeaver) {
        this.performanceService = performanceService;
        this.actorsService = actorsService;
        this.directorsService = directorsService;
        this.musiciansService = musiciansService;
        this.roleService = roleService;
        this.charactersService = charactersService;
        this.characterService = characterService;
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        initPerformanceTable();
        initActorsTable();
        initDirectorsTable();
        initMusiciansTable();
        initActorTable();
        initDirectorTable();
        initMusicianTable();
        initRoleTable();
        initCharacterTable();
        initCharactersTable();
        clickButton();
    }

    private void clickButton() {
        actors.setOnAction(event -> tableEvent(TableStatus.ACTORS, false));
        directors.setOnAction(event -> tableEvent(TableStatus.DIRECTORS, false));
        musicians.setOnAction(event -> tableEvent(TableStatus.MUSICIANS, false));
        roles.setOnAction(event -> tableEvent(TableStatus.ROLE, true));
        characters.setOnAction(event -> tableEvent(TableStatus.CHARACTERS, false));
        add.setOnAction(event -> addEvent());
//        edit.setOnAction(event -> editEvent());
        drop.setOnAction(event -> dropEvent());
        back.setOnAction(event -> showNewStage(fxWeaver.loadView(TheaterController.class)));
        close.setOnAction(event -> close.getScene().getWindow().hide());
    }

    private void dropEvent() {
        try {
            switch (tableStatus) {
                case ACTORS -> {
                    if (actorsTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    actorsService.drop(actorsTable.getSelectionModel().getSelectedItem());
                }
                case DIRECTORS -> {
                    if (directorsTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    directorsService.drop(directorsTable.getSelectionModel().getSelectedItem());
                }
                case MUSICIANS -> {
                    if (musiciansTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    musiciansService.drop(musiciansTable.getSelectionModel().getSelectedItem());
                }
                case ROLE -> {
                    if (roleTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    roleService.drop(roleTable.getSelectionModel().getSelectedItem());
                }
                case CHARACTERS -> {
                    if (charactersTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    charactersService.drop(charactersTable.getSelectionModel().getSelectedItem());
                }
            }
            result.setText("Success");
        } catch (ItemException e) {
            result.setText(e.getMessage());
        }
    }

    private void editEvent() {
        try {
            switch (tableStatus) {
                case ACTORS, DIRECTORS, MUSICIANS, CHARACTERS, ROLE -> {
                    result.setText("Function not available");
                }
            }
        } catch (ItemException e) {
            result.setText(e.getMessage());
        }
    }

    private void addEvent() {
        try {
            switch (tableStatus) {
                case ACTORS -> {
                    if (roleTable.getSelectionModel().getSelectedItem() == null ||
                            actorTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    actorsService.add(RolesActorDTO.builder()
                            .idRole(roleTable.getSelectionModel().getSelectedItem().getId())
                            .idEmployee(actorTable.getSelectionModel().getSelectedItem().getIdEmployee())
                            .build());
                }
                case DIRECTORS -> {
                    if (performanceTable.getSelectionModel().getSelectedItem() == null ||
                            directorsTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    directorsService.add(DirectorsPerformanceDTO.builder()
                            .idPerformance(performanceTable.getSelectionModel().getSelectedItem().getId())
                            .idEmployee(directorsTable.getSelectionModel().getSelectedItem().getIdEmployee())
                            .build());
                }
                case MUSICIANS -> {
                    if (performanceTable.getSelectionModel().getSelectedItem() == null ||
                            directorsTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    musiciansService.add(MusiciansPerformanceDTO.builder()
                            .idPerformance(performanceTable.getSelectionModel().getSelectedItem().getId())
                            .idEmployee(musiciansTable.getSelectionModel().getSelectedItem().getIdEmployee())
                            .build());
                }
                case ROLE -> {
                    if (performanceTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    roleService.add(RoleDTO.builder()
                            .name(addField1.getText())
                            .main(Boolean.parseBoolean(addField2.getText()))
                            .understudy(Boolean.parseBoolean(addField3.getText()))
                            .idPerformance(performanceTable.getSelectionModel().getSelectedItem().getId())
                            .build());
                }
                case CHARACTERS -> {
                    if (roleTable.getSelectionModel().getSelectedItem() == null ||
                            characterTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    charactersService.add(RoleCharacterDTO.builder()
                            .idRole(roleTable.getSelectionModel().getSelectedItem().getId())
                            .character(characterTable.getSelectionModel().getSelectedItem().getCharacter())
                            .build());
                }
            }
            result.setText("Success");
        } catch (QueryException | ItemException e) {
            result.setText(e.getMessage());
        }
    }

    private void tableEvent(TableStatus status, boolean visible) {
        tableStatus = status;
        addText1.setVisible(visible);
        addText2.setVisible(visible);
        addText3.setVisible(visible);
        addField1.setVisible(visible);
        addField2.setVisible(visible);
        addField3.setVisible(visible);
    }

    private void showNewStage(Parent parent) {
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private void initCharacterTable() {
        TableColumn<ActorCharacterDTO, String> column1 = new TableColumn<>("Character");
        column1.setCellValueFactory(new PropertyValueFactory<>("character"));
        characterTable.getColumns().add(column1);

        characterTable.setItems(FXCollections.observableList(characterService.getAll()));
    }

    private void initCharactersTable() {
        TableColumn<RoleCharacterDTO, String> column1 = new TableColumn<>("Character");
        column1.setCellValueFactory(new PropertyValueFactory<>("character"));
        charactersTable.getColumns().add(column1);
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
            actorTable.setItems(FXCollections.observableList(actorsService
                    .getAllActor(performanceTable.getSelectionModel().getSelectedItem().getTheater())
            ));
            charactersTable.setItems(FXCollections.observableList(charactersService.getAll().stream()
                    .filter(el -> el.getIdRole() == roleTable.getSelectionModel().getSelectedItem().getId())
                    .toList()
            ));
        });
    }

    private void initMusicianTable() {
        TableColumn<MusicianDTO, String> column1 = new TableColumn<>("Full Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        musicianTable.getColumns().add(column1);

        TableColumn<MusicianDTO, Integer> column2 = new TableColumn<>("Experience");
        column2.setCellValueFactory(new PropertyValueFactory<>("experience"));
        musicianTable.getColumns().add(column2);

        TableColumn<MusicianDTO, String> column3 = new TableColumn<>("Gender");
        column3.setCellValueFactory(new PropertyValueFactory<>("gender"));
        musicianTable.getColumns().add(column3);

        TableColumn<MusicianDTO, Boolean> column7 = new TableColumn<>("Is Worker");
        column7.setCellValueFactory(new PropertyValueFactory<>("worker"));
        musicianTable.getColumns().add(column7);

        TableColumn<MusicianDTO, String> column8 = new TableColumn<>("Theater");
        column8.setCellValueFactory(new PropertyValueFactory<>("theater"));
        musicianTable.getColumns().add(column8);

        TableColumn<MusicianDTO, String> column9 = new TableColumn<>("Category");
        column9.setCellValueFactory(new PropertyValueFactory<>("category"));
        musicianTable.getColumns().add(column9);
    }

    private void initDirectorTable() {
        TableColumn<DirectorDTO, String> column1 = new TableColumn<>("Full Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        directorTable.getColumns().add(column1);

        TableColumn<DirectorDTO, Integer> column2 = new TableColumn<>("Experience");
        column2.setCellValueFactory(new PropertyValueFactory<>("experience"));
        directorTable.getColumns().add(column2);

        TableColumn<DirectorDTO, String> column3 = new TableColumn<>("Gender");
        column3.setCellValueFactory(new PropertyValueFactory<>("gender"));
        directorTable.getColumns().add(column3);

        TableColumn<DirectorDTO, Boolean> column7 = new TableColumn<>("Is Worker");
        column7.setCellValueFactory(new PropertyValueFactory<>("worker"));
        directorTable.getColumns().add(column7);

        TableColumn<DirectorDTO, String> column8 = new TableColumn<>("Theater");
        column8.setCellValueFactory(new PropertyValueFactory<>("theater"));
        directorTable.getColumns().add(column8);

        TableColumn<DirectorDTO, String> column9 = new TableColumn<>("Category");
        column9.setCellValueFactory(new PropertyValueFactory<>("category"));
        directorTable.getColumns().add(column9);
    }

    private void initActorTable() {
        TableColumn<ActorDTO, String> column1 = new TableColumn<>("Full Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        actorTable.getColumns().add(column1);

        TableColumn<ActorDTO, Integer> column2 = new TableColumn<>("Experience");
        column2.setCellValueFactory(new PropertyValueFactory<>("experience"));
        actorTable.getColumns().add(column2);

        TableColumn<ActorDTO, String> column3 = new TableColumn<>("Gender");
        column3.setCellValueFactory(new PropertyValueFactory<>("gender"));
        actorTable.getColumns().add(column3);

        TableColumn<ActorDTO, Boolean> column7 = new TableColumn<>("Is Worker");
        column7.setCellValueFactory(new PropertyValueFactory<>("worker"));
        actorTable.getColumns().add(column7);

        TableColumn<ActorDTO, String> column8 = new TableColumn<>("Theater");
        column8.setCellValueFactory(new PropertyValueFactory<>("theater"));
        actorTable.getColumns().add(column8);

        TableColumn<ActorDTO, String> column9 = new TableColumn<>("Category");
        column9.setCellValueFactory(new PropertyValueFactory<>("category"));
        actorTable.getColumns().add(column9);
    }

    private void initMusiciansTable() {
        TableColumn<MusiciansPerformanceDTO, String> column0 = new TableColumn<>("Full Name");
        column0.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        musiciansTable.getColumns().add(column0);
    }

    private void initDirectorsTable() {
        TableColumn<DirectorsPerformanceDTO, String> column0 = new TableColumn<>("Full Name");
        column0.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        directorsTable.getColumns().add(column0);

        TableColumn<DirectorsPerformanceDTO, String> column1 = new TableColumn<>("Category");
        column1.setCellValueFactory(new PropertyValueFactory<>("category"));
        directorsTable.getColumns().add(column1);
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
            actorsTable.setItems(FXCollections.observableList(actorsService.getAll().stream()
                    .filter(el -> el.getIdPerformance() == performanceTable.getSelectionModel().getSelectedItem().getId())
                    .toList()));
            musiciansTable.setItems(FXCollections.observableList(musiciansService.getAll().stream()
                    .filter(el -> el.getIdPerformance() == performanceTable.getSelectionModel().getSelectedItem().getId())
                    .toList()));
            directorsTable.setItems(FXCollections.observableList(directorsService.getAll().stream()
                    .filter(el -> el.getIdPerformance() == performanceTable.getSelectionModel().getSelectedItem().getId())
                    .toList()));
            roleTable.setItems(FXCollections.observableList(roleService.getAll().stream()
                    .filter(el -> el.getIdPerformance() == performanceTable.getSelectionModel().getSelectedItem().getId())
                    .toList()));
            musicianTable.setItems(FXCollections.observableList(musiciansService
                    .getAllMusician(performanceTable.getSelectionModel().getSelectedItem().getTheater())));
            directorTable.setItems(FXCollections.observableList(directorsService
                    .getAllDirector(performanceTable.getSelectionModel().getSelectedItem().getTheater())));
        });

        performanceTable.setItems(FXCollections.observableList(performanceService.getAll()));
    }

    public enum TableStatus {
        ACTORS,
        MUSICIANS,
        DIRECTORS,
        ROLE,
        CHARACTERS
    }
}
