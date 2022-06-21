package com.projectt.projectts.serviceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.projectt.projectts.domain.BidPayment;
import com.projectt.projectts.domain.LastBid;
import com.projectt.projectts.repository.LastBidRepository;
import com.projectt.projectts.service.ILastBidService;
import com.projectt.projectts.utility.IUserMessage;

import ebaza.common.framework.exception.RequestException;
import ebaza.common.framework.service.AbstractService;
import ebaza.framework.persistance.enumerator.EStatus;

@Service
@Transactional
public class LastBidServiceImpl extends AbstractService implements ILastBidService {

	@Autowired
	LastBidRepository lrepo;
	
	@Override
	public LastBid create(LastBid last) {
		try {
			return lrepo.save(last);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public void delete(String uuid) {
		try {
			Optional<LastBid> p = lrepo.findByIdAndStatus(UUID.fromString(uuid), EStatus.ACTIVE);
			if (p.isPresent()) {
				LastBid br = p.get();
				br.setStatus(EStatus.INACTIVE);
				this.create(br);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
		
	}

	@Override
	public LastBid findById(UUID id) {
		try {
			Optional<LastBid> br = lrepo.findByIdAndStatus(id, EStatus.ACTIVE);
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
	public Page<LastBid> findAll(PageRequest pageRequest) {
		try {
			return lrepo.findAll(pageRequest);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public List<LastBid> findByClient(UUID id) {
		try {
			return lrepo.findByClientId(id);
		} catch (Exception ex) {
			throw handleException(ex);
		}
	}

}
