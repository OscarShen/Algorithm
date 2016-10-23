#include "Tools.h"

int otools::median3(std::vector<int> & v, int left, int right)
{
	int mid = (left + right) / 2;
	if (v[mid] < v[left])
		otools::swap(v[mid], v[left]);
	if (v[right] < v[left])
		otools::swap(v[right], v[left]);
	if (v[right] < v[mid])
		otools::swap(v[right], v[mid]);
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
	if (right - left < 2) {
		if (v[right] < v[left])
			otools::swap(v[right], v[left]);
		return left;
	}
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