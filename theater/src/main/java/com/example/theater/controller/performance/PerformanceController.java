package com.example.theater.controller.performance;

import com.example.theater.controller.employee.ActorController;
import com.example.theater.dto.performance.HallDTO;
import com.example.theater.dto.performance.PerformanceDTO;
import com.example.theater.dto.performance.PlaceDTO;
import com.example.theater.dto.performance.RepertoireDTO;
import com.example.theater.dto.performance.play.PlayDTO;
import com.example.theater.service.employee.category.ActorCategoryService;
import com.example.theater.service.employee.character.ActorCharacterService;
import com.example.theater.service.employee.character.CharactersActorService;
import com.example.theater.service.employee.rank.RankService;
import com.example.theater.service.employee.rank.RanksActorService;
import com.example.theater.service.performance.HallService;
import com.example.theater.service.performance.PerformanceService;
import com.example.theater.service.performance.PlaceService;
import com.example.theater.service.performance.RepertoireService;
import com.example.theater.service.performance.play.PlayService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@FxmlView("/controller/performance/performance-view.fxml")
public class PerformanceController {
    /*TODO:
     *  2. Получить перечень и общее число спектаклей
     *  3.Получить перечень и общее число всех поставленных спектаклей
     *  5.Получить перечень спектаклей
     *  11.Получить сведения о числе пpоданных билетов на все спектакли
     *  12.Получить общую сумму выpученных денег за указанный спектакль
     *  13.Получить перечень и общее число свободных мест на все спектакли*/
    @FXML private Text searchText8;
    @FXML private Text searchText7;
    @FXML private DatePicker searchField6;
    @FXML private TextField searchField4;
    @FXML private TextField addField1;
    @FXML private Text addText1;
    @FXML private TextField addField2;
    @FXML private Text addText2;
    @FXML private DatePicker addField3;
    @FXML private Text addText3;
    @FXML private DatePicker addField4;
    @FXML private Text addText4;
    @FXML private TextField addField5;
    @FXML private Text addText5;
    @FXML private TextField addField6;
    @FXML private Text addText6;
    @FXML private TextField result;
    @FXML private Text searchText1;
    @FXML private Text searchText2;
    @FXML private Text searchText3;
    @FXML private Text searchText4;
    @FXML private Text searchText5;
    @FXML private Text searchText6;
    @FXML private TableView<PlayDTO> playTable;
    @FXML private TableView<RepertoireDTO> repertoireTable;
    @FXML private TableView<PlaceDTO> placeTable;
    @FXML private TableView<HallDTO> hallTable;
    @FXML private TableView<PerformanceDTO> performanceTable;
    @FXML private TextField searchField8;
    @FXML private TextField searchField7;
    @FXML private TextField searchField3;
    @FXML private TextField searchField2;
    @FXML private DatePicker searchField5;
    @FXML private TextField searchField1;
    @FXML private ListView<String> queries;
    @FXML private Button search;
    @FXML private Button repertoire;
    @FXML private Button troupe;
    @FXML private Button performance;
    @FXML private Button hall;
    @FXML private Button place;
    @FXML private Button play;
    @FXML private Button close;
    @FXML private Button back;
    @FXML private Button drop;
    @FXML private Button edit;
    @FXML private Button add;
    private final PerformanceService performanceService;
    private final HallService hallService;
    private final PlaceService placeService;
    private final RepertoireService repertoireService;
    private final PlayService playService;
    private final FxWeaver fxWeaver;
    private ActorController.TableStatus tableStatus = ActorController.TableStatus.ACTOR;
    private ActorController.QueryStatus queryStatus = ActorController.QueryStatus.QUERY1;
    private final Map<QueryStatus, String> queryMap = new HashMap<>();

    public PerformanceController(PerformanceService performanceService, HallService hallService,
                                 PlaceService placeService, RepertoireService repertoireService,
                                 PlayService playService, FxWeaver fxWeaver) {
        this.performanceService = performanceService;
        this.hallService = hallService;
        this.placeService = placeService;
        this.repertoireService = repertoireService;
        this.playService = playService;
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        queryMap.put(QueryStatus.QUERY0, "Получить все скектакли");
        queryMap.put(QueryStatus.QUERY1, "Получить скектакли, сыгранных в театре");
        queryMap.put(QueryStatus.QUERY2, "Получить скектакли по автору");
        queryMap.put(QueryStatus.QUERY3, "Получить число пpоданных билетов на все спектакли");
        queryMap.put(QueryStatus.QUERY4, "Получить число пpоданных билетов на кокретный спектакли");
        queryMap.put(QueryStatus.QUERY5, "Получить число пpоданных билетов на премьеры спектакли");
        queryMap.put(QueryStatus.QUERY6, "Получить общую сумму выpученных денег за спектакль");
        queryMap.put(QueryStatus.QUERY7, "Получить число свободных мест на все спектакли");
        queryMap.put(QueryStatus.QUERY8, "Получить число свободных мест на конкpетный спектакль");
        queryMap.put(QueryStatus.QUERY9, "Получить число свободных мест на пpемьеpы");
    }

    private void initPerformanceTable() {
        
    }

    public enum TableStatus {
        ACTOR,
        CATEGORY,
        CHARACTER,
        CHARACTERS,
        RANK,
        RANKS
    }

    public enum QueryStatus {
        QUERY0,
        QUERY1,
        QUERY2,
        QUERY3,
        QUERY4,
        QUERY5,
        QUERY6,
        QUERY7,
        QUERY8,
        QUERY9
    }
}
