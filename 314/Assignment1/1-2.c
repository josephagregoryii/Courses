#include <stdio.h>

unsigned int combine(unsigned int x, unsigned int y){
	unsigned int new_x = (~0x00FFFFFF & x);
	unsigned int new_y = (~0xFF000000 & y);
	unsigned int result = new_x|new_y;
	return result;
}

void main(){
	printf("0x%08X\n", combine(0x12345678, 0xABCDEF00));
	printf("0x%08X\n", combine(0xABCDEF00, 0x12345678));
}
