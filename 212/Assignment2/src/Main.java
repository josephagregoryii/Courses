import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args){		//1
		Scanner scan = new Scanner(System.in);
		boolean running = true;
		int array_length = 0; double array_density = 0;
		do{
			try{
				System.out.println("Please enter array length");
				array_length = scan.nextInt();
				if(array_length < 1){
					throw new NegativeArraySizeException();
				}
				running = false;
			}
			catch(NegativeArraySizeException error){
				System.out.println("Invalid length");

			}
		}
		while(running);
		do{
			running = true;
			try{
				System.out.println("Please enter array density");
				array_density = scan.nextDouble();
				if(array_density < 0.0 || array_density >= 1.0){
					throw new Exception();	
				}
				running = false;
			}
			catch(Exception e){
				System.out.println("Invalid Density");
			}
		}while(running);

		long startTime1 = System.nanoTime();
		int[] dense_array = createDenseArray(array_length, array_density);					//create dense array
		double estimate1 = (System.nanoTime() - startTime1)/1000000.0;
		System.out.printf("create dense length: %d time: %f%n", array_length, estimate1);

		long startTime2 = System.nanoTime();
		ArrayList<int[]> convertdense = createSparseFromDense(dense_array);					//convert dense to sparse
		double estimate2 = (System.nanoTime() - startTime2)/1000000.0;
		System.out.printf("convert to sparse length: %d time: %f%n", convertdense.size(),estimate2);

		long startTime3 = System.nanoTime();
		ArrayList<int[]> sparse_array = createSparseArray(array_length, array_density);		//create sparse array
		double estimate3 = (System.nanoTime() - startTime3)/1000000.0;
		System.out.printf("create sparse length: %d time: %f%n", sparse_array.size(),estimate3);

		long startTime4 = System.nanoTime();
		int[] dense_array2 = createDenseFromSparse(sparse_array, array_length);
		double estimate4 = (System.nanoTime() - startTime4)/1000000.0;
		System.out.printf("convert to dense length: %d time: %f%n", array_length, estimate4);

		long startTime5 = System.nanoTime();
		max_dense(dense_array);
		double estimate5 = (System.nanoTime()-startTime5)/1000000.0;
		System.out.printf("dense find time: %f%n", estimate5);

		long startTime6 = System.nanoTime();
		max_sparse(sparse_array);
		double estimate6 = (System.nanoTime() - startTime6) /1000000.0;
		System.out.printf("sparse find time: %f%n", estimate6);

	}

	public static int[] createDenseArray(int array_length, double array_density){		//2
		int[] list = new int[array_length];
		Random random = new Random();
		for(int i = 0; i < array_length; i++){
			if(array_density > random.nextDouble()){
				list[i] = (random.nextInt(999999)+ 1);
			}else{
				list[i] = 0;
			}
		}
		return list;
	}

	public static ArrayList<int[]> createSparseArray(int array_length, double array_density){		//3
		ArrayList<int[]> sparseArray = new ArrayList(array_length);
		Random random = new Random();
		for(int i = 0; i < array_length; i++){
			if(array_density > random.nextDouble()){
				int[] tempArray = {i, (random.nextInt(999999)+1)};
				sparseArray.add(tempArray);
			}
		}
		return sparseArray;
	}

	public static ArrayList<int[]> createSparseFromDense(int[] dense_array){		//4
		Random random = new Random();
		ArrayList<int[]> sparse_array = new ArrayList<int[]>(dense_array.length);
		int i = 0;
		for(int item: dense_array){
			if (item != 0){
				int[] tempArray = {i,item};
				sparse_array.add(tempArray);
			}
			i++;
		}
		return sparse_array;
	}

	public static int[] createDenseFromSparse(ArrayList<int[]> sparse_array, int integer_length){	//5
		int[] dense_array = new int[integer_length];
		for (int i = 0; i < sparse_array.size(); i++){
			dense_array[sparse_array.get(i)[0]] = sparse_array.get(i)[1];
		}
		return dense_array;
	}

	public static void max_dense(int[] dense_array){					//6
		int max = 0; int index = 0;
		for(int i = 0; i < dense_array.length; i++){
			if (max < dense_array[i]){
				max = dense_array[i];
				index = i;
			}
		}
		System.out.printf("find max (dense): %d at %d%n", max, index);
	}
	public static void max_sparse(ArrayList<int[]> sparse_array){		//7
		int max = 0; int index = 0;
		for(int[] i: sparse_array){
			if(max < i[1]){
				max = i[1];
				index = i[0];
			}
		}
		System.out.printf("find max(sparse): %d at %d%n", max, index);
	}
}
/*
self tests observation summary
**********************************************************************************
If the array length is a large integer, it is faster to create a dense array rather than a sparse array.
While the density is lower, the times between the sparse and dense array are closer to each other than if the
density is larger.
on average, finding the max for dense arrays are a lot faster.

10000000, 0.001		-> creating dense array is faster than dense array
					-> creating to dense array is faster
					-> finding max at dense array is faster
TEST:
create dense length: 10000000 time: 282.667942
convert to sparse length: 9883 time: 29.700595
create sparse length: 9941 time: 272.857178
convert to dense length: 10000000 time: 20.336322
find max (dense): 999935 at 3454921
dense find time: 11.082042
find max(sparse): 999997 at 1007428
sparse find time: 1.633125

10000000, 0.5		-> creating dense array is faster
					-> converting to dense array is faster
					-> finding dense max is faster
TEST:
create dense length: 10000000 time: 363.372770
convert to sparse length: 4998719 time: 739.543488
create sparse length: 4999614 time: 2711.282973
convert to dense length: 10000000 time: 40.392666
find max (dense): 999999 at 776314
dense find time: 7.402846
find max(sparse): 999999 at 884253
sparse find time: 21.397150

100, 0.9			-> creating sparse array is faster than dense array
					-> converting to dense is faster
					-> finding max for dense is faster
TEST:
create dense length: 100 time: 0.766988
convert to sparse length: 91 time: 0.090819
create sparse length: 88 time: 0.179757
convert to dense length: 100 time: 0.074032
find max (dense): 996926 at 76
dense find time: 0.212711
find max(sparse): 984305 at 77
sparse find time: 0.390997


100, 0.001			-> creating sparse array is faster than dense array
					-> converting to dense is faster
					-> finding max for dense is faster
TEST:
create dense length: 100 time: 0.897689
convert to sparse length: 0 time: 0.029695
create sparse length: 0 time: 0.131524
convert to dense length: 100 time: 0.006024
find max (dense): 0 at 0
dense find time: 0.173664
find max(sparse): 0 at 0
sparse find time: 0.223690
*/
