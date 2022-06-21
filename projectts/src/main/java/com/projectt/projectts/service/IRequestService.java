package com.projectt.projectts.service;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.projectt.projectts.domain.Request;
import com.projectt.projectts.innerdomain.CarRequestData;
import com.projectt.projectts.innerdomain.HouseRequestData;
import com.projectt.projectts.innerdomain.InnerRequestData;
import com.projectt.projectts.innerdomain.PlotRequestData;

@Service
public interface IRequestService {

	Request create(Request request);
	void delete(String uiid);
	Request findById(UUID id);
	Page<Request>findAll(PageRequest pageRequest);
	List<Request>findByUser(UUID  id);
	List<Request> findByLocation(UUID id );
	Request createInitialRequest(InnerRequestData data);
	Request createPlotRequest(PlotRequestData data,UUID id);
	Request createCarRequest(CarRequestData data,UUID id);
	Request createHouseRequest(HouseRequestData data,UUID id);
}
