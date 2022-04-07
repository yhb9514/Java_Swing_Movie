package Movie;

import Member.MemberDto;

import java.sql.*;
import java.util.ArrayList;

public class MovieDao {
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Movie";
    String userid = "root";
    String password = "1234";

    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public ArrayList<MovieDto> select() {
        ArrayList<MovieDto> list = new ArrayList<>();

        try {
            conn = DriverManager.getConnection(url, userid, password);
            String query = "select * from movie";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                MovieDto dto = new MovieDto();
                dto.setMovieNumber(rs.getInt("movieNumber"));
                dto.setMovieName(rs.getString("movieName"));
                dto.setRunningTime(rs.getInt("runningTime"));
                dto.setGenre(rs.getString("genre"));
                dto.setScreenDate(rs.getString("ScreenDate"));
                list.add(dto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                //데이터베이스 자원은 반드시 닫아 주어야 한다.
                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return list;

    }

    public void insertMovie(String movieName, int runningTime, String genre, String screenDate) {
        try {
            conn = DriverManager.getConnection(url,userid,password);
            String query = "insert into Movie(movieNumber, movieName, runningTime, genre, screenDate) values(null, ?,?,?,?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,movieName);
            pstmt.setInt(2,runningTime);
            pstmt.setString(3, genre);
            pstmt.setString(4,screenDate);
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



    public void delete(String movieName) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(url, userid, password);
            String query = "delete from Movie where movieName = ? ";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, movieName);
            int n = pstmt.executeUpdate();
            if(n==1) {System.out.println("삭제 성공");}

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }//end delete()

    //수정버튼을 수행하는 코드
    public void update(String movieName, int runningTime, String genre, String screenDate) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(url, userid, password);
            String query = "update Movie set runningTime =?, genre = ?, screenDate = ? where movieName =?";
            pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, runningTime);
            pstmt.setString(2, genre);
            pstmt.setString(3, screenDate);
            pstmt.setString(4, movieName);
            int n = pstmt.executeUpdate();
            if(n==1) {System.out.println("수정 성공");}

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
    }//end update

    public MovieDto search(String movieName) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MovieDto dto = new MovieDto();

        try {
            conn = DriverManager.getConnection(url, userid, password);
            String query = "select * from movie where movieName = ? ";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, movieName);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                dto.setMovieName(rs.getString("movieName"));
                dto.setRunningTime(rs.getInt("runningTime"));
                dto.setGenre(rs.getString("genre"));
                dto.setScreenDate(rs.getString("screenDate"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {

            try {
                if(rs != null) { rs.close();}
                if(pstmt != null) {pstmt.close();}
                if(conn != null) {conn.close();}
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return dto;

    }
}
