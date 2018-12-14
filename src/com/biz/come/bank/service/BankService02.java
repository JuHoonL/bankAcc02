package com.biz.come.bank.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import com.biz.come.bank.vo.BankVO;

public class BankService02 {

	List<BankVO> bankList;
	String strFileName;
	String ioFolder;
	Scanner scan ;
	

	public BankService02(String strFileName) {
		bankList = new ArrayList();
		this.strFileName = strFileName; 
		this.ioFolder = "src/com/biz/come/bank/iolist/";
		scan = new Scanner(System.in);
	}
	

	public void readFile() {
		FileReader fr;
		BufferedReader br;
		
		try {
			fr = new FileReader(strFileName);
			br = new BufferedReader(fr);
			
			while(true) {
				String readF = br.readLine();
				if(readF == null) break;
				String[] splitF = readF.split(":");
				BankVO vo = new BankVO();
//				System.out.println(splitF[0] + "-" + splitF[1] + "-" + splitF[2]);
//				String formatsF = String.format("%s-%s-%s", splitF[0], splitF[1], splitF[2]);
//				System.out.println(formatsF);
				vo.setStrId(splitF[0]);
				vo.setIntBalance(Integer.valueOf(splitF[1]));
				vo.setStrLastDate(splitF[2]);
				
				bankList.add(vo);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public BankVO findId(String strId) {
		for(BankVO v : bankList) {
			if(v.getStrId().equals(strId)) {
				return v;
			}
		}
		return null;
	}
	
	
	public String bankInput01() {
		System.out.print("계좌 번호 >> ");
		String wrAcc = scan.nextLine();
		return wrAcc;
	}

	public void bankInput() {
		System.out.print("계좌 번호 >> ");
		String wrAcc = scan.nextLine();
		BankVO fvo = findId(wrAcc);
//		System.out.println(findId(wrAcc));
		if(fvo == null ) {
			System.out.println("계좌번호 없음");
			return;
		}
		int iB = fvo.getIntBalance();
		System.out.print("입금액 >> ");
		String strB = scan.nextLine();
		int intB = Integer.valueOf(strB);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date d = new Date();
		String today = sdf.format(d);
		
		int lB = iB + intB;
		fvo.setIntBalance(lB);
		fvo.setStrLastDate(today);
		
		System.out.println("입금완료");
		System.out.println("입금 후 잔액 : " + lB);
		bankIoWriter(fvo, intB);
	}	
		
		
	public void bankOutput() {
		System.out.print("계좌 번호 >> ");
		String wrAcc = scan.nextLine();
		BankVO fvo = findId(wrAcc);
//		System.out.println(findId(wrAcc));
		if(fvo == null ) {
			System.out.println("계좌번호 없음");
			return;
		}
		int oB = fvo.getIntBalance();
		System.out.print("출금액 >> ");
		String stroB = scan.nextLine();
		int intoB = Integer.valueOf(stroB);
		if(intoB > oB) {
			System.out.println("잔액부족 출금불가");
			return;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
		Date d = new Date();
		String today = sdf.format(d);
		
		int loB = oB - intoB;
		fvo.setIntBalance(loB);
		fvo.setStrLastDate(today);
		
		System.out.println("출금완료");
		System.out.println("출금 후 잔액 : " + loB);
		bankIoWriter01(fvo, intoB);
	}
		
	
	public void view() {
		System.out.print("계좌 번호 >> ");
		String wrAcc = scan.nextLine();
		BankVO fvo = findId(wrAcc);
		System.out.println(fvo);
	}
	
	
	public void bankIoWriter(BankVO v, int B) {
		
		FileWriter fw;
		PrintWriter pw;	
		String thisId = v.getStrId();
		
		try {
//			2번째 매개변수 true : 파일을 append mode로 열어라 (있으면 열고 아니면 열지마라)
			fw = new FileWriter(ioFolder+thisId,true);
			pw = new PrintWriter(fw);
			
			pw.printf("%s:%s:%d\n", v.getStrLastDate(), "입금:" + B, v.getIntBalance());

			pw.close();
			fw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
public void bankIoWriter01(BankVO v, int B) {
		
		FileWriter fw;
		PrintWriter pw;	
		String thisId = v.getStrId();
		
		try {
//			2번째 매개변수 true : 파일을 append mode로 열어라 (있으면 열고 아니면 열지마라)
			fw = new FileWriter(ioFolder+thisId,true);
			pw = new PrintWriter(fw);
			
			pw.printf("%s:%s:%d\n", v.getStrLastDate(), "출금:" + B, v.getIntBalance());

			pw.close();
			fw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void mainmenu() {
		
		readFile();
		
		while(true) {
			System.out.print("1.입금   2.출금   3.계좌조회   0.종료 >> ");
			String ioE = scan.nextLine();
			if(ioE.equals("0")) {
				break;
			}
			if(ioE.equals("1")) {
				bankInput();
				break;
			}
			if(ioE.equals("2")) {
				bankOutput();
				break;
			}
			if(ioE.equals("3")) {
				view();
				break;
			}
		}
	}
}
