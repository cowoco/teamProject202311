package com.java.www.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.java.www.dto.Stu_memberDto;

public class Stu_memberDao {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	Stu_memberDto smdto =null;
	String query,id,pw,name,gender,phone,email,region, p_num;
	Timestamp sdate;
	int result;
	
	// ★커넥션풀에서 Connection객체 가져오기
	public Connection getConnection() {
		Connection connection = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:comp/env/jdbc/Oracle18");
			connection = ds.getConnection();
		} catch (Exception e) {e.printStackTrace();}
		return connection;
	}// getConnection
	
	
	//1.로그인 하기
	public Stu_memberDto DoLogin(String id2, String pw2) {
		try {
			conn = getConnection();
			query="select * from stu_mem where id=? and pw=?";
			pstmt = conn.prepareStatement(query);
			//1,2
			pstmt.setString(1, id2);
			pstmt.setString(2, pw2);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				id = rs.getString("id");
				pw =rs.getString("pw");
				name =rs.getString("name");
				gender = rs.getString("gender");
				p_num = rs.getString("p_num");
				phone = rs.getString("phone");
				email =rs.getString("email");
				region =rs.getString("region");
				sdate = rs.getTimestamp("sdate");
				smdto = new Stu_memberDto(id, pw, name, gender, p_num, phone, email, region, sdate);
			}
		}catch (Exception e) {e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {e2.printStackTrace();}
		}//finally
		return smdto;
	}//DoLogin

	//2.회원가입 하기
	public int doMSignUp(Stu_memberDto smdto2) {
		try{
			conn = getConnection();
			query="insert into stu_mem values(?,?,?,?,?,?,?,?,sysdate)";
			pstmt = conn.prepareStatement(query);
			//1,2
			pstmt.setString(1, smdto2.getId());
			pstmt.setString(2, smdto2.getPw());
			pstmt.setString(3, smdto2.getName());
			pstmt.setString(4, smdto2.getP_num());
			pstmt.setString(5, smdto2.getPhone());
			pstmt.setString(6, smdto2.getEmail());
			pstmt.setString(7, smdto2.getGender());
			pstmt.setString(8, smdto2.getRegion());
			result =pstmt.executeUpdate();
		}catch (Exception e) {e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {e2.printStackTrace();}
		}//finally
		return result;
	}

	//3.아이디 중복체크
	public Stu_memberDto Idcheck(String id2) {
		try {
			conn =getConnection();
			query="select * from stu_mem where id=?";
			pstmt = conn.prepareStatement(query);
			//1,2
			pstmt.setString(1, id2);
			rs = pstmt.executeQuery();
			
			
		}catch (Exception e) {e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e2) {e2.printStackTrace();}
		}//finally
		return smdto;
	}
	
	
	
}//Stu_memberDao()
