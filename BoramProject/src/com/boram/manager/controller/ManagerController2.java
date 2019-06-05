package com.boram.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.boram.manager.vo.Order;
import com.boram.manager.vo.OrderDao;
import com.boram.manager.vo.Product;
import com.boram.manager.vo.ProductDao;
import com.boram.member.vo.Member;
import com.boram.member.vo.MemberDao;
import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

public class ManagerController2 {

	private OrderDao od = new OrderDao();
	private ProductDao pd = new ProductDao();
	private MemberDao md = new MemberDao();

	private ArrayList<Order> oArr = od.fileRead();
	private List<Product> pArr = (ArrayList<Product>)pd.fileRead();
	private ArrayList<Member> mArr = md.fileRead();

	public ArrayList<Member> searchMember() {
		oArr.clear();
		oArr = od.fileRead();
		pArr.clear();
		pArr = pd.fileRead();
		mArr.clear();
		mArr = md.fileRead();
		return mArr;
	}

	public List<Product> searchProduct() {
		oArr.clear();
		oArr = od.fileRead();
		pArr.clear();
		pArr = pd.fileRead();
		mArr.clear();
		mArr = md.fileRead();
		return pArr;
	}
	public ArrayList<Order> searchOrder(){
		oArr.clear();
		oArr = od.fileRead();
		pArr.clear();
		pArr = pd.fileRead();
		mArr.clear();
		mArr = md.fileRead();
		return oArr;
	}
	
	/**
	 * 사용 메소드 manageProduct, analyzeSale
	 * 용도 프로덕트 번호값을 입력받고 그 인덱스의 값을 반환한다.
	 */
	public int indexProduct(int pNo) {
		oArr.clear();
		oArr = od.fileRead();
		pArr.clear();
		pArr = pd.fileRead();
		mArr.clear();
		mArr = md.fileRead();
		int b=-1;
		for (int i = 0; i < pArr.size(); i++) {
			if (pArr.get(i).getpNo() == pNo) {
				b = i;
				break;
			}
		}
		return b;
	}
	
	public int indexMember(int mNo) {
		oArr.clear();
		oArr = od.fileRead();
		pArr.clear();
		pArr = pd.fileRead();
		mArr.clear();
		mArr = md.fileRead();
		int temp = -1;
		for (int i = 0; i < mArr.size(); i++) {
			if (mArr.get(i).getmNo() == (int) mNo) {
				temp = i;
				break;
			}
		}
		return temp;
	}
	
//	oArr.clear();
//	oArr = od.fileRead();
//	pArr.clear();
//	pArr = pd.fileRead();
//	mArr.clear();
//	mArr = md.fileRead();
	
	public String orderInfo(Order o) {
		String message = "";
		message += ("주문번호 : " + o.getOrderNo()) + "\n 주문상품 \n";// messaage에 주문번호담기
		for (int j = 0; j < o.getpNo().size(); j++) {

			for (int l = 0; l < pArr.size(); l++) {
				if (pArr.get(l).getpNo() == o.getpNo().get(j)) {
					message += pArr.get(l).getProductName() + " : "
							+ o.getAmount().get(j) + "개\n";//message에 상품명 + 상품갯수 담기
				}
			}
		}
		message += "주문가격 : " + o.getPayment() + "\n"; //message에 주문가격담기
		if (o.getState() == 0) { // 메세지에 배송상태담기
			message += "배송상태 : 결재완료";
		} else if (o.getState() == 1) {
			message += "배송상태 : 배송 중";
		} else {
			message += "배송상태 : 배송종료";
		}
		return message;
	}
	
	
//	public void insertProduct(int category, String productName, int price, String size, String explain, int stock) {
//
//		int pNo = 1;
//		try {
//			if (pArr != null) {
//				pNo = pArr.get(pArr.size() - 1).getpNo() + 1;
//			}
//		} catch (ArrayIndexOutOfBoundsException e) {
//			pNo = 1;
//		}
//
//		pArr.add(new Product(pNo, category, productName, price, size, stock, 0));
//	}


//	public void updateProduct(int index, int menu, String update) {
//
//		int result = Integer.parseInt(update);
//
//		switch (menu) {
//		case 1:
//
//			pArr.get(index).setCategory(result);
//			break;
//		case 2:
//			pArr.get(index).setProductName(update);
//			break;
//		case 3:
//			pArr.get(index).setPrice(result);
//			break;
//		case 4:
//			pArr.get(index).setSize(update);
//			break;
//		case 5:
//			pArr.get(index).setExplain(update);
//			break;
//
//		default:
//			break;
//		}
//
//	}

	public void deleteProduct(int result) {
		oArr.clear();
		oArr = od.fileRead();
		pArr.clear();
		pArr = pd.fileRead();
		mArr.clear();
		mArr = md.fileRead();
		
		pArr.remove(result);
		pd.fileSave(pArr);
	}

//	public void deleteMember(int result) {
//		mArr.remove(result);
//	}

//	public void updateProduct(int result, int stock) {
//		pArr.get(result).setStock(stock);
//	}

	
	/**
	 * 주문목록으로부터 키값에 상품번호, value값에 판매율을 넣어줄 HashMap
	 */
	public HashMap<Integer, Double> analysis() {
		oArr.clear();
		oArr = od.fileRead();
		pArr.clear();
		pArr = pd.fileRead();
		mArr.clear();
		mArr = md.fileRead();
		
		HashMap<Integer, Double> anl = new HashMap<Integer, Double>(); //반환해줄 값
		//anl.clear();
		ArrayList<Integer> pNo = new ArrayList<Integer>(); //상품번호를 받아줄 상품번호
		ArrayList<Integer> count = new ArrayList<Integer>();//상품별로 구매갯수를 저장할 int형 배열
		for (int i = 0; i < oArr.size(); i++) {
			for (int j = 0; j < oArr.get(i).getpNo().size(); j++) {
				pNo.add(oArr.get(i).getpNo().get(j));//pNo에 모든 orderList의 상품번호들담기
				count.add(oArr.get(i).getAmount().get(j));//count에 각각의 상품번호들 담기
				int index = 0;
				for (int j2 = 0; j2 < pNo.size()-1; j2++) {// 마지막에 입력한거 전까지 반복
					if(oArr.get(i).getpNo().get(j)== pNo.get(j2)) {//마지막값과 마지막 전값까지 비교
						pNo.remove(pNo.size()-1);//겹치는 게 있을시 마지막에 입력한 값제거
						int temp = count.get(j2);
						count.set(j2, temp+oArr.get(i).getAmount().get(j));
						count.remove(pNo.size()-1);
						break;
					}
				}
			}
		}
			ArrayList<Integer> same = new ArrayList<Integer>();//Orderlist의 상품번호가 같은 Product를 찾아 그 인덱스값들을 저장하는 배열
			for (int k = 0; k < pNo.size(); k++) { // pNo의 모든값 반복
				for (int j = 0; j < pArr.size(); j++) {//그상품번호의 조회수를 찾기위해 상품리스트를 모두 검색
					if (pNo.get(k) == pArr.get(j).getpNo()) {
							same.add(j);//그 인덱스값 저장
							break;
					}
				}
				try {
					anl.put(pNo.get(k), (count.get(k) / (double)(pArr.get(same.get(k)).getCount())));//해쉬맵에 저장
				} catch (ArithmeticException e) {//조회수가 0이면 0값반환
					anl.put(pNo.get(k), 0.0);
				}
			}
		return anl;
	}

//	oArr.clear();
//	oArr = od.fileRead();
//	pArr.clear();
//	pArr = pd.fileRead();
//	mArr.clear();
//	mArr = md.fileRead();
	
	public ArrayList<Integer> salesState(int month, int term) {
		oArr.clear();
		oArr = od.fileRead();
		pArr.clear();
		pArr = pd.fileRead();
		mArr.clear();
		mArr = md.fileRead();
		
		ArrayList<Integer> sumArr = new ArrayList<Integer>();//월별합산액을 저장할 arraylist
		int count;
		int a = (month / 100) % 100;//몇월인지를 나타내는 변수
		// month의 형태 'yyyyMMdd'
		for (int i = 0; i < term; i++) {//분석할 개월수만큼 반복
			count = 0;
			if (a == 0) {
				for (int j = 0; j < oArr.size(); j++) {
					if ((month / 100 - i - 100+12) == (oArr.get(j).getOrderDate() / 100)) {
						//작년으로 돌아가는 경우 연도에서도 1이 빠지고 월은 12월이 되게 만들어야함 ex)201900-> 201812;
						count += oArr.get(j).getPayment();//연도와월이 같은 경우 값을 더해줌
						a=12;
					}
				}
				sumArr.add(count);
			} else {
				for (int j = 0; j < oArr.size(); j++) {
					if ((month / 100 - i) == (oArr.get(j).getOrderDate() / 100)) {//100으로 나눠 1을 없애고 연도와 월만남김
						count += oArr.get(j).getPayment();//연도와월이 같은 경우 값을 더해줌
					}
				}
				a--;//한달씩 줄여가며 sumArr에 저장하기 위한값
				sumArr.add(count);
			}
		}
		return sumArr;
	}

}
