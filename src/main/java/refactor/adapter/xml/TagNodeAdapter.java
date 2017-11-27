package refactor.adapter.xml;

import org.w3c.dom.Text;

/**
 * Created by willi on 2017/11/27.
 */
public class TagNodeAdapter implements NodeAdapter {
    private final TagNode node;

    public TagNodeAdapter(TagNode node) {
        this.node = node;
    }

    public void addAttribute(String name, String value) {
        this.node.addAttribute(name, value);
    }

    public void appendChild(NodeAdapter childNode) {
        this.node.add((TagNode) childNode.getNode());
    }

    public Object getNode() {
        return node;
    }

    public void addValue(Object value) {
        this.node.addValue((String) value);
    }

    @Override
    public String toString() {
        return this.node.toString();
    }
}
