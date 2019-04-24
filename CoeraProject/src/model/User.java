package model;

public class User {
	
	int location = 0;
	String name;
	
	public User(int l, String n)
	{
		location = l;
		name = n;
	}
	
	public int getLocation()
	{
		return location;
	}
	
	public void setLocation(int l)
	{
		location = l;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString()
	{
		return name;
	}
}
