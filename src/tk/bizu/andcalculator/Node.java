package tk.bizu.andcalculator;

public abstract class Node implements Token
{
    protected Node leftChild;
    protected Node rightChild;

    protected Node(Node leftChild, Node rightChild)
    {
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }
    
    protected Node()
    {
        this(null, null);
    }
    
    public abstract double calculate();
    
    public void setLeftChild(Node leftChild)
    {
        this.leftChild = leftChild;
    }
    
    public void setRightChild(Node rightChild)
    {
        this.rightChild = rightChild;
    }
    
    protected void visualize(String[] rows, int level) {
        if (rows[level] == null)
            rows[level] = "";
        if (rows[level+1] == null)
            rows[level+1] = "";
        rows[level] += String.format("%" + 80 / ((1 << level) + 1) + "s",
            "[" + this.getStringValue() + "]");
        if (leftChild != null)
            leftChild.visualize(rows, level + 1);
        else
            rows[level + 1] += String.format("%" + 80 / ((1 << (level + 1)) + 1) + "s", "    ");
        if (rightChild != null)
            rightChild.visualize(rows, level + 1);
        else
            rows[level + 1] += String.format("%" + 80 / ((1 << (level + 1)) + 1) + "s", "    ");
    }
    
    public void visualize() {
        String[] rows = new String[10];
        visualize(rows, 0);
        for (String s : rows)
            if (s != null)
                System.out.println(s);
    }
}