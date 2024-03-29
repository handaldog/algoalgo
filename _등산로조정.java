import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _등산로조정 {
	static class node {
		int x;
		int y;

		node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int di[] = { 1, -1, 0, 0 }; // 하 상 우 좌
	static int dj[] = { 0, 0, 1, -1 };
	static boolean visit[][];
	static int n, k, cnt;
	static int arr[][];
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<node> list = new ArrayList<>();

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {

			int maxnum = Integer.MIN_VALUE;

			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			arr = new int[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					maxnum = Math.max(maxnum, arr[i][j]);
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (arr[i][j] == maxnum) {
						list.add(new node(i, j));
					}
				}
			}

			for (int i = 0; i < list.size(); i++) {

				visit = new boolean[n][n];
				dfs(list.get(i).x, list.get(i).y, 1, 1);
			}
			System.out.println(max);

		}
	}

	public static void dfs(int i, int j, int kcnt, int cnt) {

		visit[i][j] = true;

		for (int c = 0; c < 4; c++) {
			int nexti = i + di[c];
			int nextj = j + dj[c];

			if (nexti >= 0 && nextj >= 0 && nexti < n && nextj < n && !visit[nexti][nextj]) {

				if (arr[nexti][nextj] < arr[i][j]) { // 등산로를 만들 수 있다!!
//					visit[nexti][nextj] = true;
					dfs(nexti, nextj, kcnt, cnt + 1); // 깎을 기회는 남았으니 빼지 않고 넘겨줌.

				} else {
					if (kcnt == 1) { // 지형깎는 기회가 남아있으면
						arr[nexti][nextj] -= k;
						if (arr[nexti][nextj] < arr[i][j]) { // 깎았더니 등산로를 만들 수 있다!
//							visit[nexti][nextj] = true;
							dfs(nexti, nextj, kcnt - 1, cnt + 1);
							arr[nexti][nextj] += k;

						} else { // 깎았는데도 등산로를 못 만들면
							continue;
						}
					} else { // 등산로를 못만드는데 지형 깎을 기회가 없으면
						continue;
					}

				}

			}

		}
		max = Math.max(max, cnt);
		System.out.println(max);
//		for (int h = 0; h < n; h++) {
//			for (int b = 0; b < n; b++) {
//				System.out.print(visit[h][b]);
//			}
//			System.out.println();
//		}
//
//		
//		System.out.println("------------");

		visit[i][j] = false;
	}
}



/////////////////////////////////////// 두번째 시도

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _등산로조정 {

	static class node {
		int x;
		int y;

		node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int di[] = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int dj[] = { 0, 0, -1, 1 };
	static boolean visit[][];
	static int n, k, cnt;
	static int arr[][];
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		ArrayList<node> list = new ArrayList<>();

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {

			int maxnum = Integer.MIN_VALUE;

			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());

			arr = new int[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					maxnum = Math.max(maxnum, arr[i][j]);
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (arr[i][j] == maxnum) {
						list.add(new node(i, j));
					}
				}
			}

			for (int i = 0; i < list.size(); i++) {

				visit = new boolean[n][n];
				dfs(list.get(i).x, list.get(i).y, 1, 1);
			}
			System.out.println(max);

		}
	}

	public static void dfs(int i, int j, int kcnt, int cnt) {

		visit[i][j] = true;

		for (int c = 0; c < 4; c++) {
			int nexti = i + di[c];
			int nextj = j + dj[c];

			if (nexti >= 0 && nextj >= 0 && nexti < n && nextj < n && !visit[nexti][nextj]) {

				if (arr[nexti][nextj] < arr[i][j]) { // 등산로를 만들 수 있다!!
//					visit[nexti][nextj] = true;
					dfs(nexti, nextj, kcnt, cnt + 1); // 깎을 기회는 남았으니 빼지 않고 넘겨줌.

				} else {
					if (kcnt == 1) { // 지형깎는 기회가 남아있으면
						for (int kk = 1; kk <= k; kk++) {
							arr[nexti][nextj] -= kk;
							if (arr[nexti][nextj] < arr[i][j]) {// 깎았더니 등산로를 만들 수 있다!
								visit[nexti][nextj] = true;
								for (int cc = 0; cc < 4; cc++) {
									int chnexti = nexti + di[cc];
									int chnextj = nextj + dj[cc];

									if (chnexti >= 0 && chnextj >= 0 && chnexti < n && chnextj < n
											&& !visit[chnexti][chnextj]) {

										if (arr[nexti][nextj] > arr[chnexti][chnextj]) {
											visit[chnexti][chnextj] = true;

//											visit[nexti][nextj] = true;
											dfs(nexti, nextj, kcnt - 1, cnt + 1);

											visit[chnexti][chnextj] = false;
										}

									}
								}

							} else { // 깎았는데도 등산로를 못 만들면
								continue;
							}
							arr[nexti][nextj] += kk;
							visit[nexti][nextj] = false;
						}

					} else { // 등산로를 못만드는데 지형 깎을 기회가 없으면
						continue;
					}

				}

			}

		}

		visit[i][j] = false;

//		for (int h = 0; h < n; h++) {
//			for (int b = 0; b < n; b++) {
//				System.out.print(arr[h][b]);
//			}
//			System.out.println();
//		}
//		System.out.println(cnt);
		max = Math.max(max, cnt);
		System.out.println(max);

		// System.out.println("------------");

	}
}


///////////////////////////////////// 답지를 보고 완탐의 개념을 깨닫고 다시 푼 세번째

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class _등산로조정_세번째 {

	static class node {
		int x;
		int y;

		node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static int di[] = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int dj[] = { 0, 0, -1, 1 };

	static int n, K;
	static int arr[][];
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int tc = Integer.parseInt(br.readLine());

		for (int t = 1; t <= tc; t++) {

			ArrayList<node> list = new ArrayList<>();

			max = Integer.MIN_VALUE;

			int maxnum = Integer.MIN_VALUE;

			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			arr = new int[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					maxnum = Math.max(maxnum, arr[i][j]);
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (arr[i][j] == maxnum) {
						list.add(new node(i, j));
					}
				}
			}

			for (int k = 0; k <= K; k++) {
				for (int r = 0; r < n; r++) {
					for (int c = 0; c < n; c++) {
						if (arr[r][c] - k < 0)
							continue;

						arr[r][c] -= k;

						for (int i = 0; i < list.size(); i++) {

							dfs(list.get(i).x, list.get(i).y, 1);

						}
						arr[r][c] += k;
					}
				}
			}

			System.out.println("#" + t + " " + max);

		}
	}

	public static void dfs(int i, int j, int cnt) {

		for (int c = 0; c < 4; c++) {
			int nexti = i + di[c];
			int nextj = j + dj[c];

			if (nexti >= 0 && nextj >= 0 && nexti < n && nextj < n) {
				if ((arr[i][j] > arr[nexti][nextj])) {

					dfs(nexti, nextj, cnt + 1);

				}
			}
		}
		max = Math.max(max, cnt);

	}

}



