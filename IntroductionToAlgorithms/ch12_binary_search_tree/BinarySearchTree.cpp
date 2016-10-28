#include "BinarySearchTree.h"

bst_n * tree_search(bst_n * root, int k)
{
	while (root != nullptr && k != root->data) {
		if (k < root->data)
			root = root->left;
		else
			root = root->right;
	}
	return root;
}

bst_n * tree_maximum(bst_n * root)
{
	while (root->right != nullptr)
		root = root->right;
	return root;
}

bst_n * tree_minimum(bst_n * root)
{
	while (root->left != nullptr)
		root = root->left;
	return root;
}

bst_n * tree_successor(bst_n * node)
{
	if (node->right != nullptr)
		return tree_minimum(node->right);
	bst_n *temp = node->parent;
	while (temp != nullptr&&node == temp->right) {
		node = temp;
		temp = temp->parent;
	}
	return temp;
}

void tree_insert(bst * tree, bst_n * element)
{
	if (tree->root == nullptr) {
		tree->root = element;
		tree->root->parent = nullptr;
		tree->root->left = nullptr;
		tree->root->right = nullptr;
		return;
	}
	bst_n *root = tree->root;
	bst_n *temp = nullptr;
	while (root != nullptr) {
		temp = root;
		if (element->data < root->data)
			root = root->left;
		else
			root = root->right;
	}
	if (element->data < temp->data)
		temp->left = element;
	else
		temp->right = element;
	element->parent = temp;
}

void transplant(bst * tree, bst_n *removed, bst_n *transplant)
{
	if (removed->parent == nullptr)
		tree->root = transplant;
	else if (removed == removed->parent->left)
		removed->parent->left = transplant;
	else
		removed->parent->right = transplant;
	if (transplant != nullptr)
		transplant->parent = removed->parent;
}

void tree_delete(bst * tree, bst_n * removed)
{
	if (removed->left == nullptr)
		transplant(tree, removed, removed->right);
	else if (removed->right == nullptr)
		transplant(tree, removed, removed->left);
	else {
		bst_n *minimum = tree_minimum(removed->right);
		if (minimum->parent != removed) {
			transplant(tree, minimum, minimum->right);
			minimum->right = removed->right;
			minimum->right->parent = minimum;
		}
		transplant(tree, removed, minimum);
		minimum->left = removed->left;
		minimum->left->parent = minimum;
	}
}

#include <iostream>
int main() {
	bst *tree = new bst();
	for (int i = 0; i < 5; ++i) {
		bst_n *element = new bst_n(rand() % 10);
		std::cout << element->data << " ";
		tree_insert(tree, element);
	}
	std::cout << std::endl;
	auto root = tree->root;
	std::cout << root->data << " ";
	while ((root=tree_successor(root))!=nullptr) {
		std::cout << root->data << " ";
	}
	std::cout << std::endl;
	std::cout << tree_minimum(tree->root)->data << " ";
	std::cout << tree_maximum(tree->root)->data;
	std::cout << std::endl;
}
