#include <stdio.h>

unsigned int extract(unsigned int x, int i){
	int a = x << ((3-i)*8);	
	a = (signed)(a) >> 24;	
	return a;
}

unsigned int main(){
	printf("0x%08X\n", extract(0x12345678, 0));
	printf("0x%08X\n", extract(0xABCDEF00, 2));
}
