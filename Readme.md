This is Simple demo for building the API using Spring Boot.

1. Add Customer and create there account
   http://localhost:8080/customer/add
   
   Sample Body : 
   {
	"customerName":"Urvin"
   }
   
2. Get All Customers details
   http://localhost:8080/customer/

3. Get Customer's account Details
   http://localhost:8080/customer/1
   Sample Request Body :
   {
    "accountId": 2,
    "custId": 1,
    "balance": 500
   }
   

4. Perform Account transaction either Deposit or Withdraw
   http://localhost:8080/customer/transaction
   
   Deposit:
   {
	"custId":1,
	"accountId":2,
	"transactionType":"DEPOSIT",
	"txnAmount":1000
   }
   Withdraw:
   {
	"custId":1,
	"accountId":2,
	"transactionType":"WITHDRAW",
	"txnAmount":500
	}