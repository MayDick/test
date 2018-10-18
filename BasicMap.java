import java.util.Arrays;
import java.util.Scanner;

public class BasicMap {
	Scanner read;
	int[][] map;
	/**
	 * 地图初始化，以int存储，0代表该点可行走无特殊效果，-1代表不可行走，其他正整数代表该点有特殊效果，不同正整数有不同效果。
	 * @param n 地图的长
	 * @param m 地图的宽
	 */
	BasicMap(int n, int m)
	{
		this.map = new int[n][m];
		Arrays.fill(map, 0);
	}
	void SetUnaccess(int n)
	{
		while (n > 0)
		{
			read = new Scanner(System.in);
			int x1 = read.nextInt();
			int y1 = read.nextInt();
			int x2 = read.nextInt();
			int y2 = read.nextInt();
			for (int i = x1; i <= x2; i++)
				for (int j = y1; j <= y2; j++)
					map[i][j] = -1;
			n--;
		}
		read.close();
	}
	void SetSpecialArea(int n)
	{
		while (n > 0)
		{
			read = new Scanner(System.in);
			int x1 = read.nextInt();
			int y1 = read.nextInt();
			int x2 = read.nextInt();
			int y2 = read.nextInt();
			int e = read.nextInt();
			for (int i = x1; i <= x2; i++)
				for (int j = y1; j <= y2; j++)
					map[i][j] = e;
			n--;
		}
		read.close();
	}
}
