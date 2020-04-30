package voronoi_diagram;

import stdlib.StdDraw;

import java.util.*;
import java.util.Map.Entry;

public class Voronoi {
	
	private static final double MAX_DIM = 10;
	private static final double MIN_DIM = -10;
	
	private double sweepLoc;
	
	private final ArrayList<Point> sites;
	private final ArrayList<VoronoiEdge> edgeList;
	private HashSet<BreakPoint> breakPoints;
	private TreeMap<ArcKey, CircleEvent> arcs;
	private TreeSet<Event> events;

	public Voronoi(ArrayList<Point> sites) {
		this(sites, false);
	}

	public Voronoi(ArrayList<Point> sites, boolean animate) {
		// initialize data structures;
		this.sites = sites;
		edgeList = new ArrayList<VoronoiEdge>(sites.size());
		events = new TreeSet<Event>();
		breakPoints = new HashSet<BreakPoint>();
		arcs = new TreeMap<ArcKey, CircleEvent>();

		for (Point site : sites) {
			events.add(new Event(site));
		}
		sweepLoc = MAX_DIM;
		do {
			Event cur = events.pollFirst();
			sweepLoc = cur.getPoint().getY();
			if (animate) this.draw();
			if (cur.getClass() == Event.class) {
				handleSiteEvent(cur);
			}
			else {
				CircleEvent ce = (CircleEvent) cur;
				handleCircleEvent(ce);
			}
		} while ((events.size() > 0));

		this.sweepLoc = MIN_DIM; // hack to draw negative infinite points
		for (BreakPoint bp : breakPoints) {
			bp.finish();
		}
	}
	
	public double getSweepLoc() {
		return sweepLoc;
	}

	private void handleSiteEvent(Event cur) {
		// Deal with first point case
		if (arcs.size() == 0) {
			arcs.put(new Arc(cur.getPoint(), this), null);
			return;
		}

		// Find the arc above the site
		Map.Entry<ArcKey, CircleEvent> arcEntryAbove = arcs.floorEntry(new ArcQuery(cur.getPoint()));
		Arc arcAbove = (Arc) arcEntryAbove.getKey();

		// Deal with the degenerate case where the first two points are at the same y value
		if (arcs.size() == 0 && arcAbove.getSite().getY() == cur.getPoint().getY()) {
			VoronoiEdge newEdge = new VoronoiEdge(arcAbove.getSite(), cur.getPoint());
			newEdge.p1 = new Point((cur.getPoint().getX() + arcAbove.getSite().getX())/2, Double.POSITIVE_INFINITY);
			BreakPoint newBreak = new BreakPoint(arcAbove.getSite(), cur.getPoint(), newEdge, false, this);
			breakPoints.add(newBreak);
			this.edgeList.add(newEdge);
			Arc arcLeft = new Arc(null, newBreak, this);
			Arc arcRight = new Arc(newBreak, null, this);
			arcs.remove(arcAbove);
			arcs.put(arcLeft, null);
			arcs.put(arcRight, null);
			return;
		}

		// Remove the circle event associated with this arc if there is one
		CircleEvent falseCE = arcEntryAbove.getValue();
		if (falseCE != null) {
			events.remove(falseCE);
		}

		BreakPoint breakL = arcAbove.getBreakPointLeft();
		BreakPoint breakR = arcAbove.getBreakPointRight();
		VoronoiEdge newEdge = new VoronoiEdge(arcAbove.getSite(), cur.getPoint());
		this.edgeList.add(newEdge);
		BreakPoint newBreakL = new BreakPoint(arcAbove.getSite(), cur.getPoint(), newEdge, true, this);
		BreakPoint newBreakR = new BreakPoint(cur.getPoint(), arcAbove.getSite(), newEdge, false, this);
		breakPoints.add(newBreakL);
		breakPoints.add(newBreakR);

		Arc arcLeft = new Arc(breakL, newBreakL, this);
		Arc center = new Arc(newBreakL, newBreakR, this);
		Arc arcRight = new Arc(newBreakR, breakR, this);

		arcs.remove(arcAbove);
		arcs.put(arcLeft, null);
		arcs.put(center, null);
		arcs.put(arcRight, null);

		checkForCircleEvent(arcLeft);
		checkForCircleEvent(arcRight);
	}

	private void handleCircleEvent(CircleEvent ce) {
		arcs.remove(ce.getArc());
		ce.getArc().getBreakPointLeft().finish(ce.getVertex());
		ce.getArc().getBreakPointRight().finish(ce.getVertex());
		breakPoints.remove(ce.getArc().getBreakPointLeft());
		breakPoints.remove(ce.getArc().getBreakPointRight());

		Entry<ArcKey, CircleEvent> entryRight = arcs.higherEntry(ce.getArc());
		Entry<ArcKey, CircleEvent> entryLeft = arcs.lowerEntry(ce.getArc());
		Arc arcRight = null;
		Arc arcLeft = null;

		Point ceArcLeft = ce.getArc().getLeftPoint();
		boolean cocircularJunction = ce.getArc().getRightPoint().equals(ceArcLeft);

		if (entryRight != null) {
			arcRight = (Arc) entryRight.getKey();
			while (cocircularJunction && arcRight.getRightPoint().equals(ceArcLeft)) {
				arcs.remove(arcRight);
				arcRight.getBreakPointLeft().finish(ce.getVertex());
				arcRight.getBreakPointRight().finish(ce.getVertex());
				breakPoints.remove(arcRight.getBreakPointLeft());
				breakPoints.remove(arcRight.getBreakPointRight());

				CircleEvent falseCe = entryRight.getValue();
				if (falseCe != null) {
					events.remove(falseCe);
				}

				entryRight = arcs.higherEntry(arcRight);
				arcRight = (Arc) entryRight.getKey();
			}

			CircleEvent falseCe = entryRight.getValue();
			if (falseCe != null) {
				events.remove(falseCe);
				arcs.put(arcRight, null);
			}
		}
		if (entryLeft != null) {
			arcLeft = (Arc) entryLeft.getKey();
			while (cocircularJunction && arcLeft.getLeftPoint().equals(ceArcLeft)) {
				arcs.remove(arcLeft);
				arcLeft.getBreakPointLeft().finish(ce.getVertex());
				arcLeft.getBreakPointRight().finish(ce.getVertex());
				breakPoints.remove(arcLeft.getBreakPointLeft());
				breakPoints.remove(arcLeft.getBreakPointRight());

				CircleEvent falseCe = entryLeft.getValue();
				if (falseCe != null) {
					events.remove(falseCe);
				}

				entryLeft = arcs.lowerEntry(arcLeft);
				arcLeft = (Arc) entryLeft.getKey();
			}

			CircleEvent falseCe = entryLeft.getValue();
			if (falseCe != null) {
				events.remove(falseCe);
				arcs.put(arcLeft, null);
			}
		}

		VoronoiEdge e = new VoronoiEdge(arcLeft.getBreakPointRight().getLeft(), arcRight.getBreakPointLeft().getRight());
		edgeList.add(e);

		// Here we're trying to figure out if the org.ajwerner.voronoi.Voronoi getVertex()ex
		// we've found is the left
		// or right point of the new edge.
		// If the edges being traces out by these two arcs take a right turn then we
		// know
		// that the getVertex()ex is going to be above the current point
		boolean turnsLeft = Point.compareTurnDirection(arcLeft.getBreakPointRight().getEdgeBegin(), ce.getPoint(), arcRight.getBreakPointLeft().getEdgeBegin()) == 1;
		// So if it turns left, we know the next getVertex()ex will be below this getVertex()ex
		// so if it's below and the slow is negative then this getVertex()ex is the left point
		boolean isLeftPoint = (turnsLeft) ? (e.m < 0) : (e.m > 0);
		if (isLeftPoint) {
			e.p1 = ce.getVertex();
		} else {
			e.p2 = ce.getVertex();
		}

		BreakPoint newBP = new BreakPoint(arcLeft.getBreakPointRight().getLeft(), arcRight.getBreakPointLeft().getRight(), e, !isLeftPoint, this);
		breakPoints.add(newBP);

		arcRight.setBreakPointLeft(newBP);
		arcLeft.setBreakPointRight(newBP);

		checkForCircleEvent(arcLeft);
		checkForCircleEvent(arcRight);
	}

	private void checkForCircleEvent(Arc a) {
		Point circleCenter = a.checkCircle();
		if (circleCenter != null) {
			double radius = a.getSite().distance(a.getSite(), circleCenter);
			Point circleEventPoint = new Point(circleCenter.getX(), circleCenter.getY() - radius);
			CircleEvent ce = new CircleEvent(a, circleEventPoint, circleCenter);
			arcs.put(a, ce);
			events.add(ce);
		}
	}

	public void show() {
		StdDraw.clear();
		for (Point p : sites) {
			p.draw(StdDraw.RED);
		}
		for (VoronoiEdge e : edgeList) {
			if (e.p1 != null && e.p2 != null) {
				double topY = (e.p1.getY() == Double.POSITIVE_INFINITY) ? MAX_DIM : e.p1.getY(); // HACK to draw from infinity
				StdDraw.line(e.p1.getX(), topY, e.p2.getX(), e.p2.getY());
			}
		}
		StdDraw.show();
	}

	public void draw() {
		StdDraw.clear();
		for (Point p : sites) {
			p.draw(StdDraw.RED);
		}
		for (BreakPoint bp : breakPoints) {
			bp.draw();
		}
		for (ArcKey a : arcs.keySet()) {
			((Arc) a).draw();
		}
		for (VoronoiEdge e : edgeList) {
			if (e.p1 != null && e.p2 != null) {
				double topY = (e.p1.getY() == Double.POSITIVE_INFINITY) ? MAX_DIM : e.p1.getY(); // HACK to draw from infinity
				StdDraw.line(e.p1.getX(), topY, e.p2.getX(), e.p2.getY());
			}
		}
		StdDraw.line(MIN_DIM, sweepLoc, MAX_DIM, sweepLoc);
		StdDraw.show(1);
	}
}
