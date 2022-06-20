package com.projectt.projectts.serviceImplementation;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projectt.projectts.domain.BidAmountConfig;
import com.projectt.projectts.domain.Request;
import com.projectt.projectts.repository.BidConfigRepository;
import com.projectt.projectts.service.IBidConfigService;
import com.projectt.projectts.utility.IUserMessage;

import ebaza.common.framework.exception.RequestException;
import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.service.AbstractService;

@Service
@Transactional
public class BidConfigImplementation  extends AbstractService implements IBidConfigService {
	
	@Autowired
	BidConfigRepository crepo;
	
	@Override
	public BidAmountConfig create(BidAmountConfig config) {
		try {
			return crepo.save(config);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public void delete(String uuid) {
		try {
			Optional<BidAmountConfig> p = crepo.findByIdAndStatus(UUID.fromString(uuid), EStatus.ACTIVE);
			if (p.isPresent()) {
				BidAmountConfig br = p.get();
				br.setStatus(EStatus.INACTIVE);
				this.create(br);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
		
		
	}

	@Override
	public BidAmountConfig findById(UUID id) {
		try {
			Optional<BidAmountConfig> br = crepo.findByIdAndStatus(id, EStatus.ACTIVE);
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
	public Page<BidAmountConfig> findAll(PageRequest pageRequest) {
		try {
			return crepo.findAll(pageRequest);
		} catch (Exception e) {
			throw handleException(e);
		}
	}
	
	
}
