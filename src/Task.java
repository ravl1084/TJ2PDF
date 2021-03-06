/**
 * TJ2PDF: Script to create a LaTeX snippet to produce a PDF version of a TJ3 Project Gantt Chart
 *
 * Copyright (C) 2012 Rene Vergara
 *
 * This file is part of TJ2PDF.
 *
 * TJ2PDF is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * TJ2PDF is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TJ2PDF.  If not, see <http://www.gnu.org/licenses/>.
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
					precursors.add(str.substring(n+1,m));
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
			return "\\ganttmilestone[milestone width=1.5, name="+id+"]{"+name+"}{"+offset+"}\\\\";
		} else if(group){
			return "\\ganttgroup[progress="+complete+", name="+id+"]{"+name+"}{"+offset+"}{"+(offset + duration)+"}\\\\";
		} else {
			return "\\ganttbar[name="+id+"]{"+name+"}{"+offset+"}{"+(offset + duration)+"}\\\\";
		}
	}

	public Vector<String> linkTasks(){
		Vector<String> links = null;
		if(precursors != null){
			links = new Vector<String>();
			for(int i=0; i < precursors.size(); i++){
				links.add("\\ganttlink{"+precursors.get(i)+"}{"+id+"}");
			}
		}
		return links;
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
