package com.ddstudio.pb.repository;

import com.ddstudio.DBUtil;
import com.ddstudio.pb.model.PriceDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PriceDAO {
    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;

    public PriceDAO() {

        this.conn = DBUtil.open();

    }


    public int add(PriceDTO dto) { // 티켓 테이블 추가하기

        //DTO > insert

        try {

            String sql = "insert into TBLTICKET (ticket_seq, ticket_type, person_type, age, price) values (SEQTBLTICKET.nextval, ?, '개인', ?, ?)";

            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getTicketType());
            pstat.setString(2, dto.getAge());
            pstat.setInt(3, dto.getPrice());

            return pstat.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;

    }
    public ArrayList<PriceDTO> list() {  // 티켓 테이블 리스트

        ArrayList<PriceDTO> list = new ArrayList<>();


        try {

            String sql = "select * from TBLTICKET order by TICKET_SEQ desc";

            stat = conn.createStatement();

            rs = stat.executeQuery(sql);

            //rs == 메모 목록

            //rs를  list로 옮기기
            while (rs.next()) {

                //레코드 1줄 > MemoDTO 1개
                PriceDTO dto = new PriceDTO();
                dto.setTicketSeq(rs.getString("ticket_seq"));
                dto.setTicketType(rs.getString("ticket_type"));
                dto.setPersonType(rs.getString("person_type"));
                dto.setAge(rs.getString("age"));
                dto.setPrice(rs.getInt("price"));


                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

    public ArrayList<PriceDTO> personTypeList() {  //티켓테이블 개인/단체 에서 개인꺼만 가져오기

        ArrayList<PriceDTO> list = new ArrayList<>();


        try {

            String sql = "select * from TBLTICKET where PERSON_TYPE ='개인'";

            stat = conn.createStatement();

            rs = stat.executeQuery(sql);

            //rs == 메모 목록

            //rs를  list로 옮기기
            while (rs.next()) {

                //레코드 1줄 > MemoDTO 1개
                PriceDTO dto = new PriceDTO();
                dto.setTicketSeq(rs.getString("ticket_seq"));
                dto.setTicketType(rs.getString("ticket_type"));
                dto.setPersonType(rs.getString("person_type"));
                dto.setAge(rs.getString("age"));
                dto.setPrice(rs.getInt("price"));


                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }


    public ArrayList<PriceDTO> ticketTypeList() {

        ArrayList<PriceDTO> list = new ArrayList<>();


        try {

            String sql = "select distinct TICKET_TYPE from TBLTICKET where PERSON_TYPE = '개인'";  // 종일권인지 , 4시이후권인지

            stat = conn.createStatement();

            rs = stat.executeQuery(sql);

            //rs == 메모 목록

            //rs를  list로 옮기기
            while (rs.next()) {

                //레코드 1줄 > MemoDTO 1개
                PriceDTO dto = new PriceDTO();
                dto.setTicketType(rs.getString("ticket_type"));


                list.add(dto);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;

    }

}
