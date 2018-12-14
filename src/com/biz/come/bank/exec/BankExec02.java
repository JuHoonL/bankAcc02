package com.biz.come.bank.exec;

import com.biz.come.bank.service.BankService03;

public class BankExec02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String strR = "src/com/biz/come/bank/vo/bankBalance.txt";
		BankService03 bs = new BankService03(strR);
		
		bs.mainmenu();
		
		System.out.println("안녕히 가십시요");
				
	}

}
