package com.stockmarket.www.dao;

import java.util.List;

import com.stockmarket.www.entity.InterestStocks;

public interface InterestStocksDao {
	
	List<InterestStocks> getInterestStockList();
	
	int delete(int id, String delStockName);
	int insert(int id, String email,String stockName);
	
}
