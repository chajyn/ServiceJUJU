package com.stockmarket.www.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.stockmarket.www.dao.koreaStocksDao;
import com.stockmarket.www.entity.koreaStocks;
//koreaStocks = 코스피+ 코스닥
public class JdbckoreaStocksDao implements koreaStocksDao {

	@Override
	public koreaStocks get(String codeNum) {
		koreaStocks stock = new koreaStocks();
		
		String sql = "SELECT * FROM KOREASTOCKS WHERE stockCode=?";
		JdbcDaoContext daoContext = new JdbcDaoContext();
		PreparedStatement pst =null;
		ResultSet rs = null;
		
		try {
			pst = daoContext.getPreparedStatement(sql);
			pst.setString(1,  codeNum);
			rs = pst.executeQuery();
			if(rs.next()) { 
				stock = new koreaStocks(
						rs.getString("companyName"),
						rs.getString("stockCode"),
						rs.getString("sectors"),
						rs.getString("mainProduct"),
						rs.getString("stockedDay"),
						rs.getString("settlementMonth"),
						rs.getString("representativeName"),
						rs.getString("website"),
						rs.getString("location"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoContext.close(rs, pst);
		}
		
		return stock;
	}
	
	@Override
	public List<koreaStocks> getList() {
		
		List<koreaStocks> list = new ArrayList<>();
		
		String sql = "SELECT * FROM KOREASTOCKS";
		JdbcDaoContext daoContext = new JdbcDaoContext();
		PreparedStatement pst =null;
		ResultSet rs = null;
		
		try {
			pst = daoContext.getPreparedStatement(sql);
			rs = pst.executeQuery();

			while(rs.next()) { 
				koreaStocks korea = new koreaStocks(
						rs.getString("companyName"),
						rs.getString("stockCode"),
						rs.getString("sectors"),
						rs.getString("mainProduct"),
						rs.getString("stockedDay"),
						rs.getString("settlementMonth"),
						rs.getString("representativeName"),
						rs.getString("website"),
						rs.getString("location"));
				
				list.add(korea);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoContext.close(rs, pst);
		}
		return list;
	}

	@Override
	public int insert(List<koreaStocks> list) {
		int result = 0;
		
		String sql = "INSERT INTO KOREASTOCKS (COMPANYNAME, STOCKCODE, SECTORS, MAINPRODUCT, STOCKEDDAY, SETTLEMENTMONTH, REPRESENTATIVENAME, WEBSITE, LOCATION) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			JdbcDaoContext daoContext = new JdbcDaoContext();
			PreparedStatement statement = daoContext.getPreparedStatement(sql);
			for(int i = 0 ; i <list.size(); i++) {
				statement.setString(1, list.get(i).getCompanyName());
				statement.setString(2, list.get(i).getStockCode());
				statement.setString(3, list.get(i).getSectors());
				statement.setString(4, list.get(i).getMainProduct());
				statement.setString(5, list.get(i).getStockedDay());
				statement.setString(6, list.get(i).getSettlementMonth());
				statement.setString(7, list.get(i).getRepresentativeName());
				statement.setString(8, list.get(i).getWebsite());
				statement.setString(9, list.get(i).getLocation());
				result = statement.executeUpdate();
			}
			statement.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
		
	}

	@Override
	public int delete() {
		int result = 0;
		String sql = "DELETE FROM KOREASTOCKS";
		JdbcDaoContext context = new JdbcDaoContext();
		try {
			PreparedStatement st = context.getPreparedStatement(sql);
			result = st.executeUpdate();
			context.close(st);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public koreaStocks searchCompany(String compnayName) {
		
//		String sql = "SELECT * FROM KOREASTOCKS WHERE companyname=" + compnayName;
		String sql = "SELECT * FROM KOREASTOCKS WHERE companyname=?";
		//System.out.println("sql : " + sql);
		JdbcDaoContext daoContext = new JdbcDaoContext();
		PreparedStatement pst =null;
		ResultSet rs = null;
		
		koreaStocks korea = null;
		
		try {
			pst = daoContext.getPreparedStatement(sql);
			pst.setString(1,  compnayName);
			rs = pst.executeQuery();
			if(rs.next()) { 
				korea = new koreaStocks(
						rs.getString("companyName"),
						rs.getString("stockCode"),
						rs.getString("sectors"),
						rs.getString("mainProduct"),
						rs.getString("stockedDay"),
						rs.getString("settlementMonth"),
						rs.getString("representativeName"),
						rs.getString("website"),
						rs.getString("location"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			daoContext.close(rs, pst);
		}
		
		//System.out.println("jdbckorea :"+korea);	
		return korea;
	}
	
	

	
	

}