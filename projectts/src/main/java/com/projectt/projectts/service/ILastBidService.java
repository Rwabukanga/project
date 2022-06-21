package com.projectt.projectts.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import com.projectt.projectts.domain.LastBid;

public interface ILastBidService {
	LastBid create(LastBid last);
	void delete(String uuiid);
	LastBid findById(UUID id);
	Page<LastBid>findAll(PageRequest pageRequest);
	List<LastBid>findByClient(UUID  id);
}
