import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import org.nit.instance.DatabaseConnection;

import Data.IUserRepository;
import Data.TransactionRepository;
import Data.UserRequestRepository;
import Data.UserRepository;
import Models.Transaction;
import Models.User;
import Models.UserRequest;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JScrollBar;

public class EWalletMain {

	private JFrame frame;
	private JTextField txtLoginUserName;
	private JPasswordField txtLoginPassword;
	private JTextField txtName;
	private JTextField txtUserName;
	private JPasswordField txtPassword;
	private JPasswordField txtConfirmPassword;
	private JTextField txtPhoneNumber;
	private JLayeredPane layeredPane;
	
	private Connection con;
	private int UserId;
	private int ReceiverUserId;
	private JTable UserTable;
	private JTable AdminTransactionTable;
	private int AdminViewUserId;
	private int AdminViewUserRequestId;
	private JTextField txtEName;
	private JTextField txtEUserName;
	private JPasswordField txtEPassword;
	private JPasswordField txtEConfirmPassword;
	private JTextField txtEPhone;
	
	private String eGender;
	private String Gender;
	private JTextField txtSearchReceiver;
	private JTextField txtAmount;
	private JTextField txtAmountAdd;
	private JTable tblRequestPending;
	private JTable tblAdminViewRequestPendingReq;
	private JTable tblAdminViewRequestHistory;
	private JTable tblIn;
	private JTable tblOut;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EWalletMain window = new EWalletMain();
					window.frame.setTitle("E-Wallet (v 1.0)");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EWalletMain() {
		initialize();
		DatabaseConnection dbc = DatabaseConnection.getDatabaseConnection();
		con = dbc.getConnection();
	}
	
	public void switchPanel(JPanel panel) {
		layeredPane.removeAll();
		layeredPane.add(panel);
		layeredPane.repaint();
		layeredPane.revalidate();
	}
	
	//clearing fields
	public void ClearRegistrationField(JTextArea txtAddress,JComboBox cmb) {
		
		txtName.setText("");
		txtUserName.setText("");
		txtPassword.setText("");
		txtConfirmPassword.setText("");
		txtPhoneNumber.setText("");
		cmb.setSelectedIndex(0);
		txtAddress.setText(null);
	}
	public void ClearEditProfileField(JTextArea txtAddress) {
		
		txtEName.setText("");
		txtEUserName.setText("");
		txtEPassword.setText("");
		txtEConfirmPassword.setText("");
		txtEPhone.setText("");
		txtAddress.setText(null);
	}
	private void ClearSearchRecieverField(JTextArea txtRemarks, JLabel revieverName,JLabel recieverPhoneNum)
	{
		txtSearchReceiver.setText("");
		txtAmount.setText("");
		txtRemarks.setText("");
		revieverName.setText(null);
		recieverPhoneNum.setText(null);
	}
	//methods
	public static boolean isPhoneNumberValid(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        int d = Integer.parseInt(strNum); //you can't give float or . or string
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	public static boolean isAmountValid(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	        if(d<=0)
	        	return false;
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
	private void SetUserTableData() {
		try {
			UserRepository userRepo = new UserRepository();
			ArrayList<User> listOfUser = userRepo.GetAllUser();

			 if(listOfUser.size()>0) {
				 String[][] data = new String[listOfUser.size()][10];//rows,column
				 int rowindex = 0;
				 for(int i=0;i<listOfUser.size();i++)
				 {
					 data[rowindex][0] = listOfUser.get(i).getUserId()+"";
					 data[rowindex][1] = listOfUser.get(i).getUserName();
			  		 data[rowindex][2] = listOfUser.get(i).getPassword();
					 data[rowindex][3] = listOfUser.get(i).getName();
					 data[rowindex][4] = listOfUser.get(i).getPhoneNumber();
					 data[rowindex][5] = listOfUser.get(i).getAddress();
					 data[rowindex][6] = listOfUser.get(i).getBalance()+"";
					 data[rowindex][7] = listOfUser.get(i).getGender();
					 data[rowindex][8] = listOfUser.get(i).getStatus();
					 data[rowindex][9] = listOfUser.get(i).getUserType();
					 rowindex++;
				 }
				 String[] cols = {"Id" , "User Name" , "Password", "Name" , "Phone Number", "Address", "Balance","Gender","Status","User Type"};
	   			 DefaultTableModel model = new DefaultTableModel(data,cols);
	   			 UserTable.setModel(model);
			 }
		}
		catch(ArrayIndexOutOfBoundsException ex) {
			JOptionPane.showMessageDialog(null, "Can not retrieve data!");
		}
	}
	private void SetAdminViewTransactionTableData() {
		try {
			TransactionRepository transactionRepo = new TransactionRepository();
			ArrayList<Transaction> listOfTransactionDate = transactionRepo.GetAllTransactions();
			
			 if(listOfTransactionDate.size()>0) {
				 String[][] data = new String[listOfTransactionDate.size()][11];//rows,column
				 int rowindex = 0;
				 for(int i=0;i<listOfTransactionDate.size();i++)
				 {
					 UserRepository userRepo = new UserRepository();
					 User senderInfo = userRepo.GetUserById(listOfTransactionDate.get(i).getSenderId());
					 User recieverInfo = userRepo.GetUserById(listOfTransactionDate.get(i).getReceiverId());
					 
					 data[rowindex][0] = listOfTransactionDate.get(i).getId()+"";
					 data[rowindex][1] = listOfTransactionDate.get(i).getSenderId()+"";
					 
					 data[rowindex][2] = senderInfo.getName();
					 data[rowindex][3] = senderInfo.getPhoneNumber();
					 
			  		 data[rowindex][4] = listOfTransactionDate.get(i).getReceiverId()+"";
			  		 
			  		 data[rowindex][5] = recieverInfo.getName();
					 data[rowindex][6] = recieverInfo.getPhoneNumber();
					 
					 data[rowindex][7] = listOfTransactionDate.get(i).getDate()+"";
					 data[rowindex][8] = listOfTransactionDate.get(i).getRemarks();
					 data[rowindex][9] = listOfTransactionDate.get(i).getAmount()+"";
					 data[rowindex][10] = listOfTransactionDate.get(i).getTransactionType();
					
					 rowindex++;
				 }
				 String[] cols = {"T Id" , "S Id" ,"Sender Name", "Sender Phonenumeber","R Id","Receiver Name","Receiver PhoneNumber",  "Date" , "Remarks", "Amount", "T Type"};
	   			 DefaultTableModel model = new DefaultTableModel(data,cols);
	   			 AdminTransactionTable.setModel(model);
			 }
		}
		catch(ArrayIndexOutOfBoundsException ex) {
			JOptionPane.showMessageDialog(null, "Can not retrieve data!");
		}
	}
	private void ShowUserBalanceAddPendingRequestTableData(int userId) {
		try {
			UserRequestRepository urRepo = new UserRequestRepository();
			ArrayList<UserRequest> listOfUserRequest = urRepo.GetAllUserRequest(userId,"Pending");
			
			 if(listOfUserRequest.size()>0) {
				 String[][] data = new String[listOfUserRequest.size()][4];//rows,column
				 int rowindex = 0;
				 for(int i=0;i<listOfUserRequest.size();i++)
				 {
					 data[rowindex][0] = listOfUserRequest.get(i).getRequestDate()+"";
					 data[rowindex][1] = listOfUserRequest.get(i).getRequestBalance()+"";
					 
					 data[rowindex][2] = listOfUserRequest.get(i).getRequestStatus()+"";
					 data[rowindex][3] = listOfUserRequest.get(i).getRequestType()+"";
					 
					 rowindex++;
				 }
				 String[] cols = {"Request Date" , "Amount" ,"Request Status", "Request Type"};
	   			 DefaultTableModel model = new DefaultTableModel(data,cols);
	   			 tblRequestPending.setModel(model);
			 }
		}
		catch(ArrayIndexOutOfBoundsException ex) {
			JOptionPane.showMessageDialog(null, "Can not retrieve data!");
		}
	}
	private void ShowAdminViewRechargePendingRequestTableData() {
		try {
			UserRequestRepository urRepo = new UserRequestRepository();
			ArrayList<UserRequest> listOfUserRequest = urRepo.GetAllUserRequestByRequestStatus("Pending");
			
			 if(listOfUserRequest.size()>0) {
				 String[][] data = new String[listOfUserRequest.size()][8];//rows,column
				 int rowindex = 0;
				 for(int i=0;i<listOfUserRequest.size();i++)
				 {
					 data[rowindex][0] = listOfUserRequest.get(i).getId()+"";
					 data[rowindex][1] = listOfUserRequest.get(i).getRequestDate()+"";
					 
					 data[rowindex][2] = listOfUserRequest.get(i).getUserId()+"";
					 data[rowindex][3] = listOfUserRequest.get(i).getName();
					 data[rowindex][4] = listOfUserRequest.get(i).getPhoneNumber();
					 
					 data[rowindex][5] = listOfUserRequest.get(i).getRequestBalance()+"";
					 data[rowindex][6] = listOfUserRequest.get(i).getRequestStatus()+"";
					 data[rowindex][7] = listOfUserRequest.get(i).getRequestType()+"";
					 
					 rowindex++;
				 }
				 String[] cols = {"Req ID","Request Date" ,"User Id","User Name","Phone Number", "Amount" ,"Request Status","Request Type"};
	   			 DefaultTableModel model = new DefaultTableModel(data,cols);
	   			 tblAdminViewRequestPendingReq.setModel(model);
			 }
		}
		catch(ArrayIndexOutOfBoundsException ex) {
			JOptionPane.showMessageDialog(null, "Can not retrieve data!");
		}
	}
	private void ShowAdminViewRechargeSuccessInfoTableData() {
		try {
			UserRequestRepository urRepo = new UserRequestRepository();
			ArrayList<UserRequest> listOfUserRequest = urRepo.GetAllUserRequestByRequestStatus("Success");
			
			 if(listOfUserRequest.size()>0) {
				 String[][] data = new String[listOfUserRequest.size()][8];//rows,column
				 int rowindex = 0;
				 for(int i=0;i<listOfUserRequest.size();i++)
				 {
					 
					 data[rowindex][0] = listOfUserRequest.get(i).getId()+"";
					 data[rowindex][1] = listOfUserRequest.get(i).getRequestDate()+"";
					 
					 data[rowindex][2] = listOfUserRequest.get(i).getUserId()+"";
					 data[rowindex][3] = listOfUserRequest.get(i).getName();
					 data[rowindex][4] = listOfUserRequest.get(i).getPhoneNumber();
					 
					 data[rowindex][5] = listOfUserRequest.get(i).getRequestBalance()+"";
					 data[rowindex][6] = listOfUserRequest.get(i).getRequestStatus()+"";
					 data[rowindex][7] = listOfUserRequest.get(i).getRequestType()+"";
					 
					 rowindex++;
				 }
				 String[] cols = {"Req ID","Request Date" ,"User Id","User Name","Phone Number", "Amount" ,"Request Status","Request Type"};
	   			 DefaultTableModel model = new DefaultTableModel(data,cols);
	   			 tblAdminViewRequestHistory.setModel(model);
			 }
		}
		catch(ArrayIndexOutOfBoundsException ex) {
			JOptionPane.showMessageDialog(null, "Can not retrieve data!");
		}
	}
	private void ShowUserTransactionHistory(int UserId) {
		try {
			if(UserId!=0)
			{
				
				TransactionRepository transactionRepo = new TransactionRepository();
				ArrayList<Transaction> listOfBalanceDepositHistory = transactionRepo.GetAllTransactionsByReceiverId(UserId);
				ArrayList<Transaction> listOfBalanceWithdrawHistory = transactionRepo.GetAllTransactionsBySenderId(UserId);
				
				//Deposite history Table Area
				 if(listOfBalanceDepositHistory.size()>0) { 
					 String[][] data = new String[listOfBalanceDepositHistory.size()][6];//rows,column
					 int rowindex = 0;
					 for(int i=0;i<listOfBalanceDepositHistory.size();i++)
					 {
						 UserRepository userRepo = new UserRepository();
						 User senderInfo = userRepo.GetUserById(listOfBalanceDepositHistory.get(i).getSenderId());
						 
						 data[rowindex][0] = senderInfo.getName();
						 data[rowindex][1] = senderInfo.getPhoneNumber();
						 
						 data[rowindex][2] = listOfBalanceDepositHistory.get(i).getDate()+"";
						 data[rowindex][3] = listOfBalanceDepositHistory.get(i).getRemarks();
						 
				  		 data[rowindex][4] = listOfBalanceDepositHistory.get(i).getAmount()+"";;
				  		 
				  		 data[rowindex][5] = listOfBalanceDepositHistory.get(i).getTransactionType();
						
						 rowindex++;
					 }
					 String[] cols = {"From" , "Sender Phonenumeber", "Date" , "Remarks", "Amount", "T Type"};
		   			 DefaultTableModel model = new DefaultTableModel(data,cols);
		   			 tblIn.setModel(model);
				 }
				 //Withdraw history Table Area
				 if(listOfBalanceWithdrawHistory.size()>0) { 
					 String[][] data = new String[listOfBalanceWithdrawHistory.size()][6];//rows,column
					 int rowindex = 0;
					 for(int i=0;i<listOfBalanceWithdrawHistory.size();i++)
					 {
						 UserRepository userRepo = new UserRepository();
						 User ReceiverInfo = userRepo.GetUserById(listOfBalanceWithdrawHistory.get(i).getReceiverId());
						 
						 data[rowindex][0] = ReceiverInfo.getName();
						 data[rowindex][1] = ReceiverInfo.getPhoneNumber();
						 
						 data[rowindex][2] = listOfBalanceWithdrawHistory.get(i).getDate()+"";
						 data[rowindex][3] = listOfBalanceWithdrawHistory.get(i).getRemarks();
						 
				  		 data[rowindex][4] = listOfBalanceWithdrawHistory.get(i).getAmount()+"";;
				  		 
				  		 data[rowindex][5] = listOfBalanceWithdrawHistory.get(i).getTransactionType();
						
						 rowindex++;
					 }
					 String[] cols = {"To" , "Receiver Phonenumeber", "Date" , "Remarks", "Amount", "T Type"};
		   			 DefaultTableModel model = new DefaultTableModel(data,cols);
		   			 tblOut.setModel(model);
				 }
			}
		}
		catch(ArrayIndexOutOfBoundsException ex) {
			JOptionPane.showMessageDialog(null, "Can not retrieve data!");
		}
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(100, 100, 800, 600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(6, 37, 788, 541);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));
		
		JPanel pnlLogin = new JPanel();
		pnlLogin.setBackground(Color.BLACK);
		layeredPane.add(pnlLogin, "name_39274488224810");
		pnlLogin.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Wellcome to");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel.setBounds(28, 25, 269, 128);
		pnlLogin.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("E-Wallet");
		lblNewLabel_1.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 40));
		lblNewLabel_1.setBounds(28, 124, 242, 80);
		pnlLogin.add(lblNewLabel_1);
		
		txtLoginUserName = new JTextField();
		txtLoginUserName.setToolTipText("UserName");
		txtLoginUserName.setColumns(10);
		txtLoginUserName.setBounds(556, 187, 130, 26);
		pnlLogin.add(txtLoginUserName); //login
		
		JLabel label = new JLabel("User Name:");
		label.setForeground(Color.LIGHT_GRAY); 
		label.setBounds(460, 192, 84, 16);
		pnlLogin.add(label);
		
		JLabel label_1 = new JLabel("Password:");
		label_1.setForeground(Color.LIGHT_GRAY);
		label_1.setBounds(460, 228, 84, 16);
		pnlLogin.add(label_1);
		
		txtLoginPassword = new JPasswordField();
		txtLoginPassword.setBounds(556, 225, 130, 26);
		pnlLogin.add(txtLoginPassword);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setForeground(Color.LIGHT_GRAY);
		lblLogin.setFont(lblLogin.getFont().deriveFont(lblLogin.getFont().getSize() + 5f));
		lblLogin.setBounds(514, 124, 172, 31);
		pnlLogin.add(lblLogin);
		
		JPanel pnlRegistration = new JPanel();
		pnlRegistration.setBackground(Color.BLACK);
		layeredPane.add(pnlRegistration, "name_39298404908408");
		pnlRegistration.setLayout(null);
		
		JLabel label_2 = new JLabel("E-Wallet Registration");
		label_2.setForeground(Color.LIGHT_GRAY);
		label_2.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		label_2.setBounds(291, 6, 206, 16);
		pnlRegistration.add(label_2);
		
		JLabel lblName = new JLabel("*Name:");
		lblName.setForeground(Color.LIGHT_GRAY);
		lblName.setBounds(215, 52, 61, 16);
		pnlRegistration.add(lblName);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(318, 47, 206, 26);
		pnlRegistration.add(txtName);
		
		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(318, 82, 206, 26);
		pnlRegistration.add(txtUserName);
		
		JLabel lbluserName = new JLabel("*User Name:");
		lbluserName.setForeground(Color.LIGHT_GRAY);
		lbluserName.setBounds(215, 87, 91, 16);
		pnlRegistration.add(lbluserName);
		
		JLabel lblpassword = new JLabel("*Password: ");
		lblpassword.setForeground(Color.LIGHT_GRAY);
		lblpassword.setBounds(215, 123, 76, 16);
		pnlRegistration.add(lblpassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(318, 120, 206, 26);
		pnlRegistration.add(txtPassword);
		
		JLabel lblconfirmPassword = new JLabel("*Confirm Password: ");
		lblconfirmPassword.setForeground(Color.LIGHT_GRAY);
		lblconfirmPassword.setBounds(185, 159, 135, 16);
		pnlRegistration.add(lblconfirmPassword);
		
		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setBounds(319, 155, 206, 26);
		pnlRegistration.add(txtConfirmPassword);
		
		JLabel lblphone = new JLabel("*Phone:");
		lblphone.setForeground(Color.LIGHT_GRAY);
		lblphone.setBounds(216, 187, 61, 26);
		pnlRegistration.add(lblphone);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(319, 187, 206, 26);
		pnlRegistration.add(txtPhoneNumber);
		
		JLabel label_8 = new JLabel("Address: ");
		label_8.setForeground(Color.LIGHT_GRAY);
		label_8.setBounds(216, 225, 61, 16);
		pnlRegistration.add(label_8);
		
		JTextArea txtAddress = new JTextArea();
		txtAddress.setBounds(319, 225, 206, 111);
		pnlRegistration.add(txtAddress);
		
		JLabel label_9 = new JLabel("Gender: ");
		label_9.setForeground(Color.LIGHT_GRAY);
		label_9.setBounds(216, 361, 61, 16);
		pnlRegistration.add(label_9);
		
		JPanel pnlAdmin = new JPanel();
		pnlAdmin.setBackground(Color.BLACK);
		layeredPane.add(pnlAdmin, "name_39318229468850");
		pnlAdmin.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Admin Panel");
		lblNewLabel_2.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_2.setBounds(352, 6, 149, 25);
		pnlAdmin.add(lblNewLabel_2);
		
		JPanel pnlUserProfile = new JPanel();
		pnlUserProfile.setBackground(Color.BLACK);
		pnlUserProfile.setForeground(Color.LIGHT_GRAY);
		layeredPane.add(pnlUserProfile, "name_39347762534401");
		pnlUserProfile.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("User Profile");
		lblNewLabel_3.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_3.setBounds(286, 6, 117, 16);
		pnlUserProfile.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Welcome");
		lblNewLabel_4.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_4.setBounds(513, 6, 74, 16);
		pnlUserProfile.add(lblNewLabel_4);
		
		JLabel lblUserName = new JLabel("New label");
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setBounds(585, 6, 196, 16);
		pnlUserProfile.add(lblUserName);
		
		JLabel lblNewLabel_5 = new JLabel("Balance");
		lblNewLabel_5.setForeground(Color.LIGHT_GRAY);
		lblNewLabel_5.setBounds(513, 34, 61, 16);
		pnlUserProfile.add(lblNewLabel_5);
		
		JLabel lblBalance = new JLabel("New label");
		lblBalance.setForeground(Color.RED);
		lblBalance.setBounds(585, 34, 179, 16);
		pnlUserProfile.add(lblBalance);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 90, 776, 184);
		pnlAdmin.add(scrollPane);
		
		UserTable = new JTable();
		JTableHeader header = UserTable.getTableHeader();
	      header.setBackground(Color.black);
	      header.setForeground(Color.white);
	      
		UserTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminViewUserId =Integer.parseInt(UserTable.getValueAt(UserTable.getSelectedRow(), 0).toString());
				
			}
		});
		scrollPane.setViewportView(UserTable);
		
		JLabel lblNewLabel_6 = new JLabel("User Information");
		lblNewLabel_6.setForeground(Color.GRAY);
		lblNewLabel_6.setBounds(6, 62, 111, 16);
		pnlAdmin.add(lblNewLabel_6);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(6, 328, 776, 184);
		pnlAdmin.add(scrollPane_1);
		
		AdminTransactionTable = new JTable();
		JTableHeader adminTransactionTableHeader = AdminTransactionTable.getTableHeader();
		adminTransactionTableHeader.setBackground(Color.black);
		adminTransactionTableHeader.setForeground(Color.white);
		scrollPane_1.setViewportView(AdminTransactionTable);
		
		JLabel lblNewLabel_7 = new JLabel("Transactions");
		lblNewLabel_7.setForeground(Color.GRAY);
		lblNewLabel_7.setBackground(Color.BLACK);
		lblNewLabel_7.setBounds(6, 300, 88, 16);
		pnlAdmin.add(lblNewLabel_7);
		//Button Area
		
		JComboBox<String> cmbGender = new JComboBox<String>();
		cmbGender.addItem("Male");
		cmbGender.addItem("Female");
		cmbGender.addItem("Others");
		cmbGender.setBounds(318, 357, 213, 27);
		cmbGender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
		        Gender = (String)cb.getSelectedItem();
			}
		});
		
		pnlRegistration.add(cmbGender);
		JButton btnGotoRegister = new JButton("Register");
		btnGotoRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearRegistrationField(txtAddress,cmbGender);
				switchPanel(pnlRegistration);
			}
		});
		btnGotoRegister.setBounds(591, 279, 117, 29);
		pnlLogin.add(btnGotoRegister);
		

		JButton btnWalletRegister = new JButton("Register");
		btnWalletRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        try {
					if(!txtPhoneNumber.getText().equals("") && txtPhoneNumber.getText().length() == 11 && 
							!txtUserName.getText().equals("") && !txtName.getText().equals("") && 
							!txtPassword.getText().equals("") && !txtConfirmPassword.getText().equals("") && 
							(txtPassword.getText().equals(txtConfirmPassword.getText()))&& isPhoneNumberValid(txtPhoneNumber.getText())) 
					{
			            UserRepository userRepo = new UserRepository();
						User userData = new User();
						
						User userByPhone = userRepo.GetUserByPhoneNumber(txtPhoneNumber.getText()); // checking for unique phone number;
						if(userByPhone==null)
						{
							userData.setName(txtName.getText());
							userData.setUserName(txtUserName.getText());
							userData.setPassword(txtPassword.getText());
							userData.setPhoneNumber(txtPhoneNumber.getText());
							userData.setAddress(txtAddress.getText());
							userData.setBalance((double) 100);
							userData.setGender(Gender);
							userData.setStatus("Block");
							userData.setUserType("User");
							Boolean isUpdatedUser = userRepo.InsertUser(userData);
							
				            if(isUpdatedUser) {
				            	ClearRegistrationField(txtAddress,cmbGender);
					            JOptionPane.showMessageDialog(null, "Registration Succefully done.");
					            switchPanel(pnlLogin);
				            }
				            else {
				            	JOptionPane.showMessageDialog(null, "OOps!! Something definitely went wrong!!");
				            }
						}
						else {
							JOptionPane.showMessageDialog(null, "Phone Number already existed!! Give another Phone Number!!");
						}
					}
					else{
						if(txtName.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Please Enter Name!!");
						else if(txtUserName.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Please Enter UserName!!");
						else if(txtPassword.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Please Enter Password!!");
						else if(!(txtPassword.getText().equals(txtConfirmPassword.getText())))
							JOptionPane.showMessageDialog(null, "Password Doesn't Match.");
						else if(txtPhoneNumber.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Please Enter Phone Number!!");
						else if(txtPhoneNumber.getText().length() != 11)
							JOptionPane.showMessageDialog(null, "Please Enter 11 digit Phone Number!!");
						else if(!isPhoneNumberValid(txtPhoneNumber.getText()))
							JOptionPane.showMessageDialog(null, "Please Enter numeric Phone Number with 11 digit!!");
						
					}
		            
		        } catch (Exception exc) {
		            exc.printStackTrace();
		        } 
			}
		});
		btnWalletRegister.setBounds(306, 423, 117, 29);
		pnlRegistration.add(btnWalletRegister);
		
		
		JButton btnBackToLogin = new JButton("Back To Login");
		btnBackToLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtLoginUserName.setText(null);
				txtLoginPassword.setText(null);
				switchPanel(pnlLogin);
			}
		});
		btnBackToLogin.setBounds(435, 423, 117, 29);
		pnlRegistration.add(btnBackToLogin);
		
		JButton btnAdminLogout = new JButton("Log out");
		btnAdminLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserId = 0;
				txtLoginUserName.setText(null);
				txtLoginPassword.setText(null);
				switchPanel(pnlLogin);
			}
		});
		btnAdminLogout.setBounds(6, 5, 92, 29);
		pnlAdmin.add(btnAdminLogout);
		
		JButton btnBlockUser = new JButton("Block User");
		btnBlockUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AdminViewUserId != 0) {
					try {
						Statement smt =  con.createStatement();
						smt.execute("update User set Status= 'Block' where Id = "+AdminViewUserId);
						JOptionPane.showMessageDialog(null, "User is Blocked!!");
						SetUserTableData();
						AdminViewUserId = 0;
						
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Couldn't update the user status!!");
					}
				}
			}
		});
		btnBlockUser.setBounds(384, 57, 117, 29);
		pnlAdmin.add(btnBlockUser);
		
		JButton btnActiveUser = new JButton("Active User");
		btnActiveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AdminViewUserId != 0) {
					try {
						Statement smt =  con.createStatement();
						smt.execute("update User set Status= 'Active' where Id = "+AdminViewUserId);
						JOptionPane.showMessageDialog(null, "User Activated!!");
						SetUserTableData();
						AdminViewUserId = 0;
						
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Couldn't update the user status!!");
					}
				}
			}
		});
		btnActiveUser.setBounds(516, 59, 117, 24);
		pnlAdmin.add(btnActiveUser);
		
		JButton btnDeleteUser = new JButton("Delete User");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AdminViewUserId != 0) {
					try {
						UserRepository userRepo = new UserRepository();
						Boolean isUserDeleted = userRepo.DeleteUserbyId(AdminViewUserId);
						if(isUserDeleted)
						{
							JOptionPane.showMessageDialog(null, "User Deleted!!");
							SetUserTableData();
							AdminViewUserId = 0;
						}
						else {
							JOptionPane.showMessageDialog(null, "User Can't be Deleted!!");
						}
						
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, "Couldn't update the user status!!");
					}
				}
			}
		});
		btnDeleteUser.setBounds(645, 59, 117, 24);
		pnlAdmin.add(btnDeleteUser);
		
		JButton btnUserProfileLogout = new JButton("Log Out");
		btnUserProfileLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(pnlLogin);
			}
		});
		btnUserProfileLogout.setBounds(32, 1, 94, 29);
		pnlUserProfile.add(btnUserProfileLogout);
		//pnlUserEdit

		JPanel pnlUserEditProfile = new JPanel();
		pnlUserEditProfile.setBackground(Color.BLACK);
		layeredPane.add(pnlUserEditProfile, "name_11373662058202");
		pnlUserEditProfile.setLayout(null);
		
		JLabel label_10 = new JLabel("Edit User Profile");
		label_10.setForeground(Color.LIGHT_GRAY);
		label_10.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		label_10.setBounds(355, 6, 206, 16);
		pnlUserEditProfile.add(label_10);
		
		JLabel lblname = new JLabel("*Name:");
		lblname.setForeground(Color.LIGHT_GRAY);
		lblname.setBounds(279, 52, 61, 16);
		pnlUserEditProfile.add(lblname);
		
		txtEName = new JTextField();
		txtEName.setColumns(10);
		txtEName.setBounds(382, 47, 206, 26);
		pnlUserEditProfile.add(txtEName);
		
		txtEUserName = new JTextField();
		txtEUserName.setColumns(10);
		txtEUserName.setBounds(382, 82, 206, 26);
		pnlUserEditProfile.add(txtEUserName);
		
		JLabel lbluserName_1 = new JLabel("*User Name:");
		lbluserName_1.setForeground(Color.LIGHT_GRAY);
		lbluserName_1.setBounds(279, 87, 91, 16);
		pnlUserEditProfile.add(lbluserName_1);
		
		JLabel lblpassword_1 = new JLabel("*Password: ");
		lblpassword_1.setForeground(Color.LIGHT_GRAY);
		lblpassword_1.setBounds(279, 125, 76, 16);
		pnlUserEditProfile.add(lblpassword_1);
		
		txtEPassword = new JPasswordField();
		txtEPassword.setBounds(382, 120, 206, 26);
		pnlUserEditProfile.add(txtEPassword);
		
		JLabel lblconfirmPassword_1 = new JLabel("*Confirm Password: ");
		lblconfirmPassword_1.setForeground(Color.LIGHT_GRAY);
		lblconfirmPassword_1.setBounds(249, 159, 135, 16);
		pnlUserEditProfile.add(lblconfirmPassword_1);
		
		txtEConfirmPassword = new JPasswordField();
		txtEConfirmPassword.setBounds(383, 155, 206, 26);
		pnlUserEditProfile.add(txtEConfirmPassword);
		
		JLabel lblphone_1 = new JLabel("*Phone:");
		lblphone_1.setForeground(Color.LIGHT_GRAY);
		lblphone_1.setBounds(280, 187, 61, 26);
		pnlUserEditProfile.add(lblphone_1);
		
		txtEPhone = new JTextField();
		txtEPhone.setColumns(10);
		txtEPhone.setBounds(383, 187, 206, 26);
		pnlUserEditProfile.add(txtEPhone);
		
		JLabel label_16 = new JLabel("Address: ");
		label_16.setForeground(Color.LIGHT_GRAY);
		label_16.setBounds(280, 225, 61, 16);
		pnlUserEditProfile.add(label_16);
		
		JTextArea txtEAddress = new JTextArea();
		txtEAddress.setBounds(383, 225, 206, 111);
		pnlUserEditProfile.add(txtEAddress);
		
		JLabel label_17 = new JLabel("Gender: ");
		label_17.setForeground(Color.LIGHT_GRAY);
		label_17.setBounds(280, 361, 61, 16);
		pnlUserEditProfile.add(label_17);
		
		JComboBox<String> cmbEGender = new JComboBox<String>();
		cmbEGender.addItem("Male");
		cmbEGender.addItem("Female");
		cmbEGender.addItem("Others");
		cmbEGender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
		        eGender = (String)cb.getSelectedItem();
			}
		});
		cmbEGender.setBounds(382, 357, 206, 27);
		pnlUserEditProfile.add(cmbEGender);
		
		JButton btnEditProfile = new JButton("Edit Profile");
		btnEditProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClearEditProfileField(txtEAddress);
				if(UserId != 0) {
					UserRepository u = new UserRepository();
					User userData = u.GetUserById(UserId);
					if(userData!=null) {
						txtEName.setText(userData.getName());
						txtEUserName.setText(userData.getUserName());
						txtEPassword.setText(userData.getPassword());
						txtEConfirmPassword.setText("");
						txtEPhone.setText(userData.getPhoneNumber());
						txtEAddress.setText(userData.getAddress());
						String g = userData.getGender();
						if(g.equals("Male"))
							cmbEGender.setSelectedIndex(0);
						else if(g.equals("Female"))
							cmbEGender.setSelectedIndex(1);
						else if(g.equals("Others"))
							cmbEGender.setSelectedIndex(2);
						switchPanel(pnlUserEditProfile);
					}
				}
			}
		});
		btnEditProfile.setBounds(384, 1, 117, 29);
		pnlUserProfile.add(btnEditProfile);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.BLACK);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.addChangeListener((ChangeListener) new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				if(tabbedPane.getSelectedIndex()==0) {
					//this is send money area
				}
				else if(tabbedPane.getSelectedIndex() == 1) {
					txtAmountAdd.setText("");
					ShowUserBalanceAddPendingRequestTableData(UserId);
				}
				else if(tabbedPane.getSelectedIndex()==2) {
					ShowUserTransactionHistory(UserId);
				}
			}
	    });
		tabbedPane.setBounds(32, 62, 718, 420);
		pnlUserProfile.add(tabbedPane);
		
		JPanel pnlUserSendMoney = new JPanel();
		pnlUserSendMoney.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Send money", null, pnlUserSendMoney, null);
		pnlUserSendMoney.setLayout(null);
		
		JLabel lblNewLabel_8 = new JLabel("Search User by Phone Number:");
		lblNewLabel_8.setBounds(54, 25, 203, 16);
		pnlUserSendMoney.add(lblNewLabel_8);
		
		txtSearchReceiver = new JTextField();
		txtSearchReceiver.setBounds(269, 20, 253, 26);
		pnlUserSendMoney.add(txtSearchReceiver);
		txtSearchReceiver.setColumns(10);
		
		JLabel lblName_1 = new JLabel("Reciever Name:");
		lblName_1.setForeground(Color.BLACK);
		lblName_1.setBounds(156, 71, 101, 16);
		pnlUserSendMoney.add(lblName_1);
		
		JLabel lblrecieverp = new JLabel("Receiver Phone Number:");
		lblrecieverp.setForeground(Color.BLACK);
		lblrecieverp.setBounds(105, 106, 152, 16);
		pnlUserSendMoney.add(lblrecieverp);
		
		JLabel lblReceiverName = new JLabel("New label");
		lblReceiverName.setForeground(Color.WHITE);
		lblReceiverName.setBounds(269, 71, 253, 16);
		pnlUserSendMoney.add(lblReceiverName);
		
		JLabel lblReceiverPhoneNumber = new JLabel("New label");
		lblReceiverPhoneNumber.setForeground(Color.WHITE);
		lblReceiverPhoneNumber.setBounds(269, 106, 253, 16);
		pnlUserSendMoney.add(lblReceiverPhoneNumber);
		
		JLabel lblAmount = new JLabel("*Amount:");
		lblAmount.setForeground(Color.BLACK);
		lblAmount.setBounds(193, 143, 64, 16);
		pnlUserSendMoney.add(lblAmount);
		
		txtAmount = new JTextField();
		txtAmount.setColumns(10);
		txtAmount.setBounds(269, 138, 206, 26);
		pnlUserSendMoney.add(txtAmount);
		
		JLabel lblremarks = new JLabel("*Remarks:");
		lblremarks.setForeground(Color.BLACK);
		lblremarks.setBounds(193, 179, 64, 16);
		pnlUserSendMoney.add(lblremarks);
		
		JTextArea txtRemarks = new JTextArea();
		txtRemarks.setBounds(269, 176, 206, 111);
		pnlUserSendMoney.add(txtRemarks);
		
		JPanel pnlUserAddBalance = new JPanel();
		pnlUserAddBalance.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Recharge/CashOut", null, pnlUserAddBalance, null);
		pnlUserAddBalance.setLayout(null);
		
		JLabel lblNewLabel_9 = new JLabel("*Amount:");
		lblNewLabel_9.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_9.setBounds(134, 29, 76, 16);
		pnlUserAddBalance.add(lblNewLabel_9);
		
		txtAmountAdd = new JTextField();
		txtAmountAdd.setBounds(250, 17, 211, 42);
		pnlUserAddBalance.add(txtAmountAdd);
		txtAmountAdd.setColumns(10);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(33, 105, 634, 243);
		pnlUserAddBalance.add(scrollPane_3);
		
		tblRequestPending = new JTable();
		JTableHeader header5 = tblRequestPending.getTableHeader();
	      header5.setBackground(Color.black);
	      header5.setForeground(Color.white);
		scrollPane_3.setViewportView(tblRequestPending);
		
		JLabel lblPendingRequests = new JLabel("Pending Requests");
		lblPendingRequests.setForeground(Color.LIGHT_GRAY);
		lblPendingRequests.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblPendingRequests.setBounds(284, 79, 126, 19);
		pnlUserAddBalance.add(lblPendingRequests);
		
		JPanel pnlTransectionHistory = new JPanel();
		pnlTransectionHistory.setBackground(Color.DARK_GRAY);
		tabbedPane.addTab("Transaction", null, pnlTransectionHistory, null);
		pnlTransectionHistory.setLayout(null);
		
		
		JLabel label_3 = new JLabel("Transaction History");
		label_3.setForeground(Color.BLACK);
		label_3.setBounds(289, 6, 129, 16);
		pnlTransectionHistory.add(label_3);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.BOTTOM);
		tabbedPane_1.setBounds(6, 24, 685, 344);
		pnlTransectionHistory.add(tabbedPane_1);
		
		JPanel pnlIn = new JPanel();
		tabbedPane_1.addTab("In", null, pnlIn, null);
		pnlIn.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(6, 6, 652, 286);
		pnlIn.add(scrollPane_2);
		
		tblIn = new JTable();
		JTableHeader header8 = tblIn.getTableHeader();
	      header8.setBackground(Color.black);
	      header8.setForeground(Color.white);
		scrollPane_2.setViewportView(tblIn);
		
		JPanel pnlOut = new JPanel();
		tabbedPane_1.addTab("Out", null, pnlOut, null);
		pnlOut.setLayout(null);
		
		JScrollPane scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(6, 6, 652, 286);
		pnlOut.add(scrollPane_6);
		
		tblOut = new JTable();
		JTableHeader header6 = tblOut.getTableHeader();
	      header6.setBackground(Color.black);
	      header6.setForeground(Color.white);
		scrollPane_6.setViewportView(tblOut);
		
		JButton btnlogin = new JButton("Login");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(!txtLoginUserName.getText().equals("") && !txtLoginPassword.getText().equals("")) {
						 UserRepository ur = new UserRepository();
						 User userData = ur.GetUserByUserNameandPassword(txtLoginUserName.getText(),txtLoginPassword.getText().toString());
						 if(userData!=null && userData.getUserType().equalsIgnoreCase("User")) {
							 String UserStatus = userData.getStatus();
							 if(UserStatus.equalsIgnoreCase("Active")) {
								 UserId = userData.getUserId();
								 lblUserName.setText(userData.getName());
								 lblBalance.setText(userData.getBalance().toString());
								 txtLoginUserName.setText(null);
								 txtLoginPassword.setText(null);
								 ClearSearchRecieverField(txtRemarks,lblReceiverName,lblReceiverPhoneNumber);
								 switchPanel(pnlUserProfile);
							 }
							 else {
								 UserId = 0;
								 JOptionPane.showMessageDialog(null, "This User is currently Blocked!!");
							 }
						 }
						 else if(userData!=null && userData.getUserType().equalsIgnoreCase("Admin")) {
							 //Admin Login
							 UserId = userData.getUserId();
							 SetUserTableData();
							 SetAdminViewTransactionTableData();
							 switchPanel(pnlAdmin);
						 }
						 else {
							 JOptionPane.showMessageDialog(null, "User Name or Password Incorrect");
							 //con.close();
						 }
					}
					else {
						if(txtLoginUserName.getText().equals("") && txtLoginPassword.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Please Enter UserName & Password!!");
						else if(txtLoginPassword.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Please Enter Password!");
						else
							JOptionPane.showMessageDialog(null, "Please Enter UserName!");
					}
				}catch(Exception f) {
					System.out.println(f);
				}
			}
		});
		btnlogin.setBounds(462, 279, 117, 29);
		pnlLogin.add(btnlogin);
		

		JButton btnUpdateUser = new JButton("Update");
		btnUpdateUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(UserId != 0) {
					UserRepository userRepo = new UserRepository();
					User userData = new User();
					if(!txtEPhone.getText().equals("") && txtEPhone.getText().length() ==11 &&
							!txtEUserName.getText().equals("") && !txtEName.getText().equals("") 
							&& !txtEPassword.getText().equals("") && !txtEConfirmPassword.getText().equals("") 
							&& (txtEPassword.getText().equals(txtEConfirmPassword.getText())) && isPhoneNumberValid(txtEPhone.getText()))
					{
						userData.setName(txtEName.getText());
						userData.setUserName(txtEUserName.getText());
						userData.setPassword(txtEPassword.getText());
						userData.setPhoneNumber(txtEPhone.getText());
						userData.setAddress(txtEAddress.getText());
						userData.setUserId(UserId);
						userData.setGender(eGender);
						userData.setUserType("User");
						
						User updatedUser = userRepo.UpdateUser(userData);
						
						if(updatedUser!=null) {
							UserId = updatedUser.getUserId();
							lblUserName.setText(updatedUser.getName());
							lblBalance.setText(updatedUser.getBalance().toString());
							JOptionPane.showMessageDialog(null, "User Info updated successfully!!");
							switchPanel(pnlUserProfile);
						}
						else {
							JOptionPane.showMessageDialog(null, "User Info couldn't update!!");
						}
					}
					else{
						if(txtEName.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Please Enter Name!!");
						if(txtEUserName.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Please Enter UserName!!");
						else if(txtEPassword.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Please Enter Password!!");
						else if(!(txtEPassword.getText().equals(txtEConfirmPassword.getText())))
							JOptionPane.showMessageDialog(null, "Password Doesn't Match.");
						else if(txtEPhone.getText().equals(""))
							JOptionPane.showMessageDialog(null, "Please Enter Phone Number!!");
						else if(txtEPhone.getText().length() !=11) 
							JOptionPane.showMessageDialog(null, "Please Enter 11 digit Phone Number!!");
						else if(!isPhoneNumberValid(txtEPhone.getText()))
							JOptionPane.showMessageDialog(null, "Please Enter numeric Phone Number with 11 digit!!");
						
						
					}
				}
			}
		});
		btnUpdateUser.setBounds(370, 423, 117, 29);
		pnlUserEditProfile.add(btnUpdateUser);
		
		JButton btnBackToUserProfile = new JButton("Back");
		btnBackToUserProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchPanel(pnlUserProfile);
			}
		});
		btnBackToUserProfile.setBounds(499, 423, 117, 29);
		pnlUserEditProfile.add(btnBackToUserProfile);
		
		JButton btnSearchRecieverUser = new JButton("Search");
		btnSearchRecieverUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtSearchReceiver.getText().equals("") && txtSearchReceiver.getText().length() == 11)
				{
					UserRepository userRepo = new UserRepository();
					User receiverUser = new User();
					receiverUser = userRepo.GetUserByPhoneNumber(txtSearchReceiver.getText());
					if(receiverUser != null && receiverUser.getStatus().equals("Active") && receiverUser.getUserType().equalsIgnoreCase("User"))
					{
						ReceiverUserId = receiverUser.getUserId();
						lblReceiverName.setText(receiverUser.getName());
						lblReceiverPhoneNumber.setText(receiverUser.getPhoneNumber());
					}
					else {
						ReceiverUserId=0;
						JOptionPane.showMessageDialog(null, "No User found with this phone number!!");
						ClearSearchRecieverField(txtRemarks,lblReceiverName,lblReceiverPhoneNumber);
					}
				}
				else {
					ReceiverUserId=0;
					if(txtSearchReceiver.getText().equals(""))
						JOptionPane.showMessageDialog(null, "Please Enter Phone number for Searching user!!");
					else if(txtSearchReceiver.getText().length() != 11)
						JOptionPane.showMessageDialog(null, "Please Enter 11 digit phone number!!");
				}
			}
		});
		btnSearchRecieverUser.setBounds(534, 20, 117, 29);
		pnlUserSendMoney.add(btnSearchRecieverUser);
		
		JButton btnSendMoney = new JButton("Send Money");
		btnSendMoney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(UserId!=0 && ReceiverUserId!=0 && !txtAmount.getText().equals("") 
							&& isAmountValid(txtAmount.getText()) && !txtRemarks.getText().equals("")
							&& UserId!=ReceiverUserId) 
					{
						UserRepository userRepo = new UserRepository();
						TransactionRepository transactionRepo = new TransactionRepository();
						User senderUser = new User();
						User receiverUser = new User();
						
						senderUser = userRepo.GetUserById(UserId);
						receiverUser = userRepo.GetUserById(ReceiverUserId);
						if(senderUser!=null && receiverUser!=null)
						{
							double senderCurrentBalance = senderUser.getBalance();
							double amountTobeSend = Double.parseDouble(txtAmount.getText());
							
							
							//for checking if pending request and the amount need to send is big or not
							UserRequestRepository urRepo= new UserRequestRepository();
							ArrayList<UserRequest> userRequest = urRepo.GetAllUserRequestByUserIdandRequestType(UserId,"CashOut","Pending");
							
							double totalPendingCashoutAmountRequest = 0;
							String pendingCashoutStringLine="";
							for(int i=0;i<userRequest.size();i++)
							{
								totalPendingCashoutAmountRequest+=userRequest.get(i).getRequestBalance();
								pendingCashoutStringLine+=userRequest.get(i).getRequestBalance().toString()+",";
							}
							totalPendingCashoutAmountRequest+=amountTobeSend; //previous request and latest request
							pendingCashoutStringLine = pendingCashoutStringLine.replaceAll(",$",""); // removing last coma
							
							
							
							if(senderCurrentBalance>=amountTobeSend && senderCurrentBalance>=totalPendingCashoutAmountRequest) 
							{
								Transaction transaction = new Transaction();
								transaction.setSenderId(senderUser.getUserId());
								transaction.setReceiverId(ReceiverUserId);
								transaction.setAmount(amountTobeSend);
								transaction.setRemarks(txtRemarks.getText());
								transaction.setTransactionType("Transfer");
								
								double senderBalanceAfterUpdate = senderCurrentBalance - amountTobeSend;
								double recieverBalanceAfterUpdate = receiverUser.getBalance()+amountTobeSend;
										
								Boolean isSenderBalanceUpdate = userRepo.UpdateUserBalancebyId(senderUser.getUserId(), senderBalanceAfterUpdate);
								Boolean isReceiverBalanceUpdated = userRepo.UpdateUserBalancebyId(receiverUser.getUserId(), recieverBalanceAfterUpdate);
								if(isSenderBalanceUpdate && isReceiverBalanceUpdated) 
								{
									Boolean isTransactioninserted = transactionRepo.InsertTransaction(transaction);
									if(isTransactioninserted)
									{
										ReceiverUserId = 0;
										JOptionPane.showMessageDialog(null, "Transfered Succeffully!!");
										ClearSearchRecieverField(txtRemarks, lblReceiverName, lblReceiverPhoneNumber);
										//updateSenderbalance meant lebel
										lblBalance.setText(Double.toString(senderBalanceAfterUpdate));
										ShowUserTransactionHistory(UserId);
									}
									else {
										//no money transfer
										userRepo.UpdateUserBalancebyId(senderUser.getUserId(), senderBalanceAfterUpdate+amountTobeSend);
										userRepo.UpdateUserBalancebyId(receiverUser.getUserId(), recieverBalanceAfterUpdate-amountTobeSend);
										JOptionPane.showMessageDialog(null, "OOPs! something went wrong!! Transaction Didn't succefull!!");
									}
									
								}
								else {
									if(!isSenderBalanceUpdate)
									{
										JOptionPane.showMessageDialog(null, "Sender balance not updated!!");
									}
									else if(!isReceiverBalanceUpdated)
									{
										JOptionPane.showMessageDialog(null, "Recever Balance not updated!!");
									}
								}
							}
							else {
								if(senderCurrentBalance<amountTobeSend)
									JOptionPane.showMessageDialog(null, "Not Enough Money! Please Recharge!");
								else if(senderCurrentBalance<totalPendingCashoutAmountRequest)
									JOptionPane.showMessageDialog(null, "You got already "+pendingCashoutStringLine+" amount Cash Out pending Request! and with this "+amountTobeSend+" amount you dont't have enough money to Send!! please Recharge first!");
							}
						}
						else {
							JOptionPane.showMessageDialog(null, "Sender and Receiver not found!");
						}
					}
					else {
						if(txtAmount.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter amount!!");
						}
						else if(!isAmountValid(txtAmount.getText())) {
							JOptionPane.showMessageDialog(null, "Please enter VALID amount!!");
						}
						else if(txtRemarks.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter remarks!!");
						}
						else if(UserId==ReceiverUserId) {
							JOptionPane.showMessageDialog(null, "You can't send money to yourself! :(");
						}
						else if(ReceiverUserId==0) {
							JOptionPane.showMessageDialog(null, "Couldn't find Receiver !!");
						}
					}
				}
				catch(Exception ex){
					System.out.println(ex);
				}
				
			}
		});
		btnSendMoney.setBounds(336, 320, 117, 29);
		pnlUserSendMoney.add(btnSendMoney);
		
		JButton btnAddUserBalance = new JButton("Recharge");
		btnAddUserBalance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(UserId!=0 && !txtAmountAdd.getText().equals("") && isAmountValid(txtAmountAdd.getText())) {
					UserRepository userRepo = new UserRepository();
					User user = new User();
					double amountTobeAdd = Double.parseDouble(txtAmountAdd.getText());
					user = userRepo.GetUserById(UserId);
					if(user!=null)
					{
						UserRequestRepository urRepo= new UserRequestRepository();
						UserRequest ur = new UserRequest();
						ur.setUserId(user.getUserId());
						ur.setRequestBalance(amountTobeAdd);
						ur.setRequestStatus("Pending");
						ur.setRequestType("Recharge");
						Boolean isInserted = urRepo.InsertUserRequest(ur);
						if(isInserted)
						{
							txtAmountAdd.setText("");
							ShowUserBalanceAddPendingRequestTableData(user.getUserId());
						}
						else {
							JOptionPane.showMessageDialog(null, "Couldn't place the Request!!");
						}
					}
				}
				else {
					if(txtAmountAdd.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Please enter amount!!");
					}
					else if(!isAmountValid(txtAmountAdd.getText())) {
						JOptionPane.showMessageDialog(null, "Please enter VALID amount!!");
					}
				}
			}
		});
		btnAddUserBalance.setBounds(473, 25, 89, 29);
		pnlUserAddBalance.add(btnAddUserBalance);
		
		JButton btnCashout = new JButton("Cash Out");
		btnCashout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(UserId!=0 && !txtAmountAdd.getText().equals("") && isAmountValid(txtAmountAdd.getText())) {
						UserRepository userRepo = new UserRepository();
						User user = new User();
						double amountTobeAdd = Double.parseDouble(txtAmountAdd.getText());
						user = userRepo.GetUserById(UserId);
						
						//for checking if pending request and the amount need to cash out is big or not
						UserRequestRepository urRepo= new UserRequestRepository();
						ArrayList<UserRequest> userRequest = urRepo.GetAllUserRequestByUserIdandRequestType(UserId,"CashOut","Pending");
						
						double totalPendingCashoutAmountRequest = 0;
						String pendingCashoutStringLine="";
						for(int i=0;i<userRequest.size();i++) 
						{
							totalPendingCashoutAmountRequest+=userRequest.get(i).getRequestBalance();
							pendingCashoutStringLine+=userRequest.get(i).getRequestBalance().toString()+",";
						}
						totalPendingCashoutAmountRequest+=amountTobeAdd; //previous request and latest request
						pendingCashoutStringLine = pendingCashoutStringLine.replaceAll(",$",""); // removing last coma
						
						
						if(user!=null && amountTobeAdd<=user.getBalance() && totalPendingCashoutAmountRequest<=user.getBalance())
						{
							
							UserRequest ur = new UserRequest();
							ur.setUserId(user.getUserId());
							ur.setRequestBalance(amountTobeAdd);
							ur.setRequestStatus("Pending");
							ur.setRequestType("CashOut");
							Boolean isInserted = urRepo.InsertUserRequest(ur);
							if(isInserted)
							{
								txtAmountAdd.setText("");
								ShowUserBalanceAddPendingRequestTableData(user.getUserId());
							}
							else {
								JOptionPane.showMessageDialog(null, "Couldn't place the Request!!");
							}
						}
						else
						{
							if(user == null)
								JOptionPane.showMessageDialog(null, "User Not found!");
							else if(amountTobeAdd > user.getBalance())
								JOptionPane.showMessageDialog(null, "Not Enough Balance!! please Recharge!!");
							else if(totalPendingCashoutAmountRequest > user.getBalance())
								JOptionPane.showMessageDialog(null, "You got already "+pendingCashoutStringLine+" amount pending Request! and with this "+amountTobeAdd+" amount you dont't have enough money for cash out!! please Recharge first!");
						}
					}
					else {
						if(txtAmountAdd.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Please enter amount!!");
						}
						else if(!isAmountValid(txtAmountAdd.getText())) {
							JOptionPane.showMessageDialog(null, "Please enter VALID amount!!");
						}
					}
				}
				catch(Exception ex)
				{
					System.out.println(ex);
				}
				
			}
		});
		btnCashout.setBounds(563, 25, 89, 29);
		pnlUserAddBalance.add(btnCashout);
		
		JButton btnUserRefresh = new JButton("Refresh");
		btnUserRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserRepository userRepo = new UserRepository();
				User user = new User();
				user = userRepo.GetUserById(UserId);
				if(user!=null)
				{
					lblUserName.setText(user.getName());
					lblBalance.setText(user.getBalance().toString());
					ClearSearchRecieverField(txtRemarks,lblReceiverName,lblReceiverPhoneNumber);
					ShowUserBalanceAddPendingRequestTableData(user.getUserId());
					ShowUserTransactionHistory(user.getUserId());
				}
				else {
					JOptionPane.showMessageDialog(null, "Couldn't Refresh properly!!");
				}
			}
		});
		btnUserRefresh.setBounds(127, 1, 80, 29);
		pnlUserProfile.add(btnUserRefresh);
		
		JPanel pnlAdminViewUserPendingReq = new JPanel();
		pnlAdminViewUserPendingReq.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		pnlAdminViewUserPendingReq.setBackground(Color.BLACK);
		layeredPane.add(pnlAdminViewUserPendingReq, "name_10252460577849");
		pnlAdminViewUserPendingReq.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("Recharge Pending Request:");
		lblNewLabel_10.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblNewLabel_10.setForeground(Color.GRAY);
		lblNewLabel_10.setBounds(295, 25, 193, 23);
		pnlAdminViewUserPendingReq.add(lblNewLabel_10);
		
		JButton btnBacktoAdmin = new JButton("Back");
		btnBacktoAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminViewUserRequestId = 0;
				SetAdminViewTransactionTableData();
				switchPanel(pnlAdmin);
			}
		});
		btnBacktoAdmin.setBounds(18, 5, 117, 29);
		pnlAdminViewUserPendingReq.add(btnBacktoAdmin);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(18, 53, 752, 203);
		pnlAdminViewUserPendingReq.add(scrollPane_4);
		
		tblAdminViewRequestPendingReq = new JTable();
		tblAdminViewRequestPendingReq.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdminViewUserRequestId = Integer.parseInt(tblAdminViewRequestPendingReq.getValueAt(tblAdminViewRequestPendingReq.getSelectedRow(), 0).toString());
			}
		});
		JTableHeader header9 = tblAdminViewRequestPendingReq.getTableHeader();
	      header9.setBackground(Color.black);
	      header9.setForeground(Color.white);
		scrollPane_4.setViewportView(tblAdminViewRequestPendingReq);
		
		JButton btnApproveRechargeReq = new JButton("Approve");
		btnApproveRechargeReq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AdminViewUserRequestId!=0)
				{
					UserRequestRepository urRepo = new UserRequestRepository();
					UserRepository userRepo = new UserRepository();
					User user = new User();
					UserRequest ur = new UserRequest();
					ur = urRepo.GetUserRequestById(AdminViewUserRequestId);
					if(ur!=null)
					{
						user = userRepo.GetUserById(ur.getUserId());
						int AdminUserId = UserId;
						if(user != null && AdminUserId !=0 && user.getStatus().equalsIgnoreCase("Active") && ur.getRequestType().equalsIgnoreCase("Recharge"))
						{
							// FOR REQUEST TYPE = "RECHARGE". ADMIN BECOMES SENDER, USER BECOMES RECEIVER
							
							TransactionRepository transactionRepo = new TransactionRepository();
							Transaction transaction = new Transaction();
							transaction.setSenderId(AdminUserId);
							transaction.setReceiverId(user.getUserId());
							transaction.setAmount(ur.getRequestBalance());
							transaction.setRemarks("Balanced Recharged approved by Admin.");
							transaction.setTransactionType("Recharge");
							
							double amount = ur.getRequestBalance() + user.getBalance();
							
							Boolean isUserBalanceUpdate = userRepo.UpdateUserBalancebyId(user.getUserId(), amount);
							
							if(isUserBalanceUpdate)
							{
								Boolean isRechargeStatusUpdate = urRepo.UpdateRequestStatusByIdandStatusType(ur.getId(), "Success");
								Boolean isTransactioninserted = transactionRepo.InsertTransaction(transaction);
								if(isRechargeStatusUpdate && isTransactioninserted)
								{
									ShowAdminViewRechargePendingRequestTableData();
									ShowAdminViewRechargeSuccessInfoTableData();
									AdminViewUserRequestId = 0;
								}
								else {
									userRepo.UpdateUserBalancebyId(user.getUserId(), amount-ur.getRequestBalance());
									if(!isRechargeStatusUpdate)
										JOptionPane.showMessageDialog(null, "Couldn't approve the status!!");
									else
										JOptionPane.showMessageDialog(null, "Couldn't update the Transaction!!");
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Couldn't Update User Balance!!");
							}
						}
						else if(user != null && AdminUserId !=0 && user.getStatus().equalsIgnoreCase("Active") && ur.getRequestType().equalsIgnoreCase("CashOut"))
						{
							// FOR REQUEST TYPE = "CashOut". ADMIN BECOMES RECEIVER,USER BECOMES SENDER
							
							TransactionRepository transactionRepo = new TransactionRepository();
							Transaction transaction = new Transaction();
							transaction.setSenderId(user.getUserId());
							transaction.setReceiverId(AdminUserId);
							transaction.setAmount(ur.getRequestBalance());
							transaction.setRemarks("Balance Cash Out approved by Admin.");
							transaction.setTransactionType("CashOut");
							
							
							double amount = user.getBalance()-ur.getRequestBalance();
							
							Boolean isUserBalanceUpdate = userRepo.UpdateUserBalancebyId(user.getUserId(), amount);
							
							if(isUserBalanceUpdate)
							{
								Boolean isRechargeStatusUpdate = urRepo.UpdateRequestStatusByIdandStatusType(ur.getId(), "Success");
								Boolean isTransactioninserted = transactionRepo.InsertTransaction(transaction);
								
								if(isRechargeStatusUpdate && isTransactioninserted) {
									ShowAdminViewRechargePendingRequestTableData();
									ShowAdminViewRechargeSuccessInfoTableData();
									AdminViewUserRequestId = 0;
								}
								else {
									userRepo.UpdateUserBalancebyId(user.getUserId(), amount+ur.getRequestBalance());
									if(!isRechargeStatusUpdate)
										JOptionPane.showMessageDialog(null, "Couldn't approve the status!!");
									else
										JOptionPane.showMessageDialog(null, "Couldn't update the Transaction!!");
								}
							}
							else {
								JOptionPane.showMessageDialog(null, "Couldn't Update User Balance!!");
							}
						}
						else {
							if(user == null)
							{
								JOptionPane.showMessageDialog(null, "User don't found!!");
							}
							else if(!user.getStatus().equalsIgnoreCase("Active"))
							{
								JOptionPane.showMessageDialog(null, "This user is currently blocked!!");
							}
						}
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Recharge Request not found!!");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "No Request Selected!!");
				}
			}
		});
		btnApproveRechargeReq.setBounds(668, 24, 96, 29);
		pnlAdminViewUserPendingReq.add(btnApproveRechargeReq);
		
		JLabel lblRechargeHistory = new JLabel("Recharge History");
		lblRechargeHistory.setForeground(Color.GRAY);
		lblRechargeHistory.setFont(new Font("Lucida Grande", Font.PLAIN, 14));
		lblRechargeHistory.setBounds(329, 270, 123, 23);
		pnlAdminViewUserPendingReq.add(lblRechargeHistory);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(18, 298, 752, 203);
		pnlAdminViewUserPendingReq.add(scrollPane_5);
		
		tblAdminViewRequestHistory = new JTable();
		JTableHeader header7 = tblAdminViewRequestHistory.getTableHeader();
	      header7.setBackground(Color.black);
	      header7.setForeground(Color.white);
		scrollPane_5.setViewportView(tblAdminViewRequestHistory);
		
		JButton btnDaleteRechargeReq = new JButton("Dalete");
		btnDaleteRechargeReq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(AdminViewUserRequestId!=0)
				{
					UserRequestRepository urRepo = new UserRequestRepository();
					Boolean isRequestDeleted = urRepo.DeleteUserRequestById(AdminViewUserRequestId);
					if(isRequestDeleted)
					{
						AdminViewUserRequestId= 0;
						ShowAdminViewRechargePendingRequestTableData();
						ShowAdminViewRechargeSuccessInfoTableData();
					}
					else {
						JOptionPane.showMessageDialog(null, "Can't delete the Request!!");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "No Request Selected!!");
				}
			}
		});
		btnDaleteRechargeReq.setBounds(571, 24, 96, 29);
		pnlAdminViewUserPendingReq.add(btnDaleteRechargeReq);
		
		JButton btnRefreshRequestArea = new JButton("Refresh");
		btnRefreshRequestArea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminViewUserRequestId = 0;
				ShowAdminViewRechargePendingRequestTableData();
				ShowAdminViewRechargeSuccessInfoTableData();
			}
		});
		btnRefreshRequestArea.setBounds(137, 5, 75, 29);
		pnlAdminViewUserPendingReq.add(btnRefreshRequestArea);
		
		JButton btnPending = new JButton("Pendings");
		btnPending.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowAdminViewRechargePendingRequestTableData();
				ShowAdminViewRechargeSuccessInfoTableData();
				switchPanel(pnlAdminViewUserPendingReq);
			}
		});
		btnPending.setBounds(645, 5, 117, 29);
		pnlAdmin.add(btnPending);
		
		JButton btnRefreshAdminViewUserAndTransactionTable = new JButton("Refresh");
		btnRefreshAdminViewUserAndTransactionTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetUserTableData();
				SetAdminViewTransactionTableData();
			}
		});
		btnRefreshAdminViewUserAndTransactionTable.setBounds(98, 5, 92, 29);
		pnlAdmin.add(btnRefreshAdminViewUserAndTransactionTable);
	}
}
