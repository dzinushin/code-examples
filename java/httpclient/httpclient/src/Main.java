public class Main {
    public static void main(String[] args) throws Exception {
        String url = args[0];
        String s = HttpGetter.get(url);
        System.out.println(s);
        System.out.println(new HttpGetterNew().get(url));
    }
}
