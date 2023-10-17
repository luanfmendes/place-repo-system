package io.github.places.controllers;

import io.github.places.domain.place.Place;
import io.github.places.dtos.PlaceDTO;
import io.github.places.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/places")
public class PlaceController {

    @Autowired
    private PlaceService service;

    @PostMapping
    public ResponseEntity<Place> createPlace(@RequestBody PlaceDTO dto){
        Place newPlace = service.createPlace(dto);
        return new ResponseEntity<>(newPlace, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Place> updatePlace(@PathVariable Long id, @RequestBody PlaceDTO dto) {
        Place updatedPlace = service.updatePlace(id, dto);
        if (updatedPlace != null) {
            return new ResponseEntity<>(updatedPlace, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long id) {
        service.deletePlace(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Place> getPlaceById(@PathVariable Long id) {
        Optional<Place> place = service.getPlaceById(id);
        if (place.isPresent()) {
            return new ResponseEntity<>(place.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Place>> getAllUsers(){
        List<Place> places = service.getAllPlaces();
        return new ResponseEntity<>(places, HttpStatus.OK);
    }
}
