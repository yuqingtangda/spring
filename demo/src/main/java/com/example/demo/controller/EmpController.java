package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.EmpService;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class EmpController {

	@Autowired
	EmpService empService;


	@RequestMapping("/emp/list.do")
	public String emp(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map)
			throws Exception {
		return "/emp/emp-list";
	}
	
	@RequestMapping("/emp/add.do")
	public String add(Model model) throws Exception {
		return "/emp/emp-add";
	}

	@RequestMapping("/emp/view.do")
	public String empView(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map)
			throws Exception {
		System.out.println(map);
		request.setAttribute("map", map);
		return "/emp/emp-view";
	}


	@RequestMapping(value = "/emp/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String empList(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int pageSize = Integer.parseInt((String) map.get("pageSize"));
		int offSet = Integer.parseInt((String) map.get("offSet"));
		
		map.put("pageSize", pageSize);
		map.put("offSet", offSet);
		
        resultMap = empService.getEmpList(map);
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/emp/add.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = empService.addEmp(map);

		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/emp/info.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String empInfo(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = empService.getEmpInfo(map);
		return new Gson().toJson(resultMap);
	}

}
