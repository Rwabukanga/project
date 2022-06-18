package com.projectt.projectts.serviceImplementation;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projectt.projectts.domain.Location;
import com.projectt.projectts.repository.LocationRepository;
import com.projectt.projectts.service.ILocationService;
import com.projectt.projectts.utility.IUserMessage;

import ebaza.common.framework.exception.RequestException;
import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.service.AbstractService;

@Service
@Transactional
public class LocationImplementation extends AbstractService implements ILocationService {

	@Autowired
	LocationRepository localrepo;

	@Override
	public Location create(Location loc) {
		try {
			if (loc.getParentIds() != null) {
				Optional<Location> parentLocation = localrepo.findById(loc.getParentIds());
				loc.setParent(parentLocation.isPresent() ? parentLocation.get() : null);
				loc.setLevel(parentLocation.isPresent() ? parentLocation.get().getLevel() + 1 : 1);
			}
			return localrepo.save(loc);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public void delete(UUID id) {
		try {
			Optional<Location> p = localrepo.findByIdAndStatus(id, EStatus.ACTIVE);
			if (p.isPresent()) {
				Location pl = p.get();
				pl.setStatus(EStatus.INACTIVE);
				this.create(pl);
			}
		} catch (Exception e) {
			throw handleException(e);
		}

	}

	@Override
	public Location findById(UUID id) {
		try {
			System.out.println(id);
			Optional<Location> p = localrepo.findByIdAndStatus(id, EStatus.ACTIVE);
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
	public Page<Location> findAll(PageRequest pageRequest) {
		try {
			return localrepo.findAll(pageRequest);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public Page<Location> findByParent(UUID id, Pageable pageable) {
		return localrepo.findByParentIdAndStatus(id, EStatus.ACTIVE, pageable);
	}

	@Override
	public Page<Location> findProvinceLocation(Pageable pageable) {
		return localrepo.findByParentIdIsNull(pageable);
	}

}
