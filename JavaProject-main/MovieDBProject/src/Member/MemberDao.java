package Member;

import java.sql.*;
import java.util.ArrayList;

public class MemberDao {
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Movie";
    String userid = "root";
    String password = "1234";

    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;

    public void insertMember(String id, String name, String pwd, String phoneNumber) {
        try {
            conn = DriverManager.getConnection(url,userid,password);
            String query = "insert into Member(id, name, pwd, phoneNumber) values( ?,?,?,?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,id);
            pstmt.setString(2, name);
            pstmt.setString(3, pwd);
            pstmt.setString(4,phoneNumber);
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

    public boolean checkId(String id) {
        boolean result = false;
        try {
            conn = DriverManager.getConnection(url,userid,password);
            String query = "select id from member where id =?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                pstmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;

    }


    public int login(String id, String pwd) {
        try {
            conn = DriverManager.getConnection(url,userid,password);
            String query = "select pwd from Member where id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getString(1).contentEquals(pwd)) {
                    return 1;
                } else {
                    return 0;
                }
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public ArrayList<MemberDto> select(){
        ArrayList<MemberDto> list = new ArrayList<MemberDto>();

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(url, userid, password);
            String query = "select * from member";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                MemberDto dto = new MemberDto();
                dto.setId(rs.getString("id"));
                dto.setName(rs.getString("name"));
                dto.setPwd(rs.getString("pwd"));
                dto.setPhoneNumber(rs.getString("phoneNumber"));
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

    public void delete(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(url, userid, password);
            String query = "delete from member where id = ? ";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
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

    //수정버튼을 수행하는 코드
    public void update(String id, String name, String pwd, String phoneNumber) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = DriverManager.getConnection(url, userid, password);
            String query = "update member set name =?, pwd = ?, phoneNumber = ? where id = ?";//이름변수는 PK이기 때문에 수정할 수 없음
            pstmt = conn.prepareStatement(query);

            pstmt.setString(1, name);
            pstmt.setString(2, pwd);
            pstmt.setString(3, phoneNumber);
            pstmt.setString(4, id);
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

    public MemberDto search(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        MemberDto dto = new MemberDto();

        try {
            conn = DriverManager.getConnection(url, userid, password);
            String query = "select * from member where id = ? ";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                dto.setId(rs.getString("id"));
                dto.setName(rs.getString("name"));
                dto.setPwd(rs.getString("pwd"));
                dto.setPhoneNumber(rs.getString("phoneNumber"));
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
