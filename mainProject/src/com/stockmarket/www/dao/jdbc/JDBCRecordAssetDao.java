package com.stockmarket.www.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.stockmarket.www.dao.RecoedAssetDao;
import com.stockmarket.www.entity.RecordAsset;

public class JDBCRecordAssetDao implements RecoedAssetDao {

	@Override
	public List<RecordAsset> getList(int id) {
		List<RecordAsset> recordList = new ArrayList<>();

		String sql = "SELECT * FROM RECORD_ASSET WHERE MEMBER_ID = ?";

		try {
			PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				int memberId = rs.getInt("MEMBER_ID");
				String regdate = rs.getString("REGDATE");
				int value = rs.getInt("VALUE");

				RecordAsset recordAsset = new RecordAsset(memberId, regdate, value);
				recordList.add(recordAsset);
			}
			rs.close();
			st.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return recordList;
	}

	@Override
	public int insert(RecordAsset recordAsset) {
		int result = 0;

		String sql = "INSERT INTO RECORD_ASSET(MEMBER_ID,REGDATE,VALUE) VALUES(?,?,?)";

		try {
			PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
			st.setInt(1, recordAsset.getMemberId());
			st.setString(2, recordAsset.getRegdate());
			st.setInt(3, recordAsset.getValue());

			result = st.executeUpdate();

			st.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int delete(int memberId) {
		int result = 0;

		String sql = "DELETE RECORD_ASSET WHERE MEMBER_ID = ? ";

		try {
			PreparedStatement st = JdbcDaoContext.getPreparedStatement(sql);
			st.setInt(1, memberId);

			result = st.executeUpdate();

			st.close();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
