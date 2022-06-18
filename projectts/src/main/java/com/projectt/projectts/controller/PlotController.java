package ebaza.codejava.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import ebaza.codejava.domain.EPropertyType;
import ebaza.codejava.domain.Plot;
import ebaza.codejava.domain.Registrar;
import ebaza.codejava.service.IGalleryService;
import ebaza.codejava.service.IRegistarService;
import ebaza.codejava.service.PlotService;
import ebaza.codejava.utility.IConstants;




@RestController
@CrossOrigin
@RequestMapping("/api/plots")
public class PlotController {
	
	@Autowired
	PlotService plotservice;
	
	@Autowired
	IRegistarService regservice;
	
	@Autowired
	IGalleryService galleryservice;
	
	

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> all_(HttpServletRequest request, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {
		Sort sort = Sort.by(Sort.Direction.DESC, IConstants.UPDATED_DATE);
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		Page<Plot> car = plotservice.findAll(pageRequest);
		return new ResponseEntity<Object>(car, HttpStatus.OK);
	}
	
	@RequestMapping(value = "registrant/{uuid}/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findByRegistrant(@PathVariable("uuid") UUID uuid) {	
		return new ResponseEntity<Object>(plotservice.findByRegistrant(uuid), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPlotById(HttpServletRequest request,  @PathVariable("id") UUID id) {
		return new ResponseEntity<>(plotservice.findById(id), HttpStatus.OK);
	
	}
	
	@RequestMapping(value="/save/{id}",method=RequestMethod.POST)
	public ResponseEntity<Object> RegisterPlot(HttpServletRequest request,@RequestParam Map<String, String> params, HttpSession session,
			@RequestPart("files") MultipartFile[] files, @PathVariable UUID id) throws ParseException {
	
	
	
			     // saving plot
			    Plot p=new Plot();
			    p.setPrice(Double.parseDouble(params.get("price")));
			    p.setWidth(Double.parseDouble(params.get("width")));
			    p.setLength(Double.parseDouble(params.get("length")));
			    p.setPlotNumber(params.get("plotNumber"));
			    p.setType(EPropertyType.valueOf(params.get("type")));
			    Registrar r=regservice.findById(id);
			    p.setOwner(r);
			    p.setBidEndDate(new SimpleDateFormat("dd/MM/yyyy").parse(params.get("bid_end_date")));
			    p.setBidStartdDate(new SimpleDateFormat("dd/MM/yyyy").parse(params.get("bid_start_date")));

			    return new ResponseEntity<>(plotservice.createWithImages(p, files), HttpStatus.OK);
		
	}

	@RequestMapping(method = RequestMethod.DELETE,value = "/delete/{uuid}",consumes=MediaType.APPLICATION_JSON_VALUE)
	 public  ResponseEntity<Object> deletePlot(HttpServletRequest request, @PathVariable("id") UUID id){
		
		plotservice.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	

}
