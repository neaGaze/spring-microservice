As an ACH Department, we want to be able to check the status of the transaction at any phase. Here is the functionality of this microservice:
- Check the Status of the microservice


PRE-REQUISITES
--------------------------------------------
2. Need the Transaction table mounted database 


HOW TO COMPILE
--------------------------------------------
In Eclipse:
1. Import the project folder named "ach" as "Existing Maven Project".
2. Change the `spring.datasource.username` and `spring.datasource.password` according to your local system. Check if the port is not occupied
3. Run the project as `maven compile`

In Docker:
1. Goto the parent directory `cd ..`
2. Run the command `docker-compose build` to build the images
3. Run the command `docker-compose up` to run the containers in a bulk

REQUEST
--------------------------------------------

api
  - GET
		- http://<<host:port/path>>/stargate-status-query/getstatus/check-status?transaction_id=cebdfecf-6054-495e-8dd6-51287756ad61
		  

