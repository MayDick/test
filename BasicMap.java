import java.util.Arrays;
import java.util.Scanner;

public class BasicMap {
	Scanner read;
	int[][] map;
	/**
	 * ��ͼ��ʼ������int�洢��0����õ������������Ч����-1���������ߣ���������������õ�������Ч������ͬ�������в�ͬЧ����
	 * @param n ��ͼ�ĳ�
	 * @param m ��ͼ�Ŀ�
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
