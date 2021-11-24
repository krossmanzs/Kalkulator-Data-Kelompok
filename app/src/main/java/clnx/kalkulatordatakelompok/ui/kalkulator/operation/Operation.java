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
     * @param quartil untuk menentukan kuartil ke-berapa
     * @param tb tepi bawah
     * @param n jumlah frekuensi
     * @param fk frekuensi kumulatif sebelum kelas quartil
     * @param fi frekuensi pada kelas quartil
     * @param p panjang kelas
     * @return hasil nilai quartil ke berapa dari yang ditentukan
     */
    public static float hitungKuartil(int quartil, float tb,
                                      float n, float fk, float fi, float p) {

        final float jumlahFrek = ((float) quartil / 4f) * n;

        return tb + ((jumlahFrek - fk) / fi) * p;
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
     * @param desil untuk menentukan desil ke-berapa
     * @param tb tepi bawah
     * @param n jumlah frekuensi
     * @param fk frekuensi kumulatif sebelum kelas desil
     * @param fi frekuensi pada kelas desil
     * @param p panjang kelas
     * @return hasil perhitungan desil ke berapa dari yang sudah ditentukan
     */
    public static float hitungDesil(int desil, float tb,
                                      float n, float fk, float fi, float p) {
        float jumlahFrek = 0f;

        switch (desil) {
            case 1:
                jumlahFrek = (1f/10f)/(float) n;
                break;
            case 2:
                jumlahFrek = (2f/10f)/(float) n;
                break;
            case 3:
                jumlahFrek = (3f/10f)/(float) n;
                break;
            case 4:
                jumlahFrek = (4f/10f)/(float) n;
                break;
            case 5:
                jumlahFrek = (5f/10f)/(float) n;
                break;
            case 6:
                jumlahFrek = (6f/10f)/(float) n;
                break;
            case 7:
                jumlahFrek = (7f/10f)/(float) n;
                break;
            case 8:
                jumlahFrek = (8f/10f)/(float) n;
                break;
            case 9:
                jumlahFrek = (9f/10f)/(float) n;
                break;
            case 10:
                jumlahFrek = (1.0f)/(float) n;
                break;
        }

        return tb + ((jumlahFrek - fk) / fi) * p;
    }

    public static Map<String,Float> getDatakelasKuartil(DataModel dataModel, int kuartil) {

        final Map<String,Float> dataKelasKuartil = new HashMap<>();

        float n = dataModel.getFk(dataModel.getBaris()); // mendapatkan jumlah frekuensi
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
}
