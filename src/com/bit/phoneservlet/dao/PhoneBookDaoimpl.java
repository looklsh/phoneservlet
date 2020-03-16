package com.bit.phoneservlet.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bit.phoneservlet.vo.PhoneBookVO;

public class PhoneBookDaoimpl implements PhoneBookDao {
	private String dbuser = null;
	private String dbpass = null;
	
	public PhoneBookDaoimpl() {
		this.dbuser="bituser";
		this.dbpass="bituser";
	}
	
	public PhoneBookDaoimpl(String dbuser, String dbpass) {
		this.dbuser=dbuser;
		this.dbpass=dbpass;
	}
	
	private Connection getConnection() throws SQLException {
		Connection conn = null;

		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			
			conn = DriverManager.getConnection(dburl, dbuser, dbpass);
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		return conn;
	}

	@Override
	public List<PhoneBookVO> getList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<PhoneBookVO> list=new ArrayList<>();
		
		try {
			conn = getConnection();
			String sql = "SELECT id, name, hp, tel FROM phone_book";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);
				
				PhoneBookVO vo = new PhoneBookVO(id, name, hp, tel);
				list.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean insert(PhoneBookVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO phone_book(id, name, hp, tel) VALUES(seq_phone_book.nextval,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getTel());
			
			insertedCount = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstmt.close();
			}catch(Exception e) {
				
			}
		}
		return insertedCount == 1;
	}

	@Override
	public boolean update(PhoneBookVO vo) { //프로젝트에는 없어도되는 오버라이드
		Connection conn = null;
		PreparedStatement pstmt = null;
		int updatedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "UPDATE phone_book SET no=?, name=?, hp=?, tel=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getHp());
			pstmt.setString(4, vo.getTel());
			
			updatedCount = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstmt.close();
			}catch(Exception e) {
				
			}
		}
		return updatedCount ==1;
	}

	@Override
	public boolean delete(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		String sql = "DELETE FROM phone_book WHERE id=?";
		
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			deletedCount = pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstmt.close();
			}catch(Exception e) {
				
			}
				
			}
		
		
		return deletedCount == 1;
	}

	@Override
	public List<PhoneBookVO> searchName(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PhoneBookVO vo= null;
		
		List<PhoneBookVO> list=new ArrayList<>();
		
		try {
			conn = getConnection();
			String sql = "SELECT id, name, hp, tel FROM phone_book WHERE name Like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, '%'+ name + '%');
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long id= rs.getLong(1);
				String Name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);
				
				PhoneBookVO book = new PhoneBookVO(id, Name, hp, tel);
				list.add(book);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
				pstmt.close();
				rs.close();
			}catch(Exception e) {
				
			}
		}
		return list;
	}

}
