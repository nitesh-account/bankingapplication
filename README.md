# bankingapplication

Introduction:
Developed Banking application API's used for creating, inquire, update and delete operations for customer and account. Also, provides deposit and withdrawal operations.
Customer create and inquire API is integrated with React JS.

Requirement:
Phase 1:

Develop a spring boot MVC application that provides services for basic banking transactions like
 Create customer
 Inquire customer
 Create Account
 Deposit cash
 Withdraw cash
 Close Account
 Inquire Account

Perform basic CRUD operations for the above services using the ORM of your choice. We suggest you to use embedded database of your choice available in spring boot.
Consider the list of fields mentioned below are mandatory for each of the service, you are
encouraged to add more fields for each service you think are suitable,

Customer: Id, name
Account: Account Type, Account number (Auto generated), Account status (“Active” if open,
“Closed” is the account is closed)
Deposit: Account number, Amount
Withdraw: Account number, Amount
Close Account: Account Number (Update the account status)
Inquire Account: Account Number (return the name and id of the account holder, status of the
account)

Entity relationships are to be developed based on the understanding of the requirement.
