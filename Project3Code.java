/* 
   JedidiahPrallNicholasRyan_03.java 
   
   @author Nicholas Ryan, Jedidiah Prall
   
   This Program takes bulk data from an input file and processes the data 
   according to a number of parameters. This program will check the data line
   by line and look for "ADD" or "DEL" to know whether to add or delete the 
   data from the output file.
   
   This program will print the data to both the console as well as to the 
   output file. It will also organize the data into a readable format that 
   will display Student information as well as Grade information and overall
   grade for the class. 
   
   Developed using both JGrasp and Eclipse IDE on Windows 10.
   
   Vocabulary Word: Pain 
      - "a localized or generalized unpleasant bodily sensation or complex 
         of sensations that causes mild to severe physical discomfort and 
         emotional distress and typically results from bodily disorder 
         (such as injury or disease); mental or emotional distress or 
         suffering."
         
   Quote: "The reward of suffering is experience." - Harry S. Truman 
          (May 8, 1884 - December 26, 1972) 
      
*/

import java.io.File;		 // Defines a file
import java.io.FileWriter; 	 // Used to write to the output file
import java.io.PrintWriter;	 // Used to print to the output file
import java.util.Scanner;    // Input stream
import java.util.ArrayList;	 // Contains object arrays, used ONLY in generateReport method

public class JedidiahPrallNicholasRyan_03 {
	
	private static List<Student> listOfStudents;	 // List of Student objects
	private static List<GradeItem> listOfGradeItems; // List of GradeItem objects
	private static String INPUT_FILE = null;  		 // Input file, declared in main method
	private static String OUTPUT_FILE = null; 		 // Output file, declared in main method
	
	/**
	 * Processes both Student and GradeItem input from the input file.  
	 * Does the following: 
	 * 	- Reads the input file (displays an error if the file is not found)
	 *  - For each line in the input file, if the first item in the line is "STUDENT"
	 *    the method calls the processStudentData method
	 *  - If the first item is "GRADE ITEM", the method calls the processGradeItemData 
	 *    method
	 *  - If either tokens are not found, the user is prompted with an error and 
	 *    the line is skipped. 
	 */
	public static void processInput() {
		int lineNum = 0; 	  // Indicates which line the reader is on
		File file;			  // Declares a file
		Scanner fileReader;	  // Declares an input stream
		String[] workingLine; // Current line in the input stream
		
		try {
			file = new File(INPUT_FILE);
			fileReader = new Scanner(file);
			
			while (fileReader.hasNext()) {
				workingLine = fileReader.nextLine().split(",");
				
				if (workingLine[0].equals("STUDENT")) {
					processStudentData(workingLine);
					
				} else if (workingLine[0].equals("GRADE ITEM")) {
					processGradeItemData(workingLine);
					
				} else {
					System.err.println("\'STUDENT\' or \'GRADE ITEM\' token not found."
								+ " Skipping line " + lineNum + ".");
				} // End if / else 
				lineNum++;
			} // End while
			
		} catch (Exception e) {
			System.err.println("File \'" + INPUT_FILE + "\' not found.");
		} // End try / catch
	} // End processInput
	// **********************************************************************************
	/**
	 * Processes Student object data with help from the List class methods. 
	 * Does the following: 
	 * - If the second token is "ADD"
	 * 	- A new Student object is instantiated and populated with information from 
	 *    the line of data. 
	 *  - Attempts to add the new Student if that Student is not in the list of
	 *    students and contains a unique student ID. 
	 *    - Displays errors if any of these criteria are incorrect
	 * - If the second token is "DEL"
	 * 	- A new Student object is instantiated and populated with information form 
	 * 	  line of data
	 *  - The List class attempts to delete the new item instantiated.
	 *  	- Outputs errors if the item does not exist in the list or if any other 
	 *        errors are thrown. 
	 * - Prompts the user if either tokens are not found.
	 * 
	 * @param info : Array of information gathered from the processInfo method
	 */
	public static void processStudentData(String[] info) {
		Student newStudent;
		boolean uniqueId = false;
		
		if (info[1].equals("ADD")) {
			try {
				newStudent = new Student(info[2], info[3], info[4], info[5]);
				uniqueId = checkStudentId(newStudent.getStudentId());
				
				if (!listOfStudents.contains(newStudent) && !uniqueId) {
					
					if(!listOfStudents.add(newStudent)) {
						
						System.err.println("Student \'" + newStudent.getStudentId()
								+ "\' could not be added to the list. Skipping this entry.");
					} else {
						System.out.println("Student \'" + newStudent.getStudentId()
						+ "\' was added to the list.");
					} // End if / else
					
				} else {
					System.err.println("Student \'" + newStudent.getStudentId()
							+ "\' already exists in the list. Skipping this entry");
					
				} // End if / else
				
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}  // End try / catch 
			
		} else if (info[1].equals("DEL")) {
			try { 
				newStudent = new Student(info[2], info[3], info[4], info[5]);
				
				if (!listOfStudents.remove(newStudent)) {
					
					System.err.println("Student \'" + newStudent.getStudentId()
							+ "\' could not be removed from the list. Skipping this entry.");
				} else {
					System.out.println("Student \'" + newStudent.getStudentId()
					+ "\' was removed from the list.");
				} // End if
				
			} catch (Exception e) {
				System.err.println(e.getMessage());
			} // End try / catch
			
		} else {
			System.err.println("\'ADD\' or \'DEL\' tokens not found in line. \'" + info[1]
					+ "\' must be one of these two tokens to be processed");
		} // End if / else
	// **********************************************************************************
	} // End processStudentData
	/**
	 * Processes GradeItem object data with help from the List class methods. 
	 * Does the following: 
	 * - If the second token is "ADD"
	 * 	- A new GradeItem object is instantiated and populated with information from 
	 *    the line of data. 
	 *  - Attempts to add the new GradeItem if that GradeItem is not in the list of
	 *    GradeItems.
	 *    - Displays errors if any of these criteria are incorrect
	 * - If the second token is "DEL"
	 * 	- A new GradeItem object is instantiated and populated with information form 
	 * 	  line of data
	 *  - The List class attempts to delete the new item instantiated.
	 *  	- Outputs errors if the item does not exist in the list or if any other 
	 *        errors are thrown. 
	 * - Prompts the user if either tokens are not found.
	 * 
	 * @param info : Array of information gathered from the processInfo method
	 */
	public static void processGradeItemData(String[] info) {
		GradeItem newGradeItem;
		
		if (info[1].equals("ADD")) {
			try {
				newGradeItem = new GradeItem(info[3], info[4], info[5], 
									info[6], info[2], info[7], info[8]);

				if (!listOfGradeItems.contains(newGradeItem)) {
					
					if (!listOfGradeItems.add(newGradeItem)) {
						
						System.err.println("Grade item \'" + newGradeItem.getGradeItemId()
								+ "\' with Student ID \'" + newGradeItem.getStudentId() 
								+ "\' could not be added to the list. Skipping this entry.");
						
					} else {
						System.out.println("Grade item \'" + newGradeItem.getGradeItemId()
						+ "\' with Student ID \'" + newGradeItem.getStudentId() 
						+ "\' was added to the list.");
					} // End if
					
				} else {
					System.err.println("Grade item \'" + newGradeItem.getGradeItemId()
						+ "\' with Student ID \'" + newGradeItem.getStudentId()
						+ "\' already exists in this list. Skipping this entry.");
				} // End if / else
				
			} catch (Exception e) {
				System.err.println(e.getMessage());
			} // End try / catch 
			
		} else if (info[1].equals("DEL")) {
			try {
				newGradeItem = new GradeItem(info[3], info[4], info[5], 
									info[6], info[2], info[7], info[8]);
				
				 if (!listOfGradeItems.remove(newGradeItem)) {
					 System.err.println("Grade item \'" + newGradeItem.getGradeItemId()
						+ "\' with Student ID \'" + newGradeItem.getStudentId()
						+ "\' cannot be deleted.");
						
				 } else {
					 System.out.println("Grade item \'" + newGradeItem.getGradeItemId()
						+ "\' with Student ID \'" + newGradeItem.getStudentId()
						+ "\' was deleted.");
				 } // End if
				
			} catch (Exception e) {
				System.err.println(e.getMessage());
			} // End try / catch 
			
		} else {
			System.err.println("\'ADD\' or \'DEL\' tokens not found in line. \'" + info[1]
					+ "\' must be one of these two tokens to be processed");
		} // End if / else
	} // End processGradeItemData
	// **********************************************************************************
	/**
	 * Helper method which checks to see if a student ID 
	 * already exists in the list of students. 
	 * 
	 * @param id : Student ID
	 * @return true, if it exists, false if not. 
	 */
	public static boolean checkStudentId(String id) {
		if (listOfStudents.toArray() != null) {
			for (Object obj : listOfStudents.toArray()) {
				
				if (obj != null) {
					Student student = (Student) obj;
					if (student.getStudentId().equals(id)) {
						return true;
					} // End if
				} // End if
			} // End for
		} // End if
		return false;
	} // End checkStudentId
	
	/**
	 * Helper method for the generateReport method which searches a 
	 * gradeItem ArrayList to look for all objects with a similar student ID. 
	 * Once those values are found, it appends them to another 
	 * array which is then returned when no more 
	 * 
	 * @param id, Student ID used to search the grade Item array for a matching value.
	 * @return ArrayList<GradeItem> : list of grade item objects which match the student ID
	 */
	public static ArrayList<GradeItem> getMatchingStudentId(String id, 
										ArrayList<GradeItem> gradeItem) {
		ArrayList<GradeItem> results = new ArrayList<GradeItem>();
		
		for (GradeItem item : gradeItem) {
			if (item.getStudentId().equals(id)) {
				results.add(item);
			} // End if
		} // End for
		return results;
	} // End getMatchingStudentId
	// **********************************************************************************
	/**
	 * Generates a formatted report printing both grade item data and student data. 
	 * For each student in the student list, this method 
	 * checks the grade item data for a coresponding student ID and prints that information 
	 * associated with each student. 
	 * 
	 * Displays and outputs this information to both the console and an output file 
	 * which is defined in the main method. 
	 */
	public static void generateReport() {
		// List of Student objects
		ArrayList<Student> studentArray = new ArrayList<Student>();		 
		// List of grade item objects
		ArrayList<GradeItem> gradeItemArray = new ArrayList<GradeItem>();
		// Student header
		String topInfo = "\nStudent Grade Report"+ 			 
                "\nStudent ID\tFirst Name\tLast Name" +
                "\tEmail Address";
		// Under student header
		String infoUnderline = "\n__________\t__________\t"+ 
								"_________\t_____________";
		// Printed under student
		String separator = "================="
				+ "============================="+	
                "===================="
                + "==================";
				
		int sumOfMaxScores = 0; 	// Sum of max scores
		int sumOfActualScores = 0;  // Sum of actual scores
		double averageScore = 0.0;  // Sum of average scores
		
		// Converts generic object array returned from list 
		// of students into an ArrayList of type Student
		if (listOfStudents.toArray() != null) {
			for (Object obj : listOfStudents.toArray()) {
				if (obj != null) {
					Student student = (Student) obj;
					studentArray.add(student);
				} // End if
			} // End for
		} // End if
		// Converts generic object array retured from list 
		// of grade items into an ArrayList of type Student
		if (listOfGradeItems.toArray() != null) {
			for (Object obj : listOfGradeItems.toArray()) {
				if (obj != null) {
					GradeItem gradeItem = (GradeItem) obj;
					gradeItemArray.add(gradeItem);
				} // End if
			} // End for
		} // End if
		// Generates the report. 
		try {
			FileWriter outputDataFile = new FileWriter(OUTPUT_FILE);
			PrintWriter writer = new PrintWriter(outputDataFile);
			
			for (Student s : studentArray) {
				// Prints to console
				System.out.println(topInfo + infoUnderline);
				System.out.println(ToolkitBasic.padString(s.getStudentId(),16,""," ")+
	                    ToolkitBasic.padString(s.getStudentFirstName(),16,""," ")+
	                    ToolkitBasic.padString(s.getStudentLastName(),16,""," ")+
	                    s.getEmailAddress() + "\nGrade items: ");
				// Prints to specified output file
				writer.println(topInfo + infoUnderline);
				writer.println(ToolkitBasic.padString(s.getStudentId(),16,""," ")+
	                    ToolkitBasic.padString(s.getStudentFirstName(),16,""," ")+
	                    ToolkitBasic.padString(s.getStudentLastName(),16,""," ")+
	                    s.getEmailAddress() + "\nGrade items: ");
				
				for (GradeItem g : getMatchingStudentId(s.getStudentId(), gradeItemArray)) {
					// Prints to console
					System.out.println (ToolkitBasic.rightPad(g.getGradeItemId(),8," ")+
	                        ToolkitBasic.padString(g.getCourseId(),10,""," ")+
	                        ToolkitBasic.padString(g.getItemType(),8,""," ")+
	                        ToolkitBasic.padString(g.getDate(),10,""," ")+
	                        ToolkitBasic.rightPad(g.getMaxScore(),10," ")+ 
	                        g.getActualScore());
					// Prints to specified output file
					writer.println(ToolkitBasic.rightPad(g.getGradeItemId(),8," ")+
	                        ToolkitBasic.padString(g.getCourseId(),10,""," ")+
	                        ToolkitBasic.padString(g.getItemType(),8,""," ")+
	                        ToolkitBasic.padString(g.getDate(),10,""," ")+
	                        ToolkitBasic.rightPad(g.getMaxScore(),10," ")+ 
	                        g.getActualScore());
					
					sumOfMaxScores += g.getMaxScore();
					sumOfActualScores += g.getActualScore();
					
					if (sumOfActualScores != 0) {
						averageScore = ((double) sumOfActualScores / sumOfMaxScores) * 100;
					} // End if
				} // End for
				// Prints to console
				System.out.println(separator + "\n" 
							+ ToolkitBasic.padString("Total: ",37,""," ")
							+ ToolkitBasic.padString(
									Integer.toString(sumOfMaxScores),9,""," ")
							+ ToolkitBasic.padString(
									Integer.toString(sumOfActualScores),5,""," ")
							+ ToolkitBasic.padString(
									Double.toString(averageScore) + " %",0,""," "));
				// Prints to specified output file
				writer.println(separator + "\n" 
						+ ToolkitBasic.padString("Total: ",37,""," ")
						+ ToolkitBasic.padString(
								Integer.toString(sumOfMaxScores),9,""," ")
						+ ToolkitBasic.padString(
								Integer.toString(sumOfActualScores),5,""," ")
						+ ToolkitBasic.padString(
								Double.toString(averageScore) + " %",0,""," "));
				
				sumOfMaxScores = 0;
				sumOfActualScores = 0;
				averageScore = 0;
			} // End for
			writer.close(); // Closes the file
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} // End try /catch 
	} // End generateReport
	// **********************************************************************************
	/**
	 * Main method
	 */
	public static void main(String[] args) {
		listOfStudents = new List<Student>();		// List of students
		listOfGradeItems = new List<GradeItem>();   // List of grade items
		
		String xx = FileNumber.getFileNumber(args, "from user");
	    INPUT_FILE = "Project_03_Input" + xx + ".txt";
	    OUTPUT_FILE = "Project_03_Output" + xx + ".txt";
	    
		processInput();
		
		generateReport();
	} // End main
} // End class
