package model;

public class Hour {
	int hour = 0;
	int minute = 0;
	
	public String toString() {
		return hour + ":" + minute;
	}
	
	public Hour(int h, int m)
	{
		hour = h;
		minute = m;
	}
	
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	public int passTime(int mins)
	{
		int days = 0;
		while((minute + mins) > 59)
		{
			if(hour < 23)hour++;
			else
			{
				hour = 0;
				days ++;
			}
			mins -= 60;
		}
		minute += mins;
		return days;
	}
}
