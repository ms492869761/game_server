package com.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 随机辅助类
 * 
 * @author 王志远
 * 
 */
public class RandomUtils {
	private static Random random = new Random();
	private static final int GAILY = 10000;

	public static int random(int max) {
		return random.nextInt(max);
	}

	/**
	 * 从 min 和 max 中间随机一个值
	 * 
	 * @param max
	 * @param min
	 * @return 包含min max
	 */
	public static int random(int min, int max) {
		if (max - min <= 0)
			return min;
		return min + random.nextInt(max - min + 1);
	}

	/**
	 * 根据几率 计算是否命中
	 * 
	 * @param probability
	 * @return
	 */
	public static boolean isHit(int probability, int gailv) {
		if (gailv == 0) {
			gailv = GAILY;
		}
		int random_seed = random.nextInt(gailv + 1);
		return probability >= random_seed;
	}

	/**
	 * 机率计算 万分比
	 * 
	 * @param probability
	 * @return
	 */
	public static boolean isGenerate(int probability) {
		int random_seed = random.nextInt(GAILY + 1);
		return probability >= random_seed;
	}

	/**
	 * 随机产生min到max之间的整数值 包括min max
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int randomIntValue(int min, int max) {
		return (int) (Math.random() * (double) (max - min + 1)) + min;
	}

	public static <E> E randomItem(List<? extends E> collection) {
		if (collection == null || collection.size() == 0) {
			return null;
		}
		int t = (int) (collection.size() * Math.random());
		return collection.get(t);
	}

	public static <E> List <E> randomItemList(List<E> collection, int count) {
		if (count >= collection.size()) {
			return collection;
		}
		if ((collection.size() / 2) >= count) {
			List<E> tmpEs = new ArrayList<>(collection);
			List<E> resultList = new ArrayList<E>();
			while (resultList.size() < count) {
				E e = randomItem(tmpEs);
				resultList.add(e);
				tmpEs.remove(e);
			}
			return resultList;
		} else {
			List<E> tmpEs = new ArrayList<>(collection);
			while (tmpEs.size() > count) {
				E e = randomItem(tmpEs);
				tmpEs.remove(e);
			}
			return tmpEs;
		}

	}

	public static <E> E randomItem(E[] list) {
		if (list == null || list.length <= 0) {
			return null;
		}
		int t = (int) (list.length * Math.random());
		return list[t];
	}

	/**
	 * 1000,500,10;
	 * 
	 * @param param
	 *            根据总机率返回序号(互斥分组概率)
	 * @return
	 */
	public static int randomIndexByProb(List<Integer> probs) {
		LinkedList<Integer> newprobs = new LinkedList<Integer>();
		for (int i = 0; i < probs.size(); i++) {
			if (i == 0) {
				newprobs.add(probs.get(i));
			} else {
				newprobs.add(newprobs.get(i - 1) + probs.get(i));
			}
		}
		Integer last = newprobs.getLast();
		int random = random(last);
		for (int i = 0; i < newprobs.size(); i++) {
			if (newprobs.get(i) > random) {
				return i;
			}
		}
		return -1;
	}

	public static int randomIndexByProbShort(List<Short> probs) {
		LinkedList<Short> newprobs = new LinkedList<Short>();
		for (int i = 0; i < probs.size(); i++) {
			if (i == 0) {
				newprobs.add(probs.get(i));
			} else {
				newprobs.add((short) (newprobs.get(i - 1) + probs.get(i)));
			}
		}
		short last = newprobs.getLast();
		int random = random(last);
		for (int i = 0; i < newprobs.size(); i++) {
			if (newprobs.get(i) > random) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String agrs[]) {
		List<Integer> list=new ArrayList<>();
		for (int i = 1; i <=20; i++) {
			list.add(i);
		}
		List<Integer> monster=new ArrayList<>();
		for (int i = 53; i <= 149; i++) {
			monster.add(i);
		}
		
		for (int i = 15; i <= 30; i++) {
			for (int j = 0; j < 125; j++) {
				int count=0;
				if(i>=15&&i<=19){
					count=7;
				}else if(i>=20&&i<=24){
					count=8;
				}else if(i>=25&&i<=29){
					count=9;
				}else{
					count=10;
				}
				String result="";
				List<Integer> randomItemList = randomItemList(list, count);
				List<Integer> monsters = randomItemList(monster, count);
				for (int k = 0; k < randomItemList.size(); k++) {
					result+=randomItemList.get(k)+","+monsters.get(k)+"\t";
				}
				
				
				
				
				System.out.println("等级:"+i+"\t"+"位置:["+result+"]");
			}
		}
//		Random random=new Random(1);
//		for (int i = 0; i < 1000; i++) {
//			System.out.println(random.nextInt(1000));
//		}
	}

	
	

}
