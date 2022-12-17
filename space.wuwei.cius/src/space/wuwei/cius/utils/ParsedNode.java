package space.wuwei.cius.utils;

import java.util.List;

import org.w3c.dom.Node;

public class ParsedNode {
	public Binding binding;
	public List<Node> nodes;
	
	ParsedNode(Binding a, List<Node> b) {
		binding = a;
		nodes = b;
	}
}