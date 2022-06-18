package ebaza.codejava.serviceImplementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ebaza.codejava.domain.Gallery;
import ebaza.codejava.domain.House;
import ebaza.codejava.repository.HouseRepository;
import ebaza.codejava.service.IGalleryService;
import ebaza.codejava.service.IHouseService;
import ebaza.codejava.utility.IUserMessage;
import ebaza.common.framework.exception.RequestException;
import ebaza.framework.files.properties.FileStoragePropertie;
import ebaza.framework.files.service.IFileStorageService;
import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.service.AbstractService;

@Service
public class HouseImplementation extends AbstractService implements IHouseService {
	
	@Autowired
	HouseRepository hrepo;
	
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
	public House createWithImages(House house, MultipartFile[] file) {
		try {
			this.create(house);
			for (String image : this.uploadImages(file)) {
				Gallery gallery = new Gallery();
				gallery.setReferenceId(house.getId().toString());
				gallery.setPath(image);
				gallery.setReferenceName("Car");
				galleryService.create(gallery);
			}
			return house;
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public House create(House house) {
		try {
			return hrepo.save(house);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public void delete(String uuid) {
		try {
			Optional<House> p = hrepo.findByIdAndStatus(UUID.fromString(uuid), EStatus.ACTIVE);
			if (p.isPresent()) {
				House h = p.get();
				h.setStatus(EStatus.INACTIVE);
				this.create(h);
			}
		} catch (Exception e) {
			throw handleException(e);
		}

		
	}

	@Override
	public House findById(UUID id) {
		try {
			Optional<House> h = hrepo.findByIdAndStatus(id, EStatus.ACTIVE);
			if (h.isPresent()) {
				return h.get();
			} else {
				throw new RequestException(IUserMessage.OBJECT_NOT_FOUND);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public Page<House> findAll(PageRequest pageRequest) {
		try {
			return hrepo.findAll(pageRequest);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public List<House> findByRegistrant(String uuid) {
		return hrepo.findByOwnerUuidAndStatus(uuid, EStatus.ACTIVE);
	}


	private List<String> uploadImages(MultipartFile[] files) {
		if (files.length == 0 || files[0].getOriginalFilename() == null) {
			return new ArrayList<>();
		}
		FileStoragePropertie fileStoragePropertie = new FileStoragePropertie();
		fileStoragePropertie.setUploadDir(fullUrls + this.uploadDir);
		String[] allowedExtension = ALLOWED_IMAGE_EXTENSION;
		List<String> result = Arrays.asList(files).stream()
				.map(file -> fileStorageService.storeFile(file, fileStoragePropertie, Arrays.asList(allowedExtension)))
				.collect(Collectors.toList());

		return result;
	}

	@Override
	public List<House> findByLocation(UUID id) {
		return hrepo.findByLocationIdAndStatus(id, EStatus.ACTIVE);
	}

}
