package csi2110.assignment2;

import net.datastructures.BinaryTree;
import net.datastructures.ArrayListQueue;
import net.datastructures.ArrayStack;
import net.datastructures.LinkedBinaryTree;
import net.datastructures.Position;

/**
 * Short Exercises - Assignment 2
 * 
 *
 */
public class ShortExercises {

	/**
	 * Build a small tree composed of 7 nodes
	 * 
	 * @return the binary tree created
	 */
	public static BinaryTree<String> buildTree() {
		LinkedBinaryTree<String> bTree = new LinkedBinaryTree<String>();

		bTree.addRoot("a");

		Position<String> a = bTree.root();

		Position<String> b = bTree.insertLeft(a, "b");
		Position<String> c = bTree.insertRight(a, "c");

		bTree.insertLeft(b, "d");
		bTree.insertRight(b, "e");

		bTree.insertLeft(c, "f");
		bTree.insertRight(c, "g");

		return bTree;
	}

	/**
	 * Visits a node by printing the String value of its element
	 * 
	 * @param v: node to be visited
	 */
	public static void visit(Position<String> v) {
		System.out.println(v.element());
	}

	/**
	 * Performs the pre-order traversal of a binary tree
	 * 
	 * @param bTree: binary tree to be traversed
	 * @param v:     root node
	 */
	public static void preOrder(BinaryTree<String> bTree, Position<String> v) {

		ArrayStack stack = new ArrayStack(); 
		stack.push(bTree.root());

		while (!stack.isEmpty()) { 
			Object node_now = stack.top();
			visit((Position<String>) node_now);

			stack.pop();

			if (bTree.hasRight((Position<String>) node_now)) {
				stack.push(bTree.right((Position<String>) node_now));
			}
			if (bTree.hasLeft((Position<String>) node_now)) {
				stack.push(bTree.left((Position<String>) node_now));
			}

		}

	}

	/**
	 * Performs the in-order traversal of a binary tree
	 * 
	 * @param bTree: binary tree to be traversed
	 * @param v:     root node
	 */
	public static void inOrder(BinaryTree<String> bTree, Position<String> v) {

		boolean red_flag = false; 
		boolean makeSure = false; 
		ArrayStack stack = new ArrayStack();
		stack.push(bTree.left(v));

		while (!stack.isEmpty()) {
			Object node_now = stack.top();
			visit((Position<String>) node_now);

			stack.pop();

			if (red_flag) {
				visit(bTree.root());
				stack.push(bTree.right(v));
				red_flag = false;
				makeSure = true;
			}
			if (bTree.hasRight((Position<String>) node_now)) {
				stack.push(bTree.right((Position<String>) node_now));
			}
			if (bTree.hasLeft((Position<String>) node_now)) {
				stack.push(bTree.left((Position<String>) node_now));
			} else {
				if (!makeSure)
					red_flag = true;
			}

		}

	}

	/**
	 * Performs the level order traversal of a binary tree
	 * 
	 * @param bTree: binary tree to be traversed
	 * @param v:     root node
	 */
	public static void levelOrder(BinaryTree<String> bTree, Position<String> v) {
		ArrayListQueue stack = new ArrayListQueue();
		stack.enqueue(bTree.root());
		visit(v);
		Object node_now;
		while (!stack.isEmpty()) {
			node_now = stack.dequeue();

			if (bTree.hasLeft((Position<String>) node_now)) {
				stack.enqueue(bTree.left((Position<String>) node_now));
				visit(bTree.left((Position<String>) node_now));

			}
			if (bTree.hasRight((Position<String>) node_now)) {
				stack.enqueue(bTree.right((Position<String>) node_now));
				visit(bTree.right((Position<String>) node_now));

			}
		}

	}

	public static void main(String[] args) {

		BinaryTree<String> bTree = buildTree();

		System.out.println("Pre-Order traversal:");
		preOrder(bTree, bTree.root());

		System.out.println("In-Order traversal:");
		inOrder(bTree, bTree.root());

		System.out.println("Level Order traversal:");
		levelOrder(bTree, bTree.root());

	}
}
