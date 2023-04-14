package com.example.myfx;

public class Lager {

    private Integer id;
    private String varumarke;
    private String designer;
    private String fraktklass;
    private String farg;
    private String hoj;
    private String material;
    private String bredd;
    private String djup;
    private String hojd;

    public Lager(Integer id, String varumarke, String designer, String fraktklass, String farg, String hoj, String material, String bredd, String djup, String hojd) {
        this.id = id;
        this.varumarke = varumarke;
        this.designer = designer;
        this.fraktklass = fraktklass;
        this.farg = farg;
        this.hoj = hoj;
        this.material = material;
        this.bredd = bredd;
        this.djup = djup;
        this.hojd = hojd;
    }

    public Integer getId() {
        return id;
    }

    public String getVarumarke() {
        return varumarke;
    }

    public String getDesigner() {
        return designer;
    }

    public String getFraktklass() {
        return fraktklass;
    }

    public String getFarg() {
        return farg;
    }

    public String getHoj() {
        return hoj;
    }

    public String getMaterial() {
        return material;
    }

    public String getBredd() {
        return bredd;
    }

    public String getDjup() {
        return djup;
    }

    public String getHojd() {
        return hojd;
    }
}
