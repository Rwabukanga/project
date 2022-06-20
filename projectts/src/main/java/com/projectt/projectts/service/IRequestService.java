package com.projectt.projectts.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.projectt.projectts.domain.Request;

@Service
public interface IRequestService {

	Request create(Request request);
	void delete(String uiid);
	Request findById(UUID id);
	Page<Request>findAll(PageRequest pageRequest);
	List<Request>findByUser(UUID  id);
	List<Request> findByLocation(UUID id );
}
