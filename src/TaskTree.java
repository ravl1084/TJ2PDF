/**
 * TJ2PDF: Script to produce a LaTeX snippet to produce a PDF version of a TJ3 Project Gantt Chart
 *
 * TaskTree: Class that separates the information from the CSV report and organizes the tree appropriately
 */

import java.util.Vector;
import java.io.*;

/**
 * @author Rene Vergara
 */
public class TaskTree {
	private Vector<Task> tasks;
	private String date;
	private int duration;
	private double resolution;
	private int year;
	private int month;
	private int day;
	private int yearcount;
	private int monthcount;

	public TaskTree(){
		tasks = new Vector<Task>();
	}

	public void newTask(Vector<String> data){
		Task t = new Task(data);
		System.out.println(t);
		tasks.add(t);
	}

	public void test(){
		System.out.println(tasks.get(0));
		System.out.println(tasks.get(1));
	}

	public void markGroup(){
		String str;
		int n;
		for(int i = 0; i < tasks.size(); i++){
			str = tasks.get(i).getId();
			n = 0;
			for(int k = 0; k < tasks.size(); k++){
				if(!tasks.get(k).getId().equals(str) && tasks.get(k).getId().indexOf(str) != -1){
					n++;
				}
			}
			if(n != 0){
				tasks.get(i).setGroup();
			}
		}
		date = tasks.get(0).getDate();
		//System.out.println(date);
		duration = tasks.get(0).getDuration();
		resolution = 0.700000 / (int) duration;
		year = Integer.valueOf(date.substring(0,date.indexOf("-")));
		//System.out.println(year);
		str = date.substring(date.indexOf("-")+1, date.length());
		month = Integer.valueOf(str.substring(0,str.indexOf("-")));
		//System.out.println(month);
		str = str.substring(str.indexOf("-")+1, str.length());
		day = Integer.valueOf(str);
		yearcount = 1;
		monthcount = 1;
		int daycount = day;
		int monthc = month;
		for(int j = 0; j < duration; j++){
			daycount++;
			if(daycount % 31 == 0){
				monthcount++;
				daycount = 1;
			}
			if(monthc % 13 == 0){
				yearcount++;
				monthc = 1;
			}
		}
		//System.out.println("Years: "+yearcount);
		//System.out.println("Months: "+monthcount);
	}

	public void write() throws IOException{
		PrintWriter out = new PrintWriter("Chart.txt");
		int counter;
		try{
			out.println("\\begin{ganttchart}");
			out.println("[hgrid, vgrid={*6{draw=none}, *1{dashed}},x unit="+resolution+"\\textwidth, group/.style={draw=black}, bar/.style={draw=black, fill=blue!50}]{"+(monthcount*30)+"}");
			counter = monthcount;
			for(int i=0; i < yearcount; i++){
				if(counter > 12){
					out.print("\\gantttitle{"+(year+i)+"}{360} ");
					counter-=12;
				} else {
					out.print("\\gantttitle{"+(year+i)+"}{"+(counter*30)+"} ");
				}
			}
			out.println("\\\\");
			counter = month;
			for(int j=0; j < monthcount; j++){
				if(counter > 12){
					counter = 1;
				}
				out.print("\\gantttitle{"+counter+"}{30} ");
				counter++;
			}
			out.println("\\\\");
			counter = duration;
			for(int k=0; k < tasks.size(); k++){
				out.println(tasks.get(k).getTaskLatex(year, month, day));
			}
			for(int n=0; n < tasks.size(); n++){
				Vector<String> links = new Vector<String>();
				links = tasks.get(n).linkTasks();
				if(links != null){
					for(int m = 0; m < links.size(); m++){
						out.println(links.get(m));
					}
				}
			}
			out.println("\\end{ganttchart}");
		} finally {
			out.close();
		}
	}
}
