package com.oscarshen09.disjointSet.maze;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Maze extends JPanel {
	private static final long serialVersionUID = 1L;
	private Lattice[][] maze = null;
	private int num = -1;// 行列数
	private int padding = -1;// 边框到迷宫的距离
	private int width = -1;// 每个格子(Lattice)的宽度
	private int ballX = 0, ballY = 0;// 用来记录球的位置
	private boolean drawPath = false;// 记录是否画出路线
 
	public Maze(int num, int padding, int width) {
		this.num = num;
		this.padding = padding;
		this.width = width;
		maze = new Lattice[num][num];
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < num; j++)
				maze[i][j] = new Lattice(i, j);
		}
		createMaze();
		setKeyListener();
		this.setFocusable(true);
	}

	/**
	 * 建立一个随机的迷宫
	 */
	private void createMaze() {
		Random random = new Random();
		int x = random.nextInt(num);
		int y = random.nextInt(num);
		Stack<Lattice> s = new Stack<>();// 用来存储还没有被搜索过的父亲
		Lattice p = maze[x][y];
		s.push(p);

		Lattice[] neis = null;
		while (!s.isEmpty()) {
			p = s.pop();
			p.setFlag(Lattice.INTREE);
			neis = getNeis(p);
			int r = random.nextInt(5);
			for (int i = 0; i < 4; i++) {
				r++;
				if (r > 3)
					r %= 4;
				if (neis[r] == null || neis[r].getFlag() == Lattice.INTREE)
					continue;
				neis[r].setFather(p);
				s.push(neis[r]);
			}
		}
	}

	/**
	 * 获取格子的邻居
	 * 
	 * @param p
	 * @return
	 */
	private Lattice[] getNeis(Lattice p) {
		int[] dir = { -1, 0, 1, 0, -1 };// 用于相加来获取上下左右的方位
		Lattice[] neis = new Lattice[4];
		int x = p.getX(), y = p.getY();
		for (int i = 0; i < 4; i++) { // 获取格子邻居的顺序为上右下左
			int nx = x + dir[i];
			int ny = y + dir[i + 1];
			if (isOutOfBound(nx, ny))
				continue;
			else
				neis[i] = maze[nx][ny];
		}
		return neis;
	}

	/**
	 * 判断当前格子是否越界
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isOutOfBound(int x, int y) {
		return x < 0 || y < 0 || x >= num || y >= num;
	}

	/**
	 * 用来监听键盘输入
	 */
	private void setKeyListener() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int c = e.getKeyCode();
				move(c);
				repaint();
				checkIsWin();
			}
		});
	}

	/**
	 * 判断监听到的按键来移动或者显示（隐藏）路径
	 * 
	 * @param c
	 */
	private void move(int c) {
		int tx = ballX, ty = ballY;
		switch (c) {
		case KeyEvent.VK_LEFT:
			ty--;
			break;
		case KeyEvent.VK_RIGHT:
			ty++;
			break;
		case KeyEvent.VK_UP:
			tx--;
			break;
		case KeyEvent.VK_DOWN:
			tx++;
			break;
		case KeyEvent.VK_SPACE:
			if (drawPath)
				drawPath = false;
			else
				drawPath = true;
		default:
		}
		if (!isOutOfBound(tx, ty)
				&& (maze[tx][ty].getFather() == maze[ballX][ballY] || maze[ballX][ballY] == maze[tx][ty])) {
			ballX = tx;
			ballY = ty;
		}
	}

	/**
	 * 检查是否胜利
	 */
	private void checkIsWin() {
		if (ballX == num - 1 && ballY == num - 1) {
			JOptionPane.showMessageDialog(null, "YOU WIN !", "你走出了迷宫。", JOptionPane.PLAIN_MESSAGE);
			init();
		}
	}

	/**
	 * 如果已经胜利，那么就初始化
	 */
	private void init() {
		for (int i = 0; i < num; i++)
			for (int j = 0; j < num; j++) {
				maze[i][j].setFather(null);
				maze[i][j].setFlag(Lattice.NOTINTREE);
			}
		ballX = 0;
		ballY = 0;
		createMaze();
		this.setFocusable(true);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i <= num; i++) {
			g.drawLine(padding + i * width, padding, padding + i * width, padding + num * width);
			g.drawLine(padding, padding + i * width, padding + num * width, padding + i * width);
		}
		repaint();
		g.setColor(this.getBackground());
		for (int i = 0; i < num; i++)
			for (int j = 0; j < num; j++) {
				Lattice f = maze[i][j].getFather();
				if (f == null)
					continue;
				int fi = f.getX(), fj = f.getY();
				clearFence(i, j, fi, fj, g);
			}
		g.drawLine(padding, padding + 1, padding, padding + width - 1);
		int last = padding + num * width;
		g.drawLine(last, last - 1, last, last - width + 1);
		g.setColor(Color.RED);
		g.fillOval(getCenterX(ballY) - width / 3, getCenterY(ballX) - width / 3, width / 2, width / 2);
		if (drawPath == true)
			drawPath(g);
	}

	/**
	 * 消除两个格子之间的屏障
	 * 
	 * @param i
	 * @param j
	 * @param fi
	 * @param fj
	 * @param g
	 */
	private void clearFence(int i, int j, int fi, int fj, Graphics g) {
		int x1, x2, y1, y2;
		if (i == fi) {
			x1 = padding + width * (j > fj ? j : fj);
			y1 = padding + i * width;
			x2 = x1;
			y2 = y1 + width;
			y1++;
			y2--;
		} else {
			x1 = padding + j * width;
			y1 = padding + width * (i > fi ? i : fi);
			x2 = x1 + width;
			y2 = y1;
			x1++;
			x2--;
		}
		g.drawLine(x1, y1, x2, y2);
	}

	/**
	 * 画出路径图
	 * 
	 * @param g
	 */
	private void drawPath(Graphics g) {
		Color PATH_COLOR = Color.ORANGE, BOTH_PATH_COLOR = Color.PINK;
		if (drawPath == true)
			g.setColor(PATH_COLOR);
		else
			g.setColor(this.getBackground());
		Lattice p = maze[num - 1][num - 1];
		while (p.getFather() != null) {
			p.setFlag(2);
			p = p.getFather();
		}
		g.fillOval(getCenterX(p) - width / 3, getCenterY(p) - width / 3, width / 2, width / 2);
		p = maze[0][0];
		while (p.getFather() != null) {
			if (p.getFlag() == 2) {
				p.setFlag(3);
				g.setColor(BOTH_PATH_COLOR);
			}

			g.drawLine(getCenterX(p), getCenterY(p), getCenterX(p.getFather()), getCenterY(p.getFather()));
			p = p.getFather();
		}

		g.setColor(PATH_COLOR);
		p = maze[num - 1][num - 1];
		while (p.getFather() != null) {
			if (p.getFlag() == 3)
				break;
			g.drawLine(getCenterX(p), getCenterY(p), getCenterX(p.getFather()), getCenterY(p.getFather()));
			p = p.getFather();
		}

	}

	private int getCenterX(int x) {
		return padding + x * width + width / 2;
	}

	private int getCenterY(int y) {
		return padding + y * width + width / 2;
	}

	private int getCenterX(Lattice p) {
		return padding + p.getY() * width + width / 2;
	}

	private int getCenterY(Lattice p) {
		return padding + p.getX() * width + width / 2;
	}

	class Lattice {
		static final int NOTINTREE = 0;// 不在树中
		static final int INTREE = 1;// 已经在树中
		static final int INPATH = 2;// 在以终点网上回溯的树中
		static final int INBOTHPATH = 3;// 同时在以终点和起点回溯的树中
		Lattice father = null;// 指向回溯的父亲
		private int flag = -1;// 每一个格子的标志
		private int x = -1;// 格子的横坐标
		private int y = -1;// 格子的纵坐标

		public Lattice(int x, int y) {
			this.x = x;
			this.y = y;
			this.flag = NOTINTREE;
		}

		public Lattice getFather() {
			return father;
		}

		public void setFather(Lattice father) {
			this.father = father;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public int getFlag() {
			return flag;
		}

		public void setFlag(int flag) {
			this.flag = flag;
		}

		/**
		 * 重写toString方法，方便调试
		 */
		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}
	}

	public static void main(String[] args) {
		final int NUM = 30, width = 600, padding = 20, LX = 200, LY = 100;
		JPanel p = new Maze(NUM, padding, (width - padding - padding) / NUM);
		JFrame frame = new JFrame("MAZE(按空格键显示或隐藏路径)");
		frame.getContentPane().add(p);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(width, width + padding);
		frame.setLocation(LX, LY);
		frame.setVisible(true);
	}
}
