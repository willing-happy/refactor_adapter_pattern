package refactor.adapter.xml;

import org.w3c.dom.Text;

/**
 * Created by willi on 2017/11/27.
 */
public interface NodeAdapter {
    void addAttribute(String name, String value);

    void appendChild(NodeAdapter childNode);

    Object getNode();

    void addValue(Object value);
}
