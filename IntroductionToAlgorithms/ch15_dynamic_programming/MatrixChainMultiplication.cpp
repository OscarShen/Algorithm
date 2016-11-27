

#include <vector>
#include <iostream>
#define INT_MAX       2147483647
typedef std::vector<std::vector<int>> table_int;

void matrix_chain_order(const std::vector<int> &matrices,
	std::vector<std::vector<int>> &m, std::vector<std::vector<int>> &s);

void matrix_chain_order(const std::vector<int>& p, std::vector<std::vector<int>>& m, std::vector<std::vector<int>>& s)
{
	int n = p.size() - 1;
	for (int i = 0; i <= n; ++i) {
		m[i][i] = 0;
	}
	for (int l = 2; l <= n; ++l) {
		for (int i = 1; i <= n - l + 1; ++i) {
			int j = i + l - 1;
			m[i][j] = INT_MAX;
			for (int k = i; k < j; ++k) {
				int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
				if (q < m[i][j]) {
					m[i][j] = q;
					s[i][j] = k;
				}
			}
		}
	}
}

void print_optimal_parens(const std::vector<std::vector<int>> &s, int i, int j) {
	if (i == j)
		std::cout << "A" << i;
	else {
		std::cout << "(";
		print_optimal_parens(s, i, s[i][j]);
		print_optimal_parens(s, s[i][j] + 1, j);
		std::cout << ")";
	}
}

int main() {
	std::vector<int> p{ 30,35,15,5,10,20,25 };
	int n = p.size() - 1;
	std::vector<int> tmp(n + 1, 0);
	table_int m(n + 1, tmp);
	table_int s(n + 1, tmp);
	matrix_chain_order(p, m, s);
	print_optimal_parens(s, 1, n);
	std::cout << std::endl;
}