#include <stdio.h>

/*
int sum(int from, int to){
  int result = 0;
  do{
    result += from;
    ++from;
  } while (from <= to);
  return result;
}

int sum(int from, int to){
  int result = 0;
loop:               //do
  result += from;
  ++from;
  if (from <= to)   //while loop
          go to loop:
  return result;
} */

int sum(int from, int to){
  int result = 0;
  //Ensure that argument *from* is in %ecx,
  //argument *to* is in %edx, *result* is in %eax = do not modify.
  __asm__("movl %0, %%ecx # from in ecx;" :: "r" ( from )); //given
  __asm__("movl %0, %%edx # to in edx;" :: "r" ( to )); //given
  __asm__("movl %0, %%eax # result in eax;" :: "r" ( result )); //given

  __asm__(
    ".loop:"
          "addl %ecx, %eax;"  //result += from
          "addl $1, %ecx;"    //++from
          "cmpl %edx, %ecx;"  //if (from <= to)
          "jle .loop;"        //if true, loop.
          );
  //do not modify
  __asm__("movl %%eax, %0 #result in eax;" : "=r" ( result ));
  return result;
}

int main(){
  printf("%d\n", sum(1,6)); //first test
  printf("%d\n", sum(3,5)); //second test
}
