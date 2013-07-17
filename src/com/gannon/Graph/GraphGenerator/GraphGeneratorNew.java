package com.gannon.Graph.GraphGenerator;

import java.util.ArrayList;

import com.gannon.Graph.GraphObjects.Graph;
import com.gannon.Graph.GraphObjects.GraphObjectBuilder;
import com.gannon.Main.InterfaceAPISingleton;
import com.gannon.asm.component.BClass;
import com.gannon.asm.component.BMethod;

public class GraphGeneratorNew {
	// public static void main(String[] args) throws IOException {
	public void generateGraph() {
		ArrayList<BMethod> methods = new ArrayList<BMethod>();
		ArrayList<GraphObjectBuilder> resultantMethods = new ArrayList<GraphObjectBuilder>();

		// ClassParser parser = new ClassParser();

		// parser.cparser("Hello.class");

		// BytecodeFactory factory = new BytecodeFactory();

		BClass bClass = InterfaceAPISingleton.getInstance().getbClass();

		methods = bClass.getMethods();

		int index = bClass.getIndexOf(InterfaceAPISingleton.getInstance()
				.getSelectedMethod());

		BMethod selectedMethod = methods.get(index);

		BuildCFG buildCFG = new BuildCFG(methods, selectedMethod);
		Graph g = buildCFG.getResultGraph();
		// MethodGraphInitializer mgi = new
		// MethodGraphInitializer(methods.get(2));
		// ArrayList<Block> blocks = mgi.buildBlocks();
		// Graph g = mgi.buildGraph(blocks);

		// ArrayList<Block> blocks = mb.buildBlocks();
		// Graph g = mb.buildGraph(blocks);
		DisplayCFG d = new DisplayCFG();
		d.draw(g);
	}
}
