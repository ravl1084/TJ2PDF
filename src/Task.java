/**
 * TJ2PDF: Script to produce a LaTeX snippet to produce a PDF version of a TJ3 Project Gantt Chart
 *
 * Task: class that contains the information for each task
 */

import java.util.Vector;
/**
 * @author Rene Vergara
 */

public class Task {
	private String name;
	private String id;
	private int duration;
	private String date;
	private String complete;
	private Vector<String> precursors = new Vector<String>();
	private boolean group = false;
	private int year;
	private int month;
	private int day;
	private int start;

	public Task(Vector<String> data){
		String str;
		this.id = data.get(0);
		this.name = data.get(1).trim();
		this.duration = (int) Double.valueOf(data.get(2)).doubleValue();
		this.date = data.get(3);
		str = data.get(4);
		//System.out.print(str);
		this.complete = str.substring(0, str.indexOf("%"));
		//System.out.println(complete);
		str = data.get(5);
		//System.out.println(str);
		int n;
		int m;
		if(str.length() > 0){
			n = str.indexOf("(");
			//System.out.println(str);
			//System.out.println(n);
			while(n >= 0){
				m = str.indexOf(")");
				if(m >= 0){
					precursors.add(str.substring(n+1,m-1));
					str = str.substring(m+1,str.length()-1);
				}
				n = str.indexOf("(");
			}
		} else {
			precursors = null;
		}
		year = Integer.valueOf(date.substring(0,date.indexOf("-")));
		//System.out.print(year);
		str = date.substring(date.indexOf("-")+1, date.length());
		month = Integer.valueOf(str.substring(0,str.indexOf("-")));
		//System.out.print(month);
		str = str.substring(str.indexOf("-")+1, str.length());
		day = Integer.valueOf(str);
		//System.out.println(day);
	}
	
	public String getTaskLatex(int y, int m, int d){
		int offset = (year - y)*360 + (month - m)*30 + day - d;
		if(duration == 0){
			return "\\ganttmilestone{"+name+"}{"+offset+"}\\\\";
		} else if(group){
			return "\\ganttgroup[progress="+complete+"]{"+name+"}{"+offset+"}{"+(offset + duration)+"}\\\\";
		} else {
			return "\\ganttbar{"+name+"}{"+offset+"}{"+(offset + duration)+"}\\\\";
		}
	}

	public String toString(){
		return id;
	}

	public void setGroup(){
		group = true;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public int getDuration(){
		return duration;
	}
	
	public String getDate(){
		return date;
	}

	public String getComplete(){
		return complete;
	}
}
