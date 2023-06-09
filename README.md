# Java Servlet RESTful API Template

This program is a simple template for RESTful API CRUD application in Java Servlet.

## Tech Used in Development :
- Java 17
- Apache Tomcat 9
- PostgreSQL

## How To Run this Program :
- You need to download Apache Tomcat from its [official website](https://tomcat.apache.org/download-90.cgi)
- Setup Apache Tomcat for your IDE, for certain IDEs, you can follow this tutorial :
  - [IntelliJ Ultimate Edition](https://youtu.be/ThBw3WBTw9Q)
  - [IntelliJ Community Edition](https://youtu.be/JIRDMGJ66SE) (using Smart Tomcat plugin)
  - [Eclipse](https://youtu.be/PH-bK3g2YmU)
- Run your program from your IDE

## What is Inside This Program
We have 5 CRUD example APIs in this program

| URL                                     | Method | Description               | Request Body                                                                                           |
|-----------------------------------------|--------|---------------------------|--------------------------------------------------------------------------------------------------------|
| http://localhost:8080/RestDemo_war      | GET    | Get list of items         | -                                                                                                      |
| http://localhost:8080/RestDemo_war/{id} | GET    | Get item by its id        | -                                                                                                      |
| http://localhost:8080/RestDemo_war      | POST   | Insert new item           | <pre lang="json">{<br>  "name": "Biskuit Coklat",<br>  "itemNumber": 50,<br>  "price": 5000<br>}</pre> |
| http://localhost:8080/RestDemo_war/{id} | PUT    | Update existed item       | <pre lang="json">{<br>  "name": "Biskuit Coklat",<br>  "itemNumber": 20,<br>  "price": 5000<br>}</pre> |
| http://localhost:8080/RestDemo_war/{id} | DELETE | Soft delete existed item  | -                                                                                                      |


Happy Learning :wink: