package io.datastructure.list;

/**
 * @author by ruisu
 * @package - io.datastructure.list
 * @name - ConvertBTreeToDLList
 * @created on 17/10/2021
 * https://www.techiedelight.com/place-convert-given-binary-tree-to-doubly-linked-list/
 *
 */
class Node
{
    int data;
    Node left = null, right = null;

    Node(int data) {
        this.data = data;
    }
}

public class ConvertBTreeToDLList {
    // Helper function to print a given doubly linked list
    public static void printDLL(Node head)
    {
        Node curr = head;
        while (curr != null)
        {
            System.out.print(curr.data + " ");
            curr = curr.right;
        }
    }

    // Function to in-place convert a given binary tree into a doubly linked list
    // by doing reverse inorder traversal
    public static Node convert(Node root, Node head)
    {
        // base case: tree is empty
        if (root == null) {
            return head;
        }

        // recursively convert the right subtree first
        head = convert(root.right, head);

        // insert the current node at the beginning of a doubly linked list
        root.right = head;

        if (head != null) {
            head.left = root;
        }

        head = root;

        // recursively convert the left subtree
        return convert(root.left, head);
    }

    // In-place convert a given binary tree into a doubly linked list
    public static Node convert(Node root)
    {
        // head of the doubly linked list
        Node head = null;

        // convert the above binary tree into doubly linked list
        return convert(root, head);
    }
    // Function to reverse a doubly-linked list
    public static Node reverse(Node head)
    {
        Node prev = null;
        Node current = head;

        while (current != null)
        {
            // swap current.left with current.right
            Node temp = current.left;
            current.left = current.right;
            current.right = temp;

            prev = current;
            current = current.left;
        }

        return prev;
    }
    public static void main(String[] args)
    {
        /* Construct the following tree
                  1
                /   \
               /     \
              2       3
             / \     / \
            4   5   6   7
        */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        root = convert(root);

        // print the list
        printDLL(root);
    }
}
