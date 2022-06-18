package ebaza.codejava.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import ebaza.codejava.domain.Location;

public interface ILocationService {

	Location create(Location loc);

	void delete(UUID id);

	Location findById(UUID id);

	Page<Location> findAll(PageRequest pageRequest);

	Page<Location> findByParent(UUID id, Pageable pageable);

	Page<Location> findProvinceLocation(Pageable pageable);

}
