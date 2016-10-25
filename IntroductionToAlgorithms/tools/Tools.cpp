#include "Tools.h"

int otools::median3(std::vector<int>& v, int left, int right)
{
	int mid = (left + right) / 2;
	if (v[left] > v[right])
		otools::swap(v[left], v[right]); // Ensure v[left]<=v[right]
	if (v[left] > v[mid])
		otools::swap(v[left], v[mid]); // Ensure v[left]<=v[mid]
	if (v[mid] > v[right])
		otools::swap(v[mid], v[right]); // Ensure v[mid]<=v[right]
	otools::swap(v[mid], v[right - 1]);
	return v[right - 1];
}

void otools::swap(int & i, int & j)
{
	int temp = std::move(i);
	i = std::move(j);
	j = std::move(temp);
}

int otools::randomized_partition(std::vector<int>& v, int left, int right)
{
	int pivot = median3(v, left, right);
	int i = left, j = right - 1;
	for (;;) {
		while (v[++i] < pivot) {}
		while (v[--j] > pivot) {}
		if (i < j)
			otools::swap(v[i], v[j]);
		else
			break;
	}
	otools::swap(v[i], v[right - 1]);
	return i;
}

void otools::insertion_sort(std::vector<int>& v, int left, int right)
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

int otools::pivot_partition(std::vector<int>& v, int l, int r, int pivot)
{
	//int i = left - 1, j = right + 1;
	//for (;;) {
	//	while (v[++i] < pivot) {}
	//	while (v[--j] > pivot) {}
	//	if (i < j)
	//		otools::swap(v[i], v[j]);
	//	else
	//		break;
	//}
	//return i - left;
	int i = l;
	int j = r;
	while (true) {
		while (v[i] <= pivot && i < r)
			++i;   //i一直向后移动，直到出现a[i]>pivot
		while (v[j] > pivot)
			--j;   //j一直向前移动，直到出现a[j]<pivot
		if (i >= j) break;
		otools::swap(v[i], v[j]);
	}
	v[l] = v[j];
	v[j] = pivot;
	return j;
}