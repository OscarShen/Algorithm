#include "skip_list.h"
#include <random>

struct skip_list_node
{
	int data_;
	skip_list_node **forward_; // Each node will have a layer-dependent array of pointers
};

skip_list::skip_list(int max_level): header_(nullptr),size_(0),max_level_(max_level)
{
	this->header_ = new skip_list_node;
	this->header_->forward_ = new skip_list_node*[this->max_level_];
	for (int level = 0; level < this->max_level_; ++level) // Initializes all elements
		this->header_->forward_[level] = nullptr;
}

skip_list::~skip_list()
{
	while (this->header_ != nullptr) {
		skip_list_node *tmp = this->header_;
		this->header_ = this->header_->forward_[0]; // All elements must exist at level 0
		delete[]tmp->forward_;
		delete tmp;
	}
}

int skip_list::insert(int data)
{
	// Generates an array that stores all the elements that precede the insertion point
	skip_list_node **update = new skip_list_node*[this->max_level_];
	skip_list_node *cur = this->header_;
	for (int k = this->max_level_ - 1; k >= 0; --k) { // traversal -- from top to bottom, from left to right 
		skip_list_node *next = nullptr;
		while ((next = cur->forward_[k]) != nullptr && next->data_ < data) {
			cur = next;
		}
		update[k] = cur;
	}

	// Is the element already exists?
	bool b_find = update[0]->forward_[0] != nullptr && update[0]->forward_[0]->data_ == data;

	if (!b_find) {
		skip_list_node *node = new skip_list_node;
		node->data_ = data;
		int new_node_level = this->rand_level(); // Gets a random number of levels
		if (new_node_level > this->max_level_) { // If greater than the maximum level
			new_node_level = this->max_level_;
		}
		node->forward_ = new skip_list_node*[new_node_level];
		for (int k = 0; k < new_node_level; ++k) { // Insert level by level
			node->forward_[k] = update[k]->forward_[k];
			update[k]->forward_[k] = node;
		}
		++this->size_;
	}
	delete[]update;
	return b_find ? -1 : 0;
}

bool skip_list::find(int data) const
{
	// like insert function
	skip_list_node *cur = this->header_;
	for (int i = this->max_level_ - 1; i >= 0; --i) {
		skip_list_node *next = nullptr;
		while ((next = cur->forward_[i]) != nullptr && next->data_ < data) {
			cur = next;
		}
		if (next !=nullptr && next->data_ == data)
			return true;
	}
	return false;
}

int skip_list::remove(int data)
{
	// Like insert function
	skip_list_node **update = new skip_list_node*[this->max_level_];
	for (int i = this->max_level_ - 1; i >= 0; --i) {
		skip_list_node *cur = this->header_;
		skip_list_node *next = nullptr;
		while ((next = cur->forward_[i]) != nullptr && next->data_ < data)
			cur = next;
		update[i] = cur;
	}

	bool b_find = update[0]->forward_[0] != nullptr && update[0]->forward_[0]->data_ == data;
	if (b_find) {
		skip_list_node *find_node = update[0]->forward_[0];
		for (int i = 0; i < this->max_level_; ++i) {
			if (update[i]->forward_[i] == find_node)
				update[i]->forward_[i] = find_node->forward_[i];
			else
				break;
		}
		delete[]find_node->forward_;
		delete find_node;
		--this->size_;
	}
	return b_find ? 0 : -1;
}

int skip_list::size() const
{
	return this->size_;
}

int skip_list::rand_level()
{
	int level = 1;
	while ((::rand() & 0xffff) < (0xffff >> 2))
		++level;
	return level;
}
