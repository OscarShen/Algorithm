#pragma once
#ifndef SKIPLIST_H__
#define SKIPLIST_H__

struct skip_list_node;

class skip_list
{
public:
	explicit skip_list(int max_level = 12);
	~skip_list();

	int insert(int data);
	bool find(int data) const;
	int remove(int data);
	int size() const;

private:
	// A random number is generated, 
	// with each level L element corresponding to four elements in level L-1
	int rand_level();

private:
	// The head of the skip table
	skip_list_node *header_;
	int size_;
	int max_level_;

private:
	skip_list(const skip_list&);
	skip_list& operator=(const skip_list&);
};
#endif
