import java.sql.*;
import java.util.Scanner;

import db.sqlUtils;
import model.Server;

import java.io.*;

public class MainApp
{
	private static Scanner scanner;

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		boolean running = true;

		scanner = new Scanner(System.in);
		System.out.print("Please Enter the type of service : ");
		System.out.println("help ,quit ,countServers ,addServer ,deleteServer ,editServer ,listServers");
		String option = scanner.next();

		sqlUtils dbfunctions = new sqlUtils();

		while (running) {
			if (option.equals("help")) {
				
				showHelp();
				
			} else if (option.equals("quit")) {
				
				running = false;
				break;
				
			} else if (option.equals("countServers")) {
				
				System.out.println("Servers Count  =  " + dbfunctions.serversCount());

			} else if (option.equalsIgnoreCase("addServer")) {
				Server ser = new Server();

				System.out.println("Please Enter the Server ID");
				option = scanner.next();
				ser.setId(Integer.parseInt(option));
				System.out.println("Please Enter the Server Name");
				option = scanner.next();
				ser.setName(option);
				dbfunctions.insertNewServer(ser);

			} else if (option.equalsIgnoreCase("deleteServer")) {
				System.out.println("Please Enter the Server ID");
				option = scanner.next();
				dbfunctions.deleteServer(option);
			} else if (option.equalsIgnoreCase("editServer")) {
				
				System.out.println("Please Enter the Server ID");
				option = scanner.next();
				System.out.println("Please Enter the Server New Name");
				String option2 = scanner.next();
				dbfunctions.updateServer(option, option2);
				
			} else if (option.equalsIgnoreCase("listServers")) {
				
				dbfunctions.getAllServers();
				
			}else {
				
				System.out.print("you Entered an unlisted option can you Enter the option again please");
			}
			
			option = scanner.next();
		}
	}

    private static void showHelp()
    {
	System.out.println("help to display this message");
	System.out.println("countServers to display the current number of servers present");
	System.out.println("addServer to display the current number of servers present");
	System.out.println("editServer to change the name of a server identified by id (takes 2 additional args - the id and the new name)");
	System.out.println("deleteServer to delete a server (takes one more arg - the id of the server to delete)");
	System.out.println("listServers to display details of all servers servers");
    }
}
