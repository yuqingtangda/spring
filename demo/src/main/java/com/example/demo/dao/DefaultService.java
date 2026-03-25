package com.example.demo.dao;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.DefaultMapper;

@Service
public class DefaultService {
	@Autowired
	DefaultMapper defaultMapper;
	
	public HashMap<String, Object> getUserList(){
		
		
		defaultMapper.selectUserList();		
		return null;
	}

}
