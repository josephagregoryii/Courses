
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void inner(float *u, float *v, int length, float *dest){
    int i;
    float sum = 0.0f;
    for (i = 0; i < length; i++){	//normal for loop, one element executed per iteration
        sum += u[i] * v[i];
    }
    *dest = sum;
}

void inner2(float *u, float *v, int length, float *dest){
    int i;
    float sum = 0.0f;
    int nlength = length - 3;	//less times of iteration
    for (i = 0; i<nlength; i+=4){
        sum += u[i] * v[i];		
        sum += u[i+1] * v[i+1];
        sum += u[i+2] * v[i+2];
        sum += u[i+3] * v[i+3];	//4 elements put per each iteration
    }	
    *dest = sum;
}
int main(){
    int length = 1000000;
    
    float destination = 0;
    float *dest = &destination;
    
    float a[length];
    float b[length];
    int i;
    for(i= 0; i < length; i++){
        a[i] = (float)i;
        b[i] = (float)(length-1);
    }
    double cpu_time;
    clock_t start, end;
    start = clock();		//'start' the clock
    inner(a,b,length,dest);	//call to inner
    end = clock();			//'end' the clock
    cpu_time = (double)(end - start) / CLOCKS_PER_SEC;
    printf("Total from inner: %f\n", cpu_time);
    
    double cpu_time2;
    clock_t start2, end2;
    start2 = clock();	//'start' the clock
    inner2(a,b,length,dest);	//call to inner2
    end2 = clock();		//'end'the clock
    cpu_time2 = (double)(end2-start2) / CLOCKS_PER_SEC;
    printf("Total from inner2: %f\n", cpu_time2);
    
    return 0;
}
