package com.cyc.dao;

import java.util.List;

public interface FollowDAO {
	public int getFollowNum(int userid)throws Exception;
	public int getFollowerNum(int userid)throws Exception;
	public List<Integer> getFollows(int userid)throws Exception;
	public List<Integer> getFollowers(int userid)throws Exception;
}
