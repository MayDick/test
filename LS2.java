import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * <p>��{@code Monster}�ǹ����࣬ӵ�����ԣ�����������������
 * @author �˼���
 * @since 2018/10/7
 */
class Monster
{
	int HP, AP;
	Monster(int a, int h)
	{
		AP = a;
		HP = h;
	}
};
/**
 * <p>��{@code Player}������࣬ӵ�����ԣ������б�Ҳ�����Լ�����Ӻ�Ӣ�ۣ���
 * @author �˼���
 * @since 2018/10/7
 */
class Player
{
	List<Monster> MonsterList;
	Player()
	{
		MonsterList = new ArrayList<Monster>();
	}
};
/**
 * <p>��{@code BattleField}��ս���ࡣ
 * <br>BattleField
 * <br>ӵ�����ԣ���ҡ�
 * <br>ӵ�з�����CallMonster��Attack��Death��CheckWinner��Show��
 * @author �˼���
 * @since 2018/10/7
 */
class BattleField
{
	/**
	 * <p>����б�¯ʯ��˵ÿ��ֻ��������Ҷ��ġ�
	 * @author �˼���
	 */
	Player[] P = new Player[2];
	/**
	 * <p>��Ϸ��ʼ��Ĭ�ϴ���������ң�����ÿ����Ҵ���һֻӢ��
	 */
	BattleField()
	{
		for (int i = 0; i < 2; i++)
		{
			P[i] = new Player();
			P[i].MonsterList.add(new Monster(0, 30));
		}
	}
	/**
	 * <p>�ٻ����
	 * @param i �ٻ���ӵ����
	 * @param p ����ٻ���λ��
	 * @param a ��ӹ�����
	 * @param h ���������
	 */
	void CallMonster(int i, int p, int a, int h)
	{
		P[i].MonsterList.add(p, new Monster(a, h));
	}
	/**
	 * <p>����
	 * <br>һֻ�������һֻ���﷢�𹥻���˫����Ҫ�����˺���
	 * @param i �����������
	 * @param a ����������λ������
	 * @param d ���ط�����λ������
	 */
	void Attack(int i, int a, int d)
	{
		int j = 1 - i,
			AttackerAP = P[i].MonsterList.get(a).AP,
			AttackerHP = P[i].MonsterList.get(a).HP,
			DefenderAP = P[j].MonsterList.get(d).AP,
			DefenderHP = P[j].MonsterList.get(d).HP;
		AttackerHP -= DefenderAP;
		DefenderHP -= AttackerAP;
		if (AttackerHP <= 0 && a != 0)
			Death(i, a);
		else
			P[i].MonsterList.get(a).HP = AttackerHP;
		if (DefenderHP <= 0 && d != 0)
			Death(j, d);
		else
			P[j].MonsterList.get(d).HP = DefenderHP;
	}
	/**
	 * <p>����
	 * <br>����������ֵΪ�Ǹ���ʱ�����������б��Ƴ���
	 * @param i ������ӵ����
	 * @param p ������ӵ�λ������
	 */
	void Death(int i, int p)
	{
		P[i].MonsterList.remove(p);
	}
	/**
	 * <p>���ʤ����
	 * <br>������Instruction�����󣬶Ա�����Ϸ��������ж���
	 * @return ����һ����Ϸ���
	 */
	int CheckWinner()
	{
		if (P[0].MonsterList.get(0).HP <= 0)
			return -1;
		if (P[1].MonsterList.get(0).HP <= 0)
			return 1;
		return 0;
	}
	/**
	 * <p>չʾ��Ϸ��Ϣ
	 * <br>����Ϸ���������ս����Ϣ��
	 * @param a ��Ϸ���
	 */
	void Show(int a)
	{
		System.out.println(a);
		System.out.println(P[0].MonsterList.get(0).HP);
		System.out.print(P[0].MonsterList.size() - 1);
		for (int i = 1; i < P[0].MonsterList.size(); i++)
			System.out.print(" " + P[0].MonsterList.get(i).HP);
		System.out.println();
		System.out.println(P[1].MonsterList.get(0).HP);
		System.out.print(P[1].MonsterList.size() - 1);
		for (int i = 1; i < P[1].MonsterList.size(); i++)
			System.out.print(" " + P[1].MonsterList.get(i).HP);
	}
};
/**
 * <p>��{@code LSverb}������
 * <br>����������ݵ����룬����ֵ�����BattleField
 * @author �˼���
 * @since 2018/10/7
 */
public class LS2
{
	public static void main(String[] args)
	{
		BattleField b = new BattleField();
		Scanner read = new Scanner(System.in);
		int n = read.nextInt();
		read.nextLine();
		int Player = 0;
		while((n--) != 0)
		{
			int Opposites = 1 - Player;
			String Instruction = read.next();
			if (Instruction.equals("summon"))
				b.CallMonster(Player, read.nextInt(), read.nextInt(), read.nextInt());
			else if (Instruction.equals("attack"))
				b.Attack(Player, read.nextInt(), read.nextInt());
			else
				Player = Opposites;
		}
		b.Show(b.CheckWinner());
		read.close();
	}
}