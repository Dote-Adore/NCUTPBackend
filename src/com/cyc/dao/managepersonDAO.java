package com.cyc.dao;

import java.sql.SQLException;


public interface managepersonDAO {
	boolean login(String user, String password) throws SQLException;
	void updateSession(String username, String session) throws SQLException;
	void addPerson(String user, String password)throws SQLException;
	String getusername(String session)throws SQLException;
}
