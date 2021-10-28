package com.telit.money.start.bean;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;

import java.util.List;

@Entity
public class AdviceBean  {
    @Id
    private String typeId;
    private String name;
    private String road;

    private String adress;

    private String area;

    private boolean isOpen;

    @Convert(columnType = String.class, converter = AnswerItemListConverter.class)
    private List<Computer> computerList;


    public List<Computer> getComputerList() {
        return computerList;
    }

    public void setComputerList(List<Computer> computerList) {
        this.computerList = computerList;
    }

    public static class Computer{
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }


        @Override
        public String toString() {
            return "Computer{" +
                    "url='" + url + '\'' +
                    '}';
        }
    }


    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }


    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public boolean getIsOpen() {
        return this.isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public AdviceBean(String name, String road, String adress) {
        this.name = name;
        this.road = road;
        this.adress = adress;
    }

    public AdviceBean(String name, String road) {
        this.name = name;
        this.road = road;
    }
    public AdviceBean(String typeId, String name, String road, String adress) {
        this.typeId = typeId;
        this.name = name;
        this.road = road;
        this.adress = adress;
    }

    @Keep
    public AdviceBean(String area,String typeId, String name, String road, String adress,
            boolean isOpen) {
        this.area = area;
        this.typeId = typeId;
        this.name = name;
        this.road = road;
        this.adress = adress;
        this.isOpen = isOpen;
    }

    @Generated(hash = 300252844)
    public AdviceBean() {
    }

    @Generated(hash = 845798269)
    public AdviceBean(String typeId, String name, String road, String adress, String area,
            boolean isOpen, List<Computer> computerList) {
        this.typeId = typeId;
        this.name = name;
        this.road = road;
        this.adress = adress;
        this.area = area;
        this.isOpen = isOpen;
        this.computerList = computerList;
    }

    @Override
    public String toString() {
        return "AdviceBean{" +
                "typeId='" + typeId + '\'' +
                ", name='" + name + '\'' +
                ", road='" + road + '\'' +
                ", adress='" + adress + '\'' +
                ", area='" + area + '\'' +
                ", isOpen=" + isOpen +
                ", computerList=" + computerList +
                '}';
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
