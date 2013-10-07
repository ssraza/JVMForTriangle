package com.gannon.asm.classgenerator;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;

import com.gannon.Executor.BytecodeObjectFactories.VisitFieldInstructionFactory;
import com.gannon.Executor.BytecodeObjectFactories.VisitInstructionFactory;
import com.gannon.Executor.BytecodeObjectFactories.VisitIntegerInstructionFactory;
import com.gannon.Executor.BytecodeObjectFactories.VisitJumpInstructionFactory;
import com.gannon.Executor.BytecodeObjectFactories.VisitMethodInstructionFactory;
import com.gannon.Executor.BytecodeObjectFactories.VisitTypeInstructionFactory;
import com.gannon.Executor.BytecodeObjectFactories.VisitVariableInstructionFactory;
import com.gannon.asm.components.BBlock;
import com.gannon.asm.components.BLabel;
import com.gannon.asm.components.BLocalVariable;
import com.gannon.asm.components.BMethod;
import com.gannon.asm.components.BStackMaxLocals;
import com.gannon.jvm.utilities.ConstantsUtility;

public class ClassMethodVisitor extends MethodVisitor {
	/**
	 * Object of Method being visited *
	 */
	private BMethod currentMethod;
	private BBlock currentBlock;
	private int linNumber = ConstantsUtility.INIT_PROGRAM_LINE_NUMBER;
	private int lableID=0;

	/**
	 * Constructor of ClasMethodVisitor
	 *
	 * @param arg0
	 * @param currentMethod
	 *            object of BMethod *
	 */
	public ClassMethodVisitor(int arg0, BMethod currentMethod) {
		super(arg0);
		this.currentMethod = currentMethod;
	}

	// GETSTATIC, PUTSTATIC, GETFIELD or PUTFIELD.
	@Override
	public void visitFieldInsn(int opcode, String owner, String name,
			String desc) {
		currentBlock.addInstruction(new VisitFieldInstructionFactory()
				.createInst(opcode, owner, name, desc, linNumber));

		super.visitFieldInsn(opcode, owner, name, desc);
		linNumber++;
	}

	// ILOAD, LLOAD, FLOAD, DLOAD, ALOAD, ISTORE, LSTORE, FSTORE, DSTORE, ASTORE
	// or RET.
	@Override
	public void visitVarInsn(int opcode, int operand1) {
		currentBlock.addInstruction(new VisitVariableInstructionFactory()
				.createInst(opcode, operand1, linNumber));


		linNumber++;
	}

	// GOTO or THROW
//	 @Override
//	 public void visitFrame(int i, int i1, Object[] os, int i2, Object[] os1)
//	 {
//	 super.visitFrame(i, i1, os, i2, os1); //To change body of generated methods,
//	 //choose Tools | Templates.
//	 System.out.println("visit frame "+i +" "+i1);
//	 System.out.println(os);
//	 System.out.println(os1);
//	 }

	// IFEQ, IFNE, IFLT, IFGE, IFGT, IFLE, IF_ICMPEQ, IF_ICMPNE, IF_ICMPLT,
	// IF_ICMPGE, IF_ICMPGT, IF_ICMPLE, IF_ACMPEQ, IF_ACMPNE, GOTO, JSR, IFNULL
	// or IFNONNULL
	@Override
	public void visitJumpInsn(int opcode, Label label) {
		super.visitJumpInsn(opcode, label); // To change body of generated
		// methods, choose Tools |
		// Templates.
		currentBlock.addInstruction(new VisitJumpInstructionFactory()
				.createInst(opcode, label, linNumber));

		linNumber++;
	}

	// IINC
	@Override
	public void visitIincInsn(int opcode, int inc) {

		// int opCode = 132;
		// currentMethod.addInstruction(new BInstruction(opCode, opcode, inc));


		super.visitIincInsn(opcode, inc); // To change body of generated
											// methods, choose Tools |
											// Templates.
		linNumber++;
	}

	// NOP, ACONST_NULL, ICONST_M1, ICONST_0, ICONST_1, ICONST_2, ICONST_3,
	// ICONST_4,
	// ICONST_5, LCONST_0, LCONST_1, FCONST_0, FCONST_1, FCONST_2, DCONST_0,
	// DCONST_1,
	// IALOAD, LALOAD, FALOAD, DALOAD, AALOAD, BALOAD, CALOAD, SALOAD, IASTORE,
	// LASTORE,
	// FASTORE, DASTORE, AASTORE, BASTORE, CASTORE, SASTORE, POP, POP2, DUP,
	// DUP_X1, DUP_X2,
	// DUP2, DUP2_X1, DUP2_X2, SWAP, IADD, LADD, FADD, DADD, ISUB, LSUB, FSUB,
	// DSUB, IMUL, LMUL,
	// FMUL, DMUL, IDIV, LDIV, FDIV, DDIV, IREM, LREM, FREM, DREM, INEG, LNEG,
	// FNEG, DNEG, ISHL,
	// LSHL, ISHR, LSHR, IUSHR, LUSHR, IAND, LAND, IOR, LOR, IXOR, LXOR, I2L,
	// I2F, I2D, L2I, L2F,
	// L2D, F2I, F2L, F2D, D2I, D2L, D2F, I2B, I2C, I2S, LCMP, FCMPL, FCMPG,
	// DCMPL, DCMPG, IRETURN,
	// LRETURN, FRETURN, DRETURN, ARETURN, RETURN, ARRAYLENGTH, ATHROW,
	// MONITORENTER, or MONITOREXIT.
	@Override
	public void visitInsn(int opcode) {
		System.out.println("visitInsn " + opcode);
		currentBlock.addInstruction(new VisitInstructionFactory().createInst(
				opcode, linNumber));


		linNumber++;
	}

	// INVOKEVIRTUAL, INVOKESPECIAL, INVOKESTATIC or INVOKEINTERFACE.
	@Override
	public void visitMethodInsn(int opcode, String owner, String name,
			String desc) {
		System.out.println("ClassMethodVisitor " + opcode);
		currentBlock.addInstruction(new VisitMethodInstructionFactory()
				.createInst(opcode, owner, name, desc, linNumber));
		linNumber++;
	}

	// BIPUSH, SIPUSH or NEWARRAY
	@Override
	public void visitIntInsn(int opcode, int operand) {
		//System.out.println(opcode);
		System.out.println("visitIntInsn " + opcode);
		currentBlock.addInstruction(new VisitIntegerInstructionFactory()
				.createInst(opcode, operand, linNumber));


		linNumber++;
	}

	@Override
	public void visitLabel(Label label) {
		BLabel bLabel=new BLabel(label,lableID);
		//The label contains the instruction ID/line number the execution jumps to
		bLabel.setGoToLineNumber(linNumber);
		currentBlock = new BBlock(bLabel);
		currentMethod.addBlock(currentBlock);
		lableID++;
		super.visitLabel(label);
	}

	@Override
	public void visitLineNumber(int line, Label start) {
		//line is the line number of source code
		super.visitLineNumber(line, start);
		//System.out.println("visit line Number"+ line+"  "+start.toString());
	}

	@Override
	public void visitMaxs(int maxStack, int maxLocals) {
		currentMethod
				.addStackVariable(new BStackMaxLocals(maxStack, maxLocals));
		super.visitMaxs(maxStack, maxLocals);
	}

	@Override
	public void visitLocalVariable(String name, String desc, String signature,
			Label start, Label end, int index) {
		BLocalVariable aLocalVariable = new BLocalVariable(name, desc,
				signature, index);
		currentMethod.addLocalVariableTable(aLocalVariable);
		//System.out.println("local variables: " +name+ " " +desc +" "+signature+ " "  + start.toString()+" "+ end.toString()+ " "+ index);
	}

	@Override
	public void visitLdcInsn(Object opcode) {
		// TODO Auto-generated method stub
		super.visitLdcInsn(opcode);
	}

	@Override
	public AnnotationVisitor visitAnnotation(String arg0, boolean arg1) {
		// TODO Auto-generated method stub
		return super.visitAnnotation(arg0, arg1);
	}

	@Override
	public AnnotationVisitor visitAnnotationDefault() {
		// TODO Auto-generated method stub
		return super.visitAnnotationDefault();
	}

	@Override
	public void visitAttribute(Attribute arg0) {
		// TODO Auto-generated method stub
		super.visitAttribute(arg0);
	}

	@Override
	public void visitCode() {
		// TODO Auto-generated method stub
		super.visitCode();
	}

	@Override
	public void visitEnd() {
		// TODO Auto-generated method stub
		super.visitEnd();
	}

	@Override
	public void visitFrame(int arg0, int arg1, Object[] arg2, int arg3,
			Object[] arg4) {
		// TODO Auto-generated method stub
		super.visitFrame(arg0, arg1, arg2, arg3, arg4);
	}

	@Override
	public void visitInvokeDynamicInsn(String arg0, String arg1, Handle arg2,
			Object... arg3) {
		// TODO Auto-generated method stub
		super.visitInvokeDynamicInsn(arg0, arg1, arg2, arg3);
	}

	@Override
	public void visitLookupSwitchInsn(Label arg0, int[] arg1, Label[] arg2) {
		// TODO Auto-generated method stub
		super.visitLookupSwitchInsn(arg0, arg1, arg2);
	}

	@Override
	public void visitMultiANewArrayInsn(String arg0, int arg1) {
		// TODO Auto-generated method stub
		super.visitMultiANewArrayInsn(arg0, arg1);
	}

	@Override
	public AnnotationVisitor visitParameterAnnotation(int arg0, String arg1,
			boolean arg2) {
		// TODO Auto-generated method stub
		return super.visitParameterAnnotation(arg0, arg1, arg2);
	}

	@Override
	public void visitTableSwitchInsn(int arg0, int arg1, Label arg2,
			Label... arg3) {
		// TODO Auto-generated method stub
		super.visitTableSwitchInsn(arg0, arg1, arg2, arg3);
	}

	@Override
	public void visitTryCatchBlock(Label arg0, Label arg1, Label arg2,
			String arg3) {
		// TODO Auto-generated method stub
		super.visitTryCatchBlock(arg0, arg1, arg2, arg3);
	}

	@Override
	public void visitTypeInsn(int opcode, String packageName) {
		// TODO Auto-generated method stub
		currentBlock.addInstruction(new VisitTypeInstructionFactory().createInst(opcode, packageName, linNumber));
		linNumber++;
		super.visitTypeInsn(opcode, packageName);
	}
	
	
}
