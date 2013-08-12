package com.gannon.jvm.progam.path;

import java.util.ArrayList;
import java.util.List;

import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;
import com.gannon.bytecode.controlflowgraph.CNode;
import com.gannon.bytecode.controlflowgraph.CPath;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.instructions.BPredicateInstruction;
import com.gannon.jvm.utilities.ConstantsUtility;

public class TestPath {
	private int pathId;
	private BClass bClass;
	private BMethod bMethod;// the method to where the path belongs
	private List<Node> nodes = new ArrayList<Node>();
	private List<Object> inputs = new ArrayList<Object>();

	public TestPath() {
		super();
	}

	public TestPath(int pathId) {
		super();
		this.pathId = pathId;
	}

	// CPath has blocks (multiple instructions), however, a test path only
	// contains nodes, which is an instruction. We also need the expected
	// predicate values from CEdges
	public TestPath(CPath cPath) {
		this.pathId = cPath.getId();
		List<CNode> cNodes = cPath.getNodes();
		for (CNode cNode : cNodes) {
			List<BInstruction> instructions = cNode.getBlock().getInstructions();
			for (BInstruction inst : instructions) {
				// attach expected results from CFG to the predicate instruction
				// now only check the source instruction. If testing loop in the
				// future, we need to check target instruction as well
				if (inst instanceof BPredicateInstruction) {
					int expectedPredicateResult = cPath.findExpectedPredicateValue(inst);
					PredicateNode node = new PredicateNode(inst);
					node.setExpectedPredicateResult(expectedPredicateResult);
					nodes.add(node);
				} else {
					// attatch node to the test path
					nodes.add(new Node(inst));
				}

			}
		}
	}

	public int getPathId() {
		return pathId;
	}

	public void setPathId(int pathId) {
		this.pathId = pathId;
	}

	public BMethod getbMethod() {
		return bMethod;
	}

	public void setbMethod(BMethod bMethod) {
		this.bMethod = bMethod;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(ArrayList<Node> nodes) {
		this.nodes = nodes;
	}

	public void add(Node node) {
		this.nodes.add(node);
	}

	public List<Object> getInputs() {
		return inputs;
	}

	public void setInputs(ArrayList<Object> inputs) {
		this.inputs = inputs;
	}

	public BClass getbClass() {
		return bClass;
	}

	public void setbClass(BClass bClass) {
		this.bClass = bClass;
	}

	public ArrayList<Integer> getExecutedInsIDs() {
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (Node node : nodes) {
			ids.add(node.getInstructionLineNumber());
		}
		return ids;
	}

	public Node getNode(int lineNumber) {
		for (Node node : nodes) {
			if (node.getInstruction().getLineNumber() == lineNumber) {
				return node;
			}
		}
		return null;
	}

	public ArrayList<BInstruction> getInstrucitons() {
		ArrayList<BInstruction> instructions = new ArrayList<BInstruction>();
		for (Node node : nodes) {
			instructions.add(node.getInstruction());
		}
		return instructions;
	}

	public void clearIgnoreFlags() {
		for (Node node : nodes) {
			if (node instanceof PredicateNode) {
				((PredicateNode) node).setIgnore(false);
			}
		}
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = true;
		if (!(obj instanceof TestPath)) {
			return false;
		}
		if (nodes.size() != ((TestPath) obj).getNodes().size()) {
			return false;
		}
		for (int i = 0; i < nodes.size(); i++) {
			if (!nodes.get(i).equals(((TestPath) obj).getNodes().get(i))) {
				return false;
			}
		}
		return result;
	}

	@Override
	public int hashCode() {
		return bMethod.hashCode();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Node node : nodes) {
			sb.append(node + "\n");
			// if (node instanceof PredicateNode) {
			// int expectedValue = ((PredicateNode)
			// node).getExpectedPredicateResult();
			// if (expectedValue != ConstantsUtility.UNDEFINED_EXPECTED_VALUE) {
			// sb.append(" ->" + expectedValue);
			// }
			// }
		}
		return sb.toString();

	}
}
