package com.projectt.projectts.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.projectt.projectts.domain.BiddingRequest;



@Service
public interface IBiddingService {
	
	BiddingRequest create(BiddingRequest request);
	void delete(String uuiid);
	BiddingRequest findById(UUID id);
	Page<BiddingRequest>findAll(PageRequest pageRequest);
	List<BiddingRequest>findByUser(UUID  id);
	

}
