package Data;

import java.util.ArrayList;

import Models.User;

public interface IUserRepository {
	Boolean InsertUser(User user);
	User UpdateUser(User user);
	Boolean DeleteUserbyId(int id);
	User GetUserById(int id);
	User GetUserByPhoneNumber(String phoneNumber);
	User GetUserByUserNameandPassword(String userName,String password);
	Boolean UpdateUserBalancebyId(int Id,double Amount);
	ArrayList<User> GetAllUser();
}
