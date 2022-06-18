package ebaza.codejava.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import ebaza.codejava.domain.House;




public interface IHouseService {

	House createWithImages(House house, MultipartFile[] file);

	House create(House house);

	void delete(String uuid);

	House findById(UUID id);

	Page<House> findAll(PageRequest pageRequest);

    List<House> findByRegistrant(String uuid);
	
	List<House> findByLocation(UUID id);

}
