package naverTrend;

import java.util.Scanner;

public class InputData {
	private String startDate;
	private String endDate;
	private String timeUnit;
	private String groupName[];
	private String keyWords[][];
	private int cnt, keyCnt;
	
	private static String START_DATE = "2017-01-01";
	private static String END_DATE = "2019-01-01";
	private static String TIMEUNIT = "month";
	private static String GROUP_NAME = "한글";
	private static String KEYWORDS = "korean";
	
	public InputData() {
		groupName = new String[5];
		keyWords = new String[5][20];
	}
	
	public InputData init(boolean mode) {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("┌───────────┐");
		System.out.print("Input START_DATE(20xx-0x-0x) : ");
		if(mode)
			startDate = START_DATE;
		else
			startDate = sc.next();
		
		System.out.print("Input END_DATE(20xx-0x-0x) : ");
		if(mode)
			endDate = END_DATE;
		else
			endDate = sc.next();
		
		System.out.print("Input TIMEUNIT(date, week, month) : ");
		if(mode)
			timeUnit = TIMEUNIT;
		else
			timeUnit = sc.next();
		
		System.out.println("Input Subject Number : ");
		cnt = sc.nextInt();
		
		for(int i = 0; i < cnt; i++) {
			System.out.print("Input GROUP_NAME : ");
			groupName[i] = sc.next();
		
			System.out.println("Input Subject KeyWord Num : ");
			keyCnt = sc.nextInt();
			for(int j = 0; j < keyCnt; j++) {
				System.out.print("Input KEYWORDS : ");
				keyWords[i][j] = sc.next();
			}
		}
		return this;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getKeyCnt() {
		return keyCnt;
	}

	public void setKeyCnt(int keyCnt) {
		this.keyCnt = keyCnt;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getTimeUnit() {
		return timeUnit;
	}

	public void setTimeUnit(String timeUnit) {
		this.timeUnit = timeUnit;
	}

	public String getGroupName(int i) {
		return groupName[i];
	}

	public void setGroupName(String[] groupName) {
		this.groupName = groupName;
	}

	public int getKeyWordsCnt(int i) {
		System.out.println("--------------");
		System.out.println(i + " " + keyWords[i].length);

		
		return keyWords[i].length;
	}

	public String[] getKeyWords(int i) {
		return keyWords[i];
	}

	public void setKeyWords(String[][] keyWords) {
		this.keyWords = keyWords;
	}
	
	
}
