package io.github.places.services;

import io.github.places.domain.place.Place;
import io.github.places.dtos.PlaceDTO;
import io.github.places.exceptions.PlaceNotFoundException;
import io.github.places.repositories.PlaceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaceService {

    @Autowired
    private PlaceRepository repository;

    public Place createPlace(PlaceDTO dto){
        Place place = new Place();
        BeanUtils.copyProperties(dto, place);
        place.generateSlug();
        return repository.save(place);
    }
    public List<Place> getAllPlaces(){
        return this.repository.findAll();
    }

    public Optional<Place> getPlaceById(Long placeId) {
        Optional<Place> placeOptional = repository.findById(placeId);

        if (placeOptional.isEmpty()) {
            throw new PlaceNotFoundException("Place not found with id: " + placeId);
        }

        return placeOptional;
    }

    public Place updatePlace(Long id, PlaceDTO dto) {
        Optional<Place> optionalPlace = repository.findById(id);
        if (optionalPlace.isPresent()) {
            Place place = optionalPlace.get();
            BeanUtils.copyProperties(dto, place);
            place.generateSlug();
            return repository.save(place);
        } else {
            throw new PlaceNotFoundException("Place not found with id: " + id);
        }
    }


    public void deletePlace(Long id) {
        if (!repository.existsById(id)) {
            throw new PlaceNotFoundException("Place not found with id: " + id);
        }
        repository.deleteById(id);
    }


}
