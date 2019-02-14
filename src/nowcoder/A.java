package nowcoder;

public class A {

    public A foo() { return this; }

    public class B extends A {

        public A foo() {
            return this;
        }
    }

    public class C extends B {
        // public int foo() { return 1;}

    }
}
