package com.allianz.healthCheck.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allianz.healthCheck.domain.Portfolio;
import com.allianz.healthCheck.domain.Project;
import com.allianz.healthCheck.repository.ProjectRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	public List<Project> findAll() {
		return projectRepository.findAll();
	}

	public Project save(@Valid Project project) {
		@Valid
		Project savedEntity = projectRepository.save(project);
		return savedEntity;
	}
	
	public List<Project> getProjectsByPortfolio(Portfolio portfolio) {
		return projectRepository.findByPortfolio(portfolio);
	}
	

}
