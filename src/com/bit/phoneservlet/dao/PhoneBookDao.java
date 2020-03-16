package com.bit.phoneservlet.dao;

import java.util.List;

import com.bit.phoneservlet.vo.PhoneBookVO;



public interface PhoneBookDao {

	public List<PhoneBookVO> getList();
	public boolean insert(PhoneBookVO vo);
	public boolean update(PhoneBookVO vo);
	public boolean delete(Long id);
	public List<PhoneBookVO> searchName(String name);
	
}
