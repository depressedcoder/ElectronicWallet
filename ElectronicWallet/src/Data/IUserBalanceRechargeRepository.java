package Data;

import java.util.ArrayList;

import Models.UserBalanceRecharge;

public interface IUserBalanceRechargeRepository {
	Boolean InsertUserBalanceRecharge(UserBalanceRecharge ubr);
	Boolean UpdateRechargeStatusByIdandStatusType(int id,String Status);
	Boolean DeleteUserBalanceRechargeById(int id);
	UserBalanceRecharge GetUserBalanceRechargeById(int Id);
	ArrayList<UserBalanceRecharge> GetAllUserBalanceRecharge();
	ArrayList<UserBalanceRecharge> GetAllUserBalanceRecharge(int UserId,String RechargeStatus); // method overloading
	
	ArrayList<UserBalanceRecharge> GetAllUserBalanceRechargeByRechargeStatus(String RechargeStatus); 
}
