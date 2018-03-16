As an ACH Department, we want to be able to execute orchestrate the execution of ACH Payment. This microservice has 3 functionalities:
- Persist the `BLTransaction` data in the database
- Co-ordinator Debit transaction of the originating bank
- Co-ordinator Credit transaction of the receiving bank


PRE-REQUISITES
--------------------------------------------
1. Need to have US-2 mule endpoint up and running
2. Need the Transaction table mounted database 

HOW TO COMPILE
--------------------------------------------
In Eclipse:
1. Import the project folder named "ach" as "Existing Maven Project".
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
		- http://<<host:port/path>>/ach/persist
		  - incoming payload: 
			{
				"from": "2413116579431930",
				"destination": "7064689794255120",
				"amount": 100
			}

