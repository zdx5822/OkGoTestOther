package com.example.administrator.okgotest.JsonBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 返回消息定义
 * @author 连波
 *
 */
public class ReturnMessage implements Serializable {
	private String code;
	private String message;
	private int number;
	private List<Map<String, String>> content;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<Map<String, String>> getContent() {
		return content;
	}
	public void setContent(List<Map<String, String>> content) {
		this.content = content;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	
}
