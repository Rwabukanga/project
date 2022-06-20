package com.projectt.projectts.serviceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.projectt.projectts.domain.Bidding;
import com.projectt.projectts.domain.Request;
import com.projectt.projectts.repository.BidRepository;
import com.projectt.projectts.service.IBidService;
import com.projectt.projectts.utility.IUserMessage;

import ebaza.common.framework.exception.RequestException;
import ebaza.common.framework.service.AbstractService;
import ebaza.framework.persistance.enumerator.EStatus;

public class BiddingServiceImpl extends AbstractService implements IBidService {
	
	@Autowired
	BidRepository brepo;

	@Override
	public Bidding create(Bidding bid) {
		try {
			return brepo.save(bid);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public void delete(String uuid) {
		try {
			Optional<Bidding> p = brepo.findByIdAndStatus(UUID.fromString(uuid), EStatus.ACTIVE);
			if (p.isPresent()) {
				Bidding br = p.get();
				br.setStatus(EStatus.INACTIVE);
				this.create(br);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
		
	}

	@Override
	public Bidding findById(UUID id) {
		try {
			Optional<Bidding> br = brepo.findByIdAndStatus(id, EStatus.ACTIVE);
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
	public Page<Bidding> findAll(PageRequest pageRequest) {
		try {
			return brepo.findAll(pageRequest);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public List<Bidding> findByClient(UUID id) {
		try {
			return brepo.findByClientId(id);
		} catch (Exception ex) {
			throw handleException(ex);
		}

	}

}
