package com.example.demo.model;

import lombok.Data;

@Data
public class Board {
	int boardNo;
	String userId;
	String title;
	String contents;
	int cnt;
	int kind;
	String cDateTime;
	String uDateTime;
}