package io.github.places;

import io.github.places.domain.place.Place;
import io.github.places.dtos.PlaceDTO;
import io.github.places.exceptions.PlaceNotFoundException;
import io.github.places.repositories.PlaceRepository;
import io.github.places.services.PlaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class PlaceServiceTest {

	private MockMvc mockMvc;

	@InjectMocks
	private PlaceService placeService;

	@MockBean
	private PlaceRepository placeRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(placeService).build();
	}

	@Test
	public void testCreatePlaceSuccess() {
		PlaceDTO placeDTO = new PlaceDTO("Test Place", "Test City", "Test State");
		Place place = new Place();
		place.setName(placeDTO.name());
		place.setCity(placeDTO.city());
		place.setState(placeDTO.state());

		when(placeRepository.save(any(Place.class))).thenReturn(place);

		Place createdPlace = placeService.createPlace(placeDTO);

		assertEquals("Test Place", createdPlace.getName());
		assertEquals("Test City", createdPlace.getCity());
		assertEquals("Test State", createdPlace.getState());

		verify(placeRepository).save(any(Place.class));
	}

	@Test
	public void testCreatePlaceFailure() {
		PlaceDTO placeDTO = new PlaceDTO("Test Place", "Test City", "Test State");

		when(placeRepository.save(any(Place.class))).thenThrow(new RuntimeException("Failed to create"));

		assertThrows(RuntimeException.class, () -> placeService.createPlace(placeDTO));

		verify(placeRepository).save(any(Place.class));
	}

	@Test
	public void testUpdatePlaceSuccess() {
		Long placeId = 1L;
		PlaceDTO placeDTO = new PlaceDTO("Updated Place", "Updated City", "Updated State");

		Place existingPlace = new Place();
		existingPlace.setId(placeId);

		when(placeRepository.findById(placeId)).thenReturn(Optional.of(existingPlace));
		when(placeRepository.save(any(Place.class))).thenAnswer(invocation -> invocation.getArgument(0));

		Place updatedPlace = placeService.updatePlace(placeId, placeDTO);

		assertEquals("Updated Place", updatedPlace.getName());
		assertEquals("Updated City", updatedPlace.getCity());
		assertEquals("Updated State", updatedPlace.getState());

		verify(placeRepository).findById(placeId);
		verify(placeRepository).save(any(Place.class));
	}

	@Test
	void testUpdatePlaceFailure() {
		assertThrows(PlaceNotFoundException.class, () -> {
			placeService.updatePlace(999L, new PlaceDTO("New Name", "New City", "New State"));
		});
	}


	@Test
	void testDeletePlaceSuccess() {
		Place place = new Place();

		when(placeRepository.existsById(1L)).thenReturn(true);

		assertDoesNotThrow(() -> placeService.deletePlace(1L));

		verify(placeRepository).deleteById(1L);
	}


	@Test
	void testDeletePlaceFailure() {
		when(placeRepository.findById(1L)).thenReturn(Optional.empty());

		assertThrows(PlaceNotFoundException.class, () -> {
			placeService.deletePlace(1L);
		});

		verify(placeRepository, never()).deleteById(1L);
	}

	@Test
	public void testGetPlaceByIdSuccess() {
		Long placeId = 1L;
		Place existingPlace = new Place();
		existingPlace.setId(placeId);

		when(placeRepository.findById(placeId)).thenReturn(Optional.of(existingPlace));

		Optional<Place> place = placeService.getPlaceById(placeId);

		assertEquals(placeId, place.orElse(null).getId());

		verify(placeRepository).findById(placeId);
	}

	@Test
	public void testGetPlaceByIdFailure() {
		Long placeId = 1L;

		when(placeRepository.findById(any(Long.class))).thenReturn(Optional.empty());


		assertThrows(PlaceNotFoundException.class, () -> placeService.getPlaceById(placeId));

		verify(placeRepository).findById(placeId);
	}
}
