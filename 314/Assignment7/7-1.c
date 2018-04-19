#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define N 4
typedef int array_t[N][N];


int dim() {
    return N;
}
void f(array_t a, int x, int y) {
	int i,j;
    int size = dim();   //size variable that calls dim()
    int xy = x*y;       //single call to x*y
    for (i = 0; i < size; ++i) {
        int ixy = i*xy;	//i*xy gets called every iteration, but not n^2 iterations
        for (j = 0; j < size; ++j) {
            a[i][j] = ixy+j;	//single addition operator in inner for loop
        }
    }
}

int main(){
	array_t a = {{6,8,4,3},{5,7,1,2},{5,7,3,6},{4,3,2,1}};	//4x4 array
	int x = 2;
	int y = 4;	
	clock_t start = clock();
	f(a,x,y);	
	clock_t end = clock();
	double time = (double)(end - start) / CLOCKS_PER_SEC;
	printf("Time: %f\n",time);
	
	return 0;
}
