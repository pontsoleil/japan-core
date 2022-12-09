package space.wuwei.cius.utils;

import org.w3c.dom.NodeList;

public class ParsedNode {
	public Binding binding;
	public NodeList nodes;
	
	ParsedNode(Binding a, NodeList b) {
		binding = a;
		nodes = b;
	}
}