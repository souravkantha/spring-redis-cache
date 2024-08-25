# spring-redis-cache
Application showcasing simple write through cache implementation using spring-redis module and jedis client

```@Cacheable(value = "product", key = "#productId")```

```@CachePut(value = "product", key = "#productId")```

  ```@CacheEvict(cacheNames="product", key="#productId") ```
  
  Read more about :- https://docs.spring.io/spring-data/data-redis/docs/current/reference/html/

![image](https://github.com/souravkantha/spring-redis-cache/assets/32014166/89e2cd91-e9cf-4af4-99e3-70bc0aca2749)

## Java Version
Used open-jdk **version 18** (https://openjdk.org/projects/jdk/18/)

## Redis Installation 
https://redis.io/docs/getting-started/installation/

Upon successful redis installation and running standalone redis, you should be able to view below screenshot

<img width="1170" alt="Screenshot 2023-05-11 at 2 01 30 AM" src="https://github.com/souravkantha/spring-redis-cache/assets/32014166/f76f484c-a578-441d-bc45-2d0f60d07562">


## Build Tool
### We are using maven tool to build the project. You can download & install maven from official website (https://maven.apache.org/install.html)

## Making the build
### Download the source code using git ssh or https from https://github.com/souravkantha/spring-redis-cache

#### - [x] **Check if maven is installed by putting 'mvn' command in command prompt or terminal**
* Go the root directory i.e; **spring-redis-cache**
* Use command ```mvn clean package```. After the build is successful, run the program using following command

     ```java -jar target/cache-1.0.jar```
 
Upon successful start of the service, you should see the h2 database console

Open h2 console : http://localhost:8080/h2-console/login.jsp
    
<img width="462" alt="Screenshot 2023-05-10 at 11 44 29 PM" src="https://github.com/souravkantha/spring-redis-cache/assets/32014166/65ce330f-7ad6-40c9-848e-324b35917d06">


<img width="1119" alt="Screenshot 2023-05-10 at 11 56 25 PM" src="https://github.com/souravkantha/spring-redis-cache/assets/32014166/035f5634-7347-4d21-94d0-583830328994">

## Test data added using data.sql (in resources directory)

```
INSERT INTO ITEMS VALUES (10000, 'Kelloggs Crunchy Granola Almonds and Cranberries', 'Kelloggs Crunchy Granola Almonds and Cranberries','Breakfast Cereal', 330, 1, 1);    
INSERT INTO ITEMS VALUES (10001, 'Kelloggs Crunchy Granola Chocolate & Almonds', 'Kelloggs Crunchy Granola Chocolate & Almonds', 'Breakfast Cereal', 350, 1, 1);    
INSERT INTO ITEMS VALUES (10002, 'Kelloggs muesli', 'Kelloggs muesli','Breakfast Cereal', 270, 1, 1);    
INSERT INTO ITEMS VALUES (10003, 'Kelloggs Chocos', 'Kelloggs Chocos','Breakfast Cereal', 350, 1, 1);    
INSERT INTO ITEMS VALUES (10004, 'Kelloggs Corn Flakes Real Almond Honey', 'Kelloggs Corn Flakes Real Almond Honey', 'Breakfast Cereal', 195, 1, 1); 
```

## To test using curl 
### Create Product (using 
 ````curl --location --request POST 'http://localhost:8080/products' --header 'Content-Type: application/json' --data-raw '{"productId": 20000,"productName": "Kelloggs Crunchy Granola", "productDescription": "Kelloggs Crunchy Granola Almonds and Cranberries","type": "Breakfast Cereal","price": 330,"quantity": 1,"version": 1}' ````

#### Check with redis-cli after new product creation (cache is added for key product::20000)
<img width="176" alt="Screenshot 2023-05-11 at 6 33 39 PM" src="https://github.com/souravkantha/spring-redis-cache/assets/32014166/200c89a3-c43e-4796-80ba-a4d9a6e248ac">


### Update Product
```` curl --location --request PATCH 'http://localhost:8080/products/20000' --header 'Content-Type: application/json' --data-raw '{"productId": 20000,"productName": "Kelloggs Crunchy Granola Almonds 1 Kg","productDescription": "Kelloggs Crunchy Granola Almonds and Cranberries","type":"Breakfast Cereals","price": 330,"quantity": 1,"version": 1}' ````

### Get Product
````curl --location --request GET 'http://localhost:8080/products/20000' ````

### Delete Product
````curl --location --request DELETE 'http://localhost:8080/products/20000'````

#### Open redis-cli 
````redis-cli````

#### Check with redis-cli after product deletion (cache is evicted for key product::20000)
<img width="178" alt="Screenshot 2023-05-11 at 6 37 04 PM" src="https://github.com/souravkantha/spring-redis-cache/assets/32014166/a5bc6d43-a3e8-4dfe-b057-3d36bf073da4">






   
