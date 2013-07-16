
package test.opcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Stack;

import org.junit.Test;

import com.gannon.ASM.BytecodeComponent.BClass;
import com.gannon.ASM.BytecodeComponent.BMethod;
import com.gannon.Executor.Instruction.BInvokeVirtual;
import com.gannon.Executor.JVMExecutionObjects.BFrame;
import com.gannon.Executor.JVMExecutionObjects.BLocalVarTable;
import com.gannon.Executor.JVMExecutionObjects.JVMStackSingleton;
import com.gannon.Main.InterfaceAPISingleton;

public class BInvokeVirtualTest {

	@Test
	public void testGetOpcode() {
		System.out.println("getOpcode");
		BInvokeVirtual instance = new BInvokeVirtual("TestClass","Method2","TestDesc");
		int expResult = 182;
		int result = instance.getOpcode();
		assertEquals(expResult, result);
	}

	@Test
	public void testGetOpcodeCommand() {
		System.out.println("getOpcodeCommand");
		BInvokeVirtual instance = new BInvokeVirtual("TestClass","Method2","TestDesc");
		String expResult = "invokevirtual TestClass Method2 TestDesc";
		String result = instance.getOpcodeCommand();
		assertEquals(expResult, result);
	}

	@Test
	public void testExecuteBFrame() {
		System.out.println("ExecuteBFrame");
		
		BClass testBclass = new BClass();
		//set class name
		testBclass.setClassName("TestClass");
		//create arraylist of BMethod objects
		ArrayList<BMethod> methods = new ArrayList<BMethod>();
		methods.add(new BMethod(1, "Method1", "TestDesc1"));
		methods.add(new BMethod(1, "Method2", "TestDesc2"));
		methods.add(new BMethod(1, "Method3", "TestDesc3"));
		methods.add(new BMethod(1, "Method4", "TestDesc4"));
		testBclass.setMethods(methods);
		
		InterfaceAPISingleton.getInstance().setbClass(testBclass);// set BClass object in interfaceAPI, 
																  // It will make one BClass available through out the execution
		
		BInvokeVirtual virtualCallTest = new BInvokeVirtual("TestClass","Method2","TestDesc2");
		
		// init local Variable table
		BLocalVarTable varTable = new BLocalVarTable();
		Stack<Integer> operandStack = new Stack<Integer>();
				
		BFrame activeFrame = new BFrame(varTable, 0, operandStack);
		
		virtualCallTest.execute(activeFrame);
		
		assertEquals((Integer)JVMStackSingleton.getInstance().size(), new Integer(2));
	}

	@Test
	public void testGetNextMethod() {
		System.out.println("getNextMethod");
		//This test case is only for one class, method calling method happening in one class only,
		//
		BClass testBclass = new BClass();
		//set class name
		testBclass.setClassName("TestClass");
		//create arraylist of method. add only method names
		ArrayList<BMethod> methods = new ArrayList<BMethod>();
		methods.add(new BMethod(1, "Method1", "TestDesc1"));
		methods.add(new BMethod(1, "Method2", "TestDesc2"));
		methods.add(new BMethod(1, "Method3", "TestDesc3"));
		methods.add(new BMethod(1, "Method4", "TestDesc4"));
		testBclass.setMethods(methods);
		
		BInvokeVirtual virtualCallTest = new BInvokeVirtual("TestClass","Method2","TestDesc");
		BMethod returnMethodObject = virtualCallTest.getNextMethod("TestClass", "Method2", testBclass);
		
		assertEquals(returnMethodObject.getName(), "Method2");
	}

}
