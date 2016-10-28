#pragma once
#ifndef BINARYSEARCHTREE_H_
#define BINARYSEARCHTREE_H_

#define INT_MAX 2147483647 // Max integer
#define NOT_FIND -1 // Define a not find macro


typedef struct BinarySearchTreeNode {
	int data;
	BinarySearchTreeNode *parent = nullptr;
	BinarySearchTreeNode *left = nullptr;
	BinarySearchTreeNode *right = nullptr;
	BinarySearchTreeNode(int data = 0) :data(data), parent(nullptr), left(nullptr), right(nullptr) {}
}bst_n;

typedef struct BinarySearchTree {
	BinarySearchTreeNode *root = nullptr;
}bst;

// Search the tree of root, and return pointer to
// the element be found or nullptr
bst_n* tree_search(bst_n *root, int k);

// Return maximun of tree
bst_n* tree_maximum(bst_n *root);

// Return minimum of tree
bst_n* tree_minimum(bst_n *root);

// Return the successor of given node in mid trailing
bst_n* tree_successor(bst_n *node);

// Insert a element to a tree
void tree_insert(bst *tree, bst_n *element);

// Transplant 'transplant' node to 'removed' node, transplant's parent
// is the same of parent of 'removed', but not their childs
void transplant(bst *tree, bst_n *removed, bst_n *transplant);

// Delete the node from tree
void tree_delete(bst *tree, bst_n *removed);

#endif // !BINARYSEARCHTREE_H_
