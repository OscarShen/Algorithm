#include "Heap.h"
#include <vector>

namespace heap_algo {
	void build_heap(std::vector<int>& v)
	{
		int n = v.size() - 1;
		for (int i = n / 2; i > 0; --i) {
			percolate_down(v, i, v.size());
		}
	}

	void percolate_down(std::vector<int>& v, int pos, int right)
	{
		int tmp, child;
		for (tmp = v[pos]; left_child(pos) < right; pos = child) {
			child = left_child(pos);
			if (child != right - 1 && v[child]<v[child + 1])
				child++;
			if (tmp<v[child])
				v[pos] = v[child];
			else
				break;
		}
		v[pos] = tmp;
	}

	void heap_swap(int & i, int & j)
	{
		int temp = std::move(i);
		i = std::move(j);
		j = std::move(temp);
	}

	int left_child(int parent)
	{
		return parent * 2;
	}

	int right_child(int parent)
	{
		return parent * 2 + 1;
	}
}