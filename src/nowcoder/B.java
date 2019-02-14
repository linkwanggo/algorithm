package nowcoder;

public class B {
    int x, y, z, u;
    public B(int a, int b) {
        x = a;
        y = b;
    }

//    public B(int a, int b, int c, int d) {
//        this(a, b);
//        z = c;
//        u = d;
//    }

    public B(int a, int b, int c, int d) {
        new B(a, b);
        z = c;
        u = d;
    }

    public static void main(String[] args) {
        B b = new B(1, 2, 3, 4);
        System.out.println(111);
    }
}
