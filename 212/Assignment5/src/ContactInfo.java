import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;



public class ContactInfo {
	private final static ArrayList<Contact> _phonebook = new ArrayList<>();

	public static void main(String[] args){
		String num, first, last;
		String[] contact;
		Scanner input;
		// #1
		try{
			input = new Scanner(new File("phonebook.txt"));
			while (input.hasNextLine()){
				contact = input.nextLine().split(" ");
				num = contact[0];
				first = contact[2];
				last = contact[1].substring(0, contact[1].length()-1);
				Contact newContact = new Contact(num, first, last);
				_phonebook.add(newContact);
			}
			input.close();
		}
		catch(Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		invoke();

	}
	public static void output(String str){ //#2
		try{
			FileWriter output = new FileWriter("Output.txt");
			for (Contact c : _phonebook){
				if(c.getFirst().toLowerCase().contains(str.toLowerCase())
						|| c.getLast().toLowerCase().contains(str.toLowerCase())){
					output.write(c.getPhone() +  c.getLast() + ", " + c.getFirst() + System.lineSeparator());
				}
				output.close();
			}
		}
		catch(IOException ioe){
			ioe.printStackTrace();

		}

	}
	//#3
	//code below is cited: http://www.java2novice.com/java-sorting-algorithms/selection-sort/
	//used their program to structure, changed to implement ArrayList methods
	public static ArrayList<Contact> selectionSort(ArrayList<Contact> contacts){

		for (int n = 0; n < contacts.size() - 1 ; n++){
			int minIndex = n;
			for (int i = n+1; i < contacts.size(); i++){
				if (contacts.get(i).getLast().compareTo(contacts.get(minIndex).getLast()) < 0){
					minIndex = i;
				}
			}
			Contact temp = contacts.get(n);
			contacts.set(n, contacts.get(minIndex));
			contacts.set(minIndex, temp);
		}
		return contacts;
	}
	//#4
	//my mergeSort has a private method to do the sorting of the left and right lists
	//reference I used: http://stackoverflow.com/questions/28855350/java-mergesort-for-objects-of-arraylist
	public static ArrayList<Contact> mergeSort(ArrayList<Contact> contacts){
		int lowIndex = 0;
		int highIndex = contacts.size();

		if(contacts.size() == 1) return contacts;

		if (lowIndex < highIndex){
			int midIndex = contacts.size()/2;
			ArrayList<Contact> left = new ArrayList<Contact>(midIndex);
			for(int i = 0; i < midIndex; i++){
				left.add(contacts.get(i));
			}

			ArrayList<Contact> right = new ArrayList<Contact>(contacts.size() - midIndex);
			for(int j = midIndex; j < contacts.size(); j++){
				right.add(contacts.get(j));
			}

			left = mergeSort(left);
			right = mergeSort(right);
			merge(contacts, left, right);
			//merge(contacts, lowIndex, midIndex, highIndex);
		}

		return contacts;
	}
	//does the merging for each side
	private static void merge(ArrayList<Contact> contacts, ArrayList<Contact> leftList, ArrayList<Contact> rightList){
		int leftIndex = 0;
		int rightIndex = 0;

		for (int i = 0; i < contacts.size(); i++){
			if(leftIndex == leftList.size()){
				contacts.set(i, rightList.get(rightIndex));
				rightIndex++;
			}
			else {
				if(rightIndex == rightList.size()){
					contacts.set(i, leftList.get(leftIndex));
					leftIndex++;
				}
				else{
					if(leftList.get(leftIndex).getLast().compareTo(rightList.get(rightIndex).getLast()) < 0){
						contacts.set(i, leftList.get(leftIndex));
						leftIndex++;
					}
					else{
						if(leftList.get(leftIndex).getLast().compareTo(rightList.get(rightIndex).getLast()) > 0){
							contacts.set(i, rightList.get(rightIndex));
							rightIndex++;
						}
						

					}
				}
			}
		}
	}

	//#5
	public static boolean methodCheck(ArrayList<Contact> contacts){
		boolean sorted = true;
		for (int i = 1; i < contacts.size(); i++){
			if(contacts.get(i-1).getLast().compareTo(contacts.get(i).getLast()) > 0) sorted = false;
		}
		System.out.print(sorted);
		return sorted;
	}
	
	//#6
	public static void invoke(){
		long stime1 = System.nanoTime();
		ArrayList<Contact> list1 = selectionSort(_phonebook);
		double etime1 = (System.nanoTime() - stime1)/ 10000000.0;
		System.out.println("Selection Sort: " + etime1);

		long stime2 = System.nanoTime();
		ArrayList<Contact> list2 = mergeSort(_phonebook);
		double etime2 = (System.nanoTime() - stime2)/ 10000000.0;
		System.out.println("Merge Sort: " + etime2);

	}
}