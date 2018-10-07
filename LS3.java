import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class{@code Monster}�ǹ����࣬ӵ�����ԣ��������������������ü����ܣ�ӵ�з��������ӹ��＼�ܡ�
 * @author �˼���
 * @since 2018/10/7
 */
class Monster
{
	int AP, HP, Cost;
	List<String> Skill;
	Monster(int a, int h, int c)
	{
		AP = a;
		HP = h;
		Cost = c;
		Skill = new ArrayList<String>();
	}
	void AddSkill(String s)
	{
		Skill.add(s);
	}
};
/**
 * Class{@code Card}�ǿ���ϵͳ��ӵ�����ԣ��������������б���ƶ�ʣ������ӵ�з�������ʼ�鿨�ͳ���鿨��
 * @author �˼���
 * @since 2018/10/7
 */
class Card
{
	int Rest, Current;
	List<Monster> MonsterCard;
	Card(int o)
	{
		Rest = 30;
		MonsterCard = new ArrayList<Monster>();
		Scanner read = new Scanner(System.in);
		int n;
		if (o == 0)
			n = 3;
		else
			n = 4;
		Current = n;
		Rest -= n;
		for (int i = 0; i < n; i++)
		{
			Monster m = new Monster(read.nextInt(), read.nextInt(), read.nextInt());
			int t = read.nextInt();
			for (int j = 0; j < t; j++)
				m.AddSkill(read.next());
			MonsterCard.add(m);
		}
		read.close();
	}
	int DrawCards()
	{
		
		Rest--;
		if (Rest < 0)
			return Rest;
		else
		{
			Current++;
			Scanner read = new Scanner(System.in);
			Monster m = new Monster(read.nextInt(), read.nextInt(), read.nextInt());
			int t = read.nextInt();
			for (int j = 0; j < t; j++)
				m.AddSkill(read.next());
			MonsterCard.add(m);
			read.close();
			return 0;
		}
	}
};
/**
 * <p>��{@code Player}������࣬ӵ�����ԣ��������ˮ�������ƺ͹����б�Ҳ�����Լ�����Ӻ�Ӣ�ۣ���
 * @author �˼���
 * @since 2018/10/7
 */
class Player
{
	Card Cards;
	String Name;
	int Crystal, MaxCrystal;
	List<Monster> MonsterList;
	Player(int i, String n)
	{
		MaxCrystal = 0;
		MonsterList = new ArrayList<Monster>();
		Cards = new Card(i);
		Name = n;
	}
};
/**
 * <p>��{@code BattleField}��ս���ࡣ
 * <br>BattleField
 * <br>ӵ�����ԣ���ҡ�
 * <br>ӵ�з�����NewTurn��CallMonster��Attack��Death��CheckWinner��Show��
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
	BattleField(String n1, String n2)
	{
			P[0] = new Player(0, n1);
			P[1] = new Player(1, n2);
			P[0].MonsterList.add(new Monster(0, 30, 0));
			P[1].MonsterList.add(new Monster(0, 30, 0));
	}
	void NewTurn(int i)
	{
		P[i].MaxCrystal++;
		P[i].Crystal = P[i].MaxCrystal;
		P[i].MonsterList.get(0).HP += P[i].Cards.DrawCards();
	}
	/**
	 * <p>�ٻ����
	 * <br>��Ҵ������ٻ����
	 * @param i �ٻ���ӵ����
	 * @param p ��������Ƶ�λ������
	 */
	void CallMonster(int i, int p)
	{
		Monster m = P[i].Cards.MonsterCard.get(p);
		P[i].MonsterList.add(p, m);
		P[i].Crystal -= m.Cost;
		P[i].Cards.MonsterCard.remove(p);
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
public class LS3
{
	public static void main(String[] args)
	{
		Scanner read = new Scanner(System.in);
		String Name1 = read.next(),
			   Name2 = read.next();
		BattleField b = new BattleField(Name1, Name2);
		int Player = 0;
		while(b.CheckWinner() != 0)
		{
			b.NewTurn(Player);
			int Opposites = 1 - Player;
			int n = read.nextInt();
			read.nextLine();
			for (int i = 0; i < n; i++)
			{
				String Instruction = read.next();
				if (Instruction.equals("summon"))
					b.CallMonster(Player, read.nextInt());
				else
					b.Attack(Player, read.nextInt(), read.nextInt());
			}
			Player = Opposites;
		}
		b.Show(b.CheckWinner());
		read.close();
	}
}
