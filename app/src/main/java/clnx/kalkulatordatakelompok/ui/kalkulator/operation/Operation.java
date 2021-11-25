package clnx.kalkulatordatakelompok.ui.kalkulator.operation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import clnx.kalkulatordatakelompok.ui.kalkulator.DataModel;
import clnx.kalkulatordatakelompok.util.Utility;

public class Operation {
    /**
     * Metode ini digunakan untuk menjumlahkan total dari frekuensi
     *
     * @param dataFrekuensi merupakan tipe data mentah frekuensi
     * @return mengembalikan jumlah dari tipe mentah
     */
    public static float hitungTotalFrekuensi(ArrayList<Float> dataFrekuensi) {
        float jumlahFrekuensi = 0;
        for (float frekuensi: dataFrekuensi) {
            jumlahFrekuensi += frekuensi;
        }
        return jumlahFrekuensi;
    }

    /**
     * Metode ini digunakan untuk menjumlahkan total dari fi(Xi - Xs)
     *
     * @param dataFiXiXs merupakan tipe data mentah fiXiXs
     * @return mengembalikan jumlah dari tipe mentah
     */
    public static float hitungTotalFiXiXs(ArrayList<Float> dataFiXiXs) {
        float jumlahFiXiXs = 0;
        for (float fiXiXs: dataFiXiXs) {
            jumlahFiXiXs += fiXiXs;
        }
        return jumlahFiXiXs;
    }

    /**
     * Metode ini digunakan untuk menjumlahkan total dari fi x xi
     *
     * @param dataFiXi merupakan tipe data mentah fiXi
     * @return mengembalikan jumlah dari tipe mentah
     */
    public static float hitungTotalFiXi(ArrayList<Float> dataFiXi) {
        float jumlahFiXi = 0;
        for (float fiXi: dataFiXi) {
            jumlahFiXi += fiXi;
        }
        return jumlahFiXi;
    }

    /**
     * Metode ini berfungsi untuk menghitung mean atau rata rata.
     * dengan rumus:
     *
     *                           Σfi(xi-xs)
     *          Rata-rata = xs + ----------
     *                              Σfi
     *
     * @param xs rata rata sementara
     * @param fiXiXs frekuensi dikali (xi dikali rata rata sementara)
     * @param totalFi total frekuensi
     * @return hasil rata rata dari perhitungan menggunakan rumus diatas
     */
    public static float hitungMean(float xs, float fiXiXs, float totalFi) {
        return xs + (fiXiXs/totalFi);
    }

    /**
     * Metode ini berfungsi untuk menghitung nilai tengah atau median.
     * dengan rumus:
     *
     *                        (1/2)n - fk
     *          Median = Tb + ----------- x p
     *                             fi
     *
     * @param tb tepi bawah
     * @param n jumlah frekuensi (Σfi)
     * @param fk frekuensi kumulatif
     * @param fi frekuensi
     * @param p panjang kelas
     * @return hasil median dari perhitungan menggunakan rumus diatas
     */
    public static float hitungMedian(float tb, float n, float fk, float fi, float p) {
        return tb + (((0.5f*n)-fk)/fi)*p;
    }

    /**
     * Metode ini berfungsi untuk menghitung nilai yang paling sering
     * muncul atau disebut dengan modus.
     * dengan rumus:
     *
     *                           d1
     *          Modus = Tb + ----------- x p
     *                         d1 + d2
     *
     * @param tb tepi bawah
     * @param d1 frekuensi - frekuensi bawahnya
     * @param d2 frekuensi - frekuensi atasnya
     * @param p panjang kelas
     * @return hasil modus dari perhitungan menggunakan rumus diatas
     */
    public static float hitungModus(float tb, float d1, float d2, float p) {
        return tb + (d1/(d1+d2)) * p;
    }

    /**
     * Metode ini berfungsi untuk menghitung nilai kuartil 1 - 4.
     * Rumus:
     *
     *                              1
     *                             --- n - fk
     *                              4
     *          quartil(1) = Tb + ----------- x p
     *                                fi
     *
     *
     * @param kuartil untuk menentukan kuartil ke-berapa
     * @return hasil nilai quartil ke berapa dari yang ditentukan
     */
    public static float hitungKuartil(DataModel dataModel, int kuartil) {
        try {
            final Map<String,Float> dataDesil = getDatakelasKuartil(dataModel, kuartil);

            final float tb = dataDesil.get("tb");
            final float fk = dataDesil.get("fk");
            final float fi = dataDesil.get("fi");
            final float p = dataDesil.get("panjang");
            final float n = dataModel.getFk(dataModel.getBaris());

            final float jumlahFrek = ((float) kuartil / 4f) * n;

            return tb + ((jumlahFrek - fk) / fi) * p;
        } catch (NullPointerException e) {
            return 0f;
        }
    }

    private static Map<String,Float> getDatakelasKuartil(DataModel dataModel, int kuartil) {
        final Map<String,Float> dataKelasKuartil = new HashMap<>();

        final float n = dataModel.getFk(dataModel.getBaris()); // mendapatkan jumlah frekuensi
        final float jumlahFrek = ((float) kuartil / 4f) * n;

        for (int i = 1; i <= dataModel.getBaris(); i++) {
            if (dataModel.getFk(i) >= jumlahFrek) {
                dataKelasKuartil.put("tb",dataModel.getNilaiKiri(i) - 0.5f);
                dataKelasKuartil.put("fk",dataModel.getFk(i-1));
                dataKelasKuartil.put("fi",dataModel.getFrekuensi(i));
                dataKelasKuartil.put("panjang",dataModel.getNilaiKanan(i) - dataModel.getNilaiKiri(i) + 1 );
                break;
            }
        }

        return dataKelasKuartil;
    }

    /**
     * Metode ini berfungsi untuk menghitung nilai desil 1 - 10.
     * Rumus:
     *
     *                              1
     *                             ---- n - fk
     *                              10
     *          desil(1) = Tb + ----------- x p
     *                                fi
     *
     *
     * @param desil untuk menentukan desil ke-berapa
     * @param dataModel adalah data pada tabel
     * @return hasil perhitungan desil ke berapa dari yang sudah ditentukan
     */
    public static float hitungDesil(DataModel dataModel, int desil) {
        try {
            final Map<String,Float> dataDesil = getDataKelasDesil(dataModel, desil);

            final float tb = dataDesil.get("tb");
            final float fk = dataDesil.get("fk");
            final float fi = dataDesil.get("fi");
            final float p = dataDesil.get("panjang");
            final float n = dataModel.getFk(dataModel.getBaris());

            final float jumlahFrek = ((float) desil / 10f) * n;

            return tb + ((jumlahFrek - fk) / fi) * p;
        } catch (NullPointerException e) {
            return 0f;
        }
    }

    private static Map<String,Float> getDataKelasDesil(DataModel dataModel, int desil) {
        final Map<String,Float> dataKelasKuartil = new HashMap<>();

        final float n = dataModel.getFk(dataModel.getBaris());
        final float jumlahFrek = ((float) desil / 10f) * n;

        for (int i = 1; i <= dataModel.getBaris(); i++) {
            if (dataModel.getFk(i) >= jumlahFrek) {
                dataKelasKuartil.put("tb",dataModel.getNilaiKiri(i) - 0.5f);
                dataKelasKuartil.put("fk",dataModel.getFk(i-1));
                dataKelasKuartil.put("fi",dataModel.getFrekuensi(i));
                dataKelasKuartil.put("panjang",dataModel.getNilaiKanan(i) - dataModel.getNilaiKiri(i) + 1 );
                break;
            }
        }

        return dataKelasKuartil;
    }

    /**
     * Metode ini berfungsi untuk menghitung nilai persentil 1 - 100.
     * Rumus:
     *
     *                              1
     *                             --- n - fk
     *                             100
     *          persentil(1) = Tb + ----------- x p
     *                                fi
     *
     *
     * @param persentil untuk menentukan persentil ke-berapa
     * @return hasil perhitungan persentil ke berapa dari yang sudah ditentukan
     */
    public static float hitungPersentil(DataModel dataModel, int persentil) {
        try {
            final Map<String,Float> dataPersentil = getDataKelasPersentil(dataModel, persentil);

            final float tb = dataPersentil.get("tb");
            final float fk = dataPersentil.get("fk");
            final float fi = dataPersentil.get("fi");
            final float p = dataPersentil.get("panjang");
            final float n = dataModel.getFk(dataModel.getBaris());

            final float jumlahFrek = ((float) persentil / 100f) * n;

            return tb + ((jumlahFrek - fk) / fi) * p;
        } catch (NullPointerException e) {
            return 0f;
        }
    }

    private static Map<String,Float> getDataKelasPersentil(DataModel dataModel, int persentil) {
        final Map<String,Float> dataKelasKuartil = new HashMap<>();

        final float n = dataModel.getFk(dataModel.getBaris()); // mendapatkan jumlah frekuensi
        final float jumlahFrek = ((float) persentil / 100f) * n;

        for (int i = 1; i <= dataModel.getBaris(); i++) {
            if (dataModel.getFk(i) >= jumlahFrek) {
                dataKelasKuartil.put("tb",dataModel.getNilaiKiri(i) - 0.5f);
                dataKelasKuartil.put("fk",dataModel.getFk(i-1));
                dataKelasKuartil.put("fi",dataModel.getFrekuensi(i));
                dataKelasKuartil.put("panjang",dataModel.getNilaiKanan(i) - dataModel.getNilaiKiri(i) + 1 );
                break;
            }
        }

        return dataKelasKuartil;
    }
}
