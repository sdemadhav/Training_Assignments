import java.util.ArrayList;
import java.util.List;

class TreeNode {
    String name;
    List<TreeNode> children;

    public TreeNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public void display(String indent) {
        System.out.println(indent + name);
        for (TreeNode child : children) {
            child.display(indent + "  ");
        }
    }
}

public class TreeExample {
    public static void main(String[] args) {
        TreeNode ceo = new TreeNode("CEO");
        TreeNode managerSales = new TreeNode("Manager of Sales Dept");
        TreeNode managerMarketing = new TreeNode("Manager of Marketing Dept");

        TreeNode salesEmp1 = new TreeNode("Sales Employee 1");
        TreeNode salesEmp2 = new TreeNode("Sales Employee 2");
        TreeNode marketingEmp1 = new TreeNode("Marketing Employee 1");
        TreeNode marketingEmp2 = new TreeNode("Marketing Employee 2");
        ceo.addChild(managerSales);
        ceo.addChild(managerMarketing);

        managerSales.addChild(salesEmp1);
        managerSales.addChild(salesEmp2);
        
        managerMarketing.addChild(marketingEmp1);
        managerMarketing.addChild(marketingEmp2);

        ceo.display("");
    }
}
