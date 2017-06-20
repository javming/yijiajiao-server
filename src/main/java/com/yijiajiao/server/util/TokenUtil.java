package com.yijiajiao.server.util;


import com.eeduspace.uuims.api.enumeration.SourceEnum.EquipmentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
	private static final String WEBCLIENT="E-web";
	private static final Logger  log  = LoggerFactory.getLogger(TokenUtil.class.getName());
	public static final int WEB_EXPIRE = Config.getInt("redis.webexpire");
	public static final int APP_EXPIRE = Config.getInt("redis.appexpire");

	/**
	 * 验证token
	 * @param token
	 * @param openId
	 * @return
	 */
   	public  static boolean  verifyToken(String token,String openId) {
	   	log.info("\n __[验证登录信息：token="+token+" && openId="+openId+"]");
	   	if(token==null || openId == null || "".equals(openId) || "".equals(token)){
		   	return false;
	   	}
		boolean b = false;
		String key0 = openId+0;
		String key1 = openId+1;
		boolean flag0 = RedisUtil.exist(key0);
		boolean flag1 = RedisUtil.exist(key1);
		log.info("\n __[webToken exist:"+flag0+"]\n __[appToken exist:"+flag1+"]");
		if(flag0 && flag1){
			String value0 = RedisUtil.getValue(key0);
			String value1 = RedisUtil.getValue(key1);
			if(token.equals(value0)){
				RedisUtil.expire(key0+"r", WEB_EXPIRE);
				b= true;
			}else if(token.equals(value1)){
				RedisUtil.expire(key1+"r", APP_EXPIRE);
				b= true;
			}
		}else if(flag0){
			String value = RedisUtil.getValue(key0);
			if(token.equals(value)){
				RedisUtil.expire(key0+"r", WEB_EXPIRE);
				b= true;
			}
		}else if(flag1){
			String value = RedisUtil.getValue(key1);
			if(token.equals(value)){
				RedisUtil.expire(key1+"r", APP_EXPIRE);
				b= true;
			}
		}
	   	return b;
   	}

	public static void putToken(String openId,String token,String clientId){
		if(WEBCLIENT.equals(clientId)){
			//表示web登录
			RedisUtil.putRedis(openId+"0", token, WEB_EXPIRE);
		}else{
			//表示移动端登录
			RedisUtil.putRedis(openId+"1", token, APP_EXPIRE);
		}
	}

	/**
	 * 缓存token和refresh_token
	 * @param openId
	 * @param token
	 * @param clientId  E-web/E-ios/E-android
	 * @param refreshToken
	 */
	public static void putToken(String openId,String token,String clientId,String refreshToken){
		String tokenKey;
		String refreshTokenKey;
		if (WEBCLIENT.equals(clientId)){
			tokenKey = openId+0;
			refreshTokenKey = openId+"0r";
			RedisUtil.setEx(tokenKey,WEB_EXPIRE,token);
			RedisUtil.setEx(refreshTokenKey,WEB_EXPIRE,refreshToken);
		}else {
			tokenKey = openId+1;
			refreshTokenKey = openId+"1r";
			RedisUtil.setEx(tokenKey,APP_EXPIRE,token);
			RedisUtil.setEx(refreshTokenKey,APP_EXPIRE,refreshToken);
		}

	}

	public static void putToken(String openId,String token,String clientId,String server,String url){
		Map<String,Object> params = new HashMap<>();
		params.put("openId",openId);
		params.put("token", token);
		if ("E-web".equals(clientId)) {
			params.put("expire", WEB_EXPIRE);
		} else {
			params.put("expire",APP_EXPIRE);
		}
		params.put("clientId",clientId);
		ServerUtil.httpRest(server, url+"?requestId="+ServerUtil.randomCode(), null, params, "POST");
	}

	public static EquipmentType getClientType(String a){
		EquipmentType eq = null;
		switch(a){
			case  "E-web":
				eq = EquipmentType.Web; break;
			case  "E-ios":
				eq = EquipmentType.Ios; break;
			case  "E-android":
				eq = EquipmentType.Android; break;
		}
		return eq;
	}

}
