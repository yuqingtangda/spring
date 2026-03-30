package com.example.demo.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.BoardService;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BoardController {
	@Autowired
	BoardService boardService;

	@RequestMapping("/board/list.do")
	public String list(Model model) throws Exception {
		return "/board/board-list";
	}

	@RequestMapping("/board/add.do")
	public String add(Model model) throws Exception {
		return "/board/board-add";
	}

	@RequestMapping("/board/view.do")
	public String view(HttpServletRequest request, @RequestParam HashMap<String, Object> map) throws Exception {
		System.out.println(map);
		request.setAttribute("boardNo", map.get("boardNo"));
		return "/board/board-view";
	}

	@RequestMapping("/board/edit.do")
	public String edit(HttpServletRequest request, @RequestParam HashMap<String, Object> map) throws Exception {
		System.out.println(map);
		request.setAttribute("boardNo", map.get("boardNo"));
		return "/board/board-edit";
	}

	@RequestMapping(value = "/board/list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String list(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = boardService.getBoardList(map);

		return new Gson().toJson(resultMap);
	}

	@RequestMapping(value = "/board/add.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String add(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = boardService.addBoard(map);

		return new Gson().toJson(resultMap);
	}

	@RequestMapping(value = "/board/info.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String info(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = boardService.getBoard(map);

		return new Gson().toJson(resultMap);
	}

	@RequestMapping(value = "/board/edit.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String edit(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = boardService.editBoard(map);

		return new Gson().toJson(resultMap);
	}
}