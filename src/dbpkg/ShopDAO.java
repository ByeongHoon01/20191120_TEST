package dbpkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ShopDAO extends DAOBase{
	private static ShopDAO instance = new ShopDAO();
	public static ShopDAO getInstance() {
		return instance;
	}
	private ShopDAO() {
		
	}
	
	private Connection conn = null;
	private PreparedStatement pstmt =null;
	private ResultSet rs = null;
	
	private int result = 0;
	
	public int getCustno() {
		String sql = null;
		int custno = 0;
		try {
			conn = getConnection();
			sql="select NVL(max(custno),100000)+1 from member_tbl_02";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				custno = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResource(rs, pstmt, conn);
		}
		return custno;
	}
	public MemberVO getMember(int custno) {
		String sql = null;
		MemberVO mem = null;
		try {
			conn = getConnection();
			sql="select custname,phone, address," + 
					" to_char(joindate,'yyyymmdd'), grade, city " +
					" from MEMBER_TBL_02" + 
					" where custno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, custno);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mem = new MemberVO();
				mem.setCustno(custno);
				mem.setCustname(rs.getString("custname"));
				mem.setPhone(rs.getString(2));
				mem.setAddress(rs.getString(3));
				mem.setJoindate(rs.getString(4));
				mem.setGrade(rs.getString(5));
				mem.setCity(rs.getString(6));
			}
			
		} catch (Exception e) {
			
		} finally {
			closeDBResource(rs, pstmt, conn);
		}
		return mem;
	}
	public int insertMember(MemberVO vo) {
		String sql = null;
		try {
			conn = getConnection();
			sql="insert into member_TBL_02 values (?, ?, ?, ?, to_date(?,'yyyymmdd'), ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getCustno());
			pstmt.setString(2, vo.getCustname());
			pstmt.setString(3, vo.getPhone());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getJoindate());
			pstmt.setString(6, vo.getGrade());
			pstmt.setString(7, vo.getCity());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResource(pstmt, conn);
		}
		return result;
	}
	
	public ArrayList<MemberVO> listMember() {
		String sql = null;
		ArrayList<MemberVO> mems = null;
		try {
			conn = getConnection();
			sql="select custno,address,city,custname,to_char(joindate,'yyyy-mm-dd'),grade,phone from MEMBER_TBL_02";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				mems = new ArrayList<MemberVO>();
				do {
					MemberVO vo = new MemberVO();
					vo.setCustno(rs.getInt(1));
					vo.setAddress(rs.getString(2));
					vo.setCity(rs.getString(3));
					vo.setCustname(rs.getString(4));
					vo.setJoindate(rs.getString(5));
					if (rs.getString(6).equals("A")) {
						vo.setGrade("VIP");
					} else if (rs.getString(6).equals("B")){
						vo.setGrade("직원");
					} else if (rs.getString(6).equals("C")){
						vo.setGrade("일반");
					}
					
					vo.setPhone(rs.getString(7));
					mems.add(vo);
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDBResource(rs, pstmt, conn);
		}
		return mems;
	}
	
	public ArrayList<PriceVO> listPrice() {
		String sql = null;
		ArrayList<PriceVO> prices = null;
		try {
			conn = getConnection();
			sql="select a.custno, a.custname, a.grade, sum(b.price) as totalprice " + 
					" from MEMBER_TBL_02 a JOIN MONEY_TBL_02 b " + 
					" ON (a.custno = b.custno) " + 
					" group by a.custno, a.custname, a.grade " + 
					" order by totalprice desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				prices = new ArrayList<PriceVO>();
				do {
					PriceVO vo = new PriceVO();
					vo.setCustno(rs.getInt(1));
					vo.setCustname(rs.getString(2));
//					vo.setGrade(rs.getString(3));
					if (rs.getString(3).equals("A")) {
						vo.setGrade("VIP");
					} else if (rs.getString(3).equals("B")){
						vo.setGrade("일반");
					} else if (rs.getString(3).equals("C")){
						vo.setGrade("직원");
					}
					vo.setTotalprice(rs.getInt(4));
					prices.add(vo);
				}while(rs.next());
			}		
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDBResource(rs, pstmt, conn);
		}		
		return prices;
	}
	
	public int updateMember(MemberVO vo) {
		String sql = null;
		try {
			conn = getConnection();
			sql="update MEMBER_TBL_02 set "
					+ " custname=?,phone=?, address=?, joindate=to_date(?,'yyyymmdd'), grade=?, city=? "
					+ " where custno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getCustname());
			pstmt.setString(2, vo.getPhone());
			pstmt.setString(3, vo.getAddress());
			pstmt.setString(4, vo.getJoindate()); 
			pstmt.setString(5, vo.getGrade());
			pstmt.setString(6, vo.getCity());
			pstmt.setInt(7, vo.getCustno());
			result = pstmt.executeUpdate();			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			closeDBResource(pstmt, conn);
		}		
		return result;		
	}
}
