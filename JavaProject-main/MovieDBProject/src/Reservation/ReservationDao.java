package Reservation;

import Member.MemberDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class ReservationDao {
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Movie";
    String userid = "root";
    String password = "1234";

    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;


    public void insert(String id, int movieNumber, String movieName, String screenDate, String reservationDate) {
        try {
            conn = DriverManager.getConnection(url,userid,password);
            String query = "insert into reservation(reservationNumber, id, movieNumber, movieName, screenDate, reservationDate) values(null, ?,?,?,?,?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            pstmt.setInt(2, movieNumber);
            pstmt.setString(3, movieName);
            pstmt.setString(4, screenDate);
            pstmt.setString(5, reservationDate);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<ReservationDto> select(){
        ArrayList<ReservationDto> list = new ArrayList<ReservationDto>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, userid, password);
            String query = "select * from reservation";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                ReservationDto dto = new ReservationDto();
                dto.setReservationNumber(rs.getInt("reservationNumber"));
                dto.setId(rs.getString("id"));
                dto.setMovieNumber(rs.getInt("movieNumber"));
                dto.setMovieName(rs.getString("movieName"));
                dto.setScreenDate(rs.getString("screenDate"));
                dto.setReservationDate(rs.getString("reservationDate"));
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return list;
    }//end select()

    public void delete(String screenDate) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(url, userid, password);
            String query = "delete from reservation where screenDate = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, screenDate);
            int n = pstmt.executeUpdate();
            if(n==1) {System.out.println("삭제 성공");}

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//end delete()

    public ArrayList<ReservationDto> confirm(String id){
        ArrayList<ReservationDto> list = new ArrayList<ReservationDto>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, userid, password);
            String query = "select * from reservation where id=?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                ReservationDto dto = new ReservationDto();
                dto.setReservationNumber(rs.getInt("reservationNumber"));
                dto.setId(rs.getString("id"));
                dto.setMovieNumber(rs.getInt("movieNumber"));
                dto.setMovieName(rs.getString("movieName"));
                dto.setScreenDate(rs.getString("screenDate"));
                dto.setReservationDate(rs.getString("reservationDate"));
                list.add(dto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return list;
    }//end select()
}

