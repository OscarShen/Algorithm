#pragma once
#ifndef _HEAP_H__
#define _HEAP_H__
#include <vector>

///////////////////////////////////////
// MAX_HEAP
///////////////////////////////////////
namespace heap_algo {
	void percolate_down(std::vector<int>& v, int pos, int right);
	void heap_swap(int &i, int &j);
	int left_child(int parent);
	int right_child(int parent);
	void build_heap(std::vector<int>& v);
}

#endif // !HEAP_H__
