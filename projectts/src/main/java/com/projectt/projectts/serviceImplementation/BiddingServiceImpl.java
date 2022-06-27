package com.projectt.projectts.serviceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projectt.projectts.domain.ApprovalStatus;
import com.projectt.projectts.domain.Bidding;
import com.projectt.projectts.domain.LastBid;
import com.projectt.projectts.innerdomain.InnerBidData;
import com.projectt.projectts.repository.BidRepository;
import com.projectt.projectts.repository.BiddingRquestRepository;
import com.projectt.projectts.repository.IUserRepository;
import com.projectt.projectts.repository.LastBidRepository;
import com.projectt.projectts.service.IBidService;

import com.projectt.projectts.utility.IUserMessage;

import ebaza.common.framework.exception.RequestException;
import ebaza.common.framework.service.AbstractService;
import ebaza.framework.persistance.enumerator.EStatus;

@Service
@Transactional
public class BiddingServiceImpl extends AbstractService implements IBidService {
	
	@Autowired
	BidRepository brepo;
	@Autowired
	IUserRepository userRepo;
	
	@Autowired
	BiddingRquestRepository bidrepo;
	
	@Autowired
	LastBidRepository lastRepo;

	@Override
	public Bidding create(Bidding bid) {
		try {
			return brepo.save(bid);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public void delete(String uuid) {
		try {
			Optional<Bidding> p = brepo.findByIdAndStatus(UUID.fromString(uuid), EStatus.ACTIVE);
			if (p.isPresent()) {
				Bidding br = p.get();
				br.setStatus(EStatus.INACTIVE);
				this.create(br);
			}
		} catch (Exception e) {
			throw handleException(e);
		}
		
	}

	@Override
	public Bidding findById(UUID id) {
		try {
			Optional<Bidding> br = brepo.findByIdAndStatus(id, EStatus.ACTIVE);
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
	public Page<Bidding> findAll(PageRequest pageRequest) {
		try {
			return brepo.findAll(pageRequest);
		} catch (Exception e) {
			throw handleException(e);
		}
	}

	@Override
	public List<Bidding> findByClient(UUID id) {
		try {
			return brepo.findByClientId(id);
		} catch (Exception ex) {
			throw handleException(ex);
		}

	}

	@Override
	public Bidding createBid(InnerBidData data) {
		Bidding new_bid=new Bidding();
		new_bid.setAmount(data.getAmount());
		new_bid.setApproval_status(ApprovalStatus.CREATED);
		new_bid.setClient(userRepo.findById(UUID.fromString(data.getClientId())).get());
		new_bid.setRequest(bidrepo.findById(UUID.fromString(data.getRequestId())).get());
		
		LastBid new_last=null;
		Optional<LastBid> lastBid=lastRepo.findByRequestId(UUID.fromString(data.getRequestId()));
		if(lastBid.isPresent()) {
			new_last=lastBid.get();
			if(new_last.getAmount()<data.getAmount()) {
				new_last.setAmount(data.getAmount());
				lastRepo.save(new_last);
			}
		}else {
			new_last=new LastBid();
			new_last.setAmount(data.getAmount());
			new_last.setClient(userRepo.findById(UUID.fromString(data.getClientId())).get());
			lastRepo.save(new_last);
		}
		brepo.save(new_bid);
		return new_bid;
	}

}
