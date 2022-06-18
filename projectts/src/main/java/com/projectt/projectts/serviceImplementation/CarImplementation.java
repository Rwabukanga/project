package ebaza.codejava.serviceImplementation;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ebaza.codejava.domain.Car;
import ebaza.codejava.domain.Gallery;
import ebaza.codejava.repository.CarRepository;
import ebaza.codejava.service.IGalleryService;
import ebaza.codejava.service.ICarService;
import ebaza.codejava.utility.IUserMessage;
import ebaza.common.framework.exception.RequestException;
import ebaza.framework.files.properties.FileStoragePropertie;
import ebaza.framework.files.service.IFileStorageService;
import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.service.AbstractService;

@Service
@Transactional
public class CarImplementation extends AbstractService implements ICarService {

	@Autowired
	CarRepository crepo;
	
	@Value("${files.uploadImage}")
	private String uploadDir;
	
	@Value("${files.fullUrls}")
	private String fullUrls;
	
	@Value("${files.serverUrl}")
	private String serverUrl;
	
	String[] ALLOWED_IMAGE_EXTENSION = { "image/jpeg", "image/png", "image/gif", "image/jpg" };
	
	@Autowired
	private IFileStorageService fileStorageService;
	
	@Autowired
	private IGalleryService galleryService;

	@Override
	public Car create(Car plot) {
		try {
			return crepo.save(plot);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public void delete(String uuid) {
		try {
			Optional<Car> p = crepo.findByIdAndStatus(UUID.fromString(uuid), EStatus.ACTIVE);
			if (p.isPresent()) {
				Car pl = p.get();
				pl.setStatus(EStatus.INACTIVE);
				this.create(pl);
			}
		} catch (Exception e) {
			throw handleException(e);
		}

	}

	@Override
	public Car findById(UUID id) {
		try {
			Optional<Car> p = crepo.findByIdAndStatus(id, EStatus.ACTIVE);
			if (p.isPresent()) {
				return p.get();
			} else {
				throw new RequestException(IUserMessage.OBJECT_NOT_FOUND);
			}
		} catch (Exception e) {
			throw handleException(e);
		}

	}

	@Override
	public Page<Car> findAll(PageRequest pageRequest) {
		try {
			return crepo.findAll(pageRequest);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public Car createWithImages(Car car, MultipartFile[] file) {
		try {
			this.create(car);
			for (String image : this.uploadImages(file)) {
				Gallery gallery = new Gallery();
				gallery.setReferenceId(car.getId().toString());
				gallery.setPath(image);
				gallery.setReferenceName("Car");
				galleryService.create(gallery);
			}
			return car;
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	private List<String> uploadImages(MultipartFile[] files) {
		if (files.length == 0 || files[0].getOriginalFilename() == null) {
			return null;
		}
		FileStoragePropertie fileStoragePropertie = new FileStoragePropertie();
		fileStoragePropertie.setUploadDir(fullUrls + this.uploadDir);
		String[] allowedExtension = ALLOWED_IMAGE_EXTENSION;
		List<String> result = Arrays.asList(files).stream()
				.map(file -> fileStorageService.storeFile(file, fileStoragePropertie, Arrays.asList(allowedExtension)))
				.collect(Collectors.toList());

		return result;
	}

}
