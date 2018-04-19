/*
.L3:
  movl (%edx), %esi   //get the *ptr of the start address plus (i*N+j)
  movl (%eax), %ecx   //get the *ptr of the start address plus (j*N+j)
  addl $4, %eax       //increment j
  addl $40, %edx      //increment i
  movl %esi, -4(%eax) //move start_address+(j*N+i) to t1
  movl %ecx, -40(edx) //move start_address+(i*N+j) to t0
  cmpl %ebx, %eax     //compare i & N
  jne  .L3            //j != (i != N)
*/

#include <stdio.h>
#define N 4
typedef int array_t[N][N];

void transpose(array_t a){
  int i,j;  //initialize
  int *start_address = &a[0][0];  //allocate the address for the initial array
  for (i = 0; i != N; i++){
    for (j = 0; j != i; j++){
      int* t0 = start_address + (i*N+j); //add the current address with i and j
      int* t1 = start_address + (j*N+i); //add the current address with j and i
      int temp; //initialize
      temp = *t0;  //set pointer address to temp
      *t0 = *t1;  //swap *y with *x
      *t1 = temp;  //set temp (*y) to *x
    }
  }
}

void print_array(array_t a){ //print array func to get the elements of array_t
  int i,j;
  printf("{");  //beggining of entire array
  for(i=0; i < N; i++){
    printf("{");  //beggining of i'th array
    for (j = 0; j<N; j++  ){
      if (j == N-1){  //if its the last element in the array:
        printf("%d",a[i][j]); //print only the element
      }else{
        printf("%d, ", a[i][j]);
      }
    }
    if (i == N-1){
      printf("}");  //close the last array set
    }else{
      printf("}, ");  //close the array, seperate for the next array
    }
  }
  printf("}\n");  //close off entire set
}
void main(){
  //a is for test array if N=4.
  array_t a = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
  transpose(a);
  print_array(a);
}
