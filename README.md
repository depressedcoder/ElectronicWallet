# ElectronicWallet 
This is a desktop app where users can transfer money between users, recharge balance to their account, and cash out from their account. This is a simple project based on a complete oop
structure. It is implemented using java and swing UI and MySQL is used for the database. 

User Login Registration:

When the project runs it shows users to log in or register their profile. First, a user should register their profile by clinking register. It will open the registration panel. There are some mandatory fields to fill up like UserName, Name, Password, PhoneNumber. If the required field is not filled it shows alert through message dialog to the user. For registration, the Phonenumber must be consist of 11 digits no letter is allowed, and also the Phone number should be unique, if the PhoneNumber is used by another user it will not let the user to register hence shows an alert for giving a unique phone number. After filling up the registration form the user is registered but currently 'Blocked' for admin review. When the admin reviews and approved the user, it sets the user status to 'Active' and gives bonus money of 100.0, and only then the user can log in.

In the Login panel when a user puts their credentials and clicks login it checks in the DB with the valid user having status 'Active' and sends a response. If the user is not valid or 'Blocked' it shows the respective message through message dialog. By giving all the right credentials with user status 'Active' the system allows the user to let in the user profile panel. Here users can transfer money between users, request for recharge balance, request for cash-out balance, edit their profile, and see all the pending recharge/cash-out requests and also transaction data.

Transfer money between Users:

For sending money the user should search the receiver with phone number. If the receiver is not found it shows in the message dialog. if found, it fills up the receiver name and phone number. For transferring money user need to put a valid amount and not greater than the balance amount. if the user does that it will show a respective message through dialog. Users also need to fill the Remarks field and send the money. After sending, the amount will be deducted from the sender and will be added to the user directly and shows up in the transaction history with date and time.

Recharge/Cashout Request:

For placing Recharge Balance Request the user needs to go Recharge/Cash-Out panel by clicking on the 2nd tab. For recharge request users need to put a valid number else it will show an error message. After giving a valid amount user can place the recharge request and wait for the admin approval. After getting the admin approval the requested amount is added to the user balance and also in the transaction history table. For doing a cash-out the user also needs to place a valid amount and less or equal to the current balance then the user can place a cashout request and wait for the admin approval. While placing the cashout request if there is already pending cashout requests which is greater than the current balance of the user, the system will not allow to add more cashout request and give a respective message. when the cashout request is approved by the admin the amount is deducted from user balance and appears in the transaction history.

Admin Panel:

There are two types of User 'Admin' & 'User'. In the login section if the user type is 'Admin' it will open the admin panel. In the admin panel, Admin can see all the user lists, Transaction history, and Recharge and Cashout pending requests. Admin can Block, Activate or Delate a user also approved or delete pending Recharge or cashout request. There is no Admin creation panel, admin can be added directly from the database with a UserType equal 'Admin'.

OOP Design: 
There are three class named User, Transaction, and UserRequest in the model folder. Every model class has some attributes and some setter and getter methods for the attributes by that we achieved the encapsulation in oop. User class is inherited by the UserRequest and override some methods like user name, phone number. There are three interfaces named IUserRepository, ITransaction, IUserRequest, and their class named UserRepository, TransactionRepository, and UserRequestRepository in the Data folder. There are some methods declared in the interface which is implemented in these three repository classes. In this interface, there are some methods with the same name but different parameters that are implemented in the repository class by overriding. By that, we achieved polymorphism. The user who wants to use the methods of those interfaces only knows the classes that implement those interfaces and their methods, information about the implementation is completely hidden from the user, thus achieving 100% abstraction.


