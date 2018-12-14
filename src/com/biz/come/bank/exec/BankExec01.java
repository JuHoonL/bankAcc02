package com.biz.come.bank.exec;

import java.util.Scanner;

import com.biz.come.bank.service.BankService;

public class BankExec01 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String strR = "src/com/biz/come/bank/vo/bankBalance.txt";
		BankService bs = new BankService(strR);
		
		bs.mainmenu();
		
		System.out.println("안녕히 가십시요");
				
	}

}
