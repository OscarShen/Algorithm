#pragma once
#ifndef ORDERSTATICTREE_H_
#define ORDERSTATICTREE_H_

#define INT_MAX 2147483647 // Max integer

enum OSColor
{
	RED,
	BLACK
};
struct RedBlackNode;
typedef struct RedBlackTree
{
	static RedBlackNode *nil;
	RedBlackNode *root;
	RedBlackTree();
	~RedBlackTree();
}ost;

typedef struct RedBlackNode
{
	int data;
	RedBlackNode *parent;
	RedBlackNode *left;
	RedBlackNode *right;
	OSColor color;
	int size;
	RedBlackNode(int data, RedBlackNode *parent, RedBlackNode *left,
		RedBlackNode *right, int size, OSColor color = OSColor::RED ) :data(data), color(color), parent(parent),
		left(left), right(right), size(size) {}
}ost_n;

// Left rotate
void left_rotate(ost *tree, ost_n *node);
// Right rotate
void right_rotate(ost *tree, ost_n *node);
// Insert
void os_insert(ost *tree, ost_n *element);
// Fixup the error colors
void os_insert_fixup(ost *tree, ost_n *node);
// Transplant 'transplanted' to removed position
void os_transplant(ost *tree, ost_n *removed, ost_n *transplanted);
// Delete reb_black_tree node
void os_delete(ost *tree, ost_n *node);
// Return maximun of tree
ost_n* os_maximum(ost_n *root);
// Return minimum of tree
ost_n* os_minimum(ost_n *root);
// Find the element, and return pointer to the node
ost_n* os_find(ost *tree, ost_n *root, int data);
// Delete fixup
void os_delete_fixup(ost *tree, ost_n *fixNode);
// delete tree
void destory(ost *tree);
// delete node
void destory(ost_n *node);
// Fixup size
void fixup_size(ost *tree, ost_n *node);
// Return pointer of element in the kth
ost_n* os_select(ost_n *node, int k);
// Return rank of node
size_t os_rank(ost *tree, ost_n *node);
#endif // !ORDERSTATICTREE_H_