	/*@SuppressWarnings("unchecked")
	public List<Account> redistributeMoney(List<Account> accounts) 
	{
		int cashLimit;
		
		Collections.sort(accounts);
		List<Account> result = new ArrayList<Account>();
		
		List<Account> silver = sortList("silver", accounts);
		List<Account> gold = sortList("gold", accounts);
		List<Account> platinum = sortList("platinum", accounts);
		
		User owner = accounts.get(0).getOwner();
		
		for(Account a : platinum)
		{
			a.setLimit(500);
			if(a.getSum() < 500)
			{
				float value = 500 - a.getSum();
				a.setFlag(true);
				while(a.getSum() < 500 && value != 0 && target(accounts) != null)
				{
					Account t = target(accounts);
					float remainder = t.withdraw(value);
					a.deposit(value - remainder);
					value = remainder;
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
				while(a.getSum() < 500 && value != 0 && target(accounts) != null)
				{
					Account t = target(accounts);
					float remainder = t.withdraw(value);
					a.deposit(value - remainder);
					value = remainder;
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
				while(a.getSum() < 5000 && value != 0 && target(accounts) != null)
				{
					Account t = target(accounts);
					float remainder = t.withdraw(value);
					a.deposit(value - remainder);
					value = remainder;
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
				while(a.getSum() < 5000 && value != 0 && target(accounts) != null)
				{
					Account t = target(accounts);
					float remainder = t.withdraw(value);
					a.deposit(value - remainder);
					value = remainder;
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
				while(a.getSum() < 500 && value != 0 && target(accounts) != null)
				{
					Account t = target(accounts);
					float remainder = t.withdraw(value);
					a.deposit(value - remainder);
					value = remainder;
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
				while(a.getSum() < 5000 && value != 0 && target(accounts) != null)
				{
					Account t = target(accounts);
					float remainder = t.withdraw(value);
					a.deposit(value - remainder);
					value = remainder;
				}
			}
		}
		return result;
	}*/