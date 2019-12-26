package com.stockmarket.www.service.basic;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.stockmarket.www.dao.UpjongDao;
import com.stockmarket.www.dao.koreaStocksDao;
import com.stockmarket.www.dao.csv.CSVStockDataDao;
import com.stockmarket.www.dao.jdbc.JdbcUpjongDao;
import com.stockmarket.www.dao.jdbc.JdbckoreaStocksDao;
import com.stockmarket.www.entity.Company;
import com.stockmarket.www.entity.koreaStocks;
import com.stockmarket.www.service.CompanyService;

public class BasicCompanyService implements CompanyService {
	private UpjongDao upjongDao;
	private koreaStocksDao koreaStockDao;
	private CSVStockDataDao csvStockDataDao;
	private Map<String, Integer>crawlData = new HashMap<>(); //종목명, count 수
	private Map<String, Integer>crawlDataOrder = new LinkedHashMap<>(); //종목명, count 수 내림차순
	
	// ====================================
	public BasicCompanyService() {
		upjongDao = new JdbcUpjongDao();
		koreaStockDao = new JdbckoreaStocksDao();
	}
	
	/* deprecated */
	/* private CompanyService companyService; */
	public BasicCompanyService(String csvFilePath) {

		
		/* companyService = new BasicCompanyService(csvFilePath); */
	}

	@Override
	public Company searchCompany(String search) {

		return csvStockDataDao.searchCompany(search);

	}

	public List<String> search(String search) {
		List<String> result = null;
		
		//1차 : 검색어 + "주식" && "종목" && "테마"의 네이버검색 결과를 종목명과 매칭한다
		//     자연어처리
		try {
			filterFirst(search);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//내림차순 정렬
		filterOrder();
		
		//2차 : 1차 결과의 리스트  1,2,3위의 네이버업종 종목리스트와 일치하는 항목만 최종결과값에 포함한다 
		try {
			result = filterSecond();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return result;	
	}

	private Document naverCrawling(String url) throws IOException  {
		Document doc = null;	//크롤링 결과를 담는 Document
		Response response = null; //jsoup connect 결과 반환 
		
		response = Jsoup.connect(url)
				.method(Connection.Method.GET)
				.execute();
		
		doc = response.parse();
		return doc;
	}
	
	// 1차필터
	private void filterFirst(String search) throws IOException {
		String[] keyWord = {"주식", "종목", "테마" };
		String[] removeTarget = 
			{"큐레이션", "브레이크", "디딤돌", "트레이딩", "SBS뉴스", "트레이더", "트레이",
			 "레이더", "레이튼", "플레이어", "레이시온", "오디오", "스튜디오", "키움증권 클립", 
			 "키움증권 2", "키움증권 재생중", "한국경제TV 재생", "아시아경제 https" , "아시아경제 최신",
			 "아시아경제 2", "NH투자증권 공식", "※키움증권", "레이어", "KNN뉴스", "SBSCNBC뉴스",
			 "플레이됨", "오토레이스", "레이저", "온라인 플레이", "구글플레이", "NEWS", "CBCNEWS",
			 "SBS CNBC", "SBS 스페셜", "SBS 쩐의전쟁", "SBS 뉴스", "키움증권 hts"}; //크롤링 결과의 제거 대상. 지속적인 업데이트 필요
		
		List<koreaStocks> stockList = koreaStockDao.getList();
		for(int i = 0; i < keyWord.length; i++) {
			String str = search + " " + keyWord[i];
			String url = "https://search.naver.com/search.naver?query=" + str; 
			String text = null;

			Document doc = naverCrawling(url);
			text = doc.select("#main_pack").text(); //본문 text
			for(String s : removeTarget) {			//지정된 문자를 제거한다
				text = text.replaceAll(s, "");
			}
			
//			System.out.println(text);	//for debugging
			// <종목명, count> 저장
			for(int j = 0; j < stockList.size() ; j++) {
				String stockName = stockList.get(j).getCompanyName();
				crawlData.put(
						stockName, 
						crawlData.get((Object)stockName)==null? 
							StringUtils.countMatches(text, stockName):
							StringUtils.countMatches(text, stockName) + crawlData.get((Object)stockName));
				
				if(crawlData.get((Object)stockName) == 0)
					crawlData.remove((Object) stockName);
			}
		}
	}
	
	private void filterOrder() {
		List<Entry<String, Integer>> list = new ArrayList<>(crawlData.entrySet());
        list.sort(Entry.<String, Integer>comparingByValue().reversed());

        for (Entry<String, Integer> entry : list) 
        	crawlDataOrder.put(entry.getKey(), entry.getValue());
        
//        System.out.println(crawlDataOrder);		//for debugging
	}
	
	// 2차필터
	private List<String> filterSecond() throws IOException {
		List<String> beforeCompany = new ArrayList<String>();	//중복제거전 회사목록
		List<String> afterCompany = new ArrayList<String>(); //중복제거된 회사목록
		List<Integer> limit = new ArrayList<>(crawlDataOrder.values());

		int index = 0;
		for(String k : crawlDataOrder.keySet()) {
			index++;

			//1. 회사이름으로 업종명 및 회사리스트를 가져온다
			String upjong = upjongDao.getUpjong(k);
			List<String> list = upjongDao.getStockNames(upjong);
			
			//2. crawling data 와 업종리스트가 매칭될 경우 최종 회사 리스트에 추가된다
			for(String e : crawlDataOrder.keySet()) {
				for(String m : list) {
					if(e.equals(m))
						beforeCompany.add(e);
				}
			}
			
			if(index >= 2) {	//count 수 기준으로 1, 2 등까지 적용한다
				if(limit.get(index - 1) == limit.get(index)) 
					continue;

				break;
			}
		}
		//중복된 회사이름을 제거한다
		for(String comp : beforeCompany) {
			if(!afterCompany.contains(comp))
				afterCompany.add(comp);
		}
			
//		for(String v : afterCompany)	// for debugging
//			System.out.println(v);
	
		return afterCompany;
	}

	public static void main(String[] args) {
		BasicCompanyService service = new BasicCompanyService();

//		service.search("핑크퐁");
		service.search("새벽배송");
//		service.search("키움증권");
//		service.search("지우개");
//		service.search("카카오톡");
//		service.search("카카오");
//		service.search("반도체");
//		service.search("김정은");
//		service.search("미사일");
//		service.search("키움증권");
//		service.search("브라운더스트");
//		service.search("철도");
//		service.search("기저귀");
//		service.search("4차산업");
//		service.search("손흥민");
//		service.search("트럼프");
//		service.search("비트코인");
//		service.search("네오위즈");
//		service.search("암호화폐");
//		service.search("로봇");
//		service.search("SLAM");
//		service.search("조국");
	}
}
