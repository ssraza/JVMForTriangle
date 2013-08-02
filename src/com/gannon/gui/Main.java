package com.gannon.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.io.FileUtils;

import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.progam.path.TestPath;
import com.gannon.jvm.progam.path.TestPathGenerator;
import com.gannon.jvm.utilities.ConstantsUtility;
import com.gannon.program.cfg.CFGPanel;
import com.tutorial.filechoosen.FileChooserDemo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends JFrame {

	private static final String TestPathGenerator = null;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField txtCfgHere;
	public JTextArea txtrInstructionarea;
	private JTextArea txtrConsole;
	private File file;
	private MethodTree scrollPaneTree;
	protected JSplitPane splitPaneRoot;
	private JFileChooser fc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		initFrame();

		initMenu();

		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JToolBar northToolBar = new JToolBar();
		contentPane.add(northToolBar, BorderLayout.NORTH);

		JButton btnOpen = new JButton("");
		btnOpen.setIcon(new ImageIcon(Main.class.getResource("/com/ganon/images24x24/folder_green.png")));
		northToolBar.add(btnOpen);

		JDesktopPane centerDesktopPane = new JDesktopPane();
		centerDesktopPane.setBorder(null);
		centerDesktopPane.setBackground(Color.WHITE);
		contentPane.add(centerDesktopPane, BorderLayout.CENTER);
		centerDesktopPane.setLayout(new BorderLayout(0, 0));

		splitPaneRoot = new JSplitPane();
		splitPaneRoot.setResizeWeight(0.2);
		centerDesktopPane.add(splitPaneRoot, BorderLayout.CENTER);

		// populated tree will be added here when open a file
		scrollPaneTree = new MethodTree();

		scrollPaneTree.setPreferredSize(new Dimension(100, 23));
		scrollPaneTree.setMinimumSize(new Dimension(100, 23));
		splitPaneRoot.setLeftComponent(scrollPaneTree);

		JLabel lblTestPaths = new JLabel("====Test Paths====");
		lblTestPaths.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPaneTree.setColumnHeaderView(lblTestPaths);

		JSplitPane splitPaneMainAndOutputAndConsole = new JSplitPane();
		splitPaneMainAndOutputAndConsole.setResizeWeight(0.8);
		splitPaneMainAndOutputAndConsole.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPaneRoot.setRightComponent(splitPaneMainAndOutputAndConsole);

		JSplitPane splitPaneMainAndOutput = new JSplitPane();
		splitPaneMainAndOutput.setResizeWeight(0.8);
		splitPaneMainAndOutputAndConsole.setLeftComponent(splitPaneMainAndOutput);

		JScrollPane scrollPaneOutoutWindow = new JScrollPane();
		splitPaneMainAndOutput.setRightComponent(scrollPaneOutoutWindow);

		JPanel panel = new JPanel();
		scrollPaneOutoutWindow.setViewportView(panel);

		JLabel lblInput = new JLabel("input1");
		panel.add(lblInput);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		JButton btnRun = new JButton("Run");
		panel.add(btnRun);

		JButton btnStep = new JButton("Step");
		panel.add(btnStep);

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		splitPaneMainAndOutput.setLeftComponent(tabbedPane);

		JScrollPane scrollPaneInstruction = new JScrollPane();
		tabbedPane.addTab("Instructions", null, scrollPaneInstruction, null);

		txtrInstructionarea = new JTextArea();
		txtrInstructionarea.setText("InstructionArea");
		scrollPaneInstruction.setViewportView(txtrInstructionarea);

		JScrollPane scrollPaneCFG = new JScrollPane();
		tabbedPane.addTab("CFG", null, scrollPaneCFG, null);

		JPanel panelCFG = new JPanel();
		// scrollPaneCFG.setViewportView(panelCFG);

		// creating CFG Panel ( width x height)
		CFGPanel cfgPanel = new CFGPanel(600, 800);
		scrollPaneCFG.setViewportView(cfgPanel);

		// txtCfgHere = new JTextField();
		// txtCfgHere.setText("CFG here2");
		// panelCFG.add(txtCfgHere);
		// txtCfgHere.setColumns(10);

		JScrollPane scrollPaneConsole = new JScrollPane();
		splitPaneMainAndOutputAndConsole.setRightComponent(scrollPaneConsole);

		txtrConsole = new JTextArea();
		txtrConsole.setText("console");
		scrollPaneConsole.setViewportView(txtrConsole);

		JPanel westPanel = new JPanel();
		contentPane.add(westPanel, BorderLayout.WEST);

		JPanel southStatusPanel = new JPanel();
		contentPane.add(southStatusPanel, BorderLayout.SOUTH);

		JPanel eastPanel = new JPanel();
		contentPane.add(eastPanel, BorderLayout.EAST);
	}

	private void initMenu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpenClass = new JMenuItem("Open Class");
		mntmOpenClass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Create a file chooser

				URL location = this.getClass().getProtectionDomain().getCodeSource().getLocation();
				String directory = location.toString();
				File pathFile = new File(ConstantsUtility.GANNON_JVM_PATH_TXT);
				if (pathFile.exists()) {
					try {

						directory = FileUtils.readFileToString(pathFile);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				fc = new JFileChooser(new File(directory));

				int returnVal = fc.showOpenDialog(Main.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					file = fc.getSelectedFile();
					fc.setCurrentDirectory(file);
					// This is where a real application would open the file.
					txtrConsole.append("Opening: " + file.getName() + "." + ConstantsUtility.NEW_LINE);
					scrollPaneTree = new MethodTree(Main.this, file.getName());
					populateTree(scrollPaneTree);
					splitPaneRoot.setLeftComponent(scrollPaneTree);
					revalidate();
					repaint();

					// save the path to a file
					try {
						FileUtils.writeStringToFile(pathFile, file.getAbsolutePath());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
				}
			}
		});
		mntmOpenClass.setIcon(new ImageIcon(Main.class.getResource("/com/gannon/images16x16/folder_open.png")));
		mnFile.add(mntmOpenClass);

		JMenu mnRun = new JMenu("Run");
		menuBar.add(mnRun);

		JMenuItem mntmRun = new JMenuItem("Run");
		mnRun.add(mntmRun);

		JMenuItem mntmStepRun = new JMenuItem("Step Run");
		mnRun.add(mntmStepRun);
	}

	private void initFrame() {
		setResizable(false);
		setName("jvmFrame");
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/com/gannon/images/camera_test.png")));
		setTitle("Gannon JVM 1.0");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Make the big window be indented 50 pixels from each edge
		// of the screen.
		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);
	}

	private void populateTree(MethodTree methodScollPane) {
		String className = methodScollPane.getClassName();
		BClass myclass = BClassGenerator.getBClass(className);
		ArrayList<BMethod> methods = myclass.getMethods();

		// hard-coded need to fix
		DefaultMutableTreeNode p1;
		for (BMethod m : methods) {
			p1 = methodScollPane.addObject(null, m.getName());
			if (m.getName().equalsIgnoreCase("TriangleType")) {
				TestPathGenerator g = new TestPathGenerator(className, m.getName());
				for (TestPath p : g.deriveTestPaths().getPaths()) {
					methodScollPane.addObject(p1, p.getPathId());
				}
			}
		}
	}
}
