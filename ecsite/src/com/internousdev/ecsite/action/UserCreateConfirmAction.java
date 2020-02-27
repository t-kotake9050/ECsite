package com.internousdev.ecsite.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite.dao.UserCreateConfirmDAO;
import com.internousdev.ecsite.dao.UserListDAO;
import com.internousdev.ecsite.dto.UserInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class UserCreateConfirmAction extends ActionSupport implements SessionAware {

	private String loginUserId;
	private String loginPassword;
	private String userName;
	private Map<String, Object> session;
	private String errorMessage;
	private List<UserInfoDTO> userInfoDTOList = new ArrayList<UserInfoDTO>();

	public String execute() throws SQLException {
		String result = SUCCESS;
		//		リスト呼び出し
		UserListDAO userListDAO = new UserListDAO();
		setUserInfoDTOList(userListDAO.getUserList());

		if (!(loginUserId.equals("")) && !(loginPassword.equals("")) && !(userName.equals(""))) {
			UserCreateConfirmDAO userCreateConfirmDAO = new UserCreateConfirmDAO();
			if (!userCreateConfirmDAO.isExistUser(loginUserId)) {
				session.put("loginUserId", loginUserId);
				session.put("loginPassword", loginPassword);
				session.put("userName", userName);
			} else {
				setErrorMessage("すでに登録されているログインIDです。");
				result = ERROR;
			}
		} else {
			setErrorMessage("未入力の項目があります。");
			result = ERROR;
		}
		return result;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Map<String, Object> getSession() {
		return this.session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<UserInfoDTO> getUserInfoDTOList() {
		return userInfoDTOList;
	}

	public void setUserInfoDTOList(List<UserInfoDTO> userInfoDTOList) {
		this.userInfoDTOList = userInfoDTOList;
	}
}
