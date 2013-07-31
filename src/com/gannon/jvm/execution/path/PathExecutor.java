package com.gannon.jvm.execution.path;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;
import com.gannon.jvm.data.dependency.Dependency;
import com.gannon.jvm.progam.path.Node;
import com.gannon.jvm.progam.path.PredicateNode;
import com.gannon.jvm.progam.path.TestPath;

public class PathExecutor<T> {
	private Queue potentialGoodReusltQueue=new LinkedList<Object>();

	public PathExecutor() {
		super();
	}

	public Object execute(PathFrame pathFrame) {
	
		
		Object result = null;
		TestPath path = pathFrame.getTestPath();
		//random generate input
		//potentialGoodReusltQueue.add(randomInput);
		//while (potentialGoodReusltQueue!=empty){
		boolean endOfPathFlag = false;
		//clear all ignore flag
		while (!endOfPathFlag) {
			//pathFrame.setLocalVariableTable(new BLocalVarTable(newInputfrom queue));
			// for each predicate node, i.e., the node contains a predicate
			// instruction (a>b), if the run-time predicate result does not
			// equal to the expected results, the program re-executes the
			// program, however, the predicate will be skipped to avoid
			// localized search
			for (Node node : path.getNodes()) {
				result = node.getInstruction().execute(pathFrame);

				// if reaching the end of the program, we have a potential good
				// input, the program stops and push the input to a
				// "potential good" input queue
				if (node.hasReturnInstruction()) {
					endOfPathFlag = true;
				} else if (node.isBPredicateNode()&& !((PredicateNode) node).isIgnore()) {
					((PredicateNode) node).setActualPredicateResult((Boolean) result);
				
					
					// if the actual predicate result is not equal to expected
					// results , we need to generate a new value to pass the
					// node and the predicate as ignored for next time execution
					if (!((PredicateNode) node).hasPassed() ) {
						// System.out.println((PredicateNode) node);
						// apply rules to get new input newA, newB, newC
						
						Dependencies ds;
						Dependency d=ds.findRelation(node.getInstruction());
						d.getTheBTRootNode();
						BinNode leftNode=d.getLeftNode();
						leftNode.g
						bin.
						
						int newA = 7, newB = 7, newC = 7;
						ArrayList<Object> newInput = new ArrayList<Object>();
						newInput.add(0);// reference
						newInput.add(newA);
						newInput.add(newB);
						newInput.add(newC);
						//potentialGoodReusltQueue.add(e);

						// set ignore flag in the node so next time to skip
						// RE-Evaluate the predicate instruction
						((PredicateNode) node).setIgnore(true);
						
						//remove one item from the queue and set the new input
						//newInput=potentialGoodReusltQueue.remove();
				

						// quit the current execution and re-run the path with
						// new inputs
						break;
					}

				}
			}
		}
		
		//execute the methodJVM to see if get the same path, if so save the result
		
	   //}
		return result;
	}
}
