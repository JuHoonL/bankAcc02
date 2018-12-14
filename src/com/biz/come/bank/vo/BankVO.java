package com.biz.come.bank.vo;

public class BankVO {

	private String strId;
	private int intBalance;
	private String strLastDate;

	
	public String getStrId() {
		return strId;
	}
	public void setStrId(String strId) {
		this.strId = strId;
	}
	public int getIntBalance() {
		return intBalance;
	}
	public void setIntBalance(int intBalance) {
		this.intBalance = intBalance;
	}
	public String getStrLastDate() {
		return strLastDate;
	}
	public void setStrLastDate(String strLastDate) {
		this.strLastDate = strLastDate;
	}
	@Override
	public String toString() {
		return "계좌조회 [계좌번호 = " + strId + ", 잔액 = " + intBalance + ", 마지막 날짜 =" + strLastDate + "]";
	}
	
	
}
