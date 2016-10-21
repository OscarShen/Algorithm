#pragma once
#ifndef SORT_H__
#define SORT_H__
#include <vector>
using namespace std;

void insertionSort(vector<int> &v);
void mergeSort(vector<int>& v);
void quick_pure(vector<int>& v);
void quickRecur(vector<int>& v, int left, int right);
void heapSort(vector<int>& v);
#endif // !SORT_H__