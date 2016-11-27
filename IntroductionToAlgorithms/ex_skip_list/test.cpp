#include "skip_list.h"

#include <ctime>
#include <iostream>
#include <set>
#include <Windows.h>

static int64_t current_ms();
int main()
{

	const int num = 100000;

	int64_t t1 = current_ms();
	skip_list *sl = new skip_list;
	for (int i = 0; i < num; ++i)
		if (sl->insert(i) != 0)
			std::cout << "skip_list insert " << i << " failed." << std::endl;

	int64_t t2 = current_ms();
	std::cout << "skip_list insert used " << t2 - t1 << " ms" << std::endl;
	int64_t t3 = current_ms();
	for (int i = 0; i < num; ++i)
		if (!sl->find(i))
			std::cout << "skip_list not found " << i << std::endl;
	int64_t t4 = current_ms();
	std::cout << "skip_list find used " << t4 - t3 << " ms" << std::endl;
	delete sl;


	std::set<int> si;
	int64_t t5 = current_ms();
	for (int i = 0; i < num; ++i)
		si.insert(i);
	int64_t t6 = current_ms();
	std::cout << "rb_tree insert used " << t6 - t5 << " ms" << std::endl;
	int64_t t7 = current_ms();
	for (int i = 0; i < num; ++i)
		if (si.find(i) == si.end())
			std::cout << "rb_tree not found " << i << std::endl;
	int64_t t8 = current_ms();
	std::cout << "rb_tree find used " << t8 - t7 << " ms" << std::endl;
	return 0;
}

static int64_t current_ms()
{
	_SYSTEMTIME time;
	GetLocalTime(&time);
	return int64_t(time.wMinute * 60000 + time.wSecond * 1000 + time.wMilliseconds);
}
