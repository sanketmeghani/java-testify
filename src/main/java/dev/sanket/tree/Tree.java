package dev.sanket.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tree
{
    private Node root;

    public int findDepth(int searchNodeValue)
    {
        List<Node> nodesAtCurrentLevel = Collections.singletonList(root);

        return recursiveSearch(0, searchNodeValue, nodesAtCurrentLevel);
    }

    public int recursiveSearch(int level, int searchNodeValue, List<Node> nodesAtCurrentLevel)
    {
        List<Node> nodesAtNextLevel = new ArrayList<Node>();

        // Check if searchNode matches any node at current level
        for (Node node : nodesAtCurrentLevel)
        {
            // If it matches, we have found the node, return current level
            if (node.getValue() == searchNodeValue)
            {
                return level;
            }

            // Add children of all nodes at current level in nodesAtNextLevel
            if (node.hasChildren())
            {
                nodesAtNextLevel.addAll(node.getChildren());
            }
        }

        // searchNode is not found at current level, increment level and continue search at next level if next level
        // exists in tree
        if (!nodesAtNextLevel.isEmpty())
        {
            return recursiveSearch(level + 1, searchNodeValue, nodesAtNextLevel);
        }

        // We have traversed entire tree. Node not found. Return -1
        return -1;
    }
}
