
// cut rod to get maxmum price
// length	1	2	3	4	5	6	7	8	9	10
// price	1	5	8	9	10	17	17	20	24	30


#include <vector>
#include <iostream>
#include <algorithm>

#define MAX(A,B) (A)>(B)?A:B

int memorized_cut_rod_aux(const std::vector<int> &prices, int n, std::vector<int> &temp) {
	if (temp[n] >= 0)
		return temp[n];
	int price;
	if (n == 0)
		price = 0;
	else {
		price = -1;
		for (int i = 1; i <= n; ++i)
			price = MAX(price, prices[i] + memorized_cut_rod_aux(prices, n - i, temp));
	}
	temp[n] = price;
	return price;
}

int memorized_cut_rod(const std::vector<int> &prices, int n) { //n is the length of steel
	std::vector<int> temp(n + 1, -1);
	return memorized_cut_rod_aux(prices, n, temp);
}

int bottom_up_cut_rod(const std::vector<int> &prices, int n) {
	std::vector<int> temp(n + 1, -1);
	temp[0] = 0;
	int price;
	for (int i = 1; i <= n; ++i) {
		price = -1;
		for (int j = 1; j <= i; ++j) {
			price = MAX(price, prices[j] + temp[i - j]);
		}
		temp[i] = price;
	}
	return price;
}

void extended_bottom_up_cut_rod(std::vector<int> &profits,
	std::vector<int> &s,const std::vector<int> &prices, int n) {
	profits[0] = 0;
	int price;
	for (int i = 1; i <= n; ++i) {
		price = -1;
		for (int j = 1; j <= i; ++j) {
			if (price < prices[j] + profits[i - j]) {
				price = prices[j] + profits[i - j];
				s[i] = j;
			}
		}
		profits[i] = price;
	}
}

void print_cut_rod_solution(const std::vector<int> &prices, int n) {
	std::vector<int> profits(n + 1, -1), s(n + 1);
	extended_bottom_up_cut_rod(profits, s, prices, n);
	while (n > 0) {
		std::cout << s[n] << "\t";
		n -= s[n];
	}
}

int main() {
	std::vector<int> prices{ 0,1,5,8,9,10,17,17,20,24,30 };

	for (int i = 1; i < 10; ++i) {
		std::cout << "length: " << i << "\t";
		int p = memorized_cut_rod(prices, i);
		std::cout << "price: " << p << std::endl;
	}
	std::cout << "=======================" << std::endl;

	for (int i = 1; i < 10; ++i) {
		std::cout << "length: " << i << "\t";
		int p = bottom_up_cut_rod(prices, i);
		std::cout << "price: " << p << std::endl;
	}

	std::cout << "=======================" << std::endl;
	print_cut_rod_solution(prices, 9);
}