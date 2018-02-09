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
					"bankId": 101,
					"name": "Zachherinni",
					"accountNumber": "5784280195134310",
					"routingNumber": "ab56789",
					"accountType": "SAVINGS",
					"bankName": "XYZ"
				},
				"receiverDetails": {
					"bankId": 102,
					"name": "DeMichelis",
					"accountNumber": "3593359822843810",
					"routingNumber": "cd65432",
					"accountType": "SAVINGS",
					"bankName": "ABC"
				},
				"amount": 3500
			}
	- POST					
		 - http://<<host:port/path>>/transferfunds/XYZ/executeTransfer
			- incoming payload;
				{
					"accountNo": "7064689794255120",
					"amount": 500,
					"transactionType": "CREDIT"
				}