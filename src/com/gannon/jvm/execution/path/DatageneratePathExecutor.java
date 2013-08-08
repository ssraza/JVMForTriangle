package com.gannon.jvm.execution.path;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Queue;
import java.util.Random;

import com.gannon.asm.classgenerator.BClassGenerator;
import com.gannon.asm.components.BClass;
import com.gannon.asm.components.BMethod;
import com.gannon.jvm.data.dependency.BinNode;
import com.gannon.jvm.data.dependency.Dependencies;
import com.gannon.jvm.data.dependency.Dependency;
import com.gannon.jvm.data.dependency.GannonPredicateTreeBuilderJVM;
import com.gannon.jvm.execution.method.BLocalVarTable;
import com.gannon.jvm.execution.method.GannonMethodJVM;
import com.gannon.jvm.instructions.BIFicmpeq;
import com.gannon.jvm.instructions.BIFicmpge;
import com.gannon.jvm.instructions.BIFicmpne;
import com.gannon.jvm.instructions.BInstruction;
import com.gannon.jvm.progam.path.Node;
import com.gannon.jvm.progam.path.PredicateNode;
import com.gannon.jvm.progam.path.TestPath;
import com.gannon.jvm.utilities.ConstantsUtility;
import com.gannon.jvm.utilities.TrianglePathBuilderUtility;
import com.gannon.rule.InputObject;
import com.gannon.rule.Rule;
import com.gannon.rule.RuleIFcmpeq;
import com.gannon.rule.RuleIFcmpge;
import com.gannon.rule.RuleIFcmpne;

public class DatageneratePathExecutor<T> {
	private Queue potentialGoodReusltQueue = new LinkedList<InputObject>();
	private Queue resultQueue = new LinkedList<InputObject>();

	public ArrayList<Object> inputs;

	public DatageneratePathExecutor(ArrayList<Object> inputs) {
		super();
		this.inputs = inputs;
	}

	public Object execute(PathFrame pathFrame) {

		Object result = null;
		TestPath path = pathFrame.getTestPath();
		int count = 0;
		// random generate input
		// potentialGoodReusltQueue.add(randomInput);
		potentialGoodReusltQueue.add(ArrayListToInputObject());
		while (!potentialGoodReusltQueue.isEmpty()) {
			Iterator it = potentialGoodReusltQueue.iterator();
//			System.out.println("-------------------------");
//			while(it.hasNext()){
//				InputObject testObj = (InputObject) it.next();
//				testObj.printData();
//			}
			path.clearIgnoreFlags();
			InputObject input = (InputObject)potentialGoodReusltQueue.poll();
			BLocalVarTable vt=new BLocalVarTable();
			ArrayList<Object> vars = InputObjectToArrayList(input);
			for(Object o:vars){
				vt.add(o);
			}
			pathFrame.setLocalVariableTable(vt);
			
			boolean endOfPathFlag = false;
			// clear all ignore flag
			while (!endOfPathFlag) {
				// pathFrame.setLocalVariableTable(new
				// BLocalVarTable(newInputfrom
				// queue));
				// for each predicate node, i.e., the node contains a predicate
				// instruction (a>b), if the run-time predicate result does not
				// equal to the expected results, the program re-executes the
				// program, however, the predicate will be skipped to avoid
				// localized search

				for (Node node : path.getNodes()) {
					GannonPredicateTreeBuilderJVM jvm = new GannonPredicateTreeBuilderJVM();
					jvm.run(path, InputObjectToArrayList(input));
					Dependencies dps = jvm.getRelationFrame().getRelations();
					// System.out.println(node.getInstruction().toString());
					// Refresh PathFrame
					result = node.getInstruction().execute(pathFrame);
					// System.out.println("test");
					// if reaching the end of the program, we have a potential
					// good
					// input, the program stops and push the input to a
					// "potential good" input queue
					if (node.hasReturnInstruction()) {
						endOfPathFlag = true;
						if(potentialGoodReusltQueue.isEmpty()) {
							potentialGoodReusltQueue.add(randomGeneration(3));
						}
						
						break;
					} else if (node.isBPredicateNode() && !((PredicateNode) node).isIgnore()) {
						((PredicateNode) node)
								.setActualPredicateResult((Boolean) result.equals(true) ? ConstantsUtility.EXPECTED_TRUE
										: ConstantsUtility.EXPECTED_FALSE);
						//System.out.println("predicate");

						// if the actual predicate result is not equal to
						// expected
						// results , we need to generate a new value to pass the
						// node and the predicate as ignored for next time
						// execution
						if (!((PredicateNode) node).hasPassed()&& ((PredicateNode) node).isIgnore()!=true) {
							// System.out.println((PredicateNode) node);
							// apply rules to get new input newA, newB, newC
							// System.out.println("passed " +
							// potentialGoodReusltQueue.size());
							// ArrayListToInputObject().printData();
							// System.out.println("---------------");
							// if (potentialGoodReusltQueue.size() <= 0) {
							// potentialGoodReusltQueue.add(ArrayListToInputObject());
							// }

							BInstruction instruction = node.getInstruction();
							Dependency dp = dps.findRelation(instruction);

							// dp.getTheBTRootNode();
							BinNode leftNode = dp.getLeftNode();
							BinNode rightNode = dp.getRightNode();
							// System.out.println("Instruction:" +
							// instruction.toString());
							// int size = potentialGoodReusltQueue.size();
							// int i = 0;

							// while (!potentialGoodReusltQueue.isEmpty() && i <
							// size) {
							// Iterator<InputObject> listIterator =
							// potentialGoodReusltQueue.iterator();
							// InputObject obj = listIterator.next();
							//
							ArrayList<InputObject> newDataList = new ArrayList<InputObject>();
							Rule rule = null;
							//
							if (instruction instanceof BIFicmpge) {
								rule = new RuleIFcmpge(((PredicateNode) node).getExpectedPredicateResult() > 0 ? true :false, input, dps, leftNode, rightNode, newDataList);
							} else if (instruction instanceof BIFicmpne) {
								rule = new RuleIFcmpne(((PredicateNode) node).getExpectedPredicateResult() > 0 ? true :false , input, dps, leftNode, rightNode, newDataList);
							} else if (instruction instanceof BIFicmpeq) {
								rule = new RuleIFcmpeq(((PredicateNode) node).getExpectedPredicateResult() > 0 ? true :false, input, dps, leftNode, rightNode, newDataList);
							}
							rule.dataGeneration();
							ArrayList<InputObject> tmpResultList = rule.getNewDataList();

							// System.out.println(tmpResultList.size());
							// put new generated results to queue
							for (int j = 0; j < tmpResultList.size(); j++) {
								tmpResultList.get(j).printData();
								potentialGoodReusltQueue.add(tmpResultList.get(j));
							}
							// System.out.println("-------------------");
							// if (tmpResultList.size() > 0)
							// potentialGoodReusltQueue.remove();

							// System.out.println(i);
							// i++;
						

							// }
							// potentialGoodReusltQueue.add(e);

							// set ignore flag in the node so next time to skip
							// RE-Evaluate the predicate instruction
							 ((PredicateNode) node).setIgnore(true);

							// remove one item from the queue and set the new
							// input
							// newInput=potentialGoodReusltQueue.remove();

							// quit the current execution and re-run the path
							// with
							// new inputs
							break;
						}
					}
				}
			}
			
			
			// get Method instructions
			
			BClass myclass = BClassGenerator.getBClass("Triangle.class");
			BMethod m = myclass.getMethod("triangleType");
			
			GannonMethodJVM jvm = new GannonMethodJVM();
			jvm.run(myclass, m,vars);
			jvm.getExecutedPath();
			TestPath resultPath=jvm.getExecutedPath();
			System.out.println("Test Data");
			//input.printData();
			//System.out.println("Result Path"+resultPath);
			//System.out.println("Path "+path);
			
			if(resultPath.equals(path)&& count < 10){
				System.out.println(count+" "+vars.get(1)+","+vars.get(2)+","+vars.get(3));
				count++;
				//break;
			}
			if(count >=10)
				break;
			

		

		// execute the methodJVM to see if get the same path, if so save the
		// result

		 }
		return result;
	}

	private InputObject randomGeneration(int numOfParam){
		System.out.println("randomGeneration:");
		HashMap<String, Integer> map = new HashMap<String,Integer>();
		Random rand = new Random();
		for(int i=1;i<=numOfParam;i++){
			int value = rand.nextInt(200);
			String name= "i"+i;
			map.put(name, value);
			System.out.print(name+":"+value+" ");
		}
		InputObject newObj = new InputObject(map);
		System.out.println();
		return newObj;
	}
	
	private ArrayList<Object> InputObjectToArrayList(InputObject obj) {
		HashMap<String, Integer> map = obj.getInputDataTable();
		ArrayList<Object> input = new ArrayList<Object>();
		input.add(-1);
		input.add(-1);
		input.add(-1);
		input.add(-1);
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pairs = (Map.Entry) it.next();
			String name = pairs.getKey().toString();
			int index = Integer.parseInt(name.substring(1));
			input.set(index, pairs.getValue());
		}
		return input;
	}

	private InputObject ArrayListToInputObject() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();

		for (int i = 0; i < this.inputs.size(); i++) {
			if (i == 0)
				continue;
			String name = "i" + i;
			int value = (Integer) this.inputs.get(i);
			map.put(name, value);
		}
		InputObject newObj = new InputObject(map);
		newObj.printData();
		return newObj;
	}

}
