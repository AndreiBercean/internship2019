package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.*;

public class Controller 
{
	private int travelTime[][] = { 	{ 00, 05, 60, 30, 45 }, 
									{ 05, 00, 40, 40, 45 }, 
									{ 60, 40, 00, 15, 30 },
									{ 30, 40, 15, 00, 15},
									{ 45, 45, 30, 15, 00}};
	
	private Date date = new Date(19,3,2019,11,30);
	
	private ATM ATMS[] = {new ATM(0, new Hour(00,00), new Hour(00,00),0),
						  new ATM(1, new Hour(12,00), new Hour(18,00),5000),
						  new ATM(2, new Hour(10,00), new Hour(17,00),5000),
					 	  new ATM(3, new Hour(22,00), new Hour(13,00),5000),
						  new ATM(4, new Hour(17,00), new Hour(01,00),5000)};
	
	public boolean arrival(int mins, Hour h, Hour limit)
	{
		Hour test = new Hour(h.getHour(), h.getMinute());
		test.passTime(mins);
		
		if(test.getHour() <= limit.getHour() && test.getMinute() < limit.getMinute()) return true;
		return false;
	}
	
	public int waitTime(Hour start, Hour end)
	{
		int hours, minutes = 0;

		if(start.getHour() > end.getHour())
			hours = (24 - start.getHour()) + end.getHour();
		else 
			hours = end.getHour() - start.getHour();
		
		if(start.getMinute() > end.getMinute())
		{
			hours--;
			minutes = (60 - start.getMinute()) + end.getMinute(); 
		}
		else
			minutes = end.getMinute() - start.getMinute();
		
		return hours * 60 + minutes;
	}
	
	public ATM findNextATM(int ID)
	{
		int score[] = new int[5];
		for(int i = 1; i <= 4; i++)
		{
			if(ATMS[i].getFunds() > 0)
			{
				score[i] = travelTime[ID][i];
				
				if(!arrival(travelTime[ID][i], date.getTime(), ATMS[i].getCloseTime()))
					score[i] += waitTime(date.getTime(), ATMS[i].getOpenTime());
				else
				{
					Hour h = new Hour(date.getTime().getHour(),date.getTime().getMinute());
					h.passTime(travelTime[ID][i]);
					if(!(h.getHour() >= ATMS[i].getOpenTime().getHour() && h.getMinute() >= ATMS[i].getOpenTime().getMinute()))
						score[i] += waitTime(date.getTime(), ATMS[i].getOpenTime());
				}
			}
			else score[i] = -1;
		}
		score[ID] = -1;
		int min = 999999999, pos = 1;
		for(int j = 1; j <= 4; j++)
		{
			if(score[j] < min && score[j] != -1)
			{
				pos = j;
				min = score[j];
			}
		}
		return ATMS[pos];
	}
	
	public void goToATM(ATM dest, int ID, List<Account> accounts)
	{
		passTime(travelTime[ID][dest.getId()],accounts);
		
		Hour h = new Hour(date.getTime().getHour(),date.getTime().getMinute());
		if(!(h.getHour() >= ATMS[dest.getId()].getOpenTime().getHour() && h.getMinute() >= ATMS[dest.getId()].getOpenTime().getMinute()))
		{
			passTime(waitTime(h, ATMS[dest.getId()].getOpenTime()),accounts);
		}
	}
	
	public void passTime(int mins, List<Account> accounts)
	{
		int year,newYear;
		year = date.getYear();
		date.passTime(mins);
		newYear = date.getYear();
		for(int i = 0; i < (newYear - year); i++)
		{
			for(Account a : accounts)
			{
				a.compute();
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Account> redistributeMoney(List<Account> accounts) 
	{	
		Collections.sort(accounts);
		List<Account> result = new ArrayList<Account>();
		
		List<Account> silver = sortList("silver", accounts);
		List<Account> gold = sortList("gold", accounts);
		List<Account> platinum = sortList("platinum", accounts);
		
		User owner = accounts.get(0).getOwner();
		ATM newATM = findNextATM(owner.getLocation());
		goToATM(newATM, owner.getLocation(), result);
		owner.setLocation(newATM.getId());
		
		for(Account a : platinum)
		{
			a.setLimit(500);
			if(a.getSum() < 500)
			{
				float value = 500 - a.getSum();
				a.setFlag(true);
				while(a.getSum() < 500 && value > 0 && target(accounts) != null)
				{
					Account t = target(accounts);
					
					if(value < newATM.getFunds())
					{
						float remainder = t.withdraw(value);
						newATM.withdraw(value - remainder);
						a.deposit(value - remainder);
						value = remainder;
					}
					else
					{
						float remainder = newATM.withdraw(value);
						t.withdraw(value - remainder);
						a.deposit(value - remainder);
						value = remainder;
						
						
						newATM = findNextATM(owner.getLocation());
						goToATM(newATM, owner.getLocation(), result);
						owner.setLocation(newATM.getId());
						
						remainder = t.withdraw(value);
						newATM.withdraw(value - remainder);
						a.deposit(value - remainder);
						value = remainder;
					}
				}
			}
		}
		
		for(Account a : gold)
		{
			a.setLimit(500);
			if(a.getSum() < 500)
			{
				float value = 500 - a.getSum();
				a.setFlag(true);
				while(a.getSum() < 500 && value > 0 && target(accounts) != null)
				{
					Account t = target(accounts);
					
					if(value < newATM.getFunds())
					{
						float remainder = t.withdraw(value);
						newATM.withdraw(value - remainder);
						a.deposit(value - remainder);
						value = remainder;
					}
					else
					{
						float remainder = newATM.withdraw(value);
						t.withdraw(value - remainder);
						a.deposit(value - remainder);
						value = remainder;
						
						
						newATM = findNextATM(owner.getLocation());
						goToATM(newATM, owner.getLocation(), result);
						owner.setLocation(newATM.getId());
						
						remainder = t.withdraw(value);
						newATM.withdraw(value - remainder);
						a.deposit(value - remainder);
						value = remainder;
					}
				}
			}
		}
		
		for(Account a : platinum)
		{
			a.setLimit(5000);
			if(a.getSum() < 5000)
			{
				float value = 5000 - a.getSum();
				a.setFlag(true);
				while(a.getSum() < 5000 && value > 0 && target(accounts) != null)
				{
					Account t = target(accounts);
					
					if(value < newATM.getFunds())
					{
						float remainder = t.withdraw(value);
						newATM.withdraw(value - remainder);
						a.deposit(value - remainder);
						value = remainder;
					}
					else
					{
						float remainder = newATM.withdraw(value);
						t.withdraw(value - remainder);
						a.deposit(value - remainder);
						value = remainder;
						
						newATM = findNextATM(owner.getLocation());
						goToATM(newATM, owner.getLocation(), result);
						owner.setLocation(newATM.getId());
						
						remainder = t.withdraw(value);
						newATM.withdraw(value - remainder);
						a.deposit(value - remainder);
						value = remainder;
					}
				}
			}
		}
		
		for(Account a : gold)
		{
			a.setLimit(5000);
			if(a.getSum() < 5000)
			{
				float value = 5000 - a.getSum();
				a.setFlag(true);
				while(a.getSum() < 5000 && value > 0 && target(accounts) != null)
				{
					Account t = target(accounts);
					
					if(value < newATM.getFunds())
					{
						float remainder = t.withdraw(value);
						newATM.withdraw(value - remainder);
						a.deposit(value - remainder);
						value = remainder;
					}
					else
					{
						float remainder = newATM.withdraw(value);
						t.withdraw(value - remainder);
						a.deposit(value - remainder);
						value = remainder;
						
						newATM = findNextATM(owner.getLocation());
						goToATM(newATM, owner.getLocation(), result);
						owner.setLocation(newATM.getId());
						
						remainder = t.withdraw(value);
						newATM.withdraw(value - remainder);
						a.deposit(value - remainder);
						value = remainder;
					}
				}
			}
		}
		
		for(Account a : silver)
		{
			a.setLimit(500);
			if(a.getSum() < 500)
			{
				float value = 500 - a.getSum();
				a.setFlag(true);
				while(a.getSum() < 500 && value > 0 && target(accounts) != null)
				{
					Account t = target(accounts);
					
					if(value < newATM.getFunds())
					{
						float remainder = t.withdraw(value);
						newATM.withdraw(value - remainder);
						a.deposit(value - remainder);
						value = remainder;
					}
					else
					{
						float remainder = newATM.withdraw(value);
						t.withdraw(value - remainder);
						a.deposit(value - remainder);
						value = remainder;
						
						newATM = findNextATM(owner.getLocation());
						goToATM(newATM, owner.getLocation(), result);
						owner.setLocation(newATM.getId());
						
						remainder = t.withdraw(value);
						newATM.withdraw(value - remainder);
						a.deposit(value - remainder);
						value = remainder;
					}
				}
			}
		}
		
		for(Account a : silver)
		{
			a.setLimit(5000);
			if(a.getSum() < 5000)
			{
				float value = 5000 - a.getSum();
				a.setFlag(true);
				while(a.getSum() < 5000 && value > 0 && target(accounts) != null)
				{
					Account t = target(accounts);
					
					if(value < newATM.getFunds())
					{
						float remainder = t.withdraw(value);
						newATM.withdraw(value - remainder);
						a.deposit(value - remainder);
						value = remainder;
					}
					else
					{
						float remainder = newATM.withdraw(value);
						t.withdraw(value - remainder);
						a.deposit(value - remainder);
						value = remainder;
						
						newATM = findNextATM(owner.getLocation());
						goToATM(newATM, owner.getLocation(), result);
						owner.setLocation(newATM.getId());
						
						remainder = t.withdraw(value);
						newATM.withdraw(value - remainder);
						a.deposit(value - remainder);
						value = remainder;
					}
				}
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public Account target(List<Account> accounts)
	{
		Collections.sort(accounts);
		List<Account> aux = new ArrayList<Account>();

		aux.addAll(sortList("silver", accounts));
		aux.addAll(sortList("gold", accounts));
		aux.addAll(sortList("platinum", accounts));
		
		for(Account a : aux)
		{
			if(5000 < a.getSum() && !a.getFlag())return a;
		}
		for(Account a : aux)
		{
			if(0 != a.getSum() && !a.getFlag()) return a;
		}
		return null;
	}
	
	public List<Account> sortList(String type, List<Account> input)
	{	
		List<Account> result = new ArrayList<Account>();
		if(type == "all")
		{
			result.addAll(sortList("silver", input));
			result.addAll(sortList("gold", input));
			result.addAll(sortList("platinum", input));
		}
		else
		{
			for(Account a : input)
			{
				if(type == a.getType())result.add(a);
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public void printInfo(List<Account> accounts)
	{
		Collections.sort(accounts);
		List<Account> result = sortList("all",accounts);
		
		for(Account a : result)
		{
			System.out.println(a.toString());
		}
		System.out.println(date.toString());
		System.out.println();
	}
	
}
