package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.DefaultMapper;
import com.example.demo.model.Student;
import com.example.demo.model.User;

@Service
public class DefaultService {
	@Autowired
	DefaultMapper defaultMapper;

	public HashMap<String, Object> getUserList() {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<User> list = defaultMapper.selectUserList();

		resultMap.put("list", list);
		resultMap.put("message", "데이터 조회 성공");
		resultMap.put("result", "success");

		return resultMap;
	}

}
