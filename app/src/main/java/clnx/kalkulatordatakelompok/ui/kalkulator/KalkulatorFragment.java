package clnx.kalkulatordatakelompok.ui.kalkulator;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Selection;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import clnx.kalkulatordatakelompok.R;
import clnx.kalkulatordatakelompok.databinding.FragmentHomeBinding;
import clnx.kalkulatordatakelompok.ui.helper.ErrorMsg;
import clnx.kalkulatordatakelompok.ui.kalkulator.operation.Operation;
import clnx.kalkulatordatakelompok.util.Utility;

public class KalkulatorFragment extends Fragment {

    private DataModel dataModel;
    private FragmentHomeBinding binding;
    private TableLayout tlDisFre;
    private EditText etKiri, etKanan, etFrekuensi;
    private TextView txtMean, txtMedian, txtModus;
    private float jumlahFrekuensi;
    private float jumlahFiXiXs;
    private float jumlahFiXi;

    private float nilaiKiri;
    private float nilaiKanan;
    private float frekuensi;

    private float fk, tepiBawah, tepiAtas, xi, xiMinusXs, fiXiXs, fiTimesXi, xs;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        setHasOptionsMenu(true);

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        dataModel = new DataModel();

        final Button btnAdd = binding.btnAdd;
        txtMean = binding.txtMean;
        txtMedian = binding.txtMedian;
        txtModus = binding.txtModus;
        tlDisFre = binding.tlDistribusiFrekuensi;
        etKiri = binding.etKiri;
        etKanan = binding.etKanan;
        etFrekuensi = binding.etFrekuensi;

        btnAdd.setOnClickListener(v -> {
            String etNilaiKiri = etKiri.getText().toString();
            String etNilaiKanan = etKanan.getText().toString();
            String etFrek = etFrekuensi.getText().toString();

            if(etNilaiKiri.isEmpty() || etNilaiKanan.isEmpty() || etFrek.isEmpty()) {
                ErrorMsg.snackBar(binding.getRoot().getRootView(),
                        "Masih ada data yang kosong");
            } else {
                nilaiKiri = Float.parseFloat(etKiri.getText().toString());
                nilaiKanan = Float.parseFloat(etKanan.getText().toString());
                frekuensi = Float.parseFloat(etFrekuensi.getText().toString());
                addData(binding.getRoot().getContext());
                calculateResult();
            }
        });
        return binding.getRoot();
    }

    private void calculateResult() {
        // mean
        float mean = Operation.hitungMean(xs,jumlahFiXiXs,jumlahFrekuensi);
        txtMean.setText(Utility.reformatDecimalNum(mean));

        if (tlDisFre.getChildCount() > 4) {
            // median
            float maxFrekuensi = Utility.getMax(dataModel.getDataFrekuensi());
            float panjang = 0f;
            float fk = 0f;
            float tepiBawah = 0f;
            float d1 = 0f;
            float d2 = 0f;
            boolean isModus = false;
            for (int i = 1; i <= dataModel.getBaris(); i++) {
                if (maxFrekuensi == dataModel.getFrekuensi(i)) {
                    panjang = (dataModel.getNilaiKanan(i) - dataModel.getNilaiKiri(i)) + 1;
                    fk = dataModel.getFk(i-1);
                    frekuensi = dataModel.getFrekuensi(i);
                    tepiBawah = dataModel.getNilaiKiri(i) - 0.5f;

                    if (dataModel.getBaris() > i) {
                        isModus = true;
                        d1 = maxFrekuensi - dataModel.getFrekuensi(i-1);
                        d2 = maxFrekuensi - dataModel.getFrekuensi(i+1);
                    }
                }
            }

            float median = Operation.hitungMedian(tepiBawah,jumlahFrekuensi,fk,frekuensi,panjang);
            txtMedian.setText(Utility.reformatDecimalNum(median));

            if (isModus) {
                float modus = Operation.hitungModus(tepiBawah,d1,d2,panjang);
                txtModus.setText(Utility.reformatDecimalNum(modus));
            }
        }
    }

    private void removeSumRow() {
        TableRow lastRow = (TableRow) tlDisFre.getChildAt(tlDisFre.getChildCount()-1);
        TextView textView = (TextView) lastRow.getChildAt(0);
        String sumRow = textView.getText().toString();

        if (sumRow.isEmpty()) {
            tlDisFre.removeViews(tlDisFre.getChildCount()-1, 1);
        }
    }

    private void showSumRow(Context context) {
        removeSumRow();

        ArrayList<Float> dataFrekuensi = new ArrayList<>();
        ArrayList<Float> dataFiXiXs = new ArrayList<>();
        ArrayList<Float> dataFiXi = new ArrayList<>();

        for (int i = 1; i <= dataModel.getBaris(); i++) {
            dataFrekuensi.add(dataModel.getFrekuensi(i));
            dataFiXiXs.add(dataModel.getFiXiXs(i));
            dataFiXi.add(dataModel.getFiXi(i));
        }

        jumlahFrekuensi = Operation.hitungTotalFrekuensi(dataFrekuensi);
        jumlahFiXiXs = Operation.hitungTotalFiXiXs(dataFiXiXs);
        jumlahFiXi = Operation.hitungTotalFiXi(dataFiXi);

        TableRow tr_head = new TableRow(context);
        tr_head.setId(View.generateViewId());
        tr_head.setLayoutParams(new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));

        tr_head.addView(createBlankRowData(context));
        tr_head.addView(createBlankRowData(context));
        tr_head.addView(createRowData(Utility.reformatDecimalNum(jumlahFrekuensi),context));
        tr_head.addView(createBlankRowData(context));
        tr_head.addView(createBlankRowData(context));
        tr_head.addView(createBlankRowData(context));
        tr_head.addView(createBlankRowData(context));
        tr_head.addView(createBlankRowData(context));
        tr_head.addView(createBlankRowData(context));
        tr_head.addView(createRowData(Utility.reformatDecimalNum(jumlahFiXiXs),context));// fi xi xs
        tr_head.addView(createRowData(Utility.reformatDecimalNum(jumlahFiXi),context));// fi xi

        tlDisFre.addView(tr_head, new TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.WRAP_CONTENT));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void addData(Context context) {
        removeSumRow();

        int baris = tlDisFre.getChildCount();
        float nilaiKiri = Float.parseFloat(etKiri.getText().toString());
        float nilaiKanan = Float.parseFloat(etKanan.getText().toString());
        float frekuensi = Float.parseFloat(etFrekuensi.getText().toString());

        // nilai kiri
        dataModel.insertNilaiKiri(baris,nilaiKiri);

        // nilai kanan
        dataModel.insertNilaiKanan(baris,nilaiKanan);

        // frekuensi
        dataModel.insertFrekuensi(baris,frekuensi);

        // fk
        fk = (baris == 1) ? Integer.parseInt(etFrekuensi.getText().toString()) :
                frekuensi + dataModel.getFk(dataModel.getBaris()-1);
        dataModel.insertFk(baris,fk);

        // tepi bawah
        tepiBawah = nilaiKiri - 0.5f;
        dataModel.insertTepiBawah(baris,tepiBawah);

        // tepi atas
        tepiAtas = nilaiKanan + 0.5f;
        dataModel.insertTepiAtas(baris,tepiAtas);

        // xi
        xi = (tepiAtas + tepiBawah) / 2;
        dataModel.insertXi(baris,xi);

        // xs
        float frekuensiMax = Utility.getMax(dataModel.getDataFrekuensi());
        for (int i = 1; i <= dataModel.getBaris(); i++) {
            if (dataModel.getFrekuensi(i) == frekuensiMax) {
                xs = dataModel.getXi(i);
                dataModel.insertXs(xs);
            }
        }

        // xi-xs
        xiMinusXs = xi - xs;
        dataModel.insertXiMinusXs(baris,xiMinusXs);

        // fi(xi-xs)
        fiXiXs = frekuensi * xiMinusXs;
        dataModel.insertFiXiXs(baris,fiXiXs);

        // fi * xi
        fiTimesXi = frekuensi * xi;
        dataModel.insertFiTimesXi(baris,fiTimesXi);

        deleteAllData(false);

        // Display to table
        for (int i = 1; i <= dataModel.getBaris(); i++) {
            TableRow tr_head = new TableRow(context);
            tr_head.setId(View.generateViewId());
            tr_head.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            ArrayList<Float> datas = dataModel.getBarisData(i);
            for (int j = 0; j < datas.size(); j++) {
                xi = dataModel.getXi(i);
                frekuensi = dataModel.getFrekuensi(i);
                xiMinusXs = xi - xs;
                fiXiXs = frekuensi * xiMinusXs;
                fiTimesXi = frekuensi * xi;
                switch (j) {
                    case 8:
                        dataModel.replaceXiMinusXs(i, xiMinusXs);
                        tr_head.addView(createRowData(Utility.reformatDecimalNum(xiMinusXs), context));
                        break;
                    case 9:
                        dataModel.replaceFiXiXs(i, fiXiXs);
                        tr_head.addView(createRowData(Utility.reformatDecimalNum(fiXiXs), context));
                        break;
                    case 10:
                        dataModel.replaceFiXi(i, fiTimesXi);
                        tr_head.addView(createRowData(Utility.reformatDecimalNum(fiTimesXi), context));
                        break;
                    default:
                        tr_head.addView(createRowData(Utility.reformatDecimalNum(datas.get(j)), context));
                        break;
                }
            }
            tlDisFre.addView(tr_head, new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));
        }

        showSumRow(context);

        etKiri.setText("");
        etKanan.setText("");
        etFrekuensi.setText("");

        Selection.setSelection(etKiri.getText(),etFrekuensi.getSelectionStart());
        etKiri.requestFocus();
    }

    private void deleteAllData(boolean removeDataOnModel) {
        if (removeDataOnModel) {
            if (dataModel.removeAllData()) {
                tlDisFre.removeViews(1, tlDisFre.getChildCount()-1);
            }
        } else {
            tlDisFre.removeViews(1, tlDisFre.getChildCount()-1);
        }
    }

    @NonNull
    private TextView createRowData(String msg, Context context) {
        TextView textView = new TextView(context);
        textView.setId(View.generateViewId());
        textView.setLayoutParams(new TableRow.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        textView.setText(msg);
        textView.setHeight(100);
        textView.setBackgroundResource(R.drawable.border);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setPadding(10,10,10,10);
        return textView;
    }

    @NonNull
    private TextView createBlankRowData(Context context) {
        TextView textView = new TextView(context);
        textView.setId(View.generateViewId());
        textView.setText("");
        textView.setHeight(100);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setTextColor(Color.BLACK);
        textView.setPadding(0,10,0,10);
        return textView;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_kalkulator,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.clear_table) {
            if (tlDisFre.getChildCount() > 1) {
                txtModus.setText(R.string.noResult);
                txtMedian.setText(R.string.noResult);
                txtMean.setText(R.string.noResult);
                deleteAllData(true);
            } else {
                ErrorMsg.snackBar(binding.getRoot().getRootView(),
                        "Masukkan data terlebih dahulu");
            }
        } else if (item.getItemId() == R.id.save_table) {
            ErrorMsg.snackBar(binding.getRoot().getRootView(),
                    "Save file masih dalam pengembangan :))");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}