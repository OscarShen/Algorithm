#ifndef ORDER_STATISTIC_H__
#define ORDER_STATISTIC_H__

#include <vector>
// Find the i-st small number in vector
int randomized_select(std::vector<int> &v, int i);
// Find the i-st small number between left and right in vector
int randomized_select(std::vector<int> &v, int left, int right, int i);

#endif // !ORDER_STATISTIC_H__
