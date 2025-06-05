package DairyApp;

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

class StudentNoteKeeper{}

public class StudentNoteKeeperTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		
		int choice=4;
		String text;
		boolean isWriting = true;
//		boolean isRunning = true;
		FileWriter w = null;

		File file = null;
		
		System.out.println("1. Add notes.\n2. View all notes.\n3. Exit the program.");
		
		while(choice != 3) {
		   System.out.println("\nEnter your choice: ");
		   choice = sc.nextInt();
		
		
		switch(choice) {
		  case 1: 
			  try {
				  if (file != null) {
					  w = new FileWriter("notes.txt", true);
				  }else {
				  w = new FileWriter("notes.txt");
				  }
				  
		            System.out.println("Enter notes:\n");
		            while(isWriting) {
		              text = sc.nextLine();
		              
		              if (text.equalsIgnoreCase("n")) {
		                    isWriting = false; // Stop the loop
		                } else {
		                    w.write(text + "\n"); // Write to file with newline
		                }
		              
		            }
		            System.out.println("Successfully wrote to the file.");
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		            
		        }catch (IOException e) {
		            e.printStackTrace();
		            
		        }finally {
		        	try {
		                if (w != null) {
		                    w.close();
		                }
		            } catch (IOException e) {
		                System.out.println("Failed to close the file writer.");
		                e.printStackTrace();
		            }
		        }
			  break;
			  
		  case 2: 
			  try {
		            file = new File("notes.txt");
		            Scanner scanner = new Scanner(file);
		            while (scanner.hasNextLine()) {
		                String line = scanner.nextLine();
		                System.out.println(line);
		            }
		            scanner.close();
		        } catch (FileNotFoundException e) {
		            e.printStackTrace();
		        }
			  break;
			
		  case 3:
			  System.out.println("Exiting... Goodbye!");
			  System.exit(0);
			  
			  
		}
		}
	}

}
