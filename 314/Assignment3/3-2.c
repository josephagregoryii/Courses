#include <stdio.h>

/*
x at %ebp+8,y at %ebp+12,z at %ebp+16	int decode(intx, int y, intz)
movl 12(%ebp), %edx	move y into edx
subl 16(%ebp), %edx	subtract z from edx -> (y-z)
movl %edx, %eax		move edx (y-z) into eax
imull 8(%ebp), %edx	multiplies x into edx -> x*(y-z)
sall $31, %eax		arithmatic shift left using eax -> ((y-z) << 31)
sarl $31, %eax		arithmatic shift right using eax -> (((y-z) << 31) >> 31)
xorl %edx, %eax		exclusive or of edx or eax -> x*(y-z) ^ ((y-z)>>31)
*/

int decode(int x, int y, int z){
    (y-z);
    x*(y-z);
    (((y-z)<<31)>>31);
    return (x*(y-z)^((y-z)<<31)>>31);
}

int main(){
    printf("%d\n",decode(1,2,4));
    printf("%d\n",decode(-4,-8,-12));
}
