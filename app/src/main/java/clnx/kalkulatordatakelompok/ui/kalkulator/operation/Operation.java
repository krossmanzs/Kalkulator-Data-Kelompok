package clnx.kalkulatordatakelompok.ui.kalkulator.operation;

import java.util.ArrayList;

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
}
