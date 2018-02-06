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

REQUEST
--------------------------------------------
For details of payload and JSON format please refer to the RAML definition at 'http://gitlab.capgemini-cfs.com/na-stargate/Mule-ACH-API'

api
  - POST
		- http://<<host:port/path>>/transferfunds/1/initiateTransfer
		  - incoming payload: 
			  {
				"senderDetails": {
					"name": "Joe",
					"accountNumber": "123456",
					"routingNumber": "ab56789",
					"accountType": "SAVINGS",
					"bankName": "XYZ"
				},
				"receiverDetails": {
					"name": "Gabber",
					"accountNumber": "123456789",
					"routingNumber": "cd65432",
					"accountType": "SAVINGS",
					"bankName": "ABC"
				},
				"amount": 1000
			}
	- POST					
		 - http://<<host:port/path>>/transferfunds/XYZ/executeTransfer
			- incoming payload;
				{
					"account": {
						"bankId": 101,
						"name": "Joe",
						"accountNumber": 123456,
						"routingNumber": "ab56789",
						"accountType": "SAVINGS",
						"bankName": "XYZ"
					},
					"amount": 1000,
					"transactionType": "DEBIT"
				}