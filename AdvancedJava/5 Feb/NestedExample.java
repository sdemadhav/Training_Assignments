public class NestedExample {
    public static void main(String[] args) {
        String[][] nestedList = {
            {"CEO", "  Sales Manager", "    SalesEmp1", "    SalesEmp2", "  Marketing Manager", "    MarketingEmp1", "    MarketingEmp2"}
        };

        for (String[] level1 : nestedList) {
            for (String level2 : level1) {
                System.out.println(level2);
            }
        }
    }
}
