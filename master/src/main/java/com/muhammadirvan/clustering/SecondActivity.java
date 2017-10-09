package com.muhammadirvan.clustering;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    String[] ArrNamaProvC1  = new String[1000];
    String[] ArrLuasPanenC1 = new String[1000];
    String[] ArrProduksiC1  = new String[1000];

    String[] ArrNamaProvC2  = new String[1000];
    String[] ArrLuasPanenC2 = new String[1000];
    String[] ArrProduksiC2  = new String[1000];
    int arrJumlahC1 = 0;
    int arrJumlahC2 = 0;

    ListView listC1, listC2;
    CustomList adapter1, adapter2;
    public SecondActivity CustomListView = null;
    public ArrayList<dataList> CustomListViewValuesArr1 = new ArrayList<dataList>();
    public ArrayList<dataList> CustomListViewValuesArr2 = new ArrayList<dataList>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        CustomListView = this;
        Resources res = getResources();
        listC1=(ListView)findViewById(R.id.listC1);
        listC2=(ListView)findViewById(R.id.listC2);


        adapter1 = new CustomList(CustomListView, CustomListViewValuesArr1, res);
        adapter2 = new CustomList(CustomListView, CustomListViewValuesArr2, res);

        listC1.setAdapter(adapter1);
        listC2.setAdapter(adapter2);

        Bundle getdata = getIntent().getExtras();
        ArrNamaProvC1 = getdata.getStringArray("NamaC1");
        ArrNamaProvC2 = getdata.getStringArray("NamaC2");

        ArrLuasPanenC1 = getdata.getStringArray("LuasC1");
        ArrLuasPanenC2 = getdata.getStringArray("LuasC2");

        ArrProduksiC1 = getdata.getStringArray("ProduksiC1");
        ArrProduksiC2 = getdata.getStringArray("ProduksiC2");

        arrJumlahC1 = getdata.getInt("JC1");
        arrJumlahC2 = getdata.getInt("JC2");
        try {
            cluster1();
            cluster2();
        }catch (Exception e){
            Log.d("Masalah", e.getMessage());
        }
        Log.d("SecondAct", "JC1 : " + arrJumlahC1 + " | JC2 : " + arrJumlahC2 + " | " + "JumlahC1 : " + ArrNamaProvC1.length + " JumlahC2 : " + ArrNamaProvC2.length + " NamaC2 : " + ArrNamaProvC2[19]+ " Luas : " + ArrLuasPanenC2[19] + " | " + ArrProduksiC2[19]);
    }

    public void cluster1(){
        CustomListViewValuesArr1.clear();
        dataList sched = new dataList();
        sched.setIsCluster("YES");
        sched.setIsClusterPosition("OKE");
        sched.setNo("NO");
        sched.setNamaProv("Nama Prov");
        sched.setLuasPanen("Luas Panen");
        sched.setProduksi("Produksi");

        CustomListViewValuesArr1.add(sched);


        for(int i = 0; i < arrJumlahC1; i++) {

            sched = new dataList();
            sched.setIsCluster("YES");
            sched.setIsClusterPosition("-");
            sched.setNo(String.valueOf(i+1));
            sched.setNamaProv(ArrNamaProvC1[i]);
            sched.setLuasPanen(ArrLuasPanenC1[i]);
            sched.setProduksi(ArrProduksiC1[i]);

            CustomListViewValuesArr1.add(sched);
        }

        adapter1.notifyDataSetChanged();
        listC1.setAdapter(adapter1);


    }
    public void cluster2(){
        CustomListViewValuesArr2.clear();
        dataList sched = new dataList();
        sched.setIsCluster("YES");
        sched.setIsClusterPosition("OKE");
        sched.setNo("NO");
        sched.setNamaProv("Nama Prov");
        sched.setLuasPanen("Luas Panen");
        sched.setProduksi("Produksi");

        CustomListViewValuesArr2.add(sched);


        for(int i = 0; i < arrJumlahC2; i++) {

            sched = new dataList();
            sched.setIsCluster("YES");
            sched.setIsClusterPosition("-");
            sched.setNo(String.valueOf(i+1));
            sched.setNamaProv(ArrNamaProvC2[i]);
            sched.setLuasPanen(ArrLuasPanenC2[i]);
            sched.setProduksi(ArrProduksiC2[i]);

            CustomListViewValuesArr2.add(sched);
        }

        adapter2.notifyDataSetChanged();
        listC2.setAdapter(adapter2);


    }
}
