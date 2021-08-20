package logic.model;

import java.util.Random;

public class Coupon {
	private int uID;
	private int pID;
	private int couponCode;
	private int discount;
	private DAOCoupon dao;
	
	public Coupon(int userID, int partnerID,int discount) {
		this(userID,partnerID, false, discount);
	}
	
	public Coupon(int userID, int partnerID, boolean add, int discount){
		
		uID = userID;
		pID = partnerID;
		this.discount = discount;
		if (add) {
			genCode();
		}
	}
	
	public int getuID() {
		return uID;
	}

	public void setuID(int uID) {
		this.uID = uID;
	}

	public int getpID() {
		return pID;
	}

	public void setpID(int pID) {
		this.pID = pID;
	}

	public int getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(int couponCode) {
		this.couponCode = couponCode;
	}

	public void genCode() {
		dao = DAOCoupon.getInstance();
		Coupon coupon;
		Random rand = new Random();
		int code = 0;
		boolean check = true;
		coupon = dao.findCoupon(this.uID, this.pID);	
		if (coupon != null) {
			this.couponCode = coupon.couponCode;
			return;
		}
		
		while(check) {
			code = 100000 + (int) (rand.nextFloat() * 899900);
			if(dao.findCoupon(code) == null) {
				check = false;
				}
			}
		
		this.couponCode = code;
		
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
	
}
