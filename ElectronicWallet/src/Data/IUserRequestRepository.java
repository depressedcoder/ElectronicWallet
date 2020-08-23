package Data;

import java.util.ArrayList;

import Models.UserRequest;

public interface IUserRequestRepository {
	Boolean InsertUserRequest(UserRequest ubr);
	Boolean UpdateRequestStatusByIdandStatusType(int id,String RequestStatus);
	Boolean DeleteUserRequestById(int id);
	UserRequest GetUserRequestById(int Id);
	ArrayList<UserRequest> GetAllUserRequest();
	ArrayList<UserRequest> GetAllUserRequest(int UserId,String RequestStatus); // method overloading
	
	ArrayList<UserRequest> GetAllUserRequestByRequestStatus(String RequestStatus); 
	
	ArrayList<UserRequest> GetAllUserRequestByUserIdandRequestType(int UserId,String RequestType); 
}
