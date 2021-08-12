package com.telit.money.start.bean;

public class XmlBean {
    private String area;
    private String name;
    private String fewWays;
    private String addre;
    private String url;
    private int id;
    private int port;


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    private int position;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFewWays() {
        return fewWays;
    }

    public void setFewWays(String fewWays) {
        this.fewWays = fewWays;
    }

    public String getAddre() {
        return addre;
    }

    public void setAddre(String addre) {
        this.addre = addre;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "XmlBean{" +
                "area='" + area + '\'' +
                ", name='" + name + '\'' +
                ", fewWays='" + fewWays + '\'' +
                ", addre='" + addre + '\'' +
                ", url='" + url + '\'' +
                ", id=" + id +
                ", position=" + position +
                '}';
    }
}
