#pragma once
#ifndef SORT_H__
#define SORT_H__
#include <vector>
using namespace std;

void insertionSort(vector<int> &v);
void mergeSort(vector<int>& v);
void heapSort(vector<int>& v);
void quickSort(vector<int> &v);
#endif // !SORT_H__