package dev.sanket.tree;

import java.util.ArrayList;
import java.util.List;

public class Node
{
    private int value;

    private List<Node> children = new ArrayList<Node>();

    public Node(int value)
    {
        this.value = value;
    }

    public boolean hasChildren()
    {
        return children.isEmpty();
    }

    public int getValue()
    {
        return value;
    }

    public List<Node> getChildren()
    {
        return children;
    }
}
