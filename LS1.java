import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * 
 * @author <a href = "http://1161625901@qq.com"> �˼��� </a>
 * @since 2018/10/6
 * <br>��{@code Monster}�ǹ������ԡ�
 * <p>��Ϊ�����Ǽ�棬����ֻ�й�����������ֵ��
 */
class Monster
{
	int HP;
	int	AP;
	/**
	 * @param h �ǹ�������ֵ��
	 * @param a �ǹ��﹥������
	 */
	Monster(int h, int a)
	{
		HP = h;
		AP = a;
	}
};
/**
 * 
 * @author <a href = "http://1161625901@qq.com"> �˼��� </a>
 * @since 2018/10/6
 * <p>��{@code LS}����Ϸ��ʵ�֣�
 * <p>������ʼӢ�۵Ĵ�����������ٻ��ͽ���������������ȡ�
 *
 */
public class LS1
{
	/**
	 * 
	 * <p>����{@code main}�������LS��ȫ������
	 * @param args û��ʵ�ʺ���
	 */
	public static void main(String[] args)
	{
		Logger log = LogManager.getLogger("MyLog");
		List<Monster>[] p = new ArrayList[2];
		for (int i = 0; i < 2; i++)
			p[i] = new ArrayList<Monster>();
		Scanner read = new Scanner(System.in);
		int n = read.nextInt();
		read.nextLine();
		p[0].add(new Monster(30, 0));
		p[1].add(new Monster(30, 0));
		int Player = 0,
			Position = 0,
			AP = 0,
			HP = 0,
			Attacker = 0,
			Defender = 0;
		while(n-- != 0)
		{
			String Instruction = read.next();
			/**
			 * Opposites��ָ��ǰ��ҵĶԷ�
			 */
			int Opposites = 1 - Player;
			/**
			 * <p>����ģ���ٻ�����
			 */
			if (Instruction.equals("summon"))
			{
				Position = read.nextInt();
				AP = read.nextInt();
				HP = read.nextInt();
				p[Player].add(Position, new Monster(HP, AP));
				log.info("���" + (Player + 1) + "��λ��" + Position + "�ٻ���һֻ������Ϊ" + AP + "����ֵΪ" + HP + "����ӡ�");
			}
			/**
			 * <p>����ģ���������
			 */
			else if (Instruction.equals("attack"))
			{
				Attacker = read.nextInt();
				Defender = read.nextInt();
				p[Player].get(Attacker).HP -= p[Opposites].get(Defender).AP; 
				p[Opposites].get(Defender).HP -= p[Player].get(Attacker).AP;
				if(p[Player].get(Attacker).HP <= 0)
					p[Player].remove(Attacker);
				if(Defender != 0 && p[Opposites].get(Defender).HP <= 0)
					p[Opposites].remove(Defender);
			}
			/**
			 * <p>����ģ��غϽ���
			 */
			else
				Player = Opposites;
		}
		if (p[0].get(0).HP <= 0) 
			System.out.println(-1);
		else if (p[1].get(0).HP <= 0) 
			System.out.println(1);
		else 
			System.out.println(0);
		System.out.println(p[0].get(0).HP);
		System.out.print(p[0].size() - 1);
		for (int i = 1; i < p[0].size(); i++)
			System.out.print(" " + p[0].get(i).HP);
		System.out.println();
		System.out.println(p[1].get(0).HP);
		System.out.print(p[1].size() - 1);
		for (int i = 1; i < p[1].size(); i++)
			System.out.print(" " + p[1].get(i).HP);
		read.close();
	}
}