#include <queue>
#include <vector>
#include <iostream>
#include <map>
#include <string>
using namespace std;

typedef class HuffmanNode
{
public:
	int left, right;// 记录左右儿子
	char ch;// 码元
	float weight;// 权重
	HuffmanNode() { left = right = -1, weight = 0.0, ch = '\0'; }
	HuffmanNode(int parent, int left, int right, char ch, float weight) 
		:left(left), right(right), ch(ch), weight(weight) {}
	// Huffman树中只有叶节点才是有效码元
	bool isLeaf() { return left == -1 && right == -1; }
}Node;

struct Less
{ // 重载运算符，使优先队列顶端为权重较小Node
	bool operator()(Node* n1, Node* n2) { return n1->weight > n2->weight; }
};
// 建立Huffman树
vector<Node*> buildTree(vector<Node*>& v) {
	priority_queue<Node*, vector<Node*>, Less> queue;
	int n = v.size();
	for (int i = 0; i < n; ++i) {
		if (v[i]->weight)
			queue.push(new Node(-1, -1, -1, v[i]->ch, v[i]->weight));
	}

	vector<Node*> res;
	// build
	while (queue.size() > 1) {
		Node* lc = queue.top();
		queue.pop();
		res.push_back(lc);
		Node* rc = queue.top();
		queue.pop();
		res.push_back(rc);

		Node* parent = new Node(-1, res.size() - 2, res.size() - 1, '\0', lc->weight + rc->weight);
		queue.push(parent);
	}
	res.push_back(queue.top());
	return res;
}
// 生成编码字典
void encode(vector<Node*>& v, int root, string& code, map<char, string>& dic) {
	if (v[root]->isLeaf()) {
		dic[v[root]->ch] = code;
		return;
	}

	string lcode = code;
	string rcode = code;
	lcode.push_back('0');
	rcode.push_back('1');
	encode(v, v[root]->left, lcode, dic);
	encode(v, v[root]->right, rcode, dic);
}

//int main() {
//	cout << "请输入需要进行Huffman编码的码元个数：" << endl;
//	int n;
//	cin >> n;
//	cout << "请输入" << n << "个码元及概率（权重）：" << endl;
//	vector<Node*> v;
//	for (int i = 0; i < n; ++i) {
//		char ch;
//		float weight;
//		cin >> ch >> weight;
//		v.push_back(new Node(-1, -1, -1, ch, weight));
//	}
//	vector<Node*>&& tree = buildTree(v);
//	map<char, string> dic;
//	encode(tree, tree.size() - 1, string("0"), dic);
//	cout << "编码字典为：" << endl;
//	for (auto p : dic)
//		cout << p.first << " " << p.second << endl;
//	v.clear();
//	tree.clear();
//}