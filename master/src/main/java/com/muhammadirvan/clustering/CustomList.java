package com.muhammadirvan.clustering;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.ColorRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Irvan on 21/12/2016.
 */
public class CustomList extends BaseAdapter implements OnClickListener {
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;
    public Resources res;
    dataList tempValues = null;
    int i=0;
    public CustomList(Activity a, ArrayList d, Resources resLocal) {
        activity = a;
        data = d;
        res = resLocal;
        inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public static class ViewHolder{
        public TextView no;
        public TextView namaProv;
        public TextView luasPanen;
        public TextView produksi;
        public TextView dc1;
        public TextView dc2;
        public TextView c1;
        public TextView c2;
        public TextView garis1;
        public TextView garis2;
        public TextView noPerulangan;

    }
    @Override
    public int getCount() {
        if(data.size()<=0)
            return 1;
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;
        if(convertView == null){
            vi = inflater.inflate(R.layout.list, null);
            holder = new ViewHolder();
            holder.no = (TextView)vi.findViewById(R.id.txtNo);
            holder.namaProv = (TextView)vi.findViewById(R.id.txtNama);
            holder.luasPanen = (TextView)vi.findViewById(R.id.txtLuas);
            holder.produksi = (TextView)vi.findViewById(R.id.txtProduksi);
            holder.dc1 = (TextView)vi.findViewById(R.id.txtDc1);
            holder.dc2 = (TextView)vi.findViewById(R.id.txtDc2);
            holder.c1 = (TextView)vi.findViewById(R.id.txtC1);
            holder.c2 = (TextView)vi.findViewById(R.id.txtC2);

            holder.garis1 = (TextView) vi.findViewById(R.id.garis1);
            holder.garis2 = (TextView) vi.findViewById(R.id.garis2);

            holder.noPerulangan = (TextView) vi.findViewById(R.id.noPerulangan);

            vi.setTag(holder);
        }else
            holder=(ViewHolder)vi.getTag();

        if(data.size() <= 0){
            holder.no.setText("No");
            holder.namaProv.setText("Nama Prov");
            holder.luasPanen.setText("Luas Panen");
            holder.produksi.setText("Produksi");
            holder.dc1.setText("DC1");
            holder.dc2.setText("DC2");
            holder.c1.setText("C1");
            holder.c2.setText("C2");

            holder.no.setBackgroundResource(R.color.colorHeader);
            holder.namaProv.setBackgroundResource(R.color.colorHeader);
            holder.luasPanen.setBackgroundResource(R.color.colorHeader);
            holder.produksi.setBackgroundResource(R.color.colorHeader);
            holder.dc1.setBackgroundResource(R.color.colorHeader);
            holder.dc2.setBackgroundResource(R.color.colorHeader);
            holder.c1.setBackgroundResource(R.color.colorHeader);
            holder.c2.setBackgroundResource(R.color.colorHeader);

            holder.no.setTextColor(vi.getResources().getColor(R.color.colorTextHeader));
            holder.namaProv.setTextColor(vi.getResources().getColor(R.color.colorTextHeader));
            holder.luasPanen.setTextColor(vi.getResources().getColor(R.color.colorTextHeader));
            holder.produksi.setTextColor(vi.getResources().getColor(R.color.colorTextHeader));
            holder.dc1.setTextColor(vi.getResources().getColor(R.color.colorTextHeader));
            holder.dc2.setTextColor(vi.getResources().getColor(R.color.colorTextHeader));
            holder.c1.setTextColor(vi.getResources().getColor(R.color.colorTextHeader));
            holder.c2.setTextColor(vi.getResources().getColor(R.color.colorTextHeader));
        }else{
            tempValues = null;
            tempValues = (dataList) data.get(position);
            if(tempValues.getIsHeader() == "YES"){
                holder.no.setBackgroundResource(R.color.colorHeader);
                holder.namaProv.setBackgroundResource(R.color.colorHeader);
                holder.luasPanen.setBackgroundResource(R.color.colorHeader);
                holder.produksi.setBackgroundResource(R.color.colorHeader);
                holder.dc1.setBackgroundResource(R.color.colorHeader);
                holder.dc2.setBackgroundResource(R.color.colorHeader);
                holder.c1.setBackgroundResource(R.color.colorHeader);
                holder.c2.setBackgroundResource(R.color.colorHeader);

                holder.no.setTextColor(vi.getResources().getColor(R.color.colorTextHeader));
                holder.namaProv.setTextColor(vi.getResources().getColor(R.color.colorTextHeader));
                holder.luasPanen.setTextColor(vi.getResources().getColor(R.color.colorTextHeader));
                holder.produksi.setTextColor(vi.getResources().getColor(R.color.colorTextHeader));
                holder.dc1.setTextColor(vi.getResources().getColor(R.color.colorTextHeader));
                holder.dc2.setTextColor(vi.getResources().getColor(R.color.colorTextHeader));
                holder.c1.setTextColor(vi.getResources().getColor(R.color.colorTextHeader));
                holder.c2.setTextColor(vi.getResources().getColor(R.color.colorTextHeader));
            }else if(tempValues.getIsCluster() == "YES"){
                holder.dc1.setVisibility(View.GONE);
                holder.dc2.setVisibility(View.GONE);
                holder.c1.setVisibility(View.GONE);
                holder.c2.setVisibility(View.GONE);
                if(tempValues.getNamaProv() == "-") {
                    holder.namaProv.setVisibility(View.GONE);
                }

                if(tempValues.getIsClusterPosition() == "BEGIN"){
                    holder.garis1.setVisibility(View.VISIBLE);
                    holder.garis2.setVisibility(View.GONE);
                    if(tempValues.getNoPerulangan() != null) {
                        holder.noPerulangan.setVisibility(View.VISIBLE);
                    }
                }else if(tempValues.getIsClusterPosition() == "END"){
                    holder.garis1.setVisibility(View.GONE);
                    holder.garis2.setVisibility(View.VISIBLE);
                    holder.noPerulangan.setVisibility(View.GONE);
                }
                if(tempValues.getNo() == "C" || tempValues.getIsClusterPosition() == "OKE"){
                    holder.no.setBackgroundResource(R.color.colorHeader1);
                    holder.namaProv.setBackgroundResource(R.color.colorHeader1);
                    holder.luasPanen.setBackgroundResource(R.color.colorHeader1);
                    holder.produksi.setBackgroundResource(R.color.colorHeader1);
                    holder.dc1.setBackgroundResource(R.color.colorHeader1);
                    holder.dc2.setBackgroundResource(R.color.colorHeader1);
                    holder.c1.setBackgroundResource(R.color.colorHeader1);
                    holder.c2.setBackgroundResource(R.color.colorHeader1);


                    holder.no.setTextColor(vi.getResources().getColor(R.color.colorTextHeader1));
                    holder.namaProv.setTextColor(vi.getResources().getColor(R.color.colorTextHeader1));
                    holder.luasPanen.setTextColor(vi.getResources().getColor(R.color.colorTextHeader1));
                    holder.produksi.setTextColor(vi.getResources().getColor(R.color.colorTextHeader1));
                    holder.dc1.setTextColor(vi.getResources().getColor(R.color.colorTextHeader1));
                    holder.dc2.setTextColor(vi.getResources().getColor(R.color.colorTextHeader1));
                    holder.c1.setTextColor(vi.getResources().getColor(R.color.colorTextHeader1));
                    holder.c2.setTextColor(vi.getResources().getColor(R.color.colorTextHeader1));

                    holder.noPerulangan.setText("Perulangan Ke " + tempValues.getNoPerulangan());
                }else{
                    holder.no.setBackgroundResource(R.color.colorContent1);
                    holder.namaProv.setBackgroundResource(R.color.colorContent1);
                    holder.luasPanen.setBackgroundResource(R.color.colorContent1);
                    holder.produksi.setBackgroundResource(R.color.colorContent1);
                    holder.dc1.setBackgroundResource(R.color.colorContent1);
                    holder.dc2.setBackgroundResource(R.color.colorContent1);
                    holder.c1.setBackgroundResource(R.color.colorContent1);
                    holder.c2.setBackgroundResource(R.color.colorContent1);

                    holder.no.setTextColor(vi.getResources().getColor(R.color.colorTextContent1));
                    holder.namaProv.setTextColor(vi.getResources().getColor(R.color.colorTextContent1));
                    holder.luasPanen.setTextColor(vi.getResources().getColor(R.color.colorTextContent1));
                    holder.produksi.setTextColor(vi.getResources().getColor(R.color.colorTextContent1));
                    holder.dc1.setTextColor(vi.getResources().getColor(R.color.colorTextContent1));
                    holder.dc2.setTextColor(vi.getResources().getColor(R.color.colorTextContent1));
                    holder.c1.setTextColor(vi.getResources().getColor(R.color.colorTextContent1));
                    holder.c2.setTextColor(vi.getResources().getColor(R.color.colorTextContent1));
                }
            }else{
                holder.dc1.setVisibility(View.VISIBLE);
                holder.dc2.setVisibility(View.VISIBLE);
                holder.c1.setVisibility(View.VISIBLE);
                holder.c2.setVisibility(View.VISIBLE);
                holder.namaProv.setVisibility(View.VISIBLE);
                holder.garis1.setVisibility(View.GONE);
                holder.garis2.setVisibility(View.GONE);
                holder.noPerulangan.setVisibility(View.GONE);

                holder.no.setBackgroundResource(R.color.colorContent);
                holder.namaProv.setBackgroundResource(R.color.colorContent);
                holder.luasPanen.setBackgroundResource(R.color.colorContent);
                holder.produksi.setBackgroundResource(R.color.colorContent);
                holder.dc1.setBackgroundResource(R.color.colorContent);
                holder.dc2.setBackgroundResource(R.color.colorContent);
                holder.c1.setBackgroundResource(R.color.colorContent);
                holder.c2.setBackgroundResource(R.color.colorContent);

                holder.no.setTextColor(vi.getResources().getColor(R.color.colorTextContent));
                holder.namaProv.setTextColor(vi.getResources().getColor(R.color.colorTextContent));
                holder.luasPanen.setTextColor(vi.getResources().getColor(R.color.colorTextContent));
                holder.produksi.setTextColor(vi.getResources().getColor(R.color.colorTextContent));
                holder.dc1.setTextColor(vi.getResources().getColor(R.color.colorTextContent));
                holder.dc2.setTextColor(vi.getResources().getColor(R.color.colorTextContent));
                holder.c1.setTextColor(vi.getResources().getColor(R.color.colorTextContent));
                holder.c2.setTextColor(vi.getResources().getColor(R.color.colorTextContent));
            }

            holder.no.setText(tempValues.getNo());
            holder.namaProv.setText(tempValues.getNamaProv());
            holder.luasPanen.setText(tempValues.getLuasPanen());
            holder.produksi.setText(tempValues.getProduksi());
            holder.dc1.setText(tempValues.getDc1());
            holder.dc2.setText(tempValues.getDc2());
            holder.c1.setText(tempValues.getC1());
            holder.c2.setText(tempValues.getC2());

            vi.setOnClickListener(new OnItemClickListener(position));
        }
        return vi;
    }

    private class OnItemClickListener implements OnClickListener{
        private int mPosition;
        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            MainActivity sct = (MainActivity) activity;
            sct.onItemClick(mPosition);
        }
    }
    @Override
    public void onClick(View v) {

    }
}
