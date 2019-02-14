package algorithm4.chapter_1;

public class Test {

    public void test1_1_8() {
        int sum = 0;
        for (int i = 1; i < 1000; i*=2) {
            for (int j = 0; j < 1000; j++) {
                sum++;
            }
        }
        System.out.println(sum);
    }


    public static void main(String[] args) {
        Test test = new Test();
        test.test1_1_8();
        System.out.println(Math.log(1000) / Math.log(2));
    }
}
