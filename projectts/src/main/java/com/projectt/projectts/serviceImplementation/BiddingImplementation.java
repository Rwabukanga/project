package ebaza.codejava.serviceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ebaza.codejava.domain.BiddingRequest;
import ebaza.codejava.innerdomain.InnerApproval;
import ebaza.codejava.innerdomain.InnerRequest;
import ebaza.codejava.repository.BiddingRepository;
import ebaza.codejava.service.IBiddingService;
import ebaza.codejava.service.ICarService;
import ebaza.codejava.service.IHouseService;
import ebaza.codejava.service.IRegistarService;
import ebaza.codejava.service.IUserService;
import ebaza.codejava.service.PlotService;
import ebaza.codejava.utility.IUserMessage;
import ebaza.common.framework.exception.RequestException;
import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.service.AbstractService;

@Service
@Transactional
public class BiddingImplementation extends AbstractService implements IBiddingService {

	@Autowired
	BiddingRepository brepo;

	@Autowired
	IUserService userservice;

	@Autowired
	PlotService plotservice;

	@Autowired
	ICarService carservice;

	@Autowired
	IHouseService houseservice;

	@Autowired
	IRegistarService regservice;

	@Override
	public BiddingRequest create(BiddingRequest request) {

		try {
			return brepo.save(request);
		} catch (Exception e) {
			throw handleException(e);
		}

	}

	@Override
	public void delete(String uuid) {
		try {
			Optional<BiddingRequest> p = brepo.findByIdAndStatus(UUID.fromString(uuid), EStatus.ACTIVE);
			if (p.isPresent()) {
				BiddingRequest br = p.get();
				br.setStatus(EStatus.INACTIVE);
				this.create(br);
			}
		} catch (Exception e) {
			throw handleException(e);
		}

	}

	@Override
	public BiddingRequest findById(UUID id) {
		try {
			Optional<BiddingRequest> br = brepo.findByIdAndStatus(id, EStatus.ACTIVE);
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
	public Page<BiddingRequest> findAll(PageRequest pageRequest) {
		try {
			return brepo.findAll(pageRequest);
		} catch (Exception e) {
			throw handleException(e);
		}
	}


	@Override
	public List<BiddingRequest> findByUser(UUID id) {
		try {
			return brepo.findByUserId(id);
		} catch (Exception ex) {
			throw handleException(ex);
		}

	}

}
