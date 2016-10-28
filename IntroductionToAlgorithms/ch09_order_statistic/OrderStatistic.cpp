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
		os::insertion_sort(v, left, right);
		return v[left + k - 1];
	}
	int p = os::randomized_partition(v, left, right);
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
		os::insertion_sort(v, l, r);
		return v[l + k - 1];
	}
	int group = (r - l + 5) / 5;
	for (int i = 0; i < group; i++) {
		int left = l + 5 * i;
		int right = (l + i * 5 + 4) > r ? r : l + i * 5 + 4;
		int mid = (left + right) / 2;
		os::insertion_sort(v, left, right);
		os::swap(v[l + i], v[mid]); 
	}
	int pivot = select(v, l, l + group - 1, (group + 1) / 2);
	int p = os::pivot_partition(v, l, r, pivot);
	int leftNum = p - l;
	if (k == leftNum + 1)
		return v[p];
	else if (k <= leftNum)
		return os::select(v, l, p - 1, k);
	else
		return os::select(v, p + 1, r, k - leftNum - 1);
}

int os::median3(std::vector<int>& v, int left, int right)
{
	int mid = (left + right) / 2;
	if (v[left] > v[right])
		os::swap(v[left], v[right]); // Ensure v[left]<=v[right]
	if (v[left] > v[mid])
		os::swap(v[left], v[mid]); // Ensure v[left]<=v[mid]
	if (v[mid] > v[right])
		os::swap(v[mid], v[right]); // Ensure v[mid]<=v[right]
	os::swap(v[mid], v[right - 1]);
	return v[right - 1];
}

void os::swap(int & i, int & j)
{
	int temp = std::move(i);
	i = std::move(j);
	j = std::move(temp);
}

int os::randomized_partition(std::vector<int>& v, int left, int right)
{
	int pivot = median3(v, left, right);
	int i = left, j = right - 1;
	for (;;) {
		while (v[++i] < pivot) {}
		while (v[--j] > pivot) {}
		if (i < j)
			os::swap(v[i], v[j]);
		else
			break;
	}
	os::swap(v[i], v[right - 1]);
	return i;
}

void os::insertion_sort(std::vector<int>& v, int left, int right)
{
	for (int i = left + 1; i <= right; ++i) {
		int temp = i, num = v[i];
		while (temp > left&&v[temp - 1] > num) {
			v[temp] = v[temp - 1];
			--temp;
		}
		v[temp] = num;
	}
}

int os::pivot_partition(std::vector<int>& v, int l, int r, int pivot)
{
	int i = l;
	int j = r;
	while (true) {
		while (v[i] <= pivot && i < r)
			++i;   //i一直向后移动，直到出现a[i]>pivot
		while (v[j] > pivot)
			--j;   //j一直向前移动，直到出现a[j]<pivot
		if (i >= j) break;
		os::swap(v[i], v[j]);
	}
	v[l] = v[j];
	v[j] = pivot;
	return j;
}

int main() {
	std::vector<int> v;
	for (int j = 0; j < 10; ++j) {
		for (int i = 0; i < 50; ++i) {
			v.push_back(rand() % 100);
		}
		int k = rand() % 100;
		int jj = os::select(v, 0, v.size() - 1, k);
		int ii = os::randomized_select(v, 0, v.size() - 1, k);
		os::insertion_sort(v, 0, v.size() - 1);
		std::cout << "ii=" << ii << " j=" << jj << " answer=" << v[k - 1] << std::endl;
	}
}