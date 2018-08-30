package com.zzy.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.codec.binary.BinaryCodec;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**兼容性  commons-codec-1.10.jar 其他版本不兼容 */
public class JwtToken {
    /**公共秘钥*/
    public static String SECRET = "zzy";

    /**
     * 获取Token
     * @return
     */
    public static String createToken(){
        return createToken("没有用户名字");
    }

    public static String createToken(String userName){
        Date idtDate = new Date();//签发时间
        //过期时间
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE,1);//1 分钟过期
        Date expiresDate = nowTime.getTime();

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("alg","HS256");
        map.put("typ","JWT");
        String token = "";
        try {
            token = JWT.create().withHeader(map)
                    .withClaim("name","张小凡")
                    .withClaim("userName",userName)
                    .withClaim("org","zzy")
                    .withExpiresAt(expiresDate)
                    .withExpiresAt(idtDate)
                    .sign(Algorithm.HMAC256(SECRET));//加密
        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    public static Map<String,Claim> verifyToken(String token){
        DecodedJWT decodedJWT = null;
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            decodedJWT = jwtVerifier.verify(token);

        }catch (Exception e){
            e.printStackTrace();
        }
        if(decodedJWT!=null){
            return  decodedJWT.getClaims();
        }else{
            return  null;
        }
    }

    public static void main(String a[]){
       /* Object ss = BinaryCodec.class.getProtectionDomain().getCodeSource().getLocation();
        System.out.println(ss);

        String token = JwtToken.createToken();
        System.out.println(token);*/
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJvcmciOiJ6enkiLCJuYW1lIjoi5byg5bCP5YehIiwiZXhwIjoxNTM0OTIzMjU5LCJhZ2UiOiIyOCJ9.eCvyV_iWtffg-IKnmZAiIjxjj2j7GZ1IGg-NptsEGJg";
        Map<String,Claim>  map = JwtToken.verifyToken(token);
        System.out.println(map);//如果token失效，map为null
       String userName = map.get("userName").asString();
        System.out.println(userName);
    }
}
