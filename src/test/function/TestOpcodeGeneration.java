package test.function;

import java.util.ArrayList;

import org.junit.Test;

import com.gannon.ASM.BytecodeComponent.BClass;
import com.gannon.ASM.BytecodeComponent.BBlock;
import com.gannon.ASM.BytecodeComponent.BMethod;
import com.gannon.Executor.Instruction.BALoad;
import com.gannon.Executor.Instruction.BBipush;
import com.gannon.Executor.Instruction.BIAdd;
import com.gannon.Executor.Instruction.BIConst_0;
import com.gannon.Executor.Instruction.BIConst_1;
import com.gannon.Executor.Instruction.BILoad;
import com.gannon.Executor.Instruction.BIReturn;
import com.gannon.Executor.Instruction.BIStore;
import com.gannon.Executor.Instruction.BInstruction;
import com.gannon.Executor.Instruction.BInvokeSpecial;
import com.gannon.Executor.Instruction.BInvokeVirtual;
import com.gannon.Executor.Instruction.BPutStatic;
import com.gannon.Executor.Instruction.BReturn;

public class TestOpcodeGeneration {

	@Test
	public void test() {
		
		
		BBlock lbl = new BBlock();

		
		ArrayList<BMethod> methodObjectList = new ArrayList<BMethod>();
		
		//
		BMethod method1 = new BMethod();
		
		method1.setName("<init>");
		ArrayList<BBlock> labelList = new ArrayList<BBlock>();
			
		BBlock label0 = new BBlock();
			label0.setNewLableName("L0");
				ArrayList<BInstruction> Instr1 = new ArrayList<BInstruction>();
					Instr1.add(new BALoad(0));
					Instr1.add(new BInvokeSpecial("java/lang/Object", "init", "()V"));
			label0.setInstructions(Instr1);
			labelList.add(label0);
			
		BBlock label1 = new BBlock();
			label1.setNewLableName("L1");
				ArrayList<BInstruction> Instr2 = new ArrayList<BInstruction>();
					Instr2.add(new BALoad(0));
					Instr2.add(new BIConst_1());
					Instr2.add(new BPutStatic("com/gannon/ASM/BytecodeReader/Hello", "i", "I"));
			label1.setInstructions(Instr2);
			labelList.add(label1);
			
			BBlock label2 = new BBlock();
				label2.setNewLableName("L2");
					ArrayList<BInstruction> Instr3 = new ArrayList<BInstruction>();
						Instr3.add(new BALoad(0));
						Instr3.add(new BIConst_0());
						Instr3.add(new BPutStatic("com/gannon/ASM/BytecodeReader/Hello", "j", "I"));
				label2.setInstructions(Instr3);
				labelList.add(label2);			
				
				BBlock label3 = new BBlock();
					label3.setNewLableName("L3");
						ArrayList<BInstruction> Instr4 = new ArrayList<BInstruction>();
							Instr4.add(new BReturn());
					label3.setInstructions(Instr4);
					labelList.add(label3);
					
			method1.setBlockList(labelList);
					
			methodObjectList.add(method1);
		
		//
			BMethod method2 = new BMethod();
			
			method1.setName("intCaller");
			ArrayList<BBlock> labelList2 = new ArrayList<BBlock>();
				
			BBlock label00 = new BBlock();
				label0.setNewLableName("L0");
					ArrayList<BInstruction> Instr11 = new ArrayList<BInstruction>();
						Instr11.add(new BALoad(0));
						Instr11.add(new BILoad(1));
						Instr11.add(new BInvokeVirtual("com/gannon/ASM/BytecodeReader/Hello", "plusFive", "(I)I"));
						Instr11.add(new BIStore(2));
				label00.setInstructions(Instr11);
				labelList2.add(label00);
				
			BBlock label11 = new BBlock();
				label11.setNewLableName("L1");
					ArrayList<BInstruction> Instr12 = new ArrayList<BInstruction>();
						Instr12.add(new BILoad(2));
						Instr12.add(new BIReturn());						
				label11.setInstructions(Instr12);
				labelList2.add(label11);
													
				method1.setBlockList(labelList2);
						
				methodObjectList.add(method2);
					
		//
				BMethod method3 = new BMethod();
				
				method3.setName("plusFive");
				ArrayList<BBlock> labelList3 = new ArrayList<BBlock>();
					
				BBlock label20 = new BBlock();
					label20.setNewLableName("L0");
						ArrayList<BInstruction> Instr21 = new ArrayList<BInstruction>();
							Instr21.add(new BALoad(0));
							Instr21.add(new BILoad(1));
							Instr21.add(new BBipush(10));
							Instr21.add(new BInvokeVirtual("com/gannon/ASM/BytecodeReader/Hello", "callee", "(II)I"));
							Instr21.add(new BIStore(2));
							label20.setInstructions(Instr21);
					labelList3.add(label20);
					
					BBlock label21 = new BBlock();
					label21.setNewLableName("L1");
						ArrayList<BInstruction> Instr22 = new ArrayList<BInstruction>();
							Instr22.add(new BILoad(2));
							Instr22.add(new BIReturn());						
					label21.setInstructions(Instr22);
					labelList3.add(label21);				
							
					method1.setBlockList(labelList3);
							
					methodObjectList.add(method3);
				
		//
					BMethod method4 = new BMethod();
					
					method4.setName("calee");
					ArrayList<BBlock> labelList4 = new ArrayList<BBlock>();
						
					BBlock label30 = new BBlock();
						label30.setNewLableName("L0");
							ArrayList<BInstruction> Instr31 = new ArrayList<BInstruction>();
								Instr31.add(new BILoad(1));
								Instr31.add(new BILoad(2));
								Instr31.add(new BIAdd());
								Instr31.add(new BIStore(3));
								label30.setInstructions(Instr31);
						labelList4.add(label30);
						
						BBlock label31 = new BBlock();
						label31.setNewLableName("L1");
							ArrayList<BInstruction> Instr32 = new ArrayList<BInstruction>();
								Instr32.add(new BILoad(3));
								Instr32.add(new BIReturn());						
						label31.setInstructions(Instr32);
						labelList4.add(label31);				
								
						method1.setBlockList(labelList4);
								
						methodObjectList.add(method4);		
					
				//
						BMethod method5 = new BMethod();
						
						method4.setName("calee2");
						ArrayList<BBlock> labelList5 = new ArrayList<BBlock>();
							
						BBlock label40 = new BBlock();
							label40.setNewLableName("L0");
								ArrayList<BInstruction> Instr41 = new ArrayList<BInstruction>();
									Instr41.add(new BILoad(1));
									Instr41.add(new BILoad(2));
									Instr41.add(new BIAdd());
									Instr41.add(new BIStore(3));
									label40.setInstructions(Instr31);
							labelList5.add(label40);
							
							BBlock label41 = new BBlock();
							label31.setNewLableName("L1");
								ArrayList<BInstruction> Instr42 = new ArrayList<BInstruction>();
									Instr42.add(new BIReturn());						
							label41.setInstructions(Instr42);
							labelList5.add(label41);				
									
							method5.setBlockList(labelList5);
									
							methodObjectList.add(method5);
							
				//
							BMethod method6 = new BMethod();
							
							method6.setName("add1");
							ArrayList<BBlock> labelList6 = new ArrayList<BBlock>();
								
							BBlock label50 = new BBlock();
								label50.setNewLableName("L0");
									ArrayList<BInstruction> Instr51 = new ArrayList<BInstruction>();
										Instr51.add(new BILoad(1));
										Instr51.add(new BIConst_1());
										Instr51.add(new BIAdd());
										Instr51.add(new BIReturn());
										label50.setInstructions(Instr31);
								labelList6.add(label50);
																
								method6.setBlockList(labelList6);
										
								methodObjectList.add(method6);	
								
			//
								
//								BMethod method5 = new BMethod();
//								
//								method4.setName("calee2");
//								ArrayList<BLable> labelList5 = new ArrayList<BLable>();
//									
//								BLable label40 = new BLable();
//									label40.setLabelName("L0");
//										ArrayList<BInstruction> Instr41 = new ArrayList<BInstruction>();
//											Instr41.add(new BILoad(1));
//											Instr41.add(new BILoad(2));
//											Instr41.add(new BIAdd());
//											Instr41.add(new BIStore(3));
//											label40.setInstructions(Instr31);
//									labelList5.add(label40);
//									
//									BLable label41 = new BLable();
//									label31.setLabelName("L1");
//										ArrayList<BInstruction> Instr42 = new ArrayList<BInstruction>();
//											Instr42.add(new BIReturn());						
//									label41.setInstructions(Instr42);
//									labelList5.add(label41);				
//											
//									method5.setLabelDetails(labelList5);
//											
//									methodObjectList.add(method5);
							
		BClass myClass = new BClass();
		myClass.setMethods(methodObjectList);
	}

}
