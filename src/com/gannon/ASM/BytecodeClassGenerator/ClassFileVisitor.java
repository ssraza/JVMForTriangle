package com.gannon.ASM.BytecodeClassGenerator;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import com.gannon.ASM.BytecodeComponent.BClass;
import com.gannon.ASM.BytecodeComponent.BMethod;

public class ClassFileVisitor extends ClassVisitor {
	BClass myClass = new BClass();

	public ClassFileVisitor() {
		super(Opcodes.ASM4);
	}

	public void visit(int version, int access, String name, String signature,
			String superName, String[] interfaces) {
		myClass.setClassName(name);
		myClass.setSuperClass(superName);
		myClass.setVersion(version);
		myClass.setClassAccess(access);

	}

	public void visitSource(String source, String debug) {
		myClass.setSourceFile(source);

	}

	public void visitOuterClass(String owner, String name, String desc) {
	}

	public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
		return null;
	}

	public void visitAttribute(Attribute attr) {
	}

	public void visitInnerClass(String name, String outerName,
			String innerName, int access) {
	}

	public FieldVisitor visitField(int access, String name, String desc,
			String signature, Object value) {
		System.out.println(" Field " + desc + " " + name);
		return null;
	}

	public MethodVisitor visitMethod(int access, String name, String desc,
			String signature, String[] exceptions) {
		BMethod currentMethod = new BMethod(access, name, desc);
		myClass.addMethod(currentMethod);
		MethodVisitor oriMv = new ClassMethodVisitor(Opcodes.ASM4,
				currentMethod);
		return oriMv;
	}

	public void visitEnd() {
		//System.out.println("}");
	}

	public BClass getMyClass() {
		return myClass;
	}

	public void setMyClass(BClass myClass) {
		this.myClass = myClass;
	}

	public void display() {

	}

}
