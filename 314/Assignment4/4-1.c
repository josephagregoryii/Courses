/*
loop:
  pushl %ebp            //push base pointer to stack, save %ebp
  movl %esp, %ebp       //move the stack pointer to the base pointer
  pushl %esi            //save register %esi to the stack
  pushl %ebx            //save register %ebx to the stack
  movl 8(%ebp), %esi    //move int x into register %esi
  movl 12(%ebp), %ecx   //move int y into register %ecx as the counter to hold
                        //the loop index
  movl $2, %edx         //move mask = 2 into %edx
  movl $-1, %eax        //move result = -1 into %eax
.L2:
  movl %esi, %ebx       //move x into register %ebx
  andl %edx, %ebx       //and operationg (mask&x)
  xorl %ebx, %eax       //xor operation result ^ (mask&x)
  sall %cl, %edx        //shift %edx left by %cl bits (mask<<y)
  cmpl $1, %edx         //compare 1 to (mask<<y)
  jg .L2                // greater sign > (if mask > 1)
  popl %ebx             //remove (mask&x) from the stack
  popl %esi             //remove x from the stack
  popl %ebp             //remove base pointer
  ret                   //return result
*/
#include <stdio.h>

int loop(int x, int y){
  int result = -1 ; //movl $-1, %eax
  for (int mask = 2; mask > 1 ; mask = (mask<<y) ){   //lines 10, 18, 17
    result ^= (mask&x); //xor operation line 15
  }
  return result;  //ret line 22
}

int main(){
  printf("%d\n",loop(1,5) );
  printf("%d\n",loop(2,4) );
  printf("%d\n",loop(3,3) );
  printf("%d\n",loop(4,2) );
  printf("%d\n",loop(5,1) );
}
