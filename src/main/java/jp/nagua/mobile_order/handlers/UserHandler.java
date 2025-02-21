package jp.nagua.mobile_order.handlers;

import jp.nagua.mobile_order.elements.User;
import jp.nagua.mobile_order.interfaces.ContentHandler;

import java.util.ArrayList;

public class UserHandler implements ContentHandler {

    private static ArrayList<User> users;

    private static UserHandler handler;

    public static UserHandler getInstance() {
        if(handler == null) {
            handler = new UserHandler();
            return handler;
        }
        return handler;
    }

    @Override
    public void initializeContentsList() {
        users = new ArrayList<>();
    }

    @Override
    public void addContentToList(Object object) {
        users.add((User) object);
    }

    @Override
    public void removeContentFromList(Object object) {
        users.remove((User) object);
    }

    @Override
    public Object getContentFromList(String string) {
        for(User user : users) {
            if(user.getName().equals(string)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Object getContentFromList(int id) {
        for(User user : users) {
            if(user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Object getContents() {
        return users.clone();
    }

    @Override
    public boolean containsContent(Object object) {
        if(users.contains(object)) {
            return true;
        }
        return false;
    }

    public boolean checkToken(String string) {
        for(User user : users) {
            if(user.getToken().equals(string)) {
                return true;
            }
        }
        return false;
    }
}
