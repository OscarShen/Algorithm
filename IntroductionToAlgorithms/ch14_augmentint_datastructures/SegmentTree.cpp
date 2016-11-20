#include "SegmentTree.h"
#include <iostream>

SegmentNode * SegmentNode::buildTree(int left, int right)
{
	if (right - left < 1)
		return nullptr;
	SegmentNode *root = new SegmentNode(left,right);
	if (right - left == 1)
		return root;
	else {
		int mid = (left + right) >> 1;
		root->leftChild = buildTree(left, mid);
		root->rightChild = buildTree(mid, right);
		return root;
	}
}

void SegmentNode::insertSegment(int left, int right)
{
	insertSegment(left, right, this);
}

void SegmentNode::insertSegment(int left, int right, SegmentNode * root)
{
	if (root == nullptr || left<root->left || right>root->right)
		return;
	if (root->left == left&&root->right == right) {
		++root->cover;
		return;
	}
	int mid = (root->left + root->right) >> 1;
	if (right <= mid)
		insertSegment(left, right, root->leftChild);
	else if (left >= mid)
		insertSegment(left, right, root->rightChild);
	else {
		insertSegment(left, mid, root->leftChild);
		insertSegment(mid, right, root->rightChild);
	}
}

void SegmentNode::deleteSegment(int left, int right)
{
	deleteSegment(left, right, this);
}

void SegmentNode::deleteSegment(int left, int right, SegmentNode * root)
{
	if (root == nullptr || left<root->left || right>root->right)
		return;
	if (left == root->left && right == root->right) {
		root->cover = root->cover - 1 > 0 ? cover - 1 : 0;
		return;
	}
	int mid = (root->left + root->right) >> 1;
	if (right <= mid)
		deleteSegment(left, right, root->leftChild);
	else if (left >= mid)
		deleteSegment(left, right, root->rightChild);
	else {
		deleteSegment(left, mid, root->leftChild);
		deleteSegment(mid, right, root->rightChild);
	}
}

void inOrder(SegmentNode *node) {
	if (node->leftChild != nullptr)
		inOrder(node->leftChild);
	std::cout << node->left << "--" << node->right << "--" << node->cover << std::endl;
	if (node->rightChild != nullptr)
		inOrder(node->rightChild);
}