package core;

import java.util.ArrayList;
import java.util.List;

import model.*;

public class Main {
	
	public static void main(String[] args) 
	{
		Controller brain = new Controller();
		
		List<Account> result = new ArrayList<Account>();
		
		User man = new User(0,"John Doe");
		Date ds = new Date(21,5,2020,0,0);
		Date dg = new Date(5,7,2020,0,0);
		Date dp = new Date(15,3,2020,0,0);
		
		result.add(new Account("gold",700,man,dg));
		result.add(new Account("silver",5000,man,ds));
		result.add(new Account("platinum",300,man,dp));
		
		brain.printInfo(result);
		brain.redistributeMoney(result);
		brain.printInfo(result);
	}

}
