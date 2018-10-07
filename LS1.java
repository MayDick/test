import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * 
 * @author <a href = "http://1161625901@qq.com"> 菜鸡肺 </a>
 * @since 2018/10/6
 * <br>类{@code Monster}是怪物属性。
 * <p>因为现在是简版，所以只有攻击力和生命值。
 */
class Monster
{
	int HP;
	int	AP;
	/**
	 * @param h 是怪物生命值。
	 * @param a 是怪物攻击力。
	 */
	Monster(int h, int a)
	{
		HP = h;
		AP = a;
	}
};
/**
 * 
 * @author <a href = "http://1161625901@qq.com"> 菜鸡肺 </a>
 * @since 2018/10/6
 * <p>类{@code LS}是游戏的实现：
 * <p>包括初始英雄的创建，怪物的召唤和进攻，怪物的死亡等。
 *
 */
public class LS1
{
	/**
	 * 
	 * <p>方法{@code main}将完成类LS的全部功能
	 * @param args 没有实际含义
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
			 * Opposites是指当前玩家的对方
			 */
			int Opposites = 1 - Player;
			/**
			 * <p>以下模拟召唤过程
			 */
			if (Instruction.equals("summon"))
			{
				Position = read.nextInt();
				AP = read.nextInt();
				HP = read.nextInt();
				p[Player].add(Position, new Monster(HP, AP));
				log.info("玩家" + (Player + 1) + "在位置" + Position + "召唤了一只攻击力为" + AP + "生命值为" + HP + "的随从。");
			}
			/**
			 * <p>以下模拟进攻过程
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
			 * <p>以下模拟回合结束
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