package com.luuso.cafecode.model;

/**
 * @project： CafeCode
 * @package： com.luuso.cafecode.model
 * @class: Event
 * @author: 陆伟
 * @Date: 2017/8/27 22:51
 * @desc： TODO
 */

public class Event {
    private String action;
    private Object object;
    public int arg;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public <T extends Object> T getObject() {
        return (T) object;
    }

    public void setObject(Object obj) {
        this.object = obj;
    }


    @Override
    public String toString() {
        return "Event{" +
                "action='" + action + '\'' +
                ", arg=" + arg +
                ", object=" + object +
                '}';
    }

//    @Override
//    public String toString() {
//
//        return "Event{" + System.lineSeparator() +
//                "\t action='" + action + "\'," + System.lineSeparator() +
//                "\t arg=" + arg + System.lineSeparator() +
//                "\t object=" + object + '}';
//    }


}
