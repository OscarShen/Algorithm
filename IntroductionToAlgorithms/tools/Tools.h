#pragma once
#ifndef OTOOLS_H__
#define OTOOLS_H__
#include <vector>

namespace otools {
	// Select 3 number of left,right and mid, then get the mid number as
	// a randomized number. It will put smallest number in left, biggest
	// in right, mid in right - 1.
	int median3(std::vector<int>& v, int left, int right);
	// Swap i & j
	void swap(int &i, int &j);
	// Randomized partition with small than pivot in left of pivot, others
	// in right of pivot, return position of pivot.
	int randomized_partition(std::vector<int>  &v, int left, int right);
}
#endif // !OTOOLS_H__
