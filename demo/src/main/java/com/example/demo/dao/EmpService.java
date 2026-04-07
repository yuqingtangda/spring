package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.common.Message;
import com.example.demo.mapper.EmpMapper;
import com.example.demo.model.Emp;
import com.example.demo.model.Student;

import jakarta.servlet.http.HttpSession;

@Service
public class EmpService {
	@Autowired
	EmpMapper empMapper;
	
	@Autowired
	HttpSession session;

	public HashMap<String, Object> getEmpList(HashMap<String, Object> map) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		try {
			List<Emp> list = empMapper.selectEmpList(map);
			int totalCount = empMapper.selectEmpCount(map); 

			resultMap.put("list", list);
			resultMap.put("totalCount", totalCount);
			resultMap.put("result", "success");
			resultMap.put("message", Message.MSG_ADD);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("result", "fail");
			resultMap.put("message", Message.MSG_SERVER_ERR);
		}
		return resultMap;
	}
	
	public HashMap<String, Object> addEmp(HashMap<String, Object> map) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int cnt = empMapper.insertEmp(map);
			resultMap.put("message", "추가되었습니다!");
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("message", "서버 에러 발생!");
			resultMap.put("result", "fail");
		}

		return resultMap;
	}
	
	public HashMap<String, Object> getEmpInfo(HashMap<String, Object> map) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		try {
		    Emp info = empMapper.selectEmp(map);

			resultMap.put("info", info);
			resultMap.put("result", "success");
			resultMap.put("message", Message.MSG_SEARCH);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("result", "fail");
			resultMap.put("message", Message.MSG_SERVER_ERR);
		}
		return resultMap;
	}

}
