
public class TestBody {

    public static void main(String[] args) { Interaction(); }

    private static void Interaction(){
        System.out.println("测试两个物体之间的相互力...");
        Planet p1 = new Planet(1.0, 1.0, 3.0, 4.0, 5.0, "jupiter.gif");
        Planet p2 = new Planet(2.0, 1.0, 3.0, 4.0, 4e11, "jupiter.gif");
        checkEquals(p1.calcForceExertedBy(p2), p2.calcForceExertedBy(p1));
    }

    private static void checkEquals(double actual,double actual2){
        if (Math.abs(actual) == Math.abs(actual2)) {
            System.out.println("两个物体之间的相互力: " + actual + " 测试通过！");
        } else {
            System.out.println("两个物体之间的相互力分别为 " + actual + " 和 " + actual2 +" 测试不通过");
        }
    }

}