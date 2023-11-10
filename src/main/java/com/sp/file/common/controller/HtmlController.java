package com.sp.file.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HtmlController {
	@GetMapping("/html/**")
	public void goPage() {}
	
	@GetMapping("/")
	public String home() {
		log.info("do=>home");
		return "/html/index";
	}
	
	@GetMapping("/aop")
	public void aop() {
		
	}
}
