#ifndef ORDER_STATISTIC_H__
#define ORDER_STATISTIC_H__

#include <vector>
namespace os {
	// Find the i-st small number in vector
	int randomized_select(std::vector<int> &v, int k);
	// Find the i-st small number between left and right in vector
	int randomized_select(std::vector<int> &v, int left, int right, int k);
	// Order statistic with linear time
	int select(std::vector<int> &v, int left, int right, int k);
	// Select 3 number of left,right and mid, then get the mid number as
	// a randomized number. It will put smallest number in left, biggest
	// in right, mid in right - 1.
	int median3(std::vector<int>& v, int left, int right);
	// Swap i & j
	void swap(int &i, int &j);
	// Randomized partition with small than pivot in left of pivot, others
	// in right of pivot, return position of pivot.
	int randomized_partition(std::vector<int>  &v, int left, int right);
	// Tool of insertion sort
	void insertion_sort(std::vector<int> &v, int left, int right);
	// The partition function with pivot
	int pivot_partition(std::vector<int> &v, int left, int right, int pivot);
}
#endif // !ORDER_STATISTIC_H__
