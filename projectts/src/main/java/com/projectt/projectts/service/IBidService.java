package com.projectt.projectts.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import com.projectt.projectts.domain.Bidding;
import com.projectt.projectts.innerdomain.InnerBidData;

public interface IBidService {
	Bidding create(Bidding bid);
	void delete(String uuiid);
	Bidding findById(UUID id);
	Page<Bidding>findAll(PageRequest pageRequest);
	List<Bidding>findByClient(UUID  id);
	Bidding createBid(InnerBidData data);
}
