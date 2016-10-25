#include "OrderStatistic.h"
#include <vector>
#include <iostream>
#include "../tools/Tools.h"

int os::randomized_select(std::vector<int>& v, int k)
{
	return os::randomized_select(v, 0, v.size() - 1, k);
}

int os::randomized_select(std::vector<int>& v, int left, int right, int k)
{
	if (left + 10 > right) {
		otools::insertion_sort(v, left, right);
		return v[left + k - 1];
	}
	int p = otools::randomized_partition(v, left, right);
	int leftNum = p - left;
	if (k == leftNum + 1)
		return v[p];
	else if (k <= leftNum)
		return os::randomized_select(v, left, p - 1, k);
	else
		return os::randomized_select(v, p + 1, right, k - leftNum - 1);
}

int os::select(std::vector<int>& v, int l, int r, int k)
{
	if (r - l < 10) {
		otools::insertion_sort(v, l, r);
		return v[l + k - 1];
	}
	int group = (r - l + 5) / 5;
	for (int i = 0; i < group; i++) {
		int left = l + 5 * i;
		int right = (l + i * 5 + 4) > r ? r : l + i * 5 + 4;
		int mid = (left + right) / 2;
		otools::insertion_sort(v, left, right);
		otools::swap(v[l + i], v[mid]); 
	}
	int pivot = select(v, l, l + group - 1, (group + 1) / 2);
	int p = otools::pivot_partition(v, l, r, pivot);
	int leftNum = p - l;
	if (k == leftNum + 1)
		return v[p];
	else if (k <= leftNum)
		return os::select(v, l, p - 1, k);
	else
		return os::select(v, p + 1, r, k - leftNum - 1);
}

int main() {
	std::vector<int> v;
	for (int j = 0; j < 10; ++j) {
		for (int i = 0; i < 50; ++i) {
			v.push_back(rand() % 100);
		}
		int k = rand() % 100;
		int jj = os::select(v, 0, v.size() - 1, k);
		int ii = os::randomized_select(v, 0, v.size()-1, k);
		otools::insertion_sort(v, 0, v.size() - 1);
		std::cout << "ii=" << ii << " j=" << jj << " answer=" << v[k - 1] << std::endl;
	}
}
