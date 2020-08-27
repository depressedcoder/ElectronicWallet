# ElectronicWallet 
This is a desktop app where user can transfer money between users, recharge balance to their account and cash out from their account. This is a siimple project based on complete oop
structure. It is implemented using java and swing ui and mysql is used for the database. 

When the project run it shows user to login or register their profile. First a user should register their profile by clinking register. It will open the registration panel. There are some mendatory fields to fill up like UserName,Name,Password,PhoneNumber. If the require field is not filles it show alert through message dialog to the user. For registration the Phonenumber must me consist of 11 digits no letter is alowed and also the Phone number should be unique, if the PhoneNumber is used by other user it will not let the user to register hence shows a alart for giving a unique phone number. After filling up the Registration form the user is registerd but currently 'Blocked' for admin review. When the admin reviews and approved the user, it sets the user status to 'Active' and bonus money of 100.0 and only then the user can login.

In the Login panel when a user put their credentials and clicks login it checks in the db with valid user with status 'Active' and send response. If the user is not valid or 'Blocked' the it shows respective message through message dialog. By giving all the right credentials with user status 'Active' the system allows the use to let in the user profile panel. Here user can transfer money between users,request for recharge balance,request for cash out balance,edit their profile and see all the pending recharge/cash out request and also transaction data.

For sending money the user should search the receiver with phone number. If the receiver not found it shows in the message dialog. if found, it fill up the receiver name and phone number. For transfering money user need to put valid number and not greater than the balance amount. if the user does that it will show respetive message through dialog. User also need to fill the Remarks field and send the money. After sending, the amount will be deducted from the sender and will be added to the user directly and shows up in the transaction history with date and time.

For placing Recharge Balance Request the user need to go Recharge/Cash out area by clicking 

There are three tables User,Transaction and UserRequest.

not completed...
