package core;

import java.util.ArrayList;
import java.util.List;

import model.*;

public class Main {
	
	public static void main(String[] args) 
	{
		Controller brain = new Controller();
		Controller brain2 = new Controller();
		
		List<Account> result = new ArrayList<Account>();
		List<Account> result2 = new ArrayList<Account>();
		
		User man = new User(0,"John Doe");
		Date ds = new Date(21,5,2020,0,0);
		Date dg = new Date(5,7,2020,0,0);
		Date dp = new Date(15,3,2020,0,0);
		
		result.add(new Account("gold",53500,man,ds));
		result.add(new Account("gold",500,man,ds));
		result.add(new Account("silver",5,man,ds));
		result.add(new Account("silver",3045,man,ds));
		result.add(new Account("platinum",100,man,ds));
		
		result2.add(new Account("gold",52500,man,ds));
		result2.add(new Account("gold",500,man,ds));
		result2.add(new Account("silver",5,man,ds));
		result2.add(new Account("silver",3045,man,ds));
		result2.add(new Account("platinum",100,man,ds));
		
		brain.printInfo(result);
		brain2.printInfo(result2);
		
		brain.redistributeMoney(result);
		brain.printInfo(result);
		
		brain2.redistributeMoneyOLD(result2);
		brain2.printInfo(result2);
		
		//brain.printInfo(result);
		
		//brain.passTime(30, result);
		
		//brain.printInfo(result);
		
		/*Date d = new Date(31,12,2,23,30);
		System.out.println(d.toString());
		//System.out.println(d.passTime(690));
		d.passTime(60);
		System.out.println(d.toString());/
		
		brain.printInfo(result);
		brain.goToATM(brain.findNextATM(0), 0, result);
		brain.printInfo(result);
		
		System.out.println(brain.waitTime(new Hour(11,35), new Hour(12,00)));
		
		/*Hour h = new Hour(12,30);
		System.out.println(h.toString());
		System.out.println(h.passTime(690));
		System.out.println(h.toString());*/
		
		
		
		//System.out.println("\n" + brain.target(result));
		
		/*Account stuff = new Account("platinum",5000,man);
		stuff.setLimit(500);
		System.out.println(stuff.toString());
		System.out.println(stuff.withdraw(5001));
		System.out.println(stuff.toString());*/
		
		/*result = brain.sortList("silver", result);
		for(Account a : result)
		{
			System.out.println(a.toString());
		}*/
	}

}
