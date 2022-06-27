package com.projectt.projectts.serviceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projectt.projectts.domain.ApprovalStatus;
import com.projectt.projectts.domain.BiddingRequest;
import com.projectt.projectts.domain.Car;
import com.projectt.projectts.domain.EPropertyType;
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
import com.projectt.projectts.repository.HouseRepository;
import com.projectt.projectts.repository.IUserRepository;
import com.projectt.projectts.repository.LocationRepository;
import com.projectt.projectts.repository.PlotRepository;
import com.projectt.projectts.repository.RequestRepository;
import com.projectt.projectts.service.IRequestService;
import com.projectt.projectts.utility.IUserMessage;

import ebaza.common.framework.exception.RequestException;
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

	@Override
	public Request create(Request request) {
		try {
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
			r.setPropertType(PropertyType.valueOf(data.getPropertyType()));
			
			return r;
		}catch (Exception ex) {
			throw handleException(ex);
		}
		
	}

	@Override
	public Request createPlotRequest(PlotRequestData data, UUID id) {
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
		prepo.save(p);
		
		BiddingRequest brequest=new BiddingRequest();
		brequest.setMinAmount(data.getPrice());
		brequest.setApprovalStatus(ApprovalStatus.CREATED);
		brequest.setReferenceId(p.getId().toString());
		brequest.setReferenceName("Plot");
		brepo.save(brequest);
		
		re.setReferenceId(p.getId().toString());
		re.setReferenceName("Plot");
		Rrepo.save(re);
			return re;	
	}

	@Override
	public Request createCarRequest(CarRequestData data, UUID id) {
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
		c.setLocation(re.getLocation());
	
		
		BiddingRequest brequest=new BiddingRequest();
		brequest.setMinAmount(data.getPrice());
		brequest.setApprovalStatus(ApprovalStatus.CREATED);
		brequest.setReferenceId(c.getId().toString());
		brequest.setReferenceName("Car");
		brepo.save(brequest);
		
		re.setReferenceId(c.getId().toString());
		re.setReferenceName("Car");
		Rrepo.save(re);
			return re;	
	}

	@Override
	public Request createHouseRequest(HouseRequestData data, UUID id) {
		Request re=null;
		Car c=new Car();
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
		hrepo.save(h);
		
		BiddingRequest brequest=new BiddingRequest();
		brequest.setMinAmount(data.getPrice());
		brequest.setApprovalStatus(ApprovalStatus.CREATED);
		brequest.setReferenceId(h.getId().toString());
		brequest.setReferenceName("House");
		brepo.save(brequest);
		
		re.setReferenceId(h.getId().toString());
		re.setReferenceName("House");
		Rrepo.save(re);
			return re;	
	}

}
