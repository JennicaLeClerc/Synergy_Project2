package com.revature.shms.services;

import com.revature.shms.enums.Amenities;
import com.revature.shms.models.AmenityWrapper;
import com.revature.shms.repositories.AmenityWrapperRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AmenityWrapperService {
	@Autowired
	private AmenityWrapperRepository repo;

	/**
	 * gets all amenityWrappers
	 * @return List<AmenityWrapper>
	 */
	public List<AmenityWrapper> getAllAmenities(){
		return repo.findAll();
	}

	/**
	 * Sets an amenities prices
	 * @param amenity amenity to change price
	 * @param price  price to be set
	 * @return the amenityWrapper
	 */
	public AmenityWrapper setAmenityPrice(Amenities amenity, double price){
		return repo.save(new AmenityWrapper(amenity,price));
	}

	/**
	 * Gets AmenityWrapper based on amenities enum
	 * @param amenity amenity to look for
	 * @return AmenityWrapper
	 */
	public AmenityWrapper getAmenityWrapper(Amenities amenity){
		return repo.getById(amenity);
	}

	/**
	 * Gets price of amenity from database
	 * @param amenity amenity to look for
	 * @return double price
	 */
	public Double getAmenityPrice(Amenities amenity) {
		return repo.getById(amenity).getPriceWeight();
	}

	/**
	 * Gets total from list of amenity wrappers
	 * @param wrappers list of AmenityWrappers to get prices
	 * @return double total price
	 */
	public Double getTotal(List<AmenityWrapper> wrappers){
		return wrappers.stream().mapToDouble(AmenityWrapper::getPriceWeight).sum();
	}
	/**
	 * Generates all AmenityWrappers with 0 price
	 */
	public void GenerateAllAmenityWrappers(){
		Arrays.stream(Amenities.values()).forEach(amenities -> repo.save(new AmenityWrapper(amenities,0)));
	}
}