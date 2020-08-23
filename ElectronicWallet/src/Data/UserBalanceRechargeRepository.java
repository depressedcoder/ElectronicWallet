package Data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.nit.instance.DatabaseConnection;

import Models.Transaction;
import Models.User;
import Models.UserBalanceRecharge;

public class UserBalanceRechargeRepository implements IUserBalanceRechargeRepository{

	private Connection con;
	private DatabaseConnection dbc;
	
	public UserBalanceRechargeRepository() {
		dbc = DatabaseConnection.getDatabaseConnection();
		con = dbc.getConnection();
	}
	
	@Override
	public Boolean InsertUserBalanceRecharge(UserBalanceRecharge ubr) {
		try {
			if(ubr!=null) {
				Statement smt =  con.createStatement();
				String query = "INSERT INTO `UserBalanceRecharge`(`UserId`, `RechargeAmount`, `RechargeStatus`) VALUES "
						+ "("+ubr.getUserId()+","+ubr.getRechargeBalance()+",'"+ubr.getRechargeStatus()+"')";
				
				smt.execute(query);
				return true;
			}
		}catch(Exception ex)
		{
			System.out.println("Couldn't Inserted the Recharge Info for "+ex);
		}
		return false;
	}

	@Override
	public Boolean UpdateRechargeStatusByIdandStatusType(int id,String Status) {
		try {
			if(id!=0) {
				Statement smt =  con.createStatement();
				String query = "update UserBalanceRecharge set RechargeStatus= '"+Status+"' where Id = "+id;
				smt.execute(query);
				return true;
			}
		}catch(Exception ex)
		{
			System.out.println("Couldn't Updated the User Recharge Status for "+ex);
		}
		return false;
	}

	@Override
	public Boolean DeleteUserBalanceRechargeById(int Id) {
		try {
			if(Id!=0) {
				Statement smt =  con.createStatement();
				String query = "DELETE FROM `UserBalanceRecharge` WHERE Id="+Id;
				smt.execute(query);
				return true;
			}
		}catch(Exception ex)
		{
			System.out.println("Couldn't Delete the UserBalanceRecharge for "+ex);
		}
		return false;
	}

	@Override
	public UserBalanceRecharge GetUserBalanceRechargeById(int Id) {
		try {
			if(Id!=0) {
				Statement stmt = con.createStatement();
				String sql = "Select * from UserBalanceRecharge where Id = "+Id;
				ResultSet rs = stmt.executeQuery(sql);
				UserBalanceRecharge ubrData = new UserBalanceRecharge();
				 
				if(rs.next()) {
					ubrData.setId(rs.getInt(1));
					ubrData.setUserId(rs.getInt(2));
					ubrData.setRechargeBalance(rs.getDouble(3));
					ubrData.setRechargeDate(rs.getTimestamp(4));
					ubrData.setRechargeStatus(rs.getString("RechargeStatus"));
					 return ubrData;
				 }
			}
		}catch (Exception e) {
			System.out.println("Couldn't get Data for "+e);
		}
		return null;
	}

	@Override
	public ArrayList<UserBalanceRecharge> GetAllUserBalanceRecharge() {
		ArrayList<UserBalanceRecharge> listOfUserBalanceRecharge = new ArrayList<UserBalanceRecharge>();
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT UserBalanceRecharge.Id as Id,"
					+ "UserId,"
					+ "RechargeAmount,"
					+ "RechargeDate,"
					+ "RechargeStatus,"
					+ "user.Name as Name,"
					+ "user.PhoneNumber as PhoneNumber"
					+ " FROM UserBalanceRecharge\n" + 
					"left join User as user on user.Id = UserBalanceRecharge.UserId";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				UserBalanceRecharge userBalanceRecharge = new UserBalanceRecharge(rs.getInt(1), rs.getInt(2), rs.getDouble(3),rs.getTimestamp(4),rs.getString("RechargeStatus"),
						rs.getString("Name"),rs.getString("PhoneNumber"));
				listOfUserBalanceRecharge.add(userBalanceRecharge);
			}
		}catch(Exception e) {
			System.out.println("Couldn't get List of UserBalanceRecharge for "+e);
		}
		return listOfUserBalanceRecharge;
	}

	@Override
	public ArrayList<UserBalanceRecharge> GetAllUserBalanceRecharge(int UserId,String RechargeStatus) {
		ArrayList<UserBalanceRecharge> listOfUserBalanceRecharge = new ArrayList<UserBalanceRecharge>();
		try {
			Statement stmt = con.createStatement();
			String sql = "Select UserBalanceRecharge.Id as Id,"
					+ "UserId,"
					+ "RechargeAmount,"
					+ "RechargeDate,"
					+ "RechargeStatus,"
					+ "user.Name as Name,user.PhoneNumber as PhoneNumber"
					+ " from UserBalanceRecharge left join User as user on user.Id = UserBalanceRecharge.UserId "
					+ "where RechargeStatus = '"+RechargeStatus+"' and UserId = "+UserId+" ORDER by RechargeDate DESC";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				UserBalanceRecharge userBalanceRecharge = new UserBalanceRecharge(rs.getInt(1), rs.getInt(2), rs.getDouble(3),rs.getTimestamp(4),rs.getString("RechargeStatus"),
						rs.getString("Name"),rs.getString("PhoneNumber"));
				listOfUserBalanceRecharge.add(userBalanceRecharge);
			}
		}catch(Exception e) {
			System.out.println("Couldn't get List of UserBalanceRecharge for "+e);
		}
		return listOfUserBalanceRecharge;
	}

	@Override
	public ArrayList<UserBalanceRecharge> GetAllUserBalanceRechargeByRechargeStatus(String RechargeStatus) {
		ArrayList<UserBalanceRecharge> listOfUserBalanceRecharge = new ArrayList<UserBalanceRecharge>();
		try {
			Statement stmt = con.createStatement();
			String sql = "Select UserBalanceRecharge.Id as Id"
					+ ",UserId,"
					+ "RechargeAmount,"
					+ "RechargeDate,"
					+ "RechargeStatus,"
					+ "user.Name as Name,"
					+ "user.PhoneNumber as PhoneNumber"
					+ " from UserBalanceRecharge left join User as user on user.Id = UserBalanceRecharge.UserId"
					+ " where RechargeStatus = '"+RechargeStatus+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				UserBalanceRecharge userBalanceRecharge = new UserBalanceRecharge(rs.getInt(1), rs.getInt(2), rs.getDouble(3),rs.getTimestamp(4),rs.getString("RechargeStatus"),
						rs.getString("Name"),rs.getString("PhoneNumber"));
				listOfUserBalanceRecharge.add(userBalanceRecharge);
			}
		}catch(Exception e) {
			System.out.println("Couldn't get List of UserBalanceRecharge for "+e);
		}
		return listOfUserBalanceRecharge;
	}

}
