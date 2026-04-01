package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.SchoolService;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class SchoolController {
	@Autowired
	SchoolService schoolService;
	
	@RequestMapping("/prof/list.do")
	public String profList(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map)
			throws Exception {
		return "/school/prof-list";
	}
	
	@RequestMapping("/stu/list.do")
	public String stuList(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map)
			throws Exception {
		return "/school/stu-list";
	}
	
	@RequestMapping("/stu/add.do")
	public String stuAdd(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map)
			throws Exception {
		return "/school/stu-add";
	}
	
	@RequestMapping("/prof/add.do")
	public String profAdd(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map)
			throws Exception {
		return "/school/prof-add";
	}
	
	@RequestMapping("/stu/view.do")
	public String stuView(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map)
			throws Exception {
		System.out.println(map);
		request.setAttribute("map", map);
		return "/school/stu-view";
	}
	
	@RequestMapping("/prof/view.do")
	public String profView(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map)
			throws Exception {
		System.out.println(map);
		request.setAttribute("map", map);
		return "/school/prof-view";
	}
	
	@RequestMapping("/stu/edit.do")
	public String stuEdit(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map)
			throws Exception {
		System.out.println(map);
		request.setAttribute("map", map);
		return "/school/stu-edit";
	}
	
	@RequestMapping("/prof/edit.do")
	public String profEdit(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map)
			throws Exception {
		System.out.println(map);
		request.setAttribute("map", map);
		return "/school/prof-edit";
	}
	
	
	@RequestMapping(value = "/prof/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String copy(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = schoolService.getProfList(map);
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/stu/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String stuList(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = schoolService.getStuList(map);
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/dept/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String deptList(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = schoolService.getDeptList(map);
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/stu/add.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String stuAdd(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = schoolService.addStu(map);
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/prof/add.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String profAdd(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = schoolService.addProf(map);
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/stu/check.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String check(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = schoolService.getStu(map);
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/stu/remove.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String stuRemove(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = schoolService.removeStu(map);
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/prof/remove.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String profRemove(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = schoolService.removeProf(map);
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/stu/info.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String stuInfo(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = schoolService.getStuInfo(map);
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/prof/info.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String profInfo(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = schoolService.getProfInfo(map);
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/stu/edit.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String stuEdit(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = schoolService.editStu(map);
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/prof/edit.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String profEdit(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
        resultMap = schoolService.editProf(map);
		return new Gson().toJson(resultMap);
	}

}
