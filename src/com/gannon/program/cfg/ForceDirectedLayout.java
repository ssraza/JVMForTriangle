/*  Copyright (c) 2010 Xiaoyun Zhu
 * 
 *  Permission is hereby granted, free of charge, to any person obtaining a copy  
 *  of this software and associated documentation files (the "Software"), to deal  
 *  in the Software without restriction, including without limitation the rights  
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell  
 *  copies of the Software, and to permit persons to whom the Software is  
 *  furnished to do so, subject to the following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in  
 *  all copies or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR  
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,  
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE  
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER  
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,  
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN  
 *  THE SOFTWARE.  
 */
package com.gannon.program.cfg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * See https://github.com/dhotson/springy
 * 
 * @author x_kez
 * 
 */
public final class ForceDirectedLayout implements NodeRemovedListener, EdgeRemovedListener {
    public static final double MINIMUM_SPRING_LENGTH = 1.5;
    public static final double MAXIMUM_SPRING_LENGTH = 1.0;
    private final Graph graph;
    // spring stiffness constant
    private final double stiffness;
    // repulsion constant
    private final double repulsion;
    // velocity damping factor
    private final double damping;
    private final Map<Node, Point> nodePoints;
    private final Map<Edge, Spring> edgeSprings;
    private final GraphRenderer renderer;
    private   double g = 9.81f;  // Gravity force
    
    public ForceDirectedLayout() {
        this(new Graph());
    }

    public ForceDirectedLayout(Graph graph) {
        this(graph, new HashMap<Node, Point>(), new HashMap<Edge, Spring>());
    }

    public ForceDirectedLayout(final Graph graph, final Map<Node, Point> nodePoints, final Map<Edge, Spring> edgeSprings) {
        this(graph, nodePoints, edgeSprings, 500.0, 500.0, 0.5);
    }

    public ForceDirectedLayout(final Graph graph, final Map<Node, Point> nodePoints,
            final Map<Edge, Spring> edgeSprings, final double stiffness, final double repulsion, final double damping) {
        this.graph = graph;
        this.stiffness = stiffness;
        this.repulsion = repulsion;
        this.damping = damping;
        this.nodePoints = nodePoints;
        this.edgeSprings = edgeSprings;
        this.graph.setNodeRemovedListener(this);
        this.graph.setEdgeRemovedListener(this);
        this.renderer = new GraphRenderer(this);
    }

    public synchronized Edge addEdge(Node node, Node otherNode, EdgeData edgeData) {
        return this.graph.newEdge(node, otherNode, edgeData);
    }

    public synchronized Node addNode(NodeData nodeData) {
    	return this.graph.newNode(nodeData);
    }

    public void applyCoulombsLaw() {
        for (Node n1 : this.graph.getNodes()) {
        	
            final Point p1 = getPoint(n1);
            for (Node n2 : this.graph.getNodes()) {
                final Point p2 = getPoint(n2);
                if (p1 != p2 ) {
                    final Vector d = Vector.subtract(p1.getPosition(), p2.getPosition());
                    // avoid massive forces at small distances (and divide by zero)
                    final double distance = d.magnitude() + 0.1;
                    
                    if(n1.getData().getIsInvicible() || n2.getData().getIsInvicible()){
                        // apply force to each end point
                        d.normalize().multiply(this.repulsion/10).divide(distance * distance);
                        p1.applyForce(Vector.multiply(d, 0.5));
                        p2.applyForce(d.multiply(-0.5));
                    }
                    else  if(n1.getData().getIsInvicible() && n2.getData().getIsInvicible()){
                        // apply force to each end point
                        d.normalize().multiply(this.repulsion/10).divide(distance * distance);
                    }else{
                        // apply force to each end point
                        d.normalize().multiply(this.repulsion).divide(distance * distance);
                        p1.applyForce(Vector.multiply(d, 0.5));
                        p2.applyForce(d.multiply(-0.5));
                    }
                }
            }
        }
    }

    public void applyHookesLaw() {
        //int i=0;
        for (Edge e : this.graph.getEdges()) {
            final Spring spring = getSpring(e);
            final Vector d = Vector.subtract(spring.getPoint2().getPosition(), spring.getPoint1().getPosition());
            final double displacement = spring.getLength() - d.magnitude();
            // apply force to each end point
            d.normalize().multiply(spring.getSpringStiffness() * displacement);
            
            spring.getPoint1().applyForce(Vector.multiply(d, -0.5));
            spring.getPoint2().applyForce(d.multiply(0.5));
         
            //System.out.println("length between " + e.getSource().getData().getLabel() + " and " + e.getTarget().getData().getLabel() );
            spring.updateCurrentLength();
        }
    }
    


    public void attractToCentre() {
        for (Node n : this.graph.getNodes()) {
            final Point p = getPoint(n);
            Point k = new Point(new Vector(-22.0, 2.0), 0);
            final Vector direction = Vector.multiply(k.getPosition(), -1.0);
            p.applyForce(direction.multiply(this.repulsion / 10.0));
        }
    }
    
    public void setGravity(double gravity){
        this.g = gravity;
    }

    public double getGravityForce()
    {
       return this.g;
    }

    public void applyGravityForce() {
        for (Node n : this.graph.getNodes()) {
        	if(n.getData().getIsRoot() || n.getData().getIsInvicible()){
        		continue; // skip node
        	}
            final Point p1 = getPoint(n);
            // getting earth point which should be downwar from any node
            final Point earthPoint = new Point(new Vector(p1.getPosition().getX(),p1.getPosition().getY()+10), 10);
            final Vector d = Vector.subtract(earthPoint.getPosition(), p1.getPosition());

            p1.applyForce(d.multiply(getGravityForce()));
        }
    }

    public void eachEdge(EdgeCallback callback) {
        final ForceDirectedLayout layout = this;
        for (Edge e : this.graph.getEdges()) {
            callback.call(e, layout.getSpring(e));
        }
    }

    public void eachNode(NodeCallback callback) {
        final ForceDirectedLayout layout = this;
        for (Node n : this.graph.getNodes()) {
            callback.call(n, layout.getPoint(n));
        }
    }

    public void eachSpring(SpringCallback callback) {
        final ForceDirectedLayout layout = this;
        for (Edge e : this.graph.getEdges()) {
            callback.call(layout.getSpring(e));
        }
    }

    /**
     * 
     * @return returns [bottomleft, topright]
     */
    public BoundingBox getBoundingBox() {
        final Vector bottomLeft = new Vector(-2, -2);
        final Vector topRight = new Vector(2, 2);
        
        double tmp;
        for (Node n : this.graph.getNodes()) {
            final Point p = getPoint(n);
            tmp = p.getPosition().getX();
            if (tmp < bottomLeft.getX()) {
                bottomLeft.setX(tmp);
            }
            if (tmp > topRight.getX()) {
                topRight.setX(tmp);
            }
            tmp = p.getPosition().getY();
            if (tmp < bottomLeft.getY()) {
                bottomLeft.setY(tmp);
            }
            if (tmp > topRight.getY()) {
                topRight.setY(tmp);
            }
        }

        final Vector padding = Vector.subtract(topRight, bottomLeft).multiply(0.07); // ~5% padding

        return new BoundingBox(bottomLeft.substract(padding), topRight.add(padding));
    }

    /**
     * @return the damping
     */
    public final double getDamping() {
        return this.damping;
    }

    /**
     * @return the edgeSprings
     */
    public final Map<Edge, Spring> getEdgeSprings() {
        return this.edgeSprings;
    }

    /**
     * @return the graph
     */
    public final Graph getGraph() {
        return this.graph;
    }

    /**
     * @return the nodePoints
     */
    public final Map<Node, Point> getNodePoints() {
        return this.nodePoints;
    }

    public Point getPoint(final Node node) {
        Point point = this.nodePoints.get(node);
        if (point == null) {
            point = new Point(Vector.random(), node.getData().getMass());
            this.nodePoints.put(node, point);
        }
        return point;
    }

    /**
     * @return the repulsion
     */
    public final double getRepulsion() {
        return this.repulsion;
    }

    public Spring getSpring(final Edge edge) {
        Spring spring = this.edgeSprings.get(edge);
        if (spring == null) {
            Spring existingSpring = null;
            final List<Edge> from = this.graph.getEdges(edge.getSource(), edge.getTarget());
            for (Edge e : from) {
                if (null != (existingSpring = this.edgeSprings.get(edge))) {
                    break;
                }
            }
            if (existingSpring != null) {
                spring = new Spring(existingSpring.getPoint1(), existingSpring.getPoint2(), 0d, 0d);
            } else {
                final List<Edge> to = this.graph.getEdges(edge.getTarget(), edge.getSource());
                for (Edge e : to) {
                    if (null != (existingSpring = this.edgeSprings.get(edge))) {
                        break;
                    }
                }
                
                if (existingSpring != null) {
                    spring = new Spring(existingSpring.getPoint2(), existingSpring.getPoint1(), 0d, 0d);
                } else {
                         spring = new Spring(getPoint(edge.getSource()), getPoint(edge.getTarget()), edge.getData()
                            .getLength(),
                            this.stiffness);
              
                    
                }
            }
            this.edgeSprings.put(edge, spring);
        }
        return spring;
    }

    /**
     * @return the stiffness
     */
    public final double getStiffness() {
        return this.stiffness;
    }

    public double getTotalEnergy() {
        double energy = 0d;
        for (Node n : this.graph.getNodes()) {
            final Point p = getPoint(n);
            final double speed = p.getVelocity().magnitude();
            energy += 0.5 * p.getMass() * speed * speed;
        }
        return energy;
    }

    public final boolean isRendering() {
        return this.renderer.isRendering();
    }

    public DistanceResult nearest(final Vector position) {
        DistanceResult min = new DistanceResult();
        for (Node n : this.graph.getNodes()) {
            final Point p = getPoint(n);
            final double distance = Vector.subtract(p.getPosition(), position).magnitude();
            if (min.getDistance() == -1 || distance < min.getDistance()) {
                min.update(n, p, distance);
            }
        }
        return min;
    }

    @Override
    public void onRemoved(Edge edge) {
        final Spring spring = this.edgeSprings.remove(edge);
        if (spring != null) {
            Spring.remove(spring);
        }
    }

    @Override
    public void onRemoved(Node node) {
        final Point point = this.nodePoints.remove(node);
        if (point != null) {
            Point.remove(point);
        }
    }

    public synchronized void removeEdge(Edge edge) {
        this.graph.removeEdge(edge);
    }

    public synchronized void removeNode(Node node) {
        this.graph.removeNode(node);
    }

    public void startRendering(final long interval, final LayoutRenderListener rendererListener,
            final GraphFinishedListener finishedListener, final BoundingBox currentBB) {
        this.renderer.start(interval, rendererListener, finishedListener, currentBB);
    }

    public void stopRendering() {
        this.renderer.stop();
    }

    public void updatePosition(final double timeStep) {
        for (Node n : this.graph.getNodes()) {
        	if(n.getData().getIsRoot()){
        		continue; // skip node
        	}
            final Point p = getPoint(n);
            p.getPosition().add(Vector.multiply(p.getVelocity(), timeStep));
        }
    }

    public void updateVelocity(final double timeStep) {
        for (Node n : this.graph.getNodes()) {
            
            final Point p = getPoint(n);
            p.getVelocity().add(p.getForce().multiply(timeStep)).multiply(this.damping);
            p.getForce().clear();
        }
    }
   

}
