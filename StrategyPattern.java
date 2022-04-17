package designpatterns;

import java.util.ArrayList;
import java.util.Random;

public class StrategyPattern {
	public static void main(String[] args) {
		
		
		System.out.println("Character 생성");
		Character userA = new Elf("흑염룡");
		Character userB = new Dewarf("음악은 국가가 허락한 마약");
		
		ArrayList<Attack> attackList = new ArrayList<>();
		attackList.add(new AttackHigh());
		attackList.add(new AttackLow());
		
		ArrayList<Defend> defendList = new ArrayList<>();
		defendList.add(new GuardHigh());
		defendList.add(new GuardLow());
		
		Random rn = new Random();
		
		for(int i=0; i<10; i++){
			
			Character attacker = i%2==0? userA: userB;
			Character defender = attacker.equals(userA)? userB: userA;
			System.out.println("\n turn " + i);
			System.out.println("------------------");
			System.out.println("Attacker: " + attacker.id);
			System.out.println("Defender: " + defender.id);
			attacker.setSelectedAttack(attackList.get(rn.nextInt(2)));		
			defender.setSelectedDefend(defendList.get(rn.nextInt(2)));
			attacker.displayAttack();
			defender.displayDefend();
		}
	}

}

interface Attack {
	public void attack();
}

class AttackHigh implements Attack {
	@Override
	public void attack() {
		System.out.println("logic 1: 날아가기");
		System.out.println("logic 2: 칼을 들어서");
		System.out.println("logic 3: 어깨 찌르기");
	}
}

class AttackLow implements Attack {
	@Override
	public void attack() {
		System.out.println("logic 1: 달려가기");
		System.out.println("logic 2: 몸을 숙여서");
		System.out.println("logic 3: 다리 베기");
	}
}

interface Defend {
	public void defend();
}

class GuardHigh implements Defend {
	@Override
	public void defend() {
		System.out.println("logic 1: 왼발을 뒤로 빼고");
		System.out.println("logic 2: 방패를 높인뒤");
		System.out.println("logic 3: 상체 보호하기");
	}
}

class GuardLow implements Defend {
	@Override
	public void defend() {
		System.out.println("logic 1: 몸을 숙이고");
		System.out.println("logic 2: 방패를 내린뒤");
		System.out.println("logic 3: 하체를 보호하기");
	}
}

abstract class Character {
	String id;
	String race;

	Attack selectedAttack;
	Defend selectedDefend;

	private void performAttack() {
		selectedAttack.attack();
	}

	private void performDefend() {
		selectedDefend.defend();
	}

	public void displayAttack() {
		System.out.println(race);
		performAttack();
	}
	
	public void displayDefend() {
		System.out.println(race);
		performDefend();
	}
	
	public void setSelectedAttack(Attack selectedAttack) {
		this.selectedAttack = selectedAttack;
	}

	public void setSelectedDefend(Defend selectedDefend) {
		this.selectedDefend = selectedDefend;
	}
}


class Dewarf extends Character {
	public Dewarf(String id) {
		this.id = id;
		this.race = "난쟁이 포식자";
	}
}

class Knight extends Character {
	public Knight(String id) {
		this.id = id;
		this.race = "우락부락 갑옷 기사";
	}
}

class Elf extends Character {

	public Elf(String id) {
		this.id = id;
		this.race = "날씬한 미녀";
	}
	
}
