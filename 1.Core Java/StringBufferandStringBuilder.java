public class StringBufferandStringBuilder {
    public static void main(String[] args) {
        StringBuffer sb = new StringBuffer("Yashu");

        System.out.println(sb.length());
        System.out.println(sb.capacity());

        sb.append(" Youwaraj");
        System.out.println(sb);

        String str = sb.toString();
        System.out.println(str);

        sb.deleteCharAt(2);
        System.out.println(sb);

        sb.insert(0, "Java ");
        System.out.println(sb);

        sb.insert(5, "Hello ");
        System.out.println(sb);

        sb.setLength(30);
        System.out.println(sb);
        
        sb.ensureCapacity(100);
        System.out.println(sb);
    }
}
