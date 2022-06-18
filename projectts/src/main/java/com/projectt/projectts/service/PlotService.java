package ebaza.codejava.service;



import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;

import ebaza.codejava.domain.Plot;





public interface PlotService {
	
	Plot createWithImages(Plot car, MultipartFile[] file); 
	Plot create(Plot plot);
	void delete(UUID id);
	Plot findById(UUID id);
	public Page<Plot>findAll(PageRequest pageRequest);
	public List<Plot>findByRegistrant(UUID id);

}
