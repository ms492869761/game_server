package com.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class BeanTool {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BeanTool.class);
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map listToMap(List list,String properties,Class mapclass){
		try {
			Map hashmap = (Map)mapclass.newInstance();
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				Object item = iter.next();
				try {
					Object key = getPropValue(item, properties);
					hashmap.put(key, item);
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}
			}
			return hashmap;
		} catch (InstantiationException e) {			
			LoggerHelper.dealExceptionError(logger, e);
		} catch (IllegalAccessException e) {
			LoggerHelper.dealExceptionError(logger, e);
		}
		return null;
	}
	@SuppressWarnings("rawtypes")
	public static Map listToMap(List list, String keyPropertiesName) {
		return listToMap(list,keyPropertiesName,HashMap.class);
	}
	
	public static <T> ArrayList<T> mapToList(HashMap<String, T> map){
		ArrayList<T> list = new ArrayList<T>(map.values());
		return list;
	}
	/**
	 * 通过反射调用方法包括私有方法
	 * @param obj
	 * @param methodName
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	public static Object invokeMethod(Object obj,String methodName) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Method method = obj.getClass().getMethod(methodName);
		boolean before=method.isAccessible();
		method.setAccessible(true);
		Object invoke = method.invoke(obj);
		method.setAccessible(before);
		return invoke;
	}
	
	/**
	 * 获取BEAN中的属性值 包括私有属性 包括静态属性
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static Object getPropValue(Object Bean,String fieldName) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		Class<? extends Object> cls= Bean.getClass();
		Field declaredField = cls.getDeclaredField(fieldName);
		boolean before=declaredField.isAccessible();
		declaredField.setAccessible(true);
		Object object = declaredField.get(Bean);
		declaredField.setAccessible(before);
		return object;
	}
	
	/**
	 * 转换为二进制数组
	 * @param obj
	 * @return
	 */
	public static byte[] convertToByteArray(Serializable obj) {
		try {
			ObjectOutputStream os = null;
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream(5000);
			os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
			os.flush();
			os.writeObject(obj);
			os.flush();
			byte[] sendBuf = byteStream.toByteArray();
			os.close();
			return sendBuf;
		} catch (IOException e) {
			LoggerHelper.dealExceptionError(logger, e);
			logger.error(e.getMessage(),e);
		}
		return null;
	}	
	
	/**
	 * 将二进制数组转换为对象
	 * @param <T>
	 * @param bytes
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T convertToObject(byte[] bytes,Class<T> cls){
		try {
			ByteArrayInputStream bytestream=new ByteArrayInputStream(bytes);
			ObjectInputStream in=new ObjectInputStream(bytestream);
			return (T) in.readObject();
		} catch (IOException e) {
			LoggerHelper.dealExceptionError(logger, e);
		} catch (ClassNotFoundException e) {
			LoggerHelper.dealExceptionError(logger, e);
		}
		return null;
	}
	
	
	public static void main(String agrs[]){
	}
	/**
	 * 将source的值复制到target
	 * @param target
	 * @param source
	 */
	public static void copyProperties(Object source,Object target){
		org.springframework.beans.BeanUtils.copyProperties(source, target);	
	}
	/**
	 * 获取调用栈方法名
	 * @return
	 */
	public static String getMethodName(){
		StackTraceElement st[]= Thread.currentThread().getStackTrace();
		StackTraceElement stackTraceElement = st[2];
		return stackTraceElement.getMethodName();
	}
	
	/**
	 * 获取调用栈
	 * @return
	 */
	public static String getStack(){
		StackTraceElement st[]= Thread.currentThread().getStackTrace();
		String s="";
		for (StackTraceElement stackTraceElement : st) {
			s+=stackTraceElement.getClassName()+"."+stackTraceElement.getMethodName()+" "+stackTraceElement.getLineNumber()+"\r\n";
		}
		return s;
	}
	
	
	/**
	 * 获取类实例的属性值
	 * 
	 * @param clazz
	 *            类名
	 * @param includeParentClass
	 *            是否包括父类的属性值
	 * @return 类名.属性名=属性类型
	 */
	public static List<Field> getClassFields(Class<?> clazz,
			boolean includeParentClass) {
		List<Field> map = new ArrayList<>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			map.add(field);
		}
		if (includeParentClass)
			getParentClassFields(map, clazz.getSuperclass());
		return map;
	}

	/**
	 * 获取类实例的父类的属性值
	 * 
	 * @param map  类实例的属性值Map
	 * @param clazz	类名
	 * @return 
	 */
	private static List<Field> getParentClassFields(
			List<Field> map, Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			map.add(field);
		}
		if (clazz.getSuperclass() == null) {
			return map;
		}
		getParentClassFields(map, clazz.getSuperclass());
		return map;
	}

	/**
	 * 获取类实例的方法
	 * 
	 * @param clazz
	 *            类名
	 * @param includeParentClass
	 *            是否包括父类的方法
	 * @return List
	 */
	public static List<Method> getMothds(Class<?> clazz, boolean includeParentClass) {
		List<Method> list = new ArrayList<Method>();
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			list.add(method);
		}
		if (includeParentClass) {
			getParentClassMothds(list, clazz.getSuperclass());
		}
		return list;
	}

	/**
	 * 获取类实例的父类的方法
	 * 
	 * @param list
	 *            类实例的方法List
	 * @param clazz
	 *            类名
	 * @return List
	 */
	private static List<Method> getParentClassMothds(List<Method> list,
			Class<?> clazz) {
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			list.add(method);
		}
		if (clazz.getSuperclass() == Object.class) {
			return list;
		}
		getParentClassMothds(list, clazz.getSuperclass());
		return list;
	}

}
