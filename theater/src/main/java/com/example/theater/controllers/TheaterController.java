package com.example.theater.controllers;

import com.example.theater.mappers.TheaterMapper;
import com.example.theater.services.TheaterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/theaters")
//@RequiredArgsConstructor
public class TheaterController {
    private final TheaterService theaterService;

    TheaterController(TheaterService theaterService, TheaterMapper mapper) {
        this.theaterService = theaterService;
        theaterService.getAll().forEach(System.out::println);
    }

    @GetMapping("/{id}")
    public TheaterService getBookById(@PathVariable String id) {
        return theaterService;
    }
//
//    @GetMapping
//    public List<TheaterDTO> getAllBooks() {
//        return theaterService.getAllTheaters();
//    }

//    @PostMapping
//    public void addBook(@RequestBody TheaterRequest request) {
//        theaterService.addTheater(mapper.AddTheaterRequestToTheater(request));
//    }
//
//    @PutMapping("/{id}")
//    public void editBook(@PathVariable String id, @RequestBody TheaterRequest request) {
//        theaterService.editTheater(mapper.EditBookRequestToBook(id, request));
//    }
}
