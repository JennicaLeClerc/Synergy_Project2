package com.revature.shms.servicetests;

import com.revature.shms.enums.Amenities;
import com.revature.shms.exceptions.EntityNotFound;
import com.revature.shms.models.AmenityWrapper;
import com.revature.shms.repositories.AmenityWrapperRepository;
import com.revature.shms.services.AmenityWrapperService;
import com.revature.shms.services.ReservationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AmenityWrapperServiceTest {
	@Mock AmenityWrapperRepository repo;
	@InjectMocks AmenityWrapperService service;

	@Test
	public void getAllAmenitiesTest(){
		List<AmenityWrapper> amenityWrappers = new ArrayList<>();
		amenityWrappers.add(new AmenityWrapper(Amenities.ADA,123));
		amenityWrappers.add(new AmenityWrapper(Amenities.KING_BED,12));
		when(repo.findAll()).thenReturn(amenityWrappers);
		assertEquals(service.getAllAmenities(),amenityWrappers);
	}

	@Test
	public void setAmenityPriceTest(){
		AmenityWrapper wrapper = new AmenityWrapper(Amenities.SMALL_KITCHEN, 100);
		when(repo.save(any(AmenityWrapper.class))).thenReturn(wrapper);
		assertEquals(service.setAmenityPrice(Amenities.SMALL_KITCHEN,100),wrapper);
	}

	@Test
	public void getAmenityWrapperTest() throws EntityNotFound {
		AmenityWrapper wrapper = new AmenityWrapper(Amenities.SMALL_KITCHEN, 100);
		when(repo.findById(any())).thenReturn(java.util.Optional.of(wrapper));
		assertEquals(service.getAmenityWrapper(Amenities.SMALL_KITCHEN),wrapper);
	}

	@Test
	public void getAmenityPriceTest() throws EntityNotFound {
		AmenityWrapper wrapper = new AmenityWrapper(Amenities.SMALL_KITCHEN, 100);
		when(repo.findById(any())).thenReturn(java.util.Optional.of(wrapper));
		assertEquals(service.getAmenityPrice(Amenities.SMALL_KITCHEN),100);
	}

	@Test
	public void getTotalTest(){
		List<AmenityWrapper> amenityWrappers = new ArrayList<>();
		amenityWrappers.add(new AmenityWrapper(Amenities.ADA,123));
		amenityWrappers.add(new AmenityWrapper(Amenities.KING_BED,12));
		assertEquals(service.getTotal(amenityWrappers),135);
	}

	@Test
	public void GenerateAllAmenityWrappersTest(){
		service.GenerateAllAmenityWrappers();
		verify(repo,times(Amenities.values().length)).save(any());
	}

	@Test
	public void getterAndSetterTest(){
		AmenityWrapperRepository amenityWrapperRepository = null;
		AmenityWrapperService amenityWrapperService = new AmenityWrapperService();
		amenityWrapperService.setAmenityWrapperRepository(amenityWrapperRepository);
		Assertions.assertNull(amenityWrapperService.getAmenityWrapperRepository());

		amenityWrapperService.setAmenityWrapperRepository(null);
		Assertions.assertNull(amenityWrapperService.getAmenityWrapperRepository());
	}
}
