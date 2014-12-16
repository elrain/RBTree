package util;

import objects.Student;

public class RBTree<V extends Comparable<V>> {

    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private int size = 0;
    private Node<V> root = null;
    private Node<V> nodeForSearch = null;

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
//            int compare = comparator.compare(v, node.value);
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
//            if (newNode.key - parent.key < 0) {
//                parent.left = newNode;
//            } else {
//                parent.right = newNode;
//            }
//            if (comparator.compare(newNode.value, parent.value) < 0) {
            if (newNode.value.compareTo(parent.value) < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }

        ++size;
        // root = fixupAdd(root, newNode);
        fixuppAdd(newNode);
    }

    private void fixuppAdd(Node<V> node) {
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
            // rotateLeft(node.parent, grandparent);

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
            // rotateRight(node.parent, grandparent);

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
            // Node<V> saved_p = grandparent.right, saved_right_n = node.right;
            // grandparent.right = node;
            // // grandparent.right.parent = grandparent;
            // node.right = saved_p;
            // // node.right.parent = node;
            // saved_p.left = saved_right_n;

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
//				grandparent.left.right = grandparent;
//				grandparent.left.parent = grandparent.parent;
//				grandparent.parent = grandparent.left;
//				grandparent.left = null;
//				grandparent.parent.right = grandparent.left;
//				grandparent.parent.right.right = grandparent.parent.right.parent;
//				grandparent.parent.right.parent = grandparent.parent;
//				grandparent.parent.right.right.left = null;
//				grandparent.parent.right.right.parent = grandparent.parent.right;

//				if(node == node.parent.left){
//					rightRotate(node, grandparent);
//				} else{
                leftRotate(node, grandparent);
//				}
            }
        } else {
            // Node<V> saved_p = grandparent.left, saved_left_n = node.left;
            // grandparent = grandparent.right;
            // grandparent.left = grandparent;
            // grandparent.left.parent = grandparent.right;
            // node.left = saved_p;
            // // node.left.parent = node;
            // saved_p.right = saved_left_n;

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
//				grandparent.parent.left = grandparent.right;
//				grandparent.parent.left.left = grandparent.parent.left.parent;
//				grandparent.parent.left.parent = grandparent.parent;
//				grandparent.parent.left.left.right = null;
//				grandparent.parent.left.left.parent = grandparent.parent.left;
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

//    public V get(int key) {
//        return getNodeWithKey(root, key);
//    }

    public int size() {
        return size;
    }

    public void preOrderTravers() {
        preOrderTree(root);
    }

    public void inOrderTravers() {
        inOrderTree(root);
    }

    private void inOrderTree(Node<V> node) {
        if (null != node) {
            inOrderTree(node.left);
            System.out.println(((Student) node.value).getStudentId());
            inOrderTree(node.right);
        }
    }

//    private V getNodeWithKey(Node<V> node, int key) {
//    }

    private void preOrderTree(Node<V> node) {
        if (null != node) {
            System.out.println(node.key);
            preOrderTree(node.left);
            preOrderTree(node.right);
        }
    }

    public void fullSteck() {
        int i = 0;
        fullSteckTree(root, i);
    }

    private void fullSteckTree(Node<V> node, int count) {
        ++count;
        try {
            if (null != node.parent) {
                fullSteckTree(node.parent, count);
            } else {
                fullSteckTree(node.left, count);
            }
        } catch (StackOverflowError ex) {
            System.out.println(count);
        }
    }
}
