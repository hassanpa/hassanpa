package com.allianz.healthCheck.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.allianz.healthCheck.domain.KPI;
import com.allianz.healthCheck.domain.KPIData;
import com.allianz.healthCheck.domain.KPIMaster;
import com.allianz.healthCheck.domain.OperatingEntity;
import com.allianz.healthCheck.domain.Organization;
import com.allianz.healthCheck.domain.Portfolio;
import com.allianz.healthCheck.domain.Project;
import com.allianz.healthCheck.domain.ServiceOffering;
import com.allianz.healthCheck.dto.ProjectDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class KPIDataServiceTest {
	
		
	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private OperatingEntityService operatingEntityService;
	
	@Autowired
	private ServiceOfferingService serviceOfferingService;
	
	@Autowired
	private PortfolioService portfolioService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private KPIService kpiService;
	
	@Autowired
	private KPIDataService kpiDataService;
	
	
	@Autowired
	private KPIMasterService kpiMasterService;

	
	private Organization organization;
	private OperatingEntity operatingEntity;
	private ServiceOffering serviceOffering;
	private Portfolio portfolio;
	private Project project,project1,project2,project3;
	
	private KPIMaster kpiMaster1,kpiMaster2,kpiMaster3;
	
	private KPI kraftKpi1,kraftKpi2;	
	private KPI lifeKpi1,lifeKpi2;
	private KPI cfsKpi1;
	private KPI pmsKpi1,pmsKpi2;
	
	private KPIData kraftKpiData1,kraftKpiData2;
	private KPIData lifeKpiData1,lifeKpiData2;
	
	private KPIData cfsKpiData1;
	
	private KPIData pmsKpiData1,pmsKpiData2;
	
	

	@Before
	public void init() {
		organization=new Organization("Allianz Technology India", "Allianz Technology India branch", "India");
		organization = organizationService.save(organization);
		
		operatingEntity=new OperatingEntity("Az Deu","Germany",organization);
		operatingEntity= operatingEntityService.save(operatingEntity);
		
		serviceOffering=new ServiceOffering("AD&M", "Application Development and Maintenance",40, operatingEntity);
		serviceOffering = serviceOfferingService.save(serviceOffering);
		
		portfolio=new Portfolio("ABBS","ABBS for Deu" , "ABS", "Staff Augumentation", 70, serviceOffering);
		portfolio = portfolioService.save(portfolio);
		
		String kraftProjectDesc="Kraft team enhances the functionality of ABS to accommodate new motor-products, as well as maintain existing products. It is a staff augmentation project, where team works on the code developed by Allianz Deutschland team.Team is not involved in any of the Business requirement and Software Design discussions as project decisions like design, infrastructure, execution methodology, training and resource hiring are defined by Deutschland.";
		project=new Project("Kraft","CP05PAC",10, 60, "Scrum", "Christian Blaim","christian.blaim@allianz.de",Long.valueOf("498938006445"),"NA","NA",Long.valueOf(0), "Claudia Santacroce","claudia.santacroce@allianz.de",Long.valueOf("4989380018684"),kraftProjectDesc,portfolio);
		
		projectService.save(project);
		
		String lifeProjectDesc="\r\n" + 
				"Leben team enhances the functionality of ABS to accommodate new life insurance products, as well as maintain existing products. It is a staff augmentation project, where team works on the code developed by Allianz Deutschland team.Team is not involved in any of the Business requirement and Software Design discussions as project decisions like design, infrastructure, execution methodology, training and resource hiring are defined by Deutschland.";
		project1=new Project("Life","CP05PLM",7, 30, "Scrum","","",Long.valueOf("497116634791"),"NA","NA",Long.valueOf(0), "Thomas Motsch","thomas.motsch@allianz.de",Long.valueOf("497116632553"),lifeProjectDesc,portfolio);
		
		projectService.save(project1);
		
		
		String cfsProjectDesc="CFS team enhances the JUnit coverage and functionality of ABS to accommodate new products, as well as maintain existing products. It is a staff augmentation project, where team works on the code developed by Allianz Deutschland team.Team is not involved in any of the Business requirement and Software Design discussions as project decisions like design, infrastructure, execution methodology, training and resource hiring are defined by Deutschland.";
		
		project2=new Project("CFS","CP05IOWC1",1, 10, "Waterfall","Viktor Mueller","viktor.mueller@allianz.de",Long.valueOf("497116633103"), "NA","NA",Long.valueOf(0),"Claudia Santacroce","claudia.santacroce@allianz.de",Long.valueOf("4989380018684"),cfsProjectDesc,portfolio);
		projectService.save(project2);
		
		
		String pmsProjectDesc="Program management Migrations and Services recently started under ABBS portfolio.  PMS deals with batch support in the area of Contract (Policy processing ) and Claims (Post processing) of ABS Deutschland.Team has started delivering from India which focuses in the area of ABS policies and claims batch development.";
		
		project3=new Project("PMS","CP05PMSP2",2, 20, "Scrum", "Stefan Von Fumetti","stefan.von.fumettai@allianz.de",Long.valueOf("4989380010027"),"NA","NA",Long.valueOf(0),"Claudia Santacroce","claudia.santacroce@allianz.de",Long.valueOf("4989380018684"),pmsProjectDesc,portfolio);
		projectService.save(project3);
		
		
		
		kpiMaster1 =new KPIMaster("OnTimeDeliveryIndex", "Delivering within the release plan");
		kpiMaster2 =new KPIMaster("Defect Count", "Delivering defects within the expected quantity");
		kpiMaster3 =new KPIMaster("JUNIT Coverage", "JUNIT Coverage 75%");
		
		kpiMasterService.save(kpiMaster1);
		kpiMasterService.save(kpiMaster2);
		kpiMasterService.save(kpiMaster3);
		
		
		kraftKpi1=new KPI(kpiMaster1, 90, 70, 80,project);
		kpiService.save(kraftKpi1);
		
		kraftKpi2=new KPI(kpiMaster2, 10, 8, 20,project);
		kpiService.save(kraftKpi2);
		
		lifeKpi1=new KPI(kpiMaster1, 90, 70, 70,project1);
		kpiService.save(lifeKpi1);
		

		lifeKpi2=new KPI(kpiMaster1, 90, 70, 70,project1);
		kpiService.save(lifeKpi2);
		
		
		
		lifeKpi1=new KPI(kpiMaster1, 90, 70, 70,project1);
		kpiService.save(lifeKpi1);
		
		lifeKpi2=new KPI(kpiMaster2, 10, 8, 30,project1);
		kpiService.save(lifeKpi2);
		
		cfsKpi1=new KPI(kpiMaster3, 75, 70, 100,project2);
		kpiService.save(cfsKpi1);
		

		pmsKpi1=new KPI(kpiMaster1, 90, 70, 60,project3);
		kpiService.save(pmsKpi1);
		

		pmsKpi2=new KPI(kpiMaster2, 10, 8, 40,project3);
		kpiService.save(pmsKpi2);
		
		
		kraftKpiData1=new KPIData(70, "Network outage", new Date(), kraftKpi1);
		kpiDataService.save(kraftKpiData1);
		
		kraftKpiData2=new KPIData(6, "Network outage", new Date(), kraftKpi2);
		kpiDataService.save(kraftKpiData2);
		
		lifeKpiData1=new KPIData(70, "Network outage", new Date(), lifeKpi1);
		kpiDataService.save(lifeKpiData1);
		
		lifeKpiData2=new KPIData(6, "Network outage", new Date(), lifeKpi2);
		kpiDataService.save(lifeKpiData2);

		cfsKpiData1=new KPIData(72, "Workspace Issue", new Date(), cfsKpi1);
		kpiDataService.save(cfsKpiData1);
		
		pmsKpiData1=new KPIData(70, "Network outage", new Date(), pmsKpi1);
		kpiDataService.save(pmsKpiData1);
		
		pmsKpiData2=new KPIData(6, "Network outage", new Date(), pmsKpi2);
		kpiDataService.save(pmsKpiData2);
		
	}
	
	@Test
	public void findAll() {
		List<KPIData> records = kpiDataService.findAll();
		

		    ProjectDTO projectDTO;
		    List<KPI> kpiList ;
		    List<KPIData> kpiDatasList;
			List<ProjectDTO> projectDTOList = new ArrayList<>();
			List<Project> projects = new ArrayList<>();
			projects.add(project);
			projects.add(project1);
			projects.add(project2);
			 int activeSLA=0;
				int atRiskSLA=0;
				int breachedSLA=0;
			for (Project project : projects) {
				projectDTO = new ProjectDTO();
				ModelMapper modelMapper = new ModelMapper();
				modelMapper.map(project, projectDTO);
				
				 kpiList = kpiService.getProjectKPIs(project);
				 kpiDatasList = kpiDataService.getKPIDatas(kpiList);
				for (KPIData kpiData : kpiDatasList) {
					 int thresholdMax=kpiData.getKpi().getThresholdMax();
					 int thresholdMin=kpiData.getKpi().getThresholdMin();
					 int thresholdValue= kpiData.getValue();
					if(thresholdValue!=0) {
						if(thresholdValue >= thresholdMin && thresholdValue <= thresholdMax) {
							activeSLA++;
						}else if(thresholdValue < thresholdMin) {
							atRiskSLA++;
						}else {
							breachedSLA++;
						}
					}
				}
				 projectDTO.setActiveSLA(activeSLA);
				 projectDTO.setAtRiskSLA(atRiskSLA);
				 projectDTO.setBreachedSLA(breachedSLA);
				projectDTOList.add(projectDTO);
			}
			for (ProjectDTO project : projectDTOList) {
				System.out.println(project.getProjectName()+"-"+project.getActiveSLA()+"-"+project.getAtRiskSLA()+"-"+project.getBreachedSLA());
			}
		assertEquals(true, records.size()>0);
		
	}
	
	@Test
	@Rollback(true)
	public void save() {
		
		KPIData savedEntity = kpiDataService.save(new KPIData(70, "Network outage", new Date(), kraftKpi1));
		System.out.println(savedEntity.getId());
		assertEquals(true, savedEntity.getId()>0);
		assertEquals("Network outage", savedEntity.getComments());
		
	}

	
	
	
}
