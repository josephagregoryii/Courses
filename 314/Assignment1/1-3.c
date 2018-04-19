#include "stdio.h"

unsigned int replace(unsigned int x, int i, unsigned char b)
{
	int mask = 0xFF << (i << 3);//shifts the mask over to i of x
	int shift = (b << (i << 3));//shifts the byte to i of x
	int compl_mask = ~mask;	//compliment of mask
	int mask_x = compl_mask & x;//compliment of mask AND original byte
	return mask_x | shift;//take the OR of mask_x and shift 
}

void main()
{
	printf("0x%08X\n",replace(0x12345678,2,0xAB));
	printf("0x%08X\n",replace(0x12345678,0,0xAB));
}
