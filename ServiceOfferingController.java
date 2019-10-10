package com.allianz.healthCheck.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allianz.healthCheck.domain.ServiceOffering;
import com.allianz.healthCheck.service.OperatingEntityService;
import com.allianz.healthCheck.service.ServiceOfferingService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
public class ServiceOfferingController {

	
	@Autowired
	ServiceOfferingService serviceOfferingService;
	
	@Autowired
	OperatingEntityService OperatingEntityService;
	
	
	/*
	 * @RequestMapping(value = "/healthApp/searchServiceOfferings",method =
	 * RequestMethod.GET) public ModelAndView
	 * filterServiceOffering(@Valid @ModelAttribute("servicesOffer") ServiceOffering
	 * serviceOffering, final BindingResult result) { ModelAndView modelAndView =
	 * new ModelAndView(); List<ServiceOffering> serviceOfferings =
	 * serviceOfferingService.findAll(); modelAndView.addObject("servicesOffer",
	 * serviceOfferings); modelAndView.setViewName("serviceOfferings"); return
	 * modelAndView; }
	 */
	
	
	@GetMapping("/healthApp/getServiceOfferingsByOE")
    public List<ServiceOffering> getServiceOfferingsByOE() {
		
		List<ServiceOffering> serviceOfferings=serviceOfferingService.findByOperatingEntity(171);
        return serviceOfferings;
    }
	
	
}
