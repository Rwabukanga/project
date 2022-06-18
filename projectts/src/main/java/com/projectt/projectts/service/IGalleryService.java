package com.projectt.projectts.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.projectt.projectts.domain.Gallery;







public interface IGalleryService {
	
	Gallery create(Gallery g);
	void delete(String uuid);
	Gallery findById(UUID id);
	Page<Gallery>findByProperty(UUID  referenceId, Pageable pageable);

}
