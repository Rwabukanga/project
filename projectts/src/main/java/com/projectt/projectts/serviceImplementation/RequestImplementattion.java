package com.projectt.projectts.serviceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.projectt.projectts.domain.BiddingRequest;
import com.projectt.projectts.domain.Request;
import com.projectt.projectts.repository.RequestRepository;
import com.projectt.projectts.service.IRequestService;
import com.projectt.projectts.utility.IUserMessage;

import ebaza.common.framework.exception.RequestException;
import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.service.AbstractService;

public class RequestImplementattion extends AbstractService implements IRequestService {
	
	@Autowired
	RequestRepository Rrepo;

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

}
