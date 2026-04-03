package com.example.demo.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.common.Message;
import com.example.demo.mapper.UserMapper;
import com.example.demo.model.User;

import jakarta.servlet.http.HttpSession;

@Service
public class UserService {
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	public HashMap<String, Object> login(HashMap<String, Object> map) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		User user = userMapper.selectUser(map);
		resultMap.put("loginResult", false);
		if (user != null) {
			if (passwordEncoder.matches((String) map.get("pwd"), user.getPwd())) {				
				resultMap.put("message", user.getUserName() + "님 환영합니다.");
				resultMap.put("loginResult", true);
				session.setAttribute("sessionId", user.getUserId());
				session.setAttribute("sessionName", user.getUserName());
				session.setAttribute("sessionRole", user.getRole());
				
//				if(user.getRole().equals("A")) {
//					resultMap.put("url","/prof/list.do");
//				} else {
//					resultMap.put("url","/stu/list.do");
//				}
				resultMap.put("url","/board/list.do");
								
				
//				session.invalidate(); 세션 모든 정보 삭제(로그아웃 버튼 
				
			} else {
				resultMap.put("message", "비밀번호를 확인하세요.");
			}
		} else {
			resultMap.put("message", "없는 아이디 입니다");
		}
		resultMap.put("result", "success");

		return resultMap;
	}

	public HashMap<String, Object> addUser(HashMap<String, Object> map) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		try {
			String hashPwd = passwordEncoder.encode((String) map.get("pwd"));
			map.put("hashPwd", hashPwd);
			int cnt = userMapper.insertUser(map);
			if (cnt > 0) {
				resultMap.put("message", "회원가입 축하!");
			} else {
				resultMap.put("message", "회원가입 실패.다시 시도해주세요.");
			}
			resultMap.put("result", "success");

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("message", "서버 에러 발생! \n잠시 후 다시 시도하세요.");
			resultMap.put("result", "fail");
		}

		return resultMap;
	}

	public HashMap<String, Object> checkUser(HashMap<String, Object> map) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			User user = userMapper.selectUser(map);
			if (user != null) {
				resultMap.put("message", "이미 사용중인 아이디 입니다.");
				resultMap.put("result", false);
			} else {
				resultMap.put("message", "사용 가능한 아이디 입니다.");
				resultMap.put("result", true);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println();
			resultMap.put("message", "서버 에러.");
		}
		return resultMap;
	}

//	== 복습 ==
	public HashMap<String, Object> getUserList(HashMap<String, Object> map) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();

		try {
			List<User> list = userMapper.selectUserList(map);

			resultMap.put("list", list);
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

	public HashMap<String, Object> removeUser(HashMap<String, Object> map) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		try {
			int result = userMapper.deleteUser(map);
			resultMap.put("result", "success");
			resultMap.put("message", Message.MSG_REMOVE);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			resultMap.put("result", "fail");
			resultMap.put("message", Message.MSG_SERVER_ERR);
		}
		return resultMap;
	}

}