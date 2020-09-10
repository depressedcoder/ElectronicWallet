package Data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.nit.instance.DatabaseConnection;

import Models.Transaction;
import Models.User;
import Models.UserRequest;

public class UserRequestRepository implements IUserRequestRepository{

	private Connection con;
	private DatabaseConnection dbc;
	
	public UserRequestRepository() {
		dbc = DatabaseConnection.getDatabaseConnection();
		con = dbc.getConnection();
	}
	
	@Override
	public Boolean InsertUserRequest(UserRequest ubr) {
		try {
			if(ubr!=null) {
				Statement smt =  con.createStatement();
				String query = "INSERT INTO `UserRequest`(`UserId`, `RequestAmount`, `RequestStatus`,`RequestType`) VALUES "
						+ "("+ubr.getUserId()+","+ubr.getRequestBalance()+",'"+ubr.getRequestStatus()+"','"+ubr.getRequestType()+"')";
				
				smt.execute(query);
				return true;
			}
		}catch(Exception ex)
		{
			System.out.println("Couldn't Inserted the UserRequest Info for "+ex);
		}
		return false;
	}

	@Override
	public Boolean UpdateRequestStatusByIdandStatusType(int id,String Status) {
		try {
			if(id!=0) {
				Statement smt =  con.createStatement();
				String query = "update UserRequest set RequestStatus= '"+Status+"' where Id = "+id;
				smt.execute(query);
				return true;
			}
		}catch(Exception ex)
		{
			System.out.println("Couldn't Updated the User Request Status for "+ex);
		}
		return false;
	}

	@Override
	public Boolean DeleteUserRequestById(int Id) {
		try {
			if(Id!=0) {
				Statement smt =  con.createStatement();
				String query = "DELETE FROM `UserRequest` WHERE Id="+Id;
				smt.execute(query);
				return true;
			}
		}catch(Exception ex)
		{
			System.out.println("Couldn't Delete the UserRequest for "+ex);
		}
		return false;
	}

	@Override
	public UserRequest GetUserRequestById(int Id) {
		try {
			if(Id!=0) {
				Statement stmt = con.createStatement();
				String sql = "Select * from UserRequest where Id = "+Id;
				ResultSet rs = stmt.executeQuery(sql);
				UserRequest urData = new UserRequest();
				 
				if(rs.next()) {
					urData.setId(rs.getInt(1));
					urData.setUserId(rs.getInt(2));
					urData.setRequestBalance(rs.getDouble(3));
					urData.setRequestDate(rs.getTimestamp(4));
					urData.setRequestStatus(rs.getString("RequestStatus"));
					urData.setRequestType(rs.getString("RequestType"));
					 return urData;
				 }
			}
		}catch (Exception e) {
			System.out.println("Couldn't get Data for "+e);
		}
		return null;
	}

	@Override
	public ArrayList<UserRequest> GetAllUserRequest() {
		ArrayList<UserRequest> listOfUserRequest = new ArrayList<UserRequest>();
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT UserRequest.Id as Id,"
					+ "UserId,"
					+ "RequestAmount,"
					+ "RequestDate,"
					+ "RequestStatus,"
					+ "user.Name as Name,"
					+ "user.PhoneNumber as PhoneNumber,"
					+ "RequestType"
					+ " FROM UserRequest\n" + 
					"left join User as user on user.Id = UserRequest.UserId";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				UserRequest userRequest = new UserRequest(rs.getInt(1), rs.getInt(2), rs.getDouble(3),rs.getTimestamp(4),rs.getString("RequestStatus"),
						rs.getString("Name"),rs.getString("PhoneNumber"),rs.getString("RequestType"));
				listOfUserRequest.add(userRequest);
			}
		}catch(Exception e) {
			System.out.println("Couldn't get List of UserRequest for "+e);
		}
		return listOfUserRequest;
	}

	@Override
	public ArrayList<UserRequest> GetAllUserRequest(int UserId,String RequestStatus) {
		ArrayList<UserRequest> listOfUserRequest = new ArrayList<UserRequest>();
		try {
			Statement stmt = con.createStatement();
			String sql = "Select UserRequest.Id as Id,"
					+ "UserId,"
					+ "RequestAmount,"
					+ "RequestDate,"
					+ "RequestStatus,"
					+ "user.Name as Name,user.PhoneNumber as PhoneNumber,"
					+ "RequestType"
					+ " from UserRequest left join User as user on user.Id = UserRequest.UserId "
					+ "where RequestStatus = '"+RequestStatus+"' and UserId = "+UserId+" ORDER by RequestDate DESC";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				UserRequest userRequest = new UserRequest(rs.getInt(1), rs.getInt(2), rs.getDouble(3),rs.getTimestamp(4),rs.getString("RequestStatus"),
						rs.getString("Name"),rs.getString("PhoneNumber"),rs.getString("RequestType"));
				listOfUserRequest.add(userRequest);
			}
		}catch(Exception e) {
			System.out.println("Couldn't get List of UserRequest for "+e);
		}
		return listOfUserRequest;
	}

	@Override
	public ArrayList<UserRequest> GetAllUserRequestByRequestStatus(String RequestStatus) {
		ArrayList<UserRequest> listOfUserRequest = new ArrayList<UserRequest>();
		try {
			Statement stmt = con.createStatement();
			String sql = "Select UserRequest.Id as Id"
					+ ",UserId,"
					+ "RequestAmount,"
					+ "RequestDate,"
					+ "RequestStatus,"
					+ "user.Name as Name,"
					+ "user.PhoneNumber as PhoneNumber,"
					+ "RequestType"
					+ " from UserRequest left join User as user on user.Id = UserRequest.UserId"
					+ " where RequestStatus = '"+RequestStatus+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				UserRequest userRequest = new UserRequest(rs.getInt(1), rs.getInt(2), rs.getDouble(3),rs.getTimestamp(4),rs.getString("RequestStatus"),
						rs.getString("Name"),rs.getString("PhoneNumber"),rs.getString("RequestType"));
				listOfUserRequest.add(userRequest);
			}
		}catch(Exception e) {
			System.out.println("Couldn't get List of UserRequest for "+e);
		}
		return listOfUserRequest;
	}

	@Override
	public ArrayList<UserRequest> GetAllUserRequestByUserIdandRequestType(int UserId, String RequestType, String RequestStatus) {
		ArrayList<UserRequest> listOfUserRequest = new ArrayList<UserRequest>();
		try {
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM UserRequest where UserId = "+UserId+" and RequestType = '"+RequestType+"' and RequestStatus = '"+RequestStatus+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				
				UserRequest userRequest = new UserRequest();
				userRequest.setId(rs.getInt(1));
				userRequest.setUserId(rs.getInt(2));
				userRequest.setRequestBalance(rs.getDouble(3));
				userRequest.setRequestDate(rs.getTimestamp(4));
				userRequest.setRequestStatus(rs.getString("RequestStatus"));
				userRequest.setRequestType(rs.getString("RequestType"));
				 
				listOfUserRequest.add(userRequest);
			}
		}catch(Exception e) {
			System.out.println("Couldn't get List of UserRequest for "+e);
		}
		return listOfUserRequest;
	}

	@Override
	public Boolean DeleteUserRequestByUserId(int UserId) {
		try {
			if(UserId!=0) {
				Statement smt =  con.createStatement();
				String query = "DELETE FROM `UserRequest` WHERE UserId="+UserId;
				smt.execute(query);
				return true;
			}
		}catch(Exception ex)
		{
			System.out.println("Couldn't Delete the UserRequest for "+ex);
		}
		return false;
	}

}
