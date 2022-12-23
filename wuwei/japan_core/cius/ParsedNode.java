package wuwei.japan_core.cius;

import java.util.List;

import org.w3c.dom.Node;

/**
 * x
 *
 */
public class ParsedNode {
	public Binding binding;
	public List<Node> nodes;
	
	/**
	 * x
	 * 
	 * @param a
	 * @param b
	 */
	ParsedNode(Binding a, List<Node> b) {
		binding = a;
		nodes = b;
	}
}