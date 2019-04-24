package model;

public class ATM 
{
	private int id;
	private Hour openTime;
	private Hour closeTime;
	private float funds;
	
	public ATM(int i, Hour o, Hour c, float f)
	{
		id = i;
		openTime = o;
		closeTime = c;
		funds = f;
	}

	public Hour getOpenTime() {
		return openTime;
	}

	public Hour getCloseTime() {
		return closeTime;
	}

	public float getFunds() {
		return funds;
	}

	public float withdraw(float ammount)
	{	
		if(ammount <= funds)
		{
			funds -= ammount;
			return 0.0f;
		}
		else
		{
			ammount -= funds;
			funds = 0;
			return ammount;
		}
	}
	
	public void setFunds(float funds) {
		this.funds = funds;
	}

	public int getId() {
		return id;
	}
}
