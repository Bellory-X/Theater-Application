//package com.example.theater.mappers.entities;
//
//import com.example.theater.dao.entities.TheaterEntity;
//import com.example.theater.models.Theater;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TheaterToEntityMapperImpl implements TheaterToEntityMapper {
//
//    @Override
//    public TheaterEntity theaterToTheaterEntity(Theater theater) {
//        if ( theater == null )
//            return null;
//
//        return new TheaterEntity(theater.getId());
//    }
//
//    @Override
//    public Theater theaterEntityToTheater(TheaterEntity theaterEntity) {
//        if ( theaterEntity == null )
//            return null;
//
//        return new Theater(theaterEntity.getId());
//    }
//}
