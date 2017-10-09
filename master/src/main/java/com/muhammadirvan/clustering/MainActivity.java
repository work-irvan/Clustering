package com.muhammadirvan.clustering;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.Format;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ListView list;
    CustomList adapter;
    Button toPdf;
    public MainActivity CustomListView = null;
    public ArrayList<dataList> CustomListViewValuesArr = new ArrayList<dataList>();

    String[] ArrNamaProvC1  = new String[1000];
    String[] ArrLuasPanenC1 = new String[1000];
    String[] ArrProduksiC1  = new String[1000];
    String[] ArrNamaProvC2  = new String[1000];
    String[] ArrLuasPanenC2 = new String[1000];
    String[] ArrProduksiC2  = new String[1000];
    int arrJumlahC1 = 0;
    int arrJumlahC2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CustomListView = this;
        Resources res = getResources();
        list=(ListView)findViewById(R.id.listKacang);
        adapter = new CustomList(CustomListView, CustomListViewValuesArr, res);
        list.setAdapter(adapter);

        toPdf = (Button)findViewById(R.id.btnToPdf);
        toPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buka = new Intent(MainActivity.this, SecondActivity.class);
                buka.putExtra("NamaC1", ArrNamaProvC1);
                buka.putExtra("NamaC2", ArrNamaProvC2);

                buka.putExtra("LuasC1", ArrLuasPanenC1);
                buka.putExtra("LuasC2", ArrLuasPanenC2);

                buka.putExtra("ProduksiC1", ArrProduksiC1);
                buka.putExtra("ProduksiC2", ArrProduksiC2);

                buka.putExtra("JC1", arrJumlahC1);
                buka.putExtra("JC2", arrJumlahC2);

                startActivity(buka);
            }
        });
    }
    public void onItemClick(final int mPosition) {
        final dataList tempValues = (dataList) CustomListViewValuesArr.get(mPosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_utama, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Toast.makeText(getApplicationContext(),	item.getTitle() + " selected", Toast.LENGTH_SHORT).show();

        switch (item.getItemId()) {
            case R.id.menuOpen:
                // do something
                Intent intent = new Intent()
                        .setType("*/*")
                        .setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select a file"), 123);

                break;
            case R.id.menuClear:
                // do something
                CustomListViewValuesArr.clear();
                adapter.notifyDataSetChanged();
                list.setAdapter(adapter);
                toPdf.setVisibility(View.GONE);
                break;
        }
        return true;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123 && resultCode==RESULT_OK) {
            Uri selectedfile = data.getData(); //The uri with the location of the file
            //Toast.makeText(this, selectedfile.toString(), Toast.LENGTH_LONG).show();
            Log.d("Lokasi", selectedfile.getEncodedPath());
            Log.d("Lokasi", selectedfile.getPath());

            String csvFile = selectedfile.getPath();
            if(csvFile.substring(csvFile.length() - 3).equals("CSV") || csvFile.substring(csvFile.length() - 3).equals("csv")){
                //toPdf.setVisibility(View.VISIBLE);
                BufferedReader br = null;
                String line = "";
                String cvsSplitBy = ";";

                try {
                    CustomListViewValuesArr.clear();
                    dataList sched;
                    String[][] dataCsv = new String[1000][3];


                    br = new BufferedReader(new FileReader(csvFile));
                    int i = 0;


                    Double[] pC1 = new Double[2], pC2 = new Double[2], bC1 = new Double[2], bC2 = new Double[2];
                    while ((line = br.readLine()) != null) {

                        // use comma as separator
                        String[] datRaw = line.split(cvsSplitBy);
                        dataCsv[i][0] = datRaw[0];
                        dataCsv[i][1] = datRaw[1];
                        dataCsv[i][2] = datRaw[2];

                        i++;

                        Log.d("Data","Prov = " + datRaw[0] + " , Luas = " + datRaw[1] + " , Produksi = " + datRaw[2]);


                    }

                    int rand1 = 1 + (int)(Math.random() * (i-1));
                    int rand2 = 1 + (int)(Math.random() * (i-1));

                    Log.d("Rand", "Rand1 = " + rand1 + ", Rand2 = " + rand2);
                    String[] random1 = new String[3];
                    random1[0] = dataCsv[rand1][0];
                    random1[1] = dataCsv[rand1][1];
                    random1[2] = dataCsv[rand1][2];
                    Log.d("C1", "Prov = " + random1[0] + ", Luas = " + random1[1] + ", Luas = " + random1[2]);
                    String[] random2 = new String[3];
                    random2[0] = dataCsv[rand2][0];
                    random2[1] = dataCsv[rand2][1];
                    random2[2] = dataCsv[rand2][2];
                    Log.d("C2", "Prov = " + random2[0] + ", Luas = " + random2[1] + ", Luas = " + random2[2]);
                    String strC1, strC2;
                    int dokei = 0;
                    boolean lanjut = true;
                    do{
                        int iC1 = 0;
                        int iC2 = 0;
                        double[] jC1 = new double[2];
                        double[] jC2 = new double[2];
                        String[] bArrNamaProvC1   = new String[1000];
                        String[] bArrLuasPanenC1  = new String[1000];
                        String[] bArrProduksiC1   = new String[1000];

                        String[] bArrNamaProvC2   = new String[1000];
                        String[] bArrLuasPanenC2  = new String[1000];
                        String[] bArrProduksiC2   = new String[1000];

                        if(dokei == 0){
                            pC1[0] = Double.valueOf(random1[1]);
                            pC1[1] = Double.valueOf(random1[2]);

                            pC2[0] = Double.valueOf(random2[1]);
                            pC2[1] = Double.valueOf(random2[2]);
                        }else{
                            pC1[0] = bC1[0];
                            pC1[1] = bC1[1];

                            pC2[0] = bC2[0];
                            pC2[1] = bC2[1];
                        }

                        sched = new dataList();
                        sched.setIsCluster("YES");
                        sched.setIsClusterPosition("BEGIN");
                        sched.setNo("C");
                        if(dokei > 0) {
                            sched.setNoPerulangan(String.valueOf(dokei));
                        }
                        sched.setNamaProv("-");
                        sched.setLuasPanen("Luas Panen");
                        sched.setProduksi("Produksi");

                        CustomListViewValuesArr.add(sched);
                        sched = new dataList();
                        sched.setIsCluster("YES");
                        sched.setIsClusterPosition("-");
                        sched.setNo("C1");
                        sched.setNamaProv("-");
                        sched.setLuasPanen(String.valueOf(pC1[0]));
                        sched.setProduksi(String.valueOf(pC1[1]));

                        CustomListViewValuesArr.add(sched);
                        sched = new dataList();
                        sched.setIsCluster("YES");
                        sched.setIsClusterPosition("END");
                        sched.setNo("C2");
                        sched.setNamaProv("-");
                        sched.setLuasPanen(String.valueOf(pC2[0]));
                        sched.setProduksi(String.valueOf(pC2[1]));

                        CustomListViewValuesArr.add(sched);

                        sched = new dataList();
                        sched.setIsHeader("YES");
                        sched.setIsCluster("NO");
                        sched.setNo("NO");
                        sched.setNamaProv("Nama Prov");
                        sched.setLuasPanen("Luas Panane");
                        sched.setProduksi("Produksi");
                        sched.setDc1("DC1");
                        sched.setDc2("DC2");
                        sched.setC1("C1");
                        sched.setC2("C2");

                        CustomListViewValuesArr.add(sched);

                        for(int x = 1; x < i; x++){
                            strC1 = "";
                            strC2 = "";

                            sched = new dataList();
                            sched.setIsCluster("NO");
                            sched.setNo(String.valueOf(x) + ". ");
                            sched.setNamaProv(dataCsv[x][0]);
                            sched.setLuasPanen(dataCsv[x][1]);
                            sched.setProduksi(dataCsv[x][2]);



                            double dc1 = Math.sqrt(Math.pow((Double.valueOf(dataCsv[x][1]) - pC1[0]), 2) +
                                    Math.pow((Double.valueOf(dataCsv[x][2]) - pC1[1]), 2));

                            double dc2 = Math.sqrt(Math.pow((Double.valueOf(dataCsv[x][1]) - pC2[0]), 2) +
                                    Math.pow((Double.valueOf(dataCsv[x][2]) - pC2[1]), 2));

                            if(dc1<dc2){
                                strC1 = "OK";
                                jC1[0] += Double.valueOf(dataCsv[x][1]);
                                jC1[1] += Double.valueOf(dataCsv[x][2]);

                                bArrNamaProvC1[iC1]     = dataCsv[x][0];
                                bArrLuasPanenC1[iC1]    = dataCsv[x][1];
                                bArrProduksiC1[iC1]     = dataCsv[x][2];
                                iC1++;

                            }else {
                                strC2 = "OK";
                                jC2[0] += Double.valueOf(dataCsv[x][1]);
                                jC2[1] += Double.valueOf(dataCsv[x][2]);

                                bArrNamaProvC2[iC2]     = dataCsv[x][0];
                                bArrLuasPanenC2[iC2]    = dataCsv[x][1];
                                bArrProduksiC2[iC2]     = dataCsv[x][2];

                                Log.d("ee", "Nama " + iC2 + " : " + bArrNamaProvC2[iC2] + " | " + " Luas : " + bArrLuasPanenC2[iC2] + " | " + "Produksi : " + bArrProduksiC2[iC2]);

                                iC2++;
                            }

                            sched.setDc1(String.valueOf(dc1));
                            sched.setDc2(String.valueOf(dc2));
                            sched.setC1(strC1);
                            sched.setC2(strC2);

                            CustomListViewValuesArr.add(sched);
                        }
                        bC1[0] = Double.valueOf((jC1[0] / iC1));
                        bC1[1] = Double.valueOf((jC1[1] / iC1));

                        bC2[0] = Double.valueOf((jC2[0] / iC2));
                        bC2[1] = Double.valueOf((jC2[1] / iC2));

                        Log.d("pC1", String.valueOf(pC1[0]) + " | " + String.valueOf(pC1[1]));
                        Log.d("pC2", String.valueOf(pC2[0]) + " | " + String.valueOf(pC2[1]));

                        Log.d("bC1", String.valueOf(bC1[0]) + " | " + String.valueOf(bC1[1]));
                        Log.d("bC2", String.valueOf(bC2[0]) + " | " + String.valueOf(bC2[1]));

                    /*sched = new dataList();
                    sched.setIsCluster("YES");
                    sched.setIsClusterPosition("BEGIN");
                    sched.setNo("C");
                    sched.setNamaProv("Nama Prov");
                    sched.setLuasPanen("Luas Panen");
                    sched.setProduksi("Produksi");

                    CustomListViewValuesArr.add(sched);*/


                        adapter.notifyDataSetChanged();
                        list.setAdapter(adapter);
                        dokei++;

                        Log.d("Banding", String.valueOf("bC1[0] = " + bC1[0]) + " | pC1[0] = " + String.valueOf(pC1[0]));
                        Log.d("Banding", String.valueOf("bC2[0] = " + bC2[0]) + " | pC2[0] = " + String.valueOf(pC2[0]));

                        Log.d("Banding", String.valueOf("bC1[1] = " + bC1[1]) + " | pC1[1] = " + String.valueOf(pC1[1]));
                        Log.d("Banding", String.valueOf("bC2[1] = " + bC2[1]) + " | pC2[1] =" + String.valueOf(pC2[1]));

                        if(pC1[0].equals(bC1[0])){
                            Log.d("CEK", "pC1[0] = " + String.valueOf(pC1[0]) + " == bC1[0] =" + String.valueOf(bC1[0]) + " = true ");
                            if(pC1[1].equals(bC1[1])){
                                Log.d("CEK", "pC1[1] = " + String.valueOf(pC1[1]) + " == bC1[1] =" + String.valueOf(bC1[1]) + " = true ");
                                if(pC2[0].equals(bC2[0])){
                                    Log.d("CEK", "pC2[0] = " + String.valueOf(pC2[0]) + " == bC2[0] =" + String.valueOf(bC2[0]) + " = true ");
                                    if(pC2[1].equals(bC2[1])){
                                        Log.d("CEK", "pC2[1] = " + String.valueOf(pC2[1]) + " == bC2[1] =" + String.valueOf(bC2[1]) + " = true ");
                                        lanjut = false;

                                        ArrNamaProvC1   = bArrNamaProvC1;
                                        ArrLuasPanenC1  = bArrLuasPanenC1;
                                        ArrProduksiC1   = bArrProduksiC1;

                                        ArrNamaProvC2   = bArrNamaProvC2;
                                        for(int ix = 0; ix < iC2; ix++){
                                            Log.d("NamaDebug", ArrNamaProvC2[ix]);
                                        }
                                        ArrLuasPanenC2  = bArrLuasPanenC2;
                                        ArrProduksiC2   = bArrProduksiC2;

                                        arrJumlahC1 = iC1;
                                        arrJumlahC2 = iC2;

                                        toPdf.setVisibility(View.VISIBLE);
                                    }else{
                                        Log.d("CEK", "pC2[1] = " + String.valueOf(pC2[1]) + " == bC2[1] =" + String.valueOf(bC2[1]) + " = false ");
                                    }
                                }else{
                                    Log.d("CEK", "pC2[0] = " + String.valueOf(pC2[0]) + " == bC2[0] =" + String.valueOf(bC2[0]) + " = false ");
                                }
                            }else{
                                Log.d("CEK", "pC1[1] = " + String.valueOf(pC1[1]) + " == bC1[1] =" + String.valueOf(bC1[1]) + " = false ");
                            }
                        }else{
                            Log.d("CEK", "pC1[0] = " + String.valueOf(pC1[0]) + " == bC1[0] =" + String.valueOf(bC1[0]) + " = false ");
                        }
                        if(dokei > 10){
                            lanjut = false;
                        }
                    }while (lanjut);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (br != null) {
                        try {
                            br.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                //End Of Read data
            }else{
                Toast.makeText(this, "File yang dipilih haruslah file CSV!", Toast.LENGTH_LONG).show();
            }


        }
    }

}
