package com.thalasoft.toolbox.utils;

import static io.jsonwebtoken.SignatureAlgorithm.HS256;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.sql.Date;

// See https://github.com/jwtk/jjwt
// See https://www.mobomo.com/2012/06/signed-idempotent-action-links/
// See https://github.com/jwtk/jjwt/issues/12

// Signed Idempotent Action Links

public class SIAL {

	private static final String ACTION = "action";
	
	public static String signAction(String action, Long entityId, String privateKey, Long lifespan) {
		Date expirationDate = new Date(System.currentTimeMillis() + lifespan);
		Claims claims = Jwts.claims().setExpiration(expirationDate);
		claims.setSubject(String.valueOf(entityId));
		claims.put(ACTION, action);
		return Jwts.builder().signWith(HS256, privateKey).setClaims(claims).compact();		
	}

	public static boolean authenticate(String sialToken, String action, Long entityId, String privateKey) {
		if (sialToken != null && !sialToken.isEmpty()) {
			Claims claims = Jwts.parser().setSigningKey(privateKey).parseClaimsJws(sialToken).getBody();
			if (claims.getExpiration().after(new Date(System.currentTimeMillis())) && claims.getSubject().equals(String.valueOf(entityId)) && action.equals(claims.get(ACTION))) {
				return true;
			}
		}
		return false;
	}

}
