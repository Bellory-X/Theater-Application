package com.example.theater.controller.performance;

import com.example.theater.dto.performance.play.AuthorDTO;
import com.example.theater.dto.performance.play.GenreDTO;
import com.example.theater.dto.performance.play.PlayDTO;
import com.example.theater.dto.performance.play.PlaysAuthorDTO;
import com.example.theater.exception.QueryException;
import com.example.theater.service.performance.play.AuthorService;
import com.example.theater.service.performance.play.GenreService;
import com.example.theater.service.performance.play.PlayService;
import com.example.theater.service.performance.play.PlaysAuthorService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@FxmlView("/controller/performance/play-view.fxml")
public class PlayController {
    /*TODO:
     *  4.Получить список автоpов поставленных спектаклей*/
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
    public TableView<PlayDTO> playTable;
    public TableView<AuthorDTO> authorTable;
    public TableView<AuthorDTO> authorsTable;
    public TableView<GenreDTO> genreTable;
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
    private final PlayService playService;
    private final AuthorService authorService;
    private final PlaysAuthorService playsAuthorService;
    private final GenreService genreService;
    private final FxWeaver fxWeaver;
    private TableStatus tableStatus = TableStatus.PLAY;

    public PlayController(PlayService playService, AuthorService authorService, PlaysAuthorService playsAuthorService,
                          GenreService genreService, FxWeaver fxWeaver) {
        this.playService = playService;
        this.authorService = authorService;
        this.playsAuthorService = playsAuthorService;
        this.genreService = genreService;
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        initPlayTable();
        initAuthorTable();
        initAuthorsTable();
        initGenreTable();
        eventButton();
    }

    private void eventButton() {
        play.setOnAction(event -> playEvent());
        author.setOnAction(event -> authorEvent());
        authors.setOnAction(event -> authorsEvent());
        genre.setOnAction(event -> genreEvent());
        add.setOnAction(event -> addEvent());
        edit.setOnAction(event -> editEvent());
        drop.setOnAction(event -> dropEvent());
        back.setOnAction(event -> {});
        close.setOnAction(event -> close.getScene().getWindow().hide());
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
                case PLAY -> {
                    playService.drop(playTable.getSelectionModel().getSelectedItem());
                    playTable.setItems(FXCollections.observableList(playService.getAll()));
                    playTable.refresh();
                }
                case AUTHOR -> {
                    authorService.drop(authorTable.getSelectionModel().getSelectedItem());
                    authorTable.setItems(FXCollections.observableList(authorService.getAll()));
                    authorTable.refresh();
                }
                case AUTHORS -> {
                    playsAuthorService.getAll().stream()
                            .filter(el -> el.getIdPlay() == playTable.getSelectionModel().getSelectedItem().getId() &&
                                    el.getIdAuthor() == authorsTable.getSelectionModel().getSelectedItem().getId())
                            .findFirst().ifPresent(playsAuthorService::drop);
                }
                case GENRE -> {
                    genreService.drop(genreTable.getSelectionModel().getSelectedItem());
                    genreTable.setItems(FXCollections.observableList(genreService.getAll()));
                    genreTable.refresh();
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
            switch (tableStatus) {
                case PLAY -> {
                    PlayDTO dto = playTable.getSelectionModel().getSelectedItem();
                    dto.setName(addField1.getText());
                    dto.setData(Date.from(addField4.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                    dto.setRating(Integer.parseInt(addField2.getText()));
                    dto.setGenre(genreTable.getSelectionModel().getSelectedItem().getName());
                    playService.edit(dto);
                    playTable.setItems(FXCollections.observableList(playService.getAll()));
                    playTable.refresh();
                }
                case AUTHOR -> {
                    AuthorDTO dto = authorTable.getSelectionModel().getSelectedItem();
                    dto.setFullName(addField1.getText());
                    dto.setBirthday(Date.from(addField4.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
                    dto.setCountry(addField2.getText());
                    authorService.edit(dto);
                    authorTable.setItems(FXCollections.observableList(authorService.getAll()));
                    authorTable.refresh();
                }
                case AUTHORS -> {
                    playsAuthorService.getAll().stream()
                            .filter(el -> el.getIdPlay() == playTable.getSelectionModel().getSelectedItem().getId() &&
                                    el.getIdAuthor() == authorsTable.getSelectionModel().getSelectedItem().getId())
                            .findFirst().ifPresent(el -> {
                                el.setIdAuthor((authorTable.getSelectionModel().getSelectedItem().getId()));
                                playsAuthorService.edit(el);
                            });
                }
                case GENRE -> {
                    GenreDTO dto = genreTable.getSelectionModel().getSelectedItem();
                    dto.setName(addField1.getText());
                    genreService.edit(dto);
                    genreTable.setItems(FXCollections.observableList(genreService.getAll()));
                    genreTable.refresh();
                }
            }
            result.setText("Success");
        } catch (NumberFormatException e) {
            result.setText("In one of the fields not number");
        } catch (QueryException e) {
            result.setText(e.getMessage());
        }
    }

    private void addEvent() {
        try {
            switch (tableStatus) {
                case PLAY -> {
                    playService.add(PlayDTO.builder()
                            .name(addField1.getText())
                            .data(Date.from(addField4.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                            .rating(Integer.parseInt(addField2.getText()))
                            .genre(genreTable.getSelectionModel().getSelectedItem().getName())
                            .build());
                    playTable.setItems(FXCollections.observableList(playService.getAll()));
                    playTable.refresh();
                }
                case AUTHOR -> {
                    authorService.add(AuthorDTO.builder()
                            .fullName(addField1.getText())
                            .birthday(Date.from(addField4.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()))
                            .country(addField2.getText())
                            .build());
                    authorTable.setItems(FXCollections.observableList(authorService.getAll()));
                    authorTable.refresh();
                }
                case AUTHORS -> {
                    playsAuthorService.add(PlaysAuthorDTO.builder()
                            .idPlay(playTable.getSelectionModel().getSelectedItem().getId())
                            .idAuthor(authorTable.getSelectionModel().getSelectedItem().getId())
                            .build());
                }
                case GENRE -> {
                    genreService.add(GenreDTO.builder().name(addField1.getText()).build());
                    genreTable.setItems(FXCollections.observableList(genreService.getAll()));
                    genreTable.refresh();
                }
            }
            result.setText("Success");
        } catch (NumberFormatException e) {
            result.setText("In one of the fields not number");
        } catch (QueryException e) {
            result.setText(e.getMessage());
        }
    }

    private void initGenreTable() {
        TableColumn<GenreDTO, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        genreTable.getColumns().add(column1);

        genreTable.setItems(FXCollections.observableList(genreService.getAll()));
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

//        authorsTable.setItems(FXCollections.observableList(playsAuthorService.getAll()));
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
        column4.setCellValueFactory(new PropertyValueFactory<>("Genre"));
        playTable.getColumns().add(column4);

        playTable.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            PlayDTO dto = playTable.getSelectionModel().getSelectedItem();
            List<AuthorDTO> list = playsAuthorService.getAll().stream()
                    .filter(el -> el.getIdPlay() == dto.getId()).map(el ->
                        authorService.getById(el.getIdAuthor())
                    ).filter(Optional::isPresent).map(Optional::get).toList();
            authorsTable.getItems().clear();
            authorsTable.getItems().addAll(list);
            authorsTable.refresh();
        });

        playTable.setItems(FXCollections.observableList(playService.getAll()));
    }

    public enum TableStatus {
        PLAY,
        AUTHOR,
        AUTHORS,
        GENRE
    }

    public enum QueryStatus {
        QUERY0,
        QUERY1
    }
}
