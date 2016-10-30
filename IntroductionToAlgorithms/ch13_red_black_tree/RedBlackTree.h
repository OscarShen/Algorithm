#pragma once
#ifndef REDBLACKTREE_H_
#define REDBLACKTREE_H_

#define INT_MAX 2147483647 // Max integer

enum RBColor
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
}rbt;

typedef struct RedBlackNode
{
	int data;
	RedBlackNode *parent;
	RedBlackNode *left;
	RedBlackNode *right;
	RBColor color;
	RedBlackNode(int data, RedBlackNode *parent, RedBlackNode *left, 
		RedBlackNode *right, RBColor color=RBColor::RED ) :data(data), color(color), parent(parent),
		left(left), right(right) {}
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

void rb_delete_fixup(rbt *tree, rbt_n *fixNode);
#endif // !REDBLACKTREE_H_
