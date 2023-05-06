package com.example.theater.controller;

import com.example.theater.controller.employee.EmployeeController;
import com.example.theater.controller.performance.PerformanceController;
import com.example.theater.dto.TheaterDTO;
import com.example.theater.exception.QueryException;
import com.example.theater.service.TheaterService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/controller/theater-view.fxml")
public class TheaterController {
    @FXML private TextField result;
    @FXML private Button performance;
    @FXML private Button employee;
    @FXML private TextField name;
    @FXML private Button add;
    @FXML private Button edit;
    @FXML private Button drop;
    @FXML private Button back;
    @FXML private Button close;
    @FXML private TableView<TheaterDTO> table;
    private final TheaterService theaterService;
    private final FxWeaver fxWeaver;

    TheaterController(TheaterService theaterService, FxWeaver fxWeaver) {
        this.theaterService = theaterService;
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        TableColumn<TheaterDTO, String> column = new TableColumn<>("Name");
        column.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().add(column);
        table.setItems(FXCollections.observableList(theaterService.getAll()));
        clickButton();
    }

    private void clickButton() {
        employee.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setScene(new Scene(fxWeaver.loadView(EmployeeController.class)));
            stage.show();
        });
        performance.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setScene(new Scene(fxWeaver.loadView(PerformanceController.class)));
            stage.show();
        });
        add.setOnAction(actionEvent -> {
            try {
                theaterService.add(TheaterDTO.builder().name(name.getText()).build());
                table.setItems(FXCollections.observableList(theaterService.getAll()));
                table.refresh();
                result.setText("Success");
            } catch (QueryException e) {
                result.setText("Error");
            }
        });
        edit.setOnAction(actionEvent -> {
            try {
                TheaterDTO dto = table.getSelectionModel().getSelectedItem();
                dto.setName(name.getText());
                theaterService.edit(dto);
                table.setItems(FXCollections.observableList(theaterService.getAll()));
                table.refresh();
                result.setText("Success");
            } catch (QueryException e) {
                result.setText("Error");
            }
        });
        drop.setOnAction(actionEvent -> {
            try {
                theaterService.drop(table.getSelectionModel().getSelectedItem());
                table.setItems(FXCollections.observableList(theaterService.getAll()));
                table.refresh();
                result.setText("Success");
            } catch (QueryException e) {
                result.setText("Error");
            }
        });
        back.setOnAction(actionEvent -> back.getScene().getWindow().hide());
        close.setOnAction(actionEvent -> close.getScene().getWindow().hide());
    }
}
