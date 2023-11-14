package com.ddstudio.member.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.ddstudio.DBUtil;
import com.ddstudio.member.model.InquiryDTO;

public class InquiryDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	public InquiryDAO() {
		this.conn = DBUtil.open();
	}

	public ArrayList<InquiryDTO> get(String email) {
		
		try {
			
			String sql = "select inquiry_seq, type, subject, regdate, answer from vwUserInquiry where email = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, email);
			
			rs = pstat.executeQuery();	
			
			ArrayList<InquiryDTO> list = new ArrayList<InquiryDTO>();
			
			while (rs.next()) {
				
				InquiryDTO dto = new InquiryDTO();
				
				dto.setSeq(rs.getString("inquiry_seq"));
				dto.setType(rs.getString("type"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setAnswer(rs.getString("answer"));
				
				list.add(dto);
			}	
			
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	public InquiryDTO detail(String seq) {
		
		try {
			
			String sql = "select * from tblinquiry where inquiry_seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				InquiryDTO dto = new InquiryDTO();
				
				dto.setSeq(rs.getString("inquiry_seq"));
				dto.setType(rs.getString("type"));
				dto.setSubject(rs.getString("subject"));
				dto.setAnswer(rs.getString("answer"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setContent(rs.getString("content"));
				dto.setAttach(rs.getString("attach"));
				dto.setUser_seq(rs.getString("user_seq"));
				
				return dto;
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	
}
