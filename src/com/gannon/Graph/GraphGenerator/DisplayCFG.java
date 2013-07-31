package com.gannon.Graph.GraphGenerator;

import java.awt.Dimension;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.text.Position;

import com.gannon.bytecode.controlflowgraph.Graph;

import edu.uci.ics.jung.graph.DirectedSparseGraph;

public class DisplayCFG {
    
    public DisplayCFG(){       
    }
    
    public void draw(Graph graph){
        DirectedSparseGraph g = new DirectedSparseGraph();

        for(int i=0;i<graph.getNumberOfNodes();i++){
            String vertex = graph.getNodes().get(i).getOwner() + graph.getNodes().get(i).getId();
            g.addVertex(vertex);           
        }     

        int edgeCount = 0;
        for(int i=0;i<graph.getNumberOfEdges();i++){
            String startVertex = graph.getEdges().get(i).getNodeS().getOwner() + graph.getEdges().get(i).getNodeS().getId();
            String endVertex = graph.getEdges().get(i).getNodeE().getOwner() + graph.getEdges().get(i).getNodeE().getId();
            g.addEdge(edgeCount, startVertex, endVertex);
            edgeCount++;         
        }
        VisualizationImageServer vs = new VisualizationImageServer(new CircleLayout(g),new Dimension(700, 600));     
        JFrame frame = new JFrame();
        
        vs.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());       
        vs.getRenderContext().setVertexShapeTransformer(new ConstantTransformer(new Ellipse2D.Float(-24,-24,48,48)));
        vs.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
        
        frame.getContentPane().add(vs);    
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        frame.pack();    
        frame.setVisible(true);
    }
}
