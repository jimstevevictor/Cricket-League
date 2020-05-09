package com.spring.boot.cricketLeage.service;

import java.util.Iterator;
import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.boot.cricketLeage.model.Teams;
import com.spring.boot.cricketLeage.repository.TeamsRepository;

@Service
public class TeamsService {

	@Autowired
	private TeamsRepository teamsRepository;

	public List<Teams> getAllTeams() {
		List<Teams> teams = (List<Teams>) teamsRepository.findAll();
		return teams;
	}

	public void insertData(Teams teams) {
		teamsRepository.save(teams);
	}

	public void deleteTeams(Long id) {

		teamsRepository.deleteById(id);
	}

	public void exportToExcel(List<Teams> excelTeam, XSSFWorkbook book, XSSFSheet sheet) {
//		Iterator<Teams> iterator = excelTeam.iterator();
		int rownum = 1;
		
		
//			int columnCount=0;
			for (Teams team : excelTeam) {
				Row row = sheet.createRow(rownum++);
				row.createCell(1).setCellValue(team.getTeamName());
				row.createCell(2).setCellValue(team.getCaptain());
				row.createCell(3).setCellValue(team.getCity());
			}	 
			
			
		
	}

	public void writeHeaders(XSSFSheet sheet) {
		Row headerRow = sheet.createRow(0);
		Cell headercell = headerRow.createCell(1);
		headercell.setCellValue("Team Name");
		
		headercell = headerRow.createCell(2);
		headercell.setCellValue("Captain");
		
		headercell = headerRow.createCell(3);
		headercell.setCellValue("City");

	}

//	public void updateTeams(Long id) {
//
//		teamsRepository.saveAll(id);
//	}

}
