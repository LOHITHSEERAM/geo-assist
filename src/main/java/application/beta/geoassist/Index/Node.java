package application.beta.geoassist.Index;


import application.beta.geoassist.pojo.GeoType;
import application.beta.geoassist.util.Shapes.BBox;

import java.util.ArrayList;
import java.util.List;

public class Node {

    Node[] childNodes = new Node[4];

    Node parent;
    boolean isLeaf;


    Node[] neighbourNodes = new Node[6];
    BBox bbox = new BBox(0, 0, 0, 0);

    List<GeoType> list;

    public Node(boolean isLeaf, BBox bbo, Node parent) {
        this.isLeaf = isLeaf;
        this.bbox.minLat = bbo.minLat;
        this.bbox.maxLat = bbo.maxLat;
        this.bbox.minLon = bbo.minLon;
        this.bbox.maxLon = bbo.maxLon;
        this.list = new ArrayList<>();
        this.parent = parent;
    }
}
