package edu.institution.asn11;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;


public class BST<E extends Comparable<E>> {

	protected TreeNode<E> root;
	protected int size = 0;
	private List<E> bftList;
	
	public BST() {
		
	}
	
	public BST(E[] objects) {
		for (int i=0; i<objects.length; i++) {
			insert(objects[i]);
		}
	}
	
	/**
	 * Traverses the nodes using the breadth-first traversal algorithm and
	 * returns a list of elements in the correct order.
	 * 
	 * @return the elements in the order that reflects a breadth-first traversal.
	 */
	public List<E> breadthFirstTraversal() {
		this.bftList = new ArrayList<>();
		try {
			int height = getHeight();
			//System.out.println("h = getHeight()");
			for (int level = 0; level <= height; level++) {
				inputNodesBFT(this.root, level);
			}
		} catch (Exception e) {
			System.out.println("error occurred");
		}
		return this.bftList;
	}
	private void inputNodesBFT(TreeNode<E> root, int level) {
		if (root == null)
			return;
		if (level == 0)
			this.bftList.add(root.element);
		else if (level > 0) {
			inputNodesBFT(root.left, level - 1);
			inputNodesBFT(root.right, level - 1);
		}
	}
	
	/*
	 * Returns the number of edges between the tree's root and its furthest leaf.
	 * @return the height.
	 */
	public int getHeight() {
		return getHeight1(this.root) - 1;
	}
	private int getHeight1(TreeNode<E> root) {
		if (root == null) {
			return 0;
		}
		else {
			//height of each subtree
			int leftHeight = getHeight1(root.left);
			int rightHeight = getHeight1(root.right);
			
			if (leftHeight > rightHeight)
				return (leftHeight + 1);
			else
				return (rightHeight + 1);
		}
		
	}
	
	
	public List<E> nonRecursiveInOrder(){
		
		List<E> theList = new ArrayList<>();
		
		TreeNode<E> pivot = this.root;
		TreeNode<E> fork = this.root;
		int cap = 0;
		
		while (cap < this.size) {
			//System.out.print("[cap: " +cap+ ", size: " +this.size+ ", pivot: " +pivot.element+ ", pivot.left: " +(pivot.left != null)+ ", pivot.right: " +(pivot.right != null)+ "]	");
			/*if (!theList.isEmpty()) {
				System.out.print("[ ");
				for (E meme : theList) {
					System.out.print(meme+ " ");
				}
				System.out.print("]");
			}*/
			
			//if we reach a node with 2 branches:
			if (pivot.left != null && pivot.right != null) {
				fork = pivot;
				//if the left branch doesn't exist in the stack:
				if (!theList.contains(pivot.left.element)) {
					//System.out.println("help");
					pivot = pivot.left;
				}
				//if the left branch exists in the stack, but not the right:
				else if (theList.contains(pivot.left.element) && !theList.contains(pivot.right.element)) {
					//if the pivot is not in the stack yet:
					if (!theList.contains(pivot.element)) {
						theList.add(pivot.element);
						cap++;
					}
					pivot = pivot.right;
				}
				//if both the left and right branch exist in the stack:
				else if (theList.contains(pivot.left.element) && theList.contains(pivot.right.element)) {
					pivot = this.root;
				}
				
			}
			//if we reach a node with 1 left branch:
			else if (pivot.left != null && pivot.right == null) {
				//if the left branch does not exist in the stack:
				if (!theList.contains(pivot.left.element)) {
					pivot = pivot.left;
				}
				else {
					theList.add(pivot.element);
					cap++;
					pivot = fork;
				}
			}
			//if we reach a node with 1 right branch:
			else if (pivot.left == null && pivot.right != null) {
				theList.add(pivot.element);
				cap++;
				pivot = pivot.right;
			}
			//if we reach a leaf-node:
			else if (pivot.left == null && pivot.right == null){
				theList.add(pivot.element);
				cap++;
				pivot = fork;
			}
			//System.out.print("\n");
		}
		/*System.out.print("[cap: " +cap+ ", size: " +this.size+ ", pivot: " +pivot.element+ ", pivot.left: " +(pivot.left != null)+ ", pivot.right: " +(pivot.right != null)+ "]	");
		if (!theList.isEmpty()) {
			System.out.print("[ ");
			for (E meme : theList) {
				System.out.print(meme+ " ");
			}
			System.out.print("]");
		}
		System.out.print("\n");*/
		
		return theList;
	}
	
	
	
	public boolean search(E e) {
		TreeNode<E> current = root;
		
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean insert(E e) {
		if (root == null) {
			root = createNewNode(e);
		} else {
			TreeNode<E> parent = null;
			TreeNode<E> current = root;
			while (current != null) {
				if (e.compareTo(current.element) < 0) {
					parent = current;
					current = current.left;
				} else if (e.compareTo(current.element) > 0) {
					parent = current;
					current = current.right;
				} else {
					return false;
				}
			}
				
			if (e.compareTo(parent.element) < 0) {
				parent.left = createNewNode(e);
			} else {
				parent.right = createNewNode(e);
			}
		}
		
		size++;
		return true;
	}
	
	protected TreeNode<E> createNewNode(E e) {
		return new TreeNode<E>(e);
	}
	
	public void inorder() {
		inorder(root);
	}
	
	protected void inorder(TreeNode<E> root) {
		if (root == null) return;
		inorder(root.left);
		System.out.print(root.element + " ");
		inorder(root.right);
	}
	
	public void postorder() {
		postorder(root);
	}
	
	protected void postorder(TreeNode<E> root) {
		if (root == null) return;
		postorder(root.left);
		postorder(root.right);
		System.out.print(root.element + " ");
	}
	
	public void preorder() {
		preorder(root);
	}
	
	protected void preorder(TreeNode<E> root) {
		if (root == null) return;
		System.out.print(root.element + " ");
		preorder(root.left);
		preorder(root.right);
	}
	
	public int getSize() {
		return size;
	}
	
	public TreeNode<E> getRoot() {
		return root;
	}
	
	public ArrayList<TreeNode<E>> path(E e) {
		ArrayList<TreeNode<E>> list = new ArrayList<>();
		TreeNode<E> current = root;
		
		while (current != null) {
			list.add(current);
			if (e.compareTo(current.element) < 0) {
				current = current.left;
			} else if (e.compareTo(current.element) > 0) {
				current = current.right;
			} else {
				break;
			}
		}
		
		return list;
	}
	
	public boolean delete(E e) {
		TreeNode<E> parent = null;
		TreeNode<E> current = root;
		
		while (current != null) {
			if (e.compareTo(current.element) < 0) {
				parent = current;
				current = current.left;
			} else if (e.compareTo(current.element)> 0) {
				parent = current;
				current = current.right;
			} else {
				break;
			}
		}
		
		if (current == null) {
			return false;
		}
		
		if (current.left == null) {
			if (parent == null) {
				root = current.right;
			} else {
				if (e.compareTo(parent.element) < 0) {
					parent.left = current.right;
				} else {
					parent.right = current.right;
				}
			}
		} else {
			TreeNode<E> parentOfRightMost = current;
			TreeNode<E> rightMost = current.left;
			
			while (rightMost.right != null) {
				parentOfRightMost = rightMost;
				rightMost = rightMost.right;
			}
			
			current.element = rightMost.element;
			
			if (parentOfRightMost.right == rightMost) {
				parentOfRightMost.right = rightMost.left;
			} else {
				parentOfRightMost.left = rightMost.left;
			}
		}
		
		size--;
		return true;
	}
	
	public Iterator<E> iterator() {
		return new InorderIterator();
	}
	
	public void clear() {
		root = null;
		size = 0;
	}
	
	
	private class InorderIterator implements Iterator<E> {
		
		private ArrayList<E> list = new ArrayList<>();
		private int current = 0;
		
		public InorderIterator() {
			inorder();
		}
		
		private void inorder() {
			inorder(root);
		}
		
		private void inorder(TreeNode<E> root) {
			if (root == null) return;
			inorder(root.left);
			list.add(root.element);
			inorder(root.right);
		}
		
		public boolean hasNext() {
			return current < list.size();
		}
		
		public E next() {
			return list.get(current++);
		}
		
		public void remove() {
			delete(list.get(current));
			list.clear();
			inorder();
		}
	}
}
