#include <vector>
#include <iostream>
#include "Sort.h"
#include "../heap/Heap.h"
using namespace std;

void insertionSort(vector<int> &v, int left, int right);
void insertionSort(vector<int> &v) {
	insertionSort(v, 0, v.size() - 1);
}

void insertionSort(vector<int> &v, int left, int right) {
	int cur, temp;
	for (int i = left + 1; i <= right; ++i) {
		cur = i;
		temp = v[cur];
		while (cur > left && temp < v[cur - 1]) {
			v[cur] = v[cur - 1];
			--cur;
		}
	v[cur] = temp;
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

void swap_q(int& i, int& j) {
	int temp = std::move(i);
	i = std::move(j);
	j = std::move(temp);
}

void quickSort(vector<int> &v, int left, int right);
int median3(vector<int> &v, int left, int right);
void quickSort(vector<int>& v)
{
	quickSort(v, 0, v.size() - 1);
}

static const int CUTOFF = 10;
void quickSort(vector<int>& v, int left, int right)
{
	if (left + CUTOFF <= right) {
		int&& pivot = median3(v, left, right);
		int i = left, j = right - 1;
		for (;;) {
			while (v[++i] < pivot) {}
			while (v[--j] > pivot) {}
			if (i < j)
				swap_q(v[i], v[j]);
			else
				break;
		}
		swap_q(v[i], v[right - 1]);
		quickSort(v, left, i - 1);
		quickSort(v, i + 1, right);
	}
	else
		insertionSort(v, left, right);
}

int median3(vector<int>& v, int left, int right)
{
	int mid = (left + right) / 2;
	if (v[mid] < v[left])
		swap_q(v[mid], v[left]);
	if (v[right] < v[left])
		swap_q(v[right], v[left]);
	if (v[right] < v[mid])
		swap_q(v[right], v[mid]);

	swap_q(v[mid], v[right - 1]);
	return v[right - 1];
}