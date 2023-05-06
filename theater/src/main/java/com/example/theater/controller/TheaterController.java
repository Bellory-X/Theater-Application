package com.example.theater.controller;

import com.example.theater.controller.employee.EmployeeController;
import com.example.theater.controller.performance.PerformanceController;
import com.example.theater.dto.SubscriptionDTO;
import com.example.theater.dto.TheaterDTO;
import com.example.theater.exception.QueryException;
import com.example.theater.service.SubscriptionService;
import com.example.theater.service.TheaterService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/controller/theater-view.fxml")
public class TheaterController {
    @FXML private Button subscription;
    @FXML private Button theater;
    @FXML private Button performance;
    @FXML private Button employee;
    @FXML private Button add;
    @FXML private Button edit;
    @FXML private Button drop;
    @FXML private Button back;
    @FXML private Button close;
    @FXML private Text nameText;
    @FXML private Text priceText;
    @FXML private TextField name;
    @FXML private TextField price;
    @FXML private TextField result;
    @FXML private TableView<TheaterDTO> theaterTable;
    @FXML private TableView<SubscriptionDTO> subscriptionsTable;
    private final TheaterService theaterService;
    private final SubscriptionService subscriptionService;
    private final FxWeaver fxWeaver;
    private Status status = Status.THEATER;

    TheaterController(TheaterService theaterService, SubscriptionService subscriptionService, FxWeaver fxWeaver) {
        this.theaterService = theaterService;
        this.subscriptionService = subscriptionService;
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        initTheaterTable();
        initSubscriptionTable();
        clickButton();
    }
    private void initTheaterTable() {
        TableColumn<TheaterDTO, String> column = new TableColumn<>("Name");
        column.setCellValueFactory(new PropertyValueFactory<>("name"));
        theaterTable.getColumns().add(column);
        theaterTable.setItems(FXCollections.observableList(theaterService.getAll()));
    }

    private void initSubscriptionTable() {
        TableColumn<SubscriptionDTO, Integer> column1 = new TableColumn<>("Count");
        column1.setCellValueFactory(new PropertyValueFactory<>("count"));
        subscriptionsTable.getColumns().add(column1);
        TableColumn<SubscriptionDTO, Integer> column2 = new TableColumn<>("Price");
        column2.setCellValueFactory(new PropertyValueFactory<>("price"));
        subscriptionsTable.getColumns().add(column2);
        subscriptionsTable.setItems(FXCollections.observableList(subscriptionService.getAll()));
    }

    private void clickButton() {
        theater.setOnAction(event -> {
            status = Status.THEATER;
            nameText.setText("Name");
            priceText.setVisible(false);
            price.setVisible(false);
        });
        subscription.setOnAction(event -> {
            status = Status.SUBSCRIPTION;
            nameText.setText("Count");
            priceText.setVisible(true);
            price.setVisible(true);
        });
        employee.setOnAction(event -> showNewStage(fxWeaver.loadView(EmployeeController.class)));
        performance.setOnAction(event -> showNewStage(fxWeaver.loadView(PerformanceController.class)));
        add.setOnAction(event -> addEvent());
        edit.setOnAction(event -> editEvent());
        drop.setOnAction(event -> dropEvent());
        back.setOnAction(event -> back.getScene().getWindow().hide());
        close.setOnAction(event -> close.getScene().getWindow().hide());
    }

    private void showNewStage(Parent parent) {
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private void addEvent() {
        try {
            switch (status) {
                case THEATER -> {
                    theaterService.add(TheaterDTO.builder().name(name.getText()).build());
                    theaterTable.setItems(FXCollections.observableList(theaterService.getAll()));
                    theaterTable.refresh();
                }
                case SUBSCRIPTION -> {
                    subscriptionService.add(SubscriptionDTO.builder()
                            .count(Integer.parseInt(name.getText()))
                            .price(Integer.parseInt(price.getText()))
                            .build());
                    subscriptionsTable.setItems(FXCollections.observableList(subscriptionService.getAll()));
                    subscriptionsTable.refresh();
                }
            }
            result.setText("Success");
        } catch (NumberFormatException e) {
            result.setText("In one of the fields not number");
        } catch (QueryException e) {
            result.setText(e.getMessage());
        }
    }

    private void editEvent() {
        try {
            switch (status) {
                case THEATER -> {
                    TheaterDTO dto = theaterTable.getSelectionModel().getSelectedItem();
                    dto.setName(name.getText());
                    theaterService.edit(dto);
                    theaterTable.setItems(FXCollections.observableList(theaterService.getAll()));
                    theaterTable.refresh();
                }
                case SUBSCRIPTION -> {
                    SubscriptionDTO dto = subscriptionsTable.getSelectionModel().getSelectedItem();
                    dto.setCount(Integer.parseInt(name.getText()));
                    dto.setPrice(Integer.parseInt(price.getText()));
                    subscriptionService.edit(dto);
                    subscriptionsTable.setItems(FXCollections.observableList(subscriptionService.getAll()));
                    subscriptionsTable.refresh();
                }
            }
            result.setText("Success");
        } catch (NumberFormatException e) {
            result.setText("In one of the fields not number");
        } catch (QueryException e) {
            result.setText(e.getMessage());
        }
    }

    private void dropEvent() {
        try {
            switch (status) {
                case THEATER -> {
                    theaterService.drop(theaterTable.getSelectionModel().getSelectedItem());
                    theaterTable.setItems(FXCollections.observableList(theaterService.getAll()));
                    theaterTable.refresh();
                }
                case SUBSCRIPTION -> {
                    subscriptionService.drop(subscriptionsTable.getSelectionModel().getSelectedItem());
                    subscriptionsTable.setItems(FXCollections.observableList(subscriptionService.getAll()));
                    subscriptionsTable.refresh();
                }
            }
            result.setText("Success");
        } catch (NumberFormatException e) {
            result.setText("In one of the fields not number");
        } catch (QueryException e) {
            result.setText(e.getMessage());
        }
    }

    public enum Status {
        THEATER,
        SUBSCRIPTION
    }
}
