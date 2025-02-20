package gr.eduping.eduping.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.jwtSecretKey}")
    private String jwtSecretKey;

    @Value("${jwt.jwtExpirationMs}")
    private Long JwtExpiration;

    /**
     * Generates a JWT holding username, role and id using the HMAC-SHA256 algorithm.
     * @param username {@link UserDetails} object's username
     * @param role {@link UserDetails} object's role
     * @param id {@link UserDetails} object's id
     * @return the JWT token
     */
    public String generateToken(String username, String role, Long id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);
        claims.put("uid", id);
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration( new Date(System.currentTimeMillis() + JwtExpiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    /**
     * Checks validity of a JWT by comparing the subject with the {@link gr.eduping.eduping.model.User}'s username
     * and expiration date.
     * @param token the token to check
     * @param userDetails the model object to compare with
     * @return True if JWT is valid, false otherwise
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String subject = extractSubject(token);
        return (subject.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /**
     * Extracts the subject (registered claim "sub") of a given JWT.
     * @param token the token to extract date from
     * @return the subject of given JWT, which matches {@link gr.eduping.eduping.model.User}'s 'username'
     */
    public String extractSubject(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Generic function used to extract a specific claim from a given JWT.
     * @param token the token to extract claim from
     * @param claimsResolver the function that dictates which claim should be extracted
     * @return
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Checks if a JWT is expired.
     * @param token the token to check
     * @return True if JWT is expired, false otherwise
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /**
     * Extracts the expiration date (registered claim "exp") of a given JWT.
     * @param token the token to extract date from
     * @return the {@link Date} object that the token expires at
     */
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extracts all claims from a given JWT.
     * @param token the token to extract claims from
     * @return a {@link Claims} object containing all claims from input JWT
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Creates a HS256 Key
     * @return a {@link javax.crypto.SecretKey} object
     */
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
