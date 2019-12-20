package com.example.myapplication;

public class UserModel {
    private int id, jhektar, dhektar, hoe, sickle, sprayer, fertilizer, pesticides, seed, worker;
    private long modal;
    private String nama;

    public UserModel() {
    }

    public UserModel(int id,String nama, int jhektar, int dhektar, int worker, int hoe, int sickle, int sprayer, int fertilizer, int pesticides, int seed, long modal) {
        this.id = id;
        this.nama = nama;
        this.jhektar = jhektar;
        this.dhektar = dhektar;
        this.hoe = hoe;
        this.sickle = sickle;
        this.sprayer = sprayer;
        this.fertilizer = fertilizer;
        this.pesticides = pesticides;
        this.seed = seed;
        this.worker = worker;
        this.modal = modal;
    }

    public UserModel(int id, int jhektar, int dhektar, int worker, int hoe, int sickle, int sprayer, int fertilizer, int pesticides, int seed, long modal) {
        this.id = id;
        this.jhektar = jhektar;
        this.dhektar = dhektar;
        this.hoe = hoe;
        this.sickle = sickle;
        this.sprayer = sprayer;
        this.fertilizer = fertilizer;
        this.pesticides = pesticides;
        this.seed = seed;
        this.worker = worker;
        this.modal = modal;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnama() {
        return nama;
    }

    public void setnama(String nama) {
        this.nama = nama;
    }

    public int getJhektar() {
        return jhektar;
    }

    public void setJhektar(int jhektar) {
        this.jhektar = jhektar;
    }

    public int getDhektar() {
        return dhektar;
    }

    public void setDhektar(int dhektar) {
        this.dhektar = dhektar;
    }

    public int getHoe() {
        return hoe;
    }

    public void setHoe(int hoe) {
        this.hoe = hoe;
    }

    public int getSickle() {
        return sickle;
    }

    public void setSickle(int sickle) {
        this.sickle = sickle;
    }

    public int getSprayer() {
        return sprayer;
    }

    public void setSprayer(int sprayer) {
        this.sprayer = sprayer;
    }

    public int getFertilizer() {
        return fertilizer;
    }

    public void setFertilizer(int fertilizer) {
        this.fertilizer = fertilizer;
    }

    public int getPesticides() {
        return pesticides;
    }

    public void setPesticides(int pesticides) {
        this.pesticides = pesticides;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public int getWorker() {
        return worker;
    }

    public void setWorker(int worker) {
        this.worker = worker;
    }

    public long getModal() {
        return modal;
    }

    public void setModal(int modal) {
        this.modal = modal;
    }
}
