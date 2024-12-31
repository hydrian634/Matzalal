package com.matzalal.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.matzalal.web.service.ReportUserService;

@RestController("apiReportController")
@RequestMapping("/api/reports")
public class ReportController {
		
		@Autowired
		private ReportUserService service;
		

		
}