#include "AVLTree.h"
#include <algorithm>

int getHeight(avl_n * node)
{
	if (node == nullptr)
		return -1;
	else
		return node->height;
}

avl_n* avl_insert_node(avl_n *node, int data) {
	if (node == nullptr)
		node = new avl_n(data);
	else if (node->data < data) {
		node->left = avl_insert_node(node->left, data);
		if (getHeight(node->left) - getHeight(node->right) == 2) {
			if (data < node->left->data)
				node = right_rotate(node);
			else
				node = left_right_rotate(node);
		}
	}
	else {
		node->right = avl_insert_node(node->right, data);
		if (getHeight(node->right) - getHeight(node->left) == 2) {
			if (data > node->right->data)
				node = left_rotate(node);
			else
				node = right_left_rotate(node);
		}
	}
	node->height = std::max(node->left->height, node->right->height) + 1;
	return node;
}

avl_n* right_rotate(avl_n * node)
{
	avl_n *tmp = node->left;
	node->left = tmp->right;
	tmp->right = node;
	node->height = std::max(getHeight(node->left), getHeight(node->right)) + 1;
	tmp->height = std::max(getHeight(tmp->left), getHeight(tmp->right)) + 1;
	return tmp;
}

avl_n * left_right_rotate(avl_n * node)
{
	node->left = left_rotate(node->left);
	return right_rotate(node);
}

avl_n * left_rotate(avl_n * node)
{
	avl_n *tmp = node->right;
	node->right = tmp->left;
	tmp->left = node;
	node->height = std::max(getHeight(node->left), getHeight(node->right)) + 1;
	tmp->height = std::max(getHeight(tmp->left), getHeight(tmp->right)) + 1;
	return tmp;
}

avl_n * right_left_rotate(avl_n * node)
{
	node->right = right_rotate(node->right);
	return left_rotate(node);
}

avl_n * find_successor(avl_n * node)
{
	if (node->right == nullptr)
		return nullptr;
	else {
		node = node->right;
		while (node->left != nullptr)
			node = node->left;
		return node;
	}
}

void AVLTree::avl_insert(int data)
{
	avl_insert_node(this->root, data);
}

void AVLTree::avl_delete(int data)
{
	avl_delete_node(this->root, data);
}


