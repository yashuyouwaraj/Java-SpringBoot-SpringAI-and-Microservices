# Spring JDBC Cheat Sheet

One-place revision sheet for the `14.Spring JDBC` folder.

This sheet is based on:

- your local `SpringJDBCDemo` project
- official Spring documentation

Official references used:

- Spring Boot database initialization: https://docs.spring.io/spring-boot/4.1/how-to/data-initialization.html
- `JdbcTemplate` Javadoc: https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/jdbc/core/JdbcTemplate.html
- Spring SQL/H2 docs: https://docs.spring.io/spring-boot/4.1-SNAPSHOT/reference/data/sql.html
- Spring Boot Maven plugin run docs: https://docs.spring.io/spring-boot/maven-plugin/run.html

## 1. What Is JDBC?

JDBC = Java Database Connectivity.

It is the standard Java API used to connect Java applications to relational databases.

With plain JDBC, we usually do things like:

- open connection
- create statement
- run SQL
- process result set
- close resources

Problem with plain JDBC:

- lots of boilerplate code
- manual exception handling
- repetitive connection and result processing logic

## 2. What Is Spring JDBC?

Spring JDBC is Spring’s simplified way to work with databases using JDBC.

It helps by:

- reducing boilerplate
- managing database resource handling
- simplifying SQL execution
- converting SQL exceptions into Spring exceptions

Most important class:

- `JdbcTemplate`

Short idea:

Plain JDBC is low-level.
Spring JDBC makes low-level database work easier and cleaner.

## 3. What Is In Your Project?

Your `14.Spring JDBC` folder contains:

- one Spring Boot project: `SpringJDBCDemo`
- H2 in-memory database
- `JdbcTemplate`
- SQL scripts:
  - `schema.sql`
  - `data.sql`
- one repository:
  - `AlienRepo`
- one model:
  - `Alien`

This project teaches:

- how Spring Boot connects to a database
- how SQL tables are created automatically
- how data is inserted automatically
- how to insert data using `JdbcTemplate`
- how to read rows and map them to objects

## 4. How Spring JDBC Works

Basic flow in your project:

1. Spring Boot starts.
2. H2 database is created.
3. `schema.sql` runs and creates table.
4. `data.sql` runs and inserts initial rows.
5. Spring creates a `JdbcTemplate` bean automatically.
6. `AlienRepo` receives that `JdbcTemplate`.
7. Your code calls `save()` or `findAll()`.
8. SQL runs against the database.
9. Rows are mapped to Java objects.

## 5. Core Theory You Must Remember

### DataSource

`DataSource` represents database connection configuration.

It holds things like:

- database URL
- username
- password
- driver settings

Spring Boot usually auto-configures it for you.

### JdbcTemplate

`JdbcTemplate` is the heart of Spring JDBC.

It helps execute:

- `insert`
- `update`
- `delete`
- `select`

Common methods:

- `update(...)`
- `query(...)`
- `queryForObject(...)`

Official Spring docs describe `JdbcTemplate` as a central class for JDBC operations.

### RowMapper

When SQL returns rows, Spring needs to convert each row into a Java object.

That conversion is done using a `RowMapper`.

Your project uses a lambda row mapper:

```java
List<Alien> aliens = template.query(sql, (rs, row) -> {
    Alien a = new Alien();
    a.setId(rs.getInt(1));
    a.setName(rs.getString(2));
    a.setTech(rs.getString(3));
    return a;
});
```

### Repository

Repository layer is responsible for database operations.

In your project:

- `AlienRepo` is the repository

It contains:

- `save()`
- `findAll()`

## 6. Folder and File Revision Map

### `SpringJDBCDemo/pom.xml`

Use it to revise:

- Boot parent
- JDBC dependency
- H2 dependency
- Maven plugin

### `SpringJdbcDemoApplication.java`

Use it to revise:

- Boot startup
- getting beans from context
- calling repository methods

### `Alien.java`

Use it to revise:

- model/entity-like object
- fields + getters/setters
- bean scope idea

### `AlienRepo.java`

Use it to revise:

- `JdbcTemplate`
- insert query
- select query
- row mapping

### `schema.sql`

Use it to revise:

- table creation

### `data.sql`

Use it to revise:

- initial data insertion

### `application.properties`

Use it to revise:

- application configuration

## 7. Dependencies in Your Project

From your `pom.xml`, the important dependencies are:

### Spring JDBC starter

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
```

Why needed:

- provides Spring JDBC support
- gives `JdbcTemplate`
- configures database access support

### H2 database

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

Why needed:

- lightweight in-memory database
- great for practice and demos

### H2 console support

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-h2console</artifactId>
</dependency>
```

Why needed:

- allows H2 web console support during development

## 8. SQL Initialization Theory

Your project has:

- `schema.sql`
- `data.sql`

Official Spring Boot docs say Boot can load `schema.sql` and `data.sql` automatically for SQL database initialization.

### `schema.sql`

Used for table creation.

Your file:

```sql
create table alien(
    id int primary key,
    name varchar(50),
    tech varchar(20)
);
```

### `data.sql`

Used for inserting initial records.

Your file inserts:

- `Yashu`
- `Deepika`
- `Aman`

This means your database starts with sample data automatically.

## 9. Main Java Classes in Your Project

### Main Class

Your main class:

```java
ApplicationContext context = SpringApplication.run(SpringJdbcDemoApplication.class, args);
```

This starts Spring Boot and creates all beans.

Then your code does:

```java
Alien alien1 = context.getBean(Alien.class);
AlienRepo repo = context.getBean(AlienRepo.class);
```

And then:

```java
repo.save(alien1);
System.out.println(repo.findAll());
```

So the app:

- creates an `Alien`
- inserts it into DB
- fetches all rows
- prints them

### Model Class

Your `Alien` class has:

- `id`
- `name`
- `tech`

It is marked:

```java
@Component
@Scope("prototype")
```

Meaning in your current project:

- Spring can create `Alien` objects as beans
- `prototype` means a new object is created each time requested

Revision note:

For JDBC practice this works, but in many real applications model classes are plain POJOs and are not always Spring beans.

### Repository Class

Your repository is:

```java
@Repository
public class AlienRepo
```

Meaning:

- Spring treats it as data-access component
- it can use `JdbcTemplate`

## 10. Dependency Injection in Repository

Your project uses setter injection:

```java
@Autowired
public void setTemplate(JdbcTemplate template) {
    this.template = template;
}
```

Meaning:

- Spring creates `JdbcTemplate`
- Spring injects it into `AlienRepo`

What you should remember:

`JdbcTemplate` is auto-configured because of:

- JDBC starter
- database dependency

## 11. Insert Operation with `update()`

Your insert method:

```java
public void save(Alien alien) {
    String sql = "insert into alien (id,name,tech) values (?,?,?)";
    int rows = template.update(sql, alien.getId(), alien.getName(), alien.getTech());
}
```

Why `update()`?

In `JdbcTemplate`, `update()` is used for:

- insert
- update
- delete

It returns number of affected rows.

Revision memory:

- `update()` is not only for SQL `UPDATE`
- it is used for all write operations

## 12. Select Operation with `query()`

Your select method:

```java
public List<Alien> findAll() {
    String sql = "select * from alien";
    return template.query(sql, (rs, row) -> {
        Alien a = new Alien();
        a.setId(rs.getInt(1));
        a.setName(rs.getString(2));
        a.setTech(rs.getString(3));
        return a;
    });
}
```

Why `query()`?

Because multiple rows are expected.

Use:

- `query()` for list results
- `queryForObject()` for one row / one value

## 13. Row Mapping Theory

Database row:

```text
101 | Yashu | Full stack
```

Mapped into:

```java
Alien{id=101, name='Yashu', tech='Full stack'}
```

This conversion is the purpose of `RowMapper`.

In your lambda:

- `rs` = `ResultSet`
- `row` = row number

You can map:

- by column index
- by column name

Better readable version:

```java
a.setId(rs.getInt("id"));
a.setName(rs.getString("name"));
a.setTech(rs.getString("tech"));
```

## 14. How To Start a Spring JDBC Project

### Option A: Using Spring Initializr

Go to:

```text
https://start.spring.io
```

Choose:

- Maven
- Java
- Spring Boot

Add dependencies:

- Spring JDBC
- H2 Database

### Option B: Based on your current project

1. Create Spring Boot project.
2. Add JDBC starter.
3. Add H2.
4. Create model class.
5. Create repository class.
6. Inject `JdbcTemplate`.
7. Add `schema.sql`.
8. Add `data.sql`.
9. Run application.

## 15. How To Implement Spring JDBC

Simple implementation flow:

1. Add JDBC dependencies.
2. Add database dependency.
3. Configure DB in `application.properties` if needed.
4. Create SQL table.
5. Create model class.
6. Create repository.
7. Inject `JdbcTemplate`.
8. Write SQL queries.
9. Map rows to objects.
10. Run and test.

## 16. Commands To Run Your Project

Run these inside:

```text
d:\Full Stack\Practice\Java, Spring, and Microservices\14.Spring JDBC\SpringJDBCDemo
```

### Run with Maven Wrapper

```powershell
.\mvnw.cmd spring-boot:run
```

### Run tests

```powershell
.\mvnw.cmd test
```

### Clean and build

```powershell
.\mvnw.cmd clean package
```

### Run packaged jar

```powershell
java -jar target\SpringJDBCDemo-0.0.1-SNAPSHOT.jar
```

### If Maven is installed globally

```powershell
mvn spring-boot:run
mvn test
mvn clean package
```

## 17. Important Maven Commands

### `spring-boot:run`

Starts the Spring Boot app directly.

### `test`

Runs unit/integration tests.

### `clean`

Deletes previous build output.

### `package`

Builds jar file.

### `clean package`

Fresh build from scratch.

## 18. Useful Boot Run Variations

Based on the official Maven plugin docs:

### Run with custom port

```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.arguments="--server.port=8081"
```

### Run with profile

```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=dev
```

### Run with JVM arguments

```powershell
.\mvnw.cmd spring-boot:run -Dspring-boot.run.jvmArguments="-Xmx512m"
```

## 19. Configuration in `application.properties`

Your current file has:

```properties
spring.application.name=SpringJDBCDemo
```

Useful configs you may add for JDBC practice:

### H2 console enable

```properties
spring.h2.console.enabled=true
```

### Custom H2 console path

```properties
spring.h2.console.path=/h2-console
```

### Force SQL initialization

```properties
spring.sql.init.mode=always
```

### Manual H2 datasource config

```properties
spring.datasource.url=jdbc:h2:mem:yashudb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
```

Important note from Spring Boot docs:

By default, `schema.sql` and `data.sql` are commonly used for SQL initialization, especially with embedded databases like H2.

## 20. H2 Database Theory

H2 is a lightweight database mostly used for:

- learning
- testing
- demo apps

Why it is useful:

- no external install needed for many cases
- fast startup
- easy integration with Spring Boot

### H2 Console

Official Spring docs say H2 web console can be enabled with:

```properties
spring.h2.console.enabled=true
```

Default console path is usually:

```text
/h2-console
```

Example URL when running web app:

```text
http://localhost:8080/h2-console
```

Revision note:

H2 console is for development, not production.

## 21. SQL Commands in Your Project

### Create table

```sql
create table alien(
    id int primary key,
    name varchar(50),
    tech varchar(20)
);
```

### Insert row

```sql
insert into alien (id,name,tech) values (101, 'Yashu', 'Full stack');
```

### Select all

```sql
select * from alien;
```

### Update example

```sql
update alien set tech='Spring Boot' where id=101;
```

### Delete example

```sql
delete from alien where id=101;
```

## 22. CRUD Mapping with `JdbcTemplate`

### Create

```java
template.update("insert into alien (id,name,tech) values (?,?,?)", id, name, tech);
```

### Read all

```java
template.query("select * from alien", rowMapper);
```

### Read one

```java
template.queryForObject("select * from alien where id=?", rowMapper, id);
```

### Update

```java
template.update("update alien set name=?, tech=? where id=?", name, tech, id);
```

### Delete

```java
template.update("delete from alien where id=?", id);
```

## 23. Best Practice Structure

Good JDBC project structure:

```text
src/main/java/com/yashu/SpringJDBCDemo
|-- SpringJdbcDemoApplication.java
|-- model
|   |-- Alien.java
|-- repository
|   |-- AlienRepo.java
```

And:

```text
src/main/resources
|-- application.properties
|-- schema.sql
|-- data.sql
```

## 24. Common Mistakes

- forgetting JDBC starter dependency
- forgetting database dependency
- wrong SQL table name
- wrong column order in row mapping
- forgetting primary key uniqueness
- using `queryForObject()` when multiple rows may come
- marking every model as Spring bean even when not needed
- not enabling H2 console when trying to open it manually
- assuming external DB config is same as embedded H2 config

## 25. Interview / Viva Questions

### What is Spring JDBC?

A Spring module that simplifies database access using JDBC.

### What is `JdbcTemplate`?

Central Spring JDBC class for executing SQL and handling JDBC boilerplate.

### What is `RowMapper`?

An interface used to map one row of a SQL result set to one Java object.

### What is H2?

A lightweight in-memory relational database used for development and testing.

### What is the use of `schema.sql`?

To create database schema or tables.

### What is the use of `data.sql`?

To insert initial/sample data.

### Difference between plain JDBC and Spring JDBC?

Plain JDBC needs manual resource management.
Spring JDBC reduces boilerplate and simplifies SQL execution.

## 26. One-Minute Revision

Remember this chain:

1. Add JDBC and H2 dependencies.
2. Spring Boot auto-configures datasource and `JdbcTemplate`.
3. `schema.sql` creates tables.
4. `data.sql` inserts initial records.
5. Repository uses `JdbcTemplate`.
6. `update()` handles insert/update/delete.
7. `query()` reads many rows.
8. `RowMapper` converts rows into Java objects.

## 27. Your Project Summary

Your `SpringJDBCDemo` project demonstrates:

- Spring Boot JDBC setup
- H2 database usage
- SQL initialization with scripts
- inserting one `Alien`
- fetching all rows using `JdbcTemplate`

That makes it a very good first Spring JDBC revision project.
