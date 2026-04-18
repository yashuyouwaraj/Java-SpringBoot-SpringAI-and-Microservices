# Spring Security Cheat Sheet

Revision sheet for the `17.Spring Security` section in this repo.

This cheat sheet is written for Spring Boot style configuration using `SecurityFilterChain`, which matches the code you are practicing in folders like:

- `SpringSecEx`
- `Custom Login`
- `Custom Configuration`
- `CSRF Token`
- `UserVerifyFromDatabase`
- `Bcrypt Password Encoder`

## 1. What Spring Security Is

Spring Security is the security framework used in Spring applications to handle:

- authentication: who the user is
- authorization: what the user is allowed to do
- exploit protection: CSRF, session protection, security headers, etc.
- integration with login methods: form login, HTTP Basic, database login, OAuth2, JWT, LDAP, and more

In short:

- Authentication = identity check
- Authorization = permission check

Example:

- user logs in with username/password -> authentication
- only admins can access `/admin` -> authorization

## 2. Why We Use It

Without Spring Security, we would need to manually build:

- login logic
- session handling
- password hashing
- role checks
- filter-based request protection
- CSRF protection
- logout handling
- method-level security

Spring Security gives these features in a standard, reusable, production-ready way.

## 3. What Happens When You Add It

When you add the dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

your application becomes secured by default.

Typical default behavior:

- all endpoints require authentication
- Spring Boot creates a default user named `user`
- a generated password is printed in the console at startup
- Boot chooses `formLogin` or `httpBasic` depending on the client/request style
- logout support is available at `/logout`
- CSRF protection is enabled by default for unsafe methods like `POST`, `PUT`, `DELETE`

## 4. Core Terms You Must Know

### `SecurityFilterChain`

This is the main bean where we configure security rules.

It defines:

- which URLs need authentication
- which URLs are public
- login style
- logout behavior
- CSRF settings
- session policy
- custom filters if needed

Example:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/public/**").permitAll()
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            .build();
}
```

### `HttpSecurity`

Fluent API used to configure web security.

Common methods:

- `authorizeHttpRequests(...)`
- `formLogin(...)`
- `httpBasic(...)`
- `csrf(...)`
- `logout(...)`
- `sessionManagement(...)`

### `Authentication`

Represents the current logged-in user.

It usually contains:

- principal -> user details
- credentials -> password/token
- authorities -> roles/permissions

### `SecurityContext`

Holds the current `Authentication`.

Spring Security stores it using `SecurityContextHolder`.

### `UserDetails`

Represents user information required by Spring Security.

Usually contains:

- username
- password
- enabled status
- authorities

### `UserDetailsService`

Loads a user by username.

Used when login data comes from:

- in-memory users
- database users
- custom source

Main method:

```java
UserDetails loadUserByUsername(String username)
```

### `AuthenticationProvider`

Performs actual authentication.

Example:

- `DaoAuthenticationProvider` -> username/password using `UserDetailsService`

### `PasswordEncoder`

Used to hash passwords and verify them securely.

Common encoders:

- `BCryptPasswordEncoder`
- `NoOpPasswordEncoder` for demos only, never real production use

## 5. High-Level Request Flow

Remember this flow:

1. Request comes in
2. Spring Security filters intercept it
3. If login/authentication is needed, credentials are processed
4. `AuthenticationManager` delegates to an `AuthenticationProvider`
5. Provider checks credentials
6. If valid, an authenticated `Authentication` object is created
7. It is stored in `SecurityContext`
8. Authorization rules are checked
9. Request is allowed or blocked

Simple memory version:

`Request -> Filter Chain -> Authentication -> SecurityContext -> Authorization -> Response`

## 6. Important Features

Spring Security supports:

- form login
- HTTP Basic authentication
- custom login pages
- in-memory users
- database-based users
- role-based access control
- method-level security with annotations
- CSRF protection
- session management
- logout handling
- remember-me
- password hashing
- OAuth2 login
- JWT integration
- SAML, LDAP, and more

For your current revision, focus most on:

- Basic auth
- form login
- roles and URL rules
- `UserDetailsService`
- `DaoAuthenticationProvider`
- password encoding
- CSRF
- stateless vs stateful security

## 7. Basic Setup

### Maven dependency

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

If you use web APIs:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

If you load users from DB:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

## 8. Minimum Security Config

### Authenticate every request

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .build();
    }
}
```

Meaning:

- every endpoint needs login
- Spring Security provides default login page

## 9. HTTP Basic Authentication

Useful for:

- REST APIs
- Postman testing
- simple demos

Config:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .authorizeHttpRequests(auth -> auth
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .build();
}
```

What it does:

- browser/Postman sends username and password in `Authorization` header
- Spring Security validates them
- no custom login page required

Header format:

```text
Authorization: Basic <base64(username:password)>
```

Example:

```bash
curl -u yashu:password http://localhost:8080/hello
```

## 10. Form Login

Useful for normal web apps with browser login.

Config:

```java
.formLogin(Customizer.withDefaults())
```

What it does:

- provides a default login form if you do not create one
- processes submitted username/password
- creates authenticated session after successful login

## 11. Custom Login Page

Used in your `Custom Login` practice.

Example:

```java
.formLogin(form -> form
        .loginPage("/login")
        .permitAll()
)
```

Meaning:

- your app will show its own `/login` page
- everyone can access the login page

Important:

- if you use a custom login form, the form fields should normally be named `username` and `password`
- the form should submit to the login processing URL, by default `/login`

Example HTML:

```html
<form method="post" action="/login">
    <input type="text" name="username" />
    <input type="password" name="password" />
    <button type="submit">Login</button>
</form>
```

If CSRF is enabled, the form also needs the CSRF token.

## 12. Authorization Rules

This is where we decide which user can access which endpoint.

Example:

```java
.authorizeHttpRequests(auth -> auth
        .requestMatchers("/", "/login", "/about").permitAll()
        .requestMatchers("/admin/**").hasRole("ADMIN")
        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
        .anyRequest().authenticated()
)
```

Meaning:

- public pages -> no login needed
- `/admin/**` -> only admin role
- `/user/**` -> user or admin
- other pages -> any authenticated user

### `permitAll()`

Anyone can access.

### `authenticated()`

Only logged-in users can access.

### `hasRole("ADMIN")`

User must have role `ADMIN`.

### `hasAnyRole("USER", "ADMIN")`

User must have one of the listed roles.

## 13. Roles vs Authorities

This confuses many people.

### Role

High-level permission like:

- `ROLE_USER`
- `ROLE_ADMIN`

When you write:

```java
hasRole("ADMIN")
```

Spring usually checks for authority:

```text
ROLE_ADMIN
```

### Authority

Lower-level permission like:

- `READ_REPORTS`
- `WRITE_REPORTS`

Example:

```java
hasAuthority("READ_REPORTS")
```

Use roles for broad access control, authorities for fine-grained permissions.

## 14. In-Memory Users

Good for learning and small demos.

Example:

```java
@Bean
public UserDetailsService userDetailsService() {
    UserDetails user1 = User
            .withUsername("kiran")
            .password("{noop}k@123")
            .roles("USER")
            .build();

    UserDetails user2 = User
            .withUsername("harsh")
            .password("{noop}h@123")
            .roles("ADMIN")
            .build();

    return new InMemoryUserDetailsManager(user1, user2);
}
```

When to use:

- learning
- quick testing
- not real production user storage

## 15. Database Authentication

Used in your `UserVerifyFromDatabase` practice.

Idea:

1. user enters username/password
2. Spring Security calls your `UserDetailsService`
3. your service fetches user from DB
4. it returns a `UserDetails` implementation
5. provider checks the password using `PasswordEncoder`

Typical flow:

```java
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }
}
```

Why `UserPrincipal`?

- your DB entity may not directly implement `UserDetails`
- wrapper class converts your entity into Spring Security format

## 16. `DaoAuthenticationProvider`

This is the common provider for username/password + DB lookup.

Example:

```java
@Bean
public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService,
                                                     PasswordEncoder passwordEncoder) {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
    provider.setPasswordEncoder(passwordEncoder);
    return provider;
}
```

What it does:

- uses `UserDetailsService` to fetch user
- uses `PasswordEncoder` to verify password
- returns authenticated user if valid

## 17. Password Encoding

Never store raw passwords in production.

Use a `PasswordEncoder`.

### Bad for real apps

```java
return NoOpPasswordEncoder.getInstance();
```

What it does:

- no hashing
- plain text comparison only
- okay only for demo/learning

### Good choice for real apps

```java
@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}
```

Or tuned strength:

```java
return new BCryptPasswordEncoder(12);
```

What BCrypt gives:

- one-way hashing
- salted hashing
- slower by design, which improves password cracking resistance

### Important note

If using encoded password strings with Spring Security defaults, you may also see prefixes like:

```text
{bcrypt}...
{noop}...
```

These help Spring know which encoder to use.

## 18. Registering a User with BCrypt

Used in your `Bcrypt Password Encoder` module.

Example:

```java
String encodedPassword = passwordEncoder.encode(user.getPassword());
user.setPassword(encodedPassword);
repo.save(user);
```

At login time:

- user types raw password
- Spring Security encodes/checks internally
- it compares with stored encoded password

You should not manually compare raw and hashed passwords with `equals`.

## 19. CSRF

CSRF = Cross Site Request Forgery

It is an attack where a logged-in user is tricked into sending an unwanted request.

Spring Security enables CSRF protection by default for unsafe methods such as:

- `POST`
- `PUT`
- `PATCH`
- `DELETE`

### Why it matters

Suppose a user is logged into your banking app.
If CSRF protection is missing, a malicious site may trigger a transfer request from the user’s browser.

### When CSRF matters most

CSRF mainly matters in session-based browser applications.

### Why many REST demos disable it

In stateless APIs using:

- HTTP Basic for testing
- bearer tokens
- no browser session

developers often disable CSRF.

Example:

```java
.csrf(csrf -> csrf.disable())
```

Do not blindly disable CSRF in normal browser form-login apps.

### If CSRF is enabled

HTML form must include the CSRF token.

Conceptual example:

```html
<input type="hidden" name="_csrf" value="...token..." />
```

If CSRF token is missing for an unsafe request, Spring Security typically returns `403 Forbidden`.

## 20. Session Management

Spring Security can work in two common styles.

### Stateful

Used in normal web apps with form login.

How it works:

- user logs in once
- session is created
- subsequent requests use session

### Stateless

Used in APIs, especially with JWT or basic-auth demos.

Config:

```java
.sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
)
```

Meaning:

- do not create server session for authentication state
- every request should carry credentials

Your current API-style configs in this repo use this pattern.

## 21. Logout

Spring Security provides `/logout` by default.

With logout support:

- session can be invalidated
- `SecurityContext` can be cleared
- remember-me tokens can be cleared
- CSRF token can be removed

Simple config:

```java
.logout(logout -> logout
        .logoutSuccessUrl("/login?logout")
)
```

## 22. Method-Level Security

Instead of securing only URLs, you can secure methods.

Enable it:

```java
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
}
```

Example:

```java
@PreAuthorize("hasRole('ADMIN')")
public void deleteStudent(int id) {
    // ...
}
```

Useful when:

- same service method is called from different endpoints
- you want security near business logic

## 23. Common Config Patterns

### Public + secured endpoints

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/", "/home", "/register").permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(Customizer.withDefaults())
            .build();
}
```

### REST API with Basic Auth

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .build();
}
```

### Role-based API

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/student/**").hasAnyRole("USER", "ADMIN")
                    .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())
            .build();
}
```

## 24. Commands You Should Remember

### Run the app

From inside a Spring Boot project folder:

```bash
./mvnw spring-boot:run
```

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

What it does:

- builds and starts the Spring Boot app

### Package the app

```bash
./mvnw clean package
```

What it does:

- removes old build output
- compiles code
- runs tests
- creates jar

### Run tests

```bash
./mvnw test
```

What it does:

- runs project test cases

### Call a secured endpoint with HTTP Basic

```bash
curl -u user:password http://localhost:8080/hello
```

What it does:

- sends username/password using Basic auth

### Call a POST endpoint with JSON

```bash
curl -u user:password -X POST http://localhost:8080/students \
  -H "Content-Type: application/json" \
  -d "{\"id\":1,\"name\":\"Yashu\",\"marks\":90}"
```

What it does:

- authenticates user
- sends JSON body
- creates or posts data if endpoint allows it

### Send custom header token later in JWT-based apps

```bash
curl -H "Authorization: Bearer <token>" http://localhost:8080/api
```

What it does:

- sends bearer token instead of username/password

This becomes more important in your `18.JWT` section.

### Encode password with Spring Boot CLI

If Spring Boot CLI is installed:

```bash
spring encodepassword password
```

What it does:

- generates an encoded password string, often using bcrypt-style storage format

## 25. Example Based on Your Current Practice

Your `UserVerifyFromDatabase` config is conceptually like this:

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

Meaning:

- all requests require login
- Basic auth is used
- no session is stored
- user is loaded from DB
- password is checked through BCrypt

## 26. Common Interview and Exam Questions

### What is Spring Security?

Framework for authentication, authorization, and web security in Spring applications.

### What is the difference between authentication and authorization?

- authentication checks identity
- authorization checks access rights

### What is `SecurityFilterChain`?

Main bean that defines security rules for incoming HTTP requests.

### What is `UserDetailsService`?

Interface used to load user data by username.

### What is `UserDetails`?

Interface representing security-specific user information.

### What is `PasswordEncoder`?

Used to hash passwords and match raw password with stored encoded password.

### Why use BCrypt?

Because it stores passwords as one-way hashes and is intentionally slow for better security.

### Why disable CSRF in some REST APIs?

Because stateless APIs using headers/tokens often do not rely on browser sessions, where CSRF risk is most relevant.

### What is `DaoAuthenticationProvider`?

Authentication provider that uses `UserDetailsService` and `PasswordEncoder` for username/password login.

### What is the difference between role and authority?

- role = broad permission like `ADMIN`
- authority = fine-grained permission like `READ_REPORTS`

## 27. Common Mistakes

### Using `NoOpPasswordEncoder` in real projects

Bad because passwords remain effectively plain text.

### Disabling CSRF without understanding why

Safe in some stateless API demos, risky in browser form-login apps.

### Forgetting `permitAll()` for login or register page

Then Spring can block access to your own login page.

### Storing raw passwords in DB

Always encode first.

### Confusing `hasRole("ADMIN")` with `hasAuthority("ADMIN")`

`hasRole("ADMIN")` usually expects `ROLE_ADMIN`.

### Forgetting that every request is secured by default

If endpoint returns `401` or redirects to login unexpectedly, check your authorization rules.

## 28. Quick Comparison Table

| Topic | Meaning | Common Class/Method |
|---|---|---|
| Authentication | Who are you? | `Authentication`, `AuthenticationProvider` |
| Authorization | What can you access? | `authorizeHttpRequests`, `@PreAuthorize` |
| Current logged-in user | User stored after authentication | `SecurityContextHolder` |
| User loading | Fetch user by username | `UserDetailsService` |
| Password hashing | Secure password storage | `PasswordEncoder`, `BCryptPasswordEncoder` |
| URL security | Secure endpoints | `requestMatchers`, `permitAll`, `hasRole` |
| Form login | Browser login page | `formLogin()` |
| Basic auth | Header-based username/password | `httpBasic()` |
| CSRF | Protect unsafe browser requests | `csrf()` |
| Stateless API | No auth session stored | `SessionCreationPolicy.STATELESS` |

## 29. Best Practice Revision Notes

- use `SecurityFilterChain`, not old `WebSecurityConfigurerAdapter`
- prefer `BCryptPasswordEncoder` for password storage
- use `UserDetailsService` for custom or DB-based login
- use `DaoAuthenticationProvider` for standard username/password auth
- do not disable CSRF in normal session-based form apps without reason
- use stateless session policy for token-based APIs
- keep public endpoints explicit with `permitAll()`
- use method security when business rules should be enforced in service layer too

## 30. 30-Second Revision

Spring Security secures Spring apps using a filter chain. It authenticates users, stores the authenticated user in the security context, and authorizes access based on roles/authorities. We configure it mainly using `SecurityFilterChain` and `HttpSecurity`. For DB login, we use `UserDetailsService`, `UserDetails`, `DaoAuthenticationProvider`, and `PasswordEncoder`. For password storage, prefer BCrypt. CSRF is enabled by default and is important for session-based browser apps. For APIs, we often use `httpBasic` or JWT and stateless sessions.

## 31. 10 Must-Memorize Lines

1. Spring Security handles authentication, authorization, and exploit protection.
2. Adding `spring-boot-starter-security` secures the app by default.
3. `SecurityFilterChain` is the main place to configure security.
4. `UserDetailsService` loads users by username.
5. `UserDetails` represents the logged-in user for Spring Security.
6. `DaoAuthenticationProvider` uses `UserDetailsService` and `PasswordEncoder`.
7. `BCryptPasswordEncoder` is the preferred password encoder for real apps.
8. CSRF is enabled by default for unsafe HTTP methods.
9. `hasRole("ADMIN")` usually means authority `ROLE_ADMIN`.
10. Stateless APIs often use `SessionCreationPolicy.STATELESS`.

## 32. Practice Checklist

Use this when revising your folders:

- `SpringSecEx`: understand default login behavior
- `Custom Login`: understand custom login page flow
- `Custom Configuration`: practice URL rules and role checks
- `CSRF Token`: understand why POST may fail with 403
- `UserVerifyFromDatabase`: understand `UserDetailsService` and DB lookup
- `Bcrypt Password Encoder`: understand password hashing during registration and login

## 33. Official Docs Used

- Spring Security architecture: https://docs.spring.io/spring-security/reference/servlet/architecture.html
- Servlet authentication architecture: https://docs.spring.io/spring-security/reference/6.5/servlet/authentication/architecture.html
- Authorize HTTP requests: https://docs.spring.io/spring-security/reference/servlet/authorization/authorize-http-requests.html
- CSRF: https://docs.spring.io/spring-security/reference/servlet/exploits/csrf.html
- Password storage: https://docs.spring.io/spring-security/reference/features/authentication/password-storage.html
- Method security: https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html
- Session management: https://docs.spring.io/spring-security/reference/servlet/authentication/session-management.html
- Logout: https://docs.spring.io/spring-security/reference/6.5/servlet/authentication/logout.html
- Spring Boot security defaults: https://docs.enterprise.spring.io/spring-boot/reference/web/spring-security.html

## 34. Final Memory Map

Think of Spring Security like this:

```text
Dependency added
-> App becomes secured
-> Request hits security filters
-> User authenticates
-> User stored in SecurityContext
-> Authorization rules check role/authority
-> Access granted or denied
```

If you want, the next useful step is for me to also make a `1-page ultra-short revision sheet` version from this for last-minute exam review.
