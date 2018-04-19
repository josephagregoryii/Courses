#include <stdio.h>

int even(unsigned int x){
	unsigned int mask = 0x01010101;//bitmask for even spots
	unsigned int result = (mask & x);//combine any 1's in even spots
	return !!result;//take the NOT NOT result to get binary result
}

int main(){
	printf("%X\n",even(0x0));
	printf("%X\n",even(0x1));
	printf("%X\n",even(0x2));
	printf("%X\n",even(0x3));
	printf("%X\n",even(0xFFFFFFFF));
	printf("%X\n",even(0xAAAAAAAA));
}
