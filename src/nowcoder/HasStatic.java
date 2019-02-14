package nowcoder;

public class HasStatic {
    private static int x = 100;

    public static void main(String[] args) {
        HasStatic h1 = new HasStatic();
        h1.x++;
    }
}

