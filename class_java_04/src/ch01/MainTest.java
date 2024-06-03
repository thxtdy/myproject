package ch01;

public class MainTest {
		
	public static void main(String[] args) {
		
		// ThreeDprinter 에 종속된 Plastic 을 불러서 sysout
		ThreeDPrinter dPrinter1 = new ThreeDPrinter();
		dPrinter1.setMaterial(new Plastic());
		System.out.println(dPrinter1.material.toString());
		
		
		
		
		// 위 ThreeDPrinter 한계는 재료가 플라스틱에 종속되어있다.
		// 하지만 사용자 입장에서 재료를 파우더로 변경한다면
		// 코드의 수정이나 새로운 클래스가 필요하다.
		System.out.println("=========================");
		
		// ThreeDPrinter에 종속된 Powder 를 불러서 sysout
		ThreeDPrinter2 dPrinter2 = new ThreeDPrinter2();
		dPrinter2.setMaterial(new Powder());
		System.out.println(dPrinter2.material.toString());
		
		System.out.println("=========================");
		
		// ThreeDPrinter 에 종속된 object(Plastic, Power)를 sysout
		ThreeDPrinter3 dPrinter3 = new ThreeDPrinter3();
		dPrinter3.setMaterial(new Plastic());
		System.out.println(dPrinter3.material.toString());
		
		System.out.println("=========================");
		
		ThreeDPrinter3 dPrinter3_2 = new ThreeDPrinter3();
		dPrinter3_2.setMaterial(new Powder());
		System.out.println(dPrinter3_2.material.toString());
		
		Plastic plastic01 = (Plastic)dPrinter3.getMaterial(); //다운 캐스팅
		Powder powder01 = (Powder)dPrinter3.getMaterial(); // 오류나는 코드가 된다.
		
	}
	
	
	
}
