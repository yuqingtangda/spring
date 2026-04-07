package com.example.demo.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.model.Emp;

@Mapper
public interface EmpMapper {
	// emp 목록
	public List<Emp> selectEmpList(HashMap<String, Object> map);
	
	public int insertEmp(HashMap<String, Object> map);
	
	public Emp selectEmp(HashMap<String, Object> map);
	
	public int selectEmpCount(HashMap<String, Object> map);

}
