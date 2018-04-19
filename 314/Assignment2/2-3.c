#include <stdio.h>

int ge(float x, float y){
	unsigned int ux = *((unsigned int*) &x); //convert x raw bits
	unsigned int uy = *((unsigned int*) &y); //convert y raw bits
	unsigned int sx = ux >> 31; //extract sign bit of ux
	unsigned int sy = uy >> 31; //extract sign bit of uy
	ux <<= 1; //drop sign bit of ux
	uy <<= 1; //drop sign bit of uy

	unsigned int case1 = (ux<<1 == 0 && uy<<1 ==0);	//both are zero if you shift it by 1 to get the sign
	unsigned int case2 = (!sx && sy);		// x >= 0, y < 0
	unsigned int case3 = (!sx && !sy && ux >= uy);	// x >= 0, y >=0
	unsigned int case4 = (sx && sy && ux <= uy);	// x < 0, y < 0

	return case1 || case2 || case3 || case4;
}
int main(){
	printf("%d\n",ge(0.0f, 0.0f));
	printf("%d\n",ge(-0.0f, 0.0f));
	printf("%d\n",ge(-1.0f, 0.0f));
	printf("%d\n",ge(0.0f, 1.0f));
	printf("%d\n",ge(1.0f, 0.0f));
	printf("%d\n",ge(0.0f, -1.0f));
}
