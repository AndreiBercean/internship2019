package model;

@SuppressWarnings("rawtypes")
public class Account implements java.lang.Comparable
{
	private String type;
	private float sum = 0.0f;
	private User owner;
	private Date expiration;
	
	private boolean flag = false;
	private float limit = 0;
	
	public float getLimit() {
		return limit;
	}

	public void setLimit(float limit) {
		this.limit = limit;
	}

	public Account(String t, float s, User o, Date e)
	{
		type = t;
		sum = s;
		owner = o;
		expiration = e;
	}
	
	public void deposit (float ammount)
	{
		sum += ammount;
	}
	
	public float withdraw (float ammount)
	{
		if(ammount <= sum - limit)
		{
			sum -= ammount;
			return 0.0f;
		}
		else
		{
			ammount -= (sum - limit);
			sum = limit;
			flag = true;
			return ammount;
		}
	}
	
	public String getType()
	{
		return type;
	}
	
	public float getSum ()
	{
		return sum;
	}
	
	public User getOwner ()
	{
		return owner;
	}
	
	public void setFlag (boolean val)
	{
		flag = val;
	}
	
	public boolean getFlag ()
	{
		return flag;
	}
	
	public void compute()
	{
		switch(type)
		{
		case "silver": 
			if(sum <= 500) sum = sum + sum * 0.003f;
		    else
		    {
		    	if(sum > 5000) sum = sum + 500 * 0.003f + 4500 * 0.002f;
		        else
		        {
		        	sum = sum + 500 * 0.003f + (sum - 500) * 0.002f;
		        }
		    }
			break;
		case "gold": 
			if(sum <= 500) sum = sum + sum * 0.006f;
		    else
		    {
		    	if(sum > 5000) sum = sum + 500 * 0.006f + 4500 * 0.004f;
		        else
		        {
		        	sum = sum + 500 * 0.006f + (sum - 500) * 0.004f;
		        }
		    }
			break;
		case "platinum": 
			if(sum <= 500) sum = sum + sum * 0.009f;
		    else
		    {
		    	if(sum > 5000) sum = sum + 500 * 0.009f + 4500 * 0.005f;
		        else
		        {
		        	sum = sum + 500 * 0.009f + (sum - 500) * 0.005f;
		        }
		    }
			break;
		}
	}
	
	public boolean equals(Object obj) 
	{
		if (obj == null || obj.getClass() != this.getClass()) 
		{ 
			return false; 
		} 
		Account aux = (Account) obj;
		return sum == aux.getSum();
	}
	
	public String toString()
	{
		return "Type: " + type + " Sum: " + sum + " User: " + owner.toString();
	}

	public int compareTo(Object obj) 
	{
		return (int) (((Account)obj).getSum() - sum);
	}

	public Date getExpiration() {
		return expiration;
	}

	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
}
