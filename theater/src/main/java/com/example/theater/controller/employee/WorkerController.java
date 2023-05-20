package com.example.theater.controller.employee;

import com.example.theater.dto.employee.WorkerDTO;
import com.example.theater.dto.employee.category.WorkerCategoryDTO;
import com.example.theater.dto.employee.character.CharactersWorkerDTO;
import com.example.theater.dto.employee.character.WorkerCharacterDTO;
import com.example.theater.exception.ItemException;
import com.example.theater.exception.QueryException;
import com.example.theater.service.employee.WorkerService;
import com.example.theater.service.employee.category.WorkerCategoryService;
import com.example.theater.service.employee.character.CharactersWorkerService;
import com.example.theater.service.employee.character.WorkerCharacterService;
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
@FxmlView("/controller/employee/worker-view.fxml")
public class WorkerController {
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
    @FXML private TextField searchField1;
    @FXML private TextField searchField2;
    @FXML private TextField searchField3;
    @FXML private TextField searchField4;
    @FXML private DatePicker searchField5;
    @FXML private DatePicker searchField6;
    @FXML private TextField searchField7;
    @FXML private TextField searchField8;
    @FXML private TextField searchField9;
    @FXML private TextField searchField10;
    @FXML private TextField result;
    @FXML private ListView<String> queries;
    @FXML private TableView<CharactersWorkerDTO> charactersTable;
    @FXML private TableView<WorkerCharacterDTO> characterTable;
    @FXML private TableView<WorkerCategoryDTO> categoryTable;
    @FXML private TableView<WorkerDTO> workerTable;
    @FXML private Button search;
    @FXML private Button worker;
    @FXML private Button category;
    @FXML private Button character;
    @FXML private Button characters;
    @FXML private Button close;
    @FXML private Button back;
    @FXML private Button drop;
    @FXML private Button edit;
    @FXML private Button add;
    private WorkerTableStatus tableStatus = WorkerTableStatus.WORKER;
    private WorkerQueryStatus queryStatus = WorkerQueryStatus.QUERY0;
    private final WorkerService workerService;
    private final WorkerCategoryService categoryService;
    private final WorkerCharacterService characterService;
    private final CharactersWorkerService charactersService;
    private final FxWeaver fxWeaver;
    private final Map<WorkerQueryStatus, String> queryMap = new HashMap<>();

    public WorkerController(WorkerService workerService, WorkerCategoryService categoryService,
                            WorkerCharacterService characterService,
                            CharactersWorkerService charactersService, FxWeaver fxWeaver) {
        this.workerService = workerService;
        this.categoryService = categoryService;
        this.characterService = characterService;
        this.charactersService = charactersService;
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
        add.setOnAction(event -> addEvent());
        edit.setOnAction(event -> editEvent());
        drop.setOnAction(event -> dropEvent());
        worker.setOnAction(event -> editorEvent(WorkerTableStatus.WORKER, "full name", true, true));
        category.setOnAction(event -> editorEvent(WorkerTableStatus.CATEGORY, "category", true, false));
        character.setOnAction(event -> editorEvent(WorkerTableStatus.CHARACTER, "character", true, false));
        characters.setOnAction(event -> editorEvent(WorkerTableStatus.CHARACTERS, "", false, false));
        back.setOnAction(event -> showNewStage(fxWeaver.loadView(EmployeeController.class)));
        close.setOnAction(event -> close.getScene().getWindow().hide());
        search.setOnAction(event -> searchEvent());
    }

    private void showNewStage(Parent parent) {
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private void dropEvent() {
        try {
            switch (tableStatus) {
                case WORKER -> {
                    if (workerTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    workerService.drop(workerTable.getSelectionModel().getSelectedItem());
                    workerTable.setItems(FXCollections.observableList(workerService.getAll()));
                }
                case CATEGORY -> {
                    if (categoryTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    categoryService.drop(categoryTable.getSelectionModel().getSelectedItem());
                    categoryTable.setItems(FXCollections.observableList(categoryService.getAll()));
                }
                case CHARACTER -> {
                    if (characterTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    characterService.drop(characterTable.getSelectionModel().getSelectedItem());
                    characterTable.setItems(FXCollections.observableList(characterService.getAll()));
                }
                case CHARACTERS -> {
                    if (charactersTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    charactersService.drop(charactersTable.getSelectionModel().getSelectedItem());
                    charactersTable.setItems(FXCollections.observableList(charactersService.getAll().stream()
                            .filter(el -> el.getIdEmployee() == workerTable.getSelectionModel().getSelectedItem().getIdEmployee())
                            .toList()));
                }
            }
            result.setText("Success");
        } catch (NumberFormatException e) {
            result.setText("experience or count child or salary not positive number");
        } catch (QueryException | ItemException e) {
            result.setText(e.getMessage());
        }
    }

    private void editEvent() {
        try {
            switch (tableStatus) {
                case WORKER -> {
                    if (workerTable.getSelectionModel().getSelectedItem() == null ||
                            categoryTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    workerService.edit(WorkerDTO.builder()
                            .idEmployee(workerTable.getSelectionModel().getSelectedItem().getIdEmployee())
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
                    workerTable.setItems(FXCollections.observableList(workerService.getAll()));
                }
                case CATEGORY -> {
                    if (categoryTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    categoryService.edit(WorkerCategoryDTO.builder()
                            .id(categoryTable.getSelectionModel().getSelectedItem().getId())
                            .category(addField1.getText())
                            .build());
                    categoryTable.setItems(FXCollections.observableList(categoryService.getAll()));
                }
                case CHARACTER -> {
                    if (characterTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    characterService.edit(WorkerCharacterDTO.builder()
                            .id(characterTable.getSelectionModel().getSelectedItem().getId())
                            .character(addField1.getText())
                            .build());
                    characterTable.setItems(FXCollections.observableList(characterService.getAll()));
                }
                case CHARACTERS -> {
                    if (characterTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    charactersService.edit(CharactersWorkerDTO.builder()
                            .id(charactersTable.getSelectionModel().getSelectedItem().getId())
                            .idEmployee(workerTable.getSelectionModel().getSelectedItem().getIdEmployee())
                            .character(characterTable.getSelectionModel().getSelectedItem().getCharacter())
                            .build());
                    charactersTable.setItems(FXCollections.observableList(charactersService.getAll().stream()
                            .filter(el -> el.getIdEmployee() == workerTable.getSelectionModel().getSelectedItem().getIdEmployee())
                            .toList()));
                }
            }
            result.setText("Success");
        } catch (NumberFormatException e) {
            result.setText("experience or count child or salary not positive number");
        } catch (QueryException | ItemException e) {
            result.setText(e.getMessage());
        }
    }

    private void addEvent() {
        try {
            switch (tableStatus) {
                case WORKER -> {
                    if (categoryTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    workerService.add(WorkerDTO.builder().fullName(addField1.getText())
                            .experience(Integer.parseInt(addField2.getText()))
                            .gender(addField3.getText())
                            .birthday(Date.from(addField4.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                            .countChild(Integer.parseInt(addField5.getText()))
                            .salary(Integer.parseInt(addField6.getText()))
                            .worker(Boolean.parseBoolean(addField7.getText()))
                            .theater(addField8.getText())
                            .category(categoryTable.getSelectionModel().getSelectedItem().getCategory())
                            .build());
                    workerTable.setItems(FXCollections.observableList(workerService.getAll()));
                }
                case CATEGORY -> {
                    categoryService.add(WorkerCategoryDTO.builder().category(addField1.getText()).build());
                    categoryTable.setItems(FXCollections.observableList(categoryService.getAll()));
                }
                case CHARACTER -> {
                    characterService.add(WorkerCharacterDTO.builder().character(addField1.getText()).build());
                    characterTable.setItems(FXCollections.observableList(characterService.getAll()));
                }
                case CHARACTERS -> {
                    if (workerTable.getSelectionModel().getSelectedItem() == null ||
                            characterTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    charactersService.add(CharactersWorkerDTO.builder()
                            .idEmployee(workerTable.getSelectionModel().getSelectedItem().getIdEmployee())
                            .character(characterTable.getSelectionModel().getSelectedItem().getCharacter())
                            .build());
                    charactersTable.setItems(FXCollections.observableList(charactersService.getAll().stream()
                            .filter(el -> el.getIdEmployee() == workerTable.getSelectionModel().getSelectedItem().getIdEmployee())
                            .toList()));
                }
            }
            result.setText("Success");
        } catch (NumberFormatException e) {
            result.setText("experience or count child or salary not positive number");
        } catch (QueryException | ItemException e) {
            result.setText(e.getMessage());
        }
    }

    private void editorEvent(WorkerTableStatus status, String text1, boolean visible1, boolean visibleRest) {
        tableStatus = status;
        addField1.setVisible(visible1);
        addField2.setVisible(visibleRest);
        addField3.setVisible(visibleRest);
        addField4.setVisible(visibleRest);
        addField5.setVisible(visibleRest);
        addField6.setVisible(visibleRest);
        addField7.setVisible(visibleRest);
        addField8.setVisible(visibleRest);
        addText1.setVisible(visible1);
        addText2.setVisible(visibleRest);
        addText3.setVisible(visibleRest);
        addText4.setVisible(visibleRest);
        addText5.setVisible(visibleRest);
        addText6.setVisible(visibleRest);
        addText7.setVisible(visibleRest);
        addText8.setVisible(visibleRest);
        addText1.setText(text1);
    }

    private void searchEvent() {
        switch (queryStatus) {
            case QUERY0 -> {
                List<WorkerDTO> dtoList = workerService.getAll();
                result.setText(String.valueOf(dtoList.size()));
                workerTable.setItems(FXCollections.observableList(dtoList));
            }
            case QUERY1 -> {
                try {
                    List<WorkerDTO> dtoList = workerService.findActorQuery1(
                            searchField1.getText(),
                            Integer.parseInt(searchField2.getText()),
                            Integer.parseInt(searchField3.getText()),
                            searchField4.getText(),
                            Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                            Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                            Integer.parseInt(searchField7.getText()),
                            Integer.parseInt(searchField8.getText()),
                            Integer.parseInt(searchField9.getText()),
                            Integer.parseInt(searchField10.getText()));
                    result.setText(String.valueOf(dtoList.size()));
                    workerTable.setItems(FXCollections.observableList(dtoList));
                } catch (NumberFormatException e) {
                    result.setText("All fields except theater and gender must be positive number");
                }
            }
        }
    }

    private void initQueryList() {
        queryMap.put(WorkerQueryStatus.QUERY0, "Получить всех служащих");
        queryMap.put(WorkerQueryStatus.QUERY1, "Получить служащих, по атрибутам работника");
        queries.getItems().add(queryMap.get(WorkerQueryStatus.QUERY0));
        queries.getItems().add(queryMap.get(WorkerQueryStatus.QUERY1));
        queries.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(WorkerQueryStatus.QUERY0)))
                queryStatus = WorkerQueryStatus.QUERY0;
            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(WorkerQueryStatus.QUERY1)))
                queryStatus = WorkerQueryStatus.QUERY1;
        });
    }

    private void initWorkerTable() {
        TableColumn<WorkerDTO, String> column1 = new TableColumn<>("Full Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        workerTable.getColumns().add(column1);

        TableColumn<WorkerDTO, Integer> column2 = new TableColumn<>("Experience");
        column2.setCellValueFactory(new PropertyValueFactory<>("experience"));
        workerTable.getColumns().add(column2);

        TableColumn<WorkerDTO, String> column3 = new TableColumn<>("Gender");
        column3.setCellValueFactory(new PropertyValueFactory<>("gender"));
        workerTable.getColumns().add(column3);

        TableColumn<WorkerDTO, Date> column4 = new TableColumn<>("Birthday");
        column4.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        workerTable.getColumns().add(column4);

        TableColumn<WorkerDTO, Integer> column5 = new TableColumn<>("Count child");
        column5.setCellValueFactory(new PropertyValueFactory<>("countChild"));
        workerTable.getColumns().add(column5);

        TableColumn<WorkerDTO, Integer> column6 = new TableColumn<>("Salary");
        column6.setCellValueFactory(new PropertyValueFactory<>("salary"));
        workerTable.getColumns().add(column6);

        TableColumn<WorkerDTO, Boolean> column7 = new TableColumn<>("Is Worker");
        column7.setCellValueFactory(new PropertyValueFactory<>("worker"));
        workerTable.getColumns().add(column7);

        TableColumn<WorkerDTO, String> column8 = new TableColumn<>("Theater");
        column8.setCellValueFactory(new PropertyValueFactory<>("theater"));
        workerTable.getColumns().add(column8);

        TableColumn<WorkerDTO, String> column9 = new TableColumn<>("Category");
        column9.setCellValueFactory(new PropertyValueFactory<>("category"));
        workerTable.getColumns().add(column9);

        workerTable.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->
                charactersTable.setItems(FXCollections.observableList(charactersService.getAll().stream()
                        .filter(el -> el.getIdEmployee() == workerTable.getSelectionModel().getSelectedItem().getIdEmployee())
                        .toList())));

        workerTable.setItems(FXCollections.observableList(workerService.getAll()));
    }

    private void initCategoryTable() {
        TableColumn<WorkerCategoryDTO, String> column = new TableColumn<>("Category");
        column.setCellValueFactory(new PropertyValueFactory<>("category"));
        categoryTable.getColumns().add(column);

        categoryTable.setItems(FXCollections.observableList(categoryService.getAll()));
    }

    private void initCharacterTable() {
        TableColumn<WorkerCharacterDTO, String> column = new TableColumn<>("Character");
        column.setCellValueFactory(new PropertyValueFactory<>("character"));
        characterTable.getColumns().add(column);

        characterTable.setItems(FXCollections.observableList(characterService.getAll()));
    }

    private void initCharactersTable() {
        TableColumn<CharactersWorkerDTO, String> column = new TableColumn<>("Character");
        column.setCellValueFactory(new PropertyValueFactory<>("character"));
        charactersTable.getColumns().add(column);
    }
}
