package ebaza.codejava.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import ebaza.codejava.domain.Car;

public interface ICarService {

	Car createWithImages(Car car, MultipartFile[] file);

	Car create(Car car);

	void delete(String uuid);

	Car findById(UUID id);

	Page<Car> findAll(PageRequest pageRequest);

//	Page<Car> findByRegistrant(String uuid);

}
