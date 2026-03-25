public class MutablevsImmutablestring {
    public static void main(String[] args) {
        String name="Yashu";
        name=name+" Youwaraj";
        System.out.println(name);

        String s1="aman";
        String s2="aman";

        System.out.println(s1==s2); // true because both s1 and s2 are pointing to the same string literal in the string pool
    }
}
