package com.example.theater.controller.performance;

import com.example.theater.dto.performance.PerformanceDTO;
import com.example.theater.dto.performance.play.AuthorDTO;
import com.example.theater.dto.performance.play.GenreDTO;
import com.example.theater.dto.performance.play.PlayDTO;
import com.example.theater.dto.performance.play.PlaysAuthorDTO;
import com.example.theater.exception.ItemException;
import com.example.theater.exception.QueryException;
import com.example.theater.service.performance.PerformanceService;
import com.example.theater.service.performance.play.AuthorService;
import com.example.theater.service.performance.play.GenreService;
import com.example.theater.service.performance.play.PlayService;
import com.example.theater.service.performance.play.PlaysAuthorService;
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

import java.time.ZoneId;
import java.util.*;

@Component
@FxmlView("/controller/performance/play-view.fxml")
public class PlayController {
    /*TODO:
     *  2. Получить перечень и общее число спектаклей
     *  3.Получить перечень и общее число всех поставленных спектаклей
     *  4.Получить список автоpов поставленных спектаклей
     *  5.Получить перечень спектаклей*/
    public Button back;
    public Button close;
    public Button authors;
    public Button author;
    public Button play;
    public Button search;
    public Button add;
    public Button edit;
    public Button drop;
    public Button genre;
    public Button performance;
    public TableView<PlayDTO> playTable;
    public TableView<AuthorDTO> authorTable;
    public TableView<AuthorDTO> authorsTable;
    public TableView<GenreDTO> genreTable;
    public TableView<PerformanceDTO> performanceTable;
    public ListView<String> queries;
    public TextField result;
    public TextField searchField1;
    public Text searchText1;
    public TextField searchField2;
    public Text searchText2;
    public TextField searchField3;
    public Text searchText3;
    public TextField searchField4;
    public Text searchText4;
    public DatePicker searchField5;
    public Text searchText5;
    public DatePicker searchField6;
    public Text searchText6;
    public TextField searchField7;
    public Text searchText7;
    public TextField addField1;
    public Text addText1;
    public TextField addField2;
    public Text addText2;
    public TextField addField3;
    public Text addText3;
    public DatePicker addField4;
    public Text addText4;
    private final PerformanceService performanceService;
    private final PlayService playService;
    private final AuthorService authorService;
    private final PlaysAuthorService playsAuthorService;
    private final GenreService genreService;
    private final FxWeaver fxWeaver;
    public Button troupe;
    private TableStatus tableStatus = TableStatus.PERFORMANCE;
    private QueryStatus queryStatus = QueryStatus.QUERY0_1;
    private final Map<QueryStatus, String> queryMap = new HashMap<>();

    public PlayController(PerformanceService performanceService, PlayService playService,
                          AuthorService authorService, PlaysAuthorService playsAuthorService,
                          GenreService genreService, FxWeaver fxWeaver) {
        this.performanceService = performanceService;
        this.playService = playService;
        this.authorService = authorService;
        this.playsAuthorService = playsAuthorService;
        this.genreService = genreService;
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        initPerformanceTable();
        initPlayTable();
        initAuthorTable();
        initAuthorsTable();
        initGenreTable();
        eventButton();
        initQueries();
    }

    private void initQueries() {
        queryMap.put(QueryStatus.QUERY0, "Получить перечень спектаклей");
        queryMap.put(QueryStatus.QUERY0_1, "Получить перечень авторов");
        queryMap.put(QueryStatus.QUERY2, "Получить перечень спектаклей, сыгранных в этом театре");
        queryMap.put(QueryStatus.QUERY3, "Получить перечень спектаклей, поставленных в этом театре");
        queryMap.put(QueryStatus.QUERY4, "Получить список автоpов поставленных спектаклей");
        queryMap.put(QueryStatus.QUERY5, "Получить перечень спектаклей, некоторого автоpа");
        queries.getItems().add(queryMap.get(QueryStatus.QUERY0));
        queries.getItems().add(queryMap.get(QueryStatus.QUERY0_1));
        queries.getItems().add(queryMap.get(QueryStatus.QUERY2));
        queries.getItems().add(queryMap.get(QueryStatus.QUERY3));
        queries.getItems().add(queryMap.get(QueryStatus.QUERY4));
        queries.getItems().add(queryMap.get(QueryStatus.QUERY5));
        queries.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(QueryStatus.QUERY0))) {
                queryStatus = QueryStatus.QUERY0;
            }
            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(QueryStatus.QUERY0_1))) {
                queryStatus = QueryStatus.QUERY0_1;
            }
            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(QueryStatus.QUERY2))) {
                queryStatus = QueryStatus.QUERY2;
                searchText3.setVisible(false);
                searchText4.setVisible(false);
                searchField3.setVisible(false);
                searchField4.setVisible(false);
                searchText7.setVisible(false);
                searchField7.setVisible(false);
            }
            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(QueryStatus.QUERY3))) {
                queryStatus = QueryStatus.QUERY3;
                searchText3.setVisible(false);
                searchText4.setVisible(false);
                searchField3.setVisible(false);
                searchField4.setVisible(false);
                searchText7.setVisible(false);
                searchField7.setVisible(false);
            }
            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(QueryStatus.QUERY4))) {
                queryStatus = QueryStatus.QUERY4;
                searchText3.setVisible(true);
                searchText4.setVisible(true);
                searchField3.setVisible(true);
                searchField4.setVisible(true);
                searchText7.setVisible(true);
                searchField7.setVisible(true);
                searchText3.setText("country");
                searchText4.setText("birthday year from");
            }
            if (queries.getSelectionModel().getSelectedItem().equals(queryMap.get(QueryStatus.QUERY5))) {
                queryStatus = QueryStatus.QUERY5;
                searchText3.setVisible(true);
                searchText4.setVisible(true);
                searchField3.setVisible(true);
                searchField4.setVisible(true);
                searchText7.setVisible(false);
                searchField7.setVisible(false);
                searchText3.setText("play year from");
                searchText4.setText("play year to");
            }
        });
    }

    private void clickSearch() {
        try {
            switch (queryStatus) {
                case QUERY0 -> performanceTable.setItems(FXCollections.observableList(performanceService.getAll()));
                case QUERY0_1 -> authorTable.setItems(FXCollections.observableList(authorService.getAll()));
                case QUERY2 -> performanceTable.setItems(FXCollections.observableList(performanceService.findActorQuery2(
                        Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                        Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                        searchField1.getText(),
                        searchField2.getText()
                )));
                case QUERY3 -> performanceTable.setItems(FXCollections.observableList(performanceService.findActorQuery3(
                        Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                        Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                        searchField1.getText(),
                        searchField2.getText()
                )));
                case QUERY4 -> authorTable.setItems(FXCollections.observableList(authorService.findActorQuery4(
                        Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                        Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                        searchField1.getText(),
                        searchField2.getText(),
                        searchField3.getText(),
                        Integer.parseInt(searchField4.getText()),
                        Integer.parseInt(searchField7.getText())
                )));
                case QUERY5 -> performanceTable.setItems(FXCollections.observableList(performanceService.findActorQuery5(
                        Date.from(searchField5.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                        Date.from(searchField6.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),
                        searchField1.getText(),
                        searchField2.getText(),
                        authorTable.getSelectionModel().getSelectedItem().getCountry(),
                        authorTable.getSelectionModel().getSelectedItem().getFullName(),
                        Integer.parseInt(searchField3.getText()),
                        Integer.parseInt(searchField4.getText())
                )));
            }
            result.setText("Success");
        } catch (DataAccessException e) {
            result.setText("Recheck fields maybe theater or genre not exist");
        }
    }

    private void showNewStage(Parent parent) {
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    private void eventButton() {
        performance.setOnAction(event -> performanceEvent());
        play.setOnAction(event -> playEvent());
        author.setOnAction(event -> authorEvent());
        authors.setOnAction(event -> authorsEvent());
        genre.setOnAction(event -> genreEvent());
        add.setOnAction(event -> addEvent());
        edit.setOnAction(event -> editEvent());
        drop.setOnAction(event -> dropEvent());
        search.setOnAction(event -> clickSearch());
        troupe.setOnAction(event -> showNewStage(fxWeaver.loadView(TroupeController.class)));
        back.setOnAction(event -> showNewStage(fxWeaver.loadView(PlaceController.class)));
        close.setOnAction(event -> close.getScene().getWindow().hide());
        performanceEvent();
    }

    private void performanceEvent() {
        tableStatus = TableStatus.PERFORMANCE;
        addField1.setVisible(true);
        addField2.setVisible(false);
        addField3.setVisible(true);
        addField4.setVisible(false);
        addText1.setVisible(true);
        addText2.setVisible(false);
        addText3.setVisible(true);
        addText4.setVisible(false);
        addText1.setText("Price");
        addText3.setText("Theater");
    }

    private void playEvent() {
        tableStatus = TableStatus.PLAY;
        addField1.setVisible(true);
        addField2.setVisible(true);
        addField3.setVisible(false);
        addField4.setVisible(true);
        addText1.setVisible(true);
        addText2.setVisible(true);
        addText3.setVisible(false);
        addText4.setVisible(true);
        addText1.setText("Name");
        addText2.setText("Rating");
        addText4.setText("Data");
    }

    private void authorEvent() {
        tableStatus = TableStatus.AUTHOR;
        addField1.setVisible(true);
        addField2.setVisible(true);
        addField3.setVisible(false);
        addField4.setVisible(true);
        addText1.setVisible(true);
        addText2.setVisible(true);
        addText3.setVisible(false);
        addText4.setVisible(true);
        addText1.setText("Full Name");
        addText2.setText("Country");
        addText4.setText("Birthday");
    }

    private void authorsEvent() {
        tableStatus = TableStatus.AUTHORS;
        addField1.setVisible(false);
        addField2.setVisible(false);
        addField3.setVisible(false);
        addField4.setVisible(false);
        addText1.setVisible(false);
        addText2.setVisible(false);
        addText3.setVisible(false);
        addText4.setVisible(false);
    }

    private void genreEvent() {
        tableStatus = TableStatus.GENRE;
        addField1.setVisible(true);
        addField2.setVisible(false);
        addField3.setVisible(false);
        addField4.setVisible(false);
        addText1.setVisible(true);
        addText2.setVisible(false);
        addText3.setVisible(false);
        addText4.setVisible(false);
        addText1.setText("Name");
    }

    private void dropEvent() {
        try {
            switch (tableStatus) {
                case PERFORMANCE -> {
                    if (performanceTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    performanceService.drop(performanceTable.getSelectionModel().getSelectedItem());
                    performanceTable.setItems(FXCollections.observableList(performanceService.getAll()));
                }
                case PLAY -> {
                    if (playTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    playService.drop(playTable.getSelectionModel().getSelectedItem());
                    playTable.setItems(FXCollections.observableList(playService.getAll()));
                }
                case AUTHOR -> {
                    if (authorTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    authorService.drop(authorTable.getSelectionModel().getSelectedItem());
                    authorTable.setItems(FXCollections.observableList(authorService.getAll()));
                }
                case AUTHORS -> {
                    if (playTable.getSelectionModel().getSelectedItem() == null ||
                            authorsTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    playsAuthorService.getAll().stream()
                            .filter(el -> el.getIdPlay() == playTable.getSelectionModel().getSelectedItem().getId() &&
                                    el.getIdAuthor() == authorsTable.getSelectionModel().getSelectedItem().getId())
                            .findFirst()
                            .ifPresent(playsAuthorService::drop);
                    authorsTable.setItems(FXCollections.observableList(playsAuthorService.getAll().stream()
                            .filter(el -> el.getIdPlay() == playTable.getSelectionModel().getSelectedItem().getId())
                            .map(el -> authorService.getById(el.getIdAuthor()))
                            .filter(Optional::isPresent).map(Optional::get)
                            .toList()));
                }
                case GENRE -> {
                    if (genreTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    genreService.drop(genreTable.getSelectionModel().getSelectedItem());
                    genreTable.setItems(FXCollections.observableList(genreService.getAll()));
                }
            }
            result.setText("Success");
        } catch (NumberFormatException e) {
            result.setText("In one of the fields not number");
        } catch (QueryException | ItemException e) {
            result.setText(e.getMessage());
        }
    }

    private void editEvent() {
        try {
            switch (tableStatus) {
                case PERFORMANCE -> {
                    if (performanceTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select records performance and play");
                    PerformanceDTO dto = performanceTable.getSelectionModel().getSelectedItem();
                    dto.setIdPlay(playTable.getSelectionModel().getSelectedItem().getId());
                    dto.setPrice(Integer.parseInt(addField1.getText()));
                    dto.setTheater(addField2.getText());
                    performanceService.edit(dto);
                    performanceTable.setItems(FXCollections.observableList(performanceService.getAll()));
                }
                case PLAY -> {
                    if (playTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select records play and genre");
                    PlayDTO dto = playTable.getSelectionModel().getSelectedItem();
                    dto.setName(addField1.getText());
                    dto.setData(Date.from(addField4.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                    dto.setRating(Integer.parseInt(addField2.getText()));
                    dto.setGenre(genreTable.getSelectionModel().getSelectedItem().getName());
                    playService.edit(dto);
                    playTable.setItems(FXCollections.observableList(playService.getAll()));
                }
                case AUTHOR -> {
                    if (authorTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    AuthorDTO dto = authorTable.getSelectionModel().getSelectedItem();
                    dto.setFullName(addField1.getText());
                    dto.setBirthday(Date.from(addField4.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                    dto.setCountry(addField2.getText());
                    authorService.edit(dto);
                    authorTable.setItems(FXCollections.observableList(authorService.getAll()));
                }
                case AUTHORS -> {
                    if (authorsTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record");
                    playsAuthorService.getAll().stream()
                            .filter(el -> el.getIdPlay() == playTable.getSelectionModel().getSelectedItem().getId() &&
                                    el.getIdAuthor() == authorsTable.getSelectionModel().getSelectedItem().getId())
                            .findFirst().ifPresent(el -> {
                                el.setIdAuthor((authorTable.getSelectionModel().getSelectedItem().getId()));
                                playsAuthorService.edit(el);
                            });
                    authorsTable.setItems(FXCollections.observableList(playsAuthorService.getAll().stream()
                            .filter(el -> el.getIdPlay() == playTable.getSelectionModel().getSelectedItem().getId())
                            .map(el -> authorService.getById(el.getIdAuthor()))
                            .filter(Optional::isPresent).map(Optional::get)
                            .toList()));
                }
                case GENRE -> {
                    GenreDTO dto = genreTable.getSelectionModel().getSelectedItem();
                    dto.setName(addField1.getText());
                    genreService.edit(dto);
                    genreTable.setItems(FXCollections.observableList(genreService.getAll()));
                }
            }
            result.setText("Success");
        } catch (NumberFormatException e) {
            result.setText("In one of the fields not number");
        } catch (QueryException | ItemException e) {
            result.setText(e.getMessage());
        }
    }

    private void addEvent() {
        try {
            switch (tableStatus) {
                case PERFORMANCE -> {
                    if (playTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record play");
                    performanceService.add(PerformanceDTO.builder()
                            .idPlay(playTable.getSelectionModel().getSelectedItem().getId())
                            .price(Integer.parseInt(addField1.getText()))
                            .theater(addField3.getText())
                            .build());
                    performanceTable.setItems(FXCollections.observableList(performanceService.getAll()));
                }
                case PLAY -> {
                    if (genreTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select record genre");
                    playService.add(PlayDTO.builder()
                            .name(addField1.getText())
                            .data(Date.from(addField4.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                            .rating(Integer.parseInt(addField2.getText()))
                            .genre(genreTable.getSelectionModel().getSelectedItem().getName())
                            .build());
                    playTable.setItems(FXCollections.observableList(playService.getAll()));
                }
                case AUTHOR -> {
                    authorService.add(AuthorDTO.builder()
                            .fullName(addField1.getText())
                            .birthday(Date.from(addField4.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                            .country(addField2.getText())
                            .build());
                    authorTable.setItems(FXCollections.observableList(authorService.getAll()));
                }
                case AUTHORS -> {
                    if (playTable.getSelectionModel().getSelectedItem() == null ||
                            authorTable.getSelectionModel().getSelectedItem() == null)
                        throw new ItemException("select records play and author");
                    playsAuthorService.add(PlaysAuthorDTO.builder()
                            .idPlay(playTable.getSelectionModel().getSelectedItem().getId())
                            .idAuthor(authorTable.getSelectionModel().getSelectedItem().getId())
                            .build());
                    authorsTable.setItems(FXCollections.observableList(playsAuthorService.getAll().stream()
                            .filter(el -> el.getIdPlay() == playTable.getSelectionModel().getSelectedItem().getId())
                            .map(el -> authorService.getById(el.getIdAuthor()))
                            .filter(Optional::isPresent).map(Optional::get)
                            .toList()));
                }
                case GENRE -> {
                    genreService.add(GenreDTO.builder().name(addField1.getText()).build());
                    genreTable.setItems(FXCollections.observableList(genreService.getAll()));
                }
            }
            result.setText("Success");
        } catch (NumberFormatException e) {
            result.setText("In one of the fields not number");
        } catch (QueryException | ItemException e) {
            result.setText(e.getMessage());
        }
    }

    private void initGenreTable() {
        TableColumn<GenreDTO, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        genreTable.getColumns().add(column1);

        genreTable.setItems(FXCollections.observableList(genreService.getAll()));
    }

    private void initAuthorTable() {
        TableColumn<AuthorDTO, String> column1 = new TableColumn<>("Full Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        authorTable.getColumns().add(column1);

        TableColumn<AuthorDTO, Date> column2 = new TableColumn<>("Birthday");
        column2.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        authorTable.getColumns().add(column2);

        TableColumn<AuthorDTO, String> column3 = new TableColumn<>("Country");
        column3.setCellValueFactory(new PropertyValueFactory<>("country"));
        authorTable.getColumns().add(column3);

        authorTable.setItems(FXCollections.observableList(authorService.getAll()));
    }

    private void initAuthorsTable() {
        TableColumn<AuthorDTO, String> column1 = new TableColumn<>("Full Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        authorsTable.getColumns().add(column1);

        TableColumn<AuthorDTO, Date> column2 = new TableColumn<>("Birthday");
        column2.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        authorsTable.getColumns().add(column2);

        TableColumn<AuthorDTO, String> column3 = new TableColumn<>("Country");
        column3.setCellValueFactory(new PropertyValueFactory<>("country"));
        authorsTable.getColumns().add(column3);
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

        performanceTable.setItems(FXCollections.observableList(performanceService.getAll()));
    }

    private void initPlayTable() {
        TableColumn<PlayDTO, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        playTable.getColumns().add(column1);

        TableColumn<PlayDTO, Date> column2 = new TableColumn<>("Data");
        column2.setCellValueFactory(new PropertyValueFactory<>("data"));
        playTable.getColumns().add(column2);

        TableColumn<PlayDTO, String> column3 = new TableColumn<>("Rating");
        column3.setCellValueFactory(new PropertyValueFactory<>("rating"));
        playTable.getColumns().add(column3);

        TableColumn<PlayDTO, String> column4 = new TableColumn<>("Genre");
        column4.setCellValueFactory(new PropertyValueFactory<>("genre"));
        playTable.getColumns().add(column4);

        playTable.addEventFilter(MouseEvent.MOUSE_CLICKED, event ->
                authorsTable.setItems(FXCollections.observableList(playsAuthorService.getAll().stream()
                        .filter(el -> el.getIdPlay() == playTable.getSelectionModel().getSelectedItem().getId())
                        .map(el -> authorService.getById(el.getIdAuthor()))
                        .filter(Optional::isPresent).map(Optional::get)
                        .toList())));

        playTable.setItems(FXCollections.observableList(playService.getAll()));
    }

    public enum TableStatus {
        PERFORMANCE,
        PLAY,
        AUTHOR,
        AUTHORS,
        GENRE
    }

    public enum QueryStatus {
        QUERY0,
        QUERY0_1,
        QUERY2,
        QUERY3,
        QUERY4,
        QUERY5
    }
}
