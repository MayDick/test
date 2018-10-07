import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * <p>类{@code Monster}是怪物类，拥有属性：攻击力和生命力。
 * @author 菜鸡肺
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
 * <p>类{@code Player}是玩家类，拥有属性：怪物列表（也就是自己的随从和英雄）。
 * @author 菜鸡肺
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
 * <p>类{@code BattleField}是战场类。
 * <br>BattleField
 * <br>拥有属性：玩家。
 * <br>拥有方法：CallMonster，Attack，Death，CheckWinner，Show。
 * @author 菜鸡肺
 * @since 2018/10/7
 */
class BattleField
{
	/**
	 * <p>玩家列表，炉石传说每局只有两个玩家对弈。
	 * @author 菜鸡肺
	 */
	Player[] P = new Player[2];
	/**
	 * <p>游戏开始，默认创建两个玩家，并给每个玩家创建一只英雄
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
	 * <p>召唤随从
	 * @param i 召唤随从的玩家
	 * @param p 随从召唤的位置
	 * @param a 随从攻击力
	 * @param h 随从生命力
	 */
	void CallMonster(int i, int p, int a, int h)
	{
		P[i].MonsterList.add(p, new Monster(a, h));
	}
	/**
	 * <p>攻击
	 * <br>一只怪物对另一只怪物发起攻击，双方都要承受伤害。
	 * @param i 攻击方的玩家
	 * @param a 攻击方怪物位置索引
	 * @param d 防守方怪物位置索引
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
	 * <p>死亡
	 * <br>当怪物生命值为非负数时，从玩家随从列表移除。
	 * @param i 死亡随从的玩家
	 * @param p 死亡随从的位置索引
	 */
	void Death(int i, int p)
	{
		P[i].MonsterList.remove(p);
	}
	/**
	 * <p>检查胜利者
	 * <br>在所有Instruction结束后，对本局游戏结果进行判定。
	 * @return 返回一个游戏结果
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
	 * <p>展示游戏信息
	 * <br>在游戏结束后，输出战场信息。
	 * @param a 游戏结果
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
 * <p>类{@code LSverb}是主类
 * <br>负责接受数据的输入，并将值传输给BattleField
 * @author 菜鸡肺
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