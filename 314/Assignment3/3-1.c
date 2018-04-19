#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

#define BUFFER_SIZE 32	//buffer size for an integer

struct IntArray{
  int length;
  int *dataPtr;
};
struct IntArray *mallocIntArray(int size)
{
  struct IntArray *returnValue = NULL;
  returnValue = (struct IntArray *)malloc(sizeof(struct IntArray));
  returnValue ->length = size;  //initializes the length with size
  returnValue ->dataPtr = (int *) malloc (sizeof(int) * size);
  for(int i=0; i < size; i++){
    returnValue->dataPtr[i] = 0;  //initialize every index to 0
  }
  return returnValue;
};

void freeintArray(struct IntArray **arrayPtr){
  if((*arrayPtr != NULL)&& ((*arrayPtr) != NULL)&& ((*arrayPtr)->dataPtr != NULL))
  {
    for(int i = 0; i < (*arrayPtr)->length; i++){
      (*arrayPtr)->dataPtr[i] = 0;  //initializes dataPtr before nulling
    }
    free((*arrayPtr)->dataPtr);
    (*arrayPtr)->dataPtr = NULL;  //sets pointer to null
    (*arrayPtr)->length = 0;  //set length to 0
    free((*arrayPtr));
    (*arrayPtr) = NULL;
  }
  else{
    fprintf(stderr, "Error, bad pointer found");  //error case
    exit(-1);
  }
}

void readintArray(struct IntArray *array){
    int num;
    bool checked = false; //boolean for base case
    char *end = NULL; //pointer for the end of the input
    char buffer[BUFFER_SIZE]; //sets buffer to the length of BUFFER_SIZE
  for(int i = 0; i < array->length; i++){
    while(fgets(buffer,sizeof(buffer),stdin)){ 
      num = strtol(buffer,&end,10); //gets the input from stdin
      if(!checked){ //base case for the first prompt
        printf("Enter int: \n");
	checked = true;
      }
      else if(end == buffer || *end != '\n'){ //error case
        printf("Error: input is not an integer \n");
      }
      else{
        break; //breaks the while loop to set num equal to the i'th index
      }
    }
    array->dataPtr[i] = num;  //sets the i'th index to the user input
    printf("Enter int: \n");
  }

}
void swap(int *xp, int *yp){
  int t0 = *xp; //initialize variables
  int t1 = *yp;
  *xp = t1; //swaps the two pointer references
  *yp = t0;
}

void sortIntArray(struct IntArray *array){  //bubblesort
  for(int i = 0; i < array->length; i++){
    for(int j = i+1; j < array->length; j++){
      if(*(array->dataPtr+i) > *(array->dataPtr+j)) { //checks if the first
                                                      //dataPtr is greater than
                                                      //the following dataPtr
        swap(&(array->dataPtr[i]), &(array->dataPtr[j]));//calls swap function
      }
    }
  }
}
void printIntArray(struct IntArray *array){
  int iter = array->length; //iterate i to the length of the array
  if(iter == 1){	//base case if the length of the array contains 1 element
    printf("[%d]",array->dataPtr[0]);
  }
  else{
    for(int i = 0; i < iter; i++){
      if( i == 0){
        printf("[%d,",array->dataPtr[i]); //prints the begining of the array
      }
      else if(i == (array->length)-1){
        printf("%d]\n",array->dataPtr[i]);  //prints the end of the array
      }
      else{
        printf("%d,",array->dataPtr[i]);  //prints every element in between the []
      }
    }
  }
}
int main(){
  int length;
  printf("Enter Length: \n");
  scanf("%d", &length);
  struct IntArray *array = mallocIntArray(length);

  readintArray(array);  //read in input from user
  sortIntArray(array);  //sort the array
  printIntArray(array); //print the array
  freeintArray(&array); //free the pointers in the array
  //printIntArray(array); //used to check if there is anything in the array


}
