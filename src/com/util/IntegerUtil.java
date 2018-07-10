package com.util;

import java.util.List;




/**
 * 
 * @author 王志远
 * 2013-5-3
 */
public class IntegerUtil {
	/**
	 * 是否为空值和0
	 * @param num
	 * @return
	 */
	public static boolean isEmptORZero(Number num){
		return isNull(num)||num.equals(0);
	}
	/**
	 * num是否为空
	 * @param num
	 * @return
	 */
	public static boolean isNull(Number num){
		return num==null;
	}
	
	/**
	 * 是否包含空值
	 * @param num
	 * @return
	 */
	public static boolean isHasNull(Number ...num){
		for (Number integer : num) {
			if(isNull(integer)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 是否为0
	 * @param num
	 * @return
	 */
	public static boolean isZero(Number num){
		return num!=null&&num.equals(0);
	}
	/**
	 * 不为空且大于0
	 * @param num
	 * @return
	 */
	public static boolean notNullAndBigThan0(Number num){
		return num!=null&&Long.parseLong(num.toString())>0;
	}
	
	/**
	 * 是否介于两值之间(包含min和max)
	 * 
	 * @param value
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean isBetween(Number value, Number min, Number max) {
		return value!=null&&Long.parseLong(value.toString()) >= Long.parseLong(min.toString()) && Long.parseLong(value.toString()) <= Long.parseLong(max.toString());
	}
	
	/**
	 * 获得数字列表中的最大值
	 * @param list
	 * @return
	 */
	public static Number getMaxInList(List<Number> list) {
		Number value=null;
		for(Number number:list) {
			if(value==null||value.longValue()<number.longValue()) {
				value=number;
			}
		}
		return value;
	}
	
	/**
	 * 获得数字列表中的最小值
	 * @param list
	 * @return
	 */
	public static Number getMinInList(List<Number> list) {
		Number value=null;
		for(Number number:list) {
			if(value==null||value.longValue()>number.longValue()) {
				value=number;
			}
		}
		return value;
	}
	
	
	
}
