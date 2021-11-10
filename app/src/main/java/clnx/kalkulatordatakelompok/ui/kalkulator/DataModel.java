package clnx.kalkulatordatakelompok.ui.kalkulator;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;

public class DataModel {
    private HashMap<Integer, Float> nilaiKiri = new HashMap<>();
    private HashMap<Integer, Float> nilaiKanan = new HashMap<>();
    private HashMap<Integer, Float> frekuensi = new HashMap<>();
    private HashMap<Integer, Float> tepiBawah = new HashMap<>();
    private HashMap<Integer, Float> tepiAtas = new HashMap<>();
    private HashMap<Integer, Float> xi = new HashMap<>();
    private HashMap<Integer, Float> fk = new HashMap<>();
    private HashMap<Integer, Float> xs = new HashMap<>();
    private HashMap<Integer, Float> xiMinusXs = new HashMap<>();
    private HashMap<Integer, Float> fiXiXs = new HashMap<>();
    private HashMap<Integer, Float> fiTimesXi = new HashMap<>();

    public boolean removeAllData() {
        try {
            nilaiKiri.clear();
            nilaiKanan.clear();
            frekuensi.clear();
            fk.clear();
            tepiBawah.clear();
            tepiAtas.clear();
            xi.clear();
            xs.clear();
            xiMinusXs.clear();
            fiXiXs.clear();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int getBaris() {
        return nilaiKiri.size();
    }

    public float getFrekuensi(int row) {
        return frekuensi.get(row);
    }

    public float getXi(int row) {
        return xi.get(row);
    }

    public float getNilaiKiri(int row) {
        return nilaiKiri.get(row);
    }

    public float getNilaiKanan(int row) {
        return nilaiKanan.get(row);
    }

    public float getFiXiXs(int row) {
        return fiXiXs.get(row);
    }

    public float getFiXi(int row) {
        return fiTimesXi.get(row);
    }

    public ArrayList<Float> getDataFrekuensi() {
        ArrayList<Float> dataFrekuensi = new ArrayList<>();

        for (int i = 1; i <= getBaris(); i++) {
            dataFrekuensi.add(frekuensi.get(i));
        }

        return dataFrekuensi;
    }

    public ArrayList<Float> getBarisData(int row) {
        ArrayList<Float> data = new ArrayList<>();
        data.add(nilaiKiri.get(row));
        data.add(nilaiKanan.get(row));
        data.add(frekuensi.get(row));
        data.add(fk.get(row));
        data.add(tepiBawah.get(row));
        data.add(tepiAtas.get(row));
        data.add(xi.get(row));
        data.add(xs.get(row));
        data.add(xiMinusXs.get(row));
        data.add(fiXiXs.get(row));
        data.add(fiTimesXi.get(row));
        return data;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void replaceXiMinusXs(int row, float nilai) {
        xiMinusXs.replace(row,nilai);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void replaceFiXiXs(int row, float nilai) {
        fiXiXs.replace(row,nilai);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void replaceFiXi(int row, float nilai) {
        fiTimesXi.replace(row,nilai);
    }

    public float getFk(int row) {
        return fk.get(row);
    }

    public void insertNilaiKiri(int row,float nilai) {
        nilaiKiri.put(row,nilai);
    }

    public void insertNilaiKanan(int row,float nilai) {
        nilaiKanan.put(row,nilai);
    }

    public void insertFrekuensi(int row,float nilai) {
        frekuensi.put(row,nilai);
    }

    public void insertTepiBawah(int row,float nilai) {
        tepiBawah.put(row,nilai);
    }

    public void insertTepiAtas(int row,float nilai) {
        tepiAtas.put(row,nilai);
    }

    public void insertXi(int row,float nilai) {
        xi.put(row,nilai);
    }

    public void insertFk(int row,float nilai) {
        fk.put(row,nilai);
    }

    public void insertXs(float nilai) {
        xs.clear();
        for (int i = 1; i <= getBaris(); i++) {
            xs.put(i,nilai);
        }
    }

    public void insertXiMinusXs(int row,float nilai) {
        xiMinusXs.put(row,nilai);
    }

    public void insertFiXiXs(int row,float nilai) {
        fiXiXs.put(row,nilai);
    }

    public void insertFiTimesXi(int row,float nilai) {
        fiTimesXi.put(row,nilai);
    }

    public float getXs() {
        return xs.get(1);
    }
}
