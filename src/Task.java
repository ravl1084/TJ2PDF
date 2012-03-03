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
	private boolean group;

	public Task(Vector<String> data){
		String str;
		id = data.get(0);
		name = data.get(1).trim();
		duration = Integer.valueOf(data.get(2));
		date = data.get(3);
		complete = data.get(4);
		str = data.get(5);
		int n;
		int m;
		if(str.length() > 0){
			n = str.indexOf("(");
			while(n > 0){
				m = str.indexOf(")");
				if(m > 0){
					precursors.add(str.substring(n,m));
				}
				str = str.substring(m,str.length());
				n = str.indexOf("(");
			}
		}
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
