package logic.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import logic.controller.FindActivityController;

public class DAOCoupon {
	private static DAOCoupon INSTANCE;
	private JSONParser parser; 
	private DAOCoupon() {
		parser = new JSONParser();
	}
	
	public static DAOCoupon getInstance() {
		if(INSTANCE==null)
			INSTANCE = new DAOCoupon();
		return INSTANCE;
	}
	
	public boolean addCoupontoJSON(Coupon coupon) {
		try {
			Object coupons = parser.parse(new FileReader("WebContent/coupon.json"));
			JSONObject couponObj = (JSONObject) coupons;
			JSONArray couponArray = (JSONArray) couponObj.get("coupons");
			
			
			if (findCoupon(coupon.getCouponCode())==(null)) {				
				JSONObject newCoupon = new JSONObject();

				newCoupon.put("code", coupon.getCouponCode());
				newCoupon.put("user", coupon.getuID());
				newCoupon.put("partner", coupon.getpID());
				newCoupon.put("discount", coupon.getDiscount());
				couponArray.add(newCoupon);

				FileWriter file = new FileWriter("WebContent/coupon.json");
				file.write(couponObj.toString());
				file.flush();
				file.close();
			}
			
			
		} catch(NullPointerException e) {
			e.printStackTrace();
			return false;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public Coupon findCoupon(int code) {
		try {		
			System.out.println("valore code:"+ code);

			Object coupons = parser.parse(new FileReader("WebContent/coupon.json"));
			JSONObject couponObj = (JSONObject) coupons;
			JSONArray couponArray = (JSONArray) couponObj.get("coupons");
			JSONObject result;
			
			for(int i=0; i<couponArray.size();i++) {
				result = (JSONObject)couponArray.get(i);
				
				Long codeJSON = (Long) result.get("code");
				System.out.println("valore codeJSON:"+ codeJSON);
				
				try {
					if (codeJSON.equals(Long.valueOf(code))) {
						System.out.println("coupon trovato");
						Long user = (Long) result.get("user");
						Long partner = (Long) result.get("partner");
						Coupon coupon = new Coupon(user.intValue() , partner.intValue(), ((Long) result.get("discount")).intValue() );
						coupon.setCouponCode(((Long) result.get("code")).intValue());
						
						return coupon;
					}
				} catch (NullPointerException e) {
					e.printStackTrace();
					return null;
				} catch (ClassCastException e) {
					e.printStackTrace();
					return null;
				}
			}
				
			
		} catch(NullPointerException e) {
			e.printStackTrace();
			return null;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
	public Coupon findCoupon(int userID, int partnerID) {
		try {		

			Object coupons = parser.parse(new FileReader("WebContent/coupon.json"));
			JSONObject couponObj = (JSONObject) coupons;
			JSONArray couponArray = (JSONArray) couponObj.get("coupons");
			JSONObject result;
			
			for(int i=0; i<couponArray.size();i++) {
				result = (JSONObject)couponArray.get(i);
				
				Long userJSON = (Long) result.get("user");
				System.out.println(userJSON +" = "+ userID);

				try {
					if (userJSON.equals(Long.valueOf(userID))) {		
						System.out.println("user trovato");

						Long partnerJSON = (Long) result.get("partner");

						if(partnerJSON.equals(Long.valueOf(partnerID))) {
							System.out.println("coupon trovato");
							Long user = (Long) result.get("user");
							Long partner = (Long) result.get("partner");
							Coupon coupon = new Coupon(user.intValue() , partner.intValue(), ((Long) result.get("discount")).intValue() );
							coupon.setCouponCode(((Long) result.get("code")).intValue());
											
							return coupon;
						}
						
					}
				} catch (NullPointerException e) {
					e.printStackTrace();
					return null;
				} catch (ClassCastException e) {
					e.printStackTrace();
					return null;
				}
			}
				
			
		} catch(NullPointerException e) {
			e.printStackTrace();
			return null;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
	
}
