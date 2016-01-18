package kdc;

/**
 * Hello world!
 *
 */
public class App 
{
    private interface Base {
        default void  call() { System.out.println("Blah : ");}

    }

    @FunctionalInterface
    private interface C1 extends Base {
        default void  call() { System.out.println("c1 : " + apply());}
        String apply();
    }

    @FunctionalInterface
    private interface C2 extends Base {
        default void  call() { System.out.println("c2 : " + apply("abc"));}
        String apply(String s);
    }

    public static String apply0() {
        return "1234";
    }

    public static String apply1(String a) {
        return a + "123";
    }

    public static void doTheThing(Base b){
        b.call();
    }

    public static void doTheThing(C1 c){
        doTheThing((Base)c);
    }
    public static void doTheThing(C2 c){
        doTheThing((Base)c);
    }
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        doTheThing(App::apply0);
        doTheThing(App::apply1);
    }
}
