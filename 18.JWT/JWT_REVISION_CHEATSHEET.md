# JWT Revision Cheat Sheet for Spring Boot

This file is a one-place revision guide for everything covered in `18.JWT`:

- `Project Setup for JWT`
- `Generating JWT Token`
- `Validating JWT Token`

It combines your local implementation with current reference material from Spring Security, JJWT, RFC 7519, and OWASP.

## 1. What JWT Is

JWT stands for JSON Web Token.

It is a compact string used to carry identity and claim data between client and server. A JWT is usually used after login:

1. User sends username and password to `/login`
2. Server verifies the credentials
3. Server creates a signed JWT
4. Client stores that token
5. Client sends the token in later requests
6. Server validates the token and allows access if valid

JWT is useful when we want a stateless authentication flow. In a stateless system, the server does not keep login session data in `HttpSession` for every request.

Spring Security’s `SessionCreationPolicy.STATELESS` means Spring Security will not create an `HttpSession` and will not use it to obtain the `SecurityContext`.

## 2. JWT Structure

A JWT has 3 parts:

`header.payload.signature`

Example shape:

```text
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ5YXNodSIsImV4cCI6MTcxMzQwMDAwMH0.xxxxx
```

### Header

Contains metadata such as algorithm.

Example:

```json
{
  "alg": "HS256",
  "typ": "JWT"
}
```

### Payload

Contains claims.

Example:

```json
{
  "sub": "yashu",
  "iat": 1713456000,
  "exp": 1713459600
}
```

### Signature

This is created by signing header + payload using a secret key or key pair.

Important:

- JWT is signed, not encrypted by default
- Anyone holding the token can decode header and payload
- Never put passwords or very sensitive data inside the token

## 3. Common Claims to Remember

RFC 7519 defines standard registered claim names. The most useful for us are:

- `sub` = subject, usually username or user id
- `iat` = issued at
- `exp` = expiration time
- `nbf` = not before
- `iss` = issuer
- `aud` = audience
- `jti` = token id

In your project, the main claim being used is:

- `sub` for username

You are also setting:

- `iat`
- `exp`

## 4. Why JWT in Spring Boot

JWT is commonly used in Spring Boot REST APIs because:

- APIs are often stateless
- Mobile apps and SPA frontends can send the token easily
- It avoids server session storage for each logged-in user
- It fits well with custom authentication filters in Spring Security

## 5. Where JWT Fits in Your `18.JWT` Flow

Your folders show a natural progression:

### `Project Setup for JWT`

You prepared the Spring Security + JPA + user loading foundation:

- `SecurityConfig`
- `UserDetailsService`
- `UserPrincipal`
- `Users`
- `UserRepo`
- `UserService`

At this stage, authentication is ready, but JWT token creation/validation is not fully active.

### `Generating JWT Token`

You added token creation:

- `JWTService.generateToken(username)`
- `UserService.verify(user)` authenticates user through `AuthenticationManager`
- `/login` returns a token after successful authentication

### `Validating JWT Token`

You added token validation:

- `JwtFilter`
- extraction of username from token
- token verification
- `SecurityContextHolder` population
- custom filter registration in `SecurityConfig`

This is the full JWT request flow.

## 6. Classes You Need to Implement

For a basic JWT auth flow in Spring Boot, these are the important classes.

### 1. `SecurityConfig`

Responsibility:

- define security rules
- expose `AuthenticationManager`
- define `AuthenticationProvider`
- define `PasswordEncoder`
- make app stateless
- register JWT filter

Key things to remember:

- permit `/login` and `/register`
- protect other routes
- set `SessionCreationPolicy.STATELESS`
- add your JWT filter before `UsernamePasswordAuthenticationFilter`

Typical shape:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/login", "/register").permitAll()
                    .anyRequest().authenticated())
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
}
```

### 2. `Users`

Responsibility:

- entity class stored in database
- contains fields like `id`, `username`, `password`

### 3. `UserRepo`

Responsibility:

- fetch user from database
- usually extends `JpaRepository`
- commonly has `findByUsername(String username)`

### 4. `UserPrincipal`

Responsibility:

- adapts your `Users` entity to Spring Security `UserDetails`
- returns username, password, authorities, account flags

### 5. `MyUserDetailsService`

Responsibility:

- implements `UserDetailsService`
- loads user from DB using repository
- returns `UserPrincipal`

This class is used by `DaoAuthenticationProvider`.

### 6. `UserService`

Responsibility:

- register users
- encode password before saving
- authenticate credentials through `AuthenticationManager`
- create JWT when login is successful

### 7. `JWTService`

Responsibility:

- generate token
- extract claims
- extract username
- validate token
- detect expiration

### 8. `JwtFilter`

Responsibility:

- read `Authorization` header
- check for `Bearer <token>`
- extract username from token
- load user details
- validate token
- create `UsernamePasswordAuthenticationToken`
- set authentication into `SecurityContextHolder`

This is the class that makes JWT actually work on later requests.

## 7. The Full Request Flow

### Registration flow

1. Client calls `POST /register`
2. Password is encoded with `BCryptPasswordEncoder`
3. User is saved in database

### Login flow

1. Client calls `POST /login` with username and password
2. `AuthenticationManager` authenticates the user
3. `UserDetailsService` loads user from DB
4. `DaoAuthenticationProvider` checks password with encoder
5. If authenticated, server returns JWT

### Access secured endpoint flow

1. Client calls protected API
2. Client sends header:

```http
Authorization: Bearer <token>
```

3. `JwtFilter` runs before username/password filter
4. Filter extracts token
5. Filter reads username from token
6. Filter validates signature and expiry
7. Filter creates authentication object
8. Spring Security sees request as authenticated
9. Controller method executes

## 8. Core Spring Security Objects to Understand

### `SecurityFilterChain`

This is the main security pipeline for requests.

### `AuthenticationManager`

Used to authenticate login credentials.

You use it in `UserService.verify(...)`.

### `AuthenticationProvider`

Actual component that knows how to verify credentials.

In your case:

- `DaoAuthenticationProvider`

### `UserDetailsService`

Loads user details from database.

### `PasswordEncoder`

Used for password hashing and verification.

Use:

- `BCryptPasswordEncoder`

Avoid in real applications:

- `NoOpPasswordEncoder`

### `UsernamePasswordAuthenticationToken`

Used in 2 places:

- while authenticating raw username/password at login time
- while creating authenticated principal from JWT during request filtering

### `SecurityContextHolder`

Stores authentication for the current request thread.

If JWT is valid, we put the authentication here.

## 9. JJWT Methods You Should Remember

Your project uses the `io.jsonwebtoken` library.

Dependencies used:

- `jjwt-api`
- `jjwt-impl`
- `jjwt-jackson`

Important methods:

### Generate token

```java
String token = Jwts.builder()
        .subject(username)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
        .signWith(getKey())
        .compact();
```

### Parse and validate token

```java
Claims claims = Jwts.parser()
        .verifyWith(getKey())
        .build()
        .parseSignedClaims(token)
        .getPayload();
```

### Extract username

```java
String username = claims.getSubject();
```

## 10. Example JWTService Template

This is a clean revision-ready example:

```java
@Service
public class JWTService {

    private final String secret =
            "replace-this-with-a-long-base64-or-strong-secret-key";

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000L * 60 * 30))
                .signWith(getKey())
                .compact();
    }

    public String extractUserName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return resolver.apply(claims);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUserName(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }
}
```

## 11. Example JwtFilter Template

This is the correct pattern for Spring Boot + Spring Security.

Important:

- the filter must be a real servlet filter
- easiest way is extending `OncePerRequestFilter`

```java
@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            username = jwtService.extractUserName(token);
        }

        if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if (jwtService.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}
```

## 12. Example Login Controller

```java
@PostMapping("/login")
public String login(@RequestBody Users user) {
    return service.verify(user);
}
```

And in service:

```java
public String verify(Users user) {
    Authentication authentication = authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword()));

    if (authentication.isAuthenticated()) {
        return jwtService.generateToken(user.getUsername());
    }

    return "fail";
}
```

## 13. Commands and Test Requests

### Register user

```bash
curl -X POST http://localhost:8080/register ^
  -H "Content-Type: application/json" ^
  -d "{\"id\":1,\"username\":\"yashu\",\"password\":\"pass123\"}"
```

### Login and get token

```bash
curl -X POST http://localhost:8080/login ^
  -H "Content-Type: application/json" ^
  -d "{\"username\":\"yashu\",\"password\":\"pass123\"}"
```

Expected output:

```text
<jwt-token-here>
```

### Call secured API

```bash
curl http://localhost:8080/students ^
  -H "Authorization: Bearer <jwt-token-here>"
```

### Run app

```bash
./mvnw spring-boot:run
```

On Windows PowerShell, if needed:

```powershell
.\mvnw.cmd spring-boot:run
```

## 14. Things to Remember Before Exams or Interviews

### JWT vs Session

Session-based auth:

- server stores login state
- browser usually carries session id cookie

JWT-based auth:

- token carries identity data
- server validates token each request
- common in stateless APIs

### JWT does not mean no database

JWT only helps carry authentication state.

You still use database for:

- users
- roles
- permissions
- registration

### Passwords are never stored in JWT

Store only needed claims like:

- username
- user id
- roles

### Signature is critical

If signature verification fails, token must be rejected.

### Expiration is important

Always set `exp`.

Short-lived access tokens are safer.

### JWT is not encrypted by default

Payload is readable after decoding.

Do not put:

- password
- OTP
- secret keys
- full sensitive business data

## 15. Common Mistakes in JWT Projects

### Mistake 1: Custom filter does not extend `Filter`

If you call:

```java
.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
```

then `jwtFilter` must be a Spring/Servlet `Filter`.

Best practice:

- extend `OncePerRequestFilter`

### Mistake 2: Missing space in Bearer prefix

Wrong:

```java
authHeader.startsWith("Bearer")
```

Correct:

```java
authHeader.startsWith("Bearer ")
```

### Mistake 3: Secret key changes on every restart

Your current `JWTService` generates the secret key in the constructor.

That means:

- server restart creates a new key
- previously issued tokens become invalid

For learning, this is okay.

For real applications:

- keep the secret in config or env variable
- do not generate a fresh key on each startup unless you want forced invalidation

### Mistake 4: Wrong expiration math

This line from your current project needs attention:

```java
.expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
```

This is not 30 minutes or 30 hours in milliseconds.

It equals:

- `108000 ms`
- only `108 seconds`
- around `1.8 minutes`

Use something clear:

```java
1000L * 60 * 30
```

for 30 minutes.

### Mistake 5: Keeping `httpBasic()` while expecting only JWT

If your final design is pure JWT auth, `httpBasic()` is usually not needed.

You may keep it while learning, but remember:

- `httpBasic()` authenticates using username/password per request
- JWT auth usually replaces that flow after login

### Mistake 6: Using `NoOpPasswordEncoder`

Never use plain-text passwords in real applications.

Use:

- `BCryptPasswordEncoder`

### Mistake 7: Not handling exceptions around token parsing

Token parsing can fail because of:

- bad signature
- malformed token
- expired token
- unsupported token

In production code, catch JWT exceptions and reject the request safely.

## 16. Your Project-Specific Notes

From your current code in `18.JWT`:

- `UserService.register(...)` correctly hashes password with BCrypt
- `UserService.verify(...)` correctly authenticates before issuing token
- `MyUserDetailsService` correctly loads user from DB
- `UserPrincipal` adapts DB user to Spring Security
- `SecurityConfig` correctly uses stateless session policy
- `JwtFilter` concept is correct, but it must extend `OncePerRequestFilter`

Also note:

- `ApplicationContext` lookup inside the filter works, but direct injection of `MyUserDetailsService` or `UserDetailsService` is cleaner
- roles are currently hardcoded as `"USER"` in `UserPrincipal`
- your `application.properties` still has default Spring Security user values, but with custom DB auth these may become unnecessary

## 17. Minimal Checklist for JWT in Spring Boot

- add Spring Security dependency
- add JJWT dependency
- create user entity
- create user repository
- create `UserDetails` implementation
- create `UserDetailsService`
- create `PasswordEncoder`
- create `AuthenticationProvider`
- create `AuthenticationManager`
- create `JWTService`
- create `/register`
- create `/login`
- create `JwtFilter extends OncePerRequestFilter`
- register filter in `SecurityConfig`
- set `SessionCreationPolicy.STATELESS`
- send `Authorization: Bearer <token>` on protected calls

## 18. Best Practices to Remember

- keep access tokens short-lived
- keep signing key stable and secure
- never store passwords in tokens
- validate signature before trusting claims
- validate expiry
- consider issuer and audience checks in bigger systems
- use HTTPS in real deployments
- use refresh tokens separately if needed
- store secrets in environment variables or secret manager
- if logout invalidation is required, use short expiry or a denylist strategy

## 19. One-Page Mental Model

Think of JWT auth in 3 steps:

### Step 1: Authenticate

Use username and password once at login.

### Step 2: Issue token

Server creates signed JWT containing identity claims.

### Step 3: Validate token

Every protected request sends token.
Filter validates it and sets authentication into Spring Security context.

That is the full cycle.

## 20. Sources Checked Online

These were used to verify current theory and implementation guidance:

- Spring Security `SessionCreationPolicy` API: https://docs.spring.io/spring-security/reference/api/java/org/springframework/security/config/http/SessionCreationPolicy.html
- Spring Security filter architecture and custom filter placement: https://docs.spring.io/spring-security/reference/7.1-SNAPSHOT/servlet/architecture.html
- Spring Framework filter docs for `OncePerRequestFilter`: https://docs.spring.io/spring-framework/reference/web/webmvc/filters.html
- JJWT official repository and usage examples: https://github.com/jwtk/jjwt
- RFC 7519 JWT specification: https://datatracker.ietf.org/doc/html/rfc7519
- OWASP JWT for Java cheat sheet: https://cheatsheetseries.owasp.org/cheatsheets/JSON_Web_Token_for_Java_Cheat_Sheet.html

## 21. Final Revision Summary

If you remember only this, remember:

1. Login uses username/password and `AuthenticationManager`
2. Success returns a signed JWT
3. Client sends token in `Authorization: Bearer <token>`
4. `JwtFilter` validates the token on every protected request
5. Valid token means `SecurityContextHolder` gets authentication
6. App stays stateless with `SessionCreationPolicy.STATELESS`

For your project, the most important implementation pieces are:

- `SecurityConfig`
- `JWTService`
- `JwtFilter`
- `UserService`
- `MyUserDetailsService`
- `UserPrincipal`

If these six make sense to you, the whole JWT flow becomes easy to explain.
