package model;

public class Date 
{
	private int year = 0;
	private int month = 1;
	private int day = 1;
	private Hour time;
	
	private int maxDays[] = {0,31,28,31,30,31,30,31,31,30,31,30,31};
	
	public Date(int d, int m, int y, int hour, int minute)
	{
		year = y;
		month = m;
		day = d;
		time = new Hour(hour, minute);
	}
	
	public void passTime(int mins)
	{
		int days = time.passTime(mins);
		while((day + days) > maxDays[month])
		{
			days -= maxDays[month];
			if(month < 11)month++;
			else
			{
				month = 1;
				year++;
			}
		}
		day += days;
	}
	
	public String toString()
	{
		return day + "." + month + "." + year + " " + time.toString();
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Hour getTime() {
		return time;
	}

	public void setTime(Hour time) {
		this.time = time;
	}

}
