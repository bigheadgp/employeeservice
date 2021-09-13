Spring Data JPA它提供了极简的方式操作数据库，但有时候仍然需要MyBatis的灵活性来执行自由度极高的手工编写的SQL。这里通过在Spring Data JPA中配置MyBatis，来达到两者共存的方式操作数据库。

# 一、Spring Data JPA的配置

1、pom.xml中配置好依赖项。

```xml

<dependencies>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter</artifactId>
        <version>2.2.0</version>
    </dependency>
    <dependency>
        <groupId>org.mybatis.spring.boot</groupId>
        <artifactId>mybatis-spring-boot-starter-test</artifactId>
        <version>2.2.0</version>
    </dependency>
</dependencies>

```

2、application.properties中配置 data jpa 以及 mybatis

```properties
# Spring Data JPA Config (with hibernate)
spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.hibernate.ddl-auto = none
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl


# Mybatis Config
mybatis.config-location = classpath:mybatis/mybatis-config.xml
mybatis.mapper-locations = classpath:mybatis/mapper/*Mapper.xml
mybatis.type-aliases-package = com.example.employee.dao
logging.level.com.example.employee.dao.mapper = debug
```

# 二、MyBatis的配置

1、pom.xml和application.properties的配置在之前的JPA部分已经写过了。同时创建 *Mapper.xml, mybatis-config.xml 不再赘述，然后开始自定义Mapper接口类

```java
@Mapper
public interface EmployeeMapper {

    List<Employee> selectEmployeeByBirthdateBetween(Date from, Date to);

}

```

2、SpringBootApplication 类中加入对自定义 Mapper 的扫描

```java
@SpringBootApplication(scanBasePackages = {"com.example.employee"})
@MapperScan("com.example.employee.dao.mapper")
public class EmployeeApplication {}
```

到这里，MyBatis的配置就完成了。接下来开始把 MyBatis 的 Repository 配置到JPA中。

三、在JPA中加入MyBatis的功能（合并）

1、自定义 mybatis repository, MybatisEmployeeRepository 并且在MybatisEmployeeRepositoryImpl 中使用 EmployeeMapper 实现它

```java
@Repository
public interface MybatisEmployeeRepository {

    List<Employee> findEmployeeByBirthdayBetween(Date from, Date to);

}
```

2、在我们之前自定义的JPA repository EmployeeRepository类中，继承 MybatisEmployeeRepository 接口

```java
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, MybatisEmployeeRepository {

    public Employee findByName(String name);

    List<Employee> findEmployeeByBirthdayBetween(Date from, Date to);

}
```

到此为止，在Spring Data JPA中配置MyBatis就完成了。

接下来开始集成测试。


# 三、测试

1、使用 application-test.properties 以及 在测试类里面加入 @@ActiveProfiles("test") 来使测试时使用 h2 内存数据库

```properties

spring.datasource.driverClassName = org.h2.Driver
spring.datasource.url = jdbc:h2:mem:testdb
spring.datasource.username = sa
spring.datasource.password = password
spring.h2.console.enabled = true

spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto = create-drop

```

```java

@ActiveProfiles("test")
public class EmployeeMapperIntegrationTest {}

```

2、测试 EmployeeMapper

使用 @DataJpaTest 以便初始化 jpa 测试环境，以便能够注入 TestEntityManager 来做一些测试数据的初始化。
使用 @AutoConfigureMybatis 来初始化 mybatis 的 SqlSessionFactory 等，从而使 EmployeeMapper 能够正常使用 

```java
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureMybatis
@ActiveProfiles("test")
public class EmployeeMapperIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    EmployeeMapper employeeMapper;
    
    //......
}


```


3、测试 MybatisEmployeeRepository

使用 @DataJpaTest 以便初始化 jpa 测试环境，以便能够注入 TestEntityManager 来做一些测试数据的初始化。
使用 @AutoConfigureMybatis 来初始化 mybatis 的 SqlSessionFactory 等，从而使 EmployeeMapper 能够正常使用


4、测试 JPA Repository EmployeeRepository

因为 JPA Repository 加入了对 Mybatis Repository 的依赖，所以在@DataJpaTest 之外还需要加入 @AutoConfigureMybatis 以便能够正常注入 @EmployeeRepository

```java
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureMybatis
@ActiveProfiles("test")
public class EmployeeRepositoryIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    //...
}
```