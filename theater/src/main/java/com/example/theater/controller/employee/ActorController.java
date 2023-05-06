package com.example.theater.controller.employee;

import com.example.theater.dto.employee.ActorDTO;
import com.example.theater.dto.employee.category.ActorCategoryDTO;
import com.example.theater.dto.employee.character.ActorCharacterDTO;
import com.example.theater.dto.employee.character.CharactersActorDTO;
import com.example.theater.dto.employee.rank.RankDTO;
import com.example.theater.dto.employee.rank.RanksActorDTO;
import com.example.theater.service.employee.ActorService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/controller/employee/actor-view.fxml")
public class ActorController {
    /*TODO:
       1.Получить список и общее число
       6.Получить список, подходящих по своим данным на указанную pоль
       7.Получить общее число и список актеpов театpа, имеющих звания
       8.Получить список, пpиезжавших на гастpоли, уезжавших на гастpоли
       10.Получить перечень и общее число pолей*/

    /*TODO:
    *  Category
    *  Character
    *  Characters
    *  Rank
    *  Ranks*/
    @FXML private Button rank;
    @FXML private Button ranks;
    @FXML private TableView<RankDTO> rankTable;
    @FXML private TableView<RanksActorDTO> ranksTable;
    @FXML private TableView<CharactersActorDTO> charactersTable;
    @FXML private TableView<ActorCharacterDTO> characterTable;
    @FXML private TableView<ActorCategoryDTO> categoryTable;
    @FXML private Button drop;
    @FXML private Button edit;
    @FXML private Button add;
    @FXML private TextField name;
    @FXML private Button search;
    @FXML private TextField salary;
    @FXML private TextField countChild;
    @FXML private TextField gender;
    @FXML private TextField experience;
    @FXML private DatePicker birthday;
    @FXML private TextField theater;
    @FXML private ListView<String> queries;
    @FXML private TableView<ActorDTO> actorTable;
    @FXML private Button actor;
    @FXML private Button category;
    @FXML private Button character;
    @FXML private Button characters;
    @FXML private Button close;
    @FXML private Button back;
    private final ActorService actorService;
    private final FxWeaver fxWeaver;

    public ActorController(ActorService actorService, FxWeaver fxWeaver) {
        this.actorService = actorService;
        this.fxWeaver = fxWeaver;
    }
}
