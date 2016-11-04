#pragma once
#ifndef AVLTREE_H__
#define AVLTREE_H__

#define INT_MAX 2147483647 // Max integer

typedef class AVLNode
{
public:
	int data;
	int height;
	AVLNode *left;
	AVLNode *right;
	AVLNode(int data) :data(data), height(0), left(nullptr), right(nullptr) {}
}avl_n;

typedef class AVLTree
{
public:
	avl_n *root;
	AVLTree() :root(nullptr) {}

	void avl_insert(int data);
	void avl_delete(int data);
}avl;

int getHeight(avl_n *node);
avl_n* avl_insert_node(avl_n *node, int data);
avl_n* right_rotate(avl_n *node);
avl_n* left_right_rotate(avl_n *node);
avl_n* left_rotate(avl_n *node);
avl_n* right_left_rotate(avl_n *node);

#endif // !AVLTREE_H__
