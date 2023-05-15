//package com.example.theater.controller.performance.play;
//
//import com.example.theater.controller.employee.ActorController;
//import javafx.fxml.FXML;
//import javafx.scene.control.Button;
//import javafx.scene.control.DatePicker;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextField;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.text.Text;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class QueryController {
//    @FXML private ListView<String> queries;
//    @FXML private Text searchText1;
//    @FXML private Text searchText2;
//    @FXML private Text searchText3;
//    @FXML private Text searchText4;
//    @FXML private Text searchText5;
//    @FXML private Text searchText6;
//    @FXML private Text searchText7;
//    @FXML private Text searchText8;
//    @FXML private Text searchText9;
//    @FXML private Text searchText10;
//    @FXML private Text searchText11;
//    @FXML private TextField searchField1;
//    @FXML private TextField searchField2;
//    @FXML private TextField searchField3;
//    @FXML private TextField searchField4;
//    @FXML private DatePicker searchField5;
//    @FXML private DatePicker searchField6;
//    @FXML private TextField searchField7;
//    @FXML private TextField searchField8;
//    @FXML private TextField searchField9;
//    @FXML private TextField searchField10;
//    @FXML private DatePicker searchField11;
//    @FXML private TextField result;
//    @FXML private Button search;
//    private QueryStatus queryStatus = QueryStatus.QUERY1;
//    private final Map<QueryStatus, String> queryMap = new HashMap<>();
//
//
//
//    @FXML
//    public void initialize() {
//        queryMap.put(QueryStatus.QUERY0, "Получить всех актеpов");
//        queryMap.put(QueryStatus.QUERY1, "Получить актеpов, по атрибутам работника");
//        queryMap.put(QueryStatus.QUERY2, "Получить актеpов по атрибутам звания");
//        initQueryList();
//        search.setOnAction(event -> searchEvent());
//    }
//
//    public void searchEvent() {
//
//    }
//
//    private void initQueryList() {
//        queries.getItems().add(queryMap.get(ActorController.QueryStatus.QUERY0));
//        queries.getItems().add(queryMap.get(ActorController.QueryStatus.QUERY1));
//        queries.getItems().add(queryMap.get(ActorController.QueryStatus.QUERY2));
//        queries.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
//            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(ActorController.QueryStatus.QUERY0))) {
//                queryStatus = QueryStatus.QUERY0;
//            }
//            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(ActorController.QueryStatus.QUERY1))) {
//                queryStatus = QueryStatus.QUERY1;
//                searchField4.setVisible(true);
//                searchField7.setVisible(true);
//                searchField8.setVisible(true);
//                searchField9.setVisible(true);
//                searchField10.setVisible(true);
//                searchField11.setVisible(false);
//                searchText4.setVisible(true);
//                searchText7.setVisible(true);
//                searchText8.setVisible(true);
//                searchText9.setVisible(true);
//                searchText10.setVisible(true);
//                searchText11.setVisible(false);
//                searchText1.setText("theater");
//                searchText2.setText("from exp");
//                searchText3.setText("before exp");
//                searchText4.setText("gender");
//                searchText5.setText("from birthday");
//                searchText6.setText("before birthday");
//                searchText7.setText("from countChild");
//                searchText8.setText("before countChild");
//                searchText9.setText("from salary");
//                searchText10.setText("before salary");
//            }
//            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(ActorController.QueryStatus.QUERY2))) {
//                queryStatus = QueryStatus.QUERY2;
//                searchField4.setVisible(false);
//                searchField7.setVisible(false);
//                searchField8.setVisible(false);
//                searchField9.setVisible(false);
//                searchField10.setVisible(false);
//                searchField11.setVisible(true);
//                searchText4.setVisible(false);
//                searchText7.setVisible(false);
//                searchText8.setVisible(false);
//                searchText9.setVisible(false);
//                searchText10.setVisible(false);
//                searchText11.setVisible(true);
//                searchText1.setText("theater");
//                searchText2.setText("contest");
//                searchText3.setText("gender");
//                searchText5.setText("from data rank");
//                searchText6.setText("before data rank");
//                searchText11.setText("from birthday");
//            }
//        });
//    }
//}
