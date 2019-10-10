package com.allianz.healthCheck.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allianz.healthCheck.domain.Portfolio;
import com.allianz.healthCheck.repository.PortfolioRepository;

@Service
public class PortfolioService {

	@Autowired
	private PortfolioRepository portfolioRepository;

	public List<Portfolio> findAll() {
		return portfolioRepository.findAll();
	}

	public Portfolio save(@Valid Portfolio portfolio) {
		@Valid
		Portfolio savedEntity = portfolioRepository.save(portfolio);
		return savedEntity;
	}

	public Optional<Portfolio> findById(Integer portfolioId) {
		Optional<Portfolio> portfolio = portfolioRepository.findById(portfolioId);
		return portfolio;
	}

}
