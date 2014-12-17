package util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RBTree<V extends Comparable<V>> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private int size = 0;
    private Node<V> root = null;
    private ArrayList<V> traversingList = new ArrayList<V>();

    private static class Node<V> {
        int key;
        V value;
        boolean color;

        Node<V> left, right, parent;

        public Node(V value) {
            this.value = value;
        }
    }

    public RBTree() {
    }

    public void add(V v) {
        Node<V> node = root, parent = null;
        while (null != node) {
            int compare = v.compareTo(node.value);
            if (compare == 0) {
                node.value = v;
                return;
            } else {
                parent = node;
                if (compare < 0) {
                    node = node.left;
                } else {
                    node = node.right;
                }
            }
        }

        Node<V> newNode = new Node<V>(v);
        newNode.key = size;
        newNode.color = RED;
        newNode.parent = parent;

        if (null == parent) {
            root = newNode;
        } else {
            if (newNode.value.compareTo(parent.value) < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }

        ++size;
        fixupAdd(newNode);
    }

    private void fixupAdd(Node<V> node) {
        addCaseOne(node);
    }

    private void addCaseOne(Node<V> node) {
        if (node.parent == null) {
            node.color = BLACK;
        } else {
            addCaseTwo(node);
        }
    }

    private void addCaseTwo(Node<V> node) {
        if (node.parent.color == BLACK) {
            return;
        } else {
            addCaseThree(node);
        }
    }

    private void addCaseThree(Node<V> node) {
        Node<V> uncle = getUncle(node), grandparent;

        if ((null != uncle) && (uncle.color == RED)
                && (node.parent.color == RED)) {
            node.parent.color = BLACK;
            uncle.color = BLACK;
            grandparent = getGrandparent(node);
            grandparent.color = RED;
            addCaseOne(grandparent);
        } else {
            addCaseFour(node);
        }
    }

    private void addCaseFour(Node<V> node) {
        Node<V> grandparent = getGrandparent(node);
        if ((node == node.parent.right) && (node.parent == grandparent.left)) {
			/*
             * struct node *saved_p=g->left, *saved_left_n=n->left; g->left=n;
			 * n->left=saved_p; saved_p->right=saved_left_n;
			 */
            Node<V> saved_p = grandparent.left, saved_left_n = node.left;
            grandparent.left = node;
            grandparent.left.parent = grandparent;
            node.left = saved_p;
            node.left.parent = node;
            saved_p.right = saved_left_n;
            if (saved_p.right != null)
                saved_p.right.parent = saved_p;
            node = node.left;
        } else if ((node == node.parent.left)
                && (node.parent == grandparent.right)) {
			/*
             * struct node *saved_p=g->right, *saved_right_n=n->right;
			 * g->right=n; n->right=saved_p; saved_p->left=saved_right_n;
			 */
            Node<V> saved_p = grandparent.right, saved_right_n = node.right;
            grandparent.right = node;
            grandparent.right.parent = grandparent;
            node.right = saved_p;
            node.right.parent = node;
            saved_p.left = saved_right_n;
            if (saved_p.left != null)
                saved_p.left.parent = saved_p;
            node = node.right;
        }
        addCaseFive(node);
    }

    private void addCaseFive(Node<V> node) {
        Node<V> grandparent = getGrandparent(node);

        if ((node == node.parent.left) && (node.parent == grandparent.left)) {
            if (grandparent == root) {
                root = grandparent.left;
                if (root.right != null) {
                    root.parent.left = root.right;
                    root.parent.left.parent = root.parent;
                    root.right = root.parent;
                    root.parent = null;
                    root.right.parent = root;
                } else {
                    root.right = root.parent;
                    root.parent = null;
                    root.right.parent = root;
                    root.right.left = null;
                }
            } else {
                leftRotate(node, grandparent);
            }
        } else {
            if (grandparent == root) {
                root = grandparent.right;
                if (root.left != null) {
                    root.parent.right = root.left;
                    root.parent.right.parent = root.parent;
                    root.left = root.parent;
                    root.parent = null;
                    root.left.parent = root;
                } else {
                    root.left = root.parent;
                    root.parent = null;
                    root.left.parent = root;
                    root.left.right = null;
                }
            } else {
                rightRotate(node, grandparent);
            }
        }

        node.parent.color = BLACK;
        grandparent.color = RED;
    }

    private Node<V> rightRotate(Node<V> root, Node<V> node) {
        Node<V> right = node.right;

        node.right = right.left;
        if (null != right.left) {
            right.left.parent = node;
        }
        if (null != right) {
            right.parent = node.parent;
        }

        if (null != node.parent) {
            if (node == node.parent.left) {
                node.parent.left = right;
            } else {
                node.parent.right = right;
            }
        } else {
            root = right;
        }

        right.left = node;
        if (null != node) {
            node.parent = right;
        }
        return root;
    }

    private Node<V> leftRotate(Node<V> root, Node<V> node) {
        Node<V> left = node.left;
        node.left = left.right;
        if (null != left.right) {
            left.right.parent = node;
        }
        if (null != left) {
            left.parent = node.parent;
        }
        if (null != node.parent) {
            if (node == node.parent.right) {
                node.parent.right = left;
            } else {
                node.parent.left = left;
            }
        } else {
            root = left;
        }
        left.right = node;
        if (null != node) {
            node.parent = left;
        }
        return root;
    }

    private Node<V> getGrandparent(Node<V> node) {
        if ((null != node) && (null != node.parent)) {
            return node.parent.parent;
        } else {
            return null;
        }
    }

    private Node<V> getUncle(Node<V> node) {
        if (null == getGrandparent(node)) {
            return null; // No grandparent means no uncle
        }
        if (node.parent == getGrandparent(node).left) {
            return getGrandparent(node).right;
        } else {
            return getGrandparent(node).left;
        }
    }

    public V get(int key) {
        return getNode(key).value;
    }

    private Node<V> getNode(int key) {
        if (root.key == key)
            return root;
        else {
            Queue<Node<V>> queue = new LinkedList<Node<V>>();
            if (null != root.left)
                queue.add(root.left);
            if (null != root.right)
                queue.add(root.right);

            while (!queue.isEmpty()) {
                Node<V> node = queue.poll();
                if (node.key == key) {
                    queue = null;
                    return node;
                } else {
                    if (null != node.left)
                        queue.add(node.left);
                    if (null != node.right)
                        queue.add(node.right);
                }
            }
        }

        throw new IndexOutOfBoundsException("No item with index: " + key);
    }

    public int size() {
        return size;
    }

    public ArrayList<V> preOrderTravers() {
        traversingList = new ArrayList<V>();
        preOrderTree(root);
        return traversingList;
    }

    public ArrayList<V> inOrderTravers() {
        traversingList = new ArrayList<V>();
        inOrderTree(root);
        return traversingList;
    }

    public ArrayList<V> postOrderTravers(){
        traversingList = new ArrayList<V>();
        postOrderTree(root);
        return traversingList;
    }

    private void postOrderTree(Node<V> node) {
        if(null != node){
            postOrderTree(node.left);
            postOrderTree(node.right);
            System.out.println(node.value);
            traversingList.add(node.value);
        }
    }

    private void inOrderTree(Node<V> node) {
        if (null != node) {
            inOrderTree(node.left);
            System.out.println(node.value);
            traversingList.add(node.value);
            inOrderTree(node.right);
        }
    }

    private void preOrderTree(Node<V> node) {
        if (null != node) {
            System.out.println(node.value);
            traversingList.add(node.value);
            preOrderTree(node.left);
            preOrderTree(node.right);
        }
    }

    int countCalls;
    public int fullSteck() {
        countCalls = 0;
        fullSteckTree(root);
        return countCalls;
    }

    private void fullSteckTree(Node<V> node) {
        ++countCalls;
        try {
            if (null != node.parent) {
                fullSteckTree(node.parent);
            } else {
                fullSteckTree(node.left);
            }
        } catch (StackOverflowError ex) {
            System.out.println(countCalls);
        }
    }
}
