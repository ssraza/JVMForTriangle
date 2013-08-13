package com.gannon.gui;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
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
import com.gannon.jvm.data.input.Input;
import com.gannon.jvm.input.generator.GannonInputGeneratorJVM;
import com.gannon.jvm.progam.path.TestPath;
import com.gannon.jvm.utilities.ConstantsUtility;
import com.gannon.jvm.utilities.Utility;

public class MethodTree extends JScrollPane implements TreeSelectionListener, MouseListener {
	protected DefaultMutableTreeNode rootNode;
	protected DefaultTreeModel treeModel;
	protected JTree tree;
	private String rootName;
	private Main mainFrame;
	private BClass myclass;
	private BMethod selectedMethod;
	private TestPath testPath;

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
		// tree node right click
		tree.addMouseListener(this);

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
		myclass = BClassGenerator.getBClass(rootName);
		if (myclass != null) {
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			if (tree.getSelectionPath().getPathCount() == ConstantsUtility.METHOD_LEVEL) {
				selectedMethod = myclass.getMethod(node.toString());
				mainFrame.txtrInstructionarea.setFont(new Font("CourierNew", Font.PLAIN, 12));
				mainFrame.txtrInstructionarea.setText("");
				mainFrame.txtrInstructionarea.append(selectedMethod.toString());

				// set CFG
				CFGMethod m = new CFGMethod(selectedMethod);
				CGraph buildGraph = m.buildGraph();
				// need to change this
				buildGraph.processDominatorNodes();

				// switch tab
				mainFrame.tabbedPane.setSelectedIndex(1);
				mainFrame.scrollPaneCFG.setViewportView(new CFGPanel(buildGraph, 200, 400));

				// display input table panel
				InputTablePanel inputTablePanel = new InputTablePanel(mainFrame, selectedMethod);
				mainFrame.outputPanel.removeAll();
				mainFrame.outputPanel.add(inputTablePanel);

			} else if (tree.getSelectionPath().getPathCount() == ConstantsUtility.TESTPATH_LEVEL) {
				// get current method
				DefaultMutableTreeNode methodNode = (DefaultMutableTreeNode) tree.getSelectionPath().getParentPath()
						.getLastPathComponent();
				selectedMethod = myclass.getMethod(methodNode.toString());
				CFGMethod cfgMethod = new CFGMethod(selectedMethod);

				// get pathId from user click
				int selectedPathID = Integer.parseInt(node.toString());

				// find path with the pathID from generated CFG
				CPath selectedPath = cfgMethod.buildGraph().computeAllCPaths().findPath(selectedPathID);

				// covert to the path to a graph so it can be displayed
				CGraph pathGraph = selectedPath.convertToGraph();

				// log
				mainFrame.txtrConsole.append("\nClick path: " + selectedPathID + " in method " + methodNode.toString());
				mainFrame.txtrConsole.append("\n" + selectedPath);

				// display instruction
				mainFrame.txtrInstructionarea.setFont(new Font("CourierNew", Font.PLAIN, 12));
				mainFrame.txtrInstructionarea.setText("");
				testPath = new TestPath(selectedPath);
				mainFrame.txtrInstructionarea.append(testPath.toString());

				// display path
				pathGraph.processDominatorNodes();
				mainFrame.tabbedPane.setSelectedIndex(2);
				mainFrame.scrollPanePath.setViewportView(new CFGPanel(pathGraph, 200, 400));

			}
		}

	}

	private ArrayList<Object> hardCodeInput() {
		ArrayList<Object> inputs = new ArrayList<Object>();
		inputs.add(-1);
		inputs.add(111);
		inputs.add(6565);
		inputs.add(667);
		return inputs;
	}

	// ========Implementing MouseListener for right clicking the tree============

	@Override
	public void mouseClicked(MouseEvent e) {
		// right click pop menu
		if (SwingUtilities.isRightMouseButton(e) && testPath != null) {
			TreePath path = tree.getPathForLocation(e.getX(), e.getY());
			Rectangle pathBounds = tree.getUI().getPathBounds(tree, path);
			if (pathBounds != null && pathBounds.contains(e.getX(), e.getY())) {
				JPopupMenu menu = new JPopupMenu();
				JMenuItem inputGenerationMenuItem = new JMenuItem("Generating Inputs");
				inputGenerationMenuItem.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// ==================================================================//
						// Test input generation
						// ==================================================================//
						// we don't need to hard code here.
						Input input = new Input(1, hardCodeInput());

						testPath.setbClass(myclass);
						testPath.setbMethod(selectedMethod);

						GannonInputGeneratorJVM executor = new GannonInputGeneratorJVM();
						int numberOfResultsNeeded = 100;
						Set<Input> results = executor.run(testPath, input, numberOfResultsNeeded);
						GeneratedInputsArea generatedInputsArea = new GeneratedInputsArea(results);
						mainFrame.tabbedPane.setSelectedIndex(3);
						mainFrame.scrollPaneGeneratedInputs.setViewportView(generatedInputsArea);
					}
				});
				inputGenerationMenuItem.setIcon(new ImageIcon(Main.class
						.getResource("/com/gannon/images16x16/data_generation.png")));
				menu.add(inputGenerationMenuItem);
				menu.show(tree, e.getX(), e.getY());
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
}
