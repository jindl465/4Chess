package com.cnlab.caucse.chessteample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cnlab.caucse.chessteample.ChattingListView;
import com.cnlab.caucse.chessteample.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ChattingListView> listViewArrayList = new ArrayList<ChattingListView>();

    public ListViewAdapter() {

    }

    public void additem(String id, String message){
        ChattingListView item = new ChattingListView();

        item.setid(id);
        item.setChattingMessage(message);

        listViewArrayList.add(item);
    }

    @Override
    public int getCount() {
        return listViewArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.chatting, parent, false);

        }
            // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView idTextView = (TextView) convertView.findViewById(R.id.chattingID);
        TextView messageTextView = (TextView) convertView.findViewById(R.id.chattingMessage);

            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ChattingListView listViewItem = listViewArrayList.get(position);

            // 아이템 내 각 위젯에 데이터 반영
        idTextView.setText(listViewItem.getChattingID());
        messageTextView.setText(listViewItem.getChattingMessage());

        return convertView;
    }
}
