package ch02;

import ch01.Plastic;
import ch01.Powder;

public class MainTest2 {
	
	public static void main(String[] args) {
		
		// 재료 선언
		Plastic plastic01 = new Plastic();
		Powder powder01 = new Powder();
		Water water01 = new Water();
		// 사용하는 시점에 T 대신 어떤 자료형을 사용할지 지정을 해주어야 함.
		
		// 최상위 Object 를 활용할때와 비교
		// 형변환(다운캐스팅) 할 필요가 없다.
		
		GenericPrinter<Plastic> genericPrinter1 = new GenericPrinter<>(); // GenericPrinter의 플라스틱 자료를 사용
		genericPrinter1.setMaterial(plastic01); // 메소드 의존 주입	method 에 Plastic01에 대한 정보를 넣어줌
		Plastic returnPlastic = genericPrinter1.getMaterial(); // 위 method 를 returnPlastic 에 담아줌
		System.out.println(returnPlastic.toString()); // System.out.println();
		
		GenericPrinter<Powder> genericPrinter2 = new GenericPrinter<>();
		genericPrinter2.setMaterial(powder01);
		Powder returnPowder = genericPrinter2.getMaterial();
		System.out.println(returnPowder.toString());
		
		// 컴파일 시점에 오류를 알려줘서 안정적인 코드 작업이 진행된다.
		// Powder returnPowder = genericPrinter1.getMaterial(); 오류 발생
		GenericPrinter<Water> genericPrinter3 = new GenericPrinter<>();
		genericPrinter3.setMaterial(water01);
		Water returnWater = genericPrinter3.getMaterial();
		System.out.println(returnWater.toString());
		
		// 제네릭 프로그래밍의 단점
		// 사용하는 시점에 무엇이든 담을 수 있기 때문에 클래스 설계자 입장으로 바라볼때,
		// 의도하지 않은 타입이 담길 수도 있다.
		
		// 해결방법은 <T extends 클래스> 문법을 사용한다.
		
		
		
	} // end of main
	
} // end of class
