package com.util;
/**
 * 
 * @author 王志远
 * 2013-3-28
 */
public class MathUtil {
	public static int min(int ...param){
		int min=Integer.MAX_VALUE;
		for (int i : param) {
			if(i<=min){
				min=i;
			}
		}
		return min;
	}

	public static int max(int... param) {
		int max = Integer.MIN_VALUE;
		for (int i : param) {
			if (i >= max) {
				max = i;
			}
		}
		return max;
	}
	
	public static void main(String args[]){
	}
}
