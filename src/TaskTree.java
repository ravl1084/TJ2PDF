/**
 * TJ2PDF: Script to produce a LaTeX snippet to produce a PDF version of a TJ3 Project Gantt Chart
 *
 * TaskTree: Class that separates the information from the CSV report and organizes the tree appropriately
 */

import java.util.Vector;

/**
 * @author Rene Vergara
 */
public class TaskTree {
	private Vector<Task> tasks;

	public TaskTree(){
		tasks = new Vector<Task>();
	}

	public void add(Vector<String> data){
		tasks.add(new Task(data));
	}

	public void markGroup(){
		String str;
		int n;
		for(int i = 0; i < tasks.size(); i++){
			str = tasks.get(i).getId();
			n = 0;
			for(int k = 0; k < tasks.size(); k++){
				if(!str.equals(tasks.get(k).getId()) && tasks.get(k).getId().indexOf(str) != -1){
					n++;
				}
			}
			if(n == 0){
				tasks.get(i).setGroup();
			}
		}
	}
}
