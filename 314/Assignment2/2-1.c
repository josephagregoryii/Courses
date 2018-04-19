#include <stdio.h>

int mask(int n){
	int y = (unsigned long long)(1)<<n;
	return y-1;
}

int main(){
	printf("0x%X\n", mask(1));
	printf("0x%X\n", mask(2));
	printf("0x%X\n", mask(3));
	printf("0x%X\n", mask(5));
	printf("0x%X\n", mask(8));
	printf("0x%X\n", mask(16));
	printf("0x%X\n", mask(32));
}

