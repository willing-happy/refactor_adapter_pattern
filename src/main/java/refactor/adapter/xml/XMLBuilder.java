package refactor.adapter.xml;

import java.util.Stack;

public class XMLBuilder extends AbstractBuilder {

	public XMLBuilder(String rootName) {
		init(rootName);
	}

	@Override
	public NodeAdapter createElement(String child) {
		TagNode childNode = new TagNode(child);
		return new TagNodeAdapter(childNode);
	}

	public void addValue(String value) {
		getCurrent().addValue(value);
	}

	protected void init(String rootName) {
		setRoot(createElement(rootName));
		setCurrent(getRoot());
		setParent(getRoot());
		history = new Stack();
		history.push(getCurrent());
	}

	public void startNewBuild(String rootName) {
		init(rootName);
	}

	public String toString() {
		return getRoot().toString();
	}

}
