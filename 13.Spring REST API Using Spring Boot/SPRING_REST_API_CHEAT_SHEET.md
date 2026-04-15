# Spring REST API Cheat Sheet

One-place revision sheet for the projects inside `13.Spring REST API Using Spring Boot`.

Built from:

- your local project folders
- official Spring docs and guides

Official references used:

- Spring Boot Maven plugin run docs: https://docs.spring.io/spring-boot/maven-plugin/run.html
- Spring REST guide: https://spring.io/guides/gs/rest-service/
- Spring request mapping reference: https://docs.spring.io/spring-framework/reference/web/webflux/controller/ann-requestmapping.html
- `@RestController` Javadoc: https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RestController.html

## 1. What Is a REST API?

REST = Representational State Transfer.

A REST API is a web service where clients communicate with your application using HTTP.

The client sends:

- a URL
- an HTTP method
- sometimes JSON data

The server returns:

- data
- status
- JSON or text response

Example:

```http
GET /products
```

The server returns product data.

## 2. What Is Spring REST API in Spring Boot?

In Spring Boot, a REST API means:

- create controller classes
- map URLs to methods
- use annotations like `@RestController`, `@GetMapping`, `@PostMapping`
- return Java objects
- Spring converts them to JSON automatically

Short idea:

Your Java object becomes API response data.

## 3. How It Works

Basic flow:

1. Client sends HTTP request.
2. Spring receives the request.
3. Matching controller method is selected.
4. Path values or JSON body are mapped to method parameters.
5. Service layer performs business logic.
6. Response object is returned.
7. Spring converts Java object to JSON and sends it back.

In your folder this flow appears clearly in:

- `Spring Boot Web`
- `Spring MVC and Layers`
- `Spring Web Http Methods GET and POST`
- `Spring Web PUT and DELETE`

## 4. Main Theory You Must Remember

### `@RestController`

Marks a class as a REST controller.

Meaning:

- class handles web requests
- return values go directly into HTTP response body

Official Spring docs describe `@RestController` as a convenience annotation combining `@Controller` and `@ResponseBody`.

### Request Mapping

Spring maps URLs and HTTP methods to Java methods.

Examples:

- `@RequestMapping("/")`
- `@GetMapping("/products")`
- `@PostMapping("/products")`
- `@PutMapping("/products")`
- `@DeleteMapping("/products/{prodId}")`

### JSON Conversion

If a method returns an object or list, Spring converts it to JSON automatically.

Example:

```java
public List<Product> getProducts() {
    return service.getProducts();
}
```

Spring returns JSON, not Java source code.

### Layers

A clean REST API is usually split into layers:

- controller layer
- service layer
- model layer

Your `Spring MVC and Layers` project follows this pattern.

## 5. Folder-by-Folder Revision Map

### `Spring Boot Web`

Use this for:

- first REST controller
- basic URL mapping
- returning plain text

### `Spring MVC and Layers`

Use this for:

- controller-service-model structure
- returning object lists
- understanding separation of responsibility

### `Spring Web Http Methods GET and POST`

Use this for:

- `@GetMapping`
- `@PostMapping`
- `@PathVariable`
- `@RequestBody`

### `Spring Web PUT and DELETE`

Use this for:

- `@PutMapping`
- `@DeleteMapping`
- update and delete operations

### `simpleWebApp`

Use this as another Spring Boot web project reference if needed.

## 6. Core REST Annotations

### `@SpringBootApplication`

Marks the main Boot application class.

Example:

```java
@SpringBootApplication
public class SimpleWebAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimpleWebAppApplication.class, args);
    }
}
```

What it gives you:

- auto-configuration
- component scanning
- application startup

### `@RestController`

Example from your projects:

```java
@RestController
public class HomeController {
}
```

### `@RequestMapping`

General-purpose mapping annotation.

Example:

```java
@RequestMapping("/about")
public String about() {
    return "I am a software engineer";
}
```

### `@GetMapping`

Used for reading data.

Example:

```java
@GetMapping("/products")
public List<Product> getProducts() {
    return service.getProducts();
}
```

### `@PostMapping`

Used for creating new data.

Example:

```java
@PostMapping("/products")
public void addProduct(@RequestBody Product product) {
    service.addProduct(product);
}
```

### `@PutMapping`

Used for updating data.

Example:

```java
@PutMapping("/products")
public void updateProduct(@RequestBody Product product) {
    service.updateProduct(product);
}
```

### `@DeleteMapping`

Used for deleting data.

Example:

```java
@DeleteMapping("/products/{prodId}")
public void deleteProduct(@PathVariable int prodId) {
    service.deleteProduct(prodId);
}
```

### `@PathVariable`

Reads a value from the URL path.

Example:

```java
@GetMapping("/products/{prodId}")
public Product getProductById(@PathVariable int prodId) {
    return service.getProductById(prodId);
}
```

If URL is:

```text
/products/101
```

then:

```java
prodId = 101
```

### `@RequestBody`

Reads JSON from HTTP request body and converts it into a Java object.

Example JSON:

```json
{
  "prodId": 104,
  "prodName": "Keyboard",
  "price": 2500
}
```

Mapped to:

```java
public void addProduct(@RequestBody Product product)
```

## 7. HTTP Methods You Must Know

### `GET`

Used to read data.

Examples:

- get all products
- get one product

Typical endpoints:

```text
GET /products
GET /products/101
```

### `POST`

Used to create data.

Typical endpoint:

```text
POST /products
```

### `PUT`

Used to update existing data.

Typical endpoint:

```text
PUT /products
```

### `DELETE`

Used to delete data.

Typical endpoint:

```text
DELETE /products/101
```

Quick revision rule:

- `GET` = read
- `POST` = create
- `PUT` = update
- `DELETE` = delete

## 8. How Spring Converts Java Objects to JSON

Suppose you return:

```java
new Product(101, "Iphone", 50000)
```

Spring sends JSON like:

```json
{
  "prodId": 101,
  "prodName": "Iphone",
  "price": 50000
}
```

This is why model classes need:

- fields
- getters/setters
- usually a no-argument constructor for request-body binding

Important rule:

Model classes like `Product` are usually plain Java classes, not Spring beans.

## 9. MVC and Layers Theory

### Model

Represents data.

From your project:

```java
public class Product {
    private int prodId;
    private String prodName;
    private int price;
}
```

### Controller

Handles HTTP requests.

From your project:

```java
@RestController
public class ProductController {
}
```

### Service

Contains business logic.

From your project:

```java
@Service
public class ProductService {
}
```

Why layering matters:

- easier to read
- easier to test
- easier to maintain
- controller stays thin

Best mental model:

Controller talks to client.
Service talks to business logic.
Model holds data.

## 10. How To Implement a REST API in Spring Boot

Simple path:

1. Create Spring Boot project.
2. Add web dependency.
3. Create main class.
4. Create model class.
5. Create service class.
6. Create controller class.
7. Add endpoint mappings.
8. Run app.
9. Test in browser or Postman.

## 11. Minimum Project Setup

Your projects use Maven and Spring Boot.

Main dependency pattern in `pom.xml`:

```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>4.0.5</version>
</parent>
```

Main web dependency:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-webmvc</artifactId>
</dependency>
```

DevTools used in your projects:

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-devtools</artifactId>
    <scope>runtime</scope>
    <optional>true</optional>
</dependency>
```

Lombok is included in some later folders.

## 12. Required Configuration Files

### `pom.xml`

Used for:

- dependencies
- Java version
- Boot plugin

### `application.properties`

Your current projects contain:

```properties
spring.application.name=simpleWebApp
```

You can also configure:

```properties
server.port=8081
spring.application.name=simpleWebApp
```

Useful settings:

```properties
server.port=8081
spring.application.name=my-rest-app
```

## 13. Package Structure to Follow

Good structure based on your projects:

```text
src/main/java/com/yashu/simpleWebApp
|-- SimpleWebAppApplication.java
|-- controller
|   |-- ProductController.java
|-- service
|   |-- ProductService.java
|-- model
|   |-- Product.java
```

Why this works:

- Boot scans the package of the main application class and its subpackages
- your controllers, services, and models stay organized

## 14. Example: Basic Text REST Controller

From your `Spring Boot Web` folder idea:

```java
@RestController
public class HomeController {

    @RequestMapping("/")
    public String greet() {
        return "Welcome to Yashu!!!";
    }

    @RequestMapping("/about")
    public String about() {
        return "I am a software engineer";
    }
}
```

Test in browser:

```text
http://localhost:8080/
http://localhost:8080/about
```

## 15. Example: Layered Product API

### Model

```java
public class Product {
    private int prodId;
    private String prodName;
    private int price;
}
```

### Service

```java
@Service
public class ProductService {
    List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }
}
```

### Controller

```java
@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return service.getProducts();
    }
}
```

## 16. Example: GET, POST, PUT, DELETE Together

```java
@RestController
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return service.getProducts();
    }

    @GetMapping("/products/{prodId}")
    public Product getProductById(@PathVariable int prodId) {
        return service.getProductById(prodId);
    }

    @PostMapping("/products")
    public void addProduct(@RequestBody Product product) {
        service.addProduct(product);
    }

    @PutMapping("/products")
    public void updateProduct(@RequestBody Product product) {
        service.updateProduct(product);
    }

    @DeleteMapping("/products/{prodId}")
    public void deleteProduct(@PathVariable int prodId) {
        service.deleteProduct(prodId);
    }
}
```

## 17. API Testing Examples

### Get all products

```http
GET /products
```

### Get product by id

```http
GET /products/101
```

### Add product

```http
POST /products
Content-Type: application/json
```

Body:

```json
{
  "prodId": 104,
  "prodName": "Keyboard",
  "price": 2500
}
```

### Update product

```http
PUT /products
Content-Type: application/json
```

Body:

```json
{
  "prodId": 101,
  "prodName": "Iphone 16",
  "price": 80000
}
```

### Delete product

```http
DELETE /products/101
```

## 18. Commands You Need Most

Run these from the specific project folder.

### Windows with Maven Wrapper

Preferred in your projects because `mvnw.cmd` is included.

Run app:

```powershell
.\mvnw.cmd spring-boot:run
```

Clean and build:

```powershell
.\mvnw.cmd clean package
```

Run tests:

```powershell
.\mvnw.cmd test
```

### If Maven is installed globally

```powershell
mvn spring-boot:run
mvn clean package
mvn test
```

### Run the packaged jar

```powershell
java -jar target\simpleWebApp-0.0.1-SNAPSHOT.jar
```

## 19. Useful Spring Boot Run Commands

From official Spring Boot Maven plugin docs, `spring-boot:run` is the main command to launch the app.

### Run app

```powershell
.\mvnw.cmd spring-boot:run
```

### Run with profile

```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=dev
```

### Run with application arguments

```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

### Run with debug JVM arguments

```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005"
```

Quick meaning:

- `profiles` selects Spring profile
- `arguments` passes app args like port
- `jvmArguments` passes JVM settings

## 20. How To Start a New REST API Project

### Option A: Using Spring Initializr

Official Spring guide points to:

```text
https://start.spring.io
```

Pick:

- Maven
- Java
- Spring Boot
- dependency: Spring Web

Then generate and open in IDE.

### Option B: Based on your existing projects

1. Copy an existing Spring Boot REST project folder.
2. Rename package and artifact if needed.
3. Update controller/service/model.
4. Run using Maven wrapper.

## 21. How To Use The Commands in Practice

### For `Spring Boot Web`

```powershell
cd "d:\Full Stack\Practice\Java, Spring, and Microservices\13.Spring REST API Using Spring Boot\Spring Boot Web"
.\mvnw.cmd spring-boot:run
```

Then open:

```text
http://localhost:8080/
http://localhost:8080/about
http://localhost:8080/login
```

### For `Spring MVC and Layers`

```powershell
cd "d:\Full Stack\Practice\Java, Spring, and Microservices\13.Spring REST API Using Spring Boot\Spring MVC and Layers"
.\mvnw.cmd spring-boot:run
```

Then open:

```text
http://localhost:8080/products
```

### For `Spring Web Http Methods GET and POST`

```powershell
cd "d:\Full Stack\Practice\Java, Spring, and Microservices\13.Spring REST API Using Spring Boot\Spring Web Http Methods GET and POST"
.\mvnw.cmd spring-boot:run
```

Use browser for `GET`.
Use Postman, curl, or Thunder Client for `POST`.

### For `Spring Web PUT and DELETE`

```powershell
cd "d:\Full Stack\Practice\Java, Spring, and Microservices\13.Spring REST API Using Spring Boot\Spring Web PUT and DELETE"
.\mvnw.cmd spring-boot:run
```

Use Postman, curl, or Thunder Client for `PUT` and `DELETE`.

## 22. `curl` Commands for Revision

### GET all

```powershell
curl http://localhost:8080/products
```

### GET one

```powershell
curl http://localhost:8080/products/101
```

### POST

```powershell
curl -X POST http://localhost:8080/products ^
  -H "Content-Type: application/json" ^
  -d "{\"prodId\":104,\"prodName\":\"Keyboard\",\"price\":2500}"
```

### PUT

```powershell
curl -X PUT http://localhost:8080/products ^
  -H "Content-Type: application/json" ^
  -d "{\"prodId\":101,\"prodName\":\"Updated Iphone\",\"price\":75000}"
```

### DELETE

```powershell
curl -X DELETE http://localhost:8080/products/101
```

## 23. Browser vs Postman vs curl

### Browser

Best for:

- `GET` requests
- quick URL testing

### Postman / Thunder Client

Best for:

- `POST`
- `PUT`
- `DELETE`
- JSON body testing

### curl

Best for:

- terminal-based testing
- quick command revision

## 24. Common Configuration You May Add

### Change port

```properties
server.port=8081
```

### Set app name

```properties
spring.application.name=simpleWebApp
```

### Enable a profile at run time

```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=dev
```

## 25. Important Rules for Model Classes

Model classes like `Product` should usually:

- contain fields
- have getters/setters
- often have no-args constructor
- not be marked with `@Component`

Why not `@Component`?

Because model classes are data holders, not service/controller beans.

If Spring tries to create a model bean with constructor parameters like `int`, startup errors can happen.

## 26. Common Mistakes

- forgetting `@RestController`
- using wrong URL path
- forgetting `@RequestBody` for JSON input
- forgetting `@PathVariable` for path parameters
- making model class a Spring bean by mistake
- returning nothing when you expected JSON
- not matching JSON field names with Java fields
- running project with wrong Java version
- forgetting to run from the project folder

## 27. Best Practice Memory Notes

- Keep controllers thin.
- Put business logic in service.
- Keep model classes simple.
- Use `GET` for read, `POST` for create, `PUT` for update, `DELETE` for delete.
- Prefer specific mappings like `@GetMapping` over generic `@RequestMapping` when teaching CRUD.

## 28. One-Minute Exam Revision

Remember this chain:

1. `@SpringBootApplication` starts the Boot app.
2. `@RestController` handles HTTP requests.
3. `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` define endpoints.
4. `@PathVariable` reads values from URL.
5. `@RequestBody` reads JSON into Java object.
6. Service layer contains logic.
7. Returned Java objects become JSON responses.

## 29. Folder-to-Concept Table

| Folder | Main Concept |
|---|---|
| `Spring Boot Web` | first REST controller and text endpoints |
| `Spring MVC and Layers` | controller, service, model layering |
| `Spring Web Http Methods GET and POST` | fetch and create operations |
| `Spring Web PUT and DELETE` | update and delete operations |
| `simpleWebApp` | extra Boot app reference |

## 30. Final Summary

Spring Boot REST API means:

- define endpoints with annotations
- accept HTTP requests
- send data as JSON
- organize code with layers
- run quickly with Maven wrapper

If you remember only one thing, remember this:

Spring Boot makes it easy to expose Java methods as web endpoints, accept JSON from clients, and return JSON back through clean controller-service-model structure.
