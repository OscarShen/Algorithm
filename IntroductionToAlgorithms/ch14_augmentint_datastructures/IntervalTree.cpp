#include "IntervalTree.h"
RedBlackNode* RedBlackTree::nil;

RedBlackTree::RedBlackTree()
{
	if (nil == nullptr)
		RedBlackTree::nil = new RedBlackNode(Interval(-1,-1), nullptr, nullptr, nullptr, RBColor::BLACK);
	root = RedBlackTree::nil;
}

RedBlackTree::~RedBlackTree()
{
	destory(this);
}

void left_rotate(rbt * tree, rbt_n * node)
{
	rbt_n *temp = node->right;
	node->right = temp->left;
	if (node->right != rbt::nil)
		node->right->parent = node;
	temp->parent = node->parent;
	if (node->parent == rbt::nil) {
		tree->root = temp;
	}
	else if (node == node->parent->left)
		node->parent->left = temp;
	else
		node->parent->right = temp;
	temp->left = node;
	node->parent = temp;
}

void right_rotate(rbt * tree, rbt_n * node)
{
	rbt_n *temp = node->left;
	node->left = temp->right;
	if (node->left != rbt::nil)
		node->left->parent = node;
	temp->parent = node->parent;
	if (node->parent == rbt::nil)
		tree->root = temp;
	else if (node == node->parent->left)
		node->parent->left = temp;
	else
		node->parent->right = temp;
	temp->right = node;
	node->parent = temp;
}

void rb_insert(rbt * tree, rbt_n * element)
{
	rbt_n *root = tree->root;
	if (root == rbt::nil) {
		tree->root = element;
		element->left = rbt::nil;
		element->right = rbt::nil;
		element->parent = rbt::nil;
		element->color = RBColor::BLACK;
		return;
	}
	rbt_n *temp = rbt::nil;
	while (root != rbt::nil) {
		temp = root;
		if (element->interval.low < root->interval.low)
			root = root->left;
		else
			root = root->right;
	}
	element->parent = temp;
	if (element->interval.low < temp->interval.low)
		temp->left = element;
	else
		temp->right = element;
	element->left = rbt::nil;
	element->right = rbt::nil;
	element->color = RBColor::RED;
	rb_insert_fixup(tree, element);
}

void rb_insert_fixup(rbt * tree, rbt_n * node)
{
	rbt_n *uncle = rbt::nil;
	while (node->parent->color == RBColor::RED) {
		if (node->parent->parent->left == node->parent) { // Parent is the left child of grandparent ndoe
			uncle = node->parent->parent->right;
			if (uncle->color == RBColor::RED) { // case 1
				node->parent->color = RBColor::BLACK;
				uncle->color = RBColor::BLACK;
				node->parent->parent->color = RBColor::RED;
				node = node->parent->parent;
				continue;
			}
			else if (node == node->parent->right) { // case 2
				node = node->parent;
				left_rotate(tree, node);
			}
			node->parent->color = RBColor::BLACK; // case 3
			node->parent->parent->color = RBColor::RED;
			right_rotate(tree, node->parent->parent);
		}
		else {
			uncle = node->parent->parent->left;
			if (uncle->color == RBColor::RED) { // case 1
				node->parent->color = RBColor::BLACK;
				uncle->color = RBColor::BLACK;
				node->parent->parent->color = RBColor::RED;
				node = node->parent->parent;
				continue;
			}
			else if (node == node->parent->left) { // case 2
				node = node->parent;
				right_rotate(tree, node);
			}
			node->parent->color = RBColor::BLACK; // case 3
			node->parent->parent->color = RBColor::RED;
			left_rotate(tree, node->parent->parent);
		}
	}
	tree->root->color = RBColor::BLACK;
}

void rb_transplant(rbt * tree, rbt_n * removed, rbt_n * transplanted)
{
	if (removed->parent == rbt::nil)
		tree->root = transplanted;
	else if (removed == removed->parent->left)
		removed->parent->left = transplanted;
	else
		removed->parent->right = transplanted;
	transplanted->parent = removed->parent;
}

void rb_delete(rbt * tree, rbt_n * node)
{
	rbt_n *temp = node;
	RBColor temp_origin_color = temp->color; // Record color of temp
	rbt_n *color_fix_node = rbt::nil;
	if (node->left == rbt::nil) { // Nerver mind whether has right child
		color_fix_node = node->right;
		rb_transplant(tree, node, node->right);
	}
	else if (node->right == rbt::nil) { // yes if node don't have any child
		color_fix_node = node->left;
		rb_transplant(tree, node, node->left);
	}
	else {
		temp = rb_minimum(node->right); // Record color of temp, if color is black so we need a fixup function
		temp_origin_color = temp->color;
		color_fix_node = temp->right; // If temp has child, it must be right child
		if (temp->parent == node) // If temp is child of node, just transplant
			color_fix_node->parent = temp;
		else { // If not, get right child of node, then transfer to upper condition
			rb_transplant(tree, temp, temp->right);
			temp->right = node->right;
			temp->right->parent = temp;
		}
		rb_transplant(tree, node, temp);
		temp->left = node->left;
		temp->left->parent = temp;
		temp->color = node->color; // Inherit color of node
		if (temp_origin_color == RBColor::BLACK) // If origin color of temp is red, means that it does not break rules of rbt.
			rb_delete_fixup(tree, color_fix_node);
	}

}

rbt_n * rb_maximum(rbt_n * root)
{
	while (root->right != rbt::nil)
		root = root->right;
	return root;
}

rbt_n * rb_minimum(rbt_n * root)
{
	while (root->left != rbt::nil)
		root = root->left;
	return root;
}

rbt_n * rb_find(rbt *tree, rbt_n * root, Interval interval)
{
	if (root == tree->nil)
		return tree->nil;
	//if (root->interval == interval)
	if(!(interval.high<=root->interval.low || interval.low>=interval.high))
		return root;
	else if (interval.low < root->interval.low)
		return rb_find(tree, root->left, interval);
	else
		return rb_find(tree, root->right, interval);
}

void rb_delete_fixup(rbt * tree, rbt_n * fixNode)
{
	// At beginning, fixNode's color can be red or black, if it is 
	// red, just let it be black to fill fixNode
	while (fixNode != rbt::nil && fixNode->color == RBColor::BLACK) {
		if (fixNode == fixNode->parent->left) { // If fixNode is left child of parent
			rbt_n *brother = fixNode->parent->right; // brother
			if (brother->color == RBColor::RED) { // case 1, if brother's color is red,so we can transport it's color to left
				brother->color = RBColor::BLACK; // Color change to black
				fixNode->parent->color = RBColor::RED; // Color change to red
				left_rotate(tree, fixNode->parent); // change to case 2
			} // Then we will finish this loop, and get a new brother
			if (brother->left->color == RBColor::BLACK && brother->right->color == RBColor::BLACK) { // In this case, brother must be black
				brother->color = RBColor::RED;
				fixNode = fixNode->parent;// just remember left need a extra black
			}
			else {
				if (brother->right->color == RBColor::BLACK) {
					brother->color = RBColor::RED;
					brother->left->color = RBColor::BLACK;
					right_rotate(tree, brother);
					brother = fixNode->parent->right;
				} // Now brother's right child must be red, in case 4
				// still left needs a black
				brother->color = RBColor::RED;
				brother->parent->color = RBColor::BLACK;
				left_rotate(tree, brother->parent);
				fixNode = rbt::nil;
			}
		}
		else {
			if (fixNode == fixNode->parent->right) { // If fixNode is left child of parent
				rbt_n *brother = fixNode->parent->left; // brother
				if (brother->color == RBColor::RED) { // case 1, if brother's color is red,so we can transport it's color to left
					brother->color = RBColor::BLACK; // Color change to black
					fixNode->parent->color = RBColor::RED; // Color change to red
					right_rotate(tree, fixNode->parent); // change to case 2
				} // Then we will finish this loop, and get a new brother
				if (brother->right->color == RBColor::BLACK && brother->left->color == RBColor::BLACK) { // In this case, brother must be black
					brother->color = RBColor::RED;
					fixNode = fixNode->parent;// just remember left need a extra black
				}
				else {
					if (brother->left->color == RBColor::BLACK) {
						brother->color = RBColor::RED;
						brother->right->color = RBColor::BLACK;
						left_rotate(tree, brother);
						brother = fixNode->parent->left;
					} // Now brother's right child must be red, in case 4
					  // still left needs a black
					brother->color = RBColor::RED;
					brother->parent->color = RBColor::BLACK;
					right_rotate(tree, brother->parent);
					fixNode = rbt::nil;
				}
			}
		}
		fixNode->color = RBColor::BLACK;
	}
}



void destory(rbt * tree)
{
	destory(tree->root);
}
#include <iostream>
void destory(rbt_n * node)
{
	if (node != rbt::nil) {
		destory(node->left);
		destory(node->right);
		delete node;
	}
}

int main() {
	rbt *tree = new rbt();
	rb_insert(tree, new rbt_n(Interval(3, 6), nullptr, nullptr, nullptr));
	rb_insert(tree, new rbt_n(Interval(5, 8), nullptr, nullptr, nullptr));
	rb_insert(tree, new rbt_n(Interval(9, 11), nullptr, nullptr, nullptr));
	auto n = rb_find(tree, tree->root, Interval(3, 4));
	std::cout << n->interval.low << " " << n->interval.high << std::endl;
	delete tree;
}