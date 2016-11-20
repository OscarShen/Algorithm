#include "OrderStaticTree.h"
#include <iostream>
RedBlackNode* RedBlackTree::nil;
RedBlackTree::RedBlackTree()
{
	if (nil == nullptr) {
		static RedBlackNode node(INT_MAX, nullptr, nullptr, nullptr, 0, OSColor::BLACK);
		RedBlackTree::nil = &node;
	}
	root = RedBlackTree::nil;
}

RedBlackTree::~RedBlackTree()
{
	destory(this);
}

void left_rotate(ost * tree, ost_n * node)
{
	ost_n *temp = node->right;
	node->right = temp->left;
	if (node->right != ost::nil)
		node->right->parent = node;
	temp->parent = node->parent;
	if (node->parent == ost::nil) {
		tree->root = temp;
	}
	else if (node == node->parent->left)
		node->parent->left = temp;
	else
		node->parent->right = temp;
	temp->left = node;
	node->parent = temp;
	temp->size = node->size;
	node->size = node->left->size + node->right->size + 1;
}

void right_rotate(ost * tree, ost_n * node)
{
	ost_n *temp = node->left;
	node->left = temp->right;
	if (node->left != ost::nil)
		node->left->parent = node;
	temp->parent = node->parent;
	if (node->parent == ost::nil)
		tree->root = temp;
	else if (node == node->parent->left)
		node->parent->left = temp;
	else
		node->parent->right = temp;
	temp->right = node;
	node->parent = temp;
	temp->size = node->size;
	node->size = node->left->size + node->right->size + 1;
}

void os_insert(ost * tree, ost_n * element)
{
	ost_n *root = tree->root;
	if (root == ost::nil) {
		tree->root = element;
		element->left = ost::nil;
		element->right = ost::nil;
		element->parent = ost::nil;
		element->color = OSColor::BLACK;
		return;
	}
	ost_n *temp = ost::nil;
	while (root != ost::nil) {
		temp = root;
		if (element->data < root->data)
			root = root->left;
		else
			root = root->right;
	}
	element->parent = temp;
	if (element->data < temp->data)
		temp->left = element;
	else
		temp->right = element;
	element->left = ost::nil;
	element->right = ost::nil;
	element->color = OSColor::RED;
	element->size = 1;
	fixup_size(tree, element->parent);
	os_insert_fixup(tree, element);
}

void os_insert_fixup(ost * tree, ost_n * node)
{
	ost_n *uncle = ost::nil;
	while (node->parent->color == OSColor::RED) {
		if (node->parent->parent->left == node->parent) { // Parent is the left child of grandparent ndoe
			uncle = node->parent->parent->right;
			if (uncle->color == OSColor::RED) { // case 1
				node->parent->color = OSColor::BLACK;
				uncle->color = OSColor::BLACK;
				node->parent->parent->color = OSColor::RED;
				node = node->parent->parent;
				continue;
			}
			else if (node == node->parent->right) { // case 2
				node = node->parent;
				left_rotate(tree, node);
			}
			node->parent->color = OSColor::BLACK; // case 3
			node->parent->parent->color = OSColor::RED;
			right_rotate(tree, node->parent->parent);
		}
		else {
			uncle = node->parent->parent->left;
			if (uncle->color == OSColor::RED) { // case 1
				node->parent->color = OSColor::BLACK;
				uncle->color = OSColor::BLACK;
				node->parent->parent->color = OSColor::RED;
				node = node->parent->parent;
				continue;
			}
			else if (node == node->parent->left) { // case 2
				node = node->parent;
				right_rotate(tree, node);
			}
			node->parent->color = OSColor::BLACK; // case 3
			node->parent->parent->color = OSColor::RED;
			left_rotate(tree, node->parent->parent);
		}
	}
	tree->root->color = OSColor::BLACK;
}

void os_transplant(ost * tree, ost_n * removed, ost_n * transplanted)
{
	if (removed->parent == ost::nil)
		tree->root = transplanted;
	else if (removed == removed->parent->left)
		removed->parent->left = transplanted;
	else
		removed->parent->right = transplanted;
	transplanted->parent = removed->parent;
}

void os_delete(ost * tree, ost_n * node)
{
	ost_n *temp = node, *fix_node = nullptr;
	OSColor temp_origin_color = temp->color; // Record color of temp
	ost_n *color_fix_node = ost::nil;
	if (node->left == ost::nil) { // Nerver mind whether has right child
		color_fix_node = node->right;
		if (node->right == ost::nil)
			fix_node = node->parent; // Prevent fix_node from nil
		else
			fix_node = node->right;
		os_transplant(tree, node, node->right);
	}
	else if (node->right == ost::nil) {
		color_fix_node = node->left;
		fix_node = node->left;
		os_transplant(tree, node, node->left);
	}
	else {
		temp = os_minimum(node->right); // Record color of temp, if color is black so we need a fixup function
		temp_origin_color = temp->color;
		color_fix_node = temp->right; // If temp has child, it must be right child
		if (temp->parent == node) { // If temp is child of node, just transplant
			color_fix_node->parent = temp;
			fix_node = temp;
		}
		else { // If not, get right child of node, then transfer to upper condition
			if (temp->right == tree->nil)
				fix_node = temp->parent;
			else
				fix_node = temp->right;
			os_transplant(tree, temp, temp->right);
			temp->right = node->right;
			temp->right->parent = temp;
		}
		os_transplant(tree, node, temp);
		temp->left = node->left;
		temp->left->parent = temp;
		temp->color = node->color; // Inherit color of node
	}
	fixup_size(tree, fix_node);
	if (temp_origin_color == OSColor::BLACK) // If origin color of temp is red, means that it does not break rules of ost.
		os_delete_fixup(tree, color_fix_node);

}

ost_n * os_maximum(ost_n * root)
{
	while (root->right != ost::nil)
		root = root->right;
	return root;
}

ost_n * os_minimum(ost_n * root)
{
	while (root->left != ost::nil)
		root = root->left;
	return root;
}

ost_n * os_find(ost *tree, ost_n * root, int data)
{
	if (root == tree->nil)
		return tree->nil;
	if (root->data == data)
		return root;
	else if (data < root->data)
		return os_find(tree, root->left, data);
	else
		return os_find(tree, root->right, data);
}

void os_delete_fixup(ost * tree, ost_n * fixNode)
{
	// At beginning, fixNode's color can be red or black, if it is 
	// red, just let it be black to fill fixNode
	while (fixNode != ost::nil && fixNode->color == OSColor::BLACK) {
		if (fixNode == fixNode->parent->left) { // If fixNode is left child of parent
			ost_n *brother = fixNode->parent->right; // brother
			if (brother->color == OSColor::RED) { // case 1, if brother's color is red,so we can transport it's color to left
				brother->color = OSColor::BLACK; // Color change to black
				fixNode->parent->color = OSColor::RED; // Color change to red
				left_rotate(tree, fixNode->parent); // change to case 2
			} // Then we will finish this loop, and get a new brother
			if (brother->left->color == OSColor::BLACK && brother->right->color == OSColor::BLACK) { // In this case, brother must be black
				brother->color = OSColor::RED;
				fixNode = fixNode->parent;// just remember left need a extra black
			}
			else {
				if (brother->right->color == OSColor::BLACK) {
					brother->color = OSColor::RED;
					brother->left->color = OSColor::BLACK;
					right_rotate(tree, brother);
					brother = fixNode->parent->right;
				} // Now brother's right child must be red, in case 4
				  // still left needs a black
				brother->color = OSColor::RED;
				brother->parent->color = OSColor::BLACK;
				left_rotate(tree, brother->parent);
				fixNode = ost::nil;
			}
		}
		else {
			if (fixNode == fixNode->parent->right) { // If fixNode is left child of parent
				ost_n *brother = fixNode->parent->left; // brother
				if (brother->color == OSColor::RED) { // case 1, if brother's color is red,so we can transport it's color to left
					brother->color = OSColor::BLACK; // Color change to black
					fixNode->parent->color = OSColor::RED; // Color change to red
					right_rotate(tree, fixNode->parent); // change to case 2
				} // Then we will finish this loop, and get a new brother
				if (brother->right->color == OSColor::BLACK && brother->left->color == OSColor::BLACK) { // In this case, brother must be black
					brother->color = OSColor::RED;
					fixNode = fixNode->parent;// just remember left need a extra black
				}
				else {
					if (brother->left->color == OSColor::BLACK) {
						brother->color = OSColor::RED;
						brother->right->color = OSColor::BLACK;
						left_rotate(tree, brother);
						brother = fixNode->parent->left;
					} // Now brother's right child must be red, in case 4
					  // still left needs a black
					brother->color = OSColor::RED;
					brother->parent->color = OSColor::BLACK;
					right_rotate(tree, brother->parent);
					fixNode = ost::nil;
				}
			}
		}
		fixNode->color = OSColor::BLACK;
	}
}



void destory(ost * tree)
{
	destory(tree->root);
}
void destory(ost_n * node)
{
	if (node != ost::nil) {
		destory(node->left);
		destory(node->right);
		delete node;
	}
}

void fixup_size(ost * tree, ost_n * node)
{
	if (node == tree->nil)
		return;
	do {
		node->size = node->left->size + node->right->size + 1;
		node = node->parent;
	} while (node != tree->nil);
}

ost_n * os_select(ost_n * node, int k)
{
	int size = node->left->size + 1;
	if (k == size)
		return node;
	else if (k < size)
		return os_select(node->left, k);
	else
		return os_select(node->right, k - size);
}

int os_rank(ost * tree, ost_n * node)
{
	int res = node->left->size + 1;
	ost_n *tmp = node;
	while (tmp != tree->root) {
		if (tmp = tmp->parent->right) // If tmp is right child of parent
			res += tmp->parent->left->size + 1; // Now plus whole left child-tree's sieze and one from parent
		tmp = tmp->parent;
	}
	return res;
}

void dfs(ost *tree, ost_n *root) {
	if (root == ost::nil)
		return;
	dfs(tree, root->left);
	std::cout << root->data << " ";
	if (root->color == RED)
		std::cout << "color: RED ";
	else
		std::cout << "color: BLACK ";
	std::cout << "parent:" << root->parent->data << std::endl;
	dfs(tree, root->right);
}


int main() {
	ost *tree = new ost();
	for (int i = 0; i < 20; ++i) {
		auto temp = new ost_n(rand() % 30, ost::nil, ost::nil, ost::nil, 0);
		os_insert(tree, temp);
	}
	dfs(tree, tree->root);
	std::cout << "the eighth data is " << os_select(tree->root, 8)->data << std::endl;
	std::cout << "maximum: " << os_maximum(tree->root)->data << std::endl;
	std::cout << "minimun: " << os_minimum(tree->root)->data << std::endl;
	std::cout << "afterdelete:" << std::endl;
	os_delete(tree, os_find(tree, tree->root, 11));
	os_delete(tree, os_find(tree, tree->root, 29));
	dfs(tree, tree->root);
	std::cout << "the eleventh data is " << os_select(tree->root, 11)->data << std::endl;
	delete tree;
}