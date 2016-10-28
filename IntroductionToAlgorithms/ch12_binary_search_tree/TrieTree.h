#pragma once
#ifndef TRIETREE_H_
#define TRIETREE_H_

#include <set>
#include <string>

struct TrieNode
{
	TrieNode* childNodes[26]; //�����ڵ�
	int freq = 0;// ��Ƶͳ��
	char nodeChar;// �ڵ��ַ�
	std::set<int> set;// �����¼ʱ�ı���ID
};
// Add trie node
void addTrieNode(TrieNode* root, std::string word, int id);
// Delete trie node
void deleteTrieNode(TrieNode* root, std::string word, int id);
// Search tree and return a set result
std::set<int> searchTrie(TrieNode* root, std::string word);
// Search tree and return a set result
void searchTrie(TrieNode* root, std::string word, std::set<int>& set);
// Return word count
int wordCount(TrieNode* root, std::string word);
// Return word count
void wordCount(TrieNode* root, std::string word, int &count);

#endif // !TRIETREE_H_
