package com.spring.boot.cricketLeage.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.boot.cricketLeage.model.Teams;
import com.spring.boot.cricketLeage.service.TeamsService;

@Controller
public class TeamsController {

	@Autowired
	private TeamsService teamsService;

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String homePage() {
		return "home";
	}

	@RequestMapping(value = { "/teamsTable" }, method = RequestMethod.GET)
	public String teamsTablePage(Model model) {
		List<Teams> teams = teamsService.getAllTeams();
		model.addAttribute("teams", teams);
//		System.out.println(teams);
		return "teamsTable";
	}

	@RequestMapping(value = { "/addInfo" }, method = RequestMethod.GET)
	public String addInfoPage() {
		return "addInfo";
	}

	@RequestMapping(value = { "/submitForm" }, method = { RequestMethod.POST, RequestMethod.GET })
	public String submitFormButton(@ModelAttribute("teamsModel") Teams teamsModel, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "error";
		}
		teamsService.insertData(teamsModel);

		return "home";
	}

	@RequestMapping(value = { "/deleteTeam/{teamId}" }, method = RequestMethod.GET)
	public String deleteTeam(@PathVariable("teamId") Long teamId) {
		teamsService.deleteTeams(teamId);
		return "redirect:/teamsTable";
	}

	@RequestMapping(value = { "/updateTeam/{teamId}" }, method = RequestMethod.GET)
	public String updateFormPage(@ModelAttribute("teamsModel") Teams teamsModel, @PathVariable("teamId") Long teamId,
			Model model) {
		teamsService.deleteTeams(teamId);

		String updTeamName = teamsModel.getTeamName();
		String updCaptain = teamsModel.getCaptain();
		String updCity = teamsModel.getCity();

		model.addAttribute("updTeamName", updTeamName);
		model.addAttribute("updCaptain", updCaptain);
		model.addAttribute("updCity", updCity);

		return "updateTeam";
	}

	@RequestMapping(value = { "/submitUpdatedForm" }, method = { RequestMethod.POST, RequestMethod.GET })
	public String submitUpdatedFormButton(@ModelAttribute("teamsModel") Teams teamsModel, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "error";
		}
		teamsService.insertData(teamsModel);

		return "redirect:/teamsTable";
	}

	@RequestMapping(value = { "/exportExcel" }, method = RequestMethod.GET)
	public String exportToExcel(HttpServletResponse response)  throws IOException {
//		System.out.println("Excel in");
		String location = "C:\\Users\\jimstevevictor\\Downloads\\cricketteam.xlsx";
		List<Teams> excelTeam = teamsService.getAllTeams();
//		response.setHeader("Content-Disposition", "attachment;filename=\"cricketteam.xlsx\"");
		XSSFWorkbook book = new XSSFWorkbook();
		XSSFSheet sheet = book.createSheet("Cricket");

		teamsService.writeHeaders(sheet);
		teamsService.exportToExcel(excelTeam,book,sheet);
		FileOutputStream file = new FileOutputStream(location);
//		response.setContentType("application/vnd.ms-excel");
//		response.setHeader("Content-Disposition", "attachment; filename=marks.xlsx");
//		ServletOutputStream outStream = response.getOutputStream();
		book.write(file);
		book.close();
		file.close();
//		System.out.println("Excel out");
		return "redirect:/teamsTable";
	}

}
