package com.champlink.system.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.champlink.common.domain.system.Code;
import com.champlink.common.domain.system.CodeType;
import com.champlink.system.dao.CodeDao;
import com.champlink.system.dao.CodeTypeDao;
@Component
public class AutogenerateCoding {

	@Autowired
	private  CodeTypeDao codeTypeDao;
	
	@Autowired
	private  CodeDao codeDao;
	
	
	/**
	 * 递增规则生成 序列号
	 */
	private static String increasingRule = "ID";
	
	/**
	 * 随机规则生成 序列号
	 */
	private static String randomStrRule = "RS";
	
	/**
	 * 随机规则生成数字
	 */
	private static String randomDigitRule = "RD";
	
	
	/*************************************/
	public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String NUMBERCHAR = "0123456789";  
	/*************************************/
	
	public  String getCoding(String code, String presentId) {
		
		CodeType codeType = codeTypeDao.getByCodeType(code);
		if(codeType == null) {
			return "code不存在！";
		}
		String codeTypeId = String.valueOf(codeType.getRowId());
		List<Code> codes = codeDao.findByTypeId(codeTypeId);
		
		String result = "";
		
		for (Code code2 : codes) {
			String codeStr = code2.getCode();
			if(codeStr.startsWith("{") && codeStr.endsWith("}")) {
				
				try {
					String ruleStr = codeStr.substring(1,codeStr.indexOf(":"));
					String digits = codeStr.substring(codeStr.indexOf(":") + 1 ,codeStr.indexOf("}"));
					
					if(ruleStr.equals(increasingRule)) {
						if(presentId == null || "".equals(presentId)) {
							return "error:数据库生成编码规则中需要递增，需要传入当前id";
						}
						int newPresentId = Integer.parseInt(presentId) + 1;
						result = String.format(result + "%0" + digits + "d", newPresentId);
					}else if(ruleStr.equals(randomStrRule)) {
						String generateString = generateString(Integer.valueOf(digits));
						result += generateString;
					}else if(ruleStr.equals(randomDigitRule)){
						String generateNumStr = generateNumStr(Integer.valueOf(digits));
						result += generateNumStr;
					}else {
						result += codeStr;
					}
				}catch(Exception e) {
					result += codeStr;
				}
				
				
			}else {
				result += codeStr;
			}
		}
		
		return result;
	}
	
	
	public String getCoding(String code) {
		return getCoding(code,null);
	}
	
	
	
	
	/** 
     * 返回一个定长的随机字符串(只包含大小写字母、数字) 
     *  
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */  
    public static String generateString(int length) {  
        StringBuffer sb = new StringBuffer();  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {  
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length()))); 
        }  
        return sb.toString();  
    } 
	
    
	/** 
     * 返回一个定长的随机数字组成的字符串 
     *  
     * @param length 
     *            随机字符串长度 
     * @return 随机字符串 
     */  
    public static String generateNumStr(int length) {  
        StringBuffer sb = new StringBuffer();  
        Random random = new Random();  
        for (int i = 0; i < length; i++) {  
            sb.append(NUMBERCHAR.charAt(random.nextInt(NUMBERCHAR.length()))); 
        }  
        return sb.toString();  
    } 
}
