package com.cyc.dao;

import java.sql.SQLException;

public interface PublicDataDAO {
	public void UpdateToken(String token)throws SQLException;
	public String getToken()throws SQLException;
}
