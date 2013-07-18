package test.function;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import com.gannon.asm.component.BBlock;
import com.gannon.asm.component.BMethod;
import com.gannon.jvm.Instructions.BInstruction;
import com.gannon.jvm.Instructions_.BIAdd;
import com.gannon.jvm.Instructions_.BILoad;
import com.gannon.jvm.Instructions_.BIReturn;
import com.gannon.jvm.Instructions_.BIStore;
import com.gannon.treeStructure.Node;
import com.gannon.treeStructure.Tree;
import com.gannon.treeStructure.TreeStructure;

public class TreeStructureTest {

	@Test
	public void testGetSecondParameter1() {
		ArrayList<BInstruction> instructionList = new ArrayList<BInstruction>();
		int index;
		int tempVarCounter;
		HashMap<Integer, String> dependencyDataCollector = new HashMap<Integer, String>();
		
		instructionList.add(new BILoad(1));
		instructionList.add(new BILoad(2));
		instructionList.add(new BILoad(3));
		instructionList.add(new BIAdd());
		instructionList.add(new BIStore(3));
		
		index = 3;
		
		tempVarCounter = 0;
		
		TreeStructure tStructure = new TreeStructure();
		
		Node retrivedNode = tStructure.getSecondParameter(instructionList, index, tempVarCounter, dependencyDataCollector);
		
		assertEquals("iload 2", retrivedNode.getNodeName());
		
	}
	
	@Test
	public void testGetSecondParameter2() {
		ArrayList<BInstruction> instructionList = new ArrayList<BInstruction>();
		int index;
		int tempVarCounter;
		HashMap<Integer, String> dependencyDataCollector = new HashMap<Integer, String>();
		
		instructionList.add(new BILoad(1));
		instructionList.add(new BILoad(2));
		instructionList.add(new BILoad(3));
		instructionList.add(new BIAdd());
		instructionList.add(new BIAdd());
		instructionList.add(new BIStore(3));
		
		index = 4;
		
		tempVarCounter = 0;
		
		TreeStructure tStructure = new TreeStructure();
		
		Node retrivedNode = tStructure.getSecondParameter(instructionList, index, tempVarCounter, dependencyDataCollector);
		
		assertEquals("iload 1", retrivedNode.getNodeName());
		
	}
	
	@Test
	public void testGetSecondParameter3() {
		ArrayList<BInstruction> instructionList = new ArrayList<BInstruction>();
		int index;
		int tempVarCounter;
		HashMap<Integer, String> dependencyDataCollector = new HashMap<Integer, String>();
		
		instructionList.add(new BILoad(0));
		instructionList.add(new BILoad(1));
		instructionList.add(new BILoad(2));
		instructionList.add(new BILoad(3));
		instructionList.add(new BIAdd());
		instructionList.add(new BIAdd());
		instructionList.add(new BIAdd());
		instructionList.add(new BIStore(3));
		
		index = 6;
		
		tempVarCounter = 3;
		
		TreeStructure tStructure = new TreeStructure();
		
		Node retrivedNode = tStructure.getSecondParameter(instructionList, index, tempVarCounter, dependencyDataCollector);
		
		assertEquals("temp1", retrivedNode.getNodeName());
		
	}
	
	@Test
	public void testDependencyGeneratorForLeftLeaf() {
		ArrayList<BInstruction> instructionList = new ArrayList<BInstruction>();
		int index;
		int tempVarCounter;
		HashMap<Integer, String> dependencyDataCollector = new HashMap<Integer, String>();
		
		BMethod testMethod = new BMethod();
		
		testMethod.setName("calee");
		ArrayList<BBlock> blockList = new ArrayList<BBlock>();
			
		BBlock label = new BBlock();
			label.setNewLableName("L0");
				ArrayList<BInstruction> Instr = new ArrayList<BInstruction>();
					Instr.add(new BILoad(0));
					Instr.add(new BILoad(1));
					Instr.add(new BILoad(2));
					Instr.add(new BIAdd());
					Instr.add(new BIAdd());
					Instr.add(new BIAdd());
					Instr.add(new BIStore(3));
					label.setInstructions(Instr);
			blockList.add(label);
			
			label.setNewLableName("L1");
					Instr.add(new BILoad(3));
					Instr.add(new BIReturn());						
			label.setInstructions(Instr);
			blockList.add(label);				
					
			testMethod.setBlockList(blockList);
		
		TreeStructure tStructure = new TreeStructure();
		
		ArrayList<Tree> dependencyObjectList = tStructure.dependencyGenerator(testMethod);
		
		String leftNodeName = dependencyObjectList.get(2).getLeftLeaf().getNodeName();
	
		assertEquals("temp1", leftNodeName);
		
	}
	
	@Test
	public void testDependencyGeneratorForRightLeaf() {
		ArrayList<BInstruction> instructionList = new ArrayList<BInstruction>();
		int index;
		int tempVarCounter;
		HashMap<Integer, String> dependencyDataCollector = new HashMap<Integer, String>();
		
		BMethod testMethod = new BMethod();
		
		testMethod.setName("calee");
		ArrayList<BBlock> blockList = new ArrayList<BBlock>();
			
		BBlock label = new BBlock();
			label.setNewLableName("L0");
				ArrayList<BInstruction> Instr = new ArrayList<BInstruction>();
					Instr.add(new BILoad(0));
					Instr.add(new BILoad(1));
					Instr.add(new BILoad(2));
					Instr.add(new BIAdd());
					Instr.add(new BIAdd());
					Instr.add(new BIAdd());
					Instr.add(new BIStore(3));
					label.setInstructions(Instr);
			blockList.add(label);
			
			label.setNewLableName("L1");
					Instr.add(new BILoad(3));
					Instr.add(new BIReturn());						
			label.setInstructions(Instr);
			blockList.add(label);				
					
			testMethod.setBlockList(blockList);
		
		TreeStructure tStructure = new TreeStructure();
		
		ArrayList<Tree> dependencyObjectList = tStructure.dependencyGenerator(testMethod);
		
		String leftNodeName = dependencyObjectList.get(2).getRightLeaf().getNodeName();
	
		assertEquals("temp0", leftNodeName);
		
	}
	
	@Test
	public void testDependencyGeneratorForRightLeaf2() {
		ArrayList<BInstruction> instructionList = new ArrayList<BInstruction>();
		int index;
		int tempVarCounter;
		HashMap<Integer, String> dependencyDataCollector = new HashMap<Integer, String>();
		
		BMethod testMethod = new BMethod();
		
		testMethod.setName("calee");
		ArrayList<BBlock> blockList = new ArrayList<BBlock>();
			
		BBlock label = new BBlock();
			label.setNewLableName("L0");
				ArrayList<BInstruction> Instr = new ArrayList<BInstruction>();
					Instr.add(new BILoad(0));
					Instr.add(new BILoad(1));
					Instr.add(new BILoad(2));
					Instr.add(new BIAdd());
					Instr.add(new BIAdd());
					Instr.add(new BIAdd());
					Instr.add(new BIStore(3));
					label.setInstructions(Instr);
			blockList.add(label);
			
			label.setNewLableName("L1");
					Instr.add(new BILoad(3));
					Instr.add(new BIReturn());						
			label.setInstructions(Instr);
			blockList.add(label);				
					
			testMethod.setBlockList(blockList);
		
		TreeStructure tStructure = new TreeStructure();
		
		ArrayList<Tree> dependencyObjectList = tStructure.dependencyGenerator(testMethod);
		
		String leftNodeName = dependencyObjectList.get(1).getRightLeaf().getNodeName();
	
		assertEquals("iload 0", leftNodeName);
		
	}
	
	

}
