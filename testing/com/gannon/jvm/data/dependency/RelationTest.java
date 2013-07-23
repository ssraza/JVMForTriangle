package com.gannon.jvm.data.dependency;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class RelationTest {
	@Rule
	public TestRule watcher = new TestWatcher() {
		protected void starting(Description description) {
			System.out.println("Starting test: " + description.getMethodName());
		}
	};


	@Test
	public void testJoinLeftNode() {
		System.out.println("testJoinLeftNode");
		Relation firstRelation=new Relation(new BinNode("4"),null);
		firstRelation.insertToLeft(new BinNode("3"));
		firstRelation.insertToRight(new BinNode("5"));
		System.out.println("first Relation");
		firstRelation.inorderBST();


		Relation secondRelation=new Relation(new BinNode("3"),null);
		BinNode myLeftNewNode6 = new BinNode("6");
		secondRelation.insertToLeft(myLeftNewNode6);
		BinNode myRightNewNode7 = new BinNode("7");
		secondRelation.insertToRight(myRightNewNode7);
		System.out.println("second Relation");
		secondRelation.inorderBST();

		Relation actualResult=firstRelation.join(secondRelation);
		System.out.println("result");
		actualResult.inorderBST();

		firstRelation.getLeftNode().setLeftBNode(myLeftNewNode6);
		firstRelation.getLeftNode().setLeftBNode(myRightNewNode7);
		assertEquals(firstRelation,actualResult);
	}


	@Test
	public void testJoinLeftRight() {
		System.out.println("testJoinRightNode");
		Relation firstRelation=new Relation(new BinNode("4"),null);
		firstRelation.insertToLeft(new BinNode("8"));
		firstRelation.insertToRight(new BinNode("3"));
		System.out.println("first Relation");
		firstRelation.inorderBST();


		Relation secondRelation=new Relation(new BinNode("3"),null);
		BinNode myLeftNewNode6 = new BinNode("6");
		secondRelation.insertToLeft(myLeftNewNode6);
		BinNode myRightNewNode7 = new BinNode("7");
		secondRelation.insertToRight(myRightNewNode7);
		System.out.println("second Relation");
		secondRelation.inorderBST();

		Relation actualResult=firstRelation.join(secondRelation);
		System.out.println("result");
		actualResult.inorderBST();

		firstRelation.getLeftNode().setLeftBNode(myLeftNewNode6);
		firstRelation.getLeftNode().setLeftBNode(myRightNewNode7);
		assertEquals(firstRelation,actualResult);
	}

	@Test
	public void testGetLeftRight() {
		System.out.println("testJoinRightNode");
		Relation firstRelation=new Relation(new BinNode("4"),null);
		firstRelation.insertToLeft(new BinNode("8"));
		firstRelation.insertToRight(new BinNode("3"));
		System.out.println("first Relation");
		firstRelation.inorderBST();


		Relation secondRelation=new Relation(new BinNode("3"),null);
		BinNode myLeftNewNode6 = new BinNode("6");
		secondRelation.insertToLeft(myLeftNewNode6);
		BinNode myRightNewNode7 = new BinNode("7");
		secondRelation.insertToRight(myRightNewNode7);
		System.out.println("second Relation");
		secondRelation.inorderBST();

		Relation actualResult=firstRelation.join(secondRelation);
		System.out.println("result");
		actualResult.inorderBST();

		firstRelation.getLeftNode().setLeftBNode(myLeftNewNode6);
		firstRelation.getLeftNode().setLeftBNode(myRightNewNode7);

	}

	@Test
	public void testSearch() {
		BinNode theBTRootNode = new BinNode("4");
		Relation firstRelation=new Relation(theBTRootNode,null);
		firstRelation.insertToLeft(new BinNode("8"));
		BinNode keyNode = new BinNode("3");
		firstRelation.insertToRight(keyNode);
		System.out.println("first Relation");
		firstRelation.inorderBST();


		Relation secondRelation=new Relation(keyNode,null);
		BinNode myLeftNewNode6 = new BinNode("6");
		secondRelation.insertToLeft(myLeftNewNode6);
		BinNode myRightNewNode7 = new BinNode("7");
		secondRelation.insertToRight(myRightNewNode7);
		System.out.println("second Relation");
		secondRelation.inorderBST();


		Relation actualResult=firstRelation.join(secondRelation);
		ArrayList<BinNode> results=new ArrayList<BinNode> ();
		actualResult.search(theBTRootNode, keyNode, results);
		System.out.print(results.get(0).getLocalVariableName());

	}



}
