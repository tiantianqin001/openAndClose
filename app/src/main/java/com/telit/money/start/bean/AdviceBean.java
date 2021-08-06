package com.telit.money.start.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class AdviceBean  {
    @Id
    private String typeId;
    private String name;
    private String road;

    private String adress;

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    private boolean isOpen;

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

    @Generated(hash = 2114976909)
    public AdviceBean(String typeId, String name, String road, String adress,
            boolean isOpen) {
        this.typeId = typeId;
        this.name = name;
        this.road = road;
        this.adress = adress;
        this.isOpen = isOpen;
    }

    @Generated(hash = 300252844)
    public AdviceBean() {
    }

    @Override
    public String toString() {
        return "AdviceBean{" +
                "typeId='" + typeId + '\'' +
                ", name='" + name + '\'' +
                ", road='" + road + '\'' +
                ", adress='" + adress + '\'' +
                ", isOpen=" + isOpen +
                '}';
    }
}
