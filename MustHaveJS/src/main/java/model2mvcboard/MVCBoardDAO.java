package model2mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool {
	
	public MVCBoardDAO() {
		super();
		
	}
	
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		
		String query = "SELECT COUNT(*) FROM mvcboard";
		
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searField") + " "
					+ " LIKE '%" +map.get("searchWord") + "%'";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount=rs.getInt(1);
		}
		catch (Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		return totalCount;
	}
}
	
/*
 * public List<MVCBoardDTO> selectListPage(Map<String, Object> map) {
 * List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
 * 
 * String query = " " + "SELECT * FROM ( " +
 * "	SELECT tb.*, ROWNUM rNum FROM ( " + "		SELECT * FROM mvcboard ";
 * 
 * if (map.get("searchWord") != null ) { query += " WHERE "
 * +map.get("searchField") + " LIKE '%" + map.get("searchWord") + "%' "; } query
 * += " 	ORDER BY idx DESC "
 * 
 * 
 * } }
 */
