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
import com.projectt.projectts.domain.Request;
import com.projectt.projectts.repository.BidPaymentRepository;
import com.projectt.projectts.service.IBidPaymentService;
import com.projectt.projectts.utility.IUserMessage;

import ebaza.common.framework.exception.RequestException;
import ebaza.common.framework.service.AbstractService;
import ebaza.framework.persistance.enumerator.EStatus;

@Service
@Transactional
public class BidPaymentServiceImpl extends AbstractService implements IBidPaymentService {
	
	
	@Autowired
	BidPaymentRepository prepo;

	@Override
	public BidPayment create(BidPayment payment) {
		try {
			return prepo.save(payment);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public void delete(String uuid) {
		try {
			Optional<BidPayment> p = prepo.findByIdAndStatus(UUID.fromString(uuid), EStatus.ACTIVE);
			if (p.isPresent()) {
				BidPayment br = p.get();
				br.setStatus(EStatus.INACTIVE);
				this.create(br);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
		
	}

	@Override
	public BidPayment findById(UUID id) {
		try {
			Optional<BidPayment> br = prepo.findByIdAndStatus(id, EStatus.ACTIVE);
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
	public Page<BidPayment> findAll(PageRequest pageRequest) {
		try {
			return prepo.findAll(pageRequest);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public List<BidPayment> findByClient(UUID id) {
		try {
			return prepo.findByClientId(id);
		} catch (Exception ex) {
			throw handleException(ex);
		}
	}

}
