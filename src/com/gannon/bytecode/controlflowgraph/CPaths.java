package com.gannon.bytecode.controlflowgraph;

import java.util.ArrayList;
import java.util.Iterator;

public class CPaths {
	private final int id;
	private String coverageName;
	private ArrayList<CPath> paths = new ArrayList<CPath>();

	public CPaths(int id) {
		super();
		this.id = id;
	}

	public String getCoverageName() {
		return coverageName;
	}

	public void setCoverageName(String coverageName) {
		this.coverageName = coverageName;
	}

	public ArrayList<CPath> getPaths() {
		return paths;
	}

	public void setPaths(ArrayList<CPath> paths) {
		this.paths = paths;
	}

	public int getId() {
		return id;
	}

	public void add(CPath path) {
		paths.add(path);
	}

	public int size() {
		return paths.size();
	}

	public CPath findPath(int pathID) {
		for (CPath path : paths) {
			if (path.getId() == pathID) {
				return path;
			}
		}
		return null;
	}

	public CPath getLongestPath() {
		int maxLength = -1;
		CPath resultPath = null;
		for (CPath p : paths) {
			if (p.size() > maxLength) {
				maxLength = p.size();
				resultPath = p;
			}
		}
		return resultPath;
	}

	public Iterator<CPath> iterator() {
		return paths.iterator();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coverageName == null) ? 0 : coverageName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CPaths other = (CPaths) obj;
		if (coverageName == null) {
			if (other.coverageName != null)
				return false;
		} else if (!coverageName.equals(other.coverageName))
			return false;
		return true;
	}

}
