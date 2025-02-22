package jp.nagua.mobile_order.interfaces;

public interface ContentHandler {

    public void addContent(Object object);

    public void removeContent(Object object);

    public Object getContent(String string);

    public Object getContent(int id);

    public Object getContents();

    public boolean contains(Object object);
}
