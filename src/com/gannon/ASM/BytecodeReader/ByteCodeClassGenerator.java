package com.gannon.ASM.BytecodeReader;
import java.io.IOException;
import java.io.InputStream;

import org.objectweb.asm.ClassReader;

import com.gannon.Main.BytecodeFactory;

public class ByteCodeClassGenerator {
	private BytecodeFactory bFactory;
	private String className;
	
	public ByteCodeClassGenerator(String className) {
		super();
		this.bFactory = new BytecodeFactory();
		this.className = className;
	}

	/**
	 * This method reads the .class file and converts it to Bytecode class, i.e., BClass
	 * The bytecode class is accessible in bFactory.
	 */
	public void cparser() {
		ClassFileVisitor cp = new ClassFileVisitor();
		ClassReader cr = null;
		InputStream in = ByteCodeClassGenerator.class.getResourceAsStream(this.className);

		try {
			cr = new ClassReader(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		cr.accept(cp, 0);

		bFactory.setbClass(cp.getMyClass());
	
		//cp.getMyClass().display();
	}

	public BytecodeFactory getbFactory() {
		return bFactory;
	}

}
