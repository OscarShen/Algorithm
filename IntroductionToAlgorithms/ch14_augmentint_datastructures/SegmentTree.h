#pragma once
#ifndef SEGMENTTREE_H__
#define SEGMENTTREE_H__
#define MAX_INT 2147483647

#include <vector>

class SegmentNode {
public:
	int cover;
	int left, right;
	SegmentNode *leftChild;
	SegmentNode *rightChild;
	SegmentNode() :left(0), right(0), cover(0), leftChild(nullptr), rightChild(nullptr) {}
	SegmentNode(int left,int right) :left(left), right(right), cover(0), leftChild(nullptr), rightChild(nullptr) {}
	static SegmentNode * buildTree(int left, int right);
	void insertSegment(int left, int right);
	void deleteSegment(int left, int right);
private:
	void insertSegment(int left, int right, SegmentNode *root);
	void deleteSegment(int left, int right, SegmentNode *root);
};
#endif // !SEGMENTTREE_H__
