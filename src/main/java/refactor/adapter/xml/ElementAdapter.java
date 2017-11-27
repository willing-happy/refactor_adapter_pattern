package refactor.adapter.xml;


import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

/**
 * Created by willi on 2017/11/27.
 */
public class ElementAdapter implements NodeAdapter {

    private Element node;

    public ElementAdapter(Element element) {
        this.node = element;
    }

    public void addAttribute(String name, String value) {
        this.node.setAttribute(name, value);
    }

    public void appendChild(NodeAdapter childNode) {
        this.node.appendChild((Element) childNode.getNode());
    }

    public void appendChild(Text textNode) {
        this.node.appendChild(textNode);
    }

    public Object getNode() {
        return node;
    }

    public void addValue(Object value) {
        this.node.appendChild((Node) value);
    }

}
