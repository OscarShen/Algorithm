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
}

#endif // !ORDER_STATISTIC_H__
