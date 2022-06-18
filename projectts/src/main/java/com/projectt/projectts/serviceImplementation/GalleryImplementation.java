package com.projectt.projectts.serviceImplementation;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.projectt.projectts.domain.Gallery;
import com.projectt.projectts.repository.GalleryRepository;
import com.projectt.projectts.service.IGalleryService;
import com.projectt.projectts.utility.IUserMessage;

import ebaza.common.framework.exception.RequestException;
import ebaza.framework.persistance.enumerator.EStatus;
import ebaza.framework.persistance.service.AbstractService;

@Service
@Transactional
public class GalleryImplementation extends AbstractService implements IGalleryService {


	@Autowired
	GalleryRepository grepo;


	@Override
	public Gallery create(Gallery g) {
		try {
			return grepo.save(g);
		} catch (Exception e) {
			throw handleException(e);
		}
	}


	@Override
	public void delete(String uuid) {
		try {
			Optional<Gallery> g = grepo.findByIdAndStatus(UUID.fromString(uuid), EStatus.ACTIVE);
			if (g.isPresent()) {
				Gallery gl = g.get();
				gl.setStatus(EStatus.INACTIVE);
				this.create(gl);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
		
	}

	@Override
	public Gallery findById(UUID id) {
		try {
			Optional<Gallery> p = grepo.findByIdAndStatus(id, EStatus.ACTIVE);
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
	public Page<Gallery> findByProperty(UUID referenceId, Pageable pageable) {
		return grepo.findByReferenceIdAndStatus(referenceId, EStatus.ACTIVE, pageable);
	}

}
