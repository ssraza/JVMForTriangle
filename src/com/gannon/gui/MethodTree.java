package com.gannon.gui;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;
import com.gannon.bytecode.controlflowgraph.CFGMethod;
import com.gannon.bytecode.controlflowgraph.CGraph;
import com.gannon.bytecode.controlflowgraph.CPath;
import com.gannon.jvm.utilities.ConstantsUtility;

public class MethodTree extends JScrollPane implements TreeSelectionListener {
	protected DefaultMutableTreeNode rootNode;
	protected DefaultTreeModel treeModel;
	protected JTree tree;
	private String rootName;
	private Main mainFrame;

	public MethodTree() {
		super();
	}

	public MethodTree(Main mainFrame, String rootName) {
		this.mainFrame = mainFrame;
		this.rootName = rootName;
		rootNode = new DefaultMutableTreeNode(rootName);
		treeModel = new DefaultTreeModel(rootNode);
		treeModel.addTreeModelListener(new MethodTreeModelListener());
		tree = new JTree(treeModel);
		tree.setEditable(false);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setShowsRootHandles(true);
		tree.addTreeSelectionListener(this);

		add(tree);
		setViewportView(tree);
	}

	/** Remove all nodes except the root node. */
	public void clear() {
		rootNode.removeAllChildren();
		treeModel.reload();
	}

	/** Remove the currently selected node. */
	public void removeCurrentNode() {
		TreePath currentSelection = tree.getSelectionPath();
		if (currentSelection != null) {
			DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection.getLastPathComponent());
			MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
			if (parent != null) {
				treeModel.removeNodeFromParent(currentNode);
				return;
			}
		}
	}

	/** Add child to the currently selected node. */
	public DefaultMutableTreeNode addObject(Object child) {
		DefaultMutableTreeNode parentNode = null;
		TreePath parentPath = tree.getSelectionPath();

		if (parentPath == null) {
			parentNode = rootNode;
		} else {
			parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
		}

		return addObject(parentNode, child, true);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child) {
		return addObject(parent, child, false);
	}

	public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child, boolean shouldBeVisible) {
		DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

		if (parent == null) {
			parent = rootNode;
		}

		// It is key to invoke this on the TreeModel, and NOT
		// DefaultMutableTreeNode
		treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

		// Make sure the user can see the lovely new node.
		if (shouldBeVisible) {
			tree.scrollPathToVisible(new TreePath(childNode.getPath()));
		}
		return childNode;
	}

	class MethodTreeModelListener implements TreeModelListener {
		@Override
		public void treeNodesChanged(TreeModelEvent e) {
			DefaultMutableTreeNode node;
			node = (DefaultMutableTreeNode) (e.getTreePath().getLastPathComponent());

			/*
			 * If the event lists children, then the changed node is the child
			 * of the node we've already gotten. Otherwise, the changed node and
			 * the specified node are the same.
			 */

			int index = e.getChildIndices()[0];
			node = (DefaultMutableTreeNode) (node.getChildAt(index));

			System.out.println("The user has finished editing the node.");
			System.out.println("New value: " + node.getUserObject());
		}

		@Override
		public void treeNodesInserted(TreeModelEvent e) {
		}

		@Override
		public void treeNodesRemoved(TreeModelEvent e) {
		}

		@Override
		public void treeStructureChanged(TreeModelEvent e) {
		}
	}

	public String getClassName() {
		return rootName;
	}

	public void setClassName(String className) {
		this.rootName = className;
	}

	@Override
	public void valueChanged(TreeSelectionEvent arg0) {
		BClass myclass = BClassGenerator.getBClass(rootName);
		if (myclass != null) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (tree.getSelectionPath().getPathCount() == ConstantsUtility.METHOD_LEVEL) {
				BMethod selectedMethod = myclass.getMethod(node.toString());
				mainFrame.txtrInstructionarea.setFont(new Font("CourierNew", Font.PLAIN, 12));
				mainFrame.txtrInstructionarea.setText("");
				mainFrame.txtrInstructionarea.append(selectedMethod.toString());

				// set CFG
				CFGMethod m = new CFGMethod(selectedMethod);
				CGraph buildGraph = m.buildGraph();
				//need to change this
				buildGraph.processDominatorNodes();
				mainFrame.scrollPaneCFG.setViewportView(new CFGPanel(buildGraph, 200, 500));
			} else if (tree.getSelectionPath().getPathCount() == ConstantsUtility.TESTPATH_LEVEL) {
				// get current method
				DefaultMutableTreeNode methodNode = (DefaultMutableTreeNode) tree.getSelectionPath().getParentPath()
						.getLastPathComponent();
				BMethod method = myclass.getMethod(methodNode.toString());
				CFGMethod cfgMethod = new CFGMethod(method);

				// get pathId from user click
				int selectedPathID = Integer.parseInt(node.toString());

				// find path with the pathID from generated CFG
				CPath selectedPath=cfgMethod.buildGraph().computeAllCPaths().findPath(selectedPathID);
				
				//covert to the path to a graph so it can be displayed
				CGraph pathGraph=selectedPath.convertToGraph();

				mainFrame.txtrConsole.append("\nClick path: " + selectedPathID + " in method " + methodNode.toString());
				mainFrame.txtrConsole.append("\n"+selectedPath);
				pathGraph.processDominatorNodes();
				mainFrame.scrollPanePath.setViewportView(new CFGPanel(pathGraph, 200, 500));
			}
		}

	}
}
