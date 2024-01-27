package application.beta.geoassist.Index;


import application.beta.geoassist.pojo.GeoType;
import application.beta.geoassist.util.Shapes.BBox;
import application.beta.geoassist.util.Shapes.Circle;
import org.antlr.v4.runtime.misc.NotNull;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;


public class QuadTree {


    int capacity;

    int parts;

    double deltaX;

    double deltaY;

    Node root;

    public QuadTree(int capacity, BBox bbo, int parts) {

        this.capacity = capacity;
        root = new Node(true, bbo, null);
        deltaY = (root.bbox.maxLat - root.bbox.minLat) / parts;
        deltaX = (root.bbox.maxLon - root.bbox.minLon) / parts;
    }

    public Node insert(Node node, GeoType at) {
        if (node.isLeaf) {
            if (node.list.size() < capacity)
                node.list.add(at);
            else {
                node.isLeaf = false;
                Node child = getNode(node, at);
                child.list.add(at);
                for (int i = 0; i < node.list.size(); i++) {
                    Node c = getNode(node, node.list.get(i));
                    c.list.add(node.list.get(i));
                }
            }
        } else {
            Node child = getNode(node, at);
            insert(child, at);
        }
        return null;
    }

    public Node getNode(Node parent, GeoType at) {

        double latMid = (parent.bbox.minLat + parent.bbox.maxLat) / 2;
        double lonMid = (parent.bbox.maxLon + parent.bbox.minLon) / 2;


        if (at.getLongitude() >= lonMid && at.getLatitude() >= latMid) {

            if (parent.childNodes[0] == null)
                parent.childNodes[0] = new Node(true, new BBox(lonMid, parent.bbox.maxLon, latMid, parent.bbox.maxLat), parent);

            return parent.childNodes[0];
        } else if (at.getLongitude() >= lonMid && at.getLatitude() < latMid) {

            if (parent.childNodes[2] == null)
                parent.childNodes[2] = new Node(true, new BBox(lonMid, parent.bbox.maxLon, parent.bbox.minLat, latMid), parent);

            return parent.childNodes[2];
        } else if (at.getLongitude() < lonMid && at.getLatitude() >= latMid) {

            if (parent.childNodes[1] == null)
                parent.childNodes[1] = new Node(true, new BBox(parent.bbox.minLon, lonMid, latMid, parent.bbox.maxLat), parent);

            return parent.childNodes[1];
        } else {

            if (parent.childNodes[3] == null)
                parent.childNodes[3] = new Node(true, new BBox(parent.bbox.minLon, lonMid, parent.bbox.minLat, latMid), parent);

            return parent.childNodes[3];
        }
    }

    public Node insert(@NotNull GeoType at) {
        return insert(root, at);
    }

    public List<GeoType> getNearByWithoutRange(Node node, GeoType at) {

        if (node.isLeaf)
            return node.list;

        return getNearByWithoutRange(getNode(node, at), at);
    }

    public List<GeoType> getNearByWithRange(GeoType at, Circle s) {
        ArrayList<GeoType> nearByList = new ArrayList<>();
        Deque<Node> q = new ArrayDeque();
        BBox CircleBbox = s.bbox;
        q.addLast(root);

        while (q.size() != 0) {

            Node node = q.pollFirst();
            if (node.isLeaf) {
                for (int j = 0; j < node.list.size(); j++) {
                    if (s.contains(node.list.get(j).getLatitude(), node.list.get(j).getLongitude()))
                        nearByList.add(node.list.get(j));
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    if (node.childNodes[i] != null) {
                        if (node.childNodes[i].bbox.contains(CircleBbox)) {
                            q.addLast(node.childNodes[i]);
                            break;
                        } else if (s.contains(node.childNodes[i].bbox) || s.intersects(node.childNodes[i].bbox)) {
                            q.addLast(node.childNodes[i]);
                        }
                    }
                }
            }
        }
        return nearByList;
    }

    public List<GeoType> getNearByWithoutRange(GeoType eat) {
        return getNearByWithoutRange(root, eat);
    }
}
