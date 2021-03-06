import TerminalIO.KeyboardReader;
import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class AllTheThings {
	
	public static void Random(int []array, int n){
		Random randomGenerator = new Random();
		for (int i=0; i<n; i++){
			array[i] = randomGenerator.nextInt(20);
		}
		Output(array);
	}
	
	public static void Output(int[] array){
		System.out.println("\nYour array is: ");
		for (int i=0; i < array.length; i++){
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
	
	public static void Insertion(int[]array){
		int temp, cntr=1, checker=0;
		
		while (cntr < array.length){
			
			temp = array[cntr];
			
			if (array[cntr] < array[checker]){
				for(int a = cntr; a>checker; a--){
					 array[a] = array [a-1];
				 }
				array[checker]=temp;
			}
			else{
				while (array[cntr] > array[checker]){
					checker++;
				}
				for(int a = cntr; a>checker; a--){
					 array[a] = array [a-1];
				 }
				array[checker]=temp;
			}
			checker=0;
			cntr++;
		}
		Output(array);
		
	}
	
	public static void Bubble(int[]array){
		int temp=1, cntr=1;
		boolean swaps = true;
		
		while (swaps == true){
			
			swaps = false;
			
			for (int i=0; i<array.length-cntr; i++){
				
				if (array[i] > array[i+1]){
					temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
					swaps = true;
				}
			}
			cntr++;
		}
		Output(array);
	}
	
	public static void swap(int[]array, int first, int second){
		int temp=1;
		temp = array[first];
		array[first] = array[second];
		array[second] = temp;
		
	}
	
	public static void Selection(int[]array){
		int temp = 1, cntrt = 1;
		
		for (int c=0; c<array.length-1; c++){
			
			for (int i=c; i<array.length; i++){
				
				if (array[i] <= temp){
					temp = array[i];
					cntrt = i;
				}
				
			}
			swap(array, c, cntrt);
			temp = array[c+1];
		}
		Output(array);
	}
	
	public static void Merge(){
	
		int [] array1 = new int [array.length/2];
		int [] array2 = new int [array.length-array.length/2];
		if (array.length > 1){
			
			array1 = Arrays.copyOfRange(array, 0, array.length/2);
		    array2 = Arrays.copyOfRange(array,array.length/2,array.length);
		    
			array1 = mergeSort(array1); 
			array2 = mergeSort(array2);
		}
		
		else{
			return array;
		}
		
		int one=0, two=0, cntr=0;
			
		while (((one <= array1.length)||(two <= array2.length))&&(cntr < array.length)){
			
			if (one >= array1.length){
				for (int i = cntr; two < array2.length; i++){
					array[i] = array2[two];
					two++;
				}
				two = array.length;
			}
			else if (two >= array2.length){
				for (int i = cntr; one < array1.length; i++){
					array[i] = array1[one];
					one++;
				}
				one = array.length;
			}
			else if (array1[one] <= array2[two]){
				array[cntr] = array1[one];
				one++;
			}
			else if (array1[one] >= array2[two]){
				array[cntr] = array2[two];
				two++;
			}
			cntr++;
		}
		return array;
	}
	
	public static void Linear(int search, int []array){
		Output(array);
		boolean checker = false;
		for (int i=0; i < array.length; i++){
			if (array[i] == search){
				System.out.println("\nThe number " + search + " is located at " + i);
				i = array.length;
				checker = true;
			}
		}
		if (checker == false)
			System.out.println("\nThe number you were looking for is not here. ");
	}
	
	public static void Binary(int search, int []array){
		Insertion(array);
		int mean=(array.length/2), top=array.length, bottom=0, cntr=0;
		while((array[mean] != search)&&(cntr<(array.length)/2)){
			if (search > array[mean]){
				bottom = mean;
			}
			if (search < array[mean]){
				top = mean;
			}
			mean = (top+bottom)/2;
			cntr++;
		}
		if (array[mean] == search)
			System.out.println("\nThe number " + search + " is located at " + mean);
		else
			System.out.println("\nThe number you were looking for is not here. ");
	}
	
	public static int[] fileReader(int array[]){
		KeyboardReader reader = new KeyboardReader();
		String filename=null;
		Scanner x;
		int counter = 0, length = 0;
		
		System.out.print("\nEnter the input name you would like to read from: ");
		filename=reader.readLine();
		
		try{
			
			x = new Scanner(new File(filename + ".txt"));
			
			while(x.hasNext()){
				x.next();
				counter++;
			}
			x.close();
			
		}
		catch (Exception e){
			System.out.println("Problem opening file");
		}
		
		array = new int[counter];
		length = counter;
		counter = 0;
		
		try{
			x = new Scanner(new File(filename + ".txt"));
			
			while(x.hasNext()){
				array[counter]=(Integer.parseInt(x.next()));
				counter++;
			}
			
			x.close();
		}
		catch (Exception e){
			System.out.println("Problem opening file");
		}
		
		Output(array);
		return array;
		
	}
	
	public static void main(String[] args) {
		char choice;
		int search, n, answer;
		int array[] = new int [1]; //Declare here to maintain scope of variables
		KeyboardReader q = new KeyboardReader();
		do {
			System.out.println("\nWhat would you like to do? ");
			System.out.println("\t(A) Search  ");
			System.out.println("\t(B) Sort  ");
			choice = q.readChar("Input your choice: ");
		
			if ((choice=='A')||(choice=='a')){
				System.out.println("\nWould you like to search: ");
				System.out.println("\t(A) From a file  ");
				System.out.println("\t(B) From an array of randomly generated numbers  ");
				choice = q.readChar("Input your choice: ");
				if ((choice=='A')||(choice=='a')){
					array = fileReader(array);
					search = q.readInt("\nWhat would you like to search for: ");
					System.out.println("\nWhich search would you like to preform? ");
					System.out.println("\t(1) Linear search  ");
					System.out.println("\t(2) Binary search  ");
					answer = q.readInt("Input your choice: ");
					switch(answer){
						case 1:Linear(search, array);break;
						case 2:Binary(search, array);break;
					}
					
				}
				
				else {
					n = q.readInt("\nHow large would you like the array to be? ");
					array = new int [n];
					Random(array, n);
					search = q.readInt("\nWhat would you like to search for: ");
					System.out.println("\nWhich search would you like to preform? ");
					System.out.println("\t(1) Linear search  ");
					System.out.println("\t(2) Binary search  ");
					answer = q.readInt("Input your choice: ");
					switch(answer){
						case 1:Linear(search, array);break;
						case 2:Binary(search, array);break;
					}
				}
			}
		
			else {
				System.out.println("\nWhich would like to sort? ");
				System.out.println("\t(A) A file  ");
				System.out.println("\t(B) An array of randomly generated numbers  ");
				choice = q.readChar("Input your choice: ");
				if ((choice=='A')||(choice=='a')){
					array = fileReader(array);
					System.out.println("\n\nWhich sort would you like to preform? ");
					System.out.println("\t(1) Selection  ");
					System.out.println("\t(2) Bubble  ");
					System.out.println("\t(3) Insertion  ");
					System.out.println("\t(4) Merge  ");
					answer = q.readInt("Input your choice: ");
					
					switch(answer){
						case 1: Selection(array);break;
						case 2: Bubble(array);break;
						case 3: Insertion(array);break;
						case 4: Merge();break;
						default: System.out.println("This is not a valid choice");break;
					}
				}
				
				else {
					n = q.readInt("\nHow large would you like the array to be? ");
					array = new int [n];
					Random(array, n);
					System.out.println("\nWhich sort would you like to preform? ");
					System.out.println("\t(1) Selection  ");
					System.out.println("\t(2) Bubble  ");
					System.out.println("\t(3) Insertion  ");
					System.out.println("\t(4) Merge  ");
					answer = q.readInt("Input your choice: ");
					
					switch(answer){
						case 1: Selection(array);break;
						case 2: Bubble(array);break;
						case 3: Insertion(array);break;
						case 4: Merge();break;
						default: System.out.println("This is not a valid choice");break;
					}
					
					
				}
				choice = q.readChar("\nWould you like to search your sorted array? (Y/N) ");
					if ((choice == 'Y')||(choice == 'y')){
						search = q.readInt("\nWhat would you like to search for: ");
						System.out.println("\nWhich search would you like to preform? ");
						System.out.println("\t(1) Linear search  ");
						System.out.println("\t(2) Binary search  ");
						answer = q.readInt("Input your choice: ");
						switch(answer){
							case 1:Linear(search, array);break;
							case 2:Binary(search, array);break;
						}
					}
			}
			choice = q.readChar("\nWould you like to do something else? (Y/N) ");
		}while ((choice == 'Y')||(choice == 'y'));
		
	}

}
