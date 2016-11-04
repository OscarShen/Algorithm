#pragma once
#ifndef PERSISTENTTREE_H_
#define PERSISTENTTREE_H_
#define INT_MAX 2147483647 // Max integer

enum RBColor
{
	RED,
	BLACK
};
struct PersistentNode;
typedef struct PersistentTree
{
	static PersistentNode *nil;
	PersistentNode *root;
	PersistentTree();
	~PersistentTree();
}rbt;

typedef struct PersistentNode
{
	int data;
	PersistentNode *parent;
	PersistentNode *left;
	PersistentNode *right;
	RBColor color;
	PersistentNode(int data, PersistentNode *parent, PersistentNode *left,
		PersistentNode *right, RBColor color = RBColor::RED) :data(data), color(color), parent(parent),
		left(left), right(right) {}
	PersistentNode(PersistentNode &node);
	PersistentNode* operator=(PersistentNode &rhs);
}rbt_n;


// Left rotate
void left_rotate(rbt *tree, rbt_n *node);
// Right rotate
void right_rotate(rbt *tree, rbt_n *node);
// Insert
void rb_insert(rbt *tree, rbt_n *element);
// Fixup the error colors
void rb_insert_fixup(rbt *tree, rbt_n *node);
// Transplant 'transplanted' to removed position
void rb_transplant(rbt *tree, rbt_n *removed, rbt_n *transplanted);
// Delete reb_black_tree node
void rb_delete(rbt *tree, rbt_n *node);
// Return maximun of tree
rbt_n* rb_maximum(rbt_n *root);
// Return minimum of tree
rbt_n* rb_minimum(rbt_n *root);
// Find the element, and return pointer to the node
rbt_n* rb_find(rbt *tree, rbt_n *root, int data);
// Delete fixup
void rb_delete_fixup(rbt *tree, rbt_n *fixNode);
// delete tree
void destory(rbt *tree);
// delete node
void destory(rbt_n *node);

#endif // !PERSISTENTTREE_H_
