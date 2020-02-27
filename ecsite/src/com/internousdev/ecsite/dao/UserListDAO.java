package com.internousdev.ecsite.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.ecsite.dto.UserInfoDTO;
import com.internousdev.ecsite.util.DBConnector;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class UserListDAO {
	private DBConnector dbConnector = new DBConnector();
	private Connection con = (Connection) dbConnector.getConnection();

	public List<UserInfoDTO> getUserList() throws SQLException {
		List<UserInfoDTO> userInfoDTOList = new ArrayList<UserInfoDTO>();
		String sql ="SELECT * FROM login_user_transaction ORDER BY id";
		try {

			PreparedStatement preparedStatement = (PreparedStatement) con.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				UserInfoDTO dto = new UserInfoDTO();
				dto.setId(resultSet.getString("id"));
				dto.setLoginId(resultSet.getString("login_id"));
				dto.setLoginPass(resultSet.getString("login_pass"));
				dto.setUserName(resultSet.getString("user_name"));
				dto.setInsert_date(resultSet.getString("insert_date"));
				dto.setUpdate_date(resultSet.getString("updated_date"));
				userInfoDTOList.add(dto);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return userInfoDTOList;
	}

}

