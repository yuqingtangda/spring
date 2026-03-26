package com.example.demo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.User;

@Mapper
public interface UserMapper {
	public User selectUser(HashMap<String, Object> map);
	public int insertUser(HashMap<String, Object> map);
	
}