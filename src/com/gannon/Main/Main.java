/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gannon.Main;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.gannon.Graph.GraphGenerator.GraphGeneratorNew;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BLocalVariable;
import com.gannon.asm.components.BMethod;
import com.gannon.asm.components.BStackMaxLocals;

/**
 * 
 * @author Nicholas
 */
public class Main extends javax.swing.JFrame {

	/**
	 * Creates new form JVM_HMI
	 */
	Vector<String> methodName = new Vector<String>();
	//public static ByteCodeClassGenerator cParser = new ByteCodeClassGenerator();
	BClass bClass = new BClass();

	Vector<String> classInfo = new Vector<String>();
	Vector<String> methodInstructions = new Vector<String>();
	Vector<String> methodLocals = new Vector<String>();
	Vector<Integer> overridesList = new Vector<Integer>();
	DefaultTreeModel treeModel = new DefaultTreeModel(null);
	DefaultMutableTreeNode ROOT = new DefaultMutableTreeNode("Class Listing");
	Vector<String> locals = new Vector<String>();
	static String returnType = null;
	Integer displayValue = null;

	InterfaceAPISingleton interfaceAPISingleTon = InterfaceAPISingleton
			.getInstance();
	InterfaceDisplay guiDisplay = new InterfaceDisplay();

	public static void setReturnType(String returnType) {
		Main.returnType = returnType;
	}

	public Main() {
		initComponents();
		populateTree();

	}

	public void populateTree() {
		/* POPULATE TREE */
		classMethodTree.removeAll();
		treeModel.setRoot(ROOT);
		classMethodTree.setModel(treeModel);
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jSplitPane1 = new javax.swing.JSplitPane();
		jSplitPane2 = new javax.swing.JSplitPane();
		jSplitPane3 = new javax.swing.JSplitPane();
		jSplitPane4 = new javax.swing.JSplitPane();
		jScrollPane1 = new javax.swing.JScrollPane();
		localVariableList = new javax.swing.JList();
		jScrollPane4 = new javax.swing.JScrollPane();
		stackList = new javax.swing.JList();
		jScrollPane5 = new javax.swing.JScrollPane();
		opCodeList = new javax.swing.JList();
		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		overrideValueTextField = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		returnValueTextField = new javax.swing.JTextField();
		applyButton = new javax.swing.JButton();
		jScrollPane2 = new javax.swing.JScrollPane();
		jScrollPane6 = new javax.swing.JScrollPane();
		classMethodTree = new javax.swing.JTree();
		menuBar = new javax.swing.JMenuBar();
		fileMenu = new javax.swing.JMenu();
		openClassFile = new javax.swing.JMenuItem();
		editMenu = new javax.swing.JMenu();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Java Virtual Machine : Xu Research Group");
		setBounds(new java.awt.Rectangle(0, 22, 800, 600));
		setMinimumSize(new java.awt.Dimension(1000, 600));

		jSplitPane1.setDividerSize(3);
		jSplitPane1.setResizeWeight(0.4);
		jSplitPane1.setMinimumSize(new java.awt.Dimension(400, 286));
		jSplitPane1.setPreferredSize(new java.awt.Dimension(400, 442));

		jSplitPane2.setDividerSize(3);
		jSplitPane2.setResizeWeight(0.6);
		jSplitPane2.setMinimumSize(new java.awt.Dimension(280, 230));

		jSplitPane3.setDividerSize(3);
		jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
		jSplitPane3.setResizeWeight(0.5);

		jSplitPane4.setDividerSize(3);
		jSplitPane4.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
		jSplitPane4.setResizeWeight(0.5);

		localVariableList.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "local 1", "local 2", "local 3" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane1.setViewportView(localVariableList);

		jSplitPane4.setTopComponent(jScrollPane1);

		stackList.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "Stack 1", "Stack 2", "Stack 3", "Stack 4" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane4.setViewportView(stackList);

		jSplitPane4.setRightComponent(jScrollPane4);

		jSplitPane3.setBottomComponent(jSplitPane4);

		opCodeList.setModel(new javax.swing.AbstractListModel() {
			String[] strings = { "Opcode 0 1", "Opcode 0 2", "Opcode 0 3" };

			public int getSize() {
				return strings.length;
			}

			public Object getElementAt(int i) {
				return strings[i];
			}
		});
		jScrollPane5.setViewportView(opCodeList);

		jSplitPane3.setLeftComponent(jScrollPane5);

		jSplitPane2.setLeftComponent(jSplitPane3);

		jPanel1.setPreferredSize(new java.awt.Dimension(90, 278));
		jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

		jLabel1.setText("Local Variable Override");
		jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(
				50, 110, -1, -1));

		overrideValueTextField.setText("Override Value");
		overrideValueTextField
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						overrideValueTextFieldActionPerformed(evt);
					}
				});
		jPanel1.add(overrideValueTextField,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, 120,
						-1));

		jLabel2.setText("Method Return Value");
		jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(
				50, 220, 140, -1));

		returnValueTextField.setFocusable(false);
		jPanel1.add(returnValueTextField,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, 120,
						-1));

		applyButton.setText("Apply");
		applyButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				applyButtonActionPerformed(evt);
			}
		});
		jPanel1.add(applyButton,
				new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1,
						-1));

		jSplitPane2.setRightComponent(jPanel1);

		jSplitPane1.setRightComponent(jSplitPane2);
		jSplitPane1.setLeftComponent(jScrollPane2);

		classMethodTree.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				classMethodTreeMousePressed(evt);
			}
		});
		classMethodTree
				.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
					public void valueChanged(
							javax.swing.event.TreeSelectionEvent evt) {
						classMethodTreeValueChanged(evt);
					}
				});
		jScrollPane6.setViewportView(classMethodTree);

		jSplitPane1.setLeftComponent(jScrollPane6);

		getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

		fileMenu.setText("File");

		openClassFile.setText("Open Class File");
		openClassFile.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				openClassFileActionPerformed(evt);
			}
		});
		fileMenu.add(openClassFile);

		menuBar.add(fileMenu);

		editMenu.setText("Edit");
		menuBar.add(editMenu);

		setJMenuBar(menuBar);

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void openClassFileActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_openClassFileActionPerformed

		// Open File Browser to get path and filename of .Class file
		final JFileChooser fc = new JFileChooser();
		fc.showOpenDialog(fc);
		String pathName = fc.getSelectedFile().getPath();
		String fileName = fc.getSelectedFile().getName();

		guiDisplay.readFile(fileName);

		// cParser.cparser(fileName);
		// bClass = cParser.getbFactory().getBClass();
		classInfo.add("Class Name: " + bClass.getClassName());
		classInfo.add("Source File: " + bClass.getSourceFile());
		classInfo.add("Super ClassName: " + bClass.getSuperClass());
		classInfo.add("Class Version: " + bClass.getVersion());
		classInfo.add("Class Flag: " + bClass.getClassAccess());

		// create parent name
		DefaultMutableTreeNode newRoot = new DefaultMutableTreeNode(
				InterfaceAPISingleton.getInstance().getbClass().getClassName());
		// get methods from class
		ArrayList<BMethod> methods = InterfaceAPISingleton.getInstance()
				.getbClass().getMethods();
		System.out.println(methods.size());
		// add methods as children to parent class
		for (BMethod aMethod : methods) {
			DefaultMutableTreeNode methodAsChild = new DefaultMutableTreeNode(
					aMethod.getName());
			newRoot.add(methodAsChild);
			System.out.println(aMethod.getName());
		}

		treeModel.insertNodeInto(newRoot, ROOT, 0);
		classMethodTree.expandRow(0);
		classMethodTree.setModel(treeModel);

	}// GEN-LAST:event_openClassFileActionPerformed

	private void classMethodTreeMousePressed(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_classMethodTreeMousePressed

		if (evt.getButton() == 3) {
			classMethodTree.setSelectionRow(classMethodTree
					.getClosestRowForLocation(evt.getX(), evt.getY()));
			JPopupMenu menu = new JPopupMenu();
			JMenuItem item1 = new JMenuItem("Execute All");
			item1.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent me) {
				}

				@Override
				@SuppressWarnings("empty-statement")
				public void mousePressed(MouseEvent me) {
					System.out.println("Execute All Selected for "
							+ classMethodTree.getLastSelectedPathComponent()
							+ "!");
					System.out.println(overridesList);
					
					//set the selected method name
					interfaceAPISingleTon.setSelectedMethod(classMethodTree
							.getLastSelectedPathComponent().toString());
					// interfaceAPISingleTon.setbClass(bClass);
					// set the list of passed arguments for the selected method.
					interfaceAPISingleTon.setOverridesList(overridesList);

					GUIEventHandler handleClick = new GUIEventHandler();
					handleClick.ExecuteAllSelected();

					displayValue = InterfaceAPISingleton.getInstance()
							.getDisplayValue();
					if (returnType == "void") {
						returnValueTextField.setText("Void Function");
					} else {
						returnValueTextField.setText(displayValue.toString());
					}

				}

				@Override
				public void mouseReleased(MouseEvent me) {
				}

				@Override
				public void mouseEntered(MouseEvent me) {
				}

				@Override
				public void mouseExited(MouseEvent me) {
				}
			});
			JMenuItem item2 = new JMenuItem("Step Through");
			item2.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent me) {
				}

				@Override
				public void mousePressed(MouseEvent me) {

					System.out.println("Step Through Selected "
							+ classMethodTree.getLastSelectedPathComponent()
							+ "!");
					// Modify to run STEP THROUGH command
				}

				@Override
				public void mouseReleased(MouseEvent me) {
				}

				@Override
				public void mouseEntered(MouseEvent me) {
				}

				@Override
				public void mouseExited(MouseEvent me) {
				}
			});

			JMenuItem item3 = new JMenuItem("Generate Graph");
			item3.addMouseListener(new MouseListener() {
				@Override
				public void mouseClicked(MouseEvent me) {
				}

				@Override
				@SuppressWarnings("empty-statement")
				public void mousePressed(MouseEvent me) {
					//
					System.out.println(classMethodTree
							.getLastSelectedPathComponent().toString());
					interfaceAPISingleTon.setSelectedMethod(classMethodTree
							.getLastSelectedPathComponent().toString());
					GraphGeneratorNew graphGenerator = new GraphGeneratorNew();
					System.out.println(InterfaceAPISingleton.getInstance()
							.getbClass().getClassName());
					graphGenerator.generateGraph();

				}

				@Override
				public void mouseReleased(MouseEvent me) {
				}

				@Override
				public void mouseEntered(MouseEvent me) {
				}

				@Override
				public void mouseExited(MouseEvent me) {
				}
			});

			menu.add(item1);
			menu.add(item2);
			menu.add(item3);
			menu.show(evt.getComponent(), evt.getX(), evt.getY());

		}
	}// GEN-LAST:event_classMethodTreeMousePressed

	private void classMethodTreeValueChanged(
			javax.swing.event.TreeSelectionEvent evt) {
		{// GEN-FIRST:event_classMethodTreeValueChanged
			/* Update OpCodes */
			ArrayList<BMethod> methods = InterfaceAPISingleton.getInstance()
					.getbClass().getMethods();
			opCodeList.removeAll();
			Vector<String> OpCodes = new Vector<String>();
			BMethod method = methods
					.get(classMethodTree.getSelectionRows()[0] - 2);
			ArrayList<String> displayInstructions = new ArrayList<String>();
			
		//	opCodeList.setListData(method.getDisplayByteCodes());
			opCodeList.setListData(method.getBytecodesForDisplay());
			
			
			jScrollPane5.setBackground(Color.white);
			opCodeList.setBackground(Color.white);

			/* Update Local Variables */
			localVariableList.removeAll();
			locals.clear();

			ArrayList<BLocalVariable> localVariables = methods.get(
					classMethodTree.getSelectionRows()[0] - 2)
					.getLocalVariables();
			for (BLocalVariable aLocal : localVariables) {
				locals.add(aLocal.getIndex() + "   " + aLocal.getDesc() + "   "
						+ aLocal.getName());
			}
			// }
			localVariableList.setListData(locals);
			jScrollPane1.setBackground(Color.white);
			localVariableList.setBackground(Color.white);

			/* Update Stack */
			stackList.removeAll();
			Vector<String> stackItems = new Vector<String>();
			Vector<BStackMaxLocals> stack = new Vector<BStackMaxLocals>();
			ArrayList<BStackMaxLocals> stackDetails = methods.get(
					classMethodTree.getSelectionRows()[0] - 2)
					.getStackDetails();
			for (BStackMaxLocals stck : stack) {
				stackItems.add(stck.toString());
			}

			jScrollPane1.setBackground(Color.white);
			stackList.setBackground(Color.white);

			overridesList.removeAllElements();// erase override values

			int temp = 0;
			for (temp = 0; temp < localVariableList.getLastVisibleIndex() + 1; temp++) {
				overridesList.add(0);
			}
		}
	}// GEN-LAST:event_classMethodTreeValueChanged

	private void overrideValueTextFieldActionPerformed(
			java.awt.event.ActionEvent evt) {// GEN-FIRST:event_overrideValueTextFieldActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_overrideValueTextFieldActionPerformed

	private void applyButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_applyButtonActionPerformed
		int selectedIndex = localVariableList.getSelectedIndex();
		overridesList.setElementAt(
				Integer.parseInt(overrideValueTextField.getText()),
				selectedIndex);
		// Value is being applied to the local variable, so reflect the applied
		// value in the list.
		ArrayList<BLocalVariable> localVariables = InterfaceAPISingleton
				.getInstance().getbClass().getMethods()
				.get(classMethodTree.getSelectionRows()[0] - 2)
				.getLocalVariables();
		BLocalVariable localVariable = localVariables.get(selectedIndex);
		locals.setElementAt(
				localVariable.getIndex() + "   " + localVariable.getDesc()
						+ "   " + localVariable.getName() + " = "
						+ overrideValueTextField.getText(), selectedIndex);
		localVariableList.setListData(locals);
		jScrollPane1.setBackground(Color.white);
		localVariableList.setBackground(Color.white);
		localVariableList.setSelectedIndex(selectedIndex);// reselect the index
															// after repaint

	}// GEN-LAST:event_applyButtonActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Main.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Main().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton applyButton;
	private static javax.swing.JTree classMethodTree;
	private javax.swing.JMenu editMenu;
	private javax.swing.JMenu fileMenu;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane4;
	private javax.swing.JScrollPane jScrollPane5;
	private javax.swing.JScrollPane jScrollPane6;
	private javax.swing.JSplitPane jSplitPane1;
	private javax.swing.JSplitPane jSplitPane2;
	private javax.swing.JSplitPane jSplitPane3;
	private javax.swing.JSplitPane jSplitPane4;
	private javax.swing.JList localVariableList;
	private javax.swing.JMenuBar menuBar;
	private javax.swing.JList opCodeList;
	private javax.swing.JMenuItem openClassFile;
	private javax.swing.JTextField overrideValueTextField;
	private javax.swing.JTextField returnValueTextField;
	private javax.swing.JList stackList;
	// End of variables declaration//GEN-END:variables
}
