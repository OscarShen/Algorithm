

#include <vector>
#include <iostream>

class OptimalBST {
public:
	OptimalBST(const int SCALE, const std::vector<float> &p, const std::vector<float> &q) :
		n(SCALE), p(p), q(q) {
		e = std::vector<std::vector<float>>(n + 2, std::vector<float>(n + 1));
		w = std::vector<std::vector<float>>(n + 2, std::vector<float>(n + 1));
		root = std::vector<std::vector<int>>(n + 2, std::vector<int>(n + 1));
	}

	void optimal_bst() {
		for (int i = 1; i <= n + 1; ++i) {
			float a = e[i][i - 1];
			e[i][i - 1] = q[i - 1];
			w[i][i - 1] = q[i - 1];
		}
		for (int len = 1; len <= n; ++len) {
			for (int i = 1; i <= n - len + 1; ++i) {
				int j = i + len - 1;
				e[i][j] = this->MAX;
				w[i][j] = w[i][j - 1] + p[j] + q[i];
				for (int r = i; r <= j; ++r) {
					float t = e[i][r - 1] + e[r + 1][j] + w[i][j];
					if (t < e[i][j]) {
						e[i][j] = t;
						root[i][j] = r;
					}
				}
			}
		}
	}

	//打印最优二叉查找树的结构
	void print_root()
	{
		for (auto i : root) {
			for (auto j : i) {
				std::cout << j << " ";
			}
			std::cout << std::endl;
		}
	}
private:
	int n;
	std::vector<std::vector<float>> e;
	std::vector<std::vector<float>> w;
	std::vector<std::vector<int>> root;
	std::vector<float> p;
	std::vector<float> q;
	const float MAX = 10000;
};

int main() {
	std::vector<float> p = { 0.0f,0.15f,0.10f,0.05f,0.10f,0.20f };
	std::vector<float> q = { 0.05f,0.10f,0.05f,0.05f,0.05f,0.10f };
	OptimalBST *obst = new OptimalBST(p.size() - 1, p, q);
	obst->optimal_bst();
	obst->print_root();
	delete obst;
}