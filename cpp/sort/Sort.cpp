#include <vector>
#include <iostream>
#include "Sort.h"
#include "../heap/Heap.h"
using namespace std;

void insertionSort(vector<int> &v) {
	int n = v.size();
	for (int i = 1; i < n; ++i) {
		int cur = i;
		int temp = v[cur];
		if (cur > 1 && v[cur] < v[cur - 1]) {
			v[cur - 1] = v[cur];
			--cur;
		}
	}
}

//归并排序需要的声明
void mergeSort(vector<int>& v, vector<int>& tmp, int left, int right);
void merge(vector<int>& v, vector<int>& tmp, int leftBegin, int rightBegin, int rightEnd);

void mergeSort(vector<int>& v) {
	vector<int> temp(v.size());
	mergeSort(v, temp, 0, v.size() - 1);
}

void mergeSort(vector<int>& v, vector<int>& tmp, int left, int right) {
	if (left < right) {
		int center = (left + right) >> 1;
		mergeSort(v, tmp, left, center);
		mergeSort(v, tmp, center + 1, right);
		merge(v, tmp, left, center + 1, right);
	}
}

void merge(vector<int>& v, vector<int>& tmp, int leftBegin, int rightBegin, int rightEnd) {
	int leftEnd = rightBegin - 1;
	int cur = leftBegin;
	int n = rightEnd - leftBegin + 1;
	while (leftBegin <= leftEnd&&rightBegin <= rightEnd) {
		if (v[leftBegin] < v[rightBegin])
			tmp[cur++] = v[leftBegin++];
		else
			tmp[cur++] = v[rightBegin++];
	}
	while (leftBegin <= leftEnd) {
		tmp[cur++] = v[leftBegin++];
	}
	while (rightBegin <= rightEnd) {
		tmp[cur++] = v[rightBegin++];
	}
	for (int i = 0; i < n; i++, --rightEnd)
		v[rightEnd] = tmp[rightEnd];
}

// Prototype of quick_pure
void quickRecur(vector<int>& v, int left, int right);

// Pure quik-sort
void quick_pure(vector<int>& v) {
	quickRecur(v, 0, v.size() - 1);
}

void swap_q(int& i, int& j) {
	int temp = std::move(i);
	i = std::move(j);
	j = std::move(temp);
}

void quickRecur(vector<int>& v, int left, int right) {
	if (left >= right)
		return;
	int pivot = v[left];
	int i = left, j = right;
	while (true) {
		while (v[i] <= pivot) {
			++i;
			if (i >= j)
				break;
		}
		while (v[j] >= pivot) {
			--j;
			if (i >= j)
				break;
		}
		if (i < j)
			swap_q(v[i], v[j]);
		else {
			swap_q(v[left], v[i-1]);
			break;
		}
	}
	quickRecur(v, left, i-1);
	quickRecur(v, i, right);
}

// Heap Sort
void heapSort(vector<int>& v)
{
	for (int i = v.size() / 2; i >= 0; --i) {
		heap_algo::percolate_down(v, i, v.size());
	}
	for (int i = v.size() - 1; i > 0; --i) {
		heap_algo::heap_swap(v[0], v[i]);
		heap_algo::percolate_down(v, 0, i);
	}
}