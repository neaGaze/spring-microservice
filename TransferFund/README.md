This microservice will work with between the customer and ACH to debit/credit the transactions. It should reside within the Mule application.

PRE-REQUISITES
--------------------------------------------
1. Need to have activemq server running. Please refer this website(http://javasampleapproach.com/spring-framework/spring-jms/apache-artemis-produceconsume-jms-messages-springboot-artemis-applications#1_Create_SpringBoot_projects) for downloading and installing the artemis server to work on the JMS in your local system


HOW TO COMPILE
--------------------------------------------
In Eclipse:
1. Import the project folder named "getbalance" as "Existing Maven Project".
2. Change the `spring.datasource.username` and `spring.datasource.password` according to your local system. Check if the port is not occupied
3.  Run the project as `maven compile`


In Docker:
1. Goto the parent directory `cd ..`
2. Run the command `docker-compose build` to build the images
3. Run the command `docker-compose up` to run the containers in a bulk

REQUEST
--------------------------------------------
For details of payload and JSON format please refer to the RAML definition at 'http://gitlab.capgemini-cfs.com/na-stargate/Mule-ACH-API'

api
  - POST
		- http://<<host:port/path>>/stargate-funds/debit
		  - incoming payload: 
			{
				"id": "cebdfecf-6054-495e-8dd6-51287756ad61",
				"from": "2413116579431930",
				"destination": "7064689794255120",
				"amount": 100
			}
	- POST					
		- http://<<host:port/path>>/stargate-funds/credit
		  - incoming payload: 
			{
				"id": "cebdfecf-6054-495e-8dd6-51287756ad61",
				"from": "2413116579431930",
				"destination": "7064689794255120",
				"amount": 100
			}