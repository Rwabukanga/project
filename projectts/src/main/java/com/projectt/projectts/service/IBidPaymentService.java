package com.projectt.projectts.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.projectt.projectts.domain.BidPayment;


@Service
public interface IBidPaymentService {
	
	BidPayment create(BidPayment payment);
	void delete(String uuiid);
	BidPayment findById(UUID id);
	Page<BidPayment>findAll(PageRequest pageRequest);
	List<BidPayment>findByClient(UUID  id);

}
