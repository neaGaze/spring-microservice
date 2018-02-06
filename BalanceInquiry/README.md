This is the balance inquiry microservice powered by Spring Boot in Java and MySQL database server with JDBC connection


HOW TO COMPILE
--------------------------------------------
In Eclipse:
1. Import the project folder named "getbalance" as "Existing Maven Project".
2. Change the `spring.datasource.username` and `spring.datasource.password` according to your local system. Check if the port is not occupied
3.  Run the project as `maven compile`

REQUEST
--------------------------------------------
For details of payload and JSON format please refer to the RAML definition at 'http://gitlab.capgemini-cfs.com/na-stargate/Mule-ACH-API'

api
 - GET
	- http://<<host:port/path>>/getbalance/getbalance/find?account_no=1
