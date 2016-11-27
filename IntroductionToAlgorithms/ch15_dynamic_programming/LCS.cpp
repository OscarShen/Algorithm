//---------------------------------------------------
// Longest Common Subsequence Problem
//---------------------------------------------------
// Length of longest common subsequence:
// Print subsequence: 
//---------------------------------------------------
// Time complexity: O (MN) is spent on constructing the matrix, 
//		O (M + N) is spent in backtracking, and O (MN) is the sum of the two.
// Space complexity: O (MN) space is used to construct the matrix. 

#include <iostream>
#include <array>
enum LCS_e
{
	left,
	up,
	left_up
};
void print(int **c, int m, int n);

//---------------------------------------------------
// ** c: Stores the maximum common subsequence length
// ** b: stores the largest common subsequence search path
//---------------------------------------------------
int LCS_length(const char *str1, const char *str2, int **c, int **b) {
	int m = strlen(str1), n = strlen(str2);
	for (int i = 0; i <= m; ++i)
		c[i][0] = 0;
	for (int j = 1; j <= n; ++j)
		c[0][j] = 0;
	print(c, m, n);
	for (int i = 1; i <= m; ++i) {
		for (int j = 1; j <= n; ++j) {
			if (str1[i - 1] == str2[j - 1]) {
				c[i][j] = c[i - 1][j - 1] + 1;
				b[i][j] = LCS_e::left_up;
			}
			else if (c[i - 1][j] >= c[i][j - 1]) {
				c[i][j] = c[i - 1][j];
				b[i][j] = LCS_e::up;
			}
			else {
				c[i][j] = c[i][j - 1];
				b[i][j] = LCS_e::left;
			}
		}
		print(c, m, n);
	}
	return c[m][n];
}

//---------------------------------------------------
//print subsequence we computed: use table b
//---------------------------------------------------
void print_LCS(int **b, char * str1, int i, int j) {
	std::cout << i << " " << j << std::endl;
	if (i == 0 || j == 0)
		return;
	if (b[i][j] == LCS_e::left_up) {
		print_LCS(b, str1, i - 1, j - 1);
		std::cout << str1[i - 1] << " ";
	}
	else if (b[i][j] == LCS_e::up) {
		print_LCS(b, str1, i - 1, j);
	}
	else
		print_LCS(b, str1, i, j - 1);
}

//---------------------------------------------------
//print subsequence we computed: use table c
//---------------------------------------------------
void print_LCS(const char * str1, const char * str2, int **c, int i, int j) {
	std::cout << i << " " << j << std::endl;
	if (i == 0 || j == 0)
		return;
	if (str1[i - 1] == str2[j - 1]) {
		print_LCS(str1, str2, c, i - 1, j - 1);
		std::cout << str1[i - 1] << " ";
	}
	else if (c[i - 1][j] >= c[i][j - 1]) {
		print_LCS(str1, str2, c, i - 1, j);
	}
	else
		print_LCS(str1, str2, c, i, j - 1);
}
//---------------------------------------------------
//print nums in table
//---------------------------------------------------
void print(int **c,int m,int n) {
	for (int i = 0; i <= m; ++i) {
		for (int j = 0; j <= n; ++j) {
			std::cout << c[i][j] << " ";
		}
		std::cout << std::endl;
	}
	std::cout << "-----------------------" << std::endl;
}

int main() {
	char *str1 = "10010101";
	char *str2 = "010110110";
	int m = strlen(str1);
	int n = strlen(str2);
	int **c, **b;
	c = new int*[m + 1];
	b = new int*[m + 1];
	for (int i = 0; i <= m; ++i) {
		c[i] = new int[n + 1];
		b[i] = new int[n + 1];
	}
	std::cout << LCS_length(str1, str2, c, b) << std::endl;
	print_LCS(b, str1, m, n);
	std::cout << std::endl;
	std::cout << "----------------------" << std::endl;
	print_LCS(str1, str2, c, m, n);
	std::cout << std::endl;
	print(b, m, n);
	for (int i = 0; i <= m; ++i) {
		delete[] c[i];
		delete[] b[i];
	}
	delete[] c;
	delete[] b;
}
