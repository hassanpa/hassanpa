package com.allianz.healthCheck.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allianz.healthCheck.domain.Portfolio;
import com.allianz.healthCheck.domain.Project;
import com.allianz.healthCheck.service.PortfolioService;
import com.allianz.healthCheck.service.ProjectService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController 
public class ProjectController {
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	PortfolioService portfolioService;
	
	
	@GetMapping("/healthApp/getProjectsByPortfolio")
    public List<Project> getProjectsByPortfolio(@RequestParam(required=true,defaultValue="164")Integer portfolioId) {
		Optional<Portfolio> portfolio= portfolioService.findById(portfolioId);
		List<Project> projects=projectService.getProjectsByPortfolio(portfolio.get());
        return projects;
    }

}
