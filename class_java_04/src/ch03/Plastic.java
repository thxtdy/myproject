package ch03;
/*
 * T extends 클래스 문법을 사용하기 위해 설계
 */
public class Plastic extends Material{

	@Override
	public String toString() {
		return "재료는 플라스틱입니다.";
	}
	
	@Override
	public void doPrintting() {
		System.out.println("플라스틱 재료로 출력합니다.");
		
	}
	
	
}
