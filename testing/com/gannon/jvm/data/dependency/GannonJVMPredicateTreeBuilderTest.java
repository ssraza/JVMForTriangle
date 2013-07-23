package com.gannon.jvm.data.dependency;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class GannonJVMPredicateTreeBuilderTest {
	@Rule
	public TestRule watcher = new TestWatcher() {
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};

	@Test
	public void testBuild() {
		// assume we have 3 parameters
		Relation r0 = new Relation(new BinNode("0"), null);// not using
		Relation r1 = new Relation(new BinNode("1"), null);
		Relation r2 = new Relation(new BinNode("2"), null);
		Relation r3 = new Relation(new BinNode("3"), null);

		Relation r101 = new Relation(new BinNode("101"), null);
		r101.insertToLeft(new BinNode("102"));
		r101.insertToRight(new BinNode("103"));
		r101.niceDisplay();

		Relation r102 = new Relation(new BinNode("102"), null);
		r102.insertToLeft(new BinNode("1"));
		r102.insertToRight(new BinNode("2"));
		r102.niceDisplay();

		Relation r103 = new Relation(new BinNode("103"), null);
		r103.insertToLeft(new BinNode("3"));
		r103.insertToRight(new BinNode("2"));
		r103.niceDisplay();

		ArrayList<Relation> relations = new ArrayList<Relation>();
		relations.add(r0);
		relations.add(r1);
		relations.add(r2);
		relations.add(r3);
		relations.add(r101);
		relations.add(r102);
		relations.add(r103);

		GannonJVMPredicateTreeBuilder jvm = new GannonJVMPredicateTreeBuilder();
		Relation actualResult=null;
		jvm.build(r101.getTheBTRootNode(), relations).niceDisplay();


	}

}
