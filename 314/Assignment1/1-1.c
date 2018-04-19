#include <stdio.h>

void printBytes(unsigned char *start, int len){
	for (int i=0; i<len; i++){
		printf(" %.2x", start[i]);
	}
	printf("\n");
}

void printInt (int x){
	printBytes((unsigned char *) &x, sizeof(int));
}

void printFloat(float x){
	printBytes((unsigned char *) &x, sizeof(float));
}

void printShort(short x){
	printBytes((unsigned char *) &x, sizeof(short));
}

void printLong(long x){
	printBytes((unsigned char*) &x, sizeof(long));
}

void printDouble(double x){
	printBytes((unsigned char*) &x, sizeof(double));
}

void main(){
	printInt(16345);	//ints contain 4 bytes
	printFloat(16345);	//floats contain 4 bytes
	printShort(16345);	//shorts contain 2 bytes
	printLong(16345);	//longs contain 8 bytes
	printDouble(16345);	//doubles contain 8 bytes
}
