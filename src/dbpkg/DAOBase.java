package dbpkg;
import java.sql.*;

public class DAOBase {
	public Connection getConnection() throws SQLException {
		String driver ="oracle.jdbc.OracleDriver";
		String db_url="jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(db_url,"system","1111");
			
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void closeDBResource(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if (rs!=null) {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (pstmt!=null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (conn!=null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void closeDBResource(PreparedStatement pstmt, Connection conn) {
		if (pstmt!=null) {
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (conn!=null) {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
