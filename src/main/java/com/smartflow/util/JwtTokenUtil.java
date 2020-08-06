package com.smartflow.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACVerifier;
import com.smartflow.model.User;
import net.minidev.json.JSONObject;


public class JwtTokenUtil {

	//公用秘钥-保存在服务器，客户端是不会知道秘钥的，以防被攻击
	public static String SECRET = "TokenSecret";
	
	public static Integer ExpireTime = 24;//小时

	private static List<String> validJWTTokens = new ArrayList();
	/**
	 * 生成token
	 * @return
	 * @throws Exception
	 */
	public static String createToken(User user) throws Exception{
		//签发时间
		Date iatDate = new Date();

		//过期时间-10分钟
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(Calendar.HOUR_OF_DAY, ExpireTime);
		Date exipresDate = nowTime.getTime();
		Map<String, Object> map = new HashMap<>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");
		String token = JWT.create()
				.withHeader(map)//header
				.withClaim("account", user.getAccount())//payload
				.withClaim("password", user.getPassword())
				.withClaim("userId", user.getId())
				.withExpiresAt(exipresDate)//设置过期时间-过期时间要大于签发时间
				.withIssuedAt(iatDate)//设置签发时间
				.sign(Algorithm.HMAC256(SECRET));//加密
		validJWTTokens.add(token);
		return token;
	}

	/**
	 * 解密Token并验证token是否过期
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static User verifyToken(String token) throws Exception{
		JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
		DecodedJWT jwt = null;
		try{
			jwt = verifier.verify(token);
		}catch(Exception e){
			throw new RuntimeException("登陆凭证已过期，请重新登陆");
		}
		Map<String, Claim> claims = jwt.getClaims();
		String account = claims.get("account").asString();
		String password = claims.get("password").asString();
		Integer userId = claims.get("userId").asInt();
		User user = new User();
		user.setId(userId);
		user.setAccount(account);
		user.setPassword(password);
		return user;
	}

	public String getToken() throws NoSuchAlgorithmException{
		MessageDigest md = MessageDigest.getInstance("md5");

		//		byte[] md5 = md.digest(token);
		return null;
	}

	//解析一个token

	public static Map<String,Object> valid(String token) throws ParseException, JOSEException {
		//        解析token

		JWSObject jwsObject = JWSObject.parse(token);

		//获取到载荷
		Payload payload=jwsObject.getPayload();

		//建立一个解锁密匙
		JWSVerifier jwsVerifier = new MACVerifier(SECRET);

		Map<String, Object> resultMap = new HashMap<>();
		//判断token
		if (jwsObject.verify(jwsVerifier)) {
			resultMap.put("Result", 0);
			//载荷的数据解析成json对象。
			JSONObject jsonObject = payload.toJSONObject();
			resultMap.put("data", jsonObject);

			//判断token是否过期
			if (jsonObject.containsKey("exp")) {
				Long expTime = Long.valueOf(jsonObject.get("exp").toString());
				Long nowTime = new Date().getTime();
				//判断是否过期
				if (nowTime > expTime) {
					//已经过期
					resultMap.clear();
					resultMap.put("Result", 2);

				}
			}
		}else {
			resultMap.put("Result", 1);
		}
		return resultMap;

	}
	
	/**
	 * 验证token是否过期(JWT可以自己自动验证token是否过期)
	 * @param token
	 * @return
	 */
	/*public static boolean validTokenValidityPeriod(String token){
		Date exp = JWT.decode(token).getExpiresAt();//获取token的过期时间
		Date now = new Date();//获取当前时间
		if (exp.after(now)) {//过期时间在当前时间之后(未过期)
			return false;
		}else{//已过期
			return true;
		}
	}*/

	
	/**
	   * 解密token
	   * @param token jwt类型的token
	   * @param classT 加密时的类型
	   * @param <T>
	   * @return 返回解密后的对象 - 如果token过期返回空对象
	   */
	 /* public static <T> T unsign(String token, Class<T> classT)  {
	    JWTVerifier verifier = null;
	    try {
	      verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
	      DecodedJWT jwt = verifier.verify(token); // 如果超时,直接抛出运行时异常
	      Map<String, Claim> claims = jwt.getClaims();
	      if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
	        long tokenTime = claims.get(EXP).asDate().getTime();
	        long now = new Date().getTime();
	        // 判断令牌是否已经超时
	        if (tokenTime > now) {
	          String json = claims.get(PAYLOAD).asString();
	          // 把json转回对象，返回
	          return JSON.parseObject(json, classT);
	        }
	      }
	    } catch (UnsupportedEncodingException e) {
	      e.printStackTrace();
	    } catch (TokenExpiredException e){
	      e.printStackTrace();
	    }
	    return null;
	  }*/
	
	public static void main(String[] args) throws Exception {
		User user = new User();
		user.setId(21);
		user.setUserName("admin");
		user.setPassword("123456");
		String token = JwtTokenUtil.createToken(user);
		System.out.println("Token:"+token);
		
//		Map<String, Claim> claims = JwtTokenUtil.verifyToken(token);
		User u = JwtTokenUtil.verifyToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJwYXNzd29yZCI6IjEyMzQ1NiIsInVzZXJOYW1lIjoiYWRtaW4iLCJleHAiOjE1NDI3Njg5NTEsInVzZXJJZCI6MjEsImlhdCI6MTU0Mjc2ODc3MX0.rfaIqOZxYET1E56es5iFq8iMjuZNTxNE1-R4sMmOq-c");
		System.out.println(u.toString());
//		Map<String,Object> map = valid(token);
//		System.out.println(map.get("Result"));

	}
}
