package com.intkr.saas.domain.bo.user;

import com.intkr.saas.domain.BaseBO;
import com.intkr.saas.util.MD5Util;

/**
 * 
 * @author Beiden
 * @date 2016-5-28 下午10:50:35
 * @version 1.0
 */
public class SecureQuestionBO extends BaseBO {

	private static final long serialVersionUID = 1L;

	private Long userId;

	private String code;

	private String question;

	private String answer;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void encryptAnswer() {
		String randomString = MD5Util.getRandomAdd();
		String enAnswer = MD5Util.encrypt(answer, randomString);
		this.answer = enAnswer + "|" + randomString;
	}

}
