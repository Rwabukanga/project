package com.projectt.projectts.service;


import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.projectt.projectts.domain.BidAmountConfig;

@Service
public interface IBidConfigService {
	
	BidAmountConfig create(BidAmountConfig config);
	void delete(String uuid);
	BidAmountConfig findById(UUID id);
	Page<BidAmountConfig>findAll(PageRequest pageRequest);
}
