package com.gannon.jvm.data.dependency;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import com.gannon.jvm.instructions.BIAdd;
import com.gannon.jvm.utilities.TrianglePathBuilderUtility;


public class DependenciesTest {
	@Rule
	public TestRule watcher = new TestWatcher() {
		@Override
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};

	@Test
	public void testFindRelationByInstruction() {

		GannonPredicateTreeBuilderJVM jvm = new GannonPredicateTreeBuilderJVM();
		jvm.run(TrianglePathBuilderUtility.createPathID1());

		ArrayList<BinNode> expectedLeaves23 = new ArrayList<BinNode>();
		expectedLeaves23.add(new BinNode("2",0));
		expectedLeaves23.add(new BinNode("3",0));
		// 0:first parameter
		Dependencies relations = jvm.getRelationFrame().getRelations();
		System.out.print(relations);
		ArrayList<BinNode> actualLeaves = relations.getRelation(4).getAllLeaves(); 
		assertEquals(expectedLeaves23, actualLeaves);
		assertEquals(relations.findRelation(new BIAdd(4)),relations.getRelation(4));
//		Label l=new Label();
//		BLabel blabel=new BLabel(l);
//		assertEquals(relations.getRelation(4),relations.findRelation(new BIFicmpge(blabel,11)));
	}

}
