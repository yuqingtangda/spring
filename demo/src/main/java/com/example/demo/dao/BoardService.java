package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.BoardMapper;
import com.example.demo.model.Board;

@Service
public class BoardService {
	@Autowired
	BoardMapper boardMapper;
	
	public HashMap<String, Object> getBoardList(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			List<Board> list = boardMapper.selectBoardList(map);
			resultMap.put("list", list);
			resultMap.put("message", "데이터 조회 성공");
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("message", "서버 에러!");
			resultMap.put("result", "fail");
		}		
		return resultMap;
	}
	
	public HashMap<String, Object> addBoard(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			boardMapper.insertBoard(map);
			resultMap.put("message", "등록되었습니다.");
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("message", "서버 에러!");
			resultMap.put("result", "fail");
		}		
		return resultMap;
	}
	
	public HashMap<String, Object> getBoard(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			if(map.get("kind").equals("view")) {
				boardMapper.updateCnt(map);
			}		
			Board info = boardMapper.selectBoard(map);
			resultMap.put("info", info);
			resultMap.put("message", "데이터 조회 성공");
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("message", "서버 에러!");
			resultMap.put("result", "fail");
		}		
		return resultMap;
	}
	
	public HashMap<String, Object> editBoard(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			boardMapper.updateBoard(map);
			resultMap.put("message", "수정되었습니다.");
			resultMap.put("result", "success");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("message", "서버 에러!");
			resultMap.put("result", "fail");
		}		
		return resultMap;
	}
	
}