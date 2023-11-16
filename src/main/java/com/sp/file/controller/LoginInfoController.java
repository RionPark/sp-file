package com.sp.file.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sp.file.common.service.LoginInfoService;
import com.sp.file.vo.LoginInfoVO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LoginInfoController {

	private final LoginInfoService loginService;
	
	@PostMapping("/form/join")
	public String formJoin(LoginInfoVO login, Model m) {
		m.addAttribute("result", loginService.join(login));
		return "/html/msg";
	}


	@PostMapping("/api/join")
	@ResponseBody
	public Map<String,Integer> apiJoin(@RequestBody LoginInfoVO login) {
		Map<String,Integer> res = new HashMap<>();
		res.put("result", loginService.join(login));
		return res;
	}
}
