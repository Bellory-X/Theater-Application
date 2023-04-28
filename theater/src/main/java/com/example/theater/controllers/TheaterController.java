package com.example.theater.controllers;

import com.example.theater.dao.requests.TheaterRequest;
import com.example.theater.mappers.dto.TheaterToDTOMapper;
import com.example.theater.dto.TheaterDTO;
import com.example.theater.services.TheaterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/theaters")
//@RequiredArgsConstructor
public class TheaterController {
    private final TheaterService theaterService;
    private final TheaterToDTOMapper mapper;

    TheaterController(TheaterService theaterService, TheaterToDTOMapper mapper) {
        this.theaterService = theaterService;
        this.mapper = mapper;
//        theaterService.getAllTheaters().forEach(System.out::println);
    }

    @GetMapping("/{id}")
    public TheaterDTO getBookById(@PathVariable String id) {
        return theaterService.getTheaterById(id);
    }

    @GetMapping
    public List<TheaterDTO> getAllBooks() {
        return theaterService.getAllTheaters();
    }

    @PostMapping
    public void addBook(@RequestBody TheaterRequest request) {
        theaterService.addTheater(mapper.AddTheaterRequestToTheater(request));
    }

    @PutMapping("/{id}")
    public void editBook(@PathVariable String id, @RequestBody TheaterRequest request) {
        theaterService.editTheater(mapper.EditBookRequestToBook(id, request));
    }
}
