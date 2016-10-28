#include <vector>
#include <set>
#include <string>
#include <fstream>
#include <algorithm>
#include <iostream>
#include "TrieTree.h"
using namespace std;

void addTrieNode(TrieNode* root, string word, int id) {
	if (word.size() == 0)
		return;
	int k = word[0] - 'a';// 决定将字符放在哪个叉中
	if (root->childNodes[k] == nullptr) {
		root->childNodes[k] = new TrieNode();
		root->childNodes[k]->nodeChar = word[0];
	}
	root->childNodes[k]->set.insert(id);
	string&& nextWord = word.substr(1);
	if (nextWord.size() == 0)
		root->childNodes[k]->freq++;
	else
		addTrieNode(root->childNodes[k], nextWord, id);
}

void deleteTrieNode(TrieNode* root, string word, int id) {
	if (word.size() == 0)
		return;
	int k = word[0] - 'a';
	if (root->childNodes[k] == nullptr)
		return;
	string&& nextWord = word.substr(1);
	if (word.size() == 0 && root->childNodes[k]->freq > 0)
		root->childNodes[k]->freq--;
	root->set.erase(id);
	deleteTrieNode(root->childNodes[k], nextWord, id);
}

void searchTrie(TrieNode* root, string word, set<int>& set) {
	if (word.size() == 0)
		return ;
	int k = word[0] - 'a';
	auto&& nextWord = word.substr(1);
	if (nextWord.size() == 0) {
		set = root->childNodes[k]->set;
		return;
	}
	searchTrie(root->childNodes[k], nextWord, set);
}

set<int> searchTrie(TrieNode* root, string word) {
	set<int> set;
	searchTrie(root, word, set);
	return set;
}

void wordCount(TrieNode* root, string word, int& count) {
	if (word.size() == 0)
		return;
	int k = word[0] - 'a';
	auto nextWord = word.substr(1);
	if (nextWord.size() == 0) {
		count = root->childNodes[k]->freq;
	}
	wordCount(root->childNodes[k], word, count);
}

int wordCount(TrieNode* root, string word) {
	int count = 0;
	wordCount(root, word, count);
	return count;
}

int main() {
	TrieNode* trie = new TrieNode();
	ifstream file;
	file.open("TrieTreeResource.txt");
	int id;
	string word;
	while (!file.eof()) {
		file >> id;
		file >> word;
		cout << id << endl;
		transform(word.begin(), word.end(), word.begin(), ::tolower);
		addTrieNode(trie, word, id);
	}
	set<int>&& set = searchTrie(trie, string("go"));
	for (auto item : set)
		cout << "当前字符串的编号ID为:" << item << endl;
}