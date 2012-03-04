/**
 * TJ2PDF: Script to produce a LaTeX snippet to produce a PDF version of a TJ3 Project Gantt Chart
 */
import java.io.*;
import java.util.Vector;
/**
 * @author Rene Vergara
 */

public class GanttMaker {

	private TaskTree plan;

	public GanttMaker() {
		plan = new TaskTree();
	}

	public void go() {
		int length;
		String str;
		try{
			BufferedReader in = new BufferedReader(new FileReader("Print.csv"));
			Vector<String> data;
			int n;
			str = in.readLine();
			while((str = in.readLine()) != null){
				data = new Vector<String>();
				str = str.replaceAll("\"", "");
				n = str.indexOf(";");
				//System.out.println(str);
				while (n >= 0){
					length = str.length();
					//System.out.println(length);
					data.add(str.substring(0, n));
					//System.out.println(str.substring(0, n));
					str = str.substring(n+1, length);
					//System.out.println(str);
					n = str.indexOf(";");
					//System.out.println(n);
				}
				length = str.length();
				if(length >= 0){
					data.add(str);
				}
				for(int i = 0; i < data.size(); i++){
					data.set(i, data.get(i).replaceAll("\"", ""));
					//data.remove(0);
				}
				//System.out.println(data);
				plan.newTask(data);
			}
			plan.markGroup();
			plan.write();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("TJ2PDF: Creating a printable Gantt Chart.");
	}

	public static void main (String [] args){
		GanttMaker gm = new GanttMaker();
		gm.go();
	}
}
