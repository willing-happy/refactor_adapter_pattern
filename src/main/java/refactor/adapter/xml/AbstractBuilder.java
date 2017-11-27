package refactor.adapter.xml;

import java.util.Stack;

public abstract class AbstractBuilder implements OutputBuilder {
	static final protected String CANNOT_ADD_ABOVE_ROOT = "Cannot appendChild node above the root node.";
	static final protected String CANNOT_ADD_BESIDE_ROOT = "Cannot appendChild node beside the root node.";
	protected Stack history = new Stack();
	protected NodeAdapter rootAdapter;
	protected NodeAdapter parentAdapter;
	private NodeAdapter currentAdapter;

	public void addAbove(String uncle) {
		if (getCurrent() == getRoot())
			throw new RuntimeException(CANNOT_ADD_ABOVE_ROOT);
		history.pop();
		boolean atRootNode = (history.size() == 1);
		if (atRootNode)
			throw new RuntimeException(CANNOT_ADD_ABOVE_ROOT);
		history.pop();
		setCurrent((NodeAdapter) history.peek());
		addBelow(uncle);
	}

	public NodeAdapter getRoot() {
		return rootAdapter;
	}

	public NodeAdapter getCurrent() {
		return currentAdapter;
	}

	public void setCurrent(NodeAdapter current) {
		this.currentAdapter = current;
	}

	public void addGrandfather(String uncle) {
		if (getCurrent() == getRoot())
			throw new RuntimeException(CANNOT_ADD_ABOVE_ROOT);
		history.pop();
		boolean atRootNode = (history.size() == 1);
		if (atRootNode)
			throw new RuntimeException(CANNOT_ADD_ABOVE_ROOT);
		history.pop();
		history.pop();
		setCurrent((NodeAdapter) history.peek());
		addBelow(uncle);
	}

	public void addBelow(String child) {
		NodeAdapter childElement = createElement(child);
		getCurrent().appendChild(childElement);
		setParent(getCurrent());
		setCurrent(childElement);
		history.push(getCurrent());
	}

	public void addAttribute(String name, String value) {
		getCurrent().addAttribute(name, value);
	}

	public abstract NodeAdapter createElement(String child);

	public void setParent(NodeAdapter parent) {
		this.parentAdapter = parent;
	}

	public void addBeside(String sibling) {
		if (getCurrent() == getRoot())
			throw new RuntimeException(CANNOT_ADD_BESIDE_ROOT);
		NodeAdapter siblingElement = createElement(sibling);
		getParent().appendChild(siblingElement);
		setCurrent(siblingElement);
		history.pop();
		history.push(getCurrent());
	}

	public NodeAdapter getParent() {
		return parentAdapter;
	}

	public void setRoot(NodeAdapter root) {
		this.rootAdapter = root;
	}
}
