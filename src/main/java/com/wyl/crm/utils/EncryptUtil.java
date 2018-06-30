package com.wyl.crm.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * 这个工具类就是用来对密码进行加密处理
 * 
 * @author zhangzhenlin 
 * Encrypt : 将...译成密码 
 * algorithm : 算法 
 * Iterations : 迭代次数
 */
public class EncryptUtil {
	public static final String SYSTEM_PASSWORD_SALT = "wangyilin";

	public static String encryptPassword(String password) {
		String algorithmName = "MD5";// 加密的方式
		Object source = password;// 加密的数据
		Object salt = ByteSource.Util.bytes(SYSTEM_PASSWORD_SALT);// 盐值
		int hashIterations = 1000;// 加密次数
		// 然后就是对这个密码进行加密加盐的处理
		SimpleHash hash = new SimpleHash(algorithmName, source, salt, hashIterations);
		return hash.toString();
	}
}
