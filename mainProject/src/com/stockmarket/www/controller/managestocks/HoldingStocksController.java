package com.stockmarket.www.controller.managestocks;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.stockmarket.www.entity.HaveStockView;
import com.stockmarket.www.service.HoldingStocksService;
import com.stockmarket.www.service.basic.BasicHoldingStocksService;



@WebServlet("/card/managestocks/holdinglist")
public class HoldingStocksController extends HttpServlet{

   
	private HoldingStocksService HoldingStocksInterface;
	
	public HoldingStocksController() {
		HoldingStocksInterface = new BasicHoldingStocksService();
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
	}
		
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        
		HttpSession session = request.getSession();
		
		boolean firstSetting = true;
        int userId = (int)session.getAttribute("id");
        
	    String codeNum = request.getParameter("codeNum");
	    if(codeNum != null || firstSetting ) {
	    	updateCurrentPrice(request,response,userId);
	    	firstSetting = false;
	    	return;
	    }
		

//		for(HaveStockView data : list) {
//			System.out.println(data.getStockId());
//		}

//        System.out.println(json);

//		request.setAttribute("list", HoldingStocksInterface.getInterestHoldingList(userId));
		request.getRequestDispatcher("holdinglist.jsp").forward(request, response);
	}
	
	
	private void updateCurrentPrice(HttpServletRequest request,HttpServletResponse response , int userId) throws IOException {
		
		List<HaveStockView> list = HoldingStocksInterface.getInterestHoldingList(userId);

        Gson gson = new Gson();
		String json = gson.toJson(list);
		
        PrintWriter out = response.getWriter();
		out.write(json);
		
	}
	
}


