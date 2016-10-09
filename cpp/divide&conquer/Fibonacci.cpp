#include <vector>
#include <iostream>
using namespace std;

// Recursive squaring
// (Fn+1 Fn) = (1 1)n
// (Fn Fn-1)   (1 0)

vector<int> mat2Multiply(vector<int>& mat1,vector<int>& mat2) {
	return vector<int>{
		mat1[0] * mat2[0] + mat1[1] * mat2[2],
		mat1[0] * mat2[1] + mat1[1] * mat2[3],
		mat1[2] * mat2[0] + mat1[3] * mat2[2],
		mat1[2] * mat2[1] + mat1[3] * mat2[3]
	};
}

vector<int> matrixPow(int n) {
	vector<int> mat;
	if (n == 1)
		mat = { 1,1,1,0 };
	else if (n % 2 == 0) {
		mat = matrixPow(n / 2);
		mat = mat2Multiply(mat, mat);
	}
	else {
		mat = matrixPow((n - 1) / 2);
		mat = mat2Multiply(mat, mat);
		mat = mat2Multiply(mat, vector<int>{1, 1, 1, 0});
	}
	return mat;
}

int Fibonacci(int n) {
	vector<int> mat = matrixPow(n);
	return mat[1];
}