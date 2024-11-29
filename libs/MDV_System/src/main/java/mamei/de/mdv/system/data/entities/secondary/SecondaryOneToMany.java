package mamei.de.mdv.system.data.entities.secondary;

import java.util.List;

public class SecondaryOneToMany {

    private ISecondary parent;
    private List<ISecondary> children;
    private String childIdentifier;
    private String parentIdentifier;

    public SecondaryOneToMany(ISecondary parent, List<ISecondary> children, String childIdentifier, String parentIdentifier) {
        this.parent = parent;
        this.children = children;
        this.childIdentifier = childIdentifier;
        this.parentIdentifier = parentIdentifier;
    }

    public void addChildren(ISecondary child) {
        children.add(child);
    }

    public void addChildren(List<ISecondary> children) {
        this.children.addAll(children);
    }

    public void removeChild(ISecondary child) {
        children.remove(child);
    }

    public void removeChildren(List<ISecondary> children) {
        this.children.removeAll(children);
    }

    public void setParent(ISecondary parent) {
        this.parent = parent;
    }

    public ISecondary getParent() {
        return parent;
    }

    public List<ISecondary> getChildren() {
        return children;
    }
}
