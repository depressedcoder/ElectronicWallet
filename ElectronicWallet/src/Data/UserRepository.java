package Data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.nit.instance.DatabaseConnection;

import Models.User;

public class UserRepository implements IUserRepository{

	private Connection con;
	private DatabaseConnection dbc;
	public UserRepository() {
		dbc = DatabaseConnection.getDatabaseConnection();
		con = dbc.getConnection();
	}
	@Override
	public Boolean InsertUser(User user) {
		try {
			if(user!=null) {
				Statement smt =  con.createStatement();
				String query = "insert into User " + " (UserName, Password, Name, PhoneNumber, Address, Balance, Gender, Status,UserType)"
	                    + " values ('"+user.getUserName()+"', '"+user.getPassword()+"', '"+user.getName()+"','"+user.getPhoneNumber()+"',"
	                    		+ " '"+user.getAddress()+"', "+user.getBalance()+",'"+user.getGender()+"','"+user.getStatus()+"','"+user.getUserType()+"')";
				
				smt.execute(query);
				return true;
			}
		}catch(Exception ex)
		{
			System.out.println("Couldn't Inserted the User Info for "+ex);
		}
		return false;
	}

	@Override
	public User UpdateUser(User user) {
		try {
			if(user!=null) {
				Statement smt =  con.createStatement();
				String query = "UPDATE `User` SET `UserName`='"+user.getUserName()+"',"
						+ "`Password`='"+user.getPassword()+"',`Name`='"+user.getName()+"',`Gender`='"+user.getGender()+"',"
						+ "`PhoneNumber`='"+user.getPhoneNumber()+"',"
								+ "`Address`='"+user.getAddress()+"' "
										+ "WHERE Id = "+user.getUserId();
				smt.execute(query);
				return this.GetUserById(user.getUserId());
			}
		}catch(Exception ex)
		{
			System.out.println("Couldn't updated the data for "+ex);
		}
		return null;
	}

	@Override
	public Boolean DeleteUserbyId(int id) {
		try {
			if(id!=0) {
				Statement smt =  con.createStatement();
				String query = "DELETE FROM `User` WHERE Id="+id;
				smt.execute(query);
				return true;
			}
		}catch(Exception ex)
		{
			System.out.println("Couldn't Delete the User for "+ex);
		}
		return false;
	}

	@Override
	public User GetUserById(int id) {
		try {
			if(id!=0) {
				Statement stmt = con.createStatement();
				String sql = "Select * from User where Id = "+id;
				ResultSet rs = stmt.executeQuery(sql);
				User userData = new User();
				 
				if(rs.next()) {
					userData.setUserId(rs.getInt(1));
					 userData.setName(rs.getString("Name"));
					 userData.setPassword(rs.getString("Password"));
					 userData.setUserName(rs.getString("UserName"));
					 userData.setPhoneNumber(rs.getString("PhoneNumber"));
					 userData.setAddress(rs.getString("Address"));
					 userData.setGender(rs.getString("Gender"));
					 userData.setBalance(rs.getDouble(7)); //in 7 number index the balance is located...
					 userData.setStatus(rs.getString("Status"));
					 userData.setUserType(rs.getString("UserType"));
					 return userData;
				 }
			}
		}catch (Exception e) {
			System.out.println("Couldn't get Data for "+e);
		}
		return null;
	}

	@Override
	public User GetUserByUserNameandPassword(String userName, String password) {
		try {
			if(userName!="" && password !="") {
				Statement stmt = con.createStatement();
				 String sql = "Select * from User where UserName = '"+userName+"' and Password = '"+password+"'";
				 ResultSet rs = stmt.executeQuery(sql);
				 User userData;
				 
				 if(rs.next()) {
					 userData = new User(rs.getInt(1),rs.getString("UserName"),
								rs.getString("Password"), rs.getString("Name"),
								rs.getString("Address"), rs.getString("PhoneNumber"),
								rs.getString("Gender"), rs.getDouble(7), rs.getString("Status"), rs.getString("UserType"));
					 
					 return userData;
				 }
			}
		}catch (Exception e) {
			System.out.println("Couldn't get Data for "+e);
		}
		return null;
	}
	@Override
	public User GetUserByPhoneNumber(String phoneNumber) {
		try {
			Statement stmt = con.createStatement();
			String sql = "Select * from User where PhoneNumber = "+phoneNumber;
			ResultSet rs = stmt.executeQuery(sql);
			User userData = new User();
			 
			if(rs.next()) {
				userData.setUserId(rs.getInt(1));
				 userData.setName(rs.getString("Name"));
				 userData.setPassword(rs.getString("Password"));
				 userData.setUserName(rs.getString("UserName"));
				 userData.setPhoneNumber(rs.getString("PhoneNumber"));
				 userData.setAddress(rs.getString("Address"));
				 userData.setGender(rs.getString("Gender"));
				 userData.setBalance(rs.getDouble(7)); //in 7 number index the balance is located...
				 userData.setStatus(rs.getString("Status"));
				 userData.setUserType(rs.getString("UserType"));
				 return userData;
			 }
		}catch (Exception e) {
			System.out.println("Couldn't get Data for "+e);
		}
		return null;
	}
	@Override
	public Boolean UpdateUserBalancebyId(int Id,double Amount) {
		try {
			if(Id!=0 && Amount>0) {
				Statement smt =  con.createStatement();
				String query = "update User set Balance= "+Amount+" where Id = "+Id;
				smt.execute(query);
				return true;
			}
		}catch(Exception ex)
		{
			System.out.println("Couldn't Updated the User Balance for "+ex);
		}
		return false;
	}
	@Override
	public ArrayList<User> GetAllUser() {
		ArrayList<User> listOfUser = new ArrayList<User>();
		try {
			Statement stmt = con.createStatement();
			String sql = "Select * from User where UserType = 'User'";
			ResultSet rs = stmt.executeQuery(sql);
			User user;
			while(rs.next()) {
				user = new User(rs.getInt(1),rs.getString("UserName"),
						rs.getString("Password"), rs.getString("Name"),
						rs.getString("Address"), rs.getString("PhoneNumber"),
						rs.getString("Gender"), rs.getDouble(7), rs.getString("Status"), rs.getString("UserType"));
				listOfUser.add(user);
			}
		}catch(Exception e) {
			System.out.println("Couldn't get List of users for "+e);
		}
		return listOfUser;
	}

}
