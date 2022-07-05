package com.projectt.projectts.serviceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.projectt.projectts.domain.ApprovalStatus;
import com.projectt.projectts.domain.BiddingRequest;
import com.projectt.projectts.domain.Car;
import com.projectt.projectts.domain.EPropertyType;
import com.projectt.projectts.domain.Gallery;
import com.projectt.projectts.domain.House;
import com.projectt.projectts.domain.Location;
import com.projectt.projectts.domain.Plot;
import com.projectt.projectts.domain.PropertyType;
import com.projectt.projectts.domain.Request;
import com.projectt.projectts.domain.User;
import com.projectt.projectts.innerdomain.CarRequestData;
import com.projectt.projectts.innerdomain.HouseRequestData;
import com.projectt.projectts.innerdomain.InnerRequestData;
import com.projectt.projectts.innerdomain.PlotRequestData;
import com.projectt.projectts.repository.BiddingRquestRepository;
import com.projectt.projectts.repository.CarRepository;
import com.projectt.projectts.repository.GalleryRepository;
import com.projectt.projectts.repository.HouseRepository;
import com.projectt.projectts.repository.IUserRepository;
import com.projectt.projectts.repository.LocationRepository;
import com.projectt.projectts.repository.PlotRepository;
import com.projectt.projectts.repository.RequestRepository;
import com.projectt.projectts.service.IRequestService;
import com.projectt.projectts.utility.IUserMessage;

import ebaza.common.framework.exception.RequestException;
import ebaza.framework.files.properties.FileStoragePropertie;
import ebaza.framework.files.service.IFileStorageService;
import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.service.AbstractService;


@Service
@Transactional
public class RequestImplementattion extends AbstractService implements IRequestService {
	
	@Autowired
	RequestRepository Rrepo;
	
	@Autowired
	LocationRepository lrepo;
	
	@Autowired 
	IUserRepository urepo;
	
	@Autowired
	PlotRepository prepo;
	
	@Autowired
	BiddingRquestRepository brepo;
	
	@Autowired
	CarRepository crepo;
	
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
	private GalleryRepository grepo;


	@Override
	public Request create(Request request) {
		try {
			request.setRequestCode(generateRequestCode());
			return Rrepo.save(request);
		} catch (Exception e) {
			throw handleException(e);
		}

	}

	@Override
	public void delete(String uuid) {
		try {
			Optional<Request> p = Rrepo.findByIdAndStatus(UUID.fromString(uuid), EStatus.ACTIVE);
			if (p.isPresent()) {
				Request br = p.get();
				br.setStatus(EStatus.INACTIVE);
				this.create(br);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
		
	}

	@Override
	public Request findById(UUID id) {
		try {
			Optional<Request> br = Rrepo.findByIdAndStatus(id, EStatus.ACTIVE);
			if (br.isPresent()) {
				return br.get();
			} else {
				throw new RequestException(IUserMessage.OBJECT_NOT_FOUND);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public Page<Request> findAll(PageRequest pageRequest) {
		try {
			return Rrepo.findAll(pageRequest);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public List<Request> findByUser(UUID id) {
		try {
			return Rrepo.findByUserId(id);
		} catch (Exception ex) {
			throw handleException(ex);
		}

	}

	@Override
	public List<Request> findByLocation(UUID id) {
		try {
			return Rrepo.findByLocationId(id);
		} catch (Exception ex) {
			throw handleException(ex);
		}
		
	}

	@Override
	public Request createInitialRequest(InnerRequestData data) {
		try {
			Request r=new Request();
			Location l=null;
			User u=null;
			Optional<Location> location =lrepo.findById(UUID.fromString(data.getLocationId()));
			if (location.isPresent()) {
				l=location.get();
			}
			Optional<User> user =urepo.findById(UUID.fromString(data.getLocationId()));
			if (user.isPresent()) {
				u=user.get();
			}
			
			r.setDescription(data.getDescription());
			r.setLocation(l);
			r.setUser(u);
			r.setRequestCode(generateRequestCode());
			r.setPropertType(PropertyType.valueOf(data.getPropertyType()));
			
			return r;
		}catch (Exception ex) {
			throw handleException(ex);
		}
		
	}

	@Override
	public Request createPlotRequest(PlotRequestData data, UUID id,MultipartFile[] file) {
		Request re=null;
		Plot p=new Plot();
		Optional<Request> br = Rrepo.findByIdAndStatus(id, EStatus.ACTIVE);
		if (br.isPresent()) {
			re= br.get();
		}
		p.setWidth(data.getWidth());
		p.setLength(data.getLength());
		p.setPrice(data.getPrice());
		p.setPlotNumber(data.getPlotNumber());
		p.setBidStartdDate(data.getBidStartdDate());
		p.setBidEndDate(data.getBidEndDate());
		p.setLocation(re.getLocation());
		p.setCode(generatePlotCode());
		prepo.save(p);
		
		for (String image : this.uploadImages(file)) {
			Gallery gallery = new Gallery();
			gallery.setReferenceId(p.getId().toString());
			gallery.setPath(image);
			gallery.setReferenceName(PropertyType.Plot);
			grepo.save(gallery);
		}
		
		BiddingRequest brequest=new BiddingRequest();
		brequest.setMinAmount(data.getPrice());
		brequest.setApprovalStatus(ApprovalStatus.CREATED);
		brequest.setReferenceId(p.getId().toString());
		brequest.setReferenceName(PropertyType.Plot);
		brepo.save(brequest);
		
		re.setReferenceId(p.getId().toString());
		re.setReferenceName(PropertyType.Plot);
		re.setRequestCode(re.getRequestCode()+'/'+p.getCode());
		Rrepo.save(re);
			return re;	
	}

	@Override
	public Request createCarRequest(CarRequestData data, UUID id,MultipartFile[] file) {
		Request re=null;
		Car c=new Car();
		Optional<Request> br = Rrepo.findByIdAndStatus(id, EStatus.ACTIVE);
		if (br.isPresent()) {
			re= br.get();
		}
		c.setName(data.getName());
		c.setModel(data.getModel());
		c.setModelType(data.getModelType());
		c.setColor(data.getColor());
		c.setPlateNumber(data.getPlateNumber());
		c.setNumberOfSeat(data.getNumberOfSeat());
		c.setBidStartdDate(data.getBidStartdDate());
		c.setBidEndDate(data.getBidEndDate());
		c.setCode(generateCarCode());
		c.setLocation(re.getLocation());
		crepo.save(c);
		
		for (String image : this.uploadImages(file)) {
			Gallery gallery = new Gallery();
			gallery.setReferenceId(c.getId().toString());
			gallery.setPath(image);
			gallery.setReferenceName(PropertyType.Car);
			grepo.save(gallery);
		}
		
		BiddingRequest brequest=new BiddingRequest();
		brequest.setMinAmount(data.getPrice());
		brequest.setApprovalStatus(ApprovalStatus.CREATED);
		brequest.setReferenceId(c.getId().toString());
		brequest.setReferenceName(PropertyType.Car);
		brepo.save(brequest);
		
		re.setReferenceId(c.getId().toString());
		re.setReferenceName(PropertyType.Car);
		re.setRequestCode(re.getRequestCode()+'/'+c.getCode());
		Rrepo.save(re);
			return re;	
	}

	@Override
	public Request createHouseRequest(HouseRequestData data, UUID id,MultipartFile[] file) {
		Request re=null;
		Optional<Request> br = Rrepo.findByIdAndStatus(id, EStatus.ACTIVE);
		if (br.isPresent()) {
			re= br.get();
		}
		House h=new House();
		
		h.setRooms(Integer.parseInt(data.getRooms()));
		h.setPrice(data.getPrice());
		h.setBedrooms(Integer.parseInt(data.getBedrooms()));
		h.setType(EPropertyType.SALE);
		h.setBidStartdDate(data.getBidStartDate());
		h.setBidEndDate(data.getBidEndDate());
		h.setLocation(re.getLocation());
		h.setCode(generateHouseCode());
		hrepo.save(h);
		
		for (String image : this.uploadImages(file)) {
			Gallery gallery = new Gallery();
			gallery.setReferenceId(h.getId().toString());
			gallery.setPath(image);
			gallery.setReferenceName(PropertyType.House);
			grepo.save(gallery);
		}
		
		BiddingRequest brequest=new BiddingRequest();
		brequest.setMinAmount(data.getPrice());
		brequest.setApprovalStatus(ApprovalStatus.CREATED);
		brequest.setReferenceId(h.getId().toString());
		brequest.setReferenceName(PropertyType.House);
		brepo.save(brequest);
		
		re.setReferenceId(h.getId().toString());
		re.setReferenceName(PropertyType.House);
		re.setRequestCode(re.getRequestCode()+'/'+h.getCode());
		Rrepo.save(re);
			return re;	
	}
	
	public  String generateRequestCode(){
		String code="";
		
		List<Request>list=(List<Request>) Rrepo.findAll();
		int size=list.size();
	
		if(size==0){
			code="0001";
		}else{
			
			long newCode=(size+1);
			
			code="000"+newCode; 
		}
		
		return code;
	}
	
	public  String generateHouseCode(){
		String code="";
		
		List<House>list=(List<House>) hrepo.findAll();
		int size=list.size();
	
		if(size==0){
			code="0001";
		}else{
			
			long newCode=(size+1);
			
			code="000"+newCode; 
		}
		
		return code;
	}
	
	public  String generateCarCode(){
		String code="";
		
		List<Car>list=(List<Car>) crepo.findAll();
		int size=list.size();
	
		if(size==0){
			code="0001";
		}else{
			
			long newCode=(size+1);
			
			code="000"+newCode; 
		}
		
		return code;
	}
	
	public  String generatePlotCode(){
		String code="";
		
		List<Plot>list=(List<Plot>) prepo.findAll();
		int size=list.size();
	
		if(size==0){
			code="0001";
		}else{
			
			long newCode=(size+1);
			
			code="000"+newCode; 
		}
		
		return code;
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
